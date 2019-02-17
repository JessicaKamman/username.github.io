package zookeeper;

import java.util.Date;

//Enhancement: Establish object for Sickness
public class Sickness{
String illnessName = "";
String medicine ="";
String vetVisits= "";
Date dateStart = null;
Date dateEnd = null;

public Boolean isSick() {
	if(dateEnd==null) {
		return true; 	
	}
	else {
		return false;
	}
}

//Enhancement: Override String method to write to the council
@Override
public String toString() {
	String stringDateEnd = "Current";
	if(dateEnd != null) {
		stringDateEnd = dateEnd.toString();
	}
	return "Ilness Name: " 
			+ illnessName 
			+ " Medicine Used: " 
			+ medicine 
			+ " Number of Vet Visits: " 
			+ vetVisits 
			+ " From: " 
			+ dateStart.toString() 
			+ " To: " 
			+ stringDateEnd;
}

}




