package VideoLibrary;

public abstract class Person {
    String name;
    String surname;

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    @Override
    public String toString(){
        return String.format("%s %s", this.name, this.surname);
    }

    public abstract String getFullString();
}
