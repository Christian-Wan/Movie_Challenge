import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class FindBacon {


//make something so it doesn't back track
    public static String bruteForce(String start, ArrayList<SimpleMovie> allMovies, int level, String path) {
        if (start.equals("Kevin Bacon")) {
            return path + "\nBacon Number of: " + level;
        }
        level++;
        ArrayList<SimpleMovie> moviesIn = new ArrayList<SimpleMovie>();
        for (SimpleMovie movie: allMovies) {
            if (movie.getActors().contains(start)) {
                moviesIn.add(movie);
            }
        }
        Collections.sort(moviesIn);
        System.out.println(moviesIn);
        if (level < 6) {
            level++;
            for (SimpleMovie movie: moviesIn) {
                ArrayList<String> actors = movie.getActors();

                for (String actor: movie.getActors())
                bruteForce(start, allMovies, level, path);
            }
        }

        return "";
    }
//
//    private ArrayList<String> sortActors(ArrayList<String> actors) {
//        ArrayList<String> temp = new ArrayList<String>();
//        for (int i = 0; i < actors.size(); i++) {
//            String highest
//        }
//        return temp;
//    }

}
