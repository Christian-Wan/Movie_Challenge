import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        Scanner s = new Scanner(System.in);
        File f = new File("src/actors");
        Scanner fileScan = new Scanner(f);
        ArrayList<SimpleMovie> movies = MovieDatabaseBuilder.getMovieDB("src/movie_data");
        HashMap<String, Integer> allActors = MovieDatabaseBuilder.actorsMap();
//        for (SimpleMovie movie : movies) {
//            System.out.println(movie);
//        }
//        System.out.println("Number of movies: " + movies.size());

        while (true) {
            System.out.println("Enter an actor's name or (q) to quit");
            String name = s.nextLine().toLowerCase();
            if (name.equals("q")) {
                break;
            }
            ArrayList<String> names = new ArrayList<>();
            while (fileScan.hasNext()) {
                String line = fileScan.nextLine();
                if (line.toLowerCase().contains(name)) {
                    names.add(line);
                }
            }
            for (int i = 0; i < names.size(); i++) {
                System.out.println(i + 1 + ". " + names.get(i));
            }
            System.out.println("Which actor do you want to pick\nEnter a number");
            String choice = s.nextLine();
            choice = names.get(Integer.parseInt(choice) - 1);
            ArrayList<String> starter = new ArrayList<String>();
            starter.add(choice);
            System.out.println(FindBacon.bruteForce(choice, movies, allActors, 0, choice, new ArrayList<SimpleMovie>(), starter));
        }
    }
}