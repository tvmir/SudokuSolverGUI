package org.openjfx.SudokuSolver;

public class Grid {
	
	private Integer[][] grid = new Integer[9][9];
	private int row;
	private int col;
	
	public Grid() {
		
	}
	
	public Grid(Grid duplicate) {
		for (col=0; col<9; col++) {
			for (row=0; row<9; row++) {
				grid[col][row] = duplicate.getNum(col,row);
			}
		}
	}

	public Integer getNum(int col, int row) {
		return grid[col][row];
	}
	
	public void setNum(int col, int row, Integer n) {
		 grid[col][row] = n;
	}

}
