StringCalculator
================
TDD exercise
Steps:
1. Create a simple String calculator with a method signature

int add(string numbers)
Input: a string of comma-separated numbers
Output: an integer, sum of the numbers

Examples:
Input: “”, Output: 0
Input: “1”, Output: 1
Input: “1,5”, Output: 6

2. Allow the add method to handle any amount of numbers.

3. Allow the add method to handle new lines between numbers (instead of commas). ("1\n2,3" should return 6)

4. Support different delimiters:

To change the delimiter, the beginning of the string will contain a separate line that looks like this: "//[delimiter]\n[numbers…]". For example, "//;\n1;2" where the delimiter is ";" should return 3.

5. Calling add with a negative number will throw an exception: "negative numbers not allowed <negative_number>".

If there are multiple negative numbers, show all of them in the exception message, separated by commas.