
public class wang {
	public static double[] Performance(String path){

		ReadFile rf = new ReadFile();
		Node[][] nodes = rf.read(path);
		int BNum;
		int i;
		int j;
		double[] P= {0,0,0,0,0,0}; 
		double X = 0, Y = 0, Z = 0, F = 7800;
		double D = 0, T = 0;
		double gD=  0, gT = 0; // total
		double iD = 0, iT = 0, pD = 0, pT = 0;//fast move and printing move
				
        BNum=nodes[0][0].getB();
        //System.out.println(BNum);
        
        for(i = 0; i < BNum; i++) {
        	j=0;       	
        	while(nodes[i][j].getType() != null) {
        		if(nodes[i][j].getZ()!=0) {//z移动
        			D = dis_Z(Z, nodes[i][j]);
        			T = time_Z(D, nodes[i][j], F);
        			iD = iD + D;
        			iT = iT + T;
        		}
        		else if(nodes[i][j].getX()!=0 && nodes[i][j].getY()!=0 && nodes[i][j].getE()==0 && nodes[i][j].getF()!=0) {//不打印，光移动
        			D = dis_XY(X, Y, nodes[i][j]);
        			T = time_XYF(D, nodes[i][j], F);
        			iD = iD + D;
        			iT = iT + T;   			
        		}
        		else if(nodes[i][j].getX()!=0 && nodes[i][j].getY()!=0 && nodes[i][j].getE()!=0 && nodes[i][j].getF()!=0) {//打印，设定小block初始速度
        			D = dis_XY(X, Y, nodes[i][j]);
        			T = time_XYF(D, nodes[i][j], F);
        			pD = pD + D;
        			pT = pT + T; 
        		}
        		else if(nodes[i][j].getX()!=0 && nodes[i][j].getY()!=0 && nodes[i][j].getE()!=0 && nodes[i][j].getF()==0) {//小block恒定速度打印
        			D = dis_XY(X, Y, nodes[i][j]);
        			T = time_XY(D, F);
        			pD = pD + D;
        			pT = pT + T;
        		}      		
        		j++;
        	}     	
        }
        
        gD = iD + pD;
        gT = iT + pT;
        
        /*System.out.print("Global distance: ");
        System.out.println(gD);
        System.out.print("Global time: ");
        System.out.println(gT);
        System.out.print("Idle distance: ");
        System.out.println(iD);
        System.out.print("Idle time: ");
        System.out.println(iT);
        System.out.print("Printing distance: ");
        System.out.println(pD);
        System.out.print("Printing time: ");
        System.out.println(pT);*/
        P[0]=gD;P[1]=gT;P[2]=iD;P[3]=iT;P[4]=pD;P[5]=pT;
        return P;
	}
	
	// The method calculate Z
	public static double dis_Z(double Z, Node node) {
		double D;
		D = Arith.sub(node.getZ(), Z);
		Z = node.getZ();
		return D;
	}
	public static double time_Z(double dis_Z, Node node, double F) {
		double T;
		F = node.getF();
		T = Arith.div(dis_Z, F);
		return T;
	}
	
	// The method calculate XY with F
	public static double dis_XY(double X, double Y, Node node) {
		double D;
		D = Math.sqrt(Arith.add(Arith.mul(Arith.sub(X, node.getX()), Arith.sub(X, node.getX())), Arith.mul(Arith.sub(Y, node.getY()), Arith.sub(Y, node.getY()))));
		X = node.getX();
		Y = node.getZ();
		return D;
	}
	// Calculate the time with a new F
	public static double time_XYF(double dis_XY, Node node, double F) {
		double T;
		F = node.getF();
		T = Arith.div(dis_XY, F);
		return T;
	}
	// Calculate the time without set F
	public static double time_XY(double dis_XY, double F) {
		double T;
		T = Arith.div(dis_XY, F);
		return T;
	}

}
