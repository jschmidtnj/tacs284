package grading;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.FileDescriptor;
import java.io.FileOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.*;

class BalancedParenTreeTest {

    public static final String parseBasic1Input = "()";
    public static final String parseBasic1Output = parseBasic1Input;
    public static final String parseBasic2Input = "(";
    public static final String parseBasic2Output = "";
    public static final String parseBasic3Input = "(())";
    public static final String parseBasic3Output = parseBasic3Input;
    public static final String parseBasic4Input = "(()))";
    public static final String parseBasic4Output = "";
    public static final String parseBasic5Input = "(())()";
    public static final String parseBasic5Output = parseBasic5Input;
    public static final String parseBasic6Input = "(())(";
    public static final String parseBasic6Output = "";
    public static final String parseAdvanced1Input = "(())((())(()))()";
    public static final String parseAdvanced1Output = parseAdvanced1Input;
    public static final String parseAdvanced2Input = "(())((())()(())";
    public static final String parseAdvanced2Output = "";
    public static final String parseAdvanced3Input = "(())(((()))(()))";
    public static final String parseAdvanced3Output = parseAdvanced3Input;
    public static final String parseAdvanced4Input = "(())(())()((())";
    public static final String parseAdvanced4Output = "";

    private String capturePrint(BalancedParenTree tree) {
        // Start capturing
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        System.setOut(new PrintStream(buffer));

        // Run what is supposed to output something
        tree.print();

        // Stop capturing
        System.setOut(new PrintStream(new FileOutputStream(FileDescriptor.out)));

        // Use captured content
        String content = buffer.toString();
        buffer.reset();
        return content.trim().replace("\n", "");
    }

    void handleParse(String input, boolean handleException) {
        BalancedParenTree tree = new BalancedParenTree();
        String output = input;
        if (handleException) {
            try {
                tree.parse(input);
            } catch (Exception e) {
                assertThrows(Exception.class, () -> {
                    throw e;
                });
                return;
            }
        } else {
            assertDoesNotThrow(() -> tree.parse(input));
        }
        String res = capturePrint(tree);
        if (handleException && (res.length() == 0 || output.contains(res))) {
            output = res;
        } else if (output.length() < res.length()) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < res.length() - 1; i++) {
                sb.append(res.charAt(i));
                if (res.charAt(i) == '(' && res.charAt(i + 1) == ')') {
                    sb.append('N');
                }
            }
            sb.append(res.charAt(res.length() - 1));
            res = sb.toString();
        }
        assertEquals(output, res);
    }

    @Test
    void parseBasic1() {
        handleParse(parseBasic1Input, false);
    }

    @Test
    void parseBasic2() {
        handleParse(parseBasic2Input, true);
    }

    @Test
    void parseBasic3() {
        handleParse(parseBasic3Input, false);
    }

    @Test
    void parseBasic4() {
        handleParse(parseBasic4Input, true);
    }

    @Test
    void parseBasic5() {
        handleParse(parseBasic5Input, false);
    }

    @Test
    void parseBasic6() {
        handleParse(parseBasic6Input, true);
    }

    @Test
    void parseAdvanced1() {
        handleParse(parseAdvanced1Input, false);
    }

    @Test
    void parseAdvanced2() {
        handleParse(parseAdvanced2Input, true);
    }

    @Test
    void parseAdvanced3() {
        handleParse(parseAdvanced3Input, false);
    }

    @Test
    void parseAdvanced4() {
        handleParse(parseAdvanced4Input, true);
    }
}
