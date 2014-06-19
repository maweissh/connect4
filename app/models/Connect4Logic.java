package models;

import java.io.IOException;

import scala.util.parsing.combinator.testing.Str;

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
	
	public static int checkVictory(String x, String y, String player){	
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
			erg = chechDiagonal(x, y, player);
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
					if (array[c][r].equals("eins")) {
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
					if (array[c][r].equals("eins")) {
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
	
	private static int chechDiagonal(String column, String row, String player){
		int x = Integer.parseInt(column);
		int y = Integer.parseInt(row);
		// Addieren in horizontaler, vertikaler, diagonal (links-oben, rechts-unten), diagonal (links-unten, rechts-oben) Richtung
		// wenn Summe >= 3 (+ aktuelles Kästchen = 4) dann Sieg
		
		if (countCells(x - 1, y, -1, 0, player) + countCells(x + 1, y, 1, 0, player) >= 3 ||
				countCells(x, y - 1, 0, -1, player) + countCells(x, y + 1, 0, 1, player) >= 3 ||
				countCells(x - 1, y - 1, -1, -1, player) + countCells(x + 1, y + 1, 1, 1, player) >= 3 ||
				countCells(x - 1, y + 1, -1, 1, player) + countCells(x + 1, y - 1, 1, -1, player) >= 3) {
			if (player.equals("eins")) {
				return 1;
			} else {
				return 2;
			}			
		}		
		return 0;
	}		 
		 
		// aktuelle x,y-Position, x,y-Richtung in die gesucht wird, Spielernummer
	private static int countCells(int x, int y, int xdir, int ydir, String player) {		 
		// noch innerhalb des Feldes?
		if (x >= 0 && x < 7 && y >= 0 && y < 6) {		 
			// wenn gleiche Farbe wie Spieler, dann eins addieren und in gleiche Richtung weitersuchen
			if (array[x][y] == player){
				return countCells(x + xdir, y + ydir, xdir, ydir, player) + 1;
			}
		}		 
		// außerhalb oder andere Farbe
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
