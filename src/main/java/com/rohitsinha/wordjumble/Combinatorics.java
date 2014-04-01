package com.rohitsinha.wordjumble;

import java.util.LinkedList;

/**
 * Class to generate the all possible combination for a given string
 *
 * @author : Rohit Sinha
 */
public class Combinatorics {

	private final String str;
	private StringBuilder combinationsBuilder = new StringBuilder();
	private LinkedList<String> combinations;

	public Combinatorics(final String str) {
		this.str = str;
	}

	LinkedList<String> getCombinations() {
		return combinations;
	}

	/**
	 * Function to generate all the possible combination for the string
	 *
	 * @return {@link java.util.LinkedList} containing all the possible combinationsBuilder of the string
	 */
	public LinkedList<String> generateCombinatons() {
		combinations = new LinkedList<String>();
		generateCombinatonsHelper(0);
		return getCombinations();
	}

	/**
	 * Function which generates all the possible combinationsBuilder for a string recursively
	 *
	 * @param start: the starting index
	 */
	private void generateCombinatonsHelper(int start) {
		for (int i = start; i < str.length(); i++) {
			combinationsBuilder.append(str.charAt(i));
			combinations.add(combinationsBuilder.toString());
			if (i < str.length())
				generateCombinatonsHelper(i + 1);
			combinationsBuilder.setLength(combinationsBuilder.length() - 1);
		}
	}
}
