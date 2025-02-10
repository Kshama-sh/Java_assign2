package org.example;
import java.util.*;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, Actors> actors = Actors.readActorsCsv("actors_large.csv");
        HashMap<Integer, Directors> directors = Directors.readDirectorsCsv("directors_large.csv");
        HashMap<Integer, Movies> movies = Movies.readMoviesCsv("movies_large.csv");

        while (true) {
            System.out.println("\n===== Movie Database Menu =====");
            System.out.println("\n1. Get Movie Information");
            System.out.println("\n2. Get Top 10 Rated Movies");
            System.out.println("\n3. Get Movies by Genre");
            System.out.println("\n4. Get Movies by Director");
            System.out.println("\n5. Get Movies by Release Year");
            System.out.println("\n6. Get Movies by release year range");
            System.out.println("\n7. Add a New Movie");
            System.out.println("\n8. Update Movie Rating");
            System.out.println("\n9. Delete a Movie");
            System.out.println("\n10. Sort and return 15 movies by the release year");
            System.out.println("\n11. Get Directors with the Most Movies");
            System.out.println("\n12. Get Actor Who Have Worked in Multiple Movies");
            System.out.println("\n13. Get the movies of the actor who is the youngest as of 10-02-2025");
            System.out.println("\n14. Exit");
            System.out.print("\nChoose an option: ");

            Scanner sc=new Scanner(System.in);
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    Movies.MovieInformation(movies, actors, directors, sc);
                    break;
                case 2:
                    Movies.Top10RatedMovies(movies);
                    break;
                case 3:
                    Movies.MoviesbyGenre(movies);
                    break;
                case 4:
                    Movies.MoviesbyDirector(movies,directors,sc);
                    break;
                case 5:
                    Movies.MoviesbyReleaseYear(movies, sc);
                    break;
                case 6:
                    Movies.Moviesbyrange(movies,sc);
                    break;
                case 7:
                    Movies.AddNewMovie(movies,sc);
                    break;
                case 8:
                    Movies.UpdateMovieRating(movies,sc);
                    break;
                case 9:
                    Movies.DeleteMovie(movies,sc);
                    break;
                case 10:
                    Movies.moviesbyreleaseyear(movies);
                    break;
//                case 11:
//                    DirectorswiththeMostMovies(scanner);
//                    break;
//                case 12:
//                    WorkedinMultipleMovies(scanner);
//                    break;
//                case 13:
//                    Actorwhoistheyoungest(scanner);
//                    break;
                case 14:
                    System.out.println("Exiting program.");
                    sc.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
