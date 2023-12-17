package VideoLibrary;

import java.util.*;
import java.util.stream.Collectors;

public class Movie implements IMovie {
    private String name;
    private ArrayList<Person> actors;
    private ArrayList<Person> regisseurs;
    private boolean iSound;
    private int videoQuality;
    private int releaseYear;
    private float popularity;
    private ArrayList<String> videoQualities = new ArrayList<>(Arrays.asList("144p", "240p", "360p", "480p", "720p", "1080p"));

    Movie(String name, int releaseYear, float popularity, ArrayList<Person> actors, ArrayList<Person> regisseurs) {
        this.name = name;
        this.releaseYear = releaseYear;
        this.popularity = popularity;
        this.actors = new ArrayList<>(actors);
        this.regisseurs = new ArrayList<>(regisseurs);
        this.iSound = true;
        this.videoQuality = videoQualities.size() - 1;
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
    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    @Override
    public int getReleaseYear() {
        return this.releaseYear;
    }

    @Override
    public void setPopularity(float popularity) {
        this.popularity = popularity;
    }

    @Override
    public float getPopularity() {
        return this.popularity;
    }

    @Override
    public void addActor(Person actor) {
        actors.add(actor);
        System.out.println("\nАктёр добавлен!");
    }

    @Override
    public void removeActor(int id) {
        if (id >= 0 && id < actors.size()) {
            actors.remove(id);
            System.out.println("\nАктёр удалён!");
        } else {
            System.out.println("\nНе верный id актёра!");
        }
    }

    @Override
    public void updateActor(int id, Person actor) {
        if (id >= 0 && id < actors.size()) {
            actors.set(id, actor);
            System.out.println("\nАктёр обновлён!");
        } else {
            System.out.println("\nНе верный id актёра!");
        }
    }

    @Override
    public void addRegisseur(Person regisseur) {
        regisseurs.add(regisseur);
        System.out.println("\nДоваблен новый режисёр!");
    }

    @Override
    public void removeRegisseur(int id) {
        if (id >= 0 && id < regisseurs.size()) {
            regisseurs.remove(id);
            System.out.println("\nРежисёр удалён!");
        } else {
            System.out.println("\nНе верный id режисёра!");
        }
    }

    @Override
    public void updateRegisseur(int id, Person regisseur) {
        if (id >= 0 && id < regisseurs.size()) {
            this.regisseurs.set(id, regisseur);
            System.out.println("\nРежисер обнавлён!");
        } else {
            System.out.println("\nНе верный id режисёра!");
        }
    }

    @Override
    public void reduceMoveQuality() {
        if (this.videoQuality > 0) {
            this.videoQuality--;
            System.out.println("\nКачество видео уменьшено");
        } else {
            System.out.println("\nМинимальное качество видео!");
        }
    }

    @Override
    public void increaseMoveQuality() {
        if (this.videoQuality < this.videoQualities.size() - 1) {
            this.videoQuality++;
            System.out.println("\nКачество видео увеличино");
        } else {
            System.out.println("\nМаксимальное качество видео!");
        }
    }

    @Override
    public void disableSound() {
        this.iSound = false;
        System.out.println("\nЗвук отключён");
    }

    @Override
    public void enableSound() {
        this.iSound = true;
        System.out.println("\nЗвук включён");
    }

    @Override
    public String toString() {
        String actors = this.actors.stream().map(Person::toString).collect(Collectors.joining(", "));
        String regisseurs = this.regisseurs.stream().map(Person::toString).collect(Collectors.joining(", "));
        return "\nНазвание фильма: " + this.name +
                "\nГод релиза: " + this.releaseYear +
                "\nАктёры: " + actors +
                "\nРежисёры: " + regisseurs +
                "\nЗвук: " + (this.iSound ? "включён" : "выключен") +
                "\nКачество видио: " + this.videoQualities.get(this.videoQuality);
    }

    @Override
    public void run() {
        System.out.println(this);
        this.disableSound();
        this.reduceMoveQuality();
        this.reduceMoveQuality();
        this.reduceMoveQuality();
        System.out.println(this);
        this.increaseMoveQuality();
        this.enableSound();
        System.out.println(this);
    }

    public static class DateMovieComparator implements Comparator<Movie> {
        @Override
        public int compare(Movie o1, Movie o2) {
            return o1.releaseYear - o2.releaseYear;
        }
    }

    public static class PoplarMovieComparator implements Comparator<Movie> {
        @Override
        public int compare(Movie o1, Movie o2) {
            return Float.compare(o1.popularity, o2.popularity);
        }
    }
}
