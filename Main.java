package ISProject1;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static Board iniBoard;// initial board
    static Board goalBoard; // Goal board
    
    public static void main(String[] args) {
        // TODO code application logic here
        // lets get initial board
        System.out.println("program will take initaial board in a single line.\n"
                + "Every cell element should be provided comma sepearted.\n"
                + "Make sure you don't have extra space in between."
                + "for example\n"
                + "input=>1,2,3,4,5,6,7,8,0\n"
                + "output=>\n"
                + "1 2 3\n"
                + "4 5 6\n"
                + "7 8 0\n");
        Scanner sc = new Scanner(System.in);
        ///////////////////  getting initial board
        System.out.print("Provide initial Board=>");
//        String initialBoard = sc.next().trim();
        String initialBoard = "7,2,4,5,0,6,8,3,1";
        String[] board = initialBoard.split(",");
        System.out.println("board.length=>"+board.length);
        if(board.length!=9){
            System.out.println("Improper input!\nRun program again");
            return;
        }
        int[][] b =  new int[3][3];
        try{
            b[0][0] = Integer.valueOf(board[0]);
            b[0][1] = Integer.valueOf(board[1]);
            b[0][2] = Integer.valueOf(board[2]);
            b[1][0] = Integer.valueOf(board[3]);
            b[1][1] = Integer.valueOf(board[4]);
            b[1][2] = Integer.valueOf(board[5]);
            b[2][0] = Integer.valueOf(board[6]);
            b[2][1] = Integer.valueOf(board[7]);
            b[2][2] = Integer.valueOf(board[8]);
        }catch(NumberFormatException e){
            System.out.println("please provide integer!\n Improper input!\n run program again");
        }
        
        iniBoard =  new Board(b);
        System.out.println("new board\n"+iniBoard);
        System.out.println("zero location=>"+Arrays.toString(iniBoard.getZeroPosition()));
        
        //////////////////////  getting goal board
        System.out.print("\nProvide Goal Board=>");
//        String goal_Board = sc.next().trim();
        String goal_Board = "1,2,3,4,5,6,7,8,0";
        //1,2,3,4,5,6,7,8,0,9
        board = goal_Board.split(",");
        System.out.println("goal board.length=>"+board.length);
        if(board.length!=9){
            System.out.println("Improper input!\nRun program again");
            return;
        }
        b =  new int[3][3];
        try{
            b[0][0] = Integer.valueOf(board[0]);
            b[0][1] = Integer.valueOf(board[1]);
            b[0][2] = Integer.valueOf(board[2]);
            b[1][0] = Integer.valueOf(board[3]);
            b[1][1] = Integer.valueOf(board[4]);
            b[1][2] = Integer.valueOf(board[5]);
            b[2][0] = Integer.valueOf(board[6]);
            b[2][1] = Integer.valueOf(board[7]);
            b[2][2] = Integer.valueOf(board[8]);
        }catch(NumberFormatException e){
            System.out.println("please provide integer!\n Improper input!\n run program again");
        }
        goalBoard =  new Board(b);
        System.out.println("new board\n"+goalBoard);
        System.out.println("zero location=>"+Arrays.toString(goalBoard.getZeroPosition()));
        
        //////////// inputs
        // inital board =>7,2,4,5,0,6,8,3,1
        // goal board =>1,2,3,4,5,6,7,8,0
        ////////////////// checking for state
        
        
        //comparator 1
        Comparator<State> c1 =  new Comparator(){
            @Override
            public int compare(Object arg0, Object arg1) {
            State a = (State) arg0;
            State b = (State) arg1;
            // use of huesersitic h1
            return (a.gn+a.h1)-(b.gn+b.h1);
            }
        };
        
        //comparator 2
        Comparator<State> c2 =  new Comparator(){
            @Override
            public int compare(Object arg0, Object arg1) {
            State a = (State) arg0;
            State b = (State) arg1;
            // use of huerestic h2
            return (a.gn+a.h2)-(b.gn+b.h2);
            }
        };
        
        PriorityQueue<State> Q =  new PriorityQueue<>(c1);
        State firstState = new State(iniBoard,0,null);
        System.out.println("-------------------------------\nfirstState=>"+firstState);
        
        for(State st : firstState.Expand()){
            System.out.println("Node at level 1");
            System.out.println(st);
            Q.add(st);
        }
        
        State first_pop = Q.poll();
        System.out.println("first_pop=>"+first_pop);
        
        System.out.println("trying second level------------------");
        for(State st : first_pop.Expand()){
            System.out.println("Node at level 2");
            System.out.println(st);
            Q.add(st);
        }
    }
    
}
