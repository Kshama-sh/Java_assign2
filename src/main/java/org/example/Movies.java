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

    public static void Top10RatedMovies(HashMap<Integer, Movies> movies){
        System.out.println("\nTop 10 Rated Movies:");
        List<Movies> movieList = new ArrayList<>(movies.values());
        Collections.sort(movieList, (m1, m2) -> Double.compare(m2.rating, m1.rating));
        for (int i = 0; i < Math.min(10, movieList.size()); i++) {
            System.out.println(movieList.get(i));
        }
    }

    public static void MoviesbyGenre(HashMap<Integer, Movies> movies){
        System.out.print("Enter Genre: ");
        Scanner sc=new Scanner(System.in);
        boolean found = false;
        String genre = sc.nextLine();
        for (Movies movie : movies.values()) {
            if (movie.genre.equalsIgnoreCase(genre)) {
                System.out.println(movie);
                found = true;
            }
        }
        System.out.println("\nMovies in Genre: " + genre);
        if (!found) {
            System.out.println("No movies found in this genre.");
        }
    }

    public static void MoviesbyDirector(HashMap<Integer, Movies> movies, HashMap<Integer, Directors> directors, Scanner sc){
        int directorId = -1;
        System.out.print("Enter Director name: ");
        String name = sc.nextLine().trim();
        for (Directors director : directors.values()) {
            if (director.name.equalsIgnoreCase(name)) {
                directorId = director.directorid;
                break;
            }
        }
        System.out.println("\nMovies directed by " + name + ":");
        boolean found = false;
        for (Movies movie : movies.values()) {
            if (movie.directorid == directorId) {
                System.out.println(movie);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No movies found for this director.");
        }
    }

    public static void MoviesbyReleaseYear(HashMap<Integer, Movies> movies,Scanner sc){
        System.out.print("Enter Release year: ");
        int year = sc.nextInt();
        boolean found = false;
        for (Movies movie : movies.values()) {
            if (movie.releaseyear == year) {
                System.out.println(movie);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No movies found");
        }
    }

    public static void Moviesbyrange(HashMap<Integer, Movies> movies,Scanner sc){
        System.out.print("Enter Start Year: ");
        int startYear = sc.nextInt();
        System.out.print("Enter End Year: ");
        int endYear = sc.nextInt();
        sc.nextLine(); // Consume newline left-over
        boolean found = false;
        for (Movies movie : movies.values()) {
            if (movie.releaseyear >= startYear && movie.releaseyear <= endYear) {
                System.out.println(movie);
                found = true;
            }
        }
        if (!found) {
            System.out.println("No movies found in the given release year range.");
        }
    }

    public static void AddNewMovie(HashMap<Integer, Movies> movies,Scanner sc){
        System.out.print("\nEnter Movie ID: \n");
        int movieId = sc.nextInt();
        System.out.print("\nEnter Title:\n ");
        String title = sc.nextLine();
        System.out.print("\nEnter Release Year:\n ");
        int releaseYear = sc.nextInt();
        System.out.print("\nEnter Genre:\n ");
        String genre = sc.nextLine();
        System.out.print("\nEnter Rating:\n ");
        double rating = sc.nextDouble();
        System.out.print("\nEnter Duration:\n ");
        int duration = sc.nextInt();
        System.out.print("\nEnter Director ID:\n ");
        int directorId = sc.nextInt();
        System.out.print("\nEnter Actor IDs:\n ");
        String actorInput = sc.nextLine();
        List<Integer> actorIds = new ArrayList<>();
        String[] actorArray = actorInput.split(",");
        for (String actor : actorArray) {
            actorIds.add(Integer.parseInt(actor.trim()));
        }
        Movies newMovie = new Movies(movieId, title, releaseYear, genre, rating, duration, directorId, actorIds);
        movies.put(movieId, newMovie);
        System.out.println("Movie added successfully");
    }
    public static void UpdateMovieRating(HashMap<Integer, Movies> movies,Scanner sc){
        System.out.println("\nEnter movie id: ");
        int movieId=sc.nextInt();
        System.out.println("\nEnter updated rating: ");
        double newrating=sc.nextDouble();
        Movies movie = movies.get(movieId);
        movie.rating = newrating;
        System.out.println("\nMovie rating updated");
    }

    public static void DeleteMovie(HashMap<Integer, Movies> movies,Scanner sc){
        System.out.println("\nEnter movie id: ");
        int movieId=sc.nextInt();
        if (movies.containsKey(movieId)) {
            movies.remove(movieId);
            System.out.println("\nMovie deleted");
        } else {
            System.out.println("\nMovie not found");
        }
    }

    public static void moviesbyreleaseyear(HashMap<Integer, Movies> movies){
        List<Movies> movieList = new ArrayList<>(movies.values());
        Collections.sort(movieList, (m1, m2) -> m1.releaseyear - m2.releaseyear);
        System.out.println("\nTop 15 Movies Sorted by Release Year:");
        for (int i = 0; i < Math.min(15, movieList.size()); i++) {
            System.out.println(movieList.get(i));
        }
    }

    public static void DirectorswiththeMostMovies(HashMap<Integer, Movies> movies,HashMap<Integer, Directors> directors){
        HashMap<Integer, Integer> directorMovieCount = new HashMap<>();
        for (Movies movie : movies.values()) {
            int directorId = movie.directorid;
            directorMovieCount.put(directorId, directorMovieCount.getOrDefault(directorId, 0) + 1);
            List<Map.Entry<Integer, Integer>> sortedList = new ArrayList<>(directorMovieCount.entrySet());
            sortedList.sort((a, b) -> b.getValue() - a.getValue());
            System.out.println("\nTop 5 Directors with Most Movies:");
            int count = 0;
            for (Map.Entry<Integer, Integer> entry : sortedList) {
                if (directors.containsKey(directorId)) {
                    System.out.println(directors.get(directorId).name + " - " + entry.getValue() + " movies");
                    count++;
                    if (count == 5) break;
                }
            }
        }
    }

    public static void WorkedinMultipleMovies(HashMap<Integer, Movies> movies, HashMap<Integer, Actors> actors){
        HashMap<Integer, Integer> actorCount = new HashMap<>();
        for (Movies movie : movies.values()) {
            for (int actorId : movie.actorids) {
                actorCount.put(actorId, actorCount.getOrDefault(actorId, 0) + 1);
            }
        }
        int maxMovies = 0;
        int topActorId = -1;
        for (Map.Entry<Integer, Integer> entry : actorCount.entrySet()) {
            if (entry.getValue() > maxMovies) {
                maxMovies = entry.getValue();
                topActorId = entry.getKey();
            }
        }
        if (topActorId != -1) {
            Actors topActor = actors.get(topActorId);
            System.out.println("Actor Who Has Worked in Most Movies:");
            System.out.println("Name: " + topActor.name);
            System.out.println("Total Movies: " + maxMovies);
        } else {
            System.out.println("No actors found.");
        }
    }

    public static void Actorwhoistheyoungest(HashMap<Integer, Movies> movies, HashMap<Integer, Actors> actors){
        Actors youngestActor = null;
        for (Actors actor : actors.values()) {
            if (youngestActor == null || actor.dateOfBirth.compareTo(youngestActor.dateOfBirth) > 0) {
                youngestActor = actor;
            }
        }
        System.out.println("Youngest Actor: " + youngestActor.name);
        System.out.println("Birthdate: " + youngestActor.dateOfBirth);
        System.out.println("Movies:");
        boolean found = false;
        for (Movies movie : movies.values()) {
            for (int actorId : movie.actorids) {
                if (actorId == youngestActor.actorid) {
                    System.out.println(movie);
                    found = true;
                }
            }
        }
        if (!found) {
            System.out.println("No movies found for this actor.");
        }
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
