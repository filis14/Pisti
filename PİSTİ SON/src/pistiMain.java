import java.util.Scanner;
import java.util.Random;

public class pistiMain {

	public static void main(String[] args) {

		Cards Cards2 = new Cards();
		Scanner sc = new Scanner(System.in);
		Random r = new Random();

		System.out.println("Welcome to Pisti Game");
		System.out.println("Please make a choice...");

		Choices();

		int choice = sc.nextInt();

		if (choice == 1) {
			System.out.println(
					"The game is played by taking turns. The player starts the game and is expected to place one\r\n"
							+ "of his cards on top of the board. While doing so, he is allowed to take all the cards from the\r\n"
							+ "board if the cards have the same value. If the top card is ♣7 and the player has a 7 from any\r\n"
							+ "suit, then the player can play the 7, and take all the cards.\r\n"
							+ "The name “pişti” comes from a case where there is a single card on the board, and one of the\r\n"
							+ "players has the same card. For example, there is a single card, ♥4 on the board, and one of the\r\n"
							+ "players has a four from any suit, then the player can make a “pişti” and score 10 points.\r\n"
							+ "Talking about “points,” two cards have additional points: ♦10 is 3 points and ♣2 is 2 points.\r\n"
							+ "All other cards are 1 point each. Once all cards are played, the player who has more cards\r\n"
							+ "gets an additional 3 points.\r\n"
							+ "The “Jack” of any suit has a special power. This card can take all the cards from the board\r\n"
							+ "without taking the value of the top card into account.\r\n"
							+ "If there are cards left on the board once all the cards are played, the last player who got the\r\n"
							+ "cards from the board also gets the remaining ones.\r\n"
							+ "Basically, the game is to get all the cards on the board either by using a Jack or by using a\r\n"
							+ "card that has the same value as the top card on the board");

			Choices();

		}

		else if (choice == 2) {
			System.out.println("The game is started..");
			

			Cards2.createCardstock();
			Cards2.developerResult();
			Cards.shuffle();
			
			Cards2.cutting();
			Cards2.DealCard(3); // floor
			for (int i = 1; i <= 6; i++) {
				System.out.println("_____________ ROUND " + i + " _____________");
				Cards2.DealCard(2);
				Cards2.DealCard(1);
				for (int j = 0; j < 4; j++) {
					Cards2.playCard(1); // ben
					Cards2.playCard(2); // computer
					// Cards2.developerResult();
				}
			}
			// developerResult();

			Cards2.gameFinished();

		}

		else if (choice == 3) {
			System.out.println("It is exiting from the game..");

		}

		else {
			System.out.print("Plese enter a valid number!" + "1-2-3");
			Choices();
		}

	} // main

	private static void Choices() {
		System.out.println("1-How to play the game?");
		System.out.println("2-Play the game");
		System.out.println("3-Exit");

	}

	

}
