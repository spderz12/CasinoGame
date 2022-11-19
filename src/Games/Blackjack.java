package Games;

import java.util.Scanner;
import java.util.HashMap;

public class Blackjack {
    public static final Scanner scan = new Scanner (System.in);
    public static final Scanner scan2 = new Scanner (System.in);
    public static void play()
    {
       boolean running = true;
       int altAce = 0;//alternate ace value for 1, not 11
        HashMap<String, Integer> cardValues = new HashMap<>();
        cardValues.put("Jack", 10);
        cardValues.put("Queen", 10);
        cardValues.put("King", 10);
        cardValues.put("Ace", 11);
        //convert card names to their values
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


                    System.out.println("You drew: " + card);

                    if (cardValues.containsKey(card)) {
                        runningTotal += cardValues.get(card); //To avoid error, checks if card is in hashmap
                    } else {
                        int cardInt = Integer.parseInt(card);
                        runningTotal += cardInt;
                    }
                }
                    System.out.println("total = " + runningTotal);
                    if (runningTotal == 21){
                        System.out.println("Blackjack!!! Claim 2x bet - " + wager*2);
                        wager*=2;
                        running = false;
                        break;
                    }


                    //make ace worth 1 or 11 in certain contexts (use if condition "or")
                    while (runningTotal < 21 || runningTotal - (11*altAce) + altAce < 21)//statement eliminate ace value from running total
                    {
                        System.out.println("Hit or Stand?");
                        String ans = scan2.nextLine();
                        if (ans.equalsIgnoreCase("stand")) {
                            running = false;
                            break;
                        }

                        else if (ans.equalsIgnoreCase("hit")) {//gets another card and assigns value to running total
                            card = getCard();

                            if (card.equals("Ace")) {
                                altAce += 1;//alternate ace for multiple value task
                            }


                            System.out.println("You drew: " + card);

                            if (cardValues.containsKey(card)) {
                                runningTotal += cardValues.get(card); //To avoid error, checks if card is in hashmap
                            } else {
                                int cardInt = Integer.parseInt(card);
                                runningTotal += cardInt;
                            }

                            if (runningTotal> 21) {
                                if (runningTotal - (11 * altAce) + altAce <= 21)
                                    System.out.println("Revised total:" + (runningTotal - (11 * altAce) + altAce));
                                else
                                    System.out.println("Total: " + (runningTotal - (11 * altAce) + altAce));
                            }
                            else
                                System.out.println("Total: " + runningTotal);

                        }
                        else{System.out.println("Unknown command. Enter Hit or Stand");}

                        if (runningTotal == 21 || runningTotal-(11*altAce)+altAce == 21){
                            System.out.println("You win!");//remember to make black jack boolean true so dealer automatically loses
                            running = false;
                        }
                        else if (runningTotal > 21 && runningTotal-(11*altAce)+altAce > 21){
                            System.out.println("Bust!! You lose!");
                            running = false;
                        }
                    }
                    //TODO add dealer card set to compare to user
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
