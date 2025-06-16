import dao.*;
import model.*;
import java.time.format.DateTimeFormatter;

import service.AppointmentService;
import util.AppointmentQueue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        MedicalCaseDAO medicalCaseDAO = new MedicalCaseDAO();
        AppointmentQueue appointmentQueue = new AppointmentQueue();

        // Enhancement: Load existing appointments into queue at startup
        for (Appointment appt : appointmentDAO.getAllAppointments()) {
            appointmentQueue.enqueue(appt);
        }

        while (true) {
            System.out.println("\n--- MediConnect CRM ---");
            System.out.println("1. Add Patient\n2. View Patient\n3. Update Patient\n4. Delete Patient");
            System.out.println("5. Add Doctor\n6. View Doctor\n7. Update Doctor\n8. Delete Doctor");
            System.out.println("9. Add Appointment\n10. View Appointment\n11. Update Appointment\n12. Delete Appointment");
            System.out.println("13. Add Medical Case\n14. View Medical Case\n15. Update Medical Case\n16. Delete Medical Case");
            System.out.println("17. Process Appointments Queue");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter Patient ID: ");
                    int id = sc.nextInt();
                    if (patientDAO.getPatientById(id) != null) {
                        System.out.println("Patient ID already exists.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Gender: ");
                    String gender = sc.nextLine();
                    System.out.print("Enter Contact: ");
                    String contact = sc.nextLine();
                    patientDAO.addPatient(new Patient(id, name, contact, age, gender));
                }
                case 2 -> {
                    System.out.print("Enter Patient ID: ");
                    int id = sc.nextInt();
                    Patient p = patientDAO.getPatientById(id);
                    if (p != null) {
                        p.showDetails();
                    } else {
                        System.out.println("Patient not found.");
                    }
                }
                case 3 -> {
                    System.out.print("Enter Patient ID to update: ");
                    int id = sc.nextInt();
                    if (patientDAO.getPatientById(id) == null) {
                        System.out.println("Patient not found.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter new Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new Gender: ");
                    String gender = sc.nextLine();
                    System.out.print("Enter new Contact: ");
                    String contact = sc.nextLine();
                    patientDAO.updatePatient(new Patient(id, name, contact, age, gender));
                }
                case 4 -> {
                    System.out.print("Enter Patient ID to delete: ");
                    int id = sc.nextInt();
                    if (patientDAO.getPatientById(id) == null) {
                        System.out.println("Patient not found.");
                        break;
                    }
                    patientDAO.deletePatient(id);
                }
                case 5 -> {
                    System.out.print("Enter Doctor ID: ");
                    int id = sc.nextInt();
                    if (doctorDAO.getDoctorById(id) != null) {
                        System.out.println("Doctor ID already exists.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Specialization: ");
                    String specialization = sc.nextLine();
                    System.out.print("Enter Contact: ");
                    String contact = sc.nextLine();
                    doctorDAO.addDoctor(new Doctor(id, name, contact, specialization));
                }
                case 6 -> {
                    System.out.print("Enter Doctor ID: ");
                    int id = sc.nextInt();
                    Doctor d = doctorDAO.getDoctorById(id);
                    if (d != null) {
                        d.showDetails();
                    } else {
                        System.out.println("Doctor not found.");
                    }
                }
                case 7 -> {
                    System.out.print("Enter Doctor ID to update: ");
                    int id = sc.nextInt();
                    if (doctorDAO.getDoctorById(id) == null) {
                        System.out.println("Doctor not found.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter new Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter new Specialization: ");
                    String specialization = sc.nextLine();
                    System.out.print("Enter new Contact: ");
                    String contact = sc.nextLine();
                    doctorDAO.updateDoctor(new Doctor(id, name, contact, specialization));
                }
                case 8 -> {
                    System.out.print("Enter Doctor ID to delete: ");
                    int id = sc.nextInt();
                    if (doctorDAO.getDoctorById(id) == null) {
                        System.out.println("Doctor not found.");
                        break;
                    }
                    doctorDAO.deleteDoctor(id);
                }
                case 9 -> {
                    System.out.print("Enter Appointment ID: ");
                    int id = sc.nextInt();
                    if (appointmentDAO.getAppointmentById(id) != null) {
                        System.out.println("Appointment ID already exists.");
                        break;
                    }

                    System.out.print("Enter Patient ID: ");
                    int pid = sc.nextInt();
                    if (patientDAO.getPatientById(pid) == null) {
                        System.out.println("Invalid Patient ID. No such patient exists.");
                        break;
                    }

                    System.out.print("Enter Doctor ID: ");
                    int did = sc.nextInt();
                    if (doctorDAO.getDoctorById(did) == null) {
                        System.out.println("Invalid Doctor ID. No such doctor exists.");
                        break;
                    }

                    sc.nextLine();
                    try {
                        System.out.print("Enter Date (yyyy-MM-dd): ");
                        LocalDate date = LocalDate.parse(sc.nextLine());
                        System.out.print("Enter Time (HH:mm): ");
                        LocalTime time = LocalTime.parse(sc.nextLine());
                        LocalDateTime dateTime = LocalDateTime.of(date, time);

                        System.out.print("Enter Reason: ");
                        String reason = sc.nextLine();

                        Appointment appt = new Appointment(id, pid, did, dateTime, reason);
                        appointmentDAO.addAppointment(appt);
                        appointmentQueue.enqueue(appt);

                        System.out.println("Appointment added and queued.");
                        System.out.println("Next appointment in queue:");
                        Appointment peeked = appointmentQueue.peek();
                        if (peeked != null) peeked.printAppointment();
                        else System.out.println("No upcoming appointments in queue.");

                    } catch (Exception e) {
                        System.out.println("Invalid date or time format. Use yyyy-MM-dd and HH:mm.");
                    }
                }
                case 10 -> {
                    System.out.print("Enter Appointment ID: ");
                    Appointment ap = appointmentDAO.getAppointmentById(sc.nextInt());
                    if (ap != null) {
                        ap.printAppointment();
                    } else {
                        System.out.println("Appointment not found.");
                    }
                }
                case 11 -> {
                    System.out.print("Enter Appointment ID to update: ");
                    int id = sc.nextInt();
                    if (appointmentDAO.getAppointmentById(id) == null) {
                        System.out.println("No appointment exists with that ID.");
                        break;
                    }
                    System.out.print("Enter new Patient ID: ");
                    int pid = sc.nextInt();
                    if (patientDAO.getPatientById(pid) == null) {
                        System.out.println("Invalid Patient ID.");
                        break;
                    }
                    System.out.print("Enter new Doctor ID: ");
                    int did = sc.nextInt();
                    if (doctorDAO.getDoctorById(did) == null) {
                        System.out.println("Invalid Doctor ID.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter new Time (yyyy-MM-dd HH:mm): ");
                    LocalDateTime time = AppointmentService.parseAppointmentTime(sc.nextLine());
                    System.out.print("Enter new Reason: ");
                    String reason = sc.nextLine();
                    appointmentDAO.updateAppointment(new Appointment(id, pid, did, time, reason));
                }
                case 12 -> {
                    System.out.print("Enter Appointment ID to delete: ");
                    int id = sc.nextInt();
                    if (appointmentDAO.getAppointmentById(id) == null) {
                        System.out.println("Appointment not found.");
                        break;
                    }
                    appointmentDAO.deleteAppointment(id);
                }
                case 13 -> {
                    System.out.print("Enter Case ID: ");
                    int caseId = sc.nextInt();
                    if (medicalCaseDAO.exists(caseId)) {
                        System.out.println("Medical Case ID already exists.");
                        break;
                    }
                    System.out.print("Enter Patient ID: ");
                    int patientId = sc.nextInt();
                    if (patientDAO.getPatientById(patientId) == null) {
                        System.out.println("Invalid Patient ID.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter Diagnosis: ");
                    String diagnosis = sc.nextLine();
                    System.out.print("Enter Treatment: ");
                    String treatment = sc.nextLine();
                    System.out.print("Enter Date (yyyy-MM-dd): ");
                    LocalDate date = LocalDate.parse(sc.nextLine());
                    medicalCaseDAO.addMedicalCase(new MedicalCase(caseId, patientId, diagnosis, treatment, date));
                }
                case 14 -> {
                    System.out.print("Enter Case ID: ");
                    int id = sc.nextInt();
                    MedicalCase mc = medicalCaseDAO.getMedicalCaseById(id);
                    if (mc != null) System.out.println(mc);
                    else System.out.println("Medical case not found.");
                }
                case 15 -> {
                    System.out.print("Enter Case ID to update: ");
                    int caseId = sc.nextInt();
                    if (!medicalCaseDAO.exists(caseId)) {
                        System.out.println("Medical case not found.");
                        break;
                    }
                    System.out.print("Enter Patient ID: ");
                    int patientId = sc.nextInt();
                    if (patientDAO.getPatientById(patientId) == null) {
                        System.out.println("Invalid Patient ID.");
                        break;
                    }
                    sc.nextLine();
                    System.out.print("Enter new Diagnosis: ");
                    String diagnosis = sc.nextLine();
                    System.out.print("Enter new Treatment: ");
                    String treatment = sc.nextLine();
                    System.out.print("Enter new Date (yyyy-MM-dd): ");
                    LocalDate date = LocalDate.parse(sc.nextLine());
                    medicalCaseDAO.updateMedicalCase(new MedicalCase(caseId, patientId, diagnosis, treatment, date));
                }
                case 16 -> {
                    System.out.print("Enter Case ID to delete: ");
                    int caseId = sc.nextInt();
                    if (!medicalCaseDAO.exists(caseId)) {
                        System.out.println("Medical case not found.");
                        break;
                    }
                    medicalCaseDAO.deleteMedicalCase(caseId);
                }
                case 17 -> {
                    System.out.println("\nProcessing Appointments:");
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

                    // Reload queue from DB
                    appointmentQueue.clear();  // ← You need to add this method in AppointmentQueue
                    for (Appointment appt : appointmentDAO.getAllAppointments()) {
                        appointmentQueue.enqueue(appt);
                    }

                    while (appointmentQueue.peek() != null) {
                        Appointment appt = appointmentQueue.dequeue();
                        System.out.printf(
                                "Appointment ID: %d | Patient ID: %d | Doctor ID: %d | Time: %s | Reason: %s\n",
                                appt.getId(),
                                appt.getPatientId(),
                                appt.getDoctorId(),
                                appt.getAppointmentTime().format(formatter),
                                appt.getReason()
                        );
                    }
                }

                case 0 -> System.exit(0);
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
