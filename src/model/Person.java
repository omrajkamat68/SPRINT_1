package model;

public abstract class Person implements Displayable {
    protected int id;
    protected String name;
    protected String contact;

    public Person(int id, String name, String contact) {
        this.id = id;
        this.name = name;
        this.contact = contact;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public String getContact() { return contact; }

    public abstract void showDetails(); // inherited from Displayable
}
