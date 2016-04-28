package com;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author majcz_000
 */
   
public class BackGround extends JPanel {
    
    private BufferedImage image;
    
    public BackGround(){
        try {
        image = ImageIO.read(getClass().getResourceAsStream("resources/Blok NL.jpg"));
    } catch(IOException e) {
      e.printStackTrace();
    }
    }
protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    // paint the background image and scale it to fill the entire space
    g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
  }
    
    
}
    

