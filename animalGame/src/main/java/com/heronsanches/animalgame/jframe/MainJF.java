package com.heronsanches.animalgame.jframe;

import com.heronsanches.animalgame.Animal;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

/**
 *
 * @author Heron Sanches
 */
public class MainJF extends JFrame implements ActionListener{
   
   private static final String ANIMALS = "Animais";
   private final String CONGRATULATIONS = "Acertei de novo!";
   private final String CONGRATULATIONS_TITLE = "Jogodosanimais";
   private final String LABEL_MAIN = "Pense em um animal";
   private final String OK = "OK";
   private final String LEAVE = "Desisto";
   private final String CONFIRM = "Confirm";
   private final String COMPLETE = "Complete";
   private final String COMPLETE_MSG_1 = "Um(a) ";
   private final String COMPLETE_MSG_2 = " _____ mas um(a) ";
   private final String COMPLETE_MSG_3 = " não.";
   private final String QUESTION_ANIMAL = "Qual o animal que você pensou?";
   private final String QUESTION_1 = "O animal que você pensou vive na água?";
   private final String QUESTION_1_DEFAULT = "O animal que você pensou ";
   
   private final JLabel labelMain;
   private final JButton btnMain;
   
   private final Animal animalNoWater;
   private final Animal animalWater;


   public MainJF() throws HeadlessException {

      super(MainJF.ANIMALS);
      
      animalNoWater = new Animal("é Macaco", "Macaco");
      animalWater = new Animal("é Tubarão", "Tubarão");

      try {
         
         List<Image> lImage = new ArrayList<>();
         lImage.add(ImageIO.read(getClass().getResource("/imgs/icon_16.png")));
         lImage.add(ImageIO.read(getClass().getResource("/imgs/icon_32.png")));
         setIconImages(lImage);
         
      } catch (IOException ex) {
         Logger.getLogger(MainJF.class.getName()).log(Level.SEVERE, null, ex);
      }
      
      setLayout(new FlowLayout());
      Dimension screenSize = new Dimension(Toolkit.getDefaultToolkit().getScreenSize());
      
      //top left (x,y) position
      setLocation(Integer.valueOf(String.valueOf(screenSize.width*0.15).split("\\.")[0]), Integer.valueOf(String.valueOf(screenSize.height * 0.2).split("\\.")[0]));
      
      labelMain = new JLabel(LABEL_MAIN);
      add(labelMain);
      
      btnMain = new JButton(OK);
      btnMain.addActionListener(this);
      add(btnMain);
      
   }

   
   @Override
   public void actionPerformed(ActionEvent ae) {
      
      if(ae.getSource() == btnMain){
            
         int res = JOptionPane.showConfirmDialog(null, QUESTION_1, CONFIRM, JOptionPane.YES_NO_OPTION);

         if(res == 0){ //lives on water
            
            if(animalWater.getlAnimal().isEmpty()){
            
               res =  JOptionPane.showConfirmDialog(null, QUESTION_1_DEFAULT+animalWater.getProp()+"?", CONFIRM, JOptionPane.YES_NO_OPTION);
               
               if(res == 0){

                  UIManager.put("OptionPane.minimumSize",new Dimension(109, 117));
                  JOptionPane.showMessageDialog(null, CONGRATULATIONS, CONGRATULATIONS_TITLE, JOptionPane.PLAIN_MESSAGE);

               }else{
                  
                  String aniName = JOptionPane.showInputDialog(null, QUESTION_ANIMAL, LEAVE, JOptionPane.PLAIN_MESSAGE);
                  String aniProp = JOptionPane.showInputDialog(null, 
                     COMPLETE_MSG_1+aniName+COMPLETE_MSG_2+animalWater.getName()+COMPLETE_MSG_3, 
                     COMPLETE, JOptionPane.PLAIN_MESSAGE);
                  
                  Animal a = new Animal(aniProp, aniName);
                  animalWater.getlAnimal().add(a);
                  
               }
               
            }else{ // !animalWater.getlAnimal().isEmpty()
               constructDialogs(animalWater);
            }
                           
         }else{ //no lives on water
            
           if(animalNoWater.getlAnimal().isEmpty()){
            
               res =  JOptionPane.showConfirmDialog(null, QUESTION_1_DEFAULT+animalNoWater.getProp()+"?", CONFIRM, JOptionPane.YES_NO_OPTION);
               
               if(res == 0){

                  UIManager.put("OptionPane.minimumSize",new Dimension(109, 117));
                  JOptionPane.showMessageDialog(null, CONGRATULATIONS, CONGRATULATIONS_TITLE, JOptionPane.PLAIN_MESSAGE);

               }else{
                  
                  String aniName = JOptionPane.showInputDialog(null, QUESTION_ANIMAL, LEAVE, JOptionPane.PLAIN_MESSAGE);
                  String aniProp = JOptionPane.showInputDialog(null, 
                     COMPLETE_MSG_1+aniName+COMPLETE_MSG_2+animalNoWater.getName()+COMPLETE_MSG_3, 
                     COMPLETE, JOptionPane.PLAIN_MESSAGE);
                  
                  Animal a = new Animal(aniProp, aniName);
                  animalNoWater.getlAnimal().add(a);
                  
               }
               
            }else{ // !animalNoWater.getlAnimal().isEmpty()
               constructDialogs(animalNoWater);
            }
            
         }
            
      }
      
   }
   
   
   /**This a recursive function that constructs the needed dialogs and populates the needed data to the game.*/
   private void constructDialogs(Animal animal){
      
      Iterator<Animal> itA = animal.getlAnimal().iterator();
      int res = -1;
               
      while(itA.hasNext()){

         Animal a = itA.next();
         res =  JOptionPane.showConfirmDialog(null, QUESTION_1_DEFAULT+a.getProp()+"?", CONFIRM, JOptionPane.YES_NO_OPTION);

         if(res == 0){ //yes

            if(a.getlAnimal().size() > 0){
               
               constructDialogs(a);
               return;
               
            }
            
            res =  JOptionPane.showConfirmDialog(null, QUESTION_1_DEFAULT+"é "+a.getName()+"?", CONFIRM, JOptionPane.YES_NO_OPTION);

            if(res == 0){

               UIManager.put("OptionPane.minimumSize", new Dimension(109, 117));
               JOptionPane.showMessageDialog(null, CONGRATULATIONS, CONGRATULATIONS_TITLE, JOptionPane.PLAIN_MESSAGE);
               return;
               
            }else{

               String aniName = JOptionPane.showInputDialog(null, QUESTION_ANIMAL, LEAVE, JOptionPane.PLAIN_MESSAGE);
               String aniProp = JOptionPane.showInputDialog(null, 
                  COMPLETE_MSG_1+aniName+COMPLETE_MSG_2+a.getName()+COMPLETE_MSG_3, 
                  COMPLETE, JOptionPane.PLAIN_MESSAGE);

               Animal aAux = new Animal(aniProp, aniName);
               a.getlAnimal().add(aAux);
               return;
               
            }

         }else{

            if(itA.hasNext()){
               continue;
            }else{
               
               res =  JOptionPane.showConfirmDialog(null, QUESTION_1_DEFAULT+"é "+animal.getName()+"?", CONFIRM, JOptionPane.YES_NO_OPTION);
               
               if(res == 0){
                  
                  UIManager.put("OptionPane.minimumSize", new Dimension(109, 117));
                  JOptionPane.showMessageDialog(null, CONGRATULATIONS, CONGRATULATIONS_TITLE, JOptionPane.PLAIN_MESSAGE);
                  return;
                  
               }else{
                  
                  String aniName = JOptionPane.showInputDialog(null, QUESTION_ANIMAL, LEAVE, JOptionPane.PLAIN_MESSAGE);
                  String aniProp = JOptionPane.showInputDialog(null, 
                     COMPLETE_MSG_1+aniName+COMPLETE_MSG_2+animal.getName()+COMPLETE_MSG_3, 
                     COMPLETE, JOptionPane.PLAIN_MESSAGE);

                  Animal aAux = new Animal(aniProp, aniName);
                  animal.getlAnimal().add(aAux);
                  
               }
               
            }

         }

      }
      
   }

   
}