**Author:** Sami Siddiqui  
## Overview

`MyAudioUI` is a Java-based text simulation of a basic music application, similar to Apple Music. Users can interact with a simulated library to search, download, and manage various types of audio content, including songs, audiobooks, and podcasts. The application offers features to browse, sort, and organize audio content in playlists and allows users to play content by specifying indexes or filters.

## Features

- **Audio Content Management**: Access, search, and manage songs, audiobooks, and podcasts.
- **Playlists**: Create, manage, and play playlists containing various audio content.
- **Sorting and Filtering**: Sort songs by year, name, or length and filter searches by title, artist, or genre.
- **Download and Playback**: Download specific content from the store and play from your library.

## Requirements

- **Java 8 or higher**
- **JDK and JRE** for compiling and running Java applications
- **Command-Line Interface** (CLI) or Integrated Development Environment (IDE) for executing the program

## File Descriptions

- `MyAudioUI.java`: Main program file with user interactions and command processing.
- `AudioContent.java`: Contains the base class for audio content objects.
- `AudioContentStore.java`: Simulates an online store where users can download content.
- `Library.java`: Manages the user’s local audio content library and playlists.
- `Song.java`: Represents song objects and includes properties such as title, artist, and length.


**Commands**: Enter commands interactively to perform actions (refer to the Commands section for more details).

## Commands

The following commands can be entered into the command line to interact with the program:

- **Search**:
  - `SEARCH`: Search content by title.
  - `SEARCHA`: Search content by artist.
  - `SEARCHG`: Search content by genre.

- **Browse**:
  - `STORE`: List all available store content.
  - `SONGS`: List all songs in your library.
  - `BOOKS`: List all audiobooks in your library.
  - `PODCASTS`: List all podcasts in your library.
  - `ARTISTS`: List all unique artists.
  - `PLAYLISTS`: List all user-created playlists.

- **Download**:
  - `DOWNLOAD`: Download audio content by specifying a range of indices.
  - `DOWNLOADA`: Download all songs by a specific artist.
  - `DOWNLOADG`: Download all content of a specified genre.

- **Play**:
  - `PLAYSONG`: Play a song by index.
  - `PLAYBOOK`: Play a specific chapter in an audiobook.
  - `PODTOC`: Print the table of contents of a podcast season.
  - `PLAYPOD`: Play a specific episode in a season of a podcast.
  - `PLAYALLPL`: Play all content in a specified playlist.
  - `PLAYPL`: Play specific content in a playlist by index.

- **Playlist Management**:
  - `MAKEPL`: Create a new playlist.
  - `PRINTPL`: Print all content in a playlist.
  - `ADDTOPL`: Add content to a playlist by specifying the content type and index.
  - `DELFROMPL`: Remove content from a playlist by index.

- **Sorting**:
  - `SORTBYYEAR`: Sort songs by release year.
  - `SORTBYNAME`: Sort songs alphabetically by title.
  - `SORTBYLENGTH`: Sort songs by length.

- **Delete**:
  - `DELSONG`: Delete a song from your library and playlists.

- **Quit**:
  - `QUIT` or `Q`: Exit the program.

## Exception Handling

The program includes custom exception handling to ensure smooth interaction and error feedback. Exceptions are handled for:

- **Null Content**: Triggers an error message if content is unavailable.
- **Index Out of Bounds**: Catches errors when accessing invalid indices.
- **AudioContentNotFoundException**: Custom exception for handling missing content, displaying an error message to guide the user.
