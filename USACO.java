import java.util.Scanner;
import java.io.*; // File, FileNotFoundException

public class USACO{
  public static void main(String[] args) {
    bronze("test.txt");
  }
  public static int bronze(String filename){
    System.out.print(getArr("test.txt"));
    return 0;
  }

  public static int[][] getArr(String filename){
    int[][] arr;
    try{
      File file = new File(filename);
      Scanner inf = new Scanner(file);

      int x = 0;
      int y = 0;
      while (inf.hasNextLine()) {
        y = inf.nextLine().length();
        x++;

      }
      arr = new int[x][y];

      inf = new Scanner(file);
      int z = 0;
      while (inf.hasNextLine()){
        String line = inf.nextLine();
        for (int i = 0; i < line.length(); i++){
          arr[z][i] = line[i];
        }
        z++;
      }
      return arr;
    }
    catch(FileNotFoundException e){
      e.printStackTrace();
    }
    throw new Error();
  }

  public static int silver(String filename){
    return -1; // placeholder
  }
}
