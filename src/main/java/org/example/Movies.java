package org.example;

import java.io.InputStream;
import java.util.*;

class Movies {
    int movieid;
    String title;
    int releaseyear;
    String genre;
    double rating;
    int duration;
    int directorid;
    List<Integer> actorids;

    public Movies(int movieid, String title, int releaseyear, String genre, double rating, int duration, int directorid, List<Integer> actorids) {
        this.movieid = movieid;
        this.title = title;
        this.releaseyear = releaseyear;
        this.genre = genre;
        this.rating = rating;
        this.duration = duration;
        this.directorid = directorid;
        this.actorids = actorids;
    }

    @Override
    public String toString() {
        return "Movie ID: " + movieid + ", Title: " + title + ", Year: " + releaseyear +
                ", Genre: " + genre + ", Rating: " + rating + ", Duration: " + duration +
                " mins, Director ID: " + directorid + ", Actor IDs: " + actorids;
    }

    public static void MovieInformation(HashMap<Integer, Movies> movies, HashMap<Integer, Actors> actors, HashMap<Integer, Directors> directors, Scanner scanner) {
        System.out.print("Enter Movie ID or Title: ");
        String input = scanner.nextLine().trim();
        for (Movies movie : movies.values()) {
            if (String.valueOf(movie.movieid).equals(input) || movie.title.equalsIgnoreCase(input)) {
                System.out.println("\nMovie Details:\n" + movie);
                Directors director = directors.get(movie.directorid);
                if (director != null) {
                    System.out.println("Directed by: " + director.name);
                }
                System.out.println("Actors:");
                for (int actorId : movie.actorids) {
                    Actors actor = actors.get(actorId);
                    if (actor != null) {
                        System.out.println(actor.name);
                    }
                }
                return;
            }
        }
        System.out.println("Movie not found");
    }

    public static HashMap<Integer, Movies> readMoviesCsv(String fileName) {
        HashMap<Integer, Movies> movieMap = new HashMap<>();
        InputStream inputStream = Movies.class.getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            System.out.println("Error: File not found!");
            return movieMap;
        }

        try (Scanner sc = new Scanner(inputStream)) {
            if (sc.hasNextLine()) sc.nextLine(); // Skip header

            while (sc.hasNextLine()) {
                String line = sc.nextLine().trim();
                if (line.isEmpty()) continue;

                String[] values = line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)");
                if (values.length < 8) continue;

                int movieId = Integer.parseInt(values[0].trim());
                String title = values[1].trim();
                int releaseYear = Integer.parseInt(values[2].trim());
                String genre = values[3].trim();
                double rating = Double.parseDouble(values[4].trim());
                int duration = Integer.parseInt(values[5].trim());
                int directorId = Integer.parseInt(values[6].trim());

                List<Integer> actorIds = new ArrayList<>();
                for (String actor : values[7].replace("\"", "").split(",")) {
                    actorIds.add(Integer.parseInt(actor.trim()));
                }

                movieMap.put(movieId, new Movies(movieId, title, releaseYear, genre, rating, duration, directorId, actorIds));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return movieMap;
    }
}
