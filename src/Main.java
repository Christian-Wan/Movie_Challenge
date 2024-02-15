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
        FindBacon fb = new FindBacon();
//        for (SimpleMovie movie : movies) {
//            System.out.println(movie);
//        }
//        System.out.println("Number of movies: " + movies.size());

        while (true) {
            fb.setFound(false);
            System.out.println("Enter an actor's name or (q) to quit");
            String name = s.nextLine().toLowerCase();
            if (name.equals("q")) {
                break;
            }
            ArrayList<String> names = new ArrayList<>();
            for (String act: allActors.keySet()) {
                if (act.toLowerCase().contains(name)) {
                    names.add(act);
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
            System.out.println(fb.bruteForce(choice, movies, allActors, 0, choice, new ArrayList<SimpleMovie>(), starter));
        }
    }
}