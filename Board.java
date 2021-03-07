
package ISProject1;

import java.util.Arrays;
import java.util.Comparator;

public class Board implements Comparator{
    // variables
    int[][] board = new int[3][3];
    int[] zeroPosition =  new int[2];
    
    //constructor
    public Board(int[][] b) {
        this.board = b;
        this.zeroPosition = locateZero();
    }
    
    // methods
    // getZeroPosition
    int[] getZeroPosition(){
        return this.zeroPosition;
    }
    // getBoard
    int[][] getBoard(){
        return this.board;
    }
    // locate number
    int[] locateNumber(int x){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(this.board[i][j]==x){
                    return new int[]{i,j};
                }
            }
        }
        // highly unlikly
        return null;
    }
    
    // locate zero
    private int[] locateZero(){
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(this.board[i][j]==0){
                    return new int[]{i,j};
                }
            }
        }
        // highly unlikly
        return null;
    }
    // move up
    int[][] moveUp(){
        int row = this.zeroPosition[0];
        int col = this.zeroPosition[1];
        if(row==0) return null;
        int[][] newBoard = GetCopy();
        // swap above and current zero position
        int t = newBoard[row-1][col];
        newBoard[row-1][col] = newBoard[row][col];
        newBoard[row][col] = t;
        return newBoard;
    }
    // move down
    int[][] moveDown(){
        int row = this.zeroPosition[0];
        int col = this.zeroPosition[1];
        if(row==2) return null;
        int[][] newBoard = GetCopy();
        // swap bottom and current zero position
        int t = newBoard[row+1][col];
        newBoard[row+1][col] = newBoard[row][col];
        newBoard[row][col] = t;
        return newBoard;
    }
    // move left
    int[][] moveLeft(){
        int row = this.zeroPosition[0];
        int col = this.zeroPosition[1];
        if(col==0) return null;
        int[][] newBoard = GetCopy();
        // swap bottom and current zero position
        int t = newBoard[row][col-1];
        newBoard[row][col-1] = newBoard[row][col];
        newBoard[row][col] = t;
        return newBoard;
    }
    // move right
    int[][] moveRight(){
        int row = this.zeroPosition[0];
        int col = this.zeroPosition[1];
        if(col==2) return null;
        int[][] newBoard = GetCopy();
        // swap bottom and current zero position
        int t = newBoard[row][col+1];
        newBoard[row][col+1] = newBoard[row][col];
        newBoard[row][col] = t;
        return newBoard;
    }

    @Override
    public String toString() {
        String out = "";
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                out += this.board[i][j]+" ";
            }
            out += "\n";
        }
        return out;
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
        final Board other = (Board) obj;
        for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(this.board[i][j]!=other.board[i][j]){
                        return false;
                    }
                }
            }
        return true;
    }

   
    
    @Override
    public int compare(Object arg0, Object arg1) {
        try{
            Board a = (Board) arg0;
            Board b = (Board) arg1;
            for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    if(a.board[i][j]!=b.board[i][j]){
                        return -1;
                    }
                }
            }
        }catch(Exception e){
            throw new UnsupportedOperationException("Not supported yet. One of the argument is not Board"); //To change body of generated methods, choose Tools | Templates.
        }
        return 0;
    }
    // to copy current board configuration
    private int[][] GetCopy(){
        int[][] t = new int[3][3];
        for(int i=0;i<3;i++){
                for(int j=0;j<3;j++){
                    t[i][j]=this.board[i][j];
                }
            }
        return t;
    }
    
    
}
