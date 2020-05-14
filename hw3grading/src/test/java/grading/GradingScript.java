package grading;

import com.google.common.util.concurrent.SimpleTimeLimiter;
import com.google.common.util.concurrent.TimeLimiter;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class GradingScript {
    public static final int numCodingTests = 16;
    public static final String outputDirectory = "./output";
    public static final String statsFile = "./output/stats.csv";
    public static final String[] sourceFilePaths = {
            "./src/main/java/grading/AdvBalancedParenTree.java",
            "./src/main/java/grading/BalancedParenTree.java",
    };
    public static final int numNamePledgeTests = sourceFilePaths.length * 2;
    public static final int numTests = numNamePledgeTests + numCodingTests;
    public static final String pledge = "i pledge my honor that i have abided by the stevens honor system";
    public static final Map<Categories, Integer> weights = new HashMap() {
        {
            put(Categories.PledgeName, 5);
            put(Categories.Compiles, 30);
            put(Categories.Tests, 70);
            put(Categories.ExtraCredit, 30);
        }
    };
    public static final int numExtraCreditTests = weights.get(Categories.ExtraCredit);
    private static final Integer timeout = 2; // seconds
    public final String studentName;
    public final int extraCredit;
    private FileWriter fileWriter;
    public GradingScript(String studentName, String directory, int extraCredit) {
        File file;
        this.extraCredit = extraCredit;
        this.fileWriter = null;
        this.studentName = studentName;
        String fileName = this.studentName.replace(' ', '_') + ".txt";
        File index = new File(directory);
        if (!index.exists()) {
            if (!index.mkdir()) {
                System.out.println("folder not created");
            }
        }
        try {
            file = new File(index.getPath(), fileName);
            if (file.createNewFile()) {
                System.out.println("File created: " + file.getName());
            }
            this.fileWriter = new FileWriter(file);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void run(String name, int extraCreditPoints) {
        if (name.length() == 0) {
            throw new IllegalArgumentException("name length is 0");
        }
        GradingScript script = new GradingScript(name, outputDirectory, extraCreditPoints);
        script.runTests();
        script.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            throw new IllegalArgumentException("no argument provided");
        }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < args.length; i++) {
            sb.append(args[i]);
            if (i != args.length - 1) {
                sb.append(' ');
            }
        }
        String name = sb.toString();
        run(name, 0);
    }

    public void runTests() {
        try {
            runTestsThrows();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private String getStackTrace(Throwable error) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        error.printStackTrace(pw);
        return sw.toString();
    }

    private boolean runTimedTask(ILambdaFunction given, int testNum, String input, String expectedOutput) throws IOException {
        TimeLimiter limiter = SimpleTimeLimiter.create(Executors.newCachedThreadPool());
        Throwable err = null;
        boolean gotTimeout = false;
        long startTime = System.nanoTime();
        try {
            limiter.callWithTimeout((Callable<Void>) () -> {
                given.run();
                return null;
            }, timeout, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            gotTimeout = true;
        } catch (Throwable e) {
            err = e;
        }
        boolean success = err == null && !gotTimeout;
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        StringBuilder sb = new StringBuilder();
        sb.append("Test: ");
        sb.append(testNum);
        sb.append('/');
        sb.append(numTests);
        sb.append(success ? " successful. " : " failed. ");
        sb.append(" Execution time: ");
        sb.append(duration);
        sb.append("ms.");
        if (gotTimeout) {
            sb.append(" Method timed out.");
        }
        sb.append("\nInput: \"");
        sb.append(input);
        sb.append("\", Expected Output: \"");
        sb.append(expectedOutput);
        sb.append("\".");
        if (err != null) {
            sb.append("\nFound exception:\n");
            sb.append(getStackTrace(err));
        }
        sb.append("\n\n");
        fileWriter.write(sb.toString());
        return success;
    }

    private String addNumCorrect(Categories category, int numCorrect, int outOf) {
        StringBuilder sb = new StringBuilder();
        sb.append('\n');
        sb.append(numCorrect);
        sb.append(" / ");
        sb.append(outOf);
        sb.append(" = ");
        int percent = (int) Math.floor((double) numCorrect / outOf * 100);
        sb.append(percent);
        sb.append("% * ");
        Integer weight = weights.get(category);
        sb.append(weight);
        sb.append("% = ");
        sb.append((double) numCorrect / outOf * weight);
        sb.append("%\n");
        return sb.toString();
    }

    private void writeFinal(int numNamePledgeCorrect, int numCodingCorrect) throws IOException {
        StringBuilder sb = new StringBuilder();
        sb.append("\nTotal tests run:");
        sb.append(numTests);
        sb.append("\nName and pledge:");
        sb.append(addNumCorrect(Categories.PledgeName, numNamePledgeCorrect, numNamePledgeTests));
        sb.append("Compiles:");
        int compiles = 1;
        sb.append(addNumCorrect(Categories.Compiles, compiles, compiles));
        sb.append("Functional Tests:");
        sb.append(addNumCorrect(Categories.Tests, numCodingCorrect, numCodingTests));
        sb.append("Extra Credit:");
        sb.append(addNumCorrect(Categories.ExtraCredit, this.extraCredit, numExtraCreditTests));
        int finalGrade = (int) Math.floor(compiles * weights.get(Categories.Compiles) + (double) numNamePledgeCorrect / numNamePledgeTests * weights.get(Categories.PledgeName) +
                (double) numCodingCorrect / numCodingTests * weights.get(Categories.Tests) + (double) this.extraCredit / numExtraCreditTests * weights.get(Categories.ExtraCredit));
        sb.append("Final grade: ");
        sb.append(finalGrade);
        sb.append("%\n");
        fileWriter.append(sb.toString());
        FileWriter statsFileWriter = new FileWriter(statsFile, true);
        StringBuilder statsBuilder = new StringBuilder();
        statsBuilder.append(studentName);
        statsBuilder.append(',');
        statsBuilder.append(finalGrade);
        statsBuilder.append('\n');
        statsFileWriter.write(statsBuilder.toString());
        statsFileWriter.close();
    }

    private int checkNamePledge() throws IOException {
        int numSuccess = 0;
        StringBuilder sb = new StringBuilder();
        int testNum = 1;
        for (String filePath : sourceFilePaths) {
            File file = new File(filePath);
            Scanner scanner;
            try {
                scanner = new Scanner(file);

            } catch (FileNotFoundException e) {
                throw new IllegalArgumentException("cannot find file at path " + filePath);
            }
            boolean foundPledge = false;
            boolean foundName = false;
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.toLowerCase().contains(pledge)) {
                    foundName = true;
                    foundPledge = true;
                }
                if (foundPledge) {
                    break;
                }
            }
            sb.append("File ");
            sb.append(filePath);
            sb.append('\n');
            sb.append("Test ");
            sb.append(testNum);
            sb.append('/');
            sb.append(numTests);
            sb.append(". Found pledge: ");
            sb.append(foundPledge ? "successful" : "failed");
            sb.append(".\n");
            numSuccess += foundPledge ? 1 : 0;
            testNum++;
            sb.append("Test ");
            sb.append(testNum);
            sb.append('/');
            sb.append(numTests);
            sb.append(". Found name: ");
            sb.append(foundName ? "successful" : "failed");
            sb.append(".\n\n");
            numSuccess += foundName ? 1 : 0;
            testNum++;
        }
        fileWriter.write(sb.toString());
        return numSuccess;
    }

    private void runTestsThrows() throws IOException {
        BalancedParenTreeTest testParse = new BalancedParenTreeTest();
        AdvBalancedParenTreeTest testAdvanced = new AdvBalancedParenTreeTest();
        int numNamePledgeSuccess = checkNamePledge();
        int numCodingSuccess = 0;
        numCodingSuccess += runTimedTask(testParse::parseBasic1, 5,
                BalancedParenTreeTest.parseBasic1Input, BalancedParenTreeTest.parseBasic1Output) ? 1 : 0;
        numCodingSuccess += runTimedTask(testParse::parseBasic2, 6,
                BalancedParenTreeTest.parseBasic2Input, BalancedParenTreeTest.parseBasic2Output) ? 1 : 0;
        numCodingSuccess += runTimedTask(testParse::parseBasic3, 7,
                BalancedParenTreeTest.parseBasic3Input, BalancedParenTreeTest.parseBasic3Output) ? 1 : 0;
        numCodingSuccess += runTimedTask(testParse::parseBasic4, 8,
                BalancedParenTreeTest.parseBasic4Input, BalancedParenTreeTest.parseBasic4Output) ? 1 : 0;
        numCodingSuccess += runTimedTask(testParse::parseBasic5, 9,
                BalancedParenTreeTest.parseBasic5Input, BalancedParenTreeTest.parseBasic5Output) ? 1 : 0;
        numCodingSuccess += runTimedTask(testParse::parseBasic6, 10,
                BalancedParenTreeTest.parseBasic6Input, BalancedParenTreeTest.parseBasic6Output) ? 1 : 0;
        numCodingSuccess += runTimedTask(testParse::parseAdvanced1, 11,
                BalancedParenTreeTest.parseAdvanced1Input, BalancedParenTreeTest.parseAdvanced1Output) ? 1 : 0;
        numCodingSuccess += runTimedTask(testParse::parseAdvanced2, 12,
                BalancedParenTreeTest.parseAdvanced2Input, BalancedParenTreeTest.parseAdvanced2Output) ? 1 : 0;
        numCodingSuccess += runTimedTask(testParse::parseAdvanced3, 13,
                BalancedParenTreeTest.parseAdvanced3Input, BalancedParenTreeTest.parseAdvanced3Output) ? 1 : 0;
        numCodingSuccess += runTimedTask(testParse::parseAdvanced4, 14,
                BalancedParenTreeTest.parseAdvanced4Input, BalancedParenTreeTest.parseAdvanced4Output) ? 1 : 0;
        numCodingSuccess += runTimedTask(testAdvanced::isBalanced1, 15,
                AdvBalancedParenTreeTest.isBalanced1Input, AdvBalancedParenTreeTest.isBalanced1Output ? "true" : "false") ? 1 : 0;
        numCodingSuccess += runTimedTask(testAdvanced::isBalanced2, 16,
                AdvBalancedParenTreeTest.isBalanced2Input, AdvBalancedParenTreeTest.isBalanced2Output ? "true" : "false") ? 1 : 0;
        numCodingSuccess += runTimedTask(testAdvanced::isBalanced3, 17,
                AdvBalancedParenTreeTest.isBalanced3Input, AdvBalancedParenTreeTest.isBalanced3Output ? "true" : "false") ? 1 : 0;
        numCodingSuccess += runTimedTask(testAdvanced::isBalanced4, 18,
                AdvBalancedParenTreeTest.isBalanced4Input, AdvBalancedParenTreeTest.isBalanced4Output ? "true" : "false") ? 1 : 0;
        numCodingSuccess += runTimedTask(testAdvanced::isBalanced5, 19,
                AdvBalancedParenTreeTest.isBalanced5Input, AdvBalancedParenTreeTest.isBalanced5Output ? "true" : "false") ? 1 : 0;
        numCodingSuccess += runTimedTask(testAdvanced::isBalanced6, 20,
                AdvBalancedParenTreeTest.isBalanced6Input, AdvBalancedParenTreeTest.isBalanced6Output ? "true" : "false") ? 1 : 0;
        writeFinal(numNamePledgeSuccess, numCodingSuccess);
    }

    public void close() {
        try {
            fileWriter.close();
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public enum Categories {Compiles, PledgeName, Tests, ExtraCredit}

    private interface ILambdaFunction {
        void run();
    }
}
