package view.utils.text;

import java.io.PrintStream;
import java.io.UnsupportedEncodingException;

public class TextOutput {
    private static PrintStream outStream;

    static {
        try {
            TextOutput.outStream = new PrintStream(System.out, true, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public static void print(String string) {
        TextOutput.outStream.print(string);
    }

    public static void println(String string) {
        TextOutput.outStream.println(string);
    }

    public static void println() {
        TextOutput.outStream.println();
    }
}
