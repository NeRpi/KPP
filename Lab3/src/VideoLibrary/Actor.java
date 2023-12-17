package VideoLibrary;

public class Actor extends Person{
    Actor(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String getFullString() {
        return String.format("Актёр: %s %s", this.name, this.surname);
    }
}
