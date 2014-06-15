package models;

import java.io.IOException;

public class Connect4Logic {
	private static String[][] array= new String[7][6];
	
	//überprüft die eingeworfene Spalte auf ein freies Feld, und gibt dessen Position zurück
	public static String addChip(String column, String player){
		int col = Integer.parseInt(column);
		for (int i = 5; i >= 0; i--) {
			if (array[col][i] == null) {
				array[col][i] = player;
				return String.valueOf(i);
			}
		}
		return "column is full";
	}
	
	public static int checkVictory(){	
		try {
			int erg;
			//nach viererkette in der horizontalen suchen
			erg = checkHorizon(); 
			if (erg != 0) {
				return erg ;
			}
			
			//nach viererkette in der vertikalen suchen
			erg = checkVertical();
			if (erg !=0) {
				return erg;
			}
			
			//todo: Impelementierung der checkDiagonal Methoden
			erg = checkDiagonalOne();
			if (erg !=0) {
				return erg;
			}
			
			erg = checkDiagonalTwo();
			if (erg !=0) {
				return erg;
			}
			
			return 0;
			
		} catch (IOException e) {
			System.out.println("problem in victory");
			e.printStackTrace();
			return 6;
		}		
		
	}
	
	private static int checkHorizon() throws IOException{
		int p1 =0;
		int p2 =0;
		for (int r = 0; r < 6; r++) {
			p1=0;
			p2=0;
			for (int c = 0; c < 7; c++) {
				if (array[c][r]==null) {
					p1=0;
					p2=0;
					continue;
				} else {
					if (array[c][r].equals("e")) {
						if (p2>0) {
							p2=0;
						}
						p1++;
						if (p1 == 4) {
							return 1;
						}
					} else {
						if (p1>0) {
							p1=0;
						}
						p2++;
						if (p2 == 4) {
							return 2;
						}
					}
				}
				
			}
		}
		return 0;
	}
	
	private static int checkVertical() throws IOException{
		int p1 =0;
		int p2 =0;
		for (int c = 0; c < 7; c++) {
			p1=0;
			p2=0;
			for (int r = 0; r < 6; r++) {
				if (array[c][r]==null) {
					p1=0;
					p2=0;
					continue;
				} else {
					if (array[c][r].equals("e")) {
						if (p2>0) {
							p2=0;
						}
						p1++;
						if (p1 == 4) {
							return 1;
						}
					} else {
						if (p1>0) {
							p1=0;
						}
						p2++;
						if (p2 == 4) {
							return 2;
						}
					}
				}
				
			}
		}
		return 0;		
	}
	
	private static int checkDiagonalOne(){
		return 0;
	}
	
	private static int checkDiagonalTwo(){
		return 0;
	}
	
	
	public static void clearArray(){
		try {
			for (int c = 0; c < 7; c++) {
				for (int r = 0; r < 6; r++) {
					array[c][r] = null;
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
