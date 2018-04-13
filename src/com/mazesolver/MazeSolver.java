package com.mazesolver;

import java.awt.*;
import java.io.*;
import java.util.*;


public class MazeSolver {

    private int mazeWidth;
    private int mazeHeight;
    private Point mazeStart;
    private Point mazeEnd;
    private char [][] mazeArray;

    public int getMazeWidth() {
        return mazeWidth;
    }

    public void setMazeWidth(int mazeWidth) {
        this.mazeWidth = mazeWidth;
    }

    public int getMazeHeight() {
        return mazeHeight;
    }

    public void setMazeHeight(int mazeHeight) {
        this.mazeHeight = mazeHeight;
    }

    public Point getMazeStart() {
        return mazeStart;
    }

    public void setMazeStart(Point mazeStart) {
        this.mazeStart = mazeStart;
    }

    public Point getMazeEnd() {
        return mazeEnd;
    }

    public void setMazeEnd(Point mazeEnd) {
        this.mazeEnd = mazeEnd;
    }

    public char[][] getMaze() {
        return mazeArray;
    }

    public void setMaze(char[][] mazeArray) {
        this.mazeArray = mazeArray;
    }

    //Constructor
    public MazeSolver(int mazeWidth, int mazeHeight, Point mazeStart, Point mazeEnd, char[][] mazeArray) {
        this.mazeWidth = mazeWidth;
        this.mazeHeight = mazeHeight;
        this.mazeStart = mazeStart;
        this.mazeEnd = mazeEnd;
        this.mazeArray = mazeArray;

    }

    //Load Maze File
    public static MazeSolver readMaze() throws FileNotFoundException{
        Scanner scanner = new Scanner(new File("maze.txt"));
        int mazeWidth = scanner.nextInt();
        int mazeHeight = scanner.nextInt();
        Point mazeStart = new Point(scanner.nextInt(),scanner.nextInt());
        Point mazeEnd = new Point(scanner.nextInt(),scanner.nextInt());
        scanner.nextLine(); //consume new line after nextInt
        System.out.println("" + mazeWidth+  mazeHeight + mazeStart.toString()+ mazeEnd.toString());//PRINT TEST

        char [][] mazeArray = new char[mazeHeight][mazeWidth];
        for(int row = 0; row<mazeHeight; row++){
           mazeArray[row]= scanner.nextLine().replaceAll("\\s+","").toCharArray();
        }

        MazeSolver maze = new MazeSolver(mazeWidth, mazeHeight, mazeStart, mazeEnd, mazeArray);
        return maze;

    }



    public static void main(String[] args) throws FileNotFoundException {
        MazeSolver maze = readMaze();

        //PRINT TEST!!!!!******!!!!
        for(int row = 0; row<maze.mazeHeight; row++){
            for(int col = 0; col<maze.mazeWidth; col++){
                System.out.print(maze.mazeArray[row][col]);
            }
            System.out.println("");
        }



    }


}
