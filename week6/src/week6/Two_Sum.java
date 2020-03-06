package week6;

import java.util.ArrayList;

public class Two_Sum {

	public static ArrayList<Pair<Integer>> twoSum(int[] input, int target) {
		ArrayList<Pair<Integer>> res = new ArrayList<Pair<Integer>>();
		for (int i = 0; i < input.length; i++) {
			for (int j = 0; j < input.length; j++) {
				if (input[i] + input[j] == target) {
					res.add(new Pair<Integer>(input[i], input[j]));
				}
			}
		}
		return res;
	}
}
