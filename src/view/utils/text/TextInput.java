package view.utils.text;

import model.game.Position;

import java.util.InputMismatchException;
import java.util.Scanner;

import static view.utils.text.AppText.*;

public class TextInput {
    private static final Scanner sc = new Scanner(System.in);

    public static int getAnswersCount(String question) {
        int count = 0;

        while (hasTextFor(question + ".answer" + (count + 1))) {
            count++;
        }

        return count;
    }

    public static int getIntAnswer(String question) {
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

                if (answerNumber != 0 && (input < 1 || input > answerNumber)) {
                    System.out.println(preInformation + getTextFor("global.utils.input.error.range") + getTextFor("global.word.and") + answerNumber);
                }
            } catch (InputMismatchException ignored) {
                System.out.println(preInformation + getTextFor("global.utils.input.error.type.number"));
            } finally {
                TextInput.sc.nextLine();
            }
        } while (answerNumber != 0 && (input < 1 || input > answerNumber));

        return input;
    }

    public static char getCharAnswer(String question) {
        char input = 0;

        do {
            try {
                System.out.println(preQuestion + getTextFor(question));
                System.out.print(preInput);
                input = TextInput.sc.next().toUpperCase().charAt(0);

                if (input < 'A' || input > 'K') {
                    System.out.println(preInformation + getTextFor("global.utils.input.error.range") + "A " + getTextFor("global.word.and") + 'K');
                }
            } catch (InputMismatchException ignored) {
                System.out.println(preInformation + getTextFor("global.utils.input.error.type.character"));
            } finally {
                TextInput.sc.nextLine();
            }
        } while (input < 'A' || input > 'K');

        return input;
    }

    public static int getMenuAnswer(String question) {
        System.out.println(preInformation + getTextFor(question.substring(0, question.lastIndexOf("."))));
        return TextInput.getIntAnswer(question);
    }

    public static void waitingEnterScanner() {
        TextInput.sc.nextLine();
    }

    public static Position getMoveAnswer() {
        int line = TextInput.getIntAnswer("game.getMoveLine");
        char column = TextInput.getCharAnswer("game.getMoveColumn");
        return new Position(line, column);
    }

    public static Position getSelectPositionAnswer() {
        int line = TextInput.getIntAnswer("game.getSelectLine");
        char column = TextInput.getCharAnswer("game.getSelectColumn");
        return new Position(line, column);
    }
}
