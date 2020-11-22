package Tictactoe;

import java.util.Scanner;

public class Main {
	
	public static final int GRID =3;
	
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		Game game;
		loop:
			while (true) {
				System.out.println("Skriv in hur m�nga spelare ni �r");
				try {
					String input = scanner.next();
					switch (Integer.parseInt(input)) {
					case 1:
						game = new Game("Player1", GRID);
						break loop;
					case 2:
						game = new Game("Player1", "Player2", GRID);
						break loop;
					}
				} catch (Exception e) {
				}
			}
		game.start();
		Player winner = game.getWinner();
		System.out.println(winner != null ? "Vinnaren �r " + winner.getName() : "Oavgjort");
	}
	/* H�r laddar vi spelet, tar in hur m�nga spelare som skall vara med och ordnar med texten f�r n�r
	 * spelet �r klart. Valt denna l�sning f�r att g�ra koden mer l�ttl�st
	 */

}
