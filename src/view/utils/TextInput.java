package view.utils;

import java.util.InputMismatchException;
import java.util.Scanner;

public class TextInput {
    private static final Scanner sc = new Scanner(System.in);

    public static int getAnswer(String question, int answerNumber) {
        int input = 0;
        do {
            try {
                System.out.println(GameText.preInformation + GameText.get(question.substring(0, question.lastIndexOf("."))));
                System.out.println(GameText.preQuestion + GameText.get(question));
                for (int i = 1; i <= answerNumber; i++) {
                    System.out.println(GameText.preAnswer + GameText.get(question + ".answer" + i) + " (" + i + ")");
                }

                System.out.print(GameText.preInput);
                input = TextInput.sc.nextInt();

                if (input < 1 || input > answerNumber) {
                    System.out.println(GameText.preInformation + GameText.get("utils.input.error.range") + answerNumber);
                }
            } catch (InputMismatchException ignored) {
                System.out.println(GameText.preInformation + GameText.get("utils.input.error.type"));
            } finally {
                TextInput.sc.nextLine();
            }
        } while (input < 1 || input > answerNumber);

        return input;
    }
}
