package com.rohitsinha.wordjumble;

import java.io.*;
import java.util.HashMap;
import java.util.HashSet;
import java.util.logging.Logger;

/**
 * Dictionary class which build a quick lookup for dictionary words by calculating the prime products of alphabets and
 * storing them in a hash
 *
 * @author : Rohit Sinha
 */
public class Dictionary {

	/**
	 * predefined words file path. On *nix based systems generally the word file resides in this location. The default
	 * constructor tries to read words file from these locations.
	 */
	private final static String[] SYSTEM_WORDS_FILE_PATHS = {"/usr/share/dict/words", "/usr/dict/words"};
	private final static int FAILURE = -1;
	private final static Logger LOGGER = Logger.getLogger(Dictionary.class.getName());

	/**
	 * Hashmap for quick lookup of valid dictionary words consisting of a given alphabets. The key is a prime
	 * product of
	 * word obtained by multiplying prime numbers associated with every alphabet and the value is all the words which
	 * fall under the same key for example dog and god will be associated with same key as they consists of same
	 * alphabets
	 */
	private HashMap<Long, HashSet<String>> dict;

	/**
	 * Default constructor which read the system's word file. Should only be called from a unix type system
	 *
	 * @throws FileNotFoundException: if default word file was not found on the system
	 * @throws IOException:           if there was an error while reading the system's word file
	 * @throws RuntimeException:      if the file path is incorrect
	 */
	public Dictionary() throws RuntimeException, IOException {

		if (OSType.isMac() || OSType.isSolaris() || OSType.isUnix()) {
			LOGGER.info("Unix based system found. Looking for words file.");
		}
		int wordFilePath = defaultWordsFileExists();
		if (wordFilePath >= 0) {
			dict = new HashMap<Long, HashSet<String>>();
			processWords(SYSTEM_WORDS_FILE_PATHS[wordFilePath]);
		} else {
			throw new RuntimeException("Unable to find default words file on the system. Please provide word file " +
					"path manually");
		}

	}

	/**
	 * Parametrized constructor which will take the path to the word file
	 *
	 * @param filepath: path to the words file
	 * @throws FileNotFoundException: if the given words file was not found on the system
	 * @throws IOException:           if there was an error while reading the system's word file
	 * @throws RuntimeException:      if the file path is incorrect
	 */
	public Dictionary(String filepath) throws RuntimeException, IOException {
		if (fileExists(filepath)) {
			dict = new HashMap<Long, HashSet<String>>();
			processWords(filepath);
		} else {
			throw new RuntimeException("Unable to find the specified words file. Please make sure the path is " +
					"correct" +
					" " +
					"and the file is accessible");

		}
	}

	/**
	 * Function to read the word file word by word calculating prime products for every word and put them in the
	 * hashmap
	 *
	 * @param wordsFilePath: the path to the word file
	 * @throws FileNotFoundException: if the given words file was not found on the system
	 * @throws IOException:           if there was an error while reading the system's word file
	 */
	private void processWords(String wordsFilePath) throws IOException {
		HashSet<String> value;
		BufferedReader input = new BufferedReader(new FileReader(wordsFilePath));

		while (input.ready()) {
			String word = input.readLine().trim().toLowerCase();
			if (supportedWordFormat(word) && word.length() > 1) { // to omit one letter alphabets present in system
				// dictionary
				long key = AlphabetToPrimeMap.calcPrimeProduct(word);
				if (dict.containsKey(key))
					value = dict.get(key);
				else
					dict.put(key, value = new HashSet<String>());
				value.add(word);
			}
		}
		if (input != null)
			input.close();
	}

	/**
	 * Function to get all valid anagram words
	 *
	 * @param word: the word for which we want to find valid anagram words
	 * @return : a {@link java.util.HashSet} containing all valid words which are anagrams
	 */
	public HashSet<String> getAllWords(String word) {
		HashSet<String> validWords = null;
		long key = AlphabetToPrimeMap.calcPrimeProduct(word);
		if (dict.containsKey(key))
			validWords = dict.get(key);
		return validWords;
	}

	/**
	 * Function to checks if the word only consists of english alphabets Currently our dictionary only supports word
	 * containing 26 english letters
	 *
	 * @param word: the word which has to be checked
	 * @return : a boolean which is true if the word only has english alphabets in it else false
	 */
	public boolean supportedWordFormat(String word) {
		return word.matches("[a-zA-Z]+");
	}

	/**
	 * Function to check if the default words file exits or not and if does return index to its path
	 *
	 * @return : index to the word file path in SYSTEM_WORDS_FILE_PATHS array.
	 */
	private int defaultWordsFileExists() {
		for (int i = 0; i < SYSTEM_WORDS_FILE_PATHS.length; i++) {
			if (fileExists(SYSTEM_WORDS_FILE_PATHS[i])) {
				LOGGER.info("System words file found.");
				return i;    // return the path at which words file is found
			}
		}
		LOGGER.warning("System words file not found. Asking user for word file");
		return FAILURE;
	}

	/**
	 * Function to check if a file exists at a given path
	 *
	 * @param filepath: the path to the file
	 * @return a boolean which is true if file exits else false
	 */
	private boolean fileExists(String filepath) {
		File path = new File(filepath);
		return path.exists();
	}
}
