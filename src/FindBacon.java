import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

public class FindBacon {

    boolean found;

    public FindBacon() {
        found = false;
    }

//make something so it doesn't back track people and movies
    public  String bruteForce(String start, ArrayList<SimpleMovie> allMovies, HashMap<String, Integer> allActors, int level, String path, ArrayList<SimpleMovie> beenMovies, ArrayList<String> beenActors) {
        if (!found) {
            if (start.equals("Kevin Bacon")) {
                path += " --> Kevin Bacon";
                found = true;
                System.out.println(path + "\nBacon Number of: " + level); //try to make it a return
            }
            if (level != 0) {
                path += " --> " + start;
            }
//            System.out.println(path);
            level++;
            ArrayList<SimpleMovie> moviesIn = new ArrayList<SimpleMovie>();
            for (SimpleMovie movie : allMovies) {
                if (movie.getActors().contains(start) && !beenMovies.contains(movie)) {
                    moviesIn.add(movie);
                    if (movie.getActors().contains("Kevin Bacon")) {
                        path += " --> " + movie.getTitle() + " --> Kevin Bacon";
                        found = true;
                        System.out.println(path + "\nBacon Number of: " + level);
                    }
                }
            }
            Collections.sort(moviesIn);


            if (level < 6) {
//            System.out.println("hello");
                for (SimpleMovie movie : moviesIn) {
//                System.out.println(movie.getTitle());
                    ArrayList<String> actors = (ArrayList<String>) movie.getActors().clone();
                    actors = sortActors(allActors, actors);
                    beenMovies.add(movie);
                    path += " --> " + movie.getTitle();

                    for (String actor : actors) {

                        if (!beenActors.contains(actor)) {
//                        System.out.println(actor);
                            beenActors.add(actor);
                            bruteForce(actor, allMovies, allActors, level, path, beenMovies, beenActors);
                        }
                    }

                    path = path.substring(0, path.length() - (movie.getTitle().length() + 5));
                }
            }
            return "";
        }
        return path;
    }

    private static ArrayList<String> sortActors(HashMap<String, Integer> allActors, ArrayList<String> actors) {
        ArrayList<String> temp = new ArrayList<String>();
        String actor = "";
        int highest = 0;
        while (!actors.isEmpty()) {
            actor = "";
            highest = 0;
            for (String name: actors) {
                if (allActors.get(name) > highest) {
                    highest = allActors.get(name);
                    actor = name;
                }
            }
            temp.add(actor);
            actors.remove(actor);
        }
        return temp;
    }

    public void setFound(boolean set) {
        found = set;
    }
}
