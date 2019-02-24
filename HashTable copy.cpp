//============================================================================
// Name        : HashTable.cpp
// Author      : Jessica Kamman
// Version     : 1.0
//
//============================================================================

#include <algorithm>
#include <climits>
#include <iostream>
#include <string> // stoi
#include <time.h>

#include "CSVparser.hpp"

using namespace std;

//============================================================================
// Global definitions visible to all methods and classes
//============================================================================

const unsigned int DEFAULT_SIZE = 179;

// forward declarations
double strToDouble(string str, char ch);

// define a structure to hold bid information
struct Bid {
	string bidId; // unique identifier
	string title;
	string fund;
	double amount;
	Bid() {
		amount = 0.0;
	}
};

struct Node{
	Bid aBid;
	Node nextNode;
	Node(){
		aBid = NULL;
		nextNode= NULL;
	}

};
//============================================================================
// Hash Table class definition
//============================================================================

/**

 * implement a hash table with chaining.
 */
class HashTable {

private:
	// Define structures to hold bids


	vector<Node> bidsvector;

	unsigned int hash(int key);

public:
	HashTable();
	virtual ~HashTable();
	void Insert(Bid bid);
	void PrintAll();
	//added value to Print Fund
	void PrintFund(string searchTerm);
	void Remove(string bidId);
	Bid Search(string bidId);
};

/**
 * Default constructor
 */
HashTable::HashTable() {
	//  Initialize the structures used to hold bids

	bidsvector = vector<Node>();

}

/**
 * Destructor
 */
HashTable::~HashTable() {
	// Implement logic to free storage when class is destroyed
	//clear out the vectors
	bidsvector.clear();

}

/**
 * Calculate the hash value of a given key.
 * Note that key is specifically defined as
 * unsigned int to prevent undefined results
 * of a negative list index.
 *
 * @param key The key to hash
 * @return The calculated hash
 */
unsigned int HashTable::hash(int key) {
	// FIXME (4): Implement logic to calculate a hash value
	// change moduler to 100 to help scalability
	return key %100;

}

/**
 * Insert a bid
 *
 * @param bid The bid to insert
 */
void HashTable::Insert(Bid bid) {
	// Implement logic to insert a bid
	//convert string into integer
	unsigned int keynew = hash(std::stoi(bid.bidId));
	Node nodebid = Node();
	nodebid.aBid= bid;

	//space in the vector for the new node




	if(keynew<bidsvector.size()-1){

	}
	//resize if the vector is full
	else{
		bidsvector.resize(keynew);
	}

	//check if something is in the space
	if(bidsvector[keynew]==NULL){
		bidsvector[keynew]= nodebid; //happens if it is empty when we find it
	}
	else{
		//if there is something is in the space, add as linked list
		Node next = bidsvector[keynew];

		//find the tail
		while(next.nextNode != NULL){
			next = next.nextNode;
		}
		next.nextNode = nodebid;
	}
}

/**
 * Print all bids
 */
void HashTable::PrintAll() {
	// Implement logic to print all bids
	Node firstnode = NULL;
	for(int i=0; i<bidsvector.size()-1;i++){
		firstnode= bidsvector[i];
		if(firstnode==NULL){

		}
		else{
			while(firstnode !=NULL){
				displayBid(firstnode.aBid);
				firstnode =firstnode.nextNode;
			}


		}

	}
}
//new comand method to Print Department
void HashTable::PrintFund(string searchTerm) {
	Node firstnode = NULL;
	for (int i = 0; i < bidsvector.size() - 1; i++) {
		firstnode = bidsvector[i];
		if (firstnode == NULL) {

		}
		else {
			while (firstnode != NULL) {
				if (firstnode.aBid.fund == searchTerm) {
					 displayBid(firstnode.aBid);
				}
				firstnode = firstnode.nextNode;
			}
		}
	}

}


/**
 * Remove a bid
 *
 * @param bidId The bid id to search for
 */
void HashTable::Remove(string bidId) {
	// FIXME (7): Implement logic to remove a bid
	Node prevNode = NULL;
	Node firstnode = NULL;
	for(int i=0; i<bidsvector.size()-1;i++){
		firstnode= bidsvector[i];
		if(firstnode==NULL){

		}
		else{
			while(firstnode !=NULL){
				//check to see if the bid to remove has been found
				if(firstnode.aBid.bidId == bidId){

					//check whether we are at the beginning of the bucket
					if(prevNode ==NULL){
						bidsvector[i] = firstnode.nextNode;
					} else{
						prevNode.nextNode = firstnode.nextNode;
					}
					return;
				}

				prevNode = firstnode;
				firstnode =firstnode.nextNode;
			}


		}

	}
}


/**
 * Search for the specified bidId
 *
 * @param bidId The bid id to search for
 */
Bid HashTable::Search(string bidId) {
	Bid bid;

	// FIXME (8): Implement logic to
	bool bidFound =false;  //false, not found
	Node firstnode = NULL;
	for(int i=0; i<bidsvector.size()-1 && bidFound ==false;i++){
		firstnode= bidsvector[i];
		if(firstnode==NULL){

		}
		else{
			while(firstnode !=NULL && bidFound ==false){
				if(firstnode.aBid.bidId == bidId){
					bid=firstnode.aBid;
					bidFound = true;
				}

				firstnode =firstnode.nextNode;
			}


		}
		
		
	}



	return bid;
}

//============================================================================
// Static methods used for testing
//============================================================================

/**
 * Display the bid information to the console (std::out)
 *
 * @param bid struct containing the bid info
 */
void displayBid(Bid bid) {
	cout << bid.bidId << ": " << bid.title << " | " << bid.amount << " | "
			<< bid.fund << endl;
	return;
}

/**
 * Load a CSV file containing bids into a container
 *
 * @param csvPath the path to the CSV file to load
 * @return a container holding all the bids read
 */
void loadBids(string csvPath, HashTable* hashTable) {
	cout << "Loading CSV file " << csvPath << endl;

	// initialize the CSV Parser using the given path
	csv::Parser file = csv::Parser(csvPath);

	// read and display header row - optional
	vector<string> header = file.getHeader();
	for (auto const& c : header) {
		cout << c << " | ";
	}
	cout << "" << endl;

	try {
		// loop to read rows of a CSV file
		for (unsigned int i = 0; i < file.rowCount(); i++) {

			// Create a data structure and add to the collection of bids
			Bid bid;
			bid.bidId = file[i][1];
			bid.title = file[i][0];
			bid.fund = file[i][8];
			bid.amount = strToDouble(file[i][4], '$');

			//cout << "Item: " << bid.title << ", Fund: " << bid.fund << ", Amount: " << bid.amount << endl;

			// push this bid to the end
			hashTable->Insert(bid);
		}
	} catch (csv::Error &e) {
		std::cerr << e.what() << std::endl;
	}
}

/**
 * Simple C function to convert a string to a double
 * after stripping out unwanted char
 */
double strToDouble(string str, char ch) {
	str.erase(remove(str.begin(), str.end(), ch), str.end());
	return atof(str.c_str());
}

/**
 * The one and only main() method
 */
int main(int argc, char* argv[]) {

	// process command line arguments
	string csvPath, bidKey;
	switch (argc) {
	case 2:
		csvPath = argv[1];
		bidKey = "98109";
		break;
	case 3:
		csvPath = argv[1];
		bidKey = argv[2];
		break;
	default:
		csvPath = "eBid_Monthly_Sales_Dec_2016.csv";
		bidKey = "98109";
	}

	// Define a timer variable
	clock_t ticks;

	// Define a hash table to hold all the bids
	HashTable* bidTable;

	Bid bid;

	string input = "";
	int choice = 0;
	//modification: new value for searching
	string searchTerm = "";
	while (choice != 9) {
		cout << "Menu:" << endl;
		cout << "  1. Load Bids" << endl;
		cout << "  2. Display All Bids" << endl;
		cout << "  3. Find Bid" << endl;
		cout << "  4. Remove Bid" << endl;
		cout << "  9. Exit" << endl;
		cout << "Enter choice: ";
		//modification: change choice to input
		cin >> input;
		//modification: turn string value into an int
		choice= std::stoi(input.substr(0, input.find(' ')));
		//modification:
		if(input.find(' ') >= 1) {
			searchTerm = (input.substr(input.find(' ')));
		}




		switch (choice) {

		case 1:
			bidTable = new HashTable();

			// Initialize a timer variable before loading bids
			ticks = clock();

			// Complete the method call to load the bids
			loadBids(csvPath, bidTable);

			// Calculate elapsed time and display result
			ticks = clock() - ticks; // current clock ticks minus starting clock ticks
			cout << "time: " << ticks << " clock ticks" << endl;
			cout << "time: " << ticks * 1.0 / CLOCKS_PER_SEC << " seconds" << endl;
			break;

		case 2:
			bidTable->PrintAll();
			break;

		case 3:
			ticks = clock();
			//changed value from Bidkey to SearchTerm
			bid = bidTable->Search(searchTerm);

			ticks = clock() - ticks; // current clock ticks minus starting clock ticks

			if (!bid.bidId.empty()) {
				displayBid(bid);
			} else {
				cout << "Bid Id " << searchTerm << " not found." << endl;
			}

			cout << "time: " << ticks << " clock ticks" << endl;
			cout << "time: " << ticks * 1.0 / CLOCKS_PER_SEC << " seconds" << endl;
			break;

		case 4:
			bidTable->Remove(searchTerm);
			break;
		case 5:
			//to search for department Id
			bidTable->PrintFund(searchTerm);
			break;
			
		}
	}

	cout << "Good bye." << endl;

	return 0;
}

