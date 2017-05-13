package com.heronsanches.animalgame;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Heron Sanches
 */
public class Animal {

   private final String prop;
   private final String name;
   private final List<Animal> lAnimal;

   
   public Animal(String prop, String name) {
      
      this.prop = prop;
      this.name = name;
      this.lAnimal = new ArrayList<>();
      
   }

   
   public String getProp() {
      return prop;
   }

   public String getName() {
      return name;
   }

   public List<Animal> getlAnimal() {
      return lAnimal;
   }
      
   
}