package com.heronsanches.animalgame;

import com.heronsanches.animalgame.jframe.MainJF;
import javax.swing.JFrame;

/**
 *
 * @author Heron Sanches
 */
public class AnimalGame {
 
   
   public static void main(String args[]){
      
      MainJF mainJF = new MainJF();
      mainJF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
      mainJF.setSize(196, 92);
      mainJF.setVisible(true);
      
   }
   

}