package view.utils.text;

import model.game.Game;
import model.game.Position;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.function.Predicate;

import static view.utils.text.AppText.*;
import static view.utils.text.TextOutput.print;
import static view.utils.text.TextOutput.println;

public class TextInput {
    private static final Scanner sc = new Scanner(System.in);

    public static int getAnswersCount(String question) {
        int count = 0;

        while (hasTextFor(question + ".answer" + (count + 1))) {
            count++;
        }

        return count;
    }

    public static int getIntAnswer(String question, Predicate<Integer> condition) {
        int input = 0;
        int answerNumber = TextInput.getAnswersCount(question);

        do {
            try {
                println(preQuestion + getTextFor(question));
                for (int i = 1; i <= answerNumber; i++) {
                    println(preAnswer + getTextFor(question + ".answer" + i) + " (" + i + ")");
                }

                print(preInput);
                input = TextInput.sc.nextInt();

                if (!condition.test(input)) {
                    println(preInformation + condition);
                }
            } catch (InputMismatchException ignored) {
                println(preInformation + getTextFor("global.utils.input.error.type.number"));
            } finally {
                try {
                    TextInput.sc.nextLine();
                } catch (IndexOutOfBoundsException ignored) {
                }
            }
        } while (!condition.test(input));

        return input;
    }

    public static char getCharAnswer(String question, NamedPredicate<Character> condition) {
        char input = 0;

        do {
            try {
                println(preQuestion + getTextFor(question));
                print(preInput);
                input = TextInput.sc.next().toUpperCase().charAt(0);

                if (!condition.test(input)) {
                    println(preInformation + condition);
                }
            } catch (InputMismatchException ignored) {
                println(preInformation + getTextFor("global.utils.input.error.type.character"));
            } finally {
                TextInput.sc.nextLine();
            }
        } while (!condition.test(input));

        return input;
    }

    public static int getMenuAnswer(String question) {
        println(preInformation + getTextFor(question.substring(0, question.lastIndexOf("."))));
        int answerNumber = TextInput.getAnswersCount(question);
        return TextInput.getIntAnswer(
                question,
                new NamedPredicate<>(
                        getTextFor("global.utils.input.error.range") + getTextFor("global.word.and") + answerNumber
                        , input -> input >= 1 && input <= answerNumber));
    }

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
