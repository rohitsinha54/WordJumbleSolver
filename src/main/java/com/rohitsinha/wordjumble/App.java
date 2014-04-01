package com.rohitsinha.wordjumble;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.logging.Logger;

/**
 * Word Jumble Solver which solves the word jumble puzzle by generating all valid dictionary words for the given jumbled
 * word and all combinations of it
 *
 * @author Rohit Sinha
 */
public class App {
	private final static Logger LOGGER = Logger.getLogger(App.class.getName());
	private final static int FAILURE = -1;
	private static Dictionary dict = null;

	public static void main(String[] args) {
		int numArgs = args.length;
		switch (numArgs) {
			case 1:
				// this can only be done on *nix based systems which have a default words file
				if (!(OSType.isMac() || OSType.isSolaris() || OSType.isUnix())) {
					LOGGER.severe("Not a unix based system detected");
					System.err.println("Not a unix based system. Unable to use system default words file.");
					System.err.println("Please specify a words file path.");
					printUsage();
				} else {
					// unix based system. Use default words file
					App jumbleSolver = new App();
					dict = jumbleSolver.getDictionary();
					jumbleSolver.printValidWords(jumbleSolver.solve(args[0]));
				}
				break;
			case 2:
				// case for non *nix type system where the user has to specify a words file
				App jumbleSolver = new App();
				dict = jumbleSolver.getDictionary(args[0]);
				jumbleSolver.printValidWords(jumbleSolver.solve(args[1]));
				break;
			default:
				printUsage();
				break;
		}
	}

	/**
	 * Function which tries to pretty printValidWords the usage details
	 */
	private static void printUsage() {
		System.out.println("arguments: [filepath] [word]");
		System.out
				.println("filepath (can be omitted on unix machines)\t:\tpath to file containing words delimited by " +
						"newline. ");
		System.out
				.println("\t\t\t\t\t\t\tcan be omitted on unix based system as on unix based system the program " +
						"defaults "
						+ "to read system words file");
		System.out.println("word (required)\t\t\t\t\t:\tthe word to be solved. Currently the application only " +
				"supports" +
				" word consisting of 26 english letters");
	}

	/**
	 * Function to solve the word jumble by generating all the combination of the given words and finding all valid
	 * dictionary anagrams of these combinations
	 *
	 * @param word: the jumbled word
	 * @return : a {@link java.util.LinkedList} containing all the valid dictionary words which are solution to the
	 * given jumbled word
	 */
	private LinkedList<String> solve(String word) {
		LinkedList<String> validWords = new LinkedList<String>();
		if (dict.supportedWordFormat(word)) {
			LinkedList<String> wordComb = new Combinatorics(word).generateCombinatons();
			for (String w : wordComb) {
				HashSet<String> curWords = dict.getAllWords(w);
				if (curWords != null)
					validWords.addAll(curWords);
			}
		} else {
			unsupportedWordHandler();
		}
		return validWords;
	}

	/**
	 * Function print all valid words on the standard output
	 *
	 * @param validWords: a {@link java.util.LinkedList} containing all the valid dictionary words
	 */
	private void printValidWords(LinkedList<String> validWords) {
		if (validWords != null && validWords.size() > 0) {
			System.out.println("Word Jumble Solver: Here are the valid words -");
			int printCounter = 0;
			for (String word : validWords) {
				System.out.print(word + "\t\t");    // try to pretty print
				if (++printCounter == 5) {
					System.out.println("");
					printCounter = 0;
				}
			}
		} else {
			System.out.println("No valid words found in the dictionary");
		}
	}

	private void unsupportedWordHandler() {
		System.out.println("Not a valid word. Please try again.");
		System.out.println("Currently the application only supports word consisting of 26 english letters");
		printUsage();
	}

	/**
	 * Function to get Dictionary object
	 *
	 * @param filename: the words file path which will be null on a unix system
	 * @return : a {@link Dictionary} object for doing word searches
	 */
	private Dictionary getDictionary(String filename) {
		try {
			if (filename == null) {
				return new Dictionary();
			} else {
				return new Dictionary(filename);
			}
		} catch (RuntimeException rte) {
			LOGGER.severe(rte.getMessage());
			System.exit(FAILURE);
		} catch (FileNotFoundException e) {
			LOGGER.severe("Caught FileNotFoundException: Make sure that the word file is accessible");
			System.exit(FAILURE);
		} catch (IOException e) {
			LOGGER.severe("Caught IOException: Unable to read the words file");
			System.exit(FAILURE);
		}

		return dict;
	}

	private Dictionary getDictionary() {
		return getDictionary(null);
	}
}
