package model;

public class Doctor extends Person {
    private String specialization;

    public Doctor(int id, String name, String contact, String specialization) {
        super(id, name, contact);
        this.specialization = specialization;
    }

    public String getSpecialization() { return specialization; }

    @Override
    public void showDetails() {
        System.out.printf("Doctor [ID=%d, Name=%s, Specialization=%s, Contact=%s]%n",
                id, name, specialization, contact);
    }
}
