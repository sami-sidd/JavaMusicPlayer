//Sami Siddiqui  //501103508
import java.util.ArrayList;

import javax.print.attribute.standard.PrinterInfo;

/*
 * A Playlist contains an array list of AudioContent (i.e. Song, AudioBooks, Podcasts) from the library
 */
public class Playlist
{
	private String title;
	private ArrayList<AudioContent> contents; // songs, books, or podcasts or even mixture
	
	public Playlist(String title)
	{
		this.title = title; //seting title to parameter
		contents = new ArrayList<AudioContent>();//initalizing arraylists
	}
	//getters ans setters
	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void addContent(AudioContent content)
	{
		contents.add(content);
	}
	
	public ArrayList<AudioContent> getContent()
	{
		return contents;
	}

	public void setContent(ArrayList<AudioContent> contents)
	{
		this.contents = contents;
	}
	
	/*
	 * Print the information of each audio content object (song, audiobook, podcast)
	 * in the contents array list. Print the index of the audio content object first
	 * followed by ". " then make use of the printInfo() method of each audio content object
	 * Make sure the index starts at 1
	 */
	public void printContents()
	{
		
		for (int i = 0; i < contents.size(); i++) {//looping through content arraylist
			System.out.print(i + 1 + ". "); //print the index
			contents.get(i).printInfo();//and calling the printinfo methhod for every index of content list
			System.out.println();
		}
	}

	// Play all the AudioContent in the contents list
	public void playAll()
	{
		for(int i =0; i<getContent().size(); i++){//using for loop and using getter method of content list 
			System.out.println("\n");
			getContent().get(i).play(); //and calling play method for every index of content list and playing evry content in the playlist
		}
	}
	
	// Play the specific AudioContent from the contents array list.
	// First make sure the index is in the correct range. 
	public void play(int index)
	{
		if (index <= 0 || index > getContent().size()){
			System.out.println("Invalid choice");// if the if condition is met then the play method is called for the "index" 
		}
		else{
			getContent().get(index-1).play();
		}
	}
	
	public boolean contains(int index)
	{
		return index >= 1 && index <= contents.size();
	}
	
	// Two Playlists are equal if their titles are equal
	public boolean equals(Object other)
	{
		Playlist otherCon = (Playlist) other;
		return title.equals(otherCon.title);//checking and returing boolean value t/f if two playlists are equal
	}
	
	// Given an index of an audio content object in contents array list,
	// remove the audio content object from the array list
	// Hint: use the contains() method above to check if the index is valid
	// The given index is 1-indexed so convert to 0-indexing before removing
	public void deleteContent(int index)
	{
		if (!contains(index)){ 
			System.out.println("Invalid Choice");//checking to see if the content list contains the index and then removing it
		}                                          // from the content list
		else{
			contents.remove(index-1);
		}
	}
	
	
}
