package de.lmu.ifi.sosylab.view;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;


public class DrawBackground extends JPanel {


  private BufferedImage backgroundIMGClassic;
  private BufferedImage backgroundIMGCosmic;
  private PlayingView themesItems;
  private PlayingView themeSelected;

  public DrawBackground() {
      }
  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2D = (Graphics2D) g;    // Type g2D required for stroke methods - use unified, not both, g and g2D
    drawClassicBackground(g2D);
    //addThemesActionListener(g2D);
  }



  public void drawClassicBackground(Graphics2D g2D){
    ClassLoader classLoader = null; //Name dieser klasse
    try {
      classLoader = Class.forName("de.lmu.ifi.sosylab.view.DrawBackground").getClassLoader();
    } catch (ClassNotFoundException e) {
      throw new RuntimeException(e);
    }
    var stream = (classLoader.getResourceAsStream("back_classic.png"));
    try {
      backgroundIMGClassic = ImageIO.read(stream);
    } catch (IOException e) {
      e.printStackTrace();
    }
    g2D.drawImage(backgroundIMGClassic,0, 0,410, 900,this);
  }

//  public void drawCosmicBackground(Graphics2D g2D){
//    ClassLoader classLoader2 = null; //Name dieser klasse
//    try {
//      classLoader2 = Class.forName("de.lmu.ifi.sosylab.view.DrawBackground").getClassLoader();
//    } catch (ClassNotFoundException e) {
//      throw new RuntimeException(e);
//    }
//    var stream = (classLoader2.getResourceAsStream("back_cosmic.png"));
//    try {
//      backgroundIMGCosmic = ImageIO.read(stream);
//    } catch (IOException e) {
//      e.printStackTrace();
//    }
//    g2D.drawImage(backgroundIMGCosmic,0, 0,410, 900,this);
//  }
//
//
//  public PlayingView getThemesItems() {
//    return themesItems;
//  }
//
//  public PlayingView getThemeSelected(){
//    return themeSelected;
//  }
//
//  public void addThemesActionListener(Graphics2D g2D){
//    themesItems.addActionlistener(new ActionListener(){
//      @Override
//      public void actionPerformed(ActionEvent e) {
//        String themeSelected = String.valueOf(getThemeSelected());
//        if (themeSelected == "Cosmic"){
//          drawCosmicBackground(g2D);
//        }
//      }
//    });
 // }




}