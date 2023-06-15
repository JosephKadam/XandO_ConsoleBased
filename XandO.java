package codingPRactice;

import java.util.*;

public class XandO {

    static ArrayList<Integer>playerPostion = new ArrayList<Integer>();
    static ArrayList<Integer>cpuPostion = new ArrayList<Integer>();
    public static void main(String[] args) {
         char [] [] board = {{' ', '|',' ', '|',' '},
                             {'-', '+','-', '+','-'},
                             {' ', '|',' ', '|',' '},
                             {'-', '+','-', '+','-'},
                             {' ', '|',' ', '|',' '}};
        printBoard(board);

        while(true)
        {

            Scanner sc = new Scanner(System.in);
            System.out.println("Enter the placement (1-9): ");
            int position = sc.nextInt();
            while (playerPostion.contains(position)||cpuPostion.contains(playerPostion))
            {
                System.out.println("Position taken try different position");
                position = sc.nextInt();
            }

            placeChar(position,board,"player");
            String result = checkResult();
            if (result.length()>0)
            {
                System.out.println(result);
                break;
            }
            Random ran = new Random();
            int cpuPos = ran.nextInt(9)+1;
            while (playerPostion.contains(cpuPos)||cpuPostion.contains(cpuPos))
            {
                cpuPos = ran.nextInt(9)+1;
            }
            placeChar(cpuPos,board,"cpu");

            printBoard(board);

             result = checkResult();
             if (result.length()>0)
             {
                 System.out.println(result);
                 break;
             }

        }

    }

    public static void printBoard(char[][]board)
    {

        for(char[] row: board)
        {
            for (char c:row)
            {
                System.out.print(c);
            }
            System.out.println();
        }
    }

    public static void placeChar(int position, char[][]board, String user)
    {
        char symbol = ' ';
        if (user.equals("player"))
        {
            symbol = 'X';
            playerPostion.add(position);
        }
        else if (user.equals("cpu"))
        {
            symbol = 'O';
            cpuPostion.add(position);
        }

        switch(position){
            case 1:
                board[0][0] = symbol;
                break;
            case 2:
                board[0][2] = symbol;
                break;
            case 3:
                board[0][4] = symbol;
                break;
            case 4:
                board[2][0] = symbol;
                break;
            case 5:
                board[2][2] = symbol;
                break;
            case 6:
                board[2][4] = symbol;
                break;
            case 7:
                board[4][0] = symbol;
                break;
            case 8:
                board[4][2] = symbol;
                break;
            case 9:
                board[4][4] = symbol;
                  break;
            default:
                break;
        }

    }

    public static String checkResult()
    {

        List topRow = Arrays.asList(1,2,3);
        List middleRow = Arrays.asList(4,5,6);
        List bottomRow = Arrays.asList(7,8,9);

        List leftCol = Arrays.asList(1,4,7);
        List midCol = Arrays.asList(2,5,8);
        List rightCol = Arrays.asList(3,6,9);

        List diagonal1 = Arrays.asList(1,5,9);
        List diagonal2 = Arrays.asList(3,5,7);

        List<List> winning = new ArrayList<List>();
        winning.add(topRow);
        winning.add(middleRow);
        winning.add(bottomRow);

        winning.add(leftCol);
        winning.add(midCol);
        winning.add(rightCol);

        winning.add(diagonal1);
        winning.add(diagonal2);

        for(List l : winning)
        {
            if (playerPostion.containsAll(l))
            {
                return "Congratulations PLAYER wins";
            } else if (cpuPostion.containsAll(l))
            {
                return "CPU wins";
            }
            else if(playerPostion.size() + cpuPostion.size() == 9)
            {
                return "Match is a DRAW";
            }
        }

        return "";
    }
}
