import java.util.ArrayList;

public class rev {
	public static String[] reverseBlock(Node[] nodes){
		/*
		String path ="C:/Users/Tian/Desktop/Android.gcode";
		ReadFile rf = new ReadFile();
		Node[][] nodes = rf.read(path);	
		*/
		
		int i = 0;
		int j = 0;
		int k = 0;
		int lines = 0;
		int num_sb = 0;
		
		while(nodes[i].getType() != null)
			i++;
		lines = i;
		//System.out.println(lines);
				
		// Get the number of lines in each small block, and the number of small blocks
		ArrayList<String> num_line = new ArrayList<>();
		for(i = 0; i < lines; i++) {
			if(nodes[i].getX() != 0 && nodes[i].getY() != 0 && nodes[i].getE() != 0 && nodes[i].getF() != 0 || i == lines - 1) {				
				num_line.add(String.valueOf(j+1));
				//System.out.println(nodes[number][i].toMoveEF());
				j = 0;
				num_sb++;
			}
			else {
				j++;
			}
		}		
		//System.out.println(num_sb);
		
		// Put the numbers in the int[], and extract the biggest one to initialize the Nodes
		int[] num_lines = new int[num_sb];
		int max = 0;
		for(i = 0; i < num_sb; i++) {
			num_lines[i] = Integer.parseInt(num_line.get(i));
			if(i == 0)
				num_lines[0] = num_lines[0] - 1;
			if(i == num_sb - 1)
				num_lines[num_sb-1] = num_lines[num_sb-1] + 1;
			if(num_lines[i] > max)
				max = num_lines[i];
			//System.out.println(num_lines[i]);
		}
		
		for(i = 0; i < num_lines[0]; i++) 
			if(nodes[i].getF() == 7800 && nodes[i].getZ() != 0)
				k++;
		String[] block = new String[lines-k+1];

		
		
		
		// If the last but one line is a fast move, it can't be reverse
		//System.out.println(lines);
		if(nodes[lines-2].getF() == 7800) {
			for(i = 0; i < lines-k+1; i++)
				block[i] = "0";
			return block;
		}


		
		
		// Create the new Nodes
		Node[][] smallNodes = new Node[num_sb][max+1];
		
		// Initialization
		for(i = 0; i < num_sb; i++)
			for(j = 0; j < max + 1; j++)
				smallNodes[i][j]=new Node();
		
		// Get the nodes in new Nodes
		k = 0;
		for(i = 0; i < num_sb; i++) {
			for(j = 0; j < num_lines[i]; j++) {
				smallNodes[i][j] = nodes[k];
				k++;
			}
		}
		/*
		for(i = 0; i < num_sb; i++)
			for(j = 0; j < num_lines[i] ; j++)
				System.out.println(smallNodes[i][j].toMoveEF());
		*/
		
		reverse r = new reverse();
		block = r.reverse(smallNodes, num_sb, num_lines, lines);
		
		//for(i = 0; i < block.length; i++)
			//System.out.println(block[i]);
		
		return block;
	}

}
