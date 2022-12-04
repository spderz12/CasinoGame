package Games;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Blackjack {
    public static final Scanner scan = new Scanner (System.in);
    public static final Scanner scan2 = new Scanner (System.in);

    static int dealerAltAce = 0;
    static boolean dealerWin = false;
    static int dealerTotal = 0;
    static HashMap<String, Integer> cardValues = new HashMap<>();/*static so other methods
    can access*/

    static ArrayList<Integer> cardDeck = new ArrayList<>();


    public static void play()
    {
        boolean userWin = false;
       boolean running = true;
       boolean userBust = false;
       int altAce = 0;//alternate ace value for 1, not 11
        cardValues.put("Jack", 10);
        cardValues.put("Queen", 10);
        cardValues.put("King", 10);
        cardValues.put("Ace", 11);

        //convert card names to their values
        for(int i = 2; i < 15; i++){
            for(int k = 0; k < 4; k++){
                cardDeck.add(i);
            }
        }
        //creates deck of cards for get card method
        System.out.println("<<Blackjack>>");
        System.out.println("What amount will you wager? (Minimum $25)"); //used for winnings later
        while (true) {
            int wager = scan.nextInt();
            if (wager < 25)
                System.out.println("Not enough cash, try again!");
            else {
                //TODO Subtract bet from user balance
                int runningTotal = 0;//check to see if 21 is reached

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
                    if (runningTotal> 21)//uses altAce so user doesnt go bust
                         {
                            System.out.println("Total: " + (runningTotal - (11 * altAce) + altAce));
                         }
                    else
                        System.out.println("Total: " + runningTotal);

                    if (runningTotal == 21){
                        System.out.println("Blackjack!!! Claim 2x bet - " + wager*2);
                        wager*=2;
                        running = false;
                        userWin = true;
                        break;
                    }

                    //make ace worth 1 or 11 in certain contexts (use if condition "or")
                    while (runningTotal < 21 || runningTotal - (11*altAce) + altAce < 21)//statement eliminate ace value from running total
                    {
                        System.out.println("Hit or Stand?");
                        String ans = scan2.nextLine();
                        if (ans.equalsIgnoreCase("stand")) {
                            running = false;
                            try{
                                Thread.sleep(2000);}
                            catch (Exception e){}

                            break;
                        }

                        else if (ans.equalsIgnoreCase("hit")) {//gets another card and assigns value to running total
                            card = getCard();

                            if (card.equals("Ace")) {
                                altAce += 1;//alternate ace for multiple value task
                            }

                            if (altAce > 1){
                                if ((altAce-1) * 11 + runningTotal > altAce*11 +runningTotal && (altAce-1) * 11 + runningTotal < 22 ){
                                    altAce--;
                                }
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
                            System.out.println("You reached 21!");
                           userWin = true;
                            running = false;
                            break;
                        }
                        else if (runningTotal > 21 && runningTotal-(11*altAce)+altAce > 21){
                            System.out.println("Bust!! You lose!");
                            userBust = true;
                            running = false;
                            try{
                            Thread.sleep(2000);}
                            catch (Exception e){}

                            break;
                        }
                    }

                    }

                        dealerGame();

                        if (userWin && !dealerWin){
                            System.out.println("You got 21 and beat the dealer. Congrats!");
                            //sum increase add above
                        }
                        else if ((dealerWin && userWin)|| dealerTotal == runningTotal || dealerTotal - (11*dealerAltAce) + dealerAltAce == runningTotal - (11*altAce)+ altAce){
                            System.out.println("You and the dealer tied. Collect 50%");
                            wager*=0.5;
                        }
                        else if ((dealerTotal > 21 && (dealerTotal - (11 * dealerAltAce) + dealerAltAce) > 21) && (runningTotal <= 21 || (runningTotal - (11 * altAce) + altAce <= 21))){
                            System.out.println("dealer went bust. you win");
                            //TODO change wager balance
                        }
                        else if((runningTotal > dealerTotal && runningTotal < 22)|| runningTotal-(11*altAce)+altAce > dealerTotal - (11*dealerAltAce) + dealerAltAce && runningTotal-(11*altAce)+altAce < 22 ){
                            System.out.println("You got a higher score than the dealer");
                        }
                        else if ((dealerWin && userBust) || userBust && dealerTotal < 21 || (dealerTotal - (11 * dealerAltAce) + dealerAltAce) < 21){
                            System.out.println("Dealer beat you. :(");
                        }

                        else if ((dealerTotal > runningTotal && dealerTotal < 21) || ((runningTotal - (11 * altAce) + altAce) < (dealerTotal - (11 * dealerAltAce) + dealerAltAce)&& dealerTotal - (11 * dealerAltAce) + dealerAltAce < 21 )){
                            System.out.println("Dealer had a higher score. Dealer wins");
                            //Make wager balance 0
                        }
                        else{
                                System.out.println("Nobody won. Keep your cash.");
                            }
                        }
                       //user has higher count than dealer
                }
            }



    static String getCard(){

        int random = (int)((cardDeck.size()+1)*Math.random());
        int card = cardDeck.get(random);

        cardDeck.remove(random);


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

    static void dealerGame() {
        String card;

        for (int i = 0; i < 2; i++) {
            card = getCard();
            if (card.equals("Ace")) {
                dealerAltAce += 1;
            }

            System.out.println("Dealer drew: " + card);
            if (cardValues.containsKey(card)) {
                dealerTotal += cardValues.get(card);
            } else {
                int cardInt = Integer.parseInt(card);
                dealerTotal += cardInt;
            }
        }

        if (dealerTotal > 21)//uses altAce so user doesnt go bust (impossible on first turn)
        {
            System.out.println("Total: " + (dealerTotal - (11 * dealerAltAce) + dealerAltAce));
        } else
            System.out.println("Total: " + dealerTotal);

        try{
            Thread.sleep(2000);}
        catch (Exception e){}

        if (dealerTotal == 21) {
            System.out.println("Dealer has a blackjack!");
            dealerWin = true;
        } else if (dealerTotal >= 17) {
            System.out.println("Dealer stands at 17 and above");
        } else {
            while (dealerTotal < 17 || dealerTotal - (11 * dealerAltAce) + dealerAltAce < 17) {
                card = getCard();

                System.out.println("Dealer picked up: " + card);

                if (card.equals("Ace")) {
                    dealerAltAce += 1;
                }
                if (dealerAltAce > 1){
                    if ((dealerAltAce-1) * 11 + dealerTotal > dealerAltAce*11 +dealerTotal && (dealerAltAce-1) * 11 + dealerTotal < 22 ){
                        dealerAltAce--;
                    }
                }
                if (cardValues.containsKey(card)) {
                    dealerTotal += cardValues.get(card);
                } else {
                    int cardInt = Integer.parseInt(card);
                    dealerTotal += cardInt;
                }
                if (dealerTotal> 21) {
                    if (dealerTotal - (11 * dealerAltAce) + dealerAltAce <= 21)
                        System.out.println("Revised total:" + (dealerTotal - (11 * dealerAltAce) + dealerAltAce));
                    else
                        System.out.println("Total: " + (dealerTotal - (11 * dealerAltAce) + dealerAltAce));
                }
                else{
                    System.out.println("Total: " + dealerTotal);
                }
            }
            try{
                Thread.sleep(2000);}
            catch (Exception e){}
                if (dealerTotal > 21 && dealerTotal - (11 * dealerAltAce) + dealerAltAce > 21) {
                    System.out.println("Dealer went bust!");
                } else if (dealerTotal == 21 || dealerTotal - (11 * dealerAltAce) + dealerAltAce == 21) {
                    System.out.println("Dealer hit 21!!");
                    dealerWin = true;
                } else {
                    System.out.println("Dealer stands at 17 and above");
                }
            }

        }
            /* This is all the same as for user. If it could be made more efficient that would
            help */
    }



