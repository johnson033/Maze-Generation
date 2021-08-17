package GUI;

import MazeGeneration.AldousBroder;
import MazeGeneration.Node;
import MazeGeneration.RecursiveBacktracker;
import MazeGeneration.Wilsons;


import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;

public class MainPanel extends JPanel {

    int rows, cols, size;
    private Node[][] grid;

    public MainPanel(int width, int height){

        this.setBounds(0,0,width, height);
        this.setLayout(null);
        this.setBackground(new Color(17, 17, 17));

        this.rows = 40;
        this.size = height / rows;
        this.cols = (int) (width * .9) / size;

        this.grid = new Node[rows][cols];

        initMaze();

        initButtons();
    }

    private void initButtons(){
        int buttonWidth = (int) ((this.getWidth() - (this.cols * this.size)) * .9);
        int buttonX = (this.getWidth() - ((this.getWidth() - (this.cols * this.size))) + (int) ((this.getWidth() - (this.cols * this.size)) * .05));


        JButton AldousBroder = new JButton("AldousBroder");
        AldousBroder.addActionListener(e ->{
            initMaze();
            new AldousBroder(this.grid, this).start();
        });

        AldousBroder.setBounds(buttonX, 100, buttonWidth, 100);
        AldousBroder.setBackground(this.getBackground());
        this.add(AldousBroder);

        JButton RecursiveBacktracker = new JButton("Recursive Backtracker");
        RecursiveBacktracker.addActionListener(e ->{
            initMaze();
            new RecursiveBacktracker(this.grid, this).start();
        });
        RecursiveBacktracker.setBounds(buttonX, 400, buttonWidth, 100);
        RecursiveBacktracker.setBackground(this.getBackground());
        this.add(RecursiveBacktracker);

        JButton Wilsons = new JButton("Wilsons");
        Wilsons.addActionListener(e ->{
            initMaze();
            new Wilsons(this.grid, this).start();
        });
        Wilsons.setBounds(buttonX, 700, buttonWidth, 100);
        Wilsons.setBackground(this.getBackground());
        this.add(Wilsons);
    }

    private void initMaze(){
        for(int row = 0; row < this.grid.length; row++){
            for(int col = 0; col < this.grid[0].length; col++){
                this.grid[row][col] = new Node(row, col, this.size, this.grid);
            }
        }

        for (Node[] nodes : this.grid) {
            for (int col = 0; col < this.grid[0].length; col++) {
                nodes[col].setNeighbors();
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintGrid((Graphics2D) g);
    }

    private void paintGrid(Graphics2D g){
        for (Node[] nodes : this.grid) {
            for (int col = 0; col < this.grid[0].length; col++) {
                Node n = nodes[col];
                g.setColor(n.getColor());
                g.fillRect(n.getCol() * this.size, n.getRow() * this.size, this.size, this.size);
                g.setColor(new Color(62, 62, 62));
                g.setStroke(new BasicStroke(1));

                for (int i = 0; i < n.getWalls().length; i++) {
                    if (n.getWalls()[i] != null) {
                        Line2D line = n.getWalls()[i];
                        g.drawLine((int) line.getX1(), (int) line.getY1(), (int) line.getX2(), (int) line.getY2());
                    }
                }
            }
        }
    }
}
