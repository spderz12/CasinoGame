package Games;

import java.util.Scanner;
import java.util.HashMap;

public class Blackjack {
    public static final Scanner scan = new Scanner (System.in);
    public static void play()
    {
       boolean running = true;
       int altAce = 0;
        System.out.println("<<Blackjack>>");
        System.out.println("What amount will you wager? (Minimum $25)"); //used for winnings later
        while (true) {
            int wager = scan.nextInt();
            if (wager < 25)
                System.out.println("Not enough cash, try again!");
            else {
                //TODO Subtract bet from user balance
                int runningTotal = 0;//check to see if 21 is reached
                int dealerTotal = 0;//used later for opponent
                while (running) {

                    String card = null;
                    for (int i = 0; i < 2; i++) {
                        card = getCard();//automatically draws two cards at beginning of game
                        if (card.equals("Ace")) {
                            altAce += 1;//alternate ace for multiple value task
                        }
                    }

                    System.out.println("You drew: " + card);

                    HashMap<String, Integer> cardValues = new HashMap<>();
                    cardValues.put("Jack", 10);
                    cardValues.put("Queen", 10);
                    cardValues.put("King", 10);
                    cardValues.put("Ace", 11);
                    //assigning hashmap to change card names to number values
                    if (cardValues.containsKey(card)) {
                        runningTotal += cardValues.get(card); //To avoid error, checks if card is in hashmap
                    }
                    else {
                        int cardInt = Integer.parseInt(card);
                        runningTotal += cardInt;
                    }
                    System.out.println("total = " + runningTotal);
                   //TODO show current card values and also alternative if ace present (altAce >= 1)
                    //TODO make ace worth 1 or 11 in certain contexts (use if condition "or")
                    System.out.println("again?");
                    String ans = scan.nextLine();
                    if (ans.equals("no"))
                    break;
                }

            }
        }
    }

    static String getCard(){

        int card = (int)(13*Math.random()+2);


        if (card == 11){
            return "Jack";
        }
        else if ( card == 12){
            return "Queen";
        }
        else if (card == 13){
            return "King";
        }
        else if (card == 14) {
            return "Ace" ;
        }
        else {
            return Integer.toString(card);
        }
    }
}
