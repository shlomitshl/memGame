package memo;
import java.util.LinkedList;
import java.util.Random;
public class Board  {
    public int numRows;
    public int numColumns;
    public Card[][] board;
    public Board(int rows,int columns){//constractor fills in values to numRows and numColumns according to the chosen level
        numRows=rows;
        numColumns=columns;
        board = new Card[numRows][numColumns];
        for (int i=0;i<numRows;i++)
            for (int j=0;j<numColumns;j++)
                board[i][j]=new Card();
    }
    //prints board and showes the 2 choosen cards up(row1,column1 and row2,column2) in condition row1!=-1/-2/-3
    //if row1=-1 prints all the cards face down(all board is filld with zero's and one's)
    //if row1=-2 prints all the cards face up
    //if row1=-3 prints the first card that was chosen
    public void printBoard(int row1,int column1,int row2,int column2){
        //printing the upper line of the frame + the indexes of numColumns
        if (numRows==4) {
            System.out.println("  1   2   3   4");
            System.out.println(" -----------------");
        }
        else if(numRows==6) {
            System.out.println("  1   2   3   4   5   6");
            System.out.println(" -------------------------");
        }
        else if(numRows==10) {
            System.out.println("  1   2   3   4   5   6   7   8   9   10");
            System.out.println(" ------------------------------------------");
        }
        for (int r=0;r<numRows;r++) {
            for (int c = 0; c < numColumns; c++) {
                switch (row1) {
                    case (-1): //prints board with cards face down(0's:card was'nt taken yet, 1's:card was already taken)
                        if (c == 0)
                            System.out.print("| ");
                        if (board[r][c].isTaken)
                            System.out.print(1 + "   ");
                        else
                            System.out.print(0 + "   ");
                        if (c == numColumns - 1) {  //It meens that we are in the last column
                            System.out.print("| ");
                            System.out.println(r+1);
                        }
                        break;
                    case (-2):
                        if (c == 0)
                            System.out.print("| ");
                        if (board[r][c].value > 9)
                            System.out.print(board[r][c].value + "  ");
                        else
                            System.out.print(board[r][c].value + "   ");//bigger spase is needed
                        if (c == numColumns - 1) { //It meens that we are in the last column
                            System.out.print("| ");
                            System.out.println(r+1);
                        }
                        break;
                    case (-3):
                        if (c == 0)
                            System.out.print("| ");
                        if (r==row2&&c==column2) {
                            if (board[r][c].value > 9)//The value we print is more than one digit.
                                System.out.print(board[r][c].value + "  ");
                            else//The value we print is one digit only.
                                System.out.print(board[r][c].value + "   ");//bigger spase is needed between the cards.
                        }
                        else {
                            if (board[r][c].isTaken)
                                System.out.print(1 + "   ");
                            else
                                System.out.print(0 + "   ");
                        }
                        if (c==numColumns - 1) {  //It meens that we are in the last column
                            System.out.print("| ");
                            System.out.println(r+1);
                        }
                        break;
                    default:
                        if (c == 0)
                            System.out.print("| ");
                        if (r==row1&&c==column1||r==row2&&c==column2) {
                            if (board[r][c].value > 9)
                                System.out.print(board[r][c].value + "  ");
                            else
                                System.out.print(board[r][c].value + "   ");//bigger spase is needed between the cards.
                        }
                        else {
                            if (board[r][c].isTaken)
                                System.out.print(1 + "   ");
                            else
                                System.out.print(0 + "   ");
                        }
                        if (c==numColumns - 1) {  //It meens that we are in the last column
                            System.out.print("| ");
                            System.out.println(r+1);
                        }
                }
            }
            System.out.println();
        }
        if (numRows==4)
            System.out.println(" --------------");//printing the lower line of the frame
        else if(numRows==6)
            System.out.println(" -------------------------");
        else if(numRows==10)
            System.out.println(" -----------------------------------------");

    }
    public void fillRandomNumbers() {
        LinkedList<String> linkedlist = new LinkedList<>();
        for (int i=0;i<numRows;i++) {  //making a list of all the possible pairs of (row,column)
            for (int j=0;j<numColumns;j++) {
                linkedlist.add((Integer.toString(i)).concat(",").concat(Integer.toString(j)));
            }
        }
        /*for (int i=0;i<(numRows*numColumns);i++)   //show all linkedList values
            System.out.println(linkedlist.get(i));*/
        Random rnd = new Random();
        int randRC1,randRC2,numr1,numc1,numr2,numc2;
        int val=2;
        int fixRange=0;
        String sr1,sc1,sr2,sc2;
        String [] parts1,parts2;
        do {
            randRC1 = rnd.nextInt(numRows * numColumns-fixRange);//we get a random number which will be the index of one member
                                                             //of the linked list.The value of this member is the place in the board we
                                                           //are going to fill now with the variable val.
            fixRange++;//When one member of the Linkedlist is removed we must reduce the range of the random numbers.
            parts1=linkedlist.get(randRC1).split(",");
            sr1=parts1[0];  //number of row as a String
            sc1=parts1[1];  //number of column as a String
            numr1=Integer.parseInt(sr1);  //swich row number from a string to an integer
            numc1=Integer.parseInt(sc1);  //swich column number from a string to an integer
            board[numr1][numc1].value=val;
            linkedlist.remove(randRC1);

            randRC2 = rnd.nextInt(numRows * numColumns-fixRange);//This is for the second card which holds the same value(val)
            fixRange++;
            parts2=linkedlist.get(randRC2).split(",");
            sr2=parts2[0];  //number of row as a String
            sc2=parts2[1];  //number of column as a String
            numr2=Integer.parseInt(sr2);  //swich row number from a string to an integer
            numc2=Integer.parseInt(sc2);  //swich column number from a string to an integer
            board[numr2][numc2].value=val;
            val++;
            linkedlist.remove(randRC2);
        }while (!linkedlist.isEmpty());
        printBoard(-2,1,1,1);
    }

    public Boolean checkFinish(){
        int counter=0;
        for (int r=0;r<numRows;r++) {
            for (int c = 0; c < numColumns; c++)
                if (board[r][c].isTaken)
                    counter++;
        }
        return counter == numRows * numColumns;
    }
    //this method returns the highestPoints in the array
    public int checkHighestPoints(int[] points){
         int highestPoints=0;
        for (int point : points) {
            if (point > highestPoints) {
                highestPoints = point;
            }
        }
         return highestPoints;
    }
    public void printWinners(String[] names,int[] points,int highestPoints){
        for (int j=0;j<names.length;j++) {
            if (points[j]==highestPoints) {
                System.out.println(names[j] + " is a winner!");
                System.out.println("With "+highestPoints+" points");
            }
        }
    }
}

