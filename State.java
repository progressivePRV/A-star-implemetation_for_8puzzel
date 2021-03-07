
package ISProject1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Objects;

public class State{
    
    //variables
    // parent State
    State parent = null;
    // Board
    Board board;
    // Gn => path to this state
    int gn;
    // heuristic1
    int h1;
    // heuristic2
    int h2;
    // isHeuristic1IsSelected
//    boolean isHeuristic1IsSelected;

    public State(Board board, int gn,State parent) {
        this.board = board;
        this.gn = gn;
        this.h1 = CalculateH1();
        this.h2 = CalculateH2();
        this.parent = parent;
    }
    
    
    // methods
    // Expand
    ArrayList<State> Expand(){
        ArrayList<State> states =  new ArrayList<>();
        int[][] b;
        State t_board;
        // if action moveUp is possible add
        b = this.board.moveUp();
        if(b!=null){
            t_board = new State(new Board(b),this.gn+1,this);
            if(!t_board.equals(this.parent)){
                states.add(t_board);
            }
        }
        // if action moveLeft is possible add
        b = this.board.moveLeft();
        if(b!=null){
            t_board = new State(new Board(b),this.gn+1,this);
            if(!t_board.equals(this.parent)){
                states.add(t_board);
            }
        }
        // if action moveRight is possible add
        b = this.board.moveRight();
        if(b!=null){
            t_board = new State(new Board(b),this.gn+1,this);
            if(!t_board.equals(this.parent)){
                states.add(t_board);
            }
        }
        // if action moveDown is possible add
        b = this.board.moveDown();
        if(b!=null){
            t_board = new State(new Board(b),this.gn+1,this);
            if(!t_board.equals(this.parent)){
                states.add(t_board);
            }
        }
        return states;
    }
    // calculate h1
    private int CalculateH1(){
        // number of missplaced tiles
        int misplaced = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(this.board.board[i][j]!=Main.goalBoard.board[i][j]){
                    misplaced++;
                }
            }
        }
        return misplaced;
    }
    
    // calculate h2
    private int CalculateH2(){
        // Manhattan Distance: 
        // The distance between two points measured along axes at right angles.
        int distance = 0;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(this.board.board[i][j]!=Main.goalBoard.board[i][j]){
                    int[] arg2 = Main.goalBoard.locateNumber(this.board.board[i][j]);
                   distance = CalculateDistance(new int[]{i,j},arg2);
                }
            }
        }
        return distance;
    }
    // helper for CalculateH2
    private int CalculateDistance(int[] a,int[] b){
        // a is location of number in current state
        // b is location of number in goal state
        return Math.abs(a[0]-b[0])+Math.abs(a[1]-b[1]);
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.board);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final State other = (State) obj;
        if (!Objects.equals(this.board, other.board)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "State{" + "board=\n" + board + ", gn=" + gn + ", h1=" + h1 + ", h2=" + h2 + '}';
    }
    
    
    
    
}
