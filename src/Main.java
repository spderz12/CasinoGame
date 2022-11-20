import java.util.*;
import java.util.Scanner;
import Games.*;

public class Main {
    public static final Scanner scan = new Scanner (System.in);
    public static void main(String[] args) {
        System.out.println("<<CASINO>>");
        System.out.println("What would you like to do?\n(For a command list, type help)");
        while (true){
            String ans = scan.nextLine().replaceAll(" ", "");

            if (ans.equalsIgnoreCase("blackjack")){
                //blackjack class
                Blackjack.play();
            }
            else if (ans.equalsIgnoreCase("slots")){
                //slots class
            }
            else if (ans.equalsIgnoreCase("lotto")){
                //roulette class
            }
            else if (ans.equalsIgnoreCase("poker")){
                //make player vs house poker game with one swap with dealer mechanic
            }
            else if (ans.equalsIgnoreCase("wallet")){
                //checks chip balance and can grant loans
            }
            else if (ans.equalsIgnoreCase("exit")){
                System.out.println("\nClosing Program");
                System.exit(0);
            }
            else if (ans.equalsIgnoreCase("help")){
                System.out.println("COMMANDS:\nBlackjack - play a game of blackjack\nSlots - play a slot machine");
                System.out.println("Lotto - play the lotto\nPoker - play a modified poker game\nWallet - check balance and get loans\n Exit - exit program");
            }
            else
                System.out.println("Undefined command");

        }
    }
}