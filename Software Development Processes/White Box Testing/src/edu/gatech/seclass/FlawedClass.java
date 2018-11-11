package edu.gatech.seclass;

public class FlawedClass {


	/**
	 * Task 1: Add to the class a method called flawedMethod1 that contains a division by zero fault such that
	 * (1) it is possible to create a test suite that achieves 100% branch coverage and does not reveal the fault, and
	 * (2)  it is possible to create a test suite that achieves 100% statement coverage and reveals the fault.
	 */
	public static int flawedMethod1(int a, int b) {
		//100% branch coverage no fault::::::: a = 2 b = 5
		//100% statement coverage w/ fault a = -3 b = 3 and........ a = 6 b = 6
		int q = 0;
		if (a <= 5)
			q = a / (a + b);
		if (b >= 5)
			q = b / (b + a);
		return q;

	}

	/**
	 * Task 2: Add to the class a method called flawedMethod2 that contains a division by zero fault such that
	 * (1) it is possible to create a test suite that achieves less than 100% statement coverage and reveals the fault,
	 * and
	 * (2)   every test suite that achieves 100% statement coverage does not reveal the fault.
	 */

	public static boolean flawedMethod2(boolean a, boolean b) {
		/**
		 *
		 * This method is impossible. A complete statement coverage is more thorough than a partial statement coverage,
		 * therefore it would detect possible faults in a partial statement coverage. In other words, if a 50% statement
		 * coverage detected a fault, then it could be combined with another 50% that didn't detect a fault. This would
		 * make a case of complete statement coverage, but still reveal the fault. Therefore this is not possible
		 *
		 */
		return false;
	}
	/**
	 * Task 3: Add to the class a method called flawedMethod3 that contains a division by zero fault such that
	 * (1)  every test suite that achieves 100% path coverage reveals the fault and
	 * (2)  it is possible to create a test suite that achieves 100% statement coverage and does not reveal the fault.
	 */



	public static boolean flawedMethod3(boolean a, boolean b) {//TT TF FT FF
		int x = 2;
		int y = 6;
		if(a)
			x = 4;
		else
			y = y / x;
		if(b)
			x -= 1;
		else
			x += 1;

		return ((2/(y-x)) >= 1);


	}

	/**
	 * Task 4: Add to the class a method called flawedMethod4 that contains a division by zero fault such that
	 * (1) it is possible to create a test suite that achieves 100% branch coverage and does not reveal the fault, and
	 * (2) every test suite that achieves 100% statement coverage reveals the fault.
	 */

	public static boolean flawedMethod4(boolean a, boolean b) {
		//100% BRANCH COVERAGE NO fault:::::: a = 2 b = 6
		//100% statement coverage fault a = 0 , b = 0::::: a = 6 , b = 7
		int x = 2;
		int y = 5;
		if(a)
			x = 4;
		if(b)
			x -= 1;
		else
			x += 1;

		return ((2/(y-x)) >= 1);



	}
	/**
	 *Fill in the table in the comments, as follows:
	 For every possible input, write whether the output is T (true), F (false), or E (division by 0 exception)
	 In the “Coverage required” entry, write the minimal level of coverage that a test suite must achieve to guarantee that the fault in the code is revealed (i.e., to guarantee that a division by zero occurs), among statement, branch, and path coverage. In other words: If every test suite achieving statement coverage reveals the fault, then you should write
	 Coverage required: statement coverage
	 If statement coverage does not guarantee that the fault is revealed, but branch coverage does, you should write
	 Coverage required: branch coverage
	 If neither statement nor branch coverage guarantees that the fault is revealed, but path coverage does, you should write
	 Coverage required: path coverage
	 Finally, if none of these coverage criteria considered guarantees that the fault is revealed, you should write
	 Coverage required: N/A
	 If the answer is not “N/A”, create a JUnit test class edu.gatech.seclass.FlawedClassTestXC5 for class  FlawedClass as follows:
	 FlawedClassTestXC5 should achieve 100% of the coverage named in your comment for flawedMethod5 and reveal the fault therein.
	 The class should be saved in directory <dir>/test.
	 *
	 *
	 */

	public static boolean flawedMethod5 (boolean a, boolean b) {
		int x = 2;
		int y = 6;
		if(a)
			x = 4;
		else
			y = y / x;
		if(b)
			x -= 1;
		else
			x += 1;
		return ((2/(y-x)) >= 1);
	}

		// | a | b |output|
		// ================
		// | T | T |T     |
		// | T | F |T     |
		// | F | T |F     |
		// | F | F |E     |
		// ================
		// Coverage required: PATH COVERAGE



}
