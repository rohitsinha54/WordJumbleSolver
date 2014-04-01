package com.rohitsinha.wordjumble;

/**
 * Class which stores prime number for a all 26 alphabets in english and calculates prime products for a given word
 * based on this mapping. It should be noted that the products of prime are unique so every word which have same
 * alphabets (and will differ only in arrangement) will have the same prime product
 *
 * @author : Rohit Sinha
 */
public class AlphabetToPrimeMap {

	private static final int aDecimalValue = 97;

	/**
	 * Character to prime number mapping a = map[0] b = map[1] and so on
	 */
	private static final int[] map = {2, 3, 5, 7, 11, 13, 17, 19, 23, 29, 31, 37, 41, 43, 47, 53, 59, 61, 67, 71, 73,
			79, 83, 89, 97, 101};

	/**
	 * Function to get the prime number mapped to the character
	 *
	 * @param c: the character whose prime number has to be determined
	 * @return : a int which is the prime number mapped to character
	 */
	private static int get(char c) {
		int val = (int) c;
		return map[val - aDecimalValue];    // offset
	}

	/**
	 * Function to calculate the products of prime for a word by multiplying the prime numbers associated with every
	 * alphabet in the word
	 *
	 * @param word: the word whose prime product has to be calculated
	 * @return : a long which is the value associated to that word
	 */
	public static long calcPrimeProduct(String word) {
		char[] wordChars = word.toCharArray();
		long primeProd = 1;
		for (char c : wordChars) {
			primeProd *= AlphabetToPrimeMap.get(c);
		}
		return primeProd;
	}
}
