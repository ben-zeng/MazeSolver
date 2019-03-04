import javax.swing.JFileChooser;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class MazeLogic {            // MazeLogic class declaration

    private char[][] maze;          // Variable declarations
    private int startX;
    private int startY;
    private int endX;
    private int endY;
    private int mazeWidth;
    private int mazeHeight;

    /**
     * Main method
     * creates new instance of maze, then calls mazeStart method
     */
    public static void main(String[] args) {
        MazeLogic maze = new MazeLogic();
        maze.mazeStart();
    }

    /**
     * mazeStart method calls mazeIn to scan in maze text file to maze array
     * it then calls formatMaze to format array to required format
     * mazeSolve is then called passing startY and startX to solve maze
     * if maze is solved, the array is formatted once more to remove breadcrumbs and repopulate start and end points
     * if cannot be solved then cannot be solved message is printed to console
     */
    private void mazeStart() {
        mazeIn();
        formatMaze();
        if (mazeSolve(startY, startX)) {
            formatMaze();
            printMaze();
            System.out.println("Maze solved, terminating program");
        } else {
            System.out.println("Maze cannot be solved, terminating program");
        }
    }

    /**
     * Method to scan in a text file
     * and populate maze variables
     * then creates a maze array based on mazeWidth and mazeHeight
     */
    private void mazeIn() {
        String mazeFilePath = fileOpen();                                   // Call fileOpen method and stores mazeFilePath string
        File mazeFileIn = new File(mazeFilePath);                           // Creates new file object from mazeFilePath
        try (Scanner mazeFileScan = new Scanner(mazeFileIn)) {              // Scanner reads text from file object
            while (mazeFileScan.hasNext()) {
                mazeWidth = mazeFileScan.nextInt();                         // First int stored as mazeWidth
                mazeHeight = mazeFileScan.nextInt();                        // Second int stored as mazeHeight
                startX = mazeFileScan.nextInt();                            // Third int stored as startX
                startY = mazeFileScan.nextInt();                            // Forth int stored as startY
                endX = mazeFileScan.nextInt();                              // Fifth int stored as endX
                endY = mazeFileScan.nextInt();                              // Sixth int stored as endY
                maze = new char[mazeHeight][mazeWidth];                     // Creates Maze with size mazeHeight and mazeWidth
                for (int i = 0; i < mazeHeight; i++)
                    for (int j = 0; j < mazeWidth; j++)
                        maze[i][j] = (char) (mazeFileScan.nextInt() + '0');  // Reads the remaining text file and converts int to char
            }
        } catch (FileNotFoundException e) {                                 // Prints error if file not found
            e.printStackTrace();                                            // This is not used as fileOpen already takes care of this, but required to compile
        }
    }


    /**
     * Method to print the maze array into console
     */
    private void printMaze() {
        for (int i = 0; i < maze.length; i++) {
            for (int j = 0; j < maze[i].length; j++)
                System.out.print(maze[i][j] + " ");
            System.out.println();
        }
    }


    /**
     * Method to select file using an Open File dialogue box
     * and returns absolute file path
     * program terminates if dialog box is closed
     */
    private String fileOpen() {
        JFileChooser jfc = new JFileChooser();                                  // Create new JFileChooser object called jfc
        int jfcState = jfc.showOpenDialog(null);                         // Open "Open File" dialog, returns state of dialog

        if (jfcState == JFileChooser.APPROVE_OPTION) {                          // If jfcState is APPROVE_OPTION
            File selectedFile = jfc.getSelectedFile();                          // Create file object called selectedFile from selected file
            return selectedFile.getAbsolutePath();
        } else {                                                                // Otherwise
            System.out.println("Open file cancelled, terminating program");     // Print cancellation message in console
            System.exit(0);                                              // Terminate program
            return null;
        }
    }


    /**
     * Method to format the maze array to required output format
     */
    private void formatMaze() {
        maze[startY][startX] = 'S';                         // Mark S for start position
        maze[endY][endX] = 'E';                             // Mark E for end position
        for (int i = 0; i < mazeHeight; i++) {
            for (int j = 0; j < mazeWidth; j++) {
                if (maze[i][j] == '1') {                    // Replaces 1 with # to signify wall
                    maze[i][j] = '#';
                }
                if (maze[i][j] == '0') {                    // Replaces 0 with space to signify path
                    maze[i][j] = ' ';
                }
                if (maze[i][j] == '+') {                    // Clears breadcrumbs
                    maze[i][j] = ' ';
                }
            }
        }
    }


    /**
     * Method to solve the maze recursively
     */

    private boolean mazeSolve(int row, int col) {
        if (maze[row][col] == 'E')                                          // Is position the exit
            return true;                                                    // Return maze solved flag
        if (maze[row][col] != 'S' && maze[row][col] != ' ') {               // Is position valid
            return false;
        }
        /**
         * modulo used when going North, East, South or West to allow wrapping movement
         * mazeHeight or mazeWidth is always added to each iteration, then the modulus is taken
         * this prevents an out of range element within the maze array from being selected
         */
        maze[row][col] = '+';                                               // Leave breadcrumb on position
        if (mazeSolve((row + 1 + mazeHeight) % mazeHeight, col)) {     // Go South
            maze[row][col] = 'X';
            return true;
        }
        if (mazeSolve((row - 1 + mazeHeight) % mazeHeight, col)) {     // Go North
            maze[row][col] = 'X';
            return true;
        }
        if (mazeSolve(row, (col + 1 + mazeWidth) % mazeWidth)) {        // Go East
            maze[row][col] = 'X';
            return true;
        }
        if (mazeSolve(row, (col - 1 + mazeWidth) % mazeWidth)) {        // Go West
            maze[row][col] = 'X';
            return true;
        }
        return false;                                                        // Return maze not solvable flag
        }
    }
