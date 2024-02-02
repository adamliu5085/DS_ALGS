package assignment1;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * 
 * @author CS 2420 staff and ??
 * @version August 18, 2021
 *
 */
class MatrixTest {

	/* ******equals tests****** */
	@Test
	void testModerateMatricesEqual() {
		Matrix m1 = new Matrix(new int[][] {
			{1, 2, 3},
			{4, 5, 6}
		});
		Matrix m2 = new Matrix(new int[][] {
			{1, 2, 3},
			{4, 5, 6}
		});
		assertTrue(m1.equals(m2), "Equal Test Failed.");	
	}
	
	@Test
	void testMatrixNegativeEquals() {
		Matrix m1 = new Matrix(new int[][] {
			{-1, -2, -3},
			{-4, -5, -6}
		});
		Matrix m2 = new Matrix(new int[][] {
			{-1, -2, -3},
			{-4, -5, -6}
		});
		assertTrue(m1.equals(m2), "Negative equal test failed.");
		}
	
	@Test
	void testMatrixDoesNotEqual() {
		Matrix m1 = new Matrix(new int[][] {
			{1, 2, 3},
			{4, 5, 6}
		});
		Matrix m2 = new Matrix(new int[][] {
			{4, 5, 6},
			{1, 2, 3}
		});
		assertFalse(m1.equals(m2), "Does not equal test failed.");
		}
	
	@Test
	void testDifferentSizeMatrixEquals() {
		Matrix m1 = new Matrix(new int[][] {
			{1, 2, 3},
			{4, 5, 6}
		});
		Matrix m2 = new Matrix(new int[][] {
			{4, 3},
			{1, 2},
			{12, 1}
		});
		assertFalse(m1.equals(m2), "Differest size matrix test failed.");
	}
	
	@Test
	void testEmptyMatrix() {
		Matrix m1 = new Matrix(new int[][] {
		});
		Matrix m2 = new Matrix(new int[][] {
		});
		assertTrue(m1.equals(m2), "Empty matrix test failed.");
	}
	
	@Test
	void testOneByOneEqualMatrix() {
		Matrix m1 = new Matrix(new int[][] {{1}
		});
		Matrix m2 = new Matrix(new int[][] {{1}
		});
		assertTrue(m1.equals(m2), "One by one matrix test failed.");
	}
	
	@Test
	void testNonMatrix() {
		String m1 = "Hello";
		String m2 = "Goodbye";
		assertFalse(m1.equals(m2), "Empty matrix test failed.");
	}
	
	/* ******end equals tests****** */


	/* ******toString tests****** */
	@Test
	void testModerateToString() {
		Matrix m = new Matrix(new int[][] {
			{1, 2},
			{3, 4}
		});
		assertEquals("1 2\n3 4\n", m.toString());
	}
	
	@Test
	void testSmallToString() {
		Matrix m = new Matrix(new int[][] {
			{1},
			{30}
		});
		assertEquals("1\n30\n", m.toString());
	}
	
	@Test
	void testFalseLargeRandomToString() {
		Matrix m = new Matrix(new int[][] {
			{1, 2, -8000, 2030, 2, 8, 15},
			{3, 4, 21, 34, 12, 33, 77},
			{1, 2, -8000, 2030, 2, 8, 15},
			{3, 4, 21, 34, 12, 33, 77}
		});
		assertEquals("1 2 -8000 2030 2 8 15\n3 4 21 34 12 33 77\n1 2 -8000 2030 2 8 15\n3 4 21 34 12 33 77\n", m.toString());
	}
	
	@Test
	void testAllZeroAndNegToString() {
		Matrix m = new Matrix(new int[][] {
			{0, 0, 0},
			{-1, 0, -1},
			{0, -1, 0}
		});
		assertEquals("0 0 0\n-1 0 -1\n0 -1 0\n", m.toString());
	}
	
	@Test
	void testEmptyToString() {
		Matrix m = new Matrix(new int[][] {
		});
		assertEquals("", m.toString());
	}
	
	@Test
	void testOneByOneToString() {
		Matrix m = new Matrix(new int[][] { {32}
		});
		assertEquals("32\n", m.toString());
	}
	/* ******end toString tests****** */



	/* ******times tests****** */
	@Test
	void testCompatibleTimes() {
		Matrix m1 = new Matrix(new int[][]
				{{1, 2, 3},
				{2, 5, 6}});
		Matrix m2 = new Matrix(new int[][]
				{{4, 5},
				{3, 2},
				{1, 1}});
		int[][] expected = new int[][]
				{{13, 12},
				{29, 26}};
		Matrix mulResult = m1.times(m2);
		int[][] resultArray = mulResult.getData();
		assertEquals(expected.length, resultArray.length);
		for(int i = 0; i < expected.length; i++)
			assertArrayEquals(expected[i], resultArray[i]);
	}
	
	@Test
	void testEmptyMatrixTimes() {
		Matrix m1 = new Matrix(new int[][]
				{});

		Matrix m2 = new Matrix(new int[][]
				{});
		Matrix mExpected = new Matrix(new int[][]
				{});	
		assertEquals(mExpected, m1.times(m2), "Empty times test failed."); 
	}
	
	@Test
	void testOneByOneMatrixTimes() {
		Matrix m1 = new Matrix(new int[][]
				{{5}});

		Matrix m2 = new Matrix(new int[][]
				{{5}});
		Matrix mExpected = new Matrix(new int[][]
				{{25}});	
		assertEquals(mExpected, m1.times(m2), "One by One times test failed."); 
	}
	
	@Test
	void testIncompatibleTimes() {
		Matrix m1 = new Matrix(new int[][]
				{{1, 2, 3, 4},
				{2, 5, 6, 8}});
		Matrix m2 = new Matrix(new int[][]
				{{4, 5},
				{3, 2},
				{1, 1}});
		assertThrows(IllegalArgumentException.class, () -> { m1.times(m2); }, "testIncompatible times failed");  
	}
	
	@Test
	void testZeroTimes() {
		Matrix m1 = new Matrix(new int[][]
				{{0, 0, 0, 0},
				{0, 0, 0, 0}});
		Matrix m2 = new Matrix(new int[][]
				{{0, 0},
				{0, 0},
				{0, 0},
				{0, 0}
				});
		Matrix mExpected = new Matrix(new int[][]
				{{0, 0},
				{0, 0}
				});
		assertEquals(mExpected,m1.times(m2), "testIncompatible times failed");  
	}
	/* ******end times tests****** */
	

	/* ******plus tests****** */
	@Test
	public void testIncompatiblePlus() {	
		Matrix m1 = new Matrix(new int[][] {
			{1, 1, 1},
			{1, 1, 1}
		});
		Matrix m2 = new Matrix(new int[][] {
			{2, 2},
			{2, 2}
		});
		assertThrows(IllegalArgumentException.class, () -> { m1.plus(m2); });  
	}
	
	@Test
	public void testCompatiblePlus() {	
		Matrix m1 = new Matrix(new int[][] {
			{1, 1, 1},
			{1, 1, 1}
		});
		Matrix m2 = new Matrix(new int[][] {
			{5, 5, 5},
			{5, 5, 5}
		});
		
		Matrix mExpected = new Matrix(new int[][] {
			{6, 6, 6},
			{6, 6, 6}
		});
		assertEquals(mExpected, m1.plus(m2), "Cpmpatiple addition test failed.");  
	}
	
	@Test
	public void testSubtractPlus() {	
		Matrix m1 = new Matrix(new int[][] {
			{1, 1, 1},
			{1, 1, 1}
		});
		Matrix m2 = new Matrix(new int[][] {
			{-5, -5, -5},
			{5, -5, -5}
		});
		
		Matrix mExpected = new Matrix(new int[][] {
			{-4, -4, -4},
			{6, -4, -4}
		});
		assertEquals(mExpected, m1.plus(m2), "Cpmpatiple Subtraction test failed.");  
	}
	
	@Test
	void testOneByOneMatrixPlus() {
		Matrix m1 = new Matrix(new int[][]
				{{5}});
		Matrix m2 = new Matrix(new int[][]
				{{5}});
		Matrix mExpected = new Matrix(new int[][]
				{{10}});	
		assertEquals(mExpected, m1.plus(m2), "One by One addition test failed."); 
	}
	
	@Test
	void testEmptyMatrixPlus() {
		Matrix m1 = new Matrix(new int[][]
				{{}});
		Matrix m2 = new Matrix(new int[][]
				{{}});
		Matrix mExpected = new Matrix(new int[][]
				{{}});	
		assertEquals(mExpected, m1.plus(m2), "Empty addition test failed."); 
	}
	/* ******end plus tests****** */
	
	
	
	/* ******transpose tests****** */
	@Test
	public void testSmallTranspose() {
		Matrix m1 = new Matrix(new int[][] {
			{1, 1, 1},
			{1, 1, 1}
		});
		int expected[][] = {
				{1, 1},
				{1, 1},
				{1, 1}
		};
		Matrix t = m1.transpose();
		int resultArray[][] = t.getData();
		assertEquals(expected.length, resultArray.length);
		for(int i = 0; i < expected.length; i++)
			assertArrayEquals(expected[i], resultArray[i]);
	}
	
	@Test
	public void testEmptyTranspose() {
		Matrix m1 = new Matrix(new int[][] {});
		int expected[][] = {};
		Matrix t = m1.transpose();
		int resultArray[][] = t.getData();
		assertEquals(expected.length, resultArray.length, "Lengths test Failed.");
		for(int i = 0; i < expected.length; i++)
			assertArrayEquals(expected[i], resultArray[i],"Empty test failed.");
	}
	
	@Test
	public void testOneByOneTranspose() {
		Matrix m1 = new Matrix(new int[][] {
			{32}
		});
		int expected[][] = {
				{32}
		};
		Matrix t = m1.transpose();
		int resultArray[][] = t.getData();
		assertEquals(expected.length, resultArray.length);
		for(int i = 0; i < expected.length; i++)
			assertArrayEquals(expected[i], resultArray[i]);
	}
	
	@Test
	public void testBigNegativeTranspose() {
		Matrix m1 = new Matrix(new int[][] {
			{1, 1, 1},
			{1, 1, 1},
			{-2, -2, -2,},
			{-3, -3, -3},
			{-90, -90, -90}
		});
		int expected[][] = {
				{1, 1, -2, -3, -90},
				{1, 1, -2, -3, -90},
				{1, 1, -2, -3, -90}
		};
		Matrix t = m1.transpose();
		int resultArray[][] = t.getData();
		assertEquals(expected.length, resultArray.length);
		for(int i = 0; i < expected.length; i++)
			assertArrayEquals(expected[i], resultArray[i]);
	}
	/* ******end transpose tests****** */
}
