import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

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

    public static HashMap<String, Integer> actorsMap() {
        HashMap<String, Integer> actors = new HashMap<String, Integer>();
        try {
            File movieData = new File("src/actors_movie3");
            Scanner reader = new Scanner(movieData);
            while (reader.hasNextLine()) {
                String line = reader.nextLine();
                String[] data = line.split("!@#");
                actors.put(data[0], Integer.parseInt(data[1]));

            }
        }
        catch (FileNotFoundException noFile) {
            System.out.println("File not found!a");
            return null;
        }
        return actors;
    }

    public static void makeActorFile(ArrayList<SimpleMovie> movies) {
        TreeSet<String> actors = new TreeSet<String>();
        for (SimpleMovie movie: movies) {
            for (String actor: movie.getActors()) {
                actors.add(actor);
            }
        }
        try {
            File f = new File("src/actors2");
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
    public static void makeActorDataFile(ArrayList<SimpleMovie> movies, String fileName) throws IOException {
        int number = 0;
//        File f = new File("src/actors_movie4");
//        f.createNewFile();
//        FileWriter fw = new FileWriter(f);
        try {
            File actorData = new File(fileName);
            Scanner reader = new Scanner(actorData);
            int count = 0;
            while (reader.hasNextLine()) {
                if (reader.nextLine().isEmpty()) {
                    break;
                }
            }
            while (reader.hasNextLine()) {
                number = 0;
                if (count % 500 == 0) {
                    System.out.println("on " + count);
                }
                count++;
                String actor = reader.nextLine();

                for (SimpleMovie movie: movies) {
                    if (movie.getActors().contains(actor)) {
                        number++;
                    }
                }
                System.out.println(actor + "!@#" + number);
//                fw.write(actor + "!@#" + number + "\n");
            }
        }
        catch (FileNotFoundException noFile) {
            System.out.println("File not found!");
        }
    }

}