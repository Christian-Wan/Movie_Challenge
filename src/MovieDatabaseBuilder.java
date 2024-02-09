import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

public class MovieDatabaseBuilder {

    public static ArrayList<SimpleMovie> getMovieDB(String fileName) {
        ArrayList<SimpleMovie> movies = new ArrayList<SimpleMovie>();
        try {
            File movieData = new File(fileName);
            Scanner reader = new Scanner(movieData);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split("---");
                if (data.length > 1) {
                    SimpleMovie s = new SimpleMovie(data[0], data[1]);
                    movies.add(s);
                }

            }
        }
        catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
            return null;
        }
        return movies;

    }

    public static void makeActorFile(ArrayList<SimpleMovie> movies) {
        HashSet<String> actors = new HashSet<String>();
        for (SimpleMovie movie: movies) {
            for (String actor: movie.getActors()) {
                actors.add(actor);
            }
        }
        try {
            File f = new File("src/actors");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            for (String n : actors) {
                fw.write(n + "\n");
            }
            fw.close();
        }
        catch (IOException ioe) {
            System.out.println("Writing file failed");
            System.out.println(ioe);
        }

    }

    //mistake
    public static void makeActorDataFile(ArrayList<SimpleMovie> movies, String fileName) {
        HashMap<String, String> actorMovies = new HashMap<String, String>();
        try {
            File actorData = new File(fileName);
            Scanner reader = new Scanner(actorData);
            int count = 0;
            while (reader.hasNextLine()) {
                if (count % 500 == 0) {
                    System.out.println("on " + count);
                }
                count++;
                String actor = reader.nextLine();
                String actorMovie = "---";
                for (SimpleMovie movie: movies) {
                    if (movie.getActors().contains(actor)) {
                        actorMovie += movie.getTitle() + ":";
                    }
                }

            }
        }
        catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
        }
        try {
            File f = new File("src/actors_movie");
            f.createNewFile();
            FileWriter fw = new FileWriter(f);
            for (String n : actorMovies.keySet()) {
                fw.write(n + actorMovies.get(n) + "\n");
            }
            fw.close();
        }
        catch (IOException ioe) {
            System.out.println("Writing file failed");
            System.out.println(ioe);
        }
    }

}