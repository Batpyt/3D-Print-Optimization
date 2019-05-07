import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadFile {
	//"C:\\Users\\92886\\eclipse-workspace\\bishe\\src\\SAtest.gcode"		
	public static Node[] read(String gcode[]) {		
		int i, k;
		
		Node[] nodes = new Node[gcode.length+1];// j-2 is the block number, biggest block need add a line 'G92'
				//System.out.println(number_block);
				//System.out.println(biggest_block);
		//initialization
		for(i = 0; i < gcode.length + 1; i++)
				nodes[i]=new Node();
		
		
		//Start to convert string to Node
		
		nodes[0].setType("G92");
		nodes[0].setE(0);
		i = 1;

		int index = 0;
		for(k = 0; k < gcode.length - 1; k++) {				
			if(gcode[k].contains("G1")) {
				nodes[i].setType("G1");
				if(gcode[k].contains("Z")) {
					index = gcode[k].indexOf("Z");
					nodes[i].setZ(Double.valueOf(gcode[k].substring(index + 1, gcode[k].indexOf(" ", index))));
					nodes[i].setF(Double.valueOf(gcode[k].substring(gcode[k].indexOf("F") + 1)));
					i++;
					/*System.out.print(i);
					System.out.print(j);
					System.out.println(k);
					System.out.println(nodes[i][j].toMoveZ());*/
				}
				else if(gcode[k].contains("X") && gcode[k].contains("Y")) {
					index = gcode[k].indexOf("X");
					nodes[i].setX(Double.valueOf(gcode[k].substring(index + 1, gcode[k].indexOf(" ", index))));
					index = gcode[k].indexOf("Y");
					nodes[i].setY(Double.valueOf(gcode[k].substring(index + 1, gcode[k].indexOf(" ", index))));
					
					if(gcode[k].contains("E") == false && gcode[k].contains("F") == true) {
						nodes[i].setF(Double.valueOf(gcode[k].substring(gcode[k].indexOf("F") + 1)));
						i++;
						/*System.out.print(i);
						System.out.print(j);
						System.out.println(k);
						System.out.println(nodes[i][j].toMoveF());*/
					}
					else if(gcode[k].contains("E") == true && gcode[k].contains("F") == false) {
						nodes[i].setE(Double.valueOf(gcode[k].substring(gcode[k].indexOf("E") + 1)));
						i++;
						/*System.out.print(i);
						System.out.print(j);
						System.out.println(k);
						System.out.println(nodes[i][j].toMoveE());*/
					}
					else if(gcode[k].contains("E") && gcode[k].contains("F")) {
						index = gcode[k].indexOf("E");
						nodes[i].setE(Double.valueOf(gcode[k].substring(index + 1, gcode[k].indexOf(" ", index))));
						nodes[i].setF(Double.valueOf(gcode[k].substring(gcode[k].indexOf("F") + 1)));
						i++;
						/*System.out.print(i);
						System.out.print(j);
						System.out.println(k);
						System.out.println(nodes[i][j].toMoveEF());*/
					}
				}
				else if (gcode[k].contains("E") && gcode[k].contains("F")) {
					index = gcode[k].indexOf("E");
					if(index < gcode[k].indexOf("F")) {
						nodes[i].setE(Double.valueOf(gcode[k].substring(index + 1, gcode[k].indexOf(" ", index))));
						nodes[i].setF(Double.valueOf(gcode[k].substring(gcode[k].indexOf("F") + 1)));
						i++;
						/*System.out.print(i);
						System.out.print(j);
						System.out.println(k);
						System.out.println(nodes[i][j].toStartEF());*/
					}
					else {
						index = gcode[k].indexOf("F");
						nodes[i].setF(Double.valueOf(gcode[k].substring(index + 1, gcode[k].indexOf(" ", index))));
						nodes[i].setE(Double.valueOf(gcode[k].substring(gcode[k].indexOf("E") + 1)));
						i++;
						/*System.out.print(i);
						System.out.print(j);
						System.out.println(k);
						System.out.println(nodes[i][j].toStartFE());*/
					}
				}
			}
		}	
		
		return nodes;
	}
}
