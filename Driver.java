import java.io.*;
public class Driver{
    public static void main(String[]args){
      String filename = "Maze1.txt";
      try{
        Maze f;
        f = new Maze(filename);//true animates the maz
        System.out.println(f);
        f.setAnimate(true);
        System.out.println(f.solve());
        System.out.println(f);
      }catch(FileNotFoundException e){
        System.out.println("Invalid filename: "+filename);
      }
    }
}
