import java.util.ArrayList;

public class FindBacon {



    public static String bruteForce(String start, ArrayList<SimpleMovie> allMovies, int level, String path) {
        if (start.equals("Kevin Bacon")) {
            return "";
        }
        level++;
        ArrayList<SimpleMovie> moviesIn = new ArrayList<SimpleMovie>();
        for (SimpleMovie movie: allMovies) {
            if (movie.getActors().contains(start)) {
                moviesIn.add(movie);
            }
        }
        if (level < 6) {
            bruteForce(, allMovies, level, );
        }

        return "";
    }


}
