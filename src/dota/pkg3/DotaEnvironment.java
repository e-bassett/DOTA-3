/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dota.pkg3;

import environment.Environment;
import environment.Grid;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author erickbassett
 */
public class DotaEnvironment extends Environment{
    
    private Grid masterGrid;
    private int gridX = -200;
    private int gridY = -200;
    private Wall stone;
    private int charX = 580;
    private int charY = 340;
    private Boolean moved = true;
    private int movedCounter = 0;
    private Boolean travel = true;
    
    public Grid getMasterGrid() {
        return masterGrid;
    }

    public void setMasterGrid(Grid masterGrid) {
        this.masterGrid = masterGrid;
    }
    
    @Override
    public void initializeEnvironment() {
        
        setMasterGrid(new Grid());
        if (getMasterGrid() != null) {
            getMasterGrid().setPosition(new Point(this.gridX, this.gridY));
            getMasterGrid().setRows(49);
            getMasterGrid().setColumns(73);
            getMasterGrid().setColor(new Color(255, 0, 225));
            getMasterGrid().setCellHeight(20);
            getMasterGrid().setCellWidth(20);
        }
        
        this.stone = new Wall();
        this.stone.start(this.masterGrid);
//        this.stone.setColor(Color.black);
    }

    @Override
    public void timerTaskHandler() {
        getMasterGrid().setPosition(new Point(this.gridX, this.gridY));
        this.stone.update(this.masterGrid);
//        System.out.println("hi");
        if (this.moved != null){
        if (this.moved){
            this.movedCounter += 1;
            if(this.movedCounter >= 2){
                this.moved = false;
                this.movedCounter = 0;
            }
        }
        }
    }

    @Override
    public void keyPressedHandler(KeyEvent e) {
        if (travel){
        if (!(this.moved)){
        if (e.getKeyCode() == KeyEvent.VK_W){
            this.moved = true;
            if(this.stone.hitDetection(this.masterGrid, "up", charX, charY)){
            this.gridY = this.gridY + 20;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_A){
            this.moved = true;
            if(this.stone.hitDetection(this.masterGrid, "left", charX, charY)){
            this.gridX = this.gridX + 20;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_S){
            this.moved = true;
            if(this.stone.hitDetection(this.masterGrid, "down", charX, charY)){
            this.gridY = this.gridY - 20;
            }
        } else if (e.getKeyCode() == KeyEvent.VK_D){
            this.moved = true;
            if(this.stone.hitDetection(this.masterGrid, "right", charX, charY)){
            this.gridX = this.gridX - 20;
            }
        }
        }
    }
    }

    @Override
    public void keyReleasedHandler(KeyEvent e) {
        
    }

    @Override
    public void environmentMouseClicked(MouseEvent e) {
        
    }

    @Override
    public void paintEnvironment(Graphics graphics) {
        if (travel){
        this.stone.draw(graphics, this.masterGrid);
        getMasterGrid().paintComponent(graphics);
        graphics.setColor(Color.blue);
        graphics.fillOval(charX, charY, 20, 20);
        graphics.setColor(Color.gray);
//        graphics.fillRect(this.masterGrid.getCellPosition(30, 30).x, this.masterGrid.getCellPosition(30, 30).y, 20, 20);
    }
    }
    
}