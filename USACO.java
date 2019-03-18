import java.util.Scanner;
import java.io.*; // File, FileNotFoundException

public class USACO{
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
        r = Integer.parseInt(line[0]);  // Just sets all values from the first line
        c = Integer.parseInt(line[1]);
        e = Integer.parseInt(line[2]);
        n = Integer.parseInt(line[3]);
        arr = new int[r][c];

        // Set all the values from the actual board
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

  private static void finalElevation(int[][] board, int e){ // Takes the final elevation var
    for (int x = 0; x < board.length; x++){             // and subtracts board value and final elev
      for (int y = 0; y < board[x].length; y++){
        if (board[x][y] > e) board[x][y] = 0;
        else board[x][y] = e - board[x][y];
      }
    }
  }

  private static int[][] digH(int r, int c, int[][] board, int[][] stomp, int e, int max){
    int track = 0;
    while (track < e){
      for (int a = r; a < r+3; a++ ) { // Loop through a 3x3 grid
        for (int b = c; b < c+3; b++ ) {
          if (board[a - 1][b - 1] >= max) board[a - 1][b - 1]--;
        }
      }
      max = findMax(r, c, board); // Gets the largest value

      track++;
    }
    return board;
  }

  private static int findMax(int r, int c, int[][] board){
    // loops through a 3x3 board to find highest value
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
    char[][] board = null;
    try{
      // reads through file and sets appropriate values
      File file = new File(filename);
      Scanner inf = new Scanner(file);

      String[] line = inf.nextLine().split(" ");  // Loop through and set vars
      N = Integer.parseInt(line[0]);
      M = Integer.parseInt(line[1]);
      T = Integer.parseInt(line[2]);

      board = new char[N][M];

      for (int x = 0; x < N; x++){
        String[] lines = inf.nextLine().split("");
        for (int y = 0; y < M; y++){
          board[x][y] = lines[y].charAt(0);
        }
      }
      line = inf.nextLine().split(" ");

      R1 = Integer.parseInt(line[0]) - 1;
      C1 = Integer.parseInt(line[1]) - 1;
      R2 = Integer.parseInt(line[2]) - 1;
      C2 = Integer.parseInt(line[3]) - 1;
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }
    return silverH(board, T, R1, C1, R2, C2); // placeholder
  }

  private static boolean isValid(Tile[][] arr, int row, int col){ // Checks that all params are met
    return (row >= 0 && col >= 0 && row < arr.length && col < arr[row].length);
  }

  private static int silverH(char[][] board,  int T, int R1, int C1, int R2, int C2){
    int[][] moves = { // Horizontal and vertical moves
      {1, 0},
      {-1,0},
      {0, 1},
      {0,-1}
    };

    Tile[][] oboard = new Tile[board.length][board[0].length];

    for (int r = 0; r < oboard.length; r++){        // Assigning values of alternate board
      for (int c = 0; c < oboard[r].length; c++){
        if (board[r][c] == '*') oboard[r][c] = new Tile(-1,-1);
        else oboard[r][c] = new Tile(0, 0);
      }
    }
    oboard[R1][C1] = new Tile(1,0);

    for (int track = T; track > 0 ; track--) {
      for (int r = 0; r < oboard.length; r++){
        for (int c = 0; c < oboard[r].length; c++){
          if (oboard[r][c].first > 0){
            for (int i = 0; i < moves.length; i++){
              // moves in all move combos
              int row = r + moves[i][0];
              int col = c + moves[i][1];
              if (isValid(oboard, row, col) && oboard[row][col].first != -1){
                oboard[row][col].update(oboard[r][c].first);
              }
            }
          }
        }
      }
    for (int a = 0; a < oboard.length; a++){
      for (int b = 0; b < oboard[a].length; b++){
        oboard[a][b].first = oboard[a][b].second; // switch first and second values
        if (oboard[a][b].second != -1) oboard[a][b].second = 0; // if it's not a tree, set its second value to 0
      }
    }
  }
    return oboard[R2][C2].first;
  }

  public static String toString(Tile[][] board){
    String output = "";
    for (Tile[] x: board){
      for (Tile y: x){
        output += y.first;
      }
      output += '\n';
    }
    return output;
  }
}

class Tile{ // helper class to store two values for each tile on the board
  int first, second;
  public Tile(int first1, int second1){
    first = first1;
    second = second1;
  }
  public void update(int x){
    second += x;
  }
}
