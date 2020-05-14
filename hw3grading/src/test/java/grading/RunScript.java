package grading;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RunScript {
    @Test
    public void runTest() {
        String name = System.getProperty("name");
        Integer extraCredit = System.getProperty("extra").equals("true") ?
                GradingScript.weights.get(GradingScript.Categories.ExtraCredit) : 0;
        GradingScript.run(name, extraCredit);
        assertEquals(true, true);
    }
}
