package VideoLibrary;

public class Regisseur extends Person{
    Regisseur(String name, String surname){
        this.name = name;
        this.surname = surname;
    }

    @Override
    public String getFullString() {
        return String.format("Режисер: %s %s", this.name, this.surname);
    }
}
