
import java.util.*;
import java.io.*;
public class Maze{


    private char[][]maze;
    private boolean animate;//false by default
    private boolean solved;

    /*Constructor loads a maze text file, and sets animate to false by default.

      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)

      'S' - the location of the start(exactly 1 per file)

      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!


      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:

         throw a FileNotFoundException or IllegalStateException

    */

    public Maze(String filename) throws FileNotFoundException{
        //COMPLETE CONSTRUCTOR
        ReadFile(filename);
        animate = false;
      //  System.out.println(this);
    }

    private void ReadFile(String filename) throws FileNotFoundException{
      File f = new File(filename);

      Scanner infLen = new Scanner(f);
      int rowLen = 0;
      int colLen = 0;
      while (infLen.hasNextLine()){
        rowLen += 1;
        colLen = infLen.nextLine().length();
      }
      maze = new char[rowLen][colLen];
      Scanner inf = new Scanner(f);
      int row = 0;
      while (inf.hasNextLine()){
        String line = inf.nextLine();
        for (int i = 0; i < line.length(); i++){
          maze[row][i] = line.charAt(i);
        }
        row++;
      }
      int sn = 0;
      int en = 0;
      for (int y = 0; y < maze.length; y++){
        for (int x = 0; x < maze[0].length; x++){
          if (maze[y][x] == 'S') sn++;
          if (maze[y][x] == 'E') en++;
      }
    }
    if (sn != 1 || en != 1) throw new IllegalStateException();
    }

    public String toString(){
      String out = "";
      for (char[] y : maze){
        for (char i : y){
          out += i;
        }
        out += "\n";
      }
      return out;
    }

    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }


    public void setAnimate(boolean b){

        animate = b;

    }

    public void clearTerminal(){

        //erase terminal, go to top left of screen.

        System.out.println("\033[2J\033[1;1H");

    }



    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.
    */
    public int solve(){

            //find the location of the S.
            int sX = 0;
            int sY = 0;
            for (int y = 0; y < maze.length; y++){
              for (int x = 0; x < maze[0].length; x++){
                if (maze[y][x] == 'S'){
                  sY = y;
                  sX = x;
              }
            }
          }

            //erase the S

              maze[sY][sX] = '@';

            //and start solving at the location of the s.

            //return solve(???,???);
            return solve(sY, sX, 0);
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.


      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

        All visited spots that are part of the solution are changed to '@'
    */

    private boolean step(int r, int c){
      if (r < 0 || c < 0 || r >= maze.length || c >= maze[0].length) return false;
      if (maze[r][c] == ' ') {
        maze[r][c] = '@';
        return true;
      }
      if (maze[r][c] == 'E') return true;
      return false;
    }

    private int solve(int row, int col, int num){ //you can add more parameters since this is private


        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(200);
        }

        //COMPLETE SOLVE


        if (maze[row][col] == 'E') return num;
        else{
          int[][] moves = new int[][]{
            {0, 1},
            {0, -1},
            {1, 0},
            {-1, 0}
          };
          for (int[] i : moves){
            if (step(row + i[0], col + i[1])){
              int n = solve(row + i[0], col + i[1], num + 1);
              if (n != -1) return n;
            }
          }
          maze[row][col] = '.';
          num -= 1;
        }
        return -1; //so it compiles
    }


}
