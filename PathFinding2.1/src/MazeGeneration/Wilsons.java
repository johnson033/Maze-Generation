package MazeGeneration;

import GUI.MainPanel;

import java.awt.*;
import java.util.*;

public class Wilsons extends Thread{

    private final MainPanel panel;
    private final ArrayList<Node> walk;
    private final ArrayList<Node> allNodes;
    public Wilsons(Node[][] grid, MainPanel panel){
        this.panel = panel;

        allNodes = new ArrayList<>();
        walk = new ArrayList<>();
        for (Node[] nodes : grid) {
            allNodes.addAll(Arrays.asList(nodes).subList(0, grid[0].length));
        }

        int startNodes =  1 ;// (int) ((this.grid.length * this.grid[0].length) * .0045);
        for(int i =0; i < startNodes; i++){
            int row = 1 + new Random().nextInt(grid.length - 2);
            int col = 1 + new Random().nextInt(grid[0].length - 2);
            Node start = grid[row][col];
            allNodes.remove(start);
            start.setPartOfMaze(true);
        }
        sortAllNodes();
        this.panel.repaint();
    }


    public void run(){
        while(!allNodes.isEmpty()){
            Node node = allNodes.get(0);
            walk.add(node);
            while(!node.isPartOfMaze()) {
                Node next = getNode(node);
                node.setVisited(true);

                if(walk.contains(next)){
                    node = walk.indexOf(next) == 0? walk.get(0) : walk.get(walk.indexOf(next) - 1);
                    for(int i = walk.size() - 1; i > walk.indexOf(next); i--){
                        walk.get(i).setVisited(false);
                        walk.get(i).setWalls();
                        walk.get(i).setColor(new Color(17, 17, 17));
                        walk.remove(i);
                        this.panel.repaint();
                        Sleep();
                    }
                    walk.remove(next);
                }
                else {
                    next.setVisited(true);
                    next.setColor(Color.red);
                    clearWalls(node, next);
                    walk.add(node);
                    walk.add(next);
                    node = next;
                    this.panel.repaint();
                }
                Sleep();
            }
            for(Node n: walk){
                n.setVisited(false);
                n.setPartOfMaze(true);
                n.setColor(new Color(17,17,17));
                allNodes.remove(n);
            }
            walk.clear();
            this.panel.repaint();
            Sleep();
        }
    }

    private void Sleep(){
        try {
            sleep(1);
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
        else return node.neighbors.get(new Random().nextInt(node.neighbors.size()));
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

    private void sortAllNodes(){
        allNodes.sort(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                return o2.getRow() - o1.getRow();
            }
        });
    }
}
