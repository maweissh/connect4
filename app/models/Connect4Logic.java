package models;

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
		int p1 =0;
		int p2 =0;
		try {
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
		} catch (Exception e) {
			System.out.println("problem in victory");
			e.printStackTrace();
		}
		
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
