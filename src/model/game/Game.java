package model.game;

import model.game.board.Pawn;
import model.game.team.ArtificialPlayer;
import model.game.team.Player;
import model.game.team.Team;
import model.game.team.TeamColor;
import utils.observer.Observable;
import view.subviews.gameview.GameView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * The global Game class, it handles a whole game of PawnTest l'Initié from the start to the end.
 */
public class Game extends Observable<GameView> {
    /**
     * The Game data (contains all the data of a game instance)
     */
    protected GameData gameData = new GameData();

    /**
     * The Game constructor, add teams, players and start the game
     *
     * @param aiMode  is the game in ai mode (against computer) ?
     * @param duoMode is the game in duo mode ?
     */
    public Game(boolean aiMode, boolean duoMode) {
        this.gameData.setAiMode(aiMode);
        this.gameData.setDuoMode(duoMode);

        this.initializeTeams();

        this.gameData.setCurrentTeamIndex((int) (Math.random() * this.gameData.getTeams().length));

        this.AISelectPawn();
    }

    /**
     * Check if a move is allowed
     *
     * @param move the position of the move
     * @return is the move allowed
     */
    public boolean isMoveAllowed(Position move) {
        boolean allowed = false;

        int i = 0;
        while (i < this.gameData.getAllowedMoves().size() && !allowed) {
            Position position = this.gameData.getAllowedMoves().get(i);
            if (move.getLine() == position.getLine() && move.getColumn() == position.getColumn()) {
                allowed = true;
            }
            i++;
        }

        return allowed;
    }

    /**
     * Check if a pawn is selectable
     *
     * @param position the position of the selection
     * @return is the position selectable
     */
    public boolean isPawnSelectable(Position position) {
        boolean ret;
        ret = position.getLine() >= 0 && position.getColumn() >= 0;

        if (ret) {
            ret = position.getLine() < this.getBoardSize() && position.getColumn() < this.getBoardSize();
        }

        if (ret) {
            Pawn pawn = this.gameData.getBoard().getArray()[position.getLine()][position.getColumn()];
            Team currentTeam = this.getCurrentTeam();
            ret = currentTeam.controlPawn(pawn);
        }
        return ret;
    }

    /**
     * Move the selected pawn to the target position
     *
     * @param position the target position
     */
    public void moveSelectedPawn(Position position) {
        this.gameData.setMovingPawn(false);
        this.gameData.getBoard().getArray()[position.getLine()][position.getColumn()] = this.gameData.getBoard().getArray()[this.gameData.getSelectedPawn().getLine()][this.gameData.getSelectedPawn().getColumn()];
        this.gameData.getBoard().getArray()[this.gameData.getSelectedPawn().getLine()][this.gameData.getSelectedPawn().getColumn()] = Pawn.EMPTY;

        boolean win = this.isTeamWin(this.getCurrentTeam().getTeamColor());
        boolean opponentWin = this.isTeamWin(this.getCurrentTeam().getOpponentTeamColor());
        if (win && opponentWin) {
            this.gameData.setRunning(false);
            this.notifyGameWinner(null);
        } else if (win) {
            this.gameData.setWinner(this.getCurrentTeam());
            this.gameData.setRunning(false);
            this.notifyGameWinner(this.getCurrentTeam());
        } else if (opponentWin) {
            this.gameData.setWinner(this.getOpponentTeam());
            this.gameData.setRunning(false);
            this.notifyGameWinner(this.getOpponentTeam());
        }

        Team currentTeam = this.getCurrentTeam();
        if (!currentTeam.nextPlayerPlay()) {
            this.gameData.setCurrentTeamIndex((this.gameData.getCurrentTeamIndex() + 1) % this.gameData.getTeams().length);
        }
        this.notifyPawnMoved();

        this.AISelectPawn();
    }

    private void AISelectPawn() {
        if (!this.isHumanUserTurn()) {
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    this.setSelectedPawn(((ArtificialPlayer) this.getCurrentTeam().getCurrentPlayer()).getSelectedPosition(this.gameData));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    private void AIMovePawn() {
        if (!this.isHumanUserTurn()) {
            new Thread(() -> {
                try {
                    Thread.sleep(3000);
                    this.moveSelectedPawn(((ArtificialPlayer) this.getCurrentTeam().getCurrentPlayer()).getMovePosition(this.gameData));
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();
        }
    }

    /**
     * Save the game data to a json file
     */
    public void save() {
        this.gameData.save();
    }

    /**
     * Check if a pawn is selectable
     *
     * @param pawn the pawn
     * @return is the pawn selectable
     */
    public boolean isPawnSelectable(Pawn pawn) {
        Team currentTeam = this.getCurrentTeam();
        return currentTeam.controlPawn(pawn);
    }

    /**
     * Notify observers that there is a game winner (and transmit the winning team)
     *
     * @param team the team
     */
    protected void notifyGameWinner(Team team) {
        this.forEachObserver(gameView -> gameView.gameWinner(team));
    }

    /**
     * Initialize teams and add players into them
     */
    protected void initializeTeams() {
        this.gameData.getTeams()[0] = new Team(TeamColor.BLUE);
        this.gameData.getTeams()[1] = new Team(TeamColor.RED);

        this.gameData.getTeams()[0].addPlayer(new Player("Player 1"));
        if (this.gameData.isAiMode()) {
            this.gameData.getTeams()[1].addPlayer(new ArtificialPlayer("Player 1"));
        } else {
            this.gameData.getTeams()[1].addPlayer(new Player("Player 1"));
        }

        if (this.gameData.isDuoMode()) {
            this.gameData.getTeams()[0].addPlayer(new Player("Player 2"));
            if (this.gameData.isAiMode()) {
                this.gameData.getTeams()[1].addPlayer(new ArtificialPlayer("Player 2"));
            } else {
                this.gameData.getTeams()[1].addPlayer(new Player("Player 2"));
            }
        }
    }

    /**
     * Notify observers that a pawn moved.
     */
    private void notifyPawnMoved() {
        this.forEachObserver(gameView -> gameView.pawnMoved(this));
    }

    /**
     * Notify observers that a pawn has been selected.
     */
    private void notifyPawnSelected() {
        this.forEachObserver(gameView -> gameView.pawnSelected(this));
    }

    /**
     * Sets allowed moves (calculate all possible positions where the currently selected pawn can move)
     */
    private void setAllowedMoves() {
        this.gameData.getAllowedMoves().clear();

        for (Position position : this.gameData.getBoard().generatePositionsFrom(this.gameData.getSelectedPawn())) {
            if (this.isMoveValid(position)) {
                this.gameData.getAllowedMoves().add(position);
            }
        }
    }

    /**
     * Check if a particular position is a valid move for the currently selected pawn
     *
     * @param position the position
     * @return the boolean
     */
    private boolean isMoveValid(Position position) {
        boolean validMove = true;

        Team currentTeam = this.getCurrentTeam();
        if (this.gameData.getBoard().isPositionValid(position) && !currentTeam.controlPawn(this.gameData.getBoard().getArray()[position.getLine()][position.getColumn()])) { // destination must be on the board and can't be my pawn
            int deltaLine = position.getLine() - this.gameData.getSelectedPawn().getLine();
            int lineGradient = deltaLine == 0 ? 0 : Math.abs(deltaLine) / deltaLine; // line direction

            int deltaColumn = position.getColumn() - this.gameData.getSelectedPawn().getColumn();
            int columnGradient = deltaColumn == 0 ? 0 : Math.abs(deltaColumn) / deltaColumn; // column direction

            int line = this.gameData.getSelectedPawn().getLine() + lineGradient;
            int column = this.gameData.getSelectedPawn().getColumn() + columnGradient;
            while (this.gameData.getBoard().isPositionValid(new Position(line, column)) && validMove && !(position.getLine() == line && position.getColumn() == column)) {
                if (currentTeam.enemyHasPawn(this.gameData.getBoard().getArray()[line][column])) { // can't go hover opponent's pawn
                    validMove = false;
                }
                line += lineGradient;
                column += columnGradient;
            }
        } else {
            validMove = false;
        }

        return validMove;
    }

    /**
     * Check if a particular team won the game (by searching chains of its team color on the board)
     *
     * @param teamColor the team color
     * @return the boolean
     */
    private boolean isTeamWin(TeamColor teamColor) {
        // Convert TeamColor to Pawn
        Pawn pawn = Pawn.getPawnFromTeamColor(teamColor);

        // find all positions around each pawn of a color
        Map<Position, Position[]> toMarkMap = this.gameData.getBoard().getPawnPositionsAround(Pawn.ZEN, pawn);
        Collection<Position> markedList = new ArrayList<>();

        Position randomPosition = (Position) toMarkMap.keySet().toArray()[0]; // random position because order is not always kept in an hashmap
        markedList.add(randomPosition); // Set a random one as marked
        toMarkMap.remove(randomPosition); // Remove from the map of "to mark" positions
        boolean changeMarkedPosition = true;
        while (!toMarkMap.isEmpty() && changeMarkedPosition) { // iterate while there is change or until mark map is empty (so there is a winner)
            changeMarkedPosition = false;

            Iterator<Map.Entry<Position, Position[]>> iterator = toMarkMap.entrySet().iterator();
            while (iterator.hasNext()) { // iterate over all the positions of pawns
                Map.Entry<Position, Position[]> entryToMark = iterator.next();
                boolean noPawnAround = true;
                int positionAroundIndex = 0;
                while (noPawnAround && positionAroundIndex < entryToMark.getValue().length) { // iterate over all the positions around pawns until end or pawn around marked
                    Position position = entryToMark.getValue()[positionAroundIndex];
                    if (markedList.contains(position)) { // If position around a marked position, then mark it too
                        noPawnAround = false;
                        changeMarkedPosition = true;

                        Position newMarkedPosition = entryToMark.getKey();
                        iterator.remove(); // remove from "to mark" list
                        markedList.add(newMarkedPosition); // add to marked list
                    }

                    positionAroundIndex++;
                }
            }
        }

        return toMarkMap.isEmpty();
    }

    /**
     * Notify an observer about the whole state of the app.
     * This method should generally be used to initialize this observer.
     *
     * @param observer the observer
     */
    @Override
    protected void notifyUpdateEverything(GameView observer) {
        observer.updateGame(this);
    }

    /**
     * Add observer.
     *
     * @param observer the observer
     */
    @Override
    public void addObserver(GameView observer) {
        super.addObserver(observer);
        observer.start(this);
    }

    /**
     * Sets game data.
     *
     * @param gameData the game data
     */
    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    /**
     * Set the selected pawn
     *
     * @param position the position of the pawn to select
     */
    public void setSelectedPawn(Position position) {
        this.gameData.setMovingPawn(true);
        this.gameData.setSelectedPawn(position);
        this.setAllowedMoves();
        this.notifyPawnSelected();

        this.AIMovePawn();
    }

    /**
     * Get the allowed moves
     *
     * @return the allowed moves
     */
    public List<Position> getAllowedMoves() {
        return Collections.unmodifiableList(this.gameData.getAllowedMoves());
    }

    /**
     * Get the game board array
     *
     * @return the game board array
     */
    public Pawn[][] getBoardArray() {
        return this.gameData.getBoard().getArray();
    }

    /**
     * Get the game board array size
     *
     * @return the game board array size
     */
    public int getBoardSize() {
        return this.getBoardArray().length;
    }

    /**
     * Get the player's name of the one playing of the team playing during this turn
     *
     * @return the player's name
     */
    public String getCurrentPlayerName() {
        Team currentTeam = this.getCurrentTeam();
        return currentTeam.getCurrentPlayerName();
    }

    /**
     * Get the team playing during this turn
     *
     * @return the team
     */
    public Team getCurrentTeam() {
        return this.gameData.getTeams()[this.gameData.getCurrentTeamIndex()];
    }

    /**
     * Gets opponent team.
     *
     * @return the opponent team
     */
    private Team getOpponentTeam() {
        return this.gameData.getTeams()[(this.gameData.getCurrentTeamIndex() + 1) % this.gameData.getTeams().length];
    }

    /**
     * Gets the team who won the game (if the game is finished).
     *
     * @return the winner
     */
    public Team getWinner() {
        return this.gameData.getWinner();
    }

    /**
     * Gets is the game in ai mode against computer ?.
     *
     * @return Value of is the game in ai mode against computer ?.
     */
    public boolean isAiMode() {
        return this.gameData.isAiMode();
    }

    /**
     * Sets ai mode.
     *
     * @param aiMode the ai mode
     */
    public void setAiMode(boolean aiMode) {
        this.gameData.setAiMode(aiMode);
    }

    /**
     * Gets is the game in duo mode ?.
     *
     * @return Value of is the game in duo mode ?.
     */
    public boolean isDuoMode() {
        return this.gameData.isDuoMode();
    }

    /**
     * Sets duo mode.
     *
     * @param duoMode the duo mode
     */
    public void setDuoMode(boolean duoMode) {
        this.gameData.setDuoMode(duoMode);
    }

    /**
     * Is human user turn
     *
     * @return the boolean
     */
    public boolean isHumanUserTurn() {
        return this.getCurrentTeam().getCurrentPlayer().isHumanPlayer();
    }

    /**
     * Gets Is the pawn moving ? so if true we are selecting a target location for it, if false we are selecting a pawn to move.
     *
     * @return Value of Is the pawn moving ? so if true we are selecting a target location for it, if false we are selecting a pawn to move.
     */
    public boolean isMovingPawn() {
        return this.gameData.isMovingPawn();
    }

    /**
     * Is online boolean.
     *
     * @return the boolean
     */
    public boolean isOnline() {
        return false;
    }

    /**
     * Is finished boolean.
     *
     * @return the boolean
     */
    public boolean isRunning() {
        return this.gameData.isRunning();
    }
}
