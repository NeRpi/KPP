package VideoLibrary;

import java.util.ArrayList;
import java.util.Arrays;

public class Category implements ICategory {
    private String name;
    private ArrayList<Movie> movies;
    private IMovie targetMovie;

    Category(String name) {
        this.name = name;
        this.movies = new ArrayList<>();
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void addMovie(Movie movie) {
        this.movies.add(movie);
        System.out.println("Фильм '" + movie.getName() + "' добавлен!");
    }

    @Override
    public void removeMovie(int id) {
        if (id > 0 && id <= movies.size()) {
            Movie removingMovie = this.movies.get(id - 1);
            movies.remove(id - 1);
            System.out.println("\nФильм '" + removingMovie.getName() + "' удалён!");
        } else System.out.println("\nНе верный id фильма!");
    }

    @Override
    public void updateMovie(int id, Movie movie) {
        if (id > 0 && id <= movies.size()) {
            Movie updatingMovie = this.movies.get(id - 1);
            movies.set(id - 1, movie);
            System.out.println("\nФильм '" + updatingMovie.getName() + "' обновлён!");
        } else System.out.println("\nНе верный id фильма!");
    }

    public void getAllMovies() {
        if (movies.isEmpty()) {
            System.out.println("\nФильмы в данной библиотеке отсутствуют!");
        } else {
            System.out.println("\nСписок фильмов: ");
            for (int i = 0; i < movies.size(); i++) {
                System.out.printf("%d. %s\n", i + 1, this.movies.get(i).getName());
            }
        }
    }

    @Override
    public void getNewMovies() {
        if (movies.isEmpty()) {
            System.out.println("Фильмы в данной библиотеке отсутствуют!");
        } else {
            System.out.println("\nСписок новых фильмов: ");
            ArrayList<Movie> newMovies = new ArrayList<>(movies);
            newMovies.sort(new Movie.DateMovieComparator());
            for (int i = 0; i < movies.size() && i < 10; i++) {
                Movie movie = movies.get(i);
                System.out.printf("%d. Название фильма: %s\n   Год выхода: %d\n", i + 1, movie.getName(), movie.getReleaseYear());
            }
        }
    }

    @Override
    public void getPopularMovies() {
        if (movies.isEmpty())
            System.out.println("Фильмы в данной библиотеке отсутствуют!");
        else {
            System.out.println("\nСписок популярных фильмов: ");
            ArrayList<Movie> newMovies = new ArrayList<>(movies);
            newMovies.sort(new Movie.PoplarMovieComparator());
            for (int i = 0; i < movies.size() && i < 10; i++) {
                Movie movie = movies.get(i);
                System.out.printf("%d. Название фильма: %s\n   Рейтинг фильма: %f\n", i + 1, movie.getName(), movie.getPopularity());
            }
        }
    }

    @Override
    public void openMovie(int id) {
        if (id > 0 && id < movies.size()) {
            this.targetMovie = this.movies.get(id);
            System.out.println("\nФильм '" + this.targetMovie.getName() + "' открыт!");
            this.targetMovie.run();
        } else {
            System.out.println("\nНе верный id фильма!");
        }
    }

    @Override
    public void closeMovie() {
        if (this.targetMovie != null) System.out.println("\nФильм '" + this.targetMovie.getName() + "' закрыт!");
        this.targetMovie = null;
    }

    public void run() {
        this.movies = new ArrayList<>(Arrays.asList(new Movie("Один дома", 1990, 8.3f, new ArrayList<>(Arrays.asList(new Actor("Джо", "Пеши"), new Actor("Маколей", "Калкин"))), new ArrayList<>(Arrays.asList(new Regisseur("Крис", "Коламбус")))),
                new Movie("Один дома 2: Затеряный в Нию-Йорке", 1992, 8f, new ArrayList<>(Arrays.asList(new Actor("Джо", "Пеши"), new Actor("Маколей", "Калкин"))), new ArrayList<>(Arrays.asList(new Regisseur("Крис", "Коламбус"))))));
        this.getAllMovies();
        this.removeMovie(2);
        this.getAllMovies();
        this.addMovie(new Movie("Один дома 2", 1992, 8f, new ArrayList<>(Arrays.asList(new Actor("Джо", "Пеши"), new Actor("Маколей", "Калкин"))), new ArrayList<>(Arrays.asList(new Regisseur("Крис", "Коламбус")))));
        this.getAllMovies();
        this.updateMovie(2, new Movie("Один дома 2: Затеряный в Нию-Йорке", 1992, 8f, new ArrayList<>(Arrays.asList(new Actor("Джо", "Пеши"), new Actor("Маколей", "Калкин"))), new ArrayList<>(Arrays.asList(new Regisseur("Крис", "Коламбус")))));
        this.getAllMovies();
        this.getNewMovies();
        this.getPopularMovies();
        this.openMovie(1);
        this.closeMovie();
    }
}
