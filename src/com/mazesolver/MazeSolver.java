package com.mazesolver;

import java.awt.*;
import java.io.*;
import java.util.*;

public class MazeSolver {

    private int mazeWidth;
    private int mazeHeight;
    private static Point mazeStart;
    private static Point mazeEnd;
    private char [][] mazeMap;

    //Constructor
    public MazeSolver(int mazeWidth, int mazeHeight, Point mazeStart, Point mazeEnd, char[][] mazeMap) {
        this.mazeWidth = mazeWidth;
        this.mazeHeight = mazeHeight;
        this.mazeStart = mazeStart;
        this.mazeEnd = mazeEnd;
        this.mazeMap = mazeMap;

    }

    //METHODS

    //Read Maze File
    private static MazeSolver readMaze(File mazeFile){
        try {
        Scanner scanner = new Scanner(mazeFile);

        int mazeWidth = scanner.nextInt();
        int mazeHeight = scanner.nextInt();
        Point mazeStart = new Point(scanner.nextInt(),scanner.nextInt());
        Point mazeEnd = new Point(scanner.nextInt(),scanner.nextInt());
        scanner.nextLine(); //consume new line after nextInt
        System.out.println("" + mazeWidth+  mazeHeight + mazeStart.toString()+ mazeEnd.toString());//PRINT TEST************************

        char [][] mazeArray = new char[mazeHeight][mazeWidth];
        for(int row = 0; row<mazeHeight; row++){
           mazeArray[row]= scanner.nextLine().replaceAll("\\s+","").toCharArray();
        }
            MazeSolver ms = new MazeSolver(mazeWidth, mazeHeight, mazeStart, mazeEnd, mazeArray);
            return ms;

        } catch (java.io.FileNotFoundException e){
            System.out.println("Maze File not Found");
            System.exit(1);
            return null;
        }

    }

    //Array of possible directions - N, E, S, W
    private static final int[][] directions = {{1,0},{0,1},{-1,0},{0,-1}};

    //Reformat maze to make it visually friendly
    private void reformatMaze (){

        mazeMap[mazeStart.y][mazeStart.x]= 'S';
        mazeMap[mazeEnd.y][mazeEnd.x]= 'E';

        for(int row = 0; row<mazeHeight;row++){
            for(int col=0; col<mazeWidth; col++){
                switch(mazeMap[row][col]){
                    case '1': mazeMap[row][col] = '#'; break;
                    case '0': mazeMap[row][col] = ' '; break;
                }
            }
        }
    }

    //Recursive Depth First Search
    private boolean solve (int row, int col){

        //Check current position is not wall or has been already visited
        if (mazeMap[row][col]=='#'||mazeMap[row][col] == 'X') {
            return false;
        }

        //if current position exit then end recursion
        if (mazeMap[row][col] == 'E') {
            return true;
        }

        //mark current position as visited
        mazeMap[row][col] = 'X';

        //Recursive Method - loop through each possible direction until the method exit is reached
        for (int[] direction : directions) {
            if (solve(row+direction[0],col+direction[1])==true) {
                return true;
            }
        }

        mazeMap[row][col] = ' ';
        return false;

    }

    //Method to print solved maze with route
   private void printMaze() {

       mazeMap[mazeStart.y][mazeStart.x] = 'S';
       for (int i = 0; i < mazeMap.length; i++) {
           System.out.println(mazeMap[i]);
       }
   }

    public static void main(String[] args) {

        //Load all .txt files in 'testMaze' folder
        File[] mazeFiles = new File("testMazes").listFiles(new FilenameFilter() {
            public boolean accept(File dir, String name) {
                return name.toLowerCase().endsWith(".txt");
            }
        });

        //Loop through and solve all the .txts files loaded
        for (File mazeFile : mazeFiles) {
            System.out.println("Maze Input: " + mazeFile); //print out maze file name

            MazeSolver mazeSolver = readMaze(mazeFile); //load the maze txt into the mazeSolver object
            mazeSolver.reformatMaze();

            if (mazeSolver.solve(mazeStart.y, mazeStart.x)) {
                mazeSolver.printMaze();
            } else {
                System.out.println("Maze not solvable");
            }

            System.out.print("\n" + "\n" + "\n");

        }
    }

}
