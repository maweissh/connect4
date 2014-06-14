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
	
	public static void checkVictory(String c, String r, String player){
		int column = Integer.parseInt(c);
		int row = Integer.parseInt(r);
		
	}
	
	public static void clearArray(){
		
	}
}
