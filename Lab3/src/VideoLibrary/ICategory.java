package VideoLibrary;

public interface ICategory extends BaseAction {

    void setName(String name);
    String getName();

    void addMovie(Movie movie);

    void removeMovie(int id);

    void updateMovie(int id, Movie movie);

    void getAllMovies();

    void getNewMovies();

    void getPopularMovies();

    void openMovie(int id);

    void closeMovie();
}
