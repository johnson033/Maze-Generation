package MazeGeneration;

import GUI.MainPanel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class AldousBroder extends Thread{

    MainPanel panel;
    Node[][] grid;

    public AldousBroder(Node[][] grid, MainPanel panel){
        this.grid = grid;
        this.panel = panel;
    }

    public void run(){
        ArrayList<Node> openSet = new ArrayList<>();
        for (Node[] nodes : this.grid) {
            openSet.addAll(Arrays.asList(nodes).subList(0, this.grid[0].length));
        }

        Node current  = openSet.remove(new Random().nextInt(openSet.size()));
        while(!openSet.isEmpty()){
            Node next = getNode(current);
            if(next != null){
                clearWalls(current, next);
                next.setVisited(true);
                openSet.remove(next);
                current = next;
            }else{
                clearWalls(current, current.neighbors.get(new Random().nextInt(current.neighbors.size())));
                current = openSet.remove(new Random().nextInt(openSet.size()));
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
