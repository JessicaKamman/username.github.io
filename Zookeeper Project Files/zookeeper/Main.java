package zookeeper;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class Main {

	   public static void main(String[] args) throws IOException {
		   Zoo zoo = new Zoo();
	       //JOptionPane.showMessageDialog(null, "Alter Zookeeper Abnormal state");
	       //scanner class to read input from user
	       Scanner sc = new Scanner(System.in);
	       // buffered reader to read the file animal
	       //Enhancement: changed to include meaningful name
	       BufferedReader brAnimal=new BufferedReader(new FileReader("src/resources/animals.txt"));
	       // buffered reader to read habitats
	       //Enhancement: Changed to meaningful name
	       BufferedReader brHabitat=new BufferedReader(new FileReader("src/resources/habitats.txt"));
	       // to write to the file
	       BufferedWriter bwAnimal=new BufferedWriter(new FileWriter("src/resources/animals.txt",true));
	       BufferedWriter bwHabitat=new BufferedWriter(new FileWriter("src/resources/habitats.txt",true));
	       
    	   zoo.inputAnimals(brAnimal);
    	   zoo.inputHabitats(brHabitat);
    	   
	       // for option
	       int option=0;
	       // loop while user exists
	       int ch=0;
	       while(ch!=5) {
		       while(option!=3)
		       {	    	   
		           // prompting user
			       System.out.println("Enter 1 to monitor Animals 2 for Habitats 3 to Add Animals and Habitats");
			       option=sc.nextInt();
			       if(option==1)
			       {
			    	   //Enhancement: Method Call for ListAnimal
			    	   zoo.listAnimals();
		 
			       }
			       // For habitats same as above
			       else if (option==2)
			       {
			    	   
			    	   zoo.listHabitats();
		
			       }
			       // condition to exit
			       else if(option==3)
			           System.out.println("good bye");
			       else
			           System.out.println("Wrong option");
		       }
		       option=0;

		       System.out.println("Enter 1 to Add Animal 2 to add Habitat 3 add Sickness 4 return to top menu 5 to exit");
		       ch=sc.nextInt();
		       if(ch==1)
		       {
		    	   //Enhancement: Extracted simplified code in method call
		    	   zoo.addAnimal(bwAnimal, sc);
	
		          
		       }
		       if(ch==2)
		       {
		       //Enhancement: Extracted simplified code in method call for Habitats
		    	   zoo.addHabitat(bwHabitat, sc);
		          
		       }
		       if(ch==3)
		       {
		    	   //Enhancement: Add Sickness to txt file
		    	   zoo.addSickness(bwAnimal, sc);
		       }
	       }
	      
	    sc.close();
	    brAnimal.close();
	    brHabitat.close();
	    bwAnimal.close();
	    bwHabitat.close();
	   }
	
}
