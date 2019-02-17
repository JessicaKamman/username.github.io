/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package zookeeper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author jessicakammann
 */
public class Animal {
    String animal = "";
    String name = "";
    String age = "";
    String healthConcerns = "";
    String feeding = "";
    ArrayList<Sickness> sicknesses = new ArrayList<>(); 

 
    
    
    public Animal(){};
    // prints Animal information in order
    public void printAnimal(){
        System.out.println(this.animal);
        System.out.println(this.name);
        System.out.println(this.age);
        System.out.println(this.healthConcerns);
        System.out.println(this.feeding);
        //Enhancement: loop through Sickness and print
        for(Sickness sickness : sicknesses) {
        	System.out.println(sickness.toString());
        }
    }
    
    // provided information from file line by line fills the Class variables in order
    public void fillVariable(String line){
        if (line.contains("Animal")){
            animal = line;
        } else if (line.contains("Name")){
            name = line;
        } else if (line.contains("Age")){
            age = line;
        } else if(line.contains("Health")){
            healthConcerns = line;
        } else if (line.contains("Feeding")){
            feeding = line;
        } else if (line.contains("Sickness")) {
        	String[] sicknessArray = line.split(" ");
        	//this will give us an object that looks like this: Flu/none/1/11-02-2019/14-02-2019
        	for(int i=1; i<sicknessArray.length; i++) {
        		String[] values = sicknessArray[i].split("/");
        		Sickness sickness = new Sickness();
        		sickness.illnessName = values[0];
        		sickness.medicine = values[1];
        		sickness.vetVisits = values[2];

        		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
    			try {
    				sickness.dateStart =(Date) formatter.parse(values[3]);
    			} catch(Exception e) {
    				sickness.dateStart = null;
    			}
        		
    			try {
    				sickness.dateEnd =(Date) formatter.parse(values[4]);
    			} catch(Exception e) {
    				sickness.dateEnd = null;
    			}
    			
            	this.sicknesses.add(sickness);
        	}

        }
    }
    
    //if it has a problem in any variables returns true
    boolean hasAProblem() {
    	//Enhancement: For Loop to check if animal has Sickness
	    for(Sickness sickness : sicknesses) {
	    	if(sickness.isSick()) {
	    		return true;
	    	}
	    }
	    
       if (this.healthConcerns.contains("****") || this.animal.contains("****") || this.name.contains("****")|| this.age.contains("****")|| this.feeding.contains("****")){
           return true;
       }
       return false;
    }
}
