package VideoLibrary;

public interface IMovie extends BaseAction {
    void setName(String name);

    String getName();

    void setReleaseYear(int releaseYear);

    int getReleaseYear();

    void setPopularity(float popularity);

    float getPopularity();

    void addActor(Person actor);

    void removeActor(int id);

    void updateActor(int id, Person actor);

    void addRegisseur(Person regisseur);

    void removeRegisseur(int id);

    void updateRegisseur(int id, Person regisseur);

    void reduceMoveQuality();

    void increaseMoveQuality();

    void disableSound();

    void enableSound();

    String toString();
}
