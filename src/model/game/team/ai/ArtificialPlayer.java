package model.game.team.ai;

import model.game.Game;
import model.game.GameData;
import model.game.Position;
import model.game.board.Pawn;
import model.game.team.Player;
import model.game.team.Team;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * The type Artificial player.
 */
public class ArtificialPlayer extends Player {
    /**
     * The Last action.
     */
    private Action lastAction;
    /**
     * The Team.
     */
    private Team team;

    /**
     * Instantiates a new Artificial player.
     *
     * @param name the name
     * @param team the team
     */
    public ArtificialPlayer(String name, Team team) {
        super(name);
        this.team = new Team(team.getTeamColor());
    }

    /**
     * Instantiates a new Artificial player.
     */
    public ArtificialPlayer() {
        // Used for kryo deserialization.
    }

    /**
     * This is only intended to be used for the minimax algorithm.
     *
     * @param game the game
     * @return game copy
     */
    private static Game getGameCopy(Game game) {
        Game gameCopy = new Game(false, false);
        GameData gameDataCopy = new GameData();
        gameCopy.setGameData(gameDataCopy);

        for (int i = 0; i < game.getBoardArray().length; i++) {
            for (int j = 0; j < game.getBoardArray()[0].length; j++) {
                gameCopy.getBoardArray()[i][j] = game.getBoardArray()[i][j];
            }
        }

        gameDataCopy.getTeams()[0] = game.getGameData().getTeams()[0];
        Team team = new Team(game.getGameData().getTeams()[1].getTeamColor());
        team.addPlayer(new Player());
        gameDataCopy.getTeams()[1] = team;

        return gameCopy;
    }

    /**
     * Gets move position.
     *
     * @param game the game
     * @return the move position
     */
    public Position getMovePosition(Game game) {
        return this.lastAction.getMovePosition();
    }

    /**
     * Gets selected position.
     *
     * @param game the game
     * @return the selected position
     */
    public Position getSelectedPosition(Game game) {
        this.lastAction = this.getBestAction(game);
        return this.lastAction.getSelectedPosition();
    }

    /**
     * Gets best action.
     *
     * @param game the game
     * @return the best action
     */
    private Action getBestAction(Game game) {
        double maxvalue = -Double.MAX_VALUE;
        Action bestAction = null;

        // Check one move after
        for (Action action : this.getPossibleActions(game)) {
            Game gameCopy = ArtificialPlayer.getGameCopy(game);
            gameCopy.setSelectedPawn(action.getSelectedPosition());
            gameCopy.moveSelectedPawn(action.getMovePosition());

            // Check two moves after
            for (Action innerAction : this.getPossibleActions(game)) {
                Game innerGameCopy = ArtificialPlayer.getGameCopy(game);
                innerGameCopy.setSelectedPawn(innerAction.getSelectedPosition());
                innerGameCopy.moveSelectedPawn(innerAction.getMovePosition());

                double value = game.getGameData().getBoard().getPawnPositionsAround(Pawn.ZEN, Pawn.getPawnFromTeamColor(this.team.getTeamColor())).size() - this.getStateValue(innerGameCopy) - 1;
                if (value > maxvalue) {
                    maxvalue = value;
                    bestAction = innerAction;
                }
            }
        }

        return bestAction;
    }

    /**
     * Gets allowed selection.
     *
     * @param gameData the game data
     * @return the allowed selection
     */
    private Iterable<Position> getAllowedSelection(GameData gameData) {
        Pawn[][] board = gameData.getBoard().getArray();
        return this.getAllowedSelection(board);
    }

    /**
     * Gets allowed selection.
     *
     * @param pawn the pawn
     * @return the allowed selection
     */
    private Iterable<Position> getAllowedSelection(Pawn[][] pawn) {
        Collection<Position> allowedSelection = new ArrayList<>();

        for (int i = 0; i < pawn.length; i++) {
            for (int j = 0; j < pawn[0].length; j++) {
                if (this.team.controlPawn(pawn[i][j])) {
                    allowedSelection.add(new Position(i, j));
                }
            }
        }

        return allowedSelection;
    }

    /**
     * Gets state value.
     *
     * @param game the game
     * @return the state value
     */
    private double getStateValue(Game game) {
        return game.getLargestChain(this.team.getTeamColor());
    }

    /**
     * Gets possible actions.
     *
     * @param game the game
     * @return the possible actions
     */
    private Iterable<Action> getPossibleActions(Game game) {
        Collection<Action> actions = new ArrayList<>();

        Iterable<Position> positions = this.getAllowedSelection(game.getGameData());

        // Get selectable positions
        for (Position selectPosition : positions) {
            Game gameCopy = ArtificialPlayer.getGameCopy(game);
            gameCopy.setSelectedPawn(selectPosition);
            List<Position> movePositions = gameCopy.getAllowedMoves();

            // get move positions with respect to the selectable position
            for (Position position : movePositions) {
                actions.add(new Action(selectPosition, position));
            }
        }

        return actions;
    }

    /**
     * Is human player boolean.
     *
     * @return the boolean
     */
    @Override
    public boolean isHumanPlayer() {
        return false;
    }
}
