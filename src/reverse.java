import java.util.ArrayList;

public class reverse {
	public String[] reverse(Node[][] node, int last_a, int[] last_b, int blockLength){
		int i = 0;
		int j = 0;
		int k = 0;
		double lastE = node[last_a-1][last_b[last_a-1]-2].getE();//get the total consumption of E
		double fastF = node[0][last_b[0]-2].getF();//speed of quick move
		double[] F = new double[last_a];//every speed of movements
		//get the number of fast move lines in each small block
		ArrayList<String> num_f = new ArrayList<>();
		for(i = 0; i < last_a; i++) {
			for(j = 0; j < last_b[i]; j++) {
				if(node[i][j].getF() == 7800)
					k++;
			}
			num_f.add(String.valueOf(k));
			k = 0;
		}
		int[] num_fastF = new int[last_a];
		for(i = 0; i < last_a; i++) {
			num_fastF[i] = Integer.parseInt(num_f.get(i));
			//System.out.println(num_fastF[i]);
		}
		if(node[0][1].getZ() != 0) 
			num_fastF[0] = num_fastF[0] - 1;
		
		// Create the new block	
		blockLength = blockLength-num_fastF[0]+1;
		String[] rev_block = new String[blockLength];
		
// Start
		k = 0;// Index of the new String
		// The G92 E0 has been put in the last line
		rev_block[blockLength-1] = node[0][0].toStart();
		
		// G1 Fnnn Ennn, The last but one line
		rev_block[blockLength-2] = node[last_a-1][last_b[last_a-1]-1].toStartFE();
		
		// G1 Xnnn Ynnn Ennn, the last but two line
		node[0][last_b[0]-2].setE(lastE);
		node[0][last_b[0]-2].setF(0);
		rev_block[blockLength-3] = node[0][last_b[0]-2].toMoveE();
		
		// G1 Znnn Fnnn, If there is a move of Z, this is the first line		
		if(node[0][1].getZ() != 0) {
			rev_block[k] = node[0][1].toMoveZ();
			k++;	
		}
		
		// G1 Xnnn Ynnn Fnnn, first line if there is no Z change
		node[last_a-1][last_b[last_a-1]-2].setE(0);
		node[last_a-1][last_b[last_a-1]-2].setF(fastF);
		rev_block[k] = node[last_a-1][last_b[last_a-1]-2].toMoveF();
		k++;
		
		// G1 Ennn Fnnn, second line if there is no Z change
		rev_block[k] = node[0][last_b[0]-1].toStartEF();
		k++;
		
		
		//store the F value (for each small block) before reverse
		for(i = 1; i < last_a; i++) {
			F[i] = node[i][0].getF();
			node[i][0].setF(0);
		}
		
		// Change the value from the end to the start, and store it in the new block ´ÓºóÍùÇ°
		double E = 1;
		for(i = last_b[last_a-1] - 3; i >= 0; i--) {	//last small block
			//System.out.println(lastE);
			E = Arith.add(Arith.sub(lastE, node[last_a-1][i].getE()),E);
			//System.out.println(E);
			lastE = node[last_a-1][i].getE();
			//System.out.println(lastE);
			node[last_a-1][i].setE(E);
			// Judge it if it is the header of new block's small block
			if(i == last_b[last_a-1] - 3) {
				node[last_a-1][i].setF(F[last_a-1]);// The first line SPEED after reverse
				rev_block[k] = node[last_a-1][i].toMoveEF();
				k++;
			}
			else {
				rev_block[k] = node[last_a-1][i].toMoveE();
				k++;
			}
		}		
		for(i = last_a - 2; i > 0; i--) { // The small block from the last but one to the second small block
			for(j = last_b[i] - 1; j >= 0; j--) {
				if(j > last_b[i] - 1 - num_fastF[i]) {	// Xnnn Ynnn F7800
					rev_block[k] = node[i][j].toMoveF();
					k++;
				}
				else if(j == last_b[i] - 1 - num_fastF[i]) {
					//System.out.println(lastE);
					E = Arith.add(Arith.sub(lastE, node[i][j].getE()),E);
					//System.out.println(E);
					lastE = node[i][j].getE();
					//System.out.println(lastE);
					node[i][j].setE(E);
					node[i][j].setF(F[i]);
					rev_block[k] = node[i][j].toMoveEF();
					k++;
				}
				else {
					//System.out.println(lastE);
					E = Arith.add(Arith.sub(lastE, node[i][j].getE()),E);
					//System.out.println(E);
					lastE = node[i][j].getE();
					//System.out.println(lastE);
					node[i][j].setE(E);
					rev_block[k] = node[i][j].toMoveE();
					k++;
				}
				
				/*
				else if(node[i][j].getE() != 0) {
					lastE = lastE - node[i][j].getE() + 1;
					node[i][j].setE(lastE);
				}
				*/
				
			}
		}
			
		return rev_block;
	}

}
