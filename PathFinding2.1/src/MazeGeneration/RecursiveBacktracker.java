package MazeGeneration;

import GUI.MainPanel;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class RecursiveBacktracker extends Thread{

    Stack<Node> stack = new Stack<>();
    private Node[][] grid;
    private MainPanel panel;

    public RecursiveBacktracker(Node[][] grid, MainPanel panel){
        this.grid = grid;
        this.panel = panel;

        for (Node[] nodes : this.grid) {
            for (int col = 0; col < this.grid.length; col++) {
                nodes[col].resetNode();
            }
        }
    }

    public void run(){
        Node node = this.grid[this.grid.length / 2][this.grid[0].length / 2];
        stack.push(node);
        while(!this.stack.isEmpty()){

            node.setVisited(true);
            node.setPartOfMaze(true);
            Node nextNode = getNode(node);

            if(nextNode != null){
                nextNode.setVisited(true);
                stack.push(node);
                clearWalls(node, nextNode);
                node = nextNode;
            }else if(!stack.isEmpty()){
                node = stack.pop();
            }

            Sleep(10);
            panel.repaint();
        }
    }

    private void Sleep(int time){
        try {
            sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private Node getNode(Node node){
        ArrayList<Node> neighbors = new ArrayList<>();
        for(int i = 0; i < node.neighbors.size(); i++){
            if(!node.neighbors.get(i).isVisited()){
                neighbors.add(node.neighbors.get(i));
            }
        }
        if(neighbors.size() > 0)
            return neighbors.get(new Random().nextInt(neighbors.size()));
        else return null;
    }

    private void clearWalls(Node node, Node next){
        if(next.getRow() < node.getRow()){
            node.clearWall(0);
            next.clearWall(2);
        }
        //if left neighbor
        else if(next.getCol() < node.getCol()){
            node.clearWall(1);
            next.clearWall(3);
        }
        //if bottom neighbor
        else if(next.getRow() > node.getRow()){
            node.clearWall(2);
            next.clearWall(0);
        }
        //if right neighbor
        else if(next.getCol() > node.getCol()){
            node.clearWall(3);
            next.clearWall(1);
        }
    }
}
