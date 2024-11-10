//Sami Siddiqui  //501103508
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

// Simulation of a Simple Text-based Music App (like Apple Music)

public class MyAudioUI
{
	public static void main(String[] args) 
	{
		// Simulation of audio content in an online store
		// The songs, podcasts, audiobooks in the store can be downloaded to your mylibrary
		AudioContentStore store = new AudioContentStore();
		
		// Create my music mylibrary
		Library mylibrary = new Library();//creating new object

		Scanner scanner = new Scanner(System.in);//creating scanner
		System.out.print(">");

		// Process keyboard actions
		while (scanner.hasNextLine()){
		try{//USING TRY BLOCK TO CATCH CUSTOM MADE EXCEPTION
			String action = scanner.nextLine();

			if (action == null || action.equals("")) 
			{
				System.out.print("\n>");
				continue;
			}
			else if (action.equalsIgnoreCase("Q") || action.equalsIgnoreCase("QUIT"))
				return;//program stops if user enters q or quit
			
			else if (action.equalsIgnoreCase("SEARCH"))	// List all store
			{//id user types in "SEARCH" then sercha method is called from the AudioContentStore class
				System.out.print("Title: ");
                String title = scanner.next();
				store.search(title);
			}
			else if (action.equalsIgnoreCase("SEARCHA"))	
			{//id user types in "SEARCHA" then serchaA method is called from the AudioContentStore class
				System.out.print("Artist: ");
                String artist = scanner.nextLine();
				store.searchA(artist);
			}
			else if (action.equalsIgnoreCase("SEARCHG"))	
			{//id user types in "SEARCHG" then serchaG method is called from the AudioContentStore class
				System.out.print("Genre: ");
                String genre = scanner.nextLine();
				store.searchG(genre);
			}
				else if (action.equalsIgnoreCase("STORE"))	// List all store
			{//call list of store and display store content if user enters store
				store.listAll(); 
			}
			else if (action.equalsIgnoreCase("SONGS"))	// List all songs
			{//call list of songs and display songs if user enters songs
				mylibrary.listAllSongs(); 
			}
			else if (action.equalsIgnoreCase("BOOKS"))	// List all books
			{//call list of audiobooks and display books name content if user enters books
				mylibrary.listAllAudioBooks(); 
			}
			else if (action.equalsIgnoreCase("PODCASTS"))	// List all songs
			{
				mylibrary.listAllPodcasts(); 
			}
			else if (action.equalsIgnoreCase("ARTISTS"))	// List all artist
			{//call list of all artists and display artists content if user enters artists
				mylibrary.listAllArtists(); 
			}
			else if (action.equalsIgnoreCase("PLAYLISTS"))	// List all playlists
			{//call list of playlists and display playlist if user enters playlists
				mylibrary.listAllPlaylists(); 
			}
			// Download audiocontent (song/audiobook/podcast) from the store 
			// Specify the index of the content
			else if (action.equalsIgnoreCase("DOWNLOAD")) {
				//Takes in two inputs from user if user types "DOWNLOAD" and download all the content in b/w 
				//by using for loop and calling donwload mthod for every index in b/w
				int x = 0;
				int y = 0;
				System.out.print("from #: ");
				if (scanner.hasNextInt()) {
					x = scanner.nextInt();
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				System.out.print("To #: ");
				try {if (scanner.hasNextInt()) {//using try catch block to catch null pointer exception if user enters -ve num or num>12
					y = scanner.nextInt();
					scanner.nextLine(); // "consume" nl character (necessary when mixing nextLine() and nextInt())
				}
				for (int i = x; i <= y; i++) {
						AudioContent content = store.getContent(i);
						mylibrary.download(content); 
				}
					
				} catch (NullPointerException e) {//catching null pointer exception 
					System.out.print("NullPointerException caught");	
					e.printStackTrace();		}
				
			}
			else if (action.equalsIgnoreCase("DOWNLOADA")){
				//If user inters DOWNLOADA then code asks user to input the aritist name and downloads all its songs by calling
				//artist maps and getting the keys which are the indices of content on store that has to be downloaded
				String x;
				System.out.print("Artist: ");
				if (scanner.hasNext())
				{
					x = scanner.nextLine();
					//scanner.nextLine(); //
					if(store.artistToIndex.containsKey(x)){
						ArrayList<Integer> z = store.artistToIndex.get(x);
						for (int i=0; i<z.size(); i++){
							AudioContent content = store.getContent(z.get(i));
							mylibrary.download(content);
							if (content == null)
								System.out.println("Content Not Found in Store");
						}
					}
				}
			}
			else if (action.equalsIgnoreCase("DOWNLOADG")){
				//If user inters DOWNLOADF then code asks user to input the aritist name and downloads all its songs by calling
				//Genre maps and getting the keys which are the indices of content on store that has to be downloaded
				String x;
				System.out.print("Genre [POP, ROCK, JAZZ, RAP]: ");//user input for index of content
				if (scanner.hasNext())
				{
					x = scanner.nextLine();
					if(store.genreToIndex.containsKey(x)){
						ArrayList<Integer> z = store.genreToIndex.get(x);
						for (int i=0; i<z.size(); i++){
							AudioContent content = store.getContent(z.get(i));
							mylibrary.download(content);
							if (content == null)
								System.out.println("Content Not Found in Store");
						}
					}
				}
			}	
			// Get the *library* index (index of a song based on the songs list)
			// of a song from the keyboard and play the song 
			else if (action.equalsIgnoreCase("PLAYSONG")) 
			{// If the user enters "PLAYSONG",then have the user to enter the index of the song they want to play
            // Then, play the song if it exists in the library
				System.out.print("Song Number: ");
                int songIndex = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
                mylibrary.playSong(songIndex);
				
			}
			// Print the table of contents (TOC) of an audiobook that
			// has been downloaded to the library. Get the desired book index
			// from the keyboard - the index is based on the list of books in the library
			else if (action.equalsIgnoreCase("BOOKTOC")) 
			{// If the user enters "BOOKTOC",then have the user to enter the index of the audioobook they want to print
            // Then, print the audiobook if it exists in the library
				System.out.print("Audio Book Number #: ");
                int bTIndex = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
                mylibrary.printAudioBookTOC(bTIndex);
			}
			// Similar to playsong above except for audio book
			// In addition to the book index, read the chapter 
			// number from the keyboard - see class Library
			else if (action.equalsIgnoreCase("PLAYBOOK")) 
			{// If the user enters "PLAYBOOK",then have the user to enter the index of the audioobook they want to play
            // Then, play the audiobook if it exists in the library
				System.out.print("Audio Book Number #: ");
   			 	int bookIndex = scanner.nextInt();
    			scanner.nextLine(); // consume the newline character
    
    			System.out.print("Chapter Number #: ");
    			int chapterNum = scanner.nextInt();
    			scanner.nextLine(); // consume the newline character
                mylibrary.playAudioBook(bookIndex, chapterNum);
				
			}
			// Print the episode titles for the given season of the given podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PODTOC")) 
			{
				
			}
			// Similar to playsong above except for podcast
			// In addition to the podcast index from the list of podcasts, 
			// read the season number and the episode number from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPOD")) 
			{
				
			}
			// Specify a playlist title (string) ___________________________________________________________________
			// Play all the audio content (songs, audiobooks, podcasts) of the playlist 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYALLPL")) 
			{//if user enters "PLAYALLPL" ask user for playlist title and then playall all content in the playlist if exists
			//throws exception if content does not exists
				System.out.print("Playlist Title: ");
   			 	String title = scanner.next();
    			scanner.nextLine(); // consume the newline character
        		System.out.println();             
				mylibrary.playPlaylist(title);      //play all playlist content 
    			
			}
			// Specify a playlist title (string) 
			// Read the index of a song/audiobook/podcast in the playist from the keyboard 
			// Play all the audio content 
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("PLAYPL")) 
			{//if user enters "PLAYPL" ask user for playlist title and index of song/audiobook then play content in the playlist
			//by calling playplaylist method amd passing index and title as parameter
				System.out.print("Playlist Title: ");
   			 	String title = scanner.next();
    			scanner.nextLine(); // consume the newline character
    
    			System.out.print("Content Number #: ");
    			int cNum = scanner.nextInt();
    			scanner.nextLine(); // consume the newline character
    			mylibrary.playPlaylist(title, cNum);
			}
			// Delete a song from the list of songs in mylibrary and any play lists it belongs to
			// Read a song index from the keyboard
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("DELSONG")) 
			{//if user types "DELSONG" ask user for the index of song to delete and call deletesong method for the index
				System.out.print("Library song #: ");
                int delNum = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
                mylibrary.deleteSong(delNum);
			}
			// Read a title string from the keyboard and make a playlist
			// see class Library for the method to call
			else if (action.equalsIgnoreCase("MAKEPL")) 
			{//if user types "MAKEPL" ask user for the title of playplist to be created and call makeplaylist method 
				System.out.print("Playlist Title: ");
                String mPlTitle = scanner.next();
                scanner.nextLine(); // consume the newline character
                mylibrary.makePlaylist(mPlTitle);
			}
			// Print the content information (songs, audiobooks, podcasts) in the playlist
			// Read a playlist title string from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("PRINTPL"))	// print playlist content
			{//if user types "PRINTPL" ask user for the title of playlist and call printplaylist method for the index
				System.out.print("Playlist Title: ");
                String plTitle = scanner.next();
                scanner.nextLine(); // consume the newline character
                mylibrary.printPlaylist(plTitle);
			}
			// Add content (song, audiobook, podcast) from mylibrary (via index) to a playlist
			// Read the playlist title, the type of content ("song" "audiobook" "podcast")
			// and the index of the content (based on song list, audiobook list etc) from the keyboard
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("ADDTOPL")) 
			{//if user types "ADDTOPL" ask user for the title of playlist and the index of the song/book and type song or book 
				System.out.print("Playlist Title: ");
                String aPlTitle = scanner.next();
                scanner.nextLine(); // consume the newline character
                System.out.print("[SONG, AUDIOBOOK]: ");
                String cType = scanner.next();
				cType = cType.toUpperCase();
                scanner.nextLine();
				System.out.print("Library content #: ");
                int lbContent = scanner.nextInt();
                scanner.nextLine();
				try{//using try catch block to catch index out of bound exception
					mylibrary.addContentToPlaylist(cType, lbContent, aPlTitle);				
				}catch(IndexOutOfBoundsException e){
					System.out.println("Error: "+e.getMessage());
					e.printStackTrace();

				}
			}
				
			
			// Delete content from play list based on index from the playlist
			// Read the playlist title string and the playlist index
		  // see class Library for the method to call
			else if (action.equalsIgnoreCase("DELFROMPL")) 
			{//if user types "DELFROMPL" ask user for the title of playlist and the index of the song/book 
				System.out.print("Playlist Title: ");
				String dPlTitle = scanner.next();
                scanner.nextLine();
				System.out.print("Playlist content#: ");
                int delNumPl = scanner.nextInt();
                scanner.nextLine(); // consume the newline character
				mylibrary.delContentFromPlaylist(delNumPl, dPlTitle);
			}
			
			else if (action.equalsIgnoreCase("SORTBYYEAR")) // sort songs by year
			{//if user enters "SORTBYYEAR" call the sortbyyear method
				mylibrary.sortSongsByYear();
			}
			else if (action.equalsIgnoreCase("SORTBYNAME")) // sort songs by name (alphabetic)
			{//if user enters "SORTBYNAME" call the sortbyname method
				mylibrary.sortSongsByName();
			}
			else if (action.equalsIgnoreCase("SORTBYLENGTH")) // sort songs by length
			{//if user enters "SORTBYLENGTH" call the sortbylength method
				mylibrary.sortSongsByLength();
			}

			System.out.print("\n>");
		}catch (AudioContentNotFoundException e) {//catching the custom made exception
			System.err.println("Error: " + e.getMessage());
			e.printStackTrace();
		} 
		}
	}
}
