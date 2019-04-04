import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
	//"C:\\Users\\92886\\eclipse-workspace\\bishe\\src\\SAtest.gcode"		
		public static Node[][] read(String path) {
			int i = 0;
			// dynamic length
			ArrayList<String> arrayList = new ArrayList<>();
			try {
				FileReader fr = new FileReader(path);
				BufferedReader bf = new BufferedReader(fr);
				String str;
				//read line by line
				while ((str = bf.readLine()) != null) {
					arrayList.add(str);
				}
				bf.close();
				fr.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			// extract the string array from ArrayList
			int length = arrayList.size();
			String[] gcode = new String[length];
			for (i = 0; i < length; i++) {
				gcode[i] = arrayList.get(i);
			}
			/*
			for(int i=0;i<gcode.length;i++)
				System.out.println(gcode[i]);
			*/
			
			
			int j = 0;
			int number_block = 0;
			int biggest_block = 0;
			ArrayList<String> start_line = new ArrayList<>();
			//find the 2D size of node
			for(i = 0; i < gcode.length; i++) {		
				if(gcode[i].contains("G92")) {
					if(j > biggest_block) {
						biggest_block = j;
					}
					j = 0;
					start_line.add(String.valueOf(i));
					number_block++;
				}
				else if(gcode[i].contains("G1")) {
					j++;
				}
			}
			// get the start index of every block
			int[] start_no = new int[number_block];
			for(i = 0; i < number_block; i++) {
				start_no[i] = Integer.parseInt(start_line.get(i));
				//System.out.println(start_no[i]);
			}
			
			// !!!!!!!!!!!there's some problem about   new Node[number_block - 2][]
			Node[][] nodes = new Node[number_block-2][biggest_block+2];// j-2 is the block number, biggest block need add a line 'G92'
			
			//initialization
			for(i = 0; i < number_block - 2; i++)
				for(j = 0; j < biggest_block + 2; j++)
						nodes[i][j]=new Node();
			
			//Start to convert string to Node		
			int index = 0;
			int k = start_no[1];
			for(i = 0; i < number_block - 2; i++) {
				for(j = 0; j < start_no[i+2] - start_no[i+1]; j++) {
					if(gcode[k].contains("G92") && gcode[k].contains("E0")) {
						nodes[i][j].setType("G92");
						nodes[i][j].setE(0);
						/*System.out.print(i);
						System.out.print(j);
						System.out.println(k);
						System.out.println(nodes[i][0].toStart());*/
					}
					else if(gcode[k].contains("G1")) {
						nodes[i][j].setType("G1");
						if(gcode[k].contains("Z")) {
							index = gcode[k].indexOf("Z");
							nodes[i][j].setZ(Double.valueOf(gcode[k].substring(index + 1, gcode[k].indexOf(" ", index))));
							nodes[i][j].setF(Double.valueOf(gcode[k].substring(gcode[k].indexOf("F") + 1)));
							/*System.out.print(i);
							System.out.print(j);
							System.out.println(k);
							System.out.println(nodes[i][j].toMoveZ());*/
						}
						else if(gcode[k].contains("X") && gcode[k].contains("Y")) {
							index = gcode[k].indexOf("X");
							nodes[i][j].setX(Double.valueOf(gcode[k].substring(index + 1, gcode[k].indexOf(" ", index))));
							index = gcode[k].indexOf("Y");
							nodes[i][j].setY(Double.valueOf(gcode[k].substring(index + 1, gcode[k].indexOf(" ", index))));
							
							if(gcode[k].contains("E") == false && gcode[k].contains("F") == true) {
								nodes[i][j].setF(Double.valueOf(gcode[k].substring(gcode[k].indexOf("F") + 1)));
								/*System.out.print(i);
								System.out.print(j);
								System.out.println(k);
								System.out.println(nodes[i][j].toMoveF());*/
							}
							else if(gcode[k].contains("E") == true && gcode[k].contains("F") == false) {
								nodes[i][j].setE(Double.valueOf(gcode[k].substring(gcode[k].indexOf("E") + 1)));
								/*System.out.print(i);
								System.out.print(j);
								System.out.println(k);
								System.out.println(nodes[i][j].toMoveE());*/
							}
							else if(gcode[k].contains("E") && gcode[k].contains("F")) {
								index = gcode[k].indexOf("E");
								nodes[i][j].setE(Double.valueOf(gcode[k].substring(index + 1, gcode[k].indexOf(" ", index))));
								nodes[i][j].setF(Double.valueOf(gcode[k].substring(gcode[k].indexOf("F") + 1)));
								/*System.out.print(i);
								System.out.print(j);
								System.out.println(k);
								System.out.println(nodes[i][j].toMoveEF());*/
							}
						}
						else if (gcode[k].contains("E") && gcode[k].contains("F")) {
							index = gcode[k].indexOf("E");
							if(index < gcode[k].indexOf("F")) {
								nodes[i][j].setE(Double.valueOf(gcode[k].substring(index + 1, gcode[k].indexOf(" ", index))));
								nodes[i][j].setF(Double.valueOf(gcode[k].substring(gcode[k].indexOf("F") + 1)));
								/*System.out.print(i);
								System.out.print(j);
								System.out.println(k);
								System.out.println(nodes[i][j].toStartEF());*/
							}
							else {
								index = gcode[k].indexOf("F");
								nodes[i][j].setF(Double.valueOf(gcode[k].substring(index + 1, gcode[k].indexOf(" ", index))));
								nodes[i][j].setE(Double.valueOf(gcode[k].substring(gcode[k].indexOf("E") + 1)));
								/*System.out.print(i);
								System.out.print(j);
								System.out.println(k);
								System.out.println(nodes[i][j].toStartFE());*/
							}
						}
					}
					k++;
				}
			}

			return nodes;
		}

}
