package grading;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AdvBalancedParenTreeTest {

    public static final String isBalanced1Input = "[][()][(]";
    public static final boolean isBalanced1Output = false;
    public static final String isBalanced2Input = "[][(())][]()(())";
    public static final boolean isBalanced2Output = true;
    public static final String isBalanced3Input = "[][())][]()(())";
    public static final boolean isBalanced3Output = false;
    public static final String isBalanced4Input = "[()][(())][()]";
    public static final boolean isBalanced4Output = true;
    public static final String isBalanced5Input = "[([())(()))()][(())][()]";
    public static final boolean isBalanced5Output = false;
    public static final String isBalanced6Input = "[(())()][(())][()(())]";
    public static final boolean isBalanced6Output = true;

    void runTest(String input) {
        AdvBalancedParenTree tree = new AdvBalancedParenTree();
        try {
            assertEquals(isBalanced1Output, tree.isBalanced(input));
        } catch(Exception e) {
            assertEquals(e, null);
        }
    }

    @Test
    void isBalanced1() {
        runTest(isBalanced1Input);
    }

    @Test
    void isBalanced2() {
        runTest(isBalanced2Input);
    }

    @Test
    void isBalanced3() {
        runTest(isBalanced3Input);
    }

    @Test
    void isBalanced4() {
        runTest(isBalanced4Input);
    }

    @Test
    void isBalanced5() {
        runTest(isBalanced5Input);
    }

    @Test
    void isBalanced6() {
        runTest(isBalanced6Input);
    }
}