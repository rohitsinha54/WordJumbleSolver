# Word Jumble Solver
Word Jumble Solver is a small program written in Java which solves [word jumble](http://en.wikipedia.org/wiki/Jumble) problem.

This program is developed for coding challenge from [Twice](https://www.liketwice.com/)

## Description
- WordJumbleSolver uses a quick lookup dictionary using the words file generally found at `/usr/share/dict/words` on un*x type operating systems. 
- If running on non-un*x based operating system the user must provide a file containing words delimited by newline like [this](http://www-01.sil.org/linguistics/wordlists/english/) which will be used as valid dictionary words. 
- WordJumbleSolver then build a dictionary from this file where each word is hashed with the product of prime number associated to its characters. Since, products of primes are unique this gurantees that all the word which fall under the same key will be [anagrams](http://en.wikipedia.org/wiki/Anagram)
- WordJumbleSolver solves the jumble word by generating all possible combination of the jumbled word and finding its anagrams from the dictionary. 
- Here is an example
>jumbled word : ogd
>
> Word Jumble Solver: Here are the valid words -
> 
>og		go		god		dog		od
do

## File Structure
| Filename        | Description          |
| ------------- |:-------------:|
| App.java      | The main Word Jumble Solver class |
| AlphabetToPrimeMap.java      | Stores alphabet to prime number mapping and calculates prime factors for words      | 
| Combinatorics.java | Combinatorics class which generates all possible combination of string     |
| Dictionary.java | The dictionary class which stores all the dictionary words which are anagrams to each other in a hash where the key is product of their character's prime number|
| OSType.java | The class which helps to determines the type of operating system. Code adopted from [here](http://www.mkyong.com/java/how-to-detect-os-in-java-systemgetpropertyosname) |

##Dependencies
This project depends on the following:

1. [Maven](http://maven.apache.org/)
2. A [newline dependent words file](http://www-01.sil.org/linguistics/wordlists/english/) on non-un*x operating system. 

##How to use
Clone the repository on you local machine and from the command line excute to following command from the parent directory

>r******@R*****-MacBook-Pro> mvn package
>
>r******@R*****-MacBook-Pro> java -cp target/WordJumbleSolver-1.0-SNAPSHOT.jar com.rohitsinha.wordjumble.App ogd

On non Un*ix operating system you must provide the path to the words file (a newline delimited file containing all the words). In this case the command will be

>java -cp target/WordJumbleSolver-1.0-SNAPSHOT.jar com.rohitsinha.wordjumble.App filepath ogd

## Testing
The program has been tested on following opersating systems:

1. Mac OSX 10.9.2
2. Ubuntu 13.10


##Version
1.0 beta

##Contact Information
Please report any bugs or issues to:
[talktorohit54@gmail.com](mailto:talktorohit54@gmail.com)

##License
[MIT License](https://github.com/rohitsinha54/WordJumbleSolver/blob/master/README.md)
