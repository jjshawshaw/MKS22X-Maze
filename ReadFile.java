import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class ReadFile{
  public static void main(String[]args){
    try {
      File f = new File("Maze1.txt");
      Scanner inf = new Scanner(f);
      while (inf.hasNextLine()){
        String line = inf.nextLine();
        System.out.println(line);
      }
    }
    catch (FileNotFoundException e){
      e.printStackTrace();
    }
  }
}
