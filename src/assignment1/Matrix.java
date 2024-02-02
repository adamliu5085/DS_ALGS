package assignment1;

/**
 * This class represents a 2D matrix and simple operations on them.
 * 
 * @author Daniel Kopta and ??
 * @version August 08, 2021
 *
 */

public class Matrix {
	
	// the dimensions of the matrix
	private int numRows;
	private int numColumns;
	
	// the internal storage for the matrix elements 
	private int data[][];
	
	/**
	 * DO NOT MODIFY THIS METHOD.
	 * Constructor for a new Matrix. Automatically determines dimensions.
	 * This is implemented for you.
	 * @param d - the raw 2D array containing the initial values for the Matrix.
	 */
	public Matrix(int d[][])
	{
		// d.length is the number of 1D arrays in the 2D array
		numRows = d.length; 
		if(numRows == 0)
			numColumns = 0;
		else
			numColumns = d[0].length; // d[0] is the first 1D array
		// create a new matrix to hold the data
		data = new int[numRows][numColumns]; 
		// copy the data over
		for(int i=0; i < numRows; i++) 
			for(int j=0; j < numColumns; j++)
				data[i][j] = d[i][j];
	}

	/**
	 * Determines whether this Matrix is equal to another object.
	 * @param o - the other object to compare to, which may not be a Matrix
	 */
	@Override // instruct the compiler that we intend for this method to override the superclass' (Object) version
	public boolean equals(Object o)
	{
		// make sure the Object we're comparing to is a Matrix
		if(!(o instanceof Matrix)) 
			return false;
		// if the above was not true, we know it's safe to treat 'o' as a Matrix
		Matrix m = (Matrix)o; 
		if ((this.numColumns == m.numColumns) && (this.numRows == m.numRows)) {
			for(int i=0; i < this.numRows; i++) {
				for(int j=0; j < this.numColumns; j++) {
					if (this.data[i][j] != m.data[i][j])
						return false;
				}
			}
			return true;
		}
		/*
		 * TODO: replace the below return statement with the correct code. 
		 *  
		 *  Returns true if this Matrix is equal to the input Matrix; returns false otherwise
		 */
		return false; 
	}
	
	/**
	 * Returns a String representation of this Matrix.
	 */
	@Override // instruct the compiler that we intend for this method to override the superclass' (Object) version
	public String toString()
	{
		String returnString = "";
		for(int i=0; i < numRows; i++) 
			for(int j=0; j < numColumns; j++)
				if (j == numColumns-1 )
					returnString += data[i][j] + "\n";
				else	
					returnString += data[i][j] + " "; 
		return returnString; 
	}
	
	/**
	 * Returns a new Matrix that is the result of multiplying this Matrix as the left hand side
	 * by the input matrix as the right hand side.
	 * 
	 * @param m - the Matrix on the right hand side of the multiplication
	 * @return - the result of the multiplication
	 * @throws IllegalArgumentException - if the input Matrix does not have compatible dimensions
	 * for multiplication
	 */
	public Matrix times(Matrix m) throws IllegalArgumentException
	{
		if (this.numColumns != m.numRows) {
			throw new IllegalArgumentException("Matricies do not have the correct dimensions for multiplication.");
		}
		Matrix multipliedMatrix = new Matrix(new int[this.numRows][m.numColumns]);
		for(int i=0; i < this.numRows; i++) 
			for(int j=0; j < m.numColumns; j++) {
				for(int multIteration = 0; multIteration < this.numColumns; multIteration++) {
					multipliedMatrix.data[i][j] += this.data[i][multIteration] * m.data[multIteration][j];
				}
			}
		return multipliedMatrix;
	}
	
	/**
	 * Returns a new Matrix that is the result of adding this Matrix as the left hand side
	 * by the input matrix as the right hand side.
	 * 
	 * @param m - the Matrix on the right hand side of the addition
	 * @return - the result of the addition
	 * @throws IllegalArgumentException - if the input Matrix does not have compatible dimensions
	 * for addition
	 */
	public Matrix plus(Matrix m) throws IllegalArgumentException
	{
		if (this.numColumns != m.numColumns || this.numRows != m.numRows) {
			throw new IllegalArgumentException("Matricies do not have the same dimensions.");
		}
		Matrix m2 = new Matrix(new int[this.numRows][this.numColumns]);
		for(int i=0; i < this.numRows; i++) 
			for(int j=0; j < this.numColumns; j++) {
				m2.data[i][j] = this.data[i][j] + m.data[i][j]; 
			}
		return m2; 
	}
	
	
	/**
	 * Produces the transpose of this matrix.
	 * @return - the transpose
	 */
	public Matrix transpose()
	{
		Matrix m2 = new Matrix(new int[this.numColumns][this.numRows]);
		for(int i=0; i < this.numRows; i++) 
			for(int j=0; j < this.numColumns; j++) {
				m2.data[j][i] = this.data[i][j]; 
			}
		return m2;
	} 
	
	/**
	 * DO NOT MODIFY THIS METHOD.
	 * Produces a copy of the internal 2D array representing this matrix.
	 * This method is for grading and testing purposes.
	 * @return - the copy of the data
	 */
	public int[][] getData()
	{
		int[][] retVal = new int[data.length][];
		for(int i = 0; i < data.length; i++)
			retVal[i] = data[i].clone();
		return retVal;
	}
	
	public static void main(String[] args)
	{
		Matrix m1 = new Matrix(new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9},
		});
		
		Matrix m2 = new Matrix(new int[][] {
			{1, 2, 3},
			{4, 5, 6},
			{7, 8, 9},
		});
		
		Matrix m3 = new Matrix(new int[][] {
			{1, 2, 3},
			{4, 5, 6},
		});
		
		Matrix m4 = new Matrix(new int[][] {
			{1, 2, 3},
			{4, 5, 6},
		});
		
		Matrix m5 = new Matrix(new int[][] {
			{7, 8},
			{9, 10},
			{11, 12},
		});
		
		System.out.println(m1.toString());
		
		System.out.println(m1.equals(m2));
		
		System.out.println(m1.plus(m2));
		
		System.out.println(m3.transpose());
		
		System.out.println(m4.times(m5));
	}
}
