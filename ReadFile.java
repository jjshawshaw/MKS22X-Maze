import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ReadFile{
  public static void main(String[]args){
    try {
      File f = new File("Maze1.txt");

      Scanner infLen = new Scanner(f);
      int rowLen = 0;
      int colLen = 0;
      while (infLen.hasNextLine()){
        rowLen += 1;
        colLen = infLen.nextLine().length();
      }
      char[][] grid = new char[rowLen][colLen];
      Scanner inf = new Scanner(f);
      while (inf.hasNextLine()){

      }
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
