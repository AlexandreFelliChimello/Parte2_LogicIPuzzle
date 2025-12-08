import java.util.Arrays;

/**
 * The {@code LogicPuzzleTest} tests the main elements of the second IP2526 project. 
 * 
 * 		Compile: javac LogicPuzzleTest.java
 * 		Execute: java  LogicPuzzleTest
 * 
 * @author malopes IP2526@LEI-FCUL
 * @version 1
 */

public class LogicIPuzzleClassesTest {

	public static void main(String[] args) {
		System.out.println ("Testing LogicIPuzzle classes \n");

		testLogicPuzzle ();	
		testLogicIPuzzleGame ();
		testLogicIPuzzleRandom ();
	}

	private static void testLogicPuzzle () {
		System.out.println ("-----Testing LogicPuzzle class--------------------");
		testDefinesPuzzle();
		testGetClue();
		testGetColor();
		testCluesToString();
		testSameClues();
		System.out.println ("----------------------------------------------------");
	}


	private static void testLogicIPuzzleGame () {
		System.out.println ("-----Testing LogicIPuzzleGame class--------------------");
		testHasClue();
		testIsFilled();
		testGetColorGame();
		testCheckIfCorrect();
		System.out.println ("----------------------------------------------------");
	}

	private static void testLogicIPuzzleRandom () {
		System.out.println ("-----Testing LogicIPuzzleRandom class---------------");
		testNext();
		System.out.println ("----------------------------------------------------");
	}

	//////////////////////////////////////////////////////////////////////
	/// 	
	/// 	Tests for LogicIPuzzle class
	/// 
	/////////////////////////////////////////////////////////////////////////
	
	private static void testDefinesPuzzle () {
		String methodName = "LogicIPuzzle.definesPuzzle";
		System.out.println ("Testing "+ methodName);
		boolean error = false;
		boolean expected;
		boolean obtained;
	
		//too small!
		int rows = 2;
		int cols = 1;
		int[] squares = {}; 
		Clue[] clues = {};	
		expected = false; 
		obtained = LogicIPuzzle.definesPuzzle(rows, cols, squares, clues);
		error = checkEqual(expected, obtained, Arrays.toString(squares),  Arrays.toString(clues)) || error;

		//arrays with different lengths
		rows = 2;
		cols = 2;
		squares = new int[]{1}; 
		clues = new Clue[]{};
		expected = false; 
		obtained = LogicIPuzzle.definesPuzzle(rows, cols, squares, clues);
		error = checkEqual(expected, obtained, Arrays.toString(squares),  Arrays.toString(clues)) || error;

		//no unique solution
		rows = 2;
		cols = 2;
		squares = new int[]{1}; 
		clues = new Clue[]{Clue.WHITE2};
		expected = false; 
		obtained = LogicIPuzzle.definesPuzzle(rows, cols, squares, clues);
		error = checkEqual(expected, obtained, Arrays.toString(squares),  Arrays.toString(clues)) || error;


		//a good puzzle
		rows = 2;
		cols = 2;
		squares = new int[]{0, 1}; 
		clues = new Clue[]{Clue.BLACK, Clue.WHITE2};
		expected = true;
		obtained = LogicIPuzzle.definesPuzzle(rows, cols, squares, clues);
		error = checkEqual(expected, obtained, Arrays.toString(squares),  Arrays.toString(clues)) || error;

		//another good puzzle
		rows = 4;
		cols = 4;
		squares = new int[]{1,11,12}; 
		clues = new Clue[]{Clue.WHITE2, Clue.WHITE4, Clue.WHITE3};
		expected = true;
		obtained = LogicIPuzzle.definesPuzzle(rows, cols, squares, clues);
		error = checkEqual(expected, obtained, Arrays.toString(squares),  Arrays.toString(clues)) || error;
		
		//another good puzzle
		rows = 4;
		cols = 4;
		squares = new int[]{0,7,13}; 
		clues = new Clue[]{Clue.WHITE3, Clue.WHITE2, Clue.WHITE4};
		expected = true;
		obtained = LogicIPuzzle.definesPuzzle(rows, cols, squares, clues);
		error = checkEqual(expected, obtained, Arrays.toString(squares),  Arrays.toString(clues)) || error;

		//another good puzzle
		rows = 5;
		cols = 4;
		squares = new int[]{1, 7, 10, 12}; 
		clues = new Clue[]{Clue.WHITE4, Clue.BLACK, Clue.WHITE6, Clue.WHITE3};
		expected = true;
		obtained = LogicIPuzzle.definesPuzzle(rows, cols, squares, clues);
		error = checkEqual(expected, obtained, Arrays.toString(squares),  Arrays.toString(clues)) || error;

		//another good puzzle
		rows = 5;
		cols = 5;
		squares = new int[]{2, 3, 5, 12, 14, 22}; 
		clues = new Clue[]{Clue.WHITE2, Clue.BLACK, Clue.WHITE1, Clue.WHITE3, Clue.BLACK, Clue.WHITE5};
		expected = true;
		obtained = LogicIPuzzle.definesPuzzle(rows, cols, squares, clues);
		error = checkEqual(expected, obtained, Arrays.toString(squares),  Arrays.toString(clues)) || error;

		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}
	
	
	private static void testGetClue() {
		String methodName = "LogicIPuzzle.getClue";
		System.out.println ("Testing "+ methodName);
		boolean error = false;

		int row;
		int col;
		Clue expected;
		Clue obtained;
		
		LogicIPuzzle puzzle = onePuzzle5x5();

		row = 2;
		col = 1;
		expected = Clue.WHITE1;
		obtained = puzzle.getClue(row, col);
		error = checkEqual(expected.ordinal(), obtained.ordinal(), Integer.toString(row), Integer.toString(col)) || error;
	
		row = 5;
		col = 3;
		expected = Clue.WHITE5;
		obtained = puzzle.getClue(row, col);
		error = checkEqual(expected.ordinal(), obtained.ordinal(), Integer.toString(row), Integer.toString(col)) || error;
	
		puzzle = onePuzzle4x4();

		row = 1;
		col = 2;
		expected = Clue.WHITE2;
		obtained = puzzle.getClue(row, col);
		error = checkEqual(expected.ordinal(), obtained.ordinal(), Integer.toString(row), Integer.toString(col)) || error;

		row = 3;
		col = 4;
		expected = Clue.WHITE4;
		obtained = puzzle.getClue(row, col);
		error = checkEqual(expected.ordinal(), obtained.ordinal(), Integer.toString(row), Integer.toString(col)) || error;

		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}


	private static void testGetColor() {
		String methodName = "LogicIPuzzle.getColor";
		System.out.println ("Testing "+ methodName);
		boolean error = false;

		int row;
		int col;
		Color expected;
		Color obtained;
		
		LogicIPuzzle puzzle = onePuzzle5x5();

		row = 5;
		col = 1;
		expected = Color.WHITE;
		obtained = puzzle.getColor(row, col);
		error = checkEqual(expected.ordinal(), obtained.ordinal(), Integer.toString(row), Integer.toString(col)) || error;
	
		row = 4;
		col = 2;
		expected = Color.BLACK;
		obtained = puzzle.getColor(row, col);
		error = checkEqual(expected.ordinal(), obtained.ordinal(), Integer.toString(row), Integer.toString(col)) || error;

		row = 2;
		col = 1;
		expected = Color.WHITE;
		obtained = puzzle.getColor(row, col);
		error = checkEqual(expected.ordinal(), obtained.ordinal(), Integer.toString(row), Integer.toString(col)) || error;


		puzzle = onePuzzle4x4();

		row = 4;
		col = 1;
		expected = Color.WHITE;
		obtained = puzzle.getColor(row, col);
		error = checkEqual(expected.ordinal(), obtained.ordinal(), Integer.toString(row), Integer.toString(col)) || error;
	
		row = 2;
		col = 2;
		expected = Color.BLACK;
		obtained = puzzle.getColor(row, col);
		error = checkEqual(expected.ordinal(), obtained.ordinal(), Integer.toString(row), Integer.toString(col)) || error;

		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}

	private static void testSameClues() {
		String methodName = "LogicIPuzzle.sameClues";
		System.out.println ("Testing "+ methodName);
		boolean error = false;

		boolean expected;
		boolean obtained;

		LogicIPuzzle puzzle1 = new LogicIPuzzle(2, 2, new int[]{3}, new Clue[]{Clue.WHITE1});
		LogicIPuzzle puzzle2 = new LogicIPuzzle(2, 3, new int[]{3}, new Clue[]{Clue.WHITE1});
		expected = true; 
		obtained = puzzle1.sameClues(puzzle2);
		error = checkEqual(expected, obtained, Arrays.toString(new int[]{3}), Arrays.toString(new Clue[]{Clue.WHITE1})) || error;

		LogicIPuzzle puzzle3 = new LogicIPuzzle(2, 2, new int[]{1}, new Clue[]{Clue.WHITE1});
		expected = false; 
		obtained = puzzle1.sameClues(puzzle3);
		error = checkEqual(expected, obtained, Arrays.toString(new int[]{3}), Arrays.toString(new Clue[]{Clue.WHITE1})) || error;
	
		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	

	}

	private static void testCluesToString () {
		String methodName = "LogicPuzzle.cluesToString";
		System.out.println ("Testing "+ methodName);		
		boolean error = false;
		
		int rows;
		int cols;
		int[] squares;
		Clue[] clues;
		String expected;
		String obtained;
		LogicIPuzzle puzzle;
	
		rows = 5;
		cols = 5;
		squares = new int[]{2, 3, 5, 12, 14, 22}; 
		clues = new Clue[]{Clue.WHITE2, Clue.BLACK, Clue.WHITE1, Clue.WHITE3, Clue.BLACK, Clue.WHITE5};
		
		puzzle = new LogicIPuzzle(rows, cols, squares, clues);
		expected = " . . 2 * .\n"
				+ " 1 . . . .\n"
				+ " . . 3 . *\n"
				+ " . . . . .\n"
				+ " . . 5 . .\n";
		obtained = puzzle.cluesToString();
		error = !obtained.equals(expected) || error;
		if (!obtained.equals(expected)) {
			System.out.println (">>> expected:\n" + expected);
			System.out.println (">>> obtained:\n" + obtained);
		}
		
		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}

	//////////////////////////////////////////////////////////////////////
	/// 	
	/// 	Tests for LogicIPuzzleGame class
	/// 
	/////////////////////////////////////////////////////////////////////////

	private static void testHasClue() {
		String methodName = "LogicIPuzzleGame.hasClue";
		System.out.println ("Testing "+ methodName);
		boolean error = false;

		LogicIPuzzleGame game;

		int row;
		int col;
		boolean expected;
		boolean obtained;
				
		game = new LogicIPuzzleGame(onePuzzle5x5(), 5);

		row = 2;
		col = 1;
		expected = true;
		obtained = game.hasClue(row, col);
		error = checkEqual(expected, obtained, Integer.toString(row), Integer.toString(col)) || error;

		row = 5;
		col = 3;
		expected = true;
		obtained = game.hasClue(row, col);
		error = checkEqual(expected, obtained, Integer.toString(row), Integer.toString(col)) || error;

		row = 4;
		col = 2;
		expected = false;
		obtained = game.hasClue(row, col);
		error = checkEqual(expected, obtained, Integer.toString(row), Integer.toString(col)) || error;

		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}


	private static void testIsFilled() {
		String methodName = "LogicIPuzzleGame.IsFilled";
		System.out.println ("Testing "+ methodName);
		boolean error = false;
		
		int row;
		int col;
		boolean expected;
		boolean obtained;

		LogicIPuzzleGame game = new LogicIPuzzleGame(onePuzzle5x5(), 5);

		row = 2;
		col = 1;	
		expected = true;
		obtained = game.isFilled(row, col);
		error = checkEqual(expected, obtained, Integer.toString(row), Integer.toString(col)) || error;

		row = 1;
		col = 1;	
		game.fill(row, col, Color.BLACK);
		expected = true;
		obtained = game.isFilled (row, col);
		error = checkEqual(expected, obtained, game.toString(), Integer.toString(row), Integer.toString(col)) || error;

		row = 1;
		col = 4;	
		game.fill(row, col, Color.WHITE);
		expected = true;
		obtained = game.isFilled (row, col);
		error = checkEqual(expected, obtained, game.toString(), Integer.toString(row), Integer.toString(col)) || error;

		row = 1;
		col = 4;	
		game.unfill(row, col);
		expected = false;
		obtained = game.isFilled (row, col);
		error = checkEqual(expected, obtained, game.toString(), Integer.toString(row), Integer.toString(col)) || error;

		row = 5;
		col = 5;	
		game.fill(row, col, Color.WHITE);
		expected = true;
		obtained = game.isFilled (row, col);
		error = checkEqual(expected, obtained, game.toString(), Integer.toString(row), Integer.toString(col)) || error;

		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}

	private static void testGetColorGame() {
		String methodName = "LogicIPuzzleGame.GetColor";
		System.out.println ("Testing "+ methodName);
		boolean error = false;
		
		int row;
		int col;
		Color expected;
		Color obtained;

		LogicIPuzzleGame game = new LogicIPuzzleGame(onePuzzle5x5(), 5);

		row = 2;
		col = 1;	
		expected = Color.WHITE;
		obtained = game.getColor(row, col);
		error = checkEqual(expected.ordinal(), obtained.ordinal(), Integer.toString(row), Integer.toString(col)) || error;

		row = 1;
		col = 1;	
		game.fill(row, col, Color.BLACK);
		expected = Color.BLACK;
		obtained = game.getColor(row, col);
		error = checkEqual(expected.ordinal(), obtained.ordinal(), Integer.toString(row), Integer.toString(col)) || error;

		row = 1;
		col = 4;	
		game.fill(row, col, Color.WHITE);
		expected = Color.WHITE;
		obtained = game.getColor(row, col);
		error = checkEqual(expected.ordinal(), obtained.ordinal(), Integer.toString(row), Integer.toString(col)) || error;

		row = 5;
		col = 5;	
		game.fill(row, col, Color.WHITE);
		expected = Color.WHITE;
		obtained = game.getColor(row, col);
		error = checkEqual(expected.ordinal(), obtained.ordinal(), Integer.toString(row), Integer.toString(col)) || error;

		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}


	private static void testCheckIfCorrect() {
		String methodName = "LogicIPuzzleGame.CheckIfCorrect";
		System.out.println ("Testing "+ methodName);
		boolean error = false;

		boolean expected;
		boolean obtained;
		int expectedHints;
		int obtainedHints;	

		LogicIPuzzleGame game = new LogicIPuzzleGame(onePuzzle5x5(), 5);
		game.fill(1, 1, Color.BLACK);
		game.fill(2, 2, Color.BLACK);
		game.fill(3, 2, Color.WHITE);
		game.fill(4, 4, Color.BLACK);
		game.fill(5, 5, Color.WHITE);		
		expected = true;
		expectedHints = 4;
		obtained = game.checkIfCorrectUsingHint();
		obtainedHints = game.getAvailableHints();
		error = checkEqual(expected, obtained, game.toString()) || error;
		error = checkEqual(expectedHints, obtainedHints, game.toString()) || error;

		//square filled with wrong color
		game.fill(5, 1, Color.BLACK);
		expected = false;
		expectedHints = 3;
		obtained = game.checkIfCorrectUsingHint();
		error = checkEqual(expected, obtained, game.toString()) || error;
		obtainedHints = game.getAvailableHints();
		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));	
	}
	
	//////////////////////////////////////////////////////////////////////
	/// 	
	/// 	Tests for LogicIPuzzleRandom class
	/// 
	/////////////////////////////////////////////////////////////////////////
	
	private static void testNext() {
		String methodName = "LogicIPuzzleRandom.next";
		System.out.println ("Testing "+ methodName);
		boolean error = false;
		
		LogicIPuzzle puzzle;
		LogicIPuzzleRandom lpr = new LogicIPuzzleRandom(4,4);
		int count = 0;
		int found = 0;
	
		while (lpr.hasNextPuzzle()) {
			puzzle = lpr.nextPuzzle();
			count++;
			if (puzzle.sameClues(onePuzzle4x4()) || puzzle.sameClues(anotherPuzzle4x4()))
				found++;
		}
	
		boolean obtained = count >= 2;
		boolean expected = true;
		error = checkEqual(expected, obtained, "Puzzles 4x4 available > " + Integer.toString(count)) 
				|| error;
	
		
		obtained = found == 2;
		expected = true;
		error = checkEqual(expected, obtained, "Required puzzles 4x4 available > " + Integer.toString(found)) 
				|| error;
	
		lpr = new LogicIPuzzleRandom(5,5);
		count = 0;
		found = 0;
	
		while (lpr.hasNextPuzzle()) {
			puzzle = lpr.nextPuzzle();
			count++;
			if (puzzle.sameClues(onePuzzle5x5()))
				found++;
		}
	
		obtained = count >= 1;
		expected = true;
		error = checkEqual(expected, obtained, "Puzzles 5x5 available > " + Integer.toString(count)) 
				|| error;
	
		obtained = found == 1;
		expected = true;
		error = checkEqual(expected, obtained, "Required puzzles 5x5 available > " + Integer.toString(found)) 
				|| error;
	
		System.out.println (methodName + ": " + (error ? "FAIL" : "PASS"));			
	}
	
	/////////////////////////////////////////////////////////
	// Three diferent puzzles used in these tests 
	////////////////////////////////////////////////////////
	
	private static LogicIPuzzle onePuzzle5x5() {
		int rows = 5;
		int cols = 5;
		int[] squares = new int[]{2, 3, 5, 12, 14, 22}; 
		Clue[] clues = new Clue[]{Clue.WHITE2, Clue.BLACK, Clue.WHITE1, Clue.WHITE3, Clue.BLACK, Clue.WHITE5};
		return new LogicIPuzzle(rows, cols, squares, clues);
	}

	private static LogicIPuzzle onePuzzle4x4() {
		int rows = 4;
		int cols = 4;
		int[] squares = new int[]{1,11,12}; 
		Clue[] clues = new Clue[]{Clue.WHITE2, Clue.WHITE4, Clue.WHITE3};
		return new LogicIPuzzle(rows, cols, squares, clues);
	}
	
	private static LogicIPuzzle anotherPuzzle4x4() {
		int rows = 4;
		int cols = 4;
		int[] squares = new int[]{0,7,13}; 
		Clue[] clues = new Clue[]{Clue.WHITE3, Clue.WHITE2, Clue.WHITE4};
		return new LogicIPuzzle(rows, cols, squares, clues);
	}

	/////////////////////////////////////////////////////////
	// auxiliary procedures for helping to write the tests
	/////////////////////////////////////////////////////////
	

	private static boolean checkEqual(int expected, int obtained, String... args) {
		boolean error = false;
		if (obtained != expected) {
			System.out.println(Arrays.toString(args));
			System.out.printf (">>> expected: %d obtained: %d %n", expected, obtained);
			error = true;
		}
		return error;
	}

	private static boolean checkEqual(boolean expected, boolean obtained, String... args) {
		boolean error = false;
		if (expected != obtained) {
			System.out.println(Arrays.toString(args));
			System.out.printf (">>> expected: %b obtained: %b %n", expected, obtained);
	
			error = true;
		}
		return error;
	}

}
