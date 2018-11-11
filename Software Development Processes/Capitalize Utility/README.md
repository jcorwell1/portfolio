This is the generation of many tests for a theoretical capitalization utility

Test cases were made using the TSLgenerator.

EXAMPLES OF USAGE OF CAPITALIZATION UTILITY

Example 1: 
capitalize file1.txt
would capitalize the first character in every whitespace delimited word.

Example 2: 
capitalize -l file1.txt
would  capitalize only the first character in every line.

Example 3: 
capitalize -e -s file1.txt
would capitalize the first character in every sentence and every letter in every exclamatory sentence, where a sentence is defined as ending in one of the characters ‘.’, ‘!’, or ‘?’.

Example 4: 
capitalize -s , -x file1.txt
would capitalize all and only the first character in each sentence, where a sentence is defined as ending in a comma (such as the fields in a csv file).
