import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main {

    static Board iniBoard;// initial board
    static Board goalBoard; // Goal board
    static State startingState;
    static State goalState;
    static boolean debug = true;
    static Scanner sc;

    public static void main(String[] args) {
        // TODO code application logic here
        // small description how program will read the input
        System.out.println("program will take initaial board in a single line.\n"
                + "Every cell element should be provided comma sepearted.\n"
                + "Make sure you don't have extra space in between."
                + "\nfor example\n"
                + "input=>1,2,3,4,5,6,7,8,0\n"
                + "Generated Board=>\n"
                + "1 2 3\n"
                + "4 5 6\n"
                + "7 8 0\n"
                + "\nNote: 0 is considered as empty place in the puzzle\n");
        sc = new Scanner(System.in);
        
        
        ///////////////////////////////////////////  getting initial board
        System.out.print("Please Provide the initial Board=>");
        String initialBoard = sc.next().trim();
        String[] board = initialBoard.split(",");
        if (board.length != 9) {
            System.out.println("board.length=>" + board.length + ", There should be excatly nine digits");
            System.out.println("Improper input!\nRun program again");
            return;
        }
        int[][] b = new int[3][3];
        try {
            b[0][0] = Integer.valueOf(board[0]);
            b[0][1] = Integer.valueOf(board[1]);
            b[0][2] = Integer.valueOf(board[2]);
            b[1][0] = Integer.valueOf(board[3]);
            b[1][1] = Integer.valueOf(board[4]);
            b[1][2] = Integer.valueOf(board[5]);
            b[2][0] = Integer.valueOf(board[6]);
            b[2][1] = Integer.valueOf(board[7]);
            b[2][2] = Integer.valueOf(board[8]);
            CheckInput(b);
        } catch (NumberFormatException e) {
            System.out.println("please provide integer between [0-8] inclusive!\n Improper input!\n run program again");
            return;
        }

        iniBoard = new Board(b);
        System.out.println("new board\n" + iniBoard);
        System.out.println("location of zero (empty place in the puzzle)=>" + Arrays.toString(iniBoard.getZeroPosition()));

        /////////////////////////////////////////////  getting goal board
        System.out.print("\nPlease Provide the Goal Board=>");
        String goal_Board = sc.next().trim();
        //1,2,3,4,5,6,7,8,0,9
        board = goal_Board.split(",");
        if (board.length != 9) {
            System.out.println("goal board.length=>" + board.length);
            System.out.println("Improper input!\nRun program again");
            return;
        }
        b = new int[3][3];
        try {
            b[0][0] = Integer.valueOf(board[0]);
            b[0][1] = Integer.valueOf(board[1]);
            b[0][2] = Integer.valueOf(board[2]);
            b[1][0] = Integer.valueOf(board[3]);
            b[1][1] = Integer.valueOf(board[4]);
            b[1][2] = Integer.valueOf(board[5]);
            b[2][0] = Integer.valueOf(board[6]);
            b[2][1] = Integer.valueOf(board[7]);
            b[2][2] = Integer.valueOf(board[8]);
            CheckInput(b);
        } catch (NumberFormatException e) {
            System.out.println("please provide integer!\n Improper input!\n run program again");
            return;
        }
        goalBoard = new Board(b);
        System.out.println("new board\n" + goalBoard);
        System.out.println("location of zero (empty place in the puzzle)=>" + Arrays.toString(goalBoard.getZeroPosition()));

        //////////////////////////////////////////////// initializing both the states
        startingState = new State(iniBoard, 0, null);
        System.out.println("Starting State=>" + startingState);
        goalState = new State(goalBoard, 0, null);
        System.out.println("\nGoal State" + goalState);
        System.out.println("\nchecking if it is solvable.....");
        if(checkIfSolvable()){
            System.out.println("solvable");
        }else{
            System.out.println("NO solution found [not solvable] "
                    + "\n refer 'https://www.geeksforgeeks.org/check-instance-8-puzzle-solvable/' for further information");
            return;
        }
        
        //comparator 1: will be used for comparing Priority with respect to Heuristic 1
        Comparator<State> c1 = new Comparator() {
            @Override
            public int compare(Object arg0, Object arg1) {
                State a = (State) arg0;
                State b = (State) arg1;
                // use of huesersitic h1
                return (a.gn + a.h1) - (b.gn + b.h1);
            }
        };

        //comparator 2: will be used for comparing Priority with respect to Heuristic 2
        Comparator<State> c2 = new Comparator() {
            @Override
            public int compare(Object arg0, Object arg1) {
                State a = (State) arg0;
                State b = (State) arg1;
                // use of huerestic h2
                return (a.gn + a.h2) - (b.gn + b.h2);
            }
        };

        
        System.out.print("Please select the Heuristic function"
                + "\n1) Heuristic 1 (misplaced tiles hueristic)"
                + "\n2) Heuristic 2 (Manhattan Distance) [default]"
                + "\n=>");
        String chooseHeuristic = sc.next();
        if (chooseHeuristic.equals("1")) {
            System.out.println("Selected=> Heuristic 1 (misplaced tiles hueristic)");
            //----------------------------------------------------------------------- implementing A* with Heristic 1  start
            System.out.println("\n\nOutput of A* with Heuristic 1 (misplaced tiles hueristic)");
            PriorityQueue<State> fringe1 = new PriorityQueue<>(c1);
            boolean result = AStar(startingState, goalState, fringe1);
            if (result) {
                System.out.println("result pass");
            } else {
                System.out.println("result failure");
            }
            //----------------------------------------------------------------------- implementing A* with Heristic 1  end
        } else {
            System.out.println("Selected=> Heuristic 2 (Manhattan Distance) [default]");
            //----------------------------------------------------------------------- implementing A* with Heristic 2  start
            System.out.println("\n\nOutput of A* with Heuristic 2 (Manhattan Distance)");
            PriorityQueue<State> fringe2 = new PriorityQueue<>(c2);
            startingState = new State(iniBoard, 0, null);
            goalState = new State(goalBoard, 0, null);
            boolean result = AStar(startingState, goalState, fringe2);
            if (result) {
                System.out.println("result pass");
            } else {
                System.out.println("result failure");
            }
            //------------------------------------------------------------------------ implementing A* with Heristic 2  end
        }

    }
    //7,2,4,5,0,6,8,3,1 solvable
    //1,8,2,0,4,3,7,6,5
    //8,1,2,0,4,3,7,6,5
    //1,2,3,7,4,5,6,8,0
    //8,1,2,0,4,3,7,6,5 not solable
    
    //1,2,3,4,5,6,7,8,0
    
    public static boolean checkIfSolvable() {
        ArrayList<Integer> a = startingState.board.toArrayList();
        ArrayList<Integer> b = goalState.board.toArrayList();
        int inversions = 0;
        for (int i = 0; i < a.size(); i++) {
            if(a.get(i)!=0 ){  
                int j = i;
                while(j<b.size()){
                    if(a.get(j)!=0 && b.indexOf(a.get(i)) > b.indexOf(a.get(j)) ){
                        inversions++;
                    }
                    j++;
                }
            }
        }
        System.out.println("inversions found=>"+inversions);
        return inversions%2==0;
    }

    public static boolean AStar(State startingSate, State goalSate, PriorityQueue<State> fringe) {
        // it is a tree search algorithm
        int nodeGenerated = 0;
        int nodeExpanded = 0;
        // adding startingSate in fringe (priority Queue)
        fringe.add(startingSate);
        while (!fringe.isEmpty()) {
            // remove front
            State s = fringe.poll();
            nodeExpanded++;
            // check if selected is Goal state.
            if (s.equals(goalSate)) {
                System.out.println("nodes Generated=>" + nodeGenerated);
                System.out.println("nodes Expanded=>" + nodeExpanded);
                System.out.println("Path cost =>" + s.gn);
                generatePath(s);
                return true;
            }
            // expand the node and store newly generated node in fringe.
            for (State st : s.Expand()) {
                if (!fringe.contains(st)) {
                    fringe.add(st);
                    nodeGenerated++;
                }
            }
        }
        return false;
    }

    public static void generatePath(State goal) {
        // got to child node[goal state] to root node[initial state]
        // save it in array to display path from initial state to goal state
        ArrayList<State> path =  new ArrayList<>();
        path.add(goal);
        while (!goal.equals(startingState)) {
//            System.out.println(goal);
            goal = goal.parent;
            path.add(0,goal);
        }
        // print the path
        for(State s : path){
            System.out.println(s.board);
        }
    }
    
    public static void CheckInput(int[][] b){
        ArrayList<Integer> check = new ArrayList<>();
        for(int i=0;i<9;i++){
            check.add(i);
        }
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(!check.contains(b[i][j])){
                    throw new NumberFormatException();
                }
            }
        }
    }

}