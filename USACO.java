import java.util.Scanner;
import java.io.*; // File, FileNotFoundException

public class USACO{
  public static void main(String[] args) {
    bronze("test.txt");
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
        System.out.print(toString(line));
        System.out.println();
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
      System.out.print(toString(arr));
      System.out.print(toString(stomp));
    }
    catch(FileNotFoundException f){
      f.printStackTrace();
    }
    return 0;
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
