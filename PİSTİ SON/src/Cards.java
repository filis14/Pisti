import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;


public class Cards {

	public static final int YOU = 1;
	public static final int COMPUTER = 2;
	public static final int FLOOR = 3;
	public static final int JOKERSCORE = 1;
	public static final int NORMALSCORE = 1;
	public static final int PISTISCORE = 10;
	
	
	static ArrayList<String> Cardstock = new ArrayList<String>(52);
	static ArrayList<String> Cardstock2 = new ArrayList<String>(52);
	static String[] Jokers = {"Kupa-joker","Karo-joker","Maca-joker","Sinek-joker"};
	static ArrayList ComputerCards = new ArrayList();
	static ArrayList YourCards = new ArrayList();
	static ArrayList FloorCards = new ArrayList();
	static String ComputerCard;
	static String YourCard;
	static Scanner getScanner = new Scanner(System.in);
	static boolean CFequal = false;
	static int CFequalIndex = 0;
	static boolean ComputerJoker = false;
	static int ComputerJokerIndex = 0;
	static boolean noComputerJoker = false;
	static int noComputerJokerIndex = 0;
	static int choosedNumber;
	static int yourScore = 0;
	static int computerScore = 0;

	 public static void createCardstock() {
		System.out.println("Dealing cards...");
		for (int i = 0; i < 52; i++) {
			if (0 <= i && i < 13) {
				Cardstock.add("Kupa-" + (i % 13 + 1));
			} else if (13 <= i && i < 26) {
				Cardstock.add("Karo-" + (i % 13 + 1));
			} else if (26 <= i && i < 39) {
				Cardstock.add("Maca-" + (i % 13 + 1));
			} else {
				Cardstock.add("Sinek-" + (i % 13 + 1));
			}
		}
		
		Cardstock.remove("Kupa-11");
		Cardstock.add("Kupa-joker");
		Cardstock.remove("Karo-11");
		Cardstock.add("Karo-joker");
		Cardstock.remove("Maca-11");
		Cardstock.add("Maca-joker");
		Cardstock.remove("Sinek-11");
		Cardstock.add("Sinek-joker");
		Cardstock.remove("Sinek-12");
		Cardstock.remove("Sinek-13");
		Cardstock.remove("Kupa-12");
		Cardstock.remove("Kupa-13");
		Cardstock.remove("Karo-12");
		Cardstock.remove("Karo-13");
		Cardstock.remove("Maca-12");
		Cardstock.remove("Maca-13");
		Cardstock.add("Kupa-Q");
		Cardstock.add("Kupa-K");
		Cardstock.add("Karo-Q");
		Cardstock.add("Karo-K");
		Cardstock.add("Sinek-Q");
		Cardstock.add("Sinek-K");
		Cardstock.add("Maca-Q");
		Cardstock.add("Maca-K");
		
	
		Collections.shuffle(Cardstock); //shuffle sız dene
		
		
		
	}
	
	public static void cutting() {
		System.out.println("Cards are cut");
		System.out.println("----------------------");
		Random r= new Random();
		for(int i=0; i<26; i++) {
			int deger= r.nextInt(26);
			
			
			Cardstock2.add(Cardstock.get(deger)); // o elemana erişir
			Cardstock.remove(deger); // arreylisteki o elemanı siler
			
		}
		
		System.out.println("First card desk:" + Cardstock);
		System.out.println("Second card desk:"+Cardstock2);
		
		
	}
	
	public static void developerResult() {
		System.out.println("_________________________");
		System.out.println("Tum Kartlar = " + Cardstock);
		System.out.println("FloorCards = " + FloorCards);
		System.out.println("YourCards = " + YourCards);
		System.out.println("PC = " + ComputerCards);
		System.out.println("-------------------------");
	}
	
	public static void DealCard(int dealTo) {
		for (int i = 0; i < 4; i++) {
			if (dealTo == FLOOR)
				FloorCards.add(Cardstock.get(0));
			else if (dealTo == COMPUTER)
				ComputerCards.add(Cardstock.get(0));
			else if (dealTo == YOU)
				YourCards.add(Cardstock.get(0));
			Cardstock.remove(0);
		}

		if (dealTo == FLOOR)
			System.out.println("Last card on table " + FloorCards.get(FloorCards.size() - 1));

		if (dealTo == YOU)
			System.out.println("You have " + YourCards);

	}

	public static void checkGameStatus(String playedCard, int player) {
		String floorCard = "";
		if (FloorCards.isEmpty()) {
			FloorCards.add(playedCard);
		} else {
			floorCard = (String) FloorCards.get(FloorCards.size() - 1);
			if (playedCard.substring(1, playedCard.length()).equals(floorCard.substring(1, floorCard.length()))) {
				if (FloorCards.size() == 1) {
					if (player == COMPUTER)
						computerScore = computerScore + PISTISCORE;
					else if (player == YOU)
						yourScore = yourScore + PISTISCORE;

					FloorCards.removeAll(FloorCards);
				} else {
					if (player == COMPUTER)
						computerScore = computerScore + FloorCards.size() + NORMALSCORE;
					else
						yourScore = yourScore + FloorCards.size() + NORMALSCORE;

					FloorCards.removeAll(FloorCards);
				}
			} else if (playedCard.equals(Jokers[0]) || playedCard.equals(Jokers[1]) || playedCard.equals(Jokers[2])
					|| playedCard.equals(Jokers[3])) {
				if (player == COMPUTER)
					computerScore = computerScore + FloorCards.size() + JOKERSCORE;
				else
					yourScore = yourScore + FloorCards.size() + JOKERSCORE;

				FloorCards.removeAll(FloorCards);
			} else
				FloorCards.add(playedCard);
		}
	}

	public static void playCard(int player) {
		String floorCard = "";
		if (!FloorCards.isEmpty()) {
			floorCard = (String) FloorCards.get(FloorCards.size() - 1);
		}

		if (player == YOU) {
			System.out.print("Enter Card Number To Play: ");
			if (!YourCards.isEmpty()) {

				int ChoosedNumber = (getScanner.nextInt());

				while (YourCards.size() < ChoosedNumber || 1 > ChoosedNumber) {
					System.out.println("Please enter new card number between 1 and " + YourCards.size());
					System.out.print("Enter Card Number To Play: ");
					ChoosedNumber = (getScanner.nextInt());
				}

				checkGameStatus(YourCards.get(ChoosedNumber - 1).toString(), YOU);
				System.out.println("You played " + YourCards.get(ChoosedNumber - 1).toString());
				YourCards.remove(ChoosedNumber - 1);
				System.out.println("You have " + YourCards);
			}
		} else if (player == COMPUTER) {
			if (!FloorCards.isEmpty()) {
				for (int i = 0; i < ComputerCards.size(); i++) {
					ComputerCard = ComputerCards.get(i).toString();
					if (ComputerCard.substring(1, ComputerCard.length()).equals(
							floorCard.substring(1, floorCard.length()))) {
						CFequal = true;
						CFequalIndex = i;
					}
				}
				if (CFequal) {
					checkGameStatus(ComputerCards.get(CFequalIndex).toString(), COMPUTER);
					System.out.println("Last card on table " + ComputerCards.get(CFequalIndex).toString());
					ComputerCards.remove(CFequalIndex);
					CFequal = false;
					CFequalIndex = 0;
				} else {
					for (int j = 0; j < ComputerCards.size(); j++) {
						for (int k = 0; k < 4; k++) {
							if (ComputerCards.get(j).toString().equals(Jokers[k])) {
								ComputerJoker = true;
								ComputerJokerIndex = j;
							}
						}
					}
					if (ComputerJoker) {
						checkGameStatus(ComputerCards.get(ComputerJokerIndex).toString(), COMPUTER);
						System.out.println("Last card on table " + ComputerCards.get(ComputerJokerIndex).toString());
						ComputerCards.remove(ComputerJokerIndex);
						ComputerJoker = false;
						ComputerJokerIndex = 0;
					} else {
						int rastgele = (int) (Math.random() * ComputerCards.size());
						checkGameStatus(ComputerCards.get(rastgele).toString(), COMPUTER);
						System.out.println("Last card on table " + ComputerCards.get(rastgele).toString());
						ComputerCards.remove(rastgele);
					}
				}
			} else {
				if (ComputerCards.size() == 1) {
					checkGameStatus(ComputerCards.get(0).toString(), 1);
					System.out.println("Last card on table " + ComputerCards.get(0).toString());
					ComputerCards.removeAll(ComputerCards);
				} else {
					for (int j = 0; j < ComputerCards.size(); j++) {
						for (int k = 0; k < 4; k++) {
							if (!(ComputerCards.get(j).toString().equals(Jokers[k]))) {
								noComputerJoker = true;
								noComputerJokerIndex = j;
							}
						}
					}
					if (noComputerJoker) {
						checkGameStatus(ComputerCards.get(noComputerJokerIndex).toString(), COMPUTER);
						System.out.println("Last card on table " + ComputerCards.get(noComputerJokerIndex).toString());
						ComputerCards.remove(noComputerJokerIndex);
						noComputerJoker = false;
						noComputerJokerIndex = 0;
					} else {
						int rastgele = (int) (Math.random() * ComputerCards.size());
						checkGameStatus(ComputerCards.get(rastgele).toString(), 1);
						System.out.println("Last card on table " + ComputerCards.get(rastgele).toString());
						ComputerCards.remove(rastgele);
					}
				}
			}
		}
	}
	
	
	public static void gameFinished() {
		System.out.println("_________________________");
		System.out.println("Game Finished");
		System.out.println("Result: ");
		System.out.println("Computer Score = " + computerScore);
		System.out.println("Your Score = " + yourScore);

		if (yourScore < computerScore)
			System.out.println("Computer Wins!");
		else if (computerScore < yourScore)
			System.out.println("You Wins!");
		else
			System.out.println("Nobody won! Your score and computer score are equal :)");

		System.out.println("Thanks for playing.");
		System.out.println("-------------------------");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	