import java.util.Scanner;
import java.io.*; // File, FileNotFoundException

public class USACO{
  public static void main(String[] args) {
    int[][]arr = {
    {1,2,7,8},
    {3,4,1,2},
    {4,5,9,10},
    {5,6,7,11}
  };
    // System.out.print(findMax(1, 2, arr));
    System.out.print(bronze("test.txt"));
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
        // System.out.print(toString(line));
        // System.out.println();
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
      // System.out.print(toString(arr));
      // System.out.print(toString(stomp));
      dig(arr, stomp);
      finalElevation(arr, e);
      return volume(arr);
    }
    catch(FileNotFoundException f){
      f.printStackTrace();
      return 0;
    }
  }

  public static int volume(int[][] board){
    int total = 0;
    toString(board);
    for (int x = 0; x < board.length; x++){
      for (int y = 0; y < board[x].length; y++){
        total += board[x][y];
      }
    }
    return 72*72*total;
  }

  public static void dig(int[][] board, int[][] stomp){
    for (int x = 0; x < stomp.length; x++){
      int r = stomp[x][0];
      int c = stomp[x][1];
      int e = stomp[x][2];

      int max = findMax(r, c, board);

      digH(r, c, board, stomp, e, max);
      // System.out.print(toString(board));
      // finalElevation(board, e);
      // System.out.print(toString(board));
        }
    }

  public static void finalElevation(int[][] board, int e){
    for (int x = 0; x < board.length; x++){
      for (int y = 0; y < board[x].length; y++){
        if (board[x][y] > e) board[x][y] = 0;
        else board[x][y] = e - board[x][y];
      }
    }
  }

  public static int[][] digH(int r, int c, int[][] board, int[][] stomp, int e, int max){
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

  public static int findMax(int r, int c, int[][] board){
    int max = 0;
    for (int a = r; a < r+3; a++){
      for (int b = c ;b < c+3 ;b++ ) {
        if (max < board[a - 1][b - 1]) max = board[a - 1][b - 1];
      }
    }
    return max;
  }

  public static String toString(int[][] arr){
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

  public static String toString(String[] arr){
    String output = "";
    for (String x: arr){
      output += x;
      output += ' ';
    }
    return output;
  }

  public static int silver(String filename){
    return -1; // placeholder
  }
}
