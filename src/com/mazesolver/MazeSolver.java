package com.mazesolver;

import java.awt.*;
import java.io.*;
import java.util.*;
import java.util.List;

//http://www.baeldung.com/java-solve-maze
public class MazeSolver {

    private int mazeWidth;
    private int mazeHeight;
    private Point mazeStart;
    private Point mazeEnd;
    private char [][] mazeArray;

    //Getters & Setters
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

    public char[][] getMazeArray() {
        return mazeArray;
    }

    public void setMazeArray(char[][] mazeArray) {
        this.mazeArray = mazeArray;
    }

    public char getMazeChar(int row, int col) {
        return mazeArray[row][col];
    }

    public void setMazeChar(char mazeChar, int row, int col) {
        this.mazeArray[row][col] = mazeChar;
    }

    public boolean isValidLocation{

    }

    public boolean isExplored{

    }

    public boolean isWall{

    }

    public boolean setVisited{

    }

    public boolean isExit{

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

    //Array of possible directions - right, up, left, down
    private static final int[][] directions = {{0,1},{1,0},{0,-1},{-1,0}};

    //Calculate next coordinate path takes - current coordinate + a direction
    private Point getNextCoordinate( int row, int col, int x, int y) {
        return new Point(row + x, col + y);
    }


    //Solve
    public List<Point> solve(MazeSolver maze) {
        List<Point> path = new ArrayList<>();
        if (explore(maze, maze.getEntry().getX(), maze.getEntry().getY(), path)) {
            return path;
        }
        return Collections.emptyList();
    }

    //
    private boolean explore(MazeSolver maze, int row, int col, List<Point> path) {
        if (  !maze.isValidLocation(row, col) || maze.isWall(row, col)|| maze.isExplored(row, col) {
            return false;
        }

        path.add(new Point(row, col));
        maze.setVisited(row, col, true);

        if (maze.isExit(row, col)) {
            return true;
        }

        for (int[] direction : directions) {
            Point point = getNextCoordinate(row, col, direction[0], direction[1]);
            if (explore(maze, point.getX(),point.getY(), path)) {
                return true;
            }
        }

        path.remove(path.size() - 1);
        return false;
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
