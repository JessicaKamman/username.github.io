/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zookeeper;

/**
 *
 * @author jessicakammann
 */
public class Habitat {
    String habitat = "";
    String temperature = "";
    String food = "";
    String cleanliness = "";
    
    public Habitat() {}

    // prints Habitat information in order
    public void printHabitat (){
        System.out.println(this.habitat);
        System.out.println(this.temperature);
        System.out.println(this.food);
        System.out.println(this.cleanliness);
    }
  
    // provided information from file line by line fills the Class variables in order
    public void fillVariable(String line){
    	//Enhancements: check input string to see what variable is being set
    	  if (line.contains("Habitat")){
              habitat = line;
          } else if (line.contains("Temperature")){
              temperature = line;
          } else if (line.contains("Diet")){
              food = line;
          } else if(line.contains("Cleanliness")){
              cleanliness = line;
          }
    }
    //create has a problem condition
    Boolean hasAProblem() {
         if (this.habitat.contains("****") || this.temperature.contains("****") || this.food.contains("****")|| this.cleanliness.contains("****")){
           return true;
       }
       return false;
    }
    
}