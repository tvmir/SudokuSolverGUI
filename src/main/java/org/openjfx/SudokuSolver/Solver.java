package org.openjfx.SudokuSolver;

import java.util.HashSet;
import java.util.Set;

public class Solver {
	
private Set<Integer> audit;
	
	public Solver() {
		audit = new HashSet<Integer>();
		audit.add(1);
		audit.add(2);
		audit.add(3);
		audit.add(4);
		audit.add(5);
		audit.add(6);
		audit.add(7);
		audit.add(8);
		audit.add(9);
	}
	
	
	public boolean checkIfBoxValid(Grid grid, int c, int r) {
		Set<Integer> num = new HashSet<Integer>(audit);
		for (int row=0; row<3; row++) {
			for (int col=0; col<3; col++) {
			Integer ir = grid.getNum(c+col, r+row);
			if (ir!=null) {
				if (!num.contains(ir))
					return false;
				else 
					num.remove(ir);
				}
			}
		}
		return true;
	}
	
	
	public boolean checkBox(Grid grid) {
		for (int row=0; row<9; row+=3) {
			for (int col=0; col<9; col+=3) {
			if (!checkIfBoxValid(grid,col,row))
				return false;
			}
		}
		return true;
	}
	
	
	public boolean checkIfRowValid(Grid grid, int row) {
		Set<Integer> num = new HashSet<Integer>(audit);
		for (int col=0; col<9; col++) {
			Integer ir = grid.getNum(col, row);
			if (ir!=null) {
				if (!num.contains(ir))
					return false;
				else 
				num.remove(ir);
				}
			}
		return true;
	}
	
	
	public boolean checkRow(Grid grid) {
		for (int row=0; row<9; row++) {
			if (!checkIfRowValid(grid,row))
				return false;
		}
		return true;
	}
	
	
	public boolean checkIfColumnValid(Grid grid, int col) {
		Set<Integer> num = new HashSet<Integer>(audit);
		for (int row=0; row<9; row++) {
			Integer ir = grid.getNum(col, row);
			if (ir!=null) {
				if (!num.contains(ir))
					return false;
				else 
					num.remove(ir);
				}
			}
		return true;
	}
	
	
	public boolean checkCol(Grid grid) {
		for (int col=0; col<9; col++) {
			if (!checkIfColumnValid(grid,col))
				return false;
		}
		return true;
	}
	
	
	public boolean validSolution(Grid grid) {
		return checkRow(grid) && checkCol(grid) && checkBox(grid);
	
	}
	
	
	public boolean outcome(Grid grid) {
		for (int row=0; row<9; row++) {
			for (int col=0; col<9; col++) {
				if (grid.getNum(col, row)==null)
					return false;
			}
		}
		return true;
	}
	
	
	public boolean finished(Grid grid) {
		return validSolution(grid) && outcome(grid);
	}

	
	// Recursion+Backtracking
	Grid solution(Grid entity) {				
		if (!validSolution(entity))
			return null;	
		if (finished(entity))
			return entity;
		
		Grid grid = new Grid(entity);
		for (int row=0; row<9; row++) {
			for (int col=0; col<9; col++) {
				Integer ir = grid.getNum(col, row);
				if (ir==null) {
					Set<Integer> check = new HashSet<Integer>(audit);
					for (Integer execute:check) {
						grid.setNum(col, row, execute);
						Grid ans = solution(grid);
						if (ans!=null)
							return ans;
					}
					return null;
				}
			}
		}
		return null;
	}
}
