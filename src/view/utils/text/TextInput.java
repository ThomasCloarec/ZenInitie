package view.utils.text;

import model.game.Game;
import model.game.Position;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

import static view.utils.text.AppText.*;

/**
 * The type Text input.
 */
public class TextInput {
    /**
     * The constant sc.
     */
    private static final Scanner sc = new Scanner(System.in);

    /**
     * Gets answers count.
     *
     * @param question the question
     * @return the answers count
     */
    public static int getAnswersCount(String question) {
        int count = 0;

        while (hasTextFor(question + ".answer" + (count + 1))) {
            count++;
        }

        return count;
    }

    /**
     * Gets int answer.
     *
     * @param question  the question
     * @param condition the condition
     * @return the int answer
     */
    public static int getIntAnswer(String question, Predicate<Integer> condition) {
        int input = 0;
        int answerNumber = TextInput.getAnswersCount(question);

        do {
            try {
                System.out.println(preQuestion + getTextFor(question));
                for (int i = 1; i <= answerNumber; i++) {
                    System.out.println(preAnswer + getTextFor(question + ".answer" + i) + " (" + i + ")");
                }

                System.out.print(preInput);
                input = TextInput.sc.nextInt();

                if (!condition.test(input)) {
                    System.out.println(preInformation + condition);
                }
            } catch (InputMismatchException ignored) {
                System.out.println(preInformation + getTextFor("global.utils.input.error.type.number"));
            } finally {
                try {
                    TextInput.sc.nextLine();
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        } while (!condition.test(input));

        return input;
    }

    /**
     * Gets char answer.
     *
     * @param question  the question
     * @param condition the condition
     * @return the char answer
     */
    public static char getCharAnswer(String question, NamedPredicate<Character> condition) {
        char input = 0;

        do {
            try {
                System.out.println(preQuestion + getTextFor(question));
                System.out.print(preInput);
                input = TextInput.sc.next().toUpperCase().charAt(0);

                if (!condition.test(input)) {
                    System.out.println(preInformation + condition);
                }
            } catch (InputMismatchException ignored) {
                System.out.println(preInformation + getTextFor("global.utils.input.error.type.character"));
            } finally {
                TextInput.sc.nextLine();
            }
        } while (!condition.test(input));

        return input;
    }

    /**
     * Gets menu answer.
     *
     * @param question the question
     * @return the menu answer
     */
    public static int getMenuAnswer(String question) {
        System.out.println(preInformation + getTextFor(question.substring(0, question.lastIndexOf("."))));
        int answerNumber = TextInput.getAnswersCount(question);
        return TextInput.getIntAnswer(
                question,
                new NamedPredicate<>(
                        getTextFor("global.utils.input.error.range") + getTextFor("global.word.and") + answerNumber
                        , input -> input >= 1 && input <= answerNumber));
    }

    /**
     * Gets move position answer.
     *
     * @param game the game
     * @return the move position answer
     */
    public static Position getMovePositionAnswer(Game game) {
        int line = TextInput.getIntAnswer(
                "game.getMoveLine",
                new NamedPredicate<>(
                        getTextFor("global.utils.input.error.range") + getTextFor("global.word.and") + 11
                        , input -> input >= 1 && input <= game.getBoardSize()));

        char column = TextInput.getCharAnswer(
                "game.getMoveColumn",
                new NamedPredicate<>(
                        getTextFor("global.utils.input.error.range") + "A " + getTextFor("global.word.and") + 'K',
                        input -> input >= 'A' && input <= 64 + game.getBoardSize()));

        return new Position(line, column);
    }

    /**
     * Gets select position answer.
     *
     * @param game the game
     * @return the select position answer
     */
    public static Position getSelectPositionAnswer(Game game) {
        int line = TextInput.getIntAnswer(
                "game.getSelectLine",
                new NamedPredicate<>(
                        getTextFor("global.utils.input.error.range") + getTextFor("global.word.and") + 11
                        , input -> input >= 1 && input <= game.getBoardSize()));

        char column = TextInput.getCharAnswer(
                "game.getSelectColumn",
                new NamedPredicate<>(
                        getTextFor("global.utils.input.error.range") + "A " + getTextFor("global.word.and") + 'K',
                        input -> input >= 'A' && input <= 64 + game.getBoardSize()));

        return new Position(line, column);
    }
}
