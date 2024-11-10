//Sami Siddiqui  //501103508
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
// Simulation of audio content in an online store
// The songs, podcasts, audiobooks listed here can be "downloaded" to your library


import javax.sound.midi.Soundbank;


public class AudioContentStore
{
        private ArrayList<AudioContent> contents;
        private Map<String, Integer> titleToIndex;
        public  Map<String, ArrayList<Integer>> artistToIndex;
        public  Map<String, ArrayList<Integer>> genreToIndex;
       
        public AudioContentStore()
        {
            contents = new ArrayList<AudioContent>();
            titleToIndex = new HashMap<>();//maps for song title and their index
            artistToIndex = new HashMap<>();//maps for artists and their song index
            genreToIndex = new HashMap<>();//maps for genre and their song index
            try {
                this.contents = readContentsFromFile();
            } catch (IOException e) {
                System.err.println("Error reading from file: " + e.getMessage());
                System.exit(1);
            }
          // Create some songs audiobooks and podcasts and to store
        //genre named arryalists which stores song indices 
          ArrayList<Integer> pop = new ArrayList<>();
          ArrayList<Integer> rap = new ArrayList<>();
          ArrayList<Integer> rock = new ArrayList<>(); 
          ArrayList<Integer> jazz = new ArrayList<>();
        //artist nnamed arryalists which stores song indices 
          ArrayList<Integer> cara = new ArrayList<Integer>();
          ArrayList<Integer> dapaah = new ArrayList<Integer>();
          ArrayList<Integer> chris = new ArrayList<Integer>();
          ArrayList<Integer> coldplay = new ArrayList<Integer>();
          ArrayList<Integer> beatles = new ArrayList<>();
          ArrayList<Integer> dragon = new ArrayList<Integer>();
          ArrayList<Integer> nina = new ArrayList<Integer>();

            //storing songs names as keys and their indices as values in titleTOindex map
            titleToIndex.put("Yesterday", 1);
            //adding indices in artist named arraylist and storing them in artistToIndex map
            beatles.add(1);
            artistToIndex.put("The Beatles", beatles);
            //adding indices in genre arraylist and adding them in genreToIndex map
            pop.add(1);
            genreToIndex.put("POP", pop);
           
            titleToIndex.put("Here", 2);
            cara.add(2);
            artistToIndex.put("Alessia Cara",cara);
            pop.add(2);
            genreToIndex.put("POP", pop);
           
            titleToIndex.put("Man's Not Hot", 3);
            dapaah.add(3);
            artistToIndex.put("Micheal Dapaah", dapaah);
            rap.add(3);
            genreToIndex.put("RAP", rap);
           
            titleToIndex.put("Wicked Game", 4);
            chris.add(4);
            artistToIndex.put("Chris Isaak", chris);
            rock.add(4);
            genreToIndex.put("ROCK", rock);

            titleToIndex.put("Clocks", 5);
            coldplay.add(5);
            artistToIndex.put("Colplay", coldplay);
            rock.add(5);
            genreToIndex.put("ROCK", rock);

            titleToIndex.put("Scars To Your Beautiful", 6);
            cara.add(6);
            artistToIndex.put("Alessia Cara", cara);
            pop.add(6);
            genreToIndex.put("POP", pop);

            titleToIndex.put("Radioactive", 7);
            dragon.add(7);
            artistToIndex.put("Imagine Dragons", dragon);
            rock.add(7);
            genreToIndex.put("ROCK", rock);

            titleToIndex.put("Feelin' Good", 8);           
            nina.add(8);
            artistToIndex.put("Nina Simone", nina);
            jazz.add(8);
            genreToIndex.put("JAZZ", jazz);
           
            titleToIndex.put("Wild Things", 9);
            cara.add(9);
            artistToIndex.put("Alessia Cara", cara);
            pop.add(9);
            genreToIndex.put("POP", pop);
            
            //dping smae thing for books authors creating authors arraylist and storing indices
            //and adding them to artistToIndex maps 
            ArrayList<Integer> moby = new ArrayList<Integer>();
            ArrayList<Integer> rowling = new ArrayList<Integer>();
            ArrayList<Integer> ralph = new ArrayList<Integer>();

            titleToIndex.put("Harry Potter and the Goblet of Fire", 10);
            rowling.add(10);
            artistToIndex.put("J.K. Rowling", rowling);
           
            titleToIndex.put("Moby Dick", 11);
            moby.add(11);
            artistToIndex.put("Herman Melville", moby);
           
            titleToIndex.put("Shogun", 12);
            ralph.add(12);
            artistToIndex.put("James Clavel", ralph);
            // Create a podcast object if you are doing the bonus see the makeSeasons() method below
            // It is currently commented out. It makes use of a class Season you may want to also create
            // or change it to something else
                   
        }
        public void search(String title){
         //taking string as parameter and getting its value (index) from titleToIndex map and priting its contents
         try{
            int x = titleToIndex.get(title);
            System.out.print(x+". ");
            contents.get(x-1).printInfo();
         }catch(NullPointerException e){//if an exception is caught... if user enter invalid title error msg is printed
            System.out.println("Error: "+e.getMessage());
            e.printStackTrace();
         }

        }
        public void searchA(String artist){
            //this method takes artists name as a parameter and get the arraylist of (indices) values from artistToIndex map
            //initiate ot to list arraylist and print info for all the indices
            ArrayList<Integer> list = artistToIndex.get(artist);
            if (list == null) {
                System.out.println("No results found.");
            } else {
                for (int i=0; i<list.size(); i++ ) {
                    System.out.print(list.get(i) + ". ");
                    contents.get(list.get(i)-1).printInfo();
                    System.out.println("");
                }
            }
        }  
        public void searchG(String genre){
            //this method takes genre type as a parameter and get the arraylist of (indices) values from genreToIndex map
            //initiate ot to list2 arraylist and print info for all the indices
            ArrayList<Integer> list2 = genreToIndex.get(genre);
            if (list2 == null) {
                System.out.println("No results found.");
            } else {
                for (int i=0; i<list2.size(); i++ ) {
                    System.out.print(list2.get(i) + ". ");
                    contents.get(list2.get(i)-1).printInfo();
                    System.out.println("");
                }
            }
        }  
       
        public AudioContent getContent(int index)
        {
            if (index < 1 || index > contents.size())
            {
                return null;
            }
            return contents.get(index-1);
        }
       
        public void listAll()
        {
            for (int i = 0; i < contents.size(); i++)
            {
                int index = i + 1;
                System.out.print("" + index + ". ");
                contents.get(i).printInfo();
                System.out.println();
            }
        }

        private ArrayList<AudioContent> readContentsFromFile() throws IOException {
            //reading contents from store file and creating obj and adding them to content arraylists
            ArrayList<AudioContent> contents = new ArrayList<AudioContent>();
            Scanner reader = new Scanner(new File("store.txt"));//reading store file (Using scanner)

            String line = "";
            while(reader.hasNextLine()){//using while loop to go thorugh every line in the text file
                line=reader.nextLine();
                if (line.equals("SONG")){//if the first line is SONG the storing all the song contents
                    String id = reader.nextLine();
                    String title = reader.nextLine();
                    int year = reader.nextInt();
					reader.nextLine();
                    int length = reader.nextInt();
                    reader.nextLine();
                    String artist = reader.nextLine();
                    String composer = reader.nextLine();
                    String genre = reader.nextLine();
                    int lyricsSize = reader.nextInt();
                    reader.nextLine();
                    String lyrics = "";
                   
                    for (int i=0; i<lyricsSize; i++){
                        lyrics+=reader.nextLine() +"\n";
                    }
                    Song.Genre genre2 = Song.Genre.valueOf(genre);
                    contents.add(new Song(title, year, id, Song.TYPENAME, lyrics, length, artist, composer, genre2, lyrics));//creating noe obj and adding it to content arrylist
                }
               
                else if (line.equals("AUDIOBOOK")){//if the first line is AUDIOBOOK the storing all the book contents
                    String id = reader.nextLine();
                    String title = reader.nextLine();
                    int year = reader.nextInt();
                    reader.nextLine();
                    int length = reader.nextInt();
                    reader.nextLine();
                    String author = reader.nextLine();
                    String narrator = reader.nextLine();
                    int numberOfChap = reader.nextInt();
                    reader.nextLine();
                    ArrayList<String> chapTitles = new ArrayList<>();//chapter title arraylist
                    ArrayList<String> chapters = new ArrayList<>();//Chapters arrraylist
                    for(int i=0; i<numberOfChap; i++){//using for loop to store all the chaptitles in the arraylist
                        chapTitles.add(reader.nextLine());
                    }
                    int chapLines;
                    for(int i=0; i<numberOfChap; i++){//using for loop to read chaplines and storing them in the string first and then storing the whole chap string in chapters arraylist
                        chapLines = reader.nextInt();
                        reader.nextLine();
                        String chap="";
                        for (int j=0; j<chapLines; j++){
                            chap +=reader.nextLine();
                        }
                        chapters.add(chap);
                    }
                    contents.add(new AudioBook(title, year, id, AudioBook.TYPENAME, "", length, author, narrator, chapTitles, chapters));//creating noe obj and adding it to content arrylist
                }
            }
            return contents;
        }
   
}







