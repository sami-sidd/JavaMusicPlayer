//Sami Siddiqui  //501103508
import java.time.Year;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import javax.xml.namespace.QName;

/*
 * This class manages, stores, and plays audio content such as songs, podcasts and audiobooks. 
 */
public class Library
{
	private ArrayList<Song> 			songs; //song,  audiobook and playlists arraylists that are private and can only be accessed 
	private ArrayList<AudioBook> 	audiobooks;//by getters and setters
	private ArrayList<Playlist> 	playlists; 
	
  //private ArrayList<Podcast> 	podcasts;
	
	// Public methods in this class set errorMesg string 
	// Error Messages can be retrieved from main in class MyAudioUI by calling  getErrorMessage()
	// In assignment 2 we will replace this with Java Exceptions
	String errorMsg = "";//error msg string
	
	public Library()  //class constructor
	{
		songs 			= new ArrayList<Song>(); 
		audiobooks 	= new ArrayList<AudioBook>(); ;
		playlists   = new ArrayList<Playlist>();
	  //podcasts		= new ArrayList<Podcast>(); ;
	}
	/*
	 * Download audio content from the store. Since we have decided (design decision) to keep 3 separate lists in our library
	 * to store our songs, podcasts and audiobooks (we could have used one list) then we need to look at the type of
	 * audio content (hint: use the getType() method and compare to Song.TYPENAME or AudioBook.TYPENAME etc)
	 * to determine which list it belongs to above
	 * 
	 * Make sure you do not add song/podcast/audiobook to a list if it is already there. Hint: use the equals() method
	 * If it is already in a list, set the errorMsg string and return false. Otherwise add it to the list and return true
	 * See the video
	 */
	public void download(AudioContent content) 
	{
		if (content.getType().equals(Song.TYPENAME)) {
			// Check if the song already exists in the library
			if (songs.contains((Song)content)) {
				int x = songs.indexOf((Song)content);
				System.out.println("Song: "+songs.get(x).getTitle()+" already exists in library");//If content is already donwloaded then print this msg
				;
			} else {
				// Add the song to the library if doesnt exists and return true
				songs.add((Song)content);
			}
		}
			 else if (content.getType().equals(AudioBook.TYPENAME)) {
			// Check if the audiobook already exists in the library 
			if (audiobooks.contains((AudioBook)content)) {
				int x = audiobooks.indexOf(content);
				System.out.println("Audio book: "+audiobooks.get(x).getTitle() +" already exists in library");
			} else {
				// Add the audiobook to the library if it doesnt exits 
				audiobooks.add((AudioBook)content);
			}
		} else {
			// Invalid type
			throw new AudioContentNotFoundException("Invalid index");//throw exception if invalid type
		}
	}
	
	// Print Information (printInfo()) about all songs in the array list
	public void listAllSongs()
	{
		for (int i = 0; i < songs.size(); i++)
		{//used for loop to go through song lists and print and index and get printinfo method for every index  
			int index = i + 1;
			System.out.print("" + index + ". ");
			songs.get(i).printInfo();
			System.out.println();	
		}
	}
	
	// Print Information (printInfo()) about all audiobooks in the array list
	public void listAllAudioBooks()
	{
		for (int i = 0; i < audiobooks.size(); i++)
		{//used for loop to go through audiobook lists and print and index and get printinfo method for every index
			int index = i + 1;
			System.out.print("" + index + ". ");
			audiobooks.get(i).printInfo();
			System.out.println();	
		}
	}
	
  // Print Information (printInfo()) about all podcasts in the array list
	public void listAllPodcasts()
	{
		
	}
	
  // Print the name of all playlists in the playlists array list
	// First print the index number as in listAllSongs() above
	public void listAllPlaylists()
	{
		for (int i = 0; i < playlists.size(); i++)
		{//used for loop to go through playlist lists and print and index and get title method for every index
			int index = i + 1;
			System.out.println("" + index + ". "+playlists.get(i).getTitle());
		}
	}
	
  // Print the name of all artists. 
	public void listAllArtists()
	{
		// First create a new (empty) array list of string 
		// Go through the songs array list and add the artist name to the new arraylist only if it is
		// not already there. Once the artist arrayl ist is complete, print the artists names
	
		ArrayList<String> artistSet = new ArrayList<>();

    // Go through the songs array list and add the artist name to the set only if it is not already there
    	for (Song song : songs) {
			if(!artistSet.contains(song.getArtist())){
				artistSet.add(song.getArtist());
			}	
    }
		int i =0;
    // Once the artist set list is complete, print the artist names
    	for (String artist : artistSet) {
			i++;
        	System.out.println(i+": "+artist);
    	}
	}

	// Delete a song from the library (i.e. the songs list) - 
	// also go through all playlists and remove it from any playlist as well if it is part of the playlist
	public void deleteSong(int index)
	{
		if (index < 1 || index > songs.size()) {//checking if index is in list or invalid	
			throw new AudioContentNotFoundException("Audio content not found");//throws exception if invalid index
		} else {
			AudioContent songToDelete = songs.get(index - 1);//equating song that had to be deleted to songtodelete variable
			songs.remove(songToDelete);//removing it from the song list
			
			for (Playlist playlist : playlists) { //also going through playlists lists and checking if any playlist user made contains it
				if (playlist.getContent().contains(songToDelete)) {//and removing if from the playlist
					playlist.deleteContent(playlist.getContent().indexOf(songToDelete) + 1);
				}
			}
		}
    }
	
  //Sort songs in library by year
	public void sortSongsByYear()
	{
		// Use Collections.sort() 
		Collections.sort(songs, new SongYearComparator());//calling colleection method and pass song and the class as parameters
		
	
	}
  // Write a class SongYearComparator that implements
	// the Comparator interface and compare two songs based on year
	private class SongYearComparator implements Comparator<Song>
	{
		public int compare(Song a, Song b){
			if(a.getYear()>b.getYear()){//checking to see if song a year is greater then song b's 
				return 1;
			}
			else if(b.getYear()>a.getYear()){//checking to see if song b year is greater then song a's 
				return -1;
			}
			else{//returning 0 if neither is greater(=)
				return 0;
			}
		}
		
	}

	// Sort songs by length
	public void sortSongsByLength()
	{
	 // Use Collections.sort() 
	 Collections.sort(songs, new SongLengthComparator());
	}
  // Write a class SongLengthComparator that implements
	// the Comparator interface and compare two songs based on length
	private class SongLengthComparator implements Comparator<Song>
	{//doing same as sortbyyear method but with length of the song 
		public int compare(Song a, Song b){
			if(a.getLength()>b.getLength()){
				return 1;
			}
			else if(b.getLength()>a.getLength()){
				return -1;
			}
			else{
				return 0;
			}

		}
	}

	// Sort songs by title 
	public void sortSongsByName() 
	{
	  // Use Collections.sort()
		// class Song should implement the Comparable interface
		// see class Song code
		Collections.sort(songs); //implemented the comparable <song> interface to class song and passed parameter in .sort method.

	}

	
	
	/*
	 * Play Content
	 */
	
	// Play song from songs list
	public void playSong(int index)
	{//use if statement to check if the parameter index is within range in list songs
		//and calling .play method for that index and setting error msg if not within range
		if (index < 1 || index > songs.size())
		{
			throw new AudioContentNotFoundException("Audio content not found");//throw exception if index not found
		}
		else{
			songs.get(index-1).play();
		}
	}
	
	// Play podcast from list (specify season and episode)
	// Bonus
	public boolean playPodcast(int index, int season, int episode)
	{
		return false;
	}
	
	// Print the episode titles of a specified season
	// Bonus 
	public boolean printPodcastEpisodes(int index, int season)
	{
		return false;
	}
	
	// Play a chapter of an audio book from list of audiobooks
	public void playAudioBook(int index, int chapter)
	{//check the parameter index if its within range and getting select chap and passing parameter chapter in
		//also playing that chap by calling .play method for the index
		if (index < 1 || index > audiobooks.size())
		{
			throw new AudioContentNotFoundException("Chapter not found");//throw exception id index not found
		}
		else{
			audiobooks.get(index-1).selectChapter(chapter);
			audiobooks.get(index-1).play();
		}
	}
	
	// Print the chapter titles (Table Of Contents) of an audiobook
	// see class AudioBook
	public void printAudioBookTOC(int index)
	{//check the parameter index if its within range and calling printoc method on the index if its wihtin range
		if (index < 1 || index > audiobooks.size())
		{
			throw new AudioContentNotFoundException("Audio book not found");//throw exception id index not found
		}
		else{//call print toc for that audio content
			audiobooks.get(index-1).printTOC();
			
		}
		
	}
	
  /*
   * Playlist Related Methods
   */
	
	// Make a new playlist and add to playlists array list
	// Make sure a playlist with the same title doesn't already exist
	public void makePlaylist(String title)
	{//checing to see if the playlist list already contains a playlist with the title passed in parameter
		for (Playlist playlist : playlists) {
			if (playlist.getTitle().equals(title)) {
				errorMsg = "Playlist with the same title already exists";
				throw new AudioContentNotFoundException(errorMsg);//throw exception id index not found
			}
		}
		// If a playlist with the same title doesn't exist, create a new playlist and add it to the list
		Playlist newPlaylist = new Playlist(title);//creating new playlist
		playlists.add(newPlaylist);//adding it to playlist list
	}
	
	// Print list of content information (songs, audiobooks etc) in playlist named title from list of playlists
	public void printPlaylist(String title)
	{//using for loop to go through playlists list and checing to see if any playlist stored have the same title as in parameter
		for (Playlist playlist : playlists) {
			if (playlist.getTitle().equals(title)) {
				// If the playlist is found, print the songs in the playlist 
				playlist.printContents();
			}
			else{		// If the function reaches this point, it means that the playlist was not found
				errorMsg = "Playlist not found";
				throw new AudioContentNotFoundException(errorMsg);//throw exception if playlist not found
			}
		}

	}
	
	// Play all content in a playlist
	public void playPlaylist(String playlistTitle)
	{
		// Loop through the playlists list to find the playlist with the given title
		for (Playlist playlist : playlists) {
			if (playlist.getTitle().equals(playlistTitle)) {
				// If the playlist is found, play all the content in the playlist 
				playlist.playAll();
			}
			else{
				errorMsg = "Playlist not found";
				throw new AudioContentNotFoundException(errorMsg);
			}
		}
		
		// If the playlist is not found, set an error message and throw exception
		
	}
	
	// Play a specific song/audiobook in a playlist
	public void playPlaylist(String playlistTitle, int indexInPL)
	{// Loop through the playlists list to find the playlist with the given title
		for (Playlist playlist : playlists) {
			if (playlist.getTitle().equals(playlistTitle)) {
				playlist.play(indexInPL);//playing the index song from the playlist if the title is found
			}
			else{
				errorMsg = "Playlist not found";//error msg is set if playlist is found //throw exception
				throw new AudioContentNotFoundException(errorMsg);
			}
		}
		
		
	}
	
	// Add a song/audiobook/podcast from library lists at top to a playlist
	// Use the type parameter and compare to Song.TYPENAME etc
	// to determine which array list it comes from then use the given index
	// for that list
	public void addContentToPlaylist(String type, int index, String playlistTitle)
	{
		AudioContent content;
		//checking to see type 
    switch (type) {
        case Song.TYPENAME:
            content = songs.get(index-1);
            break;
        case AudioBook.TYPENAME:
            content = audiobooks.get(index-1);
            break;
        default:
            content=null;
    }
    //getting the playlst from the list of playlists and add the content to content list
    for (Playlist playlist : playlists) {
        if (playlist.getTitle().equals(playlistTitle)) {
            playlist.addContent(content);
        }else{
			throw new AudioContentNotFoundException("Playlist not found");//throw exception if playlist title not found
		}
    }
    
	}

  // Delete a song/audiobook/podcast from a playlist with the given title
	// Make sure the given index of the song/audiobook/podcast in the playlist is valid 
	public void delContentFromPlaylist(int index, String title)
	{//
		for (Playlist playlist : playlists) {//going through playlist list
			if (playlist.getTitle().equals(title)) {//if the title is found in the list of playlist
				if (index > 0 || index < playlist.getContent().size()) {//going through playlist content and checking if index is with in range
					playlist.deleteContent(index);//alling delcontent and deleting the content 
				}
			}
			else{
				throw new AudioContentNotFoundException("Playlist not found");//throw exception if playlist title not found
			}
		}
	}
}
class AudioContentNotFoundException extends RuntimeException {//custom excption which extends to runtime exception and takes in stringg as a parameter
    public AudioContentNotFoundException(String message) {
        super(message);
    }
}