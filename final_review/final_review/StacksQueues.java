package final_review;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StacksQueues {
    // stacks, queues, binary trees, self-balancing, dfs, heap, quicksort
    public static void main(String[] args) {
        Stack<Integer> testStack = new Stack<>();
        testStack.empty();
        testStack.push(4);
        testStack.push(5);
        testStack.peek();
        testStack.pop();
        System.out.println(testStack.peek());
        // 5
        // 4
        Queue<Integer> testQueue = new LinkedList<>();
        testQueue.add(4);
        testQueue.add(5);
        testQueue.add(6);
        testQueue.add(7);
        testQueue.remove();
        System.out.println(testQueue.peek());
        // 5 6 7
        // https://github.com/jschmidtnj/cs284

        // self-balancing trees: https://www.cs.usfca.edu/~galles/visualization/AVLtree.html
    }
}
