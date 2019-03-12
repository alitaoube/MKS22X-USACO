import java.util.Scanner;
import java.io.*; // File, FileNotFoundException

public class USACO{
  public static void main(String[] args) {
    System.out.print(silver("test1.txt"));
  }

  public static int bronze(String filename){
    int[][] arr = null;
    int[][] stomp = null;
    int r = 0;
    int c = 0;
    int e = 0;
    int n = 0;

    try{
      File file = new File(filename);
      Scanner inf = new Scanner(file);


      while (inf.hasNextLine()) {
        String[] line = inf.nextLine().split(" ");
        r = Integer.parseInt(line[0]);
        c = Integer.parseInt(line[1]);
        e = Integer.parseInt(line[2]);
        n = Integer.parseInt(line[3]);
        arr = new int[r][c];


        for (int x = 0; x < r; x++){
          String[] lines = inf.nextLine().split(" ");

          for (int y = 0; y < c; y++){
            arr[x][y] = Integer.parseInt(lines[y]);
          }
        }

        stomp = new int[n][3];
        for (int i = 0; i < n; i++){
          line = inf.nextLine().split(" ");
          for (int a = 0; a < 3; a++) {
            stomp[i][a] = Integer.parseInt(line[a]);
          }
        }
      }
      dig(arr, stomp);
      finalElevation(arr, e);
      return volume(arr);
    }
    catch(FileNotFoundException f){
      f.printStackTrace();
      return 0;
    }
  }

  private static int volume(int[][] board){
    int total = 0;
    toString(board);
    for (int x = 0; x < board.length; x++){
      for (int y = 0; y < board[x].length; y++){
        total += board[x][y];
      }
    }
    return 72*72*total;
  }

  private static void dig(int[][] board, int[][] stomp){
    for (int x = 0; x < stomp.length; x++){
      int r = stomp[x][0];
      int c = stomp[x][1];
      int e = stomp[x][2];

      int max = findMax(r, c, board);

      digH(r, c, board, stomp, e, max);
        }
    }

  private static void finalElevation(int[][] board, int e){
    for (int x = 0; x < board.length; x++){
      for (int y = 0; y < board[x].length; y++){
        if (board[x][y] > e) board[x][y] = 0;
        else board[x][y] = e - board[x][y];
      }
    }
  }

  private static int[][] digH(int r, int c, int[][] board, int[][] stomp, int e, int max){
    int track = 0;
    while (track < e){
      for (int a = r; a < r+3; a++ ) {
        for (int b = c; b < c+3; b++ ) {
          if (board[a - 1][b - 1] >= max) board[a - 1][b - 1]--;
        }
      }
      max = findMax(r, c, board);

      track++;
    }
    return board;
  }

  private static int findMax(int r, int c, int[][] board){
    int max = 0;
    for (int a = r; a < r+3; a++){
      for (int b = c ;b < c+3 ;b++ ) {
        if (max < board[a - 1][b - 1]) max = board[a - 1][b - 1];
      }
    }
    return max;
  }

  private static String toString(int[][] arr){
    String output = "";
    for (int[] x: arr){
      for (int y: x){
        output += y;
        output += ' ';
      }
      output += '\n';
    }
    return output;
  }

  private static String toString(String[] arr){
    String output = "";
    for (String x: arr){
      output += x;
    }
    return output;
  }

  private static String toString(String[][] arr){
    String output = "";
    for (String[] x: arr){
      for (String y: x){
        output += y;
        output += ' ';
      }
      output += '\n';
    }
    return output;
  }

  public static int silver(String filename){
    int N = 0 , M = 0, T = 0, R1 = 0, C1 = 0, R2 = 0, C2 = 0;
    String[][] board = null;
    try{
      File file = new File(filename);
      Scanner inf = new Scanner(file);

      String[] line = inf.nextLine().split(" ");
      N = Integer.parseInt(line[0]);
      M = Integer.parseInt(line[1]);
      T = Integer.parseInt(line[2]);

      board = new String[N][M];

      // System.out.print(N);
      // System.out.println();
      // System.out.print(M);
      // System.out.println();
      // System.out.print(toString(board));


      for (int x = 0; x < N; x++){
        String[] lines = inf.nextLine().split("");
        // System.out.print(toString(lines));
        for (int y = 0; y < M; y++){
          board[x][y] = lines[y];
        }
      }
      line = inf.nextLine().split(" ");

      R1 = Integer.parseInt(line[0]);
      C1 = Integer.parseInt(line[1]);
      R2 = Integer.parseInt(line[2]);
      C2 = Integer.parseInt(line[3]);
      System.out.print(toString(board));
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }
    // System.out.print(board);
    return -1; // placeholder
  }

  private boolean isValid(int row, int col){ // Checks that all params are met
    return (row >= 0 && col >= 0 && row < board.length && col < board[row].length);
  } 

  private static int silverH(String[] board,  int T, int R1, int C1, int R2, C2){
    int[][] moves = {
      {1, 0},
      {-1,0},
      {0, 1},
      {0,-1}
    };
    int[][] oboard = new int[board.length][board[0].length];
    for (int r = 0; x < board.length; x++){
      for (int c = 0; c < board[r].length; c++){
        if (map[r][c] == '*' ) oboard[r][c] = -1;
        else oboard[r][c] = 0;
      }
    }
    oboard[r][c] = 1;

    for (int x = time; x > 0 ; x++) {
      for (int r = 0; x < board.length; x++){
        for (int c = 0; c < board[r].length; c++){
          if (oboard[r][c] > 0){
            for (int i = 0; i < moves.length; i++){
              int row = r + moves[i][0];
              int col = c + moves[i][1];
            }
          }
        }
      }
    }
  }
}
