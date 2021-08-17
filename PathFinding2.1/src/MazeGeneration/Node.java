package MazeGeneration;

import java.awt.*;
import java.awt.geom.Line2D;
import java.util.ArrayList;

public class Node {

    ArrayList<Node> neighbors;
    Line2D[] walls;
    Node[][] grid;
    Color color;
    private boolean isStartNode = false, isEndNode = false, visited = false, partOfMaze = false;
    private int row, col, size, index;


    public Node(int row, int col, int size, Node[][] grid){
        this.row = row;
        this.col = col;
        this.index = (this.row * grid[0].length) + this.col;
        this.size = size;
        this.grid = grid;
        this.walls = new Line2D[4];
        this.color = new Color(17, 17, 17);
        setWalls();
    }

    public void resetNode(){
        setWalls();
        this.isStartNode = false;
        this.isEndNode = false;
        this.visited = false;
        this.partOfMaze = false;
    }

    public void setNeighbors(){
        this.neighbors = new ArrayList<>();
        if(this.row > 0){ // top
            this.neighbors.add(this.grid[this.row - 1][this.col]);
        }
        if(this.row < this.grid.length - 1){ // bottom
            this.neighbors.add(this.grid[this.row + 1][this.col]);
        }
        if(this.col > 0){ // left
            this.neighbors.add(this.grid[this.row][this.col - 1]);
        }
        if(this.col < this.grid[0].length - 1){ // right
            this.neighbors.add(this.grid[this.row][this.col + 1]);
        }
    }
    public void setWalls(){
        int x = this.col * this.size;
        int y = this.row * this.size;
        this.walls[0] = new Line2D.Double(x,y,x + this.size, y);//top
        this.walls[1] = new Line2D.Double(x,y,x,y+this.size);//left
        this.walls[2] = new Line2D.Double(x, y+this.size, x+this.size, y+this.size);//bottom
        this.walls[3] = new Line2D.Double(x+this.size, y, x+this.size, y+this.size);//right
    }
    public void clearWall(int wallIndex){
        this.walls[wallIndex] = null;
    }

    public Color getColor(){return this.color;}
    public Line2D[] getWalls(){return this.walls;}
    public int getRow(){return this.row;}
    public int getCol(){return this.col;}
    public int getIndex(){return this.index;}
    public int getSize(){return this.size;}
    public boolean isStartNode(){return isStartNode;}
    public boolean isEndNode(){return isEndNode;}
    public boolean isVisited(){return visited;}
    public boolean isPartOfMaze(){return partOfMaze;}

    public void setStartNode(boolean val){this.isStartNode = val;}
    public void setEndNode(boolean val){this.isEndNode = val;}
    public void setVisited(boolean val){this.visited = val;}
    public void setPartOfMaze(boolean val){this.partOfMaze = val;}
    public void setColor(Color color){this.color = color;}
}
