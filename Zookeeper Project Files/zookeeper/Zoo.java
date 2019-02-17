package zookeeper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JOptionPane;

public class Zoo {
	
	ArrayList<Animal> animals = new ArrayList<Animal>();
	ArrayList<Habitat> habitats = new ArrayList<Habitat>();
	
	public Zoo() {}
	
	public void inputAnimals(BufferedReader brListAnimal) throws IOException {
		//String for line
        String line = brListAnimal.readLine();
      
        // loop through file
        while(line!=null)
        {  
            // checking blank line
            if(line.equals(""))
            {
                // add a new Animal to the list
                animals.add(new Animal());
            } else {
                // loading list to array
                animals.get(animals.size()-1).fillVariable(line);
            } 
            line = brListAnimal.readLine();
        }
		
	}
	
	//Enhancement: Add method for add Animal
	public void addAnimal(BufferedWriter bwAddAnimal, Scanner sc) throws IOException {
		String line = "";

		Animal animal = new Animal();
		bwAddAnimal.newLine();
		
        System.out.println("Enter animal type:");
        line = "Animal - " + sc.next();
        animal.animal = line;
        bwAddAnimal.write(line);
        
        System.out.println("Enter animal name:");
        line = "Name: " + sc.next();
        animal.name = line;
        bwAddAnimal.write(line);
        
        System.out.println("Enter age:");
        line = "Age: " + sc.next();
        animal.age = line;
        bwAddAnimal.write(line);
        
        System.out.println("Enter health Concerns: ");
        line = "Health concerns: " + sc.next();
        animal.healthConcerns = line;
        bwAddAnimal.write(line);
        
        System.out.println("Enter Feeding schedule: ");
        line = "Feeding schedule: " + sc.next();
        animal.feeding = line;
        bwAddAnimal.write(line);
		
	}
	//Enhancement: Add addSickness functionality
	public void addSickness(BufferedWriter bwAddSickness, Scanner sc) {
		String line= "";
		
		System.out.println("Enter Animal Name to add sickness to: ");
		line = sc.next();
		Animal animal = null;
		for(Animal animalToCheck : animals) {
			if(animalToCheck.name.contains(line)) {
				animal = animalToCheck;
			}
		}
		if(animal == null) {
			System.out.println("There is no animal with name: " + line);
			return;
		}
		
		Sickness sickness = new Sickness();
		
		//String illnessName
		System.out.println("Enter illnessName: ");
		line= sc.next();
		sickness.illnessName =line;
		
		//String medicine
		System.out.println("Enter Medicine: ");
		line= sc.next();
		sickness.medicine =line;
		
		//String vetVisits
		System.out.println("Enter Vet Visits: ");
		line= sc.next();
		sickness.vetVisits =line;
		
		DateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		//Date dateStart
		Boolean startDateProvided = false;
		while(!startDateProvided) {
			System.out.println("From: (DD-MM-YYY)");
			line= sc.next();
			try {
				sickness.dateStart =(Date) formatter.parse(line);
				startDateProvided = true;
			} catch(Exception e) {
				System.out.println("That was an invalid Date. Please try again.");
			}
		}
        
		//Date dateEnd
		Boolean endDateProvided = false;
		while(!endDateProvided) {
			System.out.println("To: (DD-MM-YYY)");
			line= sc.next();
			try {
				sickness.dateEnd =(Date) formatter.parse(line);
				endDateProvided = true;
			} catch(Exception e) {
				sickness.dateEnd =null;
				endDateProvided = true;
			}
		}
		
		animal.sicknesses.add(sickness);
	}
	
	//Enhancement: Declared method for listing Animals
	public void listAnimals() throws IOException {		
		
        System.out.println("List of animals");
        System.out.println();
        //Enhancement: Check if there are animals on the list
        if(animals.isEmpty()) {
        	System.out.println("No Animals Found");
        return;
        }
        
		// printing the info
        for(Animal animal : animals) {
            animal.printAnimal();
            if(animal.hasAProblem()) {
            	// Alert box
                JOptionPane.showMessageDialog(null, "Alter Zookeeper Abnormal state");
            }
        }

        System.out.println();
	}
	
	public void inputHabitats(BufferedReader brHabitat) throws IOException {
		// Enhancement: Declare inputHabitat method
		//String for line
        String line = brHabitat.readLine();
      
        // loop through file
        while(line!=null)
        {
            // checking blank line
            if(line.equals("")) {
                // add a new Animal to the list
                habitats.add(new Habitat());
            } else {
                // loading list to array
                habitats.get(habitats.size()-1).fillVariable(line);
            } 
            line = brHabitat.readLine();
        }		
	}
		
	
	
	//Enhancement: Add method for add Habitat
	public void addHabitat(BufferedWriter bwAddHabitat, Scanner sc) throws IOException {
		String line= "";
		Habitat habitat = new Habitat();
		bwAddHabitat.newLine();
		//Enhancement: To store in memory and save on file
        System.out.println("Enter Habitat name:");
        line = "Habitat - " + sc.next();
        habitat.habitat = line;
        bwAddHabitat.write(line);
        
        System.out.println("Enter Habitat Temperature:");
        line = "Temperature: " + sc.next();
        habitat.temperature = line;
        bwAddHabitat.write(line);
        
        System.out.println("Enter Diet:");
        line = "Diet: " + sc.next();
        habitat.food = line;
        bwAddHabitat.write(line);
        
        System.out.println("Enter Cleanliness: ");
        line = "Cleanliness: " + sc.next();
        habitat.cleanliness = line;
        bwAddHabitat.write(line);
        
        habitats.add(habitat);
        
       
		
	}
	
	
	//Enhancement: declare method for listing Habitat
	public void listHabitats() throws IOException{
		//Enhancement: convert to use in memory habitats
        System.out.println("List of habitats");
        System.out.println();
      //Enhancement: Check if there are habitats on the list
        if(habitats.isEmpty()) {
        	System.out.println("No Habitats Found");
        	System.out.println();
        return;
        }
        
		// printing the info
        for(Habitat habitat : habitats) {
            habitat.printHabitat();
            if(habitat.hasAProblem()) {
            	// Alert box
                JOptionPane.showMessageDialog(null, "Alter Zookeeper Abnormal state");
            }
        }

        System.out.println();
	}

}