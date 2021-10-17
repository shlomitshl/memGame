package memo;
import java.util.Scanner;
public class Game  {
    public Board board;
    String[] names;
    int[] points;
    int participants,level;
    int row1,column1,row2,column2;
    boolean exceptionSign=true;
    public void startGame() {
        do {
            try {
                System.out.println("Please enter number of participants(2-5) :");
                Scanner scan = new Scanner(System.in);
                participants = scan.nextInt();
                exceptionSign = false;
            } catch (Exception e) {  //if the input was not an integer we are throwing an exception
                System.out.println("This is not a number!");
                System.out.println("Try again...");
            }
            //}while(exceptionSign);
        }while(participants<2||participants>5||exceptionSign);   //checking legality of participants number

        names = new String[participants];
        points= new int[participants];

        for (int i=0;i<participants;i++) {   //geting names of participants and saying Hellow
            System.out.println("Participant " + (i + 1) + " Please enter your name:");
            Scanner scan1 = new Scanner(System.in);
            names[i] = scan1.nextLine();
            System.out.println("Hellow " + names[i] + "!");
        }

        System.out.print("For getting instructions press 'i' ,else press any key to get started...:");
        Scanner scan1 = new Scanner(System.in);
        char instructions = scan1.next().charAt(0);
        if (instructions == 'i') {
            System.out.println("The goal is to win As much couples of cards as you can.");
            System.out.println("If card number is 0 it meens that it is backward.");
            System.out.println("If card number is 1 it meens that this card has already been taken by someone.");
            System.out.println("Every participant have to print nuber of row first and number of column next,to choose a card.");
            System.out.println("and after he had seen the card he have to print again");
            System.out.println("nuber of row first and number of column next,");
            System.out.println("If the 2 cards  he have choosen are equal he gets a good point and another turn.");
            System.out.println("If not, the turn is going to the next participant.");
            System.out.println("The game is over when all the board is filled with '1's");
            System.out.println("The winner is the one who gained the most cards.");
            System.out.println();
        }

        exceptionSign=true;
        do {
            try {
                System.out.print("Please enter the number of the level you want to play (1-3): ");
                Scanner scan = new Scanner(System.in);
                level = scan.nextInt();
                exceptionSign = false;
            } catch (Exception e) {
                System.out.println("This is not a number!");
            }
        }while(level>3||level<1||exceptionSign);//When all 3 conditions are false we get out of the loop.

        if (level == 1) {
            System.out.println("Geting started...");
            board=new Board(4,4);
            board.printBoard(-1,-1,-1,-1);
            playGame();
        } else if (level == 2) {
            System.out.println("Geting started...");
            board=new Board(6,6);
            board.printBoard(-1,1,1,1);
            playGame();
        } else {//level==3
            System.out.println("Geting started...");
            board = new Board(10, 10);
            board.printBoard(-1,-1,-1,-1);
            playGame();
        }
    }
    public void playGame(){
        board.fillRandomNumbers();
        int i=0;//The index of participant's name/points in the names/points array.
        boolean card2IsLegal;

        do {
            System.out.println(names[i] + " it is your turn.");
            exceptionSign=true;
            do{
                try {
                    System.out.println("Please choose your first card.");
                    System.out.println("Enter row nunmber:");
                    Scanner scan = new Scanner(System.in);
                    row1 = scan.nextInt();
                    if (row1 < 1 || row1 > board.numRows) {
                        System.out.println("This number is out of range (range=1-"+board.numRows+")");
                    }
                    exceptionSign = false;
                } catch (Exception e){
                    System.out.println("This is not a number!");
                }
            }while (row1 < 1 || row1 > board.numRows || exceptionSign);
            exceptionSign=true;
            do {
                try {
                    System.out.println("Enter column nunmber:");
                    Scanner scan = new Scanner(System.in);
                    column1 = scan.nextInt();
                    if (column1 < 1 || column1 > board.numColumns) {
                        System.out.println("This number is out of range (range=1-"+board.numColumns+")");
                    }
                    exceptionSign = false;
                }catch (Exception e){
                    System.out.println("This is not a number!");
                }
            }while(column1 < 1 || column1 > board.numColumns || exceptionSign);
            if (!board.board[row1 - 1][column1 - 1].isTaken)
            {
                //Printing board shoing the first card that was chosen
                board.printBoard(-3, column1, row1 - 1, column1 - 1);
                do {
                    exceptionSign=true;
                    do {
                        try {
                            System.out.println("Please choose your second card.");
                            System.out.println("Enter row nunmber:");
                            Scanner scan = new Scanner(System.in);
                            row2 = scan.nextInt();
                            if (row2 < 1 || row2 > board.numRows) {
                                System.out.println("This number is out of range (range=1-" + board.numRows+")");
                            }
                            exceptionSign = false;
                        } catch (Exception e) {
                            System.out.println("This is not a number!");
                        }
                    }while(row2 < 1 || row2 > board.numRows || exceptionSign);
                    exceptionSign=true;
                    do {
                        try {
                            System.out.println("Enter column nunmber:");
                            Scanner scan = new Scanner(System.in);
                            column2 = scan.nextInt();
                            if (column2 < 1 || column2 > board.numColumns) {
                                System.out.println("This number is out of range (range=1-"+board.numColumns+")");
                            }
                            exceptionSign = false;
                        }catch (Exception e){
                            System.out.println("This is not a number!");
                        }
                    }while(column2 < 1 || column2 > board.numColumns ||exceptionSign);
                    if (!board.board[row2 - 1][column2 - 1].isTaken) {
                        if (!(row1 == row2 && column1 == column2)) {//cheking if it is not the same first choosen card
                            //prints board with only two choosen cards up.
                            board.printBoard(row1 - 1, column1 - 1, row2 - 1, column2 - 1);
                            //checking if the chosen cards are equal
                            if (board.board[row1 - 1][column1 - 1].value == board.board[row2 - 1][column2 - 1].value) {
                                points[i]++;
                                board.board[row1 - 1][column1 - 1].isTaken = true;
                                board.board[row2 - 1][column2 - 1].isTaken = true;
                                if (points[i]==1)
                                   System.out.println(names[i] + " you have " + points[i] + " point.");
                                else
                                    System.out.println(names[i] + " you have " + points[i] + " points.");
                            } else {
                                System.out.println("Sorry...you don't have a couple");
                                i++;//next participants turn
                                if (i == participants)//turn go's back to the first participant.
                                    i = 0;
                                try {
                                    Thread.sleep(10000);
                                }
                                catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            }
                            card2IsLegal=true;
                        }else {
                            System.out.println("This is your first card,");
                            card2IsLegal=false;
                        }
                    } else {
                        System.out.println("This card was already taken");
                        card2IsLegal=false;
                    }

                }while(!card2IsLegal);
            }
            else
            {
                System.out.println("This card was already taken");
            }

            board.printBoard(-1,-1,-1,-1);//prints board with 1's&0's
                                                                                                   //means cards upside down.
        }while(!board.checkFinish());
        System.out.println("Game is over!");
        int highestPoints=board.checkHighestPoints(points);
        board.printWinners(names,points,highestPoints);
    }
}