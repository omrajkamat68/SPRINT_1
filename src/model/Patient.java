package model;

public class Patient extends Person {
    private int age;
    private String gender;

    public Patient(int id, String name, String contact, int age, String gender) {
        super(id, name, contact);
        this.age = age;
        this.gender = gender;
    }

    public int getAge() { return age; }
    public String getGender() { return gender; }

    @Override
    public void showDetails() {
        System.out.printf("Patient [ID=%d, Name=%s, Age=%d, Gender=%s, Contact=%s]%n",
                id, name, age, gender, contact);
    }
}
