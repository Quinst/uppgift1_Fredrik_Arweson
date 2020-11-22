package Tictactoe;

import java.util.Objects;
import java.util.Scanner;

public class Player {
	public Player(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
		
	}
	
	public String getName() {
		return name;
		
	}
	
	public String getSymbol() {
		return symbol;
		
	}
	
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Player player = (Player) o;
		return Objects.equals(name, player.name) &&
				Objects.equals(symbol, player.symbol);
	}
	
	public int hashCode() {
		return Objects.hash(name, symbol);
	}
	
	private String name;
	
	private String symbol;
	
	public void playMove(Game game) {
		System.out.println("Skriv in dina x-y koordinater, för övre vänstra hörnet skriv 1,1");
		Scanner scanner = new Scanner(System.in);
		String input = scanner.next();
		try {
			String[] moves = input.split(",");
			game.move(Integer.parseInt(moves[0]) - 1, Integer.parseInt(moves[1]) - 1);
			}
		catch (Exception e) {
			playMove(game);
		}
	}
	//Hela funktionen i denna klass är att läsa in spelarens drag

}
