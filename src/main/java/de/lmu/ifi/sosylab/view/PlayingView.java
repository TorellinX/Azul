package main.java.de.lmu.ifi.sosylab.view;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serial;


public class PlayingView extends JFrame {
  @Serial
  private static final long serialVersionUID = 1L;

  public PlayingView() {
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    setResizable(false);
    setSize(1200, 700);

    createPlayingView();

  }

  private void createPlayingView() {

    JFrame playingviewframe = new JFrame("Azul");


    playingviewframe.setLayout(new BorderLayout());


    Color backroundColor = new Color(135, 206, 250);

    //Zeichenelemente werden übergeben.
    DrawboardPlayerBoardLeft drawboardplayerboardleft = new DrawboardPlayerBoardLeft();
    DrawboardPlayerBoardRight drawboardplayerboardright = new DrawboardPlayerBoardRight();
    DrawboardTableCenter drawboardtablecenter = new DrawboardTableCenter();


    //Oberes Panel wird mit Combobox gefüllt.
    JPanel panelUp = new JPanel();
    panelUp.setSize(1200, 75);
    panelUp.setLayout(new FlowLayout());
    JComboBox<String> menu = new JComboBox<String>(new String[]{"- menu -", "restart", "leave", "end game"});
    panelUp.add(menu);
    panelUp.setBackground(backroundColor);


    //Linkes Panel wird mit Buttons gefüllt:

    drawboardplayerboardleft.setLayout(null);

    JButton firstrowu1button = new JButton();
    firstrowu1button.setBounds(145, 5, 35, 35);
    firstrowu1button.setVisible(false);
    firstrowu1button.setEnabled(true);

    JButton secondrowu1button = new JButton();
    secondrowu1button.setBounds(110, 40, 70, 35);
    secondrowu1button.setVisible(false);
    secondrowu1button.setEnabled(true);

    JButton thirdrowu1button = new JButton();
    thirdrowu1button.setBounds(75, 75, 105, 35);
    thirdrowu1button.setVisible(false);
    thirdrowu1button.setEnabled(true);

    JButton fourthrowu1button = new JButton();
    fourthrowu1button.setBounds(40, 110, 140, 35);
    fourthrowu1button.setVisible(false);
    fourthrowu1button.setEnabled(true);

    JButton fifthrowu1button = new JButton();
    fifthrowu1button.setBounds(5, 145, 175, 35);
    fifthrowu1button.setVisible(false);
    fifthrowu1button.setEnabled(true);

    JButton floorlineu1button = new JButton();
    floorlineu1button.setBounds(5, 205, 245, 35);
    floorlineu1button.setVisible(false);
    floorlineu1button.setEnabled(true);

    drawboardplayerboardleft.add(firstrowu1button);
    drawboardplayerboardleft.add(secondrowu1button);
    drawboardplayerboardleft.add(thirdrowu1button);
    drawboardplayerboardleft.add(fourthrowu1button);
    drawboardplayerboardleft.add(fifthrowu1button);
    drawboardplayerboardleft.add(floorlineu1button);

    JButton firstrowu3button = new JButton();
    firstrowu3button.setBounds(145, 305, 35, 35);
    firstrowu3button.setVisible(false);
    firstrowu3button.setEnabled(true);

    JButton secondrowu3button = new JButton();
    secondrowu3button.setBounds(110, 340, 70, 35);
    secondrowu3button.setVisible(false);
    secondrowu3button.setEnabled(true);

    JButton thirdrowu3button = new JButton();
    thirdrowu3button.setBounds(75, 375, 105, 35);
    thirdrowu3button.setVisible(false);
    thirdrowu3button.setEnabled(true);

    JButton fourthrowu3button = new JButton();
    fourthrowu3button.setBounds(40, 410, 140, 35);
    fourthrowu3button.setVisible(false);
    fourthrowu3button.setEnabled(true);

    JButton fifthrowu3button = new JButton();
    fifthrowu3button.setBounds(5, 445, 175, 35);
    fifthrowu3button.setVisible(false);
    fifthrowu3button.setEnabled(true);

    JButton floorlineu3button = new JButton();
    floorlineu3button.setBounds(5, 505, 245, 35);
    floorlineu3button.setVisible(false);
    floorlineu3button.setEnabled(true);

    drawboardplayerboardleft.add(firstrowu3button);
    drawboardplayerboardleft.add(secondrowu3button);
    drawboardplayerboardleft.add(thirdrowu3button);
    drawboardplayerboardleft.add(fourthrowu3button);
    drawboardplayerboardleft.add(fifthrowu3button);
    drawboardplayerboardleft.add(floorlineu3button);


    //Rechtes Panel wird mit Buttons gefüllt:

    drawboardplayerboardright.setLayout(null);

    JButton firstrowu2button = new JButton();
    firstrowu2button.setBounds(145, 5, 35, 35);
    firstrowu2button.setVisible(false);
    firstrowu2button.setEnabled(true);

    JButton secondrowu2button = new JButton();
    secondrowu2button.setBounds(110, 40, 70, 35);
    secondrowu2button.setVisible(false);
    secondrowu2button.setEnabled(true);

    JButton thirdrowu2button = new JButton();
    thirdrowu2button.setBounds(75, 75, 105, 35);
    thirdrowu2button.setVisible(false);
    thirdrowu2button.setEnabled(true);

    JButton fourthrowu2button = new JButton();
    fourthrowu2button.setBounds(40, 110, 140, 35);
    fourthrowu2button.setVisible(false);
    fourthrowu2button.setEnabled(true);

    JButton fifthrowu2button = new JButton();
    fifthrowu2button.setBounds(5, 145, 175, 35);
    fifthrowu2button.setVisible(false);
    fifthrowu2button.setEnabled(true);

    JButton floorlineu2button = new JButton();
    floorlineu2button.setBounds(5, 205, 245, 35);
    floorlineu2button.setVisible(false);
    floorlineu2button.setEnabled(true);

    drawboardplayerboardright.add(firstrowu2button);
    drawboardplayerboardright.add(secondrowu2button);
    drawboardplayerboardright.add(thirdrowu2button);
    drawboardplayerboardright.add(fourthrowu2button);
    drawboardplayerboardright.add(fifthrowu2button);
    drawboardplayerboardright.add(floorlineu2button);


    JButton firstrowu4button = new JButton();
    firstrowu4button.setBounds(145, 305, 35, 35);
    firstrowu4button.setVisible(false);
    firstrowu4button.setEnabled(true);

    JButton secondrowu4button = new JButton();
    secondrowu4button.setBounds(110, 340, 70, 35);
    secondrowu4button.setVisible(false);
    secondrowu4button.setEnabled(true);

    JButton thirdrowu4button = new JButton();
    thirdrowu4button.setBounds(75, 375, 105, 35);
    thirdrowu4button.setVisible(false);
    thirdrowu4button.setEnabled(true);

    JButton fourthrowu4button = new JButton();
    fourthrowu4button.setBounds(40, 410, 140, 35);
    fourthrowu4button.setVisible(false);
    fourthrowu4button.setEnabled(true);

    JButton fifthrowu4button = new JButton();
    fifthrowu4button.setBounds(5, 445, 175, 35);
    fifthrowu4button.setVisible(false);
    fifthrowu4button.setEnabled(true);

    JButton floorlineu4button = new JButton();
    floorlineu4button.setBounds(5, 505, 245, 35);
    floorlineu4button.setVisible(false);
    floorlineu4button.setEnabled(true);

    drawboardplayerboardright.add(firstrowu4button);
    drawboardplayerboardright.add(secondrowu4button);
    drawboardplayerboardright.add(thirdrowu4button);
    drawboardplayerboardright.add(fourthrowu4button);
    drawboardplayerboardright.add(fifthrowu4button);
    drawboardplayerboardright.add(floorlineu4button);


    //Mittleres Panel wird mit Buttons gefüllt:

    drawboardtablecenter.setLayout(null);

    //Zuerst kommen die Buttons oberhalb der Stein auf den Plates:
    JButton firsttilefromplatesbutton = new JButton();
    firsttilefromplatesbutton.setBounds(17, 17, 35, 35);
    firsttilefromplatesbutton.setVisible(false);
    firsttilefromplatesbutton.setEnabled(true);

    JButton secondtilefromplatesbutton = new JButton();
    secondtilefromplatesbutton.setBounds(58, 17, 35, 35);
    secondtilefromplatesbutton.setVisible(false);
    secondtilefromplatesbutton.setEnabled(true);

    JButton thirdtilefromplatesbutton = new JButton();
    thirdtilefromplatesbutton.setBounds(17, 58, 35, 35);
    thirdtilefromplatesbutton.setVisible(false);
    thirdtilefromplatesbutton.setEnabled(true);

    JButton fourthtilefromplatesbutton = new JButton();
    fourthtilefromplatesbutton.setBounds(58, 58, 35, 35);
    fourthtilefromplatesbutton.setVisible(false);
    fourthtilefromplatesbutton.setEnabled(true);

    JButton fifthtilefromplatesbutton = new JButton();
    fifthtilefromplatesbutton.setBounds(167, 17, 35, 35);
    fifthtilefromplatesbutton.setVisible(false);
    fifthtilefromplatesbutton.setEnabled(true);

    JButton sixthtilefromplatesbutton = new JButton();
    sixthtilefromplatesbutton.setBounds(208, 17, 35, 35);
    sixthtilefromplatesbutton.setVisible(false);
    sixthtilefromplatesbutton.setEnabled(true);

    JButton seventhtilefromplatesbutton = new JButton();
    seventhtilefromplatesbutton.setBounds(167, 58, 35, 35);
    seventhtilefromplatesbutton.setVisible(false);
    seventhtilefromplatesbutton.setEnabled(true);

    JButton eightthtilefromplatesbutton = new JButton();
    eightthtilefromplatesbutton.setBounds(208, 58, 35, 35);
    eightthtilefromplatesbutton.setVisible(false);
    eightthtilefromplatesbutton.setEnabled(true);

    JButton ninethtilefromplatesbutton = new JButton();
    ninethtilefromplatesbutton.setBounds(317, 17, 35, 35);
    ninethtilefromplatesbutton.setVisible(false);
    ninethtilefromplatesbutton.setEnabled(true);

    JButton tenthtilefromplatesbutton = new JButton();
    tenthtilefromplatesbutton.setBounds(358, 17, 35, 35);
    tenthtilefromplatesbutton.setVisible(false);
    tenthtilefromplatesbutton.setEnabled(true);

    JButton eleventhtilefromplatesbutton = new JButton();
    eleventhtilefromplatesbutton.setBounds(317, 58, 35, 35);
    eleventhtilefromplatesbutton.setVisible(false);
    eleventhtilefromplatesbutton.setEnabled(true);

    JButton twelvethtilefromplatesbutton = new JButton();
    twelvethtilefromplatesbutton.setBounds(358, 58, 35, 35);
    twelvethtilefromplatesbutton.setVisible(false);
    twelvethtilefromplatesbutton.setEnabled(true);

    JButton thirteenthtilefromplatesbutton = new JButton();
    thirteenthtilefromplatesbutton.setBounds(92, 117, 35, 35);
    thirteenthtilefromplatesbutton.setVisible(false);
    thirteenthtilefromplatesbutton.setEnabled(true);

    JButton fourteenthtilefromplatesbutton = new JButton();
    fourteenthtilefromplatesbutton.setBounds(133, 117, 35, 35);
    fourteenthtilefromplatesbutton.setVisible(false);
    fourteenthtilefromplatesbutton.setEnabled(true);

    JButton fifteenthtilefromplatesbutton = new JButton();
    fifteenthtilefromplatesbutton.setBounds(92, 158, 35, 35);
    fifteenthtilefromplatesbutton.setVisible(false);
    fifteenthtilefromplatesbutton.setEnabled(true);

    JButton sixteenthtilefromplatesbutton = new JButton();
    sixteenthtilefromplatesbutton.setBounds(133, 158, 35, 35);
    sixteenthtilefromplatesbutton.setVisible(false);
    sixteenthtilefromplatesbutton.setEnabled(true);

    JButton seventeenthtilefromplatesbutton = new JButton();
    seventeenthtilefromplatesbutton.setBounds(242, 117, 35, 35);
    seventeenthtilefromplatesbutton.setVisible(false);
    seventeenthtilefromplatesbutton.setEnabled(true);

    JButton eighteenthtilefromplatesbutton = new JButton();
    eighteenthtilefromplatesbutton.setBounds(283, 117, 35, 35);
    eighteenthtilefromplatesbutton.setVisible(false);
    eighteenthtilefromplatesbutton.setEnabled(true);

    JButton nineteenthtilefromplatesbutton = new JButton();
    nineteenthtilefromplatesbutton.setBounds(242, 158, 35, 35);
    nineteenthtilefromplatesbutton.setVisible(false);
    nineteenthtilefromplatesbutton.setEnabled(true);

    JButton twentiethtilefromplatesbutton = new JButton();
    twentiethtilefromplatesbutton.setBounds(283, 158, 35, 35);
    twentiethtilefromplatesbutton.setVisible(false);
    twentiethtilefromplatesbutton.setEnabled(true);

    JButton twentyfirsttilefromplatesbutton = new JButton();
    twentyfirsttilefromplatesbutton.setBounds(92, 242, 35, 35);
    twentyfirsttilefromplatesbutton.setVisible(false);
    twentyfirsttilefromplatesbutton.setEnabled(true);

    JButton twentysecondtilefromplatesbutton = new JButton();
    twentysecondtilefromplatesbutton.setBounds(133, 242, 35, 35);
    twentysecondtilefromplatesbutton.setVisible(false);
    twentysecondtilefromplatesbutton.setEnabled(true);

    JButton twentythirdtilefromplatesbutton = new JButton();
    twentythirdtilefromplatesbutton.setBounds(92, 283, 35, 35);
    twentythirdtilefromplatesbutton.setVisible(false);
    twentythirdtilefromplatesbutton.setEnabled(true);

    JButton twentyfourthtilefromplatesbutton = new JButton();
    twentyfourthtilefromplatesbutton.setBounds(133, 283, 35, 35);
    twentyfourthtilefromplatesbutton.setVisible(false);
    twentyfourthtilefromplatesbutton.setEnabled(true);

    JButton twentyfifthtilefromplatesbutton = new JButton();
    twentyfifthtilefromplatesbutton.setBounds(242, 242, 35, 35);
    twentyfifthtilefromplatesbutton.setVisible(false);
    twentyfifthtilefromplatesbutton.setEnabled(true);

    JButton twentysixthtilefromplatesbutton = new JButton();
    twentysixthtilefromplatesbutton.setBounds(283, 242, 35, 35);
    twentysixthtilefromplatesbutton.setVisible(false);
    twentysixthtilefromplatesbutton.setEnabled(true);

    JButton twentyseventhtilefromplatesbutton = new JButton();
    twentyseventhtilefromplatesbutton.setBounds(242, 283, 35, 35);
    twentyseventhtilefromplatesbutton.setVisible(false);
    twentyseventhtilefromplatesbutton.setEnabled(true);

    JButton twentyeightthtilefromplatesbutton = new JButton();
    twentyeightthtilefromplatesbutton.setBounds(283, 283, 35, 35);
    twentyeightthtilefromplatesbutton.setVisible(false);
    twentyeightthtilefromplatesbutton.setEnabled(true);

    JButton twentyninethtilefromplatesbutton = new JButton();
    twentyninethtilefromplatesbutton.setBounds(92, 367, 35, 35);
    twentyninethtilefromplatesbutton.setVisible(false);
    twentyninethtilefromplatesbutton.setEnabled(true);

    JButton thirtiethtilefromplatesbutton = new JButton();
    thirtiethtilefromplatesbutton.setBounds(133, 367, 35, 35);
    thirtiethtilefromplatesbutton.setVisible(false);
    thirtiethtilefromplatesbutton.setEnabled(true);

    JButton thirtyfirsttilefromplatesbutton = new JButton();
    thirtyfirsttilefromplatesbutton.setBounds(92, 408, 35, 35);
    thirtyfirsttilefromplatesbutton.setVisible(false);
    thirtyfirsttilefromplatesbutton.setEnabled(true);

    JButton thirtysecondtilefromplatesbutton = new JButton();
    thirtysecondtilefromplatesbutton.setBounds(133, 408, 35, 35);
    thirtysecondtilefromplatesbutton.setVisible(false);
    thirtysecondtilefromplatesbutton.setEnabled(true);

    JButton thirtythirdtilefromplatesbutton = new JButton();
    thirtythirdtilefromplatesbutton.setBounds(242, 367, 35, 35);
    thirtythirdtilefromplatesbutton.setVisible(false);
    thirtythirdtilefromplatesbutton.setEnabled(true);

    JButton thirtyfourthtilefromplatesbutton = new JButton();
    thirtyfourthtilefromplatesbutton.setBounds(283, 367, 35, 35);
    thirtyfourthtilefromplatesbutton.setVisible(false);
    thirtyfourthtilefromplatesbutton.setEnabled(true);

    JButton thirtyfifthtilefromplatesbutton = new JButton();
    thirtyfifthtilefromplatesbutton.setBounds(242, 408, 35, 35);
    thirtyfifthtilefromplatesbutton.setVisible(false);
    thirtyfifthtilefromplatesbutton.setEnabled(true);

    JButton thirtysixthtilefromplatesbutton = new JButton();
    thirtysixthtilefromplatesbutton.setBounds(283, 408, 35, 35);
    thirtysixthtilefromplatesbutton.setVisible(false);
    thirtysixthtilefromplatesbutton.setEnabled(true);

    drawboardtablecenter.add(firsttilefromplatesbutton);
    drawboardtablecenter.add(secondtilefromplatesbutton);
    drawboardtablecenter.add(thirdtilefromplatesbutton);
    drawboardtablecenter.add(fourthtilefromplatesbutton);
    drawboardtablecenter.add(fifthtilefromplatesbutton);
    drawboardtablecenter.add(sixthtilefromplatesbutton);
    drawboardtablecenter.add(seventhtilefromplatesbutton);
    drawboardtablecenter.add(eightthtilefromplatesbutton);
    drawboardtablecenter.add(ninethtilefromplatesbutton);
    drawboardtablecenter.add(tenthtilefromplatesbutton);
    drawboardtablecenter.add(eleventhtilefromplatesbutton);
    drawboardtablecenter.add(twelvethtilefromplatesbutton);
    drawboardtablecenter.add(thirteenthtilefromplatesbutton);
    drawboardtablecenter.add(fourteenthtilefromplatesbutton);
    drawboardtablecenter.add(fifteenthtilefromplatesbutton);
    drawboardtablecenter.add(sixteenthtilefromplatesbutton);
    drawboardtablecenter.add(seventeenthtilefromplatesbutton);
    drawboardtablecenter.add(eighteenthtilefromplatesbutton);
    drawboardtablecenter.add(nineteenthtilefromplatesbutton);
    drawboardtablecenter.add(twentiethtilefromplatesbutton);
    drawboardtablecenter.add(twentyfirsttilefromplatesbutton);
    drawboardtablecenter.add(twentysecondtilefromplatesbutton);
    drawboardtablecenter.add(twentythirdtilefromplatesbutton);
    drawboardtablecenter.add(twentyfourthtilefromplatesbutton);
    drawboardtablecenter.add(twentyfifthtilefromplatesbutton);
    drawboardtablecenter.add(twentysixthtilefromplatesbutton);
    drawboardtablecenter.add(twentyseventhtilefromplatesbutton);
    drawboardtablecenter.add(twentyeightthtilefromplatesbutton);
    drawboardtablecenter.add(twentyninethtilefromplatesbutton);
    drawboardtablecenter.add(thirtiethtilefromplatesbutton);
    drawboardtablecenter.add(thirtyfirsttilefromplatesbutton);
    drawboardtablecenter.add(thirtysecondtilefromplatesbutton);
    drawboardtablecenter.add(thirtythirdtilefromplatesbutton);
    drawboardtablecenter.add(thirtyfourthtilefromplatesbutton);
    drawboardtablecenter.add(thirtyfifthtilefromplatesbutton);
    drawboardtablecenter.add(thirtysixthtilefromplatesbutton);

    //Hier kommen die Buttons oberhalb der Steine auf dem Table:
    JButton penaltytilefromtablebutton = new JButton();
    penaltytilefromtablebutton.setBounds(25, 505, 35, 35);
    penaltytilefromtablebutton.setVisible(false);
    penaltytilefromtablebutton.setEnabled(true);

    JButton firsttilefromtablebutton = new JButton();
    firsttilefromtablebutton.setBounds(105, 505, 35, 35);
    firsttilefromtablebutton.setVisible(false);
    firsttilefromtablebutton.setEnabled(true);

    JButton secondtilefromtablebutton = new JButton();
    secondtilefromtablebutton.setBounds(145, 505, 35, 35);
    secondtilefromtablebutton.setVisible(false);
    secondtilefromtablebutton.setEnabled(true);

    JButton thirdtilefromtablebutton = new JButton();
    thirdtilefromtablebutton.setBounds(185, 505, 35, 35);
    thirdtilefromtablebutton.setVisible(false);
    thirdtilefromtablebutton.setEnabled(true);

    JButton fourthtilefromtablebutton = new JButton();
    fourthtilefromtablebutton.setBounds(225, 505, 35, 35);
    fourthtilefromtablebutton.setVisible(false);
    fourthtilefromtablebutton.setEnabled(true);

    JButton fifthtilefromtablebutton = new JButton();
    fifthtilefromtablebutton.setBounds(265, 505, 35, 35);
    fifthtilefromtablebutton.setVisible(false);
    fifthtilefromtablebutton.setEnabled(true);

    JButton sixthtilefromtablebutton = new JButton();
    sixthtilefromtablebutton.setBounds(305, 505, 35, 35);
    sixthtilefromtablebutton.setVisible(false);
    sixthtilefromtablebutton.setEnabled(true);

    JButton seventhtilefromtablebutton = new JButton();
    seventhtilefromtablebutton.setBounds(345, 505, 35, 35);
    seventhtilefromtablebutton.setVisible(false);
    seventhtilefromtablebutton.setEnabled(true);

    JButton eightthtilefromtablebutton = new JButton();
    eightthtilefromtablebutton.setBounds(105, 545, 35, 35);
    eightthtilefromtablebutton.setVisible(false);
    eightthtilefromtablebutton.setEnabled(true);

    JButton ninethtilefromtablebutton = new JButton();
    ninethtilefromtablebutton.setBounds(145, 545, 35, 35);
    ninethtilefromtablebutton.setVisible(false);
    ninethtilefromtablebutton.setEnabled(true);

    JButton tenthtilefromtablebutton = new JButton();
    tenthtilefromtablebutton.setBounds(185, 545, 35, 35);
    tenthtilefromtablebutton.setVisible(false);
    tenthtilefromtablebutton.setEnabled(true);

    JButton eleventhtilefromtablebutton = new JButton();
    eleventhtilefromtablebutton.setBounds(225, 545, 35, 35);
    eleventhtilefromtablebutton.setVisible(false);
    eleventhtilefromtablebutton.setEnabled(true);

    JButton twelvethtilefromtablebutton = new JButton();
    twelvethtilefromtablebutton.setBounds(265, 545, 35, 35);
    twelvethtilefromtablebutton.setVisible(false);
    twelvethtilefromtablebutton.setEnabled(true);

    JButton thirteenthtilefromtablebutton = new JButton();
    thirteenthtilefromtablebutton.setBounds(305, 545, 35, 35);
    thirteenthtilefromtablebutton.setVisible(false);
    thirteenthtilefromtablebutton.setEnabled(true);

    JButton fourteenthtilefromtablebutton = new JButton();
    fourteenthtilefromtablebutton.setBounds(345, 545, 35, 35);
    fourteenthtilefromtablebutton.setVisible(false);
    fourteenthtilefromtablebutton.setEnabled(true);

    JButton fifteenthtilefromtablebutton = new JButton();
    fifteenthtilefromtablebutton.setBounds(105, 585, 35, 35);
    fifteenthtilefromtablebutton.setVisible(false);
    fifteenthtilefromtablebutton.setEnabled(true);

    JButton sixteenthtilefromtablebutton = new JButton();
    sixteenthtilefromtablebutton.setBounds(145, 585, 35, 35);
    sixteenthtilefromtablebutton.setVisible(false);
    sixteenthtilefromtablebutton.setEnabled(true);

    JButton seventeenthtilefromtablebutton = new JButton();
    seventeenthtilefromtablebutton.setBounds(185, 585, 35, 35);
    seventeenthtilefromtablebutton.setVisible(false);
    seventeenthtilefromtablebutton.setEnabled(true);

    JButton eighteenthtilefromtablebutton = new JButton();
    eighteenthtilefromtablebutton.setBounds(225, 585, 35, 35);
    eighteenthtilefromtablebutton.setVisible(false);
    eighteenthtilefromtablebutton.setEnabled(true);

    JButton nineteenthtilefromtablebutton = new JButton();
    nineteenthtilefromtablebutton.setBounds(265, 585, 35, 35);
    nineteenthtilefromtablebutton.setVisible(false);
    nineteenthtilefromtablebutton.setEnabled(true);

    JButton twentiethtilefromtablebutton = new JButton();
    twentiethtilefromtablebutton.setBounds(305, 585, 35, 35);
    twentiethtilefromtablebutton.setVisible(false);
    twentiethtilefromtablebutton.setEnabled(true);

    JButton twentyfirsttilefromtablebutton = new JButton();
    twentyfirsttilefromtablebutton.setBounds(345, 585, 35, 35);
    twentyfirsttilefromtablebutton.setVisible(false);
    twentyfirsttilefromtablebutton.setEnabled(true);

    JButton twentysecondtilefromtablebutton = new JButton();
    twentysecondtilefromtablebutton.setBounds(105, 625, 35, 35);
    twentysecondtilefromtablebutton.setVisible(false);
    twentysecondtilefromtablebutton.setEnabled(true);

    JButton twentythirdtilefromtablebutton = new JButton();
    twentythirdtilefromtablebutton.setBounds(145, 625, 35, 35);
    twentythirdtilefromtablebutton.setVisible(false);
    twentythirdtilefromtablebutton.setEnabled(true);

    JButton twentyfourthtilefromtablebutton = new JButton();
    twentyfourthtilefromtablebutton.setBounds(185, 625, 35, 35);
    twentyfourthtilefromtablebutton.setVisible(false);
    twentyfourthtilefromtablebutton.setEnabled(true);

    JButton twentyfifthtilefromtablebutton = new JButton();
    twentyfifthtilefromtablebutton.setBounds(225, 625, 35, 35);
    twentyfifthtilefromtablebutton.setVisible(false);
    twentyfifthtilefromtablebutton.setEnabled(true);

    JButton twentysixthtilefromtablebutton = new JButton();
    twentysixthtilefromtablebutton.setBounds(265, 625, 35, 35);
    twentysixthtilefromtablebutton.setVisible(false);
    twentysixthtilefromtablebutton.setEnabled(true);

    JButton twentyseventhtilefromtablebutton = new JButton();
    twentyseventhtilefromtablebutton.setBounds(305, 625, 35, 35);
    twentyseventhtilefromtablebutton.setVisible(false);
    twentyseventhtilefromtablebutton.setEnabled(true);

    drawboardtablecenter.add(penaltytilefromtablebutton);
    drawboardtablecenter.add(firsttilefromtablebutton);
    drawboardtablecenter.add(secondtilefromtablebutton);
    drawboardtablecenter.add(thirdtilefromtablebutton);
    drawboardtablecenter.add(fourthtilefromtablebutton);
    drawboardtablecenter.add(fifthtilefromtablebutton);
    drawboardtablecenter.add(sixthtilefromtablebutton);
    drawboardtablecenter.add(seventhtilefromtablebutton);
    drawboardtablecenter.add(eightthtilefromtablebutton);
    drawboardtablecenter.add(ninethtilefromtablebutton);
    drawboardtablecenter.add(tenthtilefromtablebutton);
    drawboardtablecenter.add(eleventhtilefromtablebutton);
    drawboardtablecenter.add(twelvethtilefromtablebutton);
    drawboardtablecenter.add(thirteenthtilefromtablebutton);
    drawboardtablecenter.add(fourteenthtilefromtablebutton);
    drawboardtablecenter.add(fifteenthtilefromtablebutton);
    drawboardtablecenter.add(sixteenthtilefromtablebutton);
    drawboardtablecenter.add(seventeenthtilefromtablebutton);
    drawboardtablecenter.add(eighteenthtilefromtablebutton);
    drawboardtablecenter.add(nineteenthtilefromtablebutton);
    drawboardtablecenter.add(twentiethtilefromtablebutton);
    drawboardtablecenter.add(twentyfirsttilefromtablebutton);
    drawboardtablecenter.add(twentysecondtilefromtablebutton);
    drawboardtablecenter.add(twentythirdtilefromtablebutton);
    drawboardtablecenter.add(twentyfourthtilefromtablebutton);
    drawboardtablecenter.add(twentyfifthtilefromtablebutton);
    drawboardtablecenter.add(twentysixthtilefromtablebutton);
    drawboardtablecenter.add(twentyseventhtilefromtablebutton);


    //Unteres Panel wird erzeugt.
    JPanel panelSouth = new JPanel();
    panelSouth.setBackground(backroundColor);

    //Teile des Borderlayouts werden mit Panels und Graphikelementen gefüllt.
    Container c = playingviewframe.getContentPane();
    c.add(panelUp, BorderLayout.NORTH);
    c.add(panelSouth, BorderLayout.SOUTH);
    c.add(drawboardplayerboardright, BorderLayout.EAST);
    c.add(drawboardplayerboardleft, BorderLayout.WEST);
    c.add(drawboardtablecenter, BorderLayout.CENTER);

    playingviewframe.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    playingviewframe.setSize(1300, 800);
    playingviewframe.setVisible(true);


    //ActionListeners Lines User 1
    firstrowu1button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    secondrowu1button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    thirdrowu1button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    fourthrowu1button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    fifthrowu1button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    floorlineu1button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });


    //ActionListeners Lines User 2
    firstrowu2button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    secondrowu2button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    thirdrowu2button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    fourthrowu2button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    fifthrowu2button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    floorlineu2button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    //ActionListeners Lines User 3
    firstrowu3button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    secondrowu3button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    thirdrowu3button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    fourthrowu3button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    fifthrowu3button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    floorlineu3button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });


    //ActionListeners Lines User 4
    firstrowu4button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    secondrowu4button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    thirdrowu4button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    fourthrowu4button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    fifthrowu4button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });
    floorlineu4button.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });


    //Buttons für Tiles aus Tables bekommen Actionlisteners:
    firsttilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    secondtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    thirdtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    fourthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    fifthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    sixthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    seventhtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    eightthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    ninethtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    tenthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    eleventhtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twelvethtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    thirteenthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    fourteenthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    fifteenthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    sixteenthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    seventeenthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    eighteenthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    nineteenthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentiethtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentyfirsttilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentysecondtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentythirdtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentyfourthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentyfifthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentysixthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentyseventhtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentyeightthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentyninethtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    thirtiethtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    thirtyfirsttilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    thirtysecondtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    thirtythirdtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    thirtyfourthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    thirtyfifthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    thirtysixthtilefromplatesbutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });


    //Buttons für Tiles aus Table bekommen Actionlisteners:
    firsttilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    secondtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    thirdtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    fourthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    fifthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    sixthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    seventhtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    eightthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    ninethtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    tenthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    eleventhtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twelvethtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    thirteenthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    fourteenthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    fifteenthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    sixteenthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    seventeenthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    eighteenthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    nineteenthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentiethtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentyfirsttilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentysecondtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentythirdtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentyfourthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentyfifthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentysixthtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    twentyseventhtilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });

    penaltytilefromtablebutton.addActionListener(new ActionListener() {
      @Override
      public void actionPerformed(ActionEvent e) {
      }
    });


  }
}

