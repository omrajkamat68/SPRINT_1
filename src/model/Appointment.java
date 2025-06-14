package model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Appointment implements Displayable {
    private int id;
    private int patientId;
    private int doctorId;
    private LocalDateTime appointmentTime;
    private String reason;

    public Appointment(int id, int patientId, int doctorId, LocalDateTime appointmentTime, String reason) {
        this.id = id;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.appointmentTime = appointmentTime;
        this.reason = reason;
    }

    public int getId() { return id; }
    public int getPatientId() { return patientId; }
    public int getDoctorId() { return doctorId; }
    public LocalDateTime getAppointmentTime() { return appointmentTime; }
    public String getReason() { return reason; }

    @Override
    public void showDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        System.out.printf(
                "Appointment ID: %d | Patient ID: %d | Doctor ID: %d | Time: %s | Reason: %s%n",
                id, patientId, doctorId, appointmentTime.format(formatter), reason
        );
    }

    public void printAppointment() {
        showDetails();
    }
}
