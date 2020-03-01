package Assignments;

import java.util.*;
import java.io.*;

/*
 * Class Main fills the 2D array myList with data from the csv file
 * the While loop reads in a line from the csv file which is split into an array called rows by commas
 * the line is edited three times with replaceAll() to remove all extra commas so the data is split correctly
 * the rows array is copied into each row in the myList array
 * ArtistList is the sorted linked list that myList is turned into
 * the Artist class represents each node in the linked list
 * the insert() inserts data from myList into ArtistList lexicographically with compareTo()
 * Finally the data in ArtistList is printed out an output file called out.txt
 * For some reason my computer can't read in data past line 81 and only returns null, however I tested the program with individual rows
 * in myList and they are sorted correctly into ArtistList, can you give me feedback for this please?
 */

public class Main {
      public static void main(String[] args) throws FileNotFoundException {
	    File myFile = new File("C:\\Users\\nickb\\eclipse-workspace\\CISC3130\\src\\Assignments\\regional-us-weekly-2020-01-17--2020-01-24.csv");
	    File output = new File("C:\\Users\\nickb\\Desktop\\out.txt");
	    PrintWriter outputfile = new PrintWriter(output);
	    Scanner cFile = new Scanner(myFile);
	    int i = 0;
	    String[][] myList = new String[200][5];
	    TopStreamingArtists ArtistList = new TopStreamingArtists();
	    System.out.println(ArtistList.isEmpty());
	    while (cFile.hasNext()) {
	      String line = cFile.nextLine();
	      String line2 = line.replaceAll(", ", " ");
	      String line3 = line2.replaceAll(",0", "0");
	      String[] rows = line3.split(",");
	      for (int w = 0; w < rows.length; w++) {	    	  
	    	  myList[i][w] = rows[w];
	      }
	      i++;
	    }
	    for (int y = 2; y < myList.length; y++) {
	    	ArtistList.insert(myList[y][2], myList[y][1], myList[y][3], myList[y][4]);
	    	System.out.println(myList[y][2] + " " + myList[y][1] + " " + myList[y][3] + " " + myList[y][4]);
	    	//individual tests due to the IDE not reading past 81 and causing the NullPointerException:
	    	//ArtistList.insert(myList[5][2], myList[5][1], myList[5][3], myList[5][4]);
	    	//ArtistList.insert(myList[9][2], myList[9][1], myList[9][3], myList[9][4]);
	    	//ArtistList.insert(myList[50][2], myList[50][1], myList[50][3], myList[50][4]);

	    }
	    ArtistList.displayList(outputfile);
	    cFile.close();
        outputfile.close();
      }
}
/*
 * Artist class represents a node in the ArtistList linked list
 * name represents the artist name
 * song is the artist song
 * stream is the stream number
 * link is the spotify link
 * displayData() prints output to a textfile
 * next points to the next node in the linked list
 * There are getters and setters for name and next
 */
class Artist {
  private String name;
  private String song;
  private String stream;
  private String link;
  private Artist next;
  Artist(String n, String so, String str, String l) {
    name = n;
    song = so;
    link = l;
    stream = str;
    next = null;
  }
  public void displayData(PrintWriter outputFile) {
	  outputFile.println(name + " " + song + " " + stream + " " + link);
  }
  public void setName(String n) {
    name = n;
  }
  public String getName() {
	  return name;
  }
  public void setNext(Artist nx) {
    next = nx;
  }
  public Artist getNext() {
	  return next;
  }
}

/*
 * TopStreamingArtists is the linked list class
 * first represents the first Artist node in the list
 * the constructor sets first to null by default
 * isEmpty() tests if the list is empty
 * There is a setter for first
 * insert creates Artist nodes with strings from myList and inserts them into the linked list
 * the linked list is sorted lexicographically with compareTo()
 * displayList() uses a while loop that prints each node and points to the next one
 */
class TopStreamingArtists {
    private Artist first;
    public TopStreamingArtists(){
      first = null;
    }
    public boolean isEmpty(){
     return (first == null);
    }
    public void setFirst(Artist x) {
      first = x;
    }
    public void insert(String n, String so, String str, String l) {
    	Artist newArtist = new Artist(n, so, str, l);
    	Artist current = first;
    	Artist previous = null;
    	while(current != null && n.compareTo(current.getName()) > 0) {
    		previous = current;
    		current = current.getNext();
    	}
    	if(previous == null) {
    		first = newArtist;
    	}
    	else {
    		previous.setNext(newArtist);
    	}
    	newArtist.setNext(current);
    }
    public void displayList(PrintWriter outputFile) {
    	Artist current = first;
    	while(current != null) {
    		current.displayData(outputFile);
    		current = current.getNext();
    	}
    }
}
 
 

