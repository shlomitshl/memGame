package memo;

import java.util.Scanner;
public class MemoryGame {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("To start game enter 's':");
        String ch = scan.nextLine();
        if(ch.equals("s")) {
           Game game=new Game();
           game.startGame();
        }
        else
            System.out.println("Goodbye...!");
    }
}