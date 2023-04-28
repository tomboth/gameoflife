import java.util.Arrays;

public class Main {

    public static void main(String[] args) {
        int [][] myBoard = {
                {0,1,0,0,1},
                {0,0,0,1,1},
                {1,1,1,1,1},
                {1,0,0,0,1},
                {1,0,0,1,1}};

        printArray(myBoard);
        gameOfLife(myBoard);

    }

    public static void printArray(int [][] board){
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                encode(board, i, j);
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                udpate(board, i, j);
            }
        }
        System.out.println();
        printArray(board);
    }

    private static void encode(int[][] board, int i, int j) {
        int aliveCount = 0;
        for (int rowOffset = -1; rowOffset <= 1; rowOffset++) {
            for (int columnOffset = -1; columnOffset <= 1; columnOffset++) {
                int neighbourRow = i + rowOffset;
                int neighbourColumn = j + columnOffset;

                if (neighbourRow >= 0 && neighbourRow < board.length
                        && neighbourColumn >= 0 && neighbourColumn < board[0].length) {
                    int neighbourValue = board[neighbourRow][neighbourColumn];
                    if (rowOffset < 0 || (rowOffset == 0 && columnOffset < 0)) {
                        neighbourValue = neighbourValue / 10;
                    }
                    if (neighbourValue == 1 && !(rowOffset == 0 && columnOffset == 0)) {
                        aliveCount++;
                    }
                }
            }
        }
        board[i][j] = board[i][j] * 10 + aliveCount;
    }

    private static void udpate(int[][] board, int i, int j) {
        boolean isAlive = board[i][j] / 10 == 1;
        int aliveNeighbours = board[i][j] % 10;
        if (isAlive && (aliveNeighbours <2 || aliveNeighbours >3)){
            board[i][j] = 0;
        } else if(isAlive){
            board[i][j] = 1;
        } else if (!isAlive && aliveNeighbours == 3){
            board[i][j] = 1;
        } else {
            board[i][j] = board[i][j] /10;
        }
    }


}
