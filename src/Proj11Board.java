/* Proj11Board
 *
 * CSc 127a Fall 15 - Project 11
 *
 * Author: 
 *
 * This implements the various methods required to display
 * and modify tiles in the game 2048
 * 
 */

import java.util.Random;
public class Proj11Board {
	private int[][] matrix;	//the matrix where values are stored


	public Proj11Board() {
		matrix = new int[4][4];	//initializing the matrix

	}

	public void addOne() {
		boolean flag = true;
		for(int i = 0; i < 4; i++){		//check to see if addOne is feasible
			for(int j = 0; j < 4; j++){
				if(matrix[i][j] == 0){
					flag = false;
				}
			}
		}
		if(flag)
			return;
		Random rand = new Random();
		int	r = rand.nextInt(4);
		int	c = rand.nextInt(4);
		if(matrix[r][c] == 0){
			double probability = rand.nextDouble();
			if(probability<0.75)
				matrix[r][c] = 2;		//probability of adding 2 is 0.75 and of 4 is 0.25
			else
				matrix[r][c] = 4;
		}
		else{
			addOne();
		}
	}

	public void draw() {
		StdDraw.clear();
		StdDraw.setXscale(-0.5, 3.5);	
		StdDraw.setYscale(-3.5, 0.5);

		for (int i = 0; i < 4; i++){		//drawing the grid layout
			StdDraw.line(i - 0.5, 0.5, i - 0.5, -3.5);
			StdDraw.line(-0.5, -i + 0.5, 3.5, -i + 0.5);
		}
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 4; j++){
				if(matrix[i][j] != 0){		//outputting numbers based on matrix entries
					StdDraw.text(j, -i, ""+matrix[i][j]);
				}
			}
		}
	}

	public boolean shiftUp() {
		boolean flag = false;
		
		for(int i = 0; i < 4; i ++){
			for(int j = 0; j < 3; j++){
				if(matrix[j][i] == 0 && matrix[j+1][i] != 0)
					flag = true;
			}
		}
		for(int i = 0; i < 4; i++){
			int[] temp = new int[4];
			for(int j = 0,k = 0; j < 4; j++){
				if(matrix[j][i] != 0){
					temp[k++] = matrix[j][i];
				}
				else
					flag = true;
			}
			for(int l = 0; l < 4; l++)
				matrix[l][i] = temp[l];
		}
		return flag;
	}

	public boolean shiftRight() {
		boolean flag = false;
		
		for(int i = 0; i < 4; i ++){
			for(int j = 3; j > 0; j--){
				if(matrix[i][j] == 0 && matrix[i][j-1] != 0)
					flag = true;
			}
		}
		for(int i = 0; i < 4; i++){
			int[] temp = new int[4];
			for(int j = 3,k = 3; j >= 0; j--){
				if(matrix[i][j] != 0){
					temp[k--] = matrix[i][j];
				}
			}
			matrix[i] = temp;
		}
		return flag;
	}

	public boolean shiftDown() {
		boolean flag = false;
		
		for(int i = 0; i < 4; i ++){
			for(int j = 3; j > 0; j--){
				if(matrix[j][i] == 0 && matrix[j-1][i] != 0)
					flag = true;
			}
		}
		for(int i = 0; i < 4; i++){
			int[] temp = new int[4];
			for(int j = 3,k = 3; j >= 0; j--){
				if(matrix[j][i] != 0){
					temp[k--] = matrix[j][i];
				}
			}
			for(int l = 0; l < 4; l++)
				matrix[l][i] = temp[l];
		}
		return flag;
	}

	public boolean shiftLeft() {
		boolean flag = false;

		for(int i = 0; i < 4; i ++){
			for(int j = 0; j < 3; j++){
				if(matrix[i][j] == 0 && matrix[i][j+1] != 0)
					flag = true;
			}
		}
		for(int i = 0; i < 4; i++){
			int[] temp = new int[4];
			for(int j = 0,k = 0; j < temp.length; j++){
				if(matrix[i][j] != 0){
					temp[k++] = matrix[i][j];
				}
			}
			matrix[i] = temp;
		}
		return flag;
	}

	public boolean collapseUp() {
		boolean flag = false;
		for(int j = 0; j < 4; j++){
			for(int i = 0; i < 3; i++){
				if(matrix[i][j] !=0 && matrix[i][j] == matrix[i+1][j]){
					flag = true;
					matrix[i][j] = 2*matrix[i][j];
					matrix[i+1][j] = 0;
				}
			}
		}
		shiftUp();
		return flag;
	}

	public boolean collapseRight() {
		boolean flag = false;
		for(int i = 0; i < 4; i++){
			for(int j = 3; j > 0; j--){
				if(matrix[i][j] !=0 && matrix[i][j] == matrix[i][j-1]){
					flag = true;
					matrix[i][j] = 2*matrix[i][j];
					matrix[i][j-1] = 0;
				}
			}
		}
		shiftRight();
		return flag;
	}

	public boolean collapseDown() {
		boolean flag = false;
		for(int j = 0; j < 4; j++){
			for(int i = 3; i > 0; i--){
				if(matrix[i][j] !=0 && matrix[i][j] == matrix[i-1][j]){
					flag = true;
					matrix[i][j] = 2*matrix[i][j];
					matrix[i-1][j] = 0;
				}
			}
		}
		shiftDown();
		return flag;
	}

	public boolean collapseLeft() {
		boolean flag = false;
		for(int i = 0; i < 4; i++){
			for(int j = 0; j < 3; j++){
				if(matrix[i][j] !=0 && matrix[i][j] == matrix[i][j+1]){
					flag = true;
					matrix[i][j] = 2*matrix[i][j];
					matrix[i][j+1] = 0;
				}
			}
		}
		shiftLeft();
		return flag;
	}

}
