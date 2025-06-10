import dao.*;
import model.*;
import service.AppointmentService;
import util.AppointmentQueue;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PatientDAO patientDAO = new PatientDAO();
        DoctorDAO doctorDAO = new DoctorDAO();
        AppointmentDAO appointmentDAO = new AppointmentDAO();
        MedicalCaseDAO medicalCaseDAO = new MedicalCaseDAO();

        while (true) {
            System.out.println("\n--- MediConnect CRM ---");
            System.out.println("1. Add Patient\n2. View Patient\n3. Update Patient\n4. Delete Patient");
            System.out.println("5. Add Doctor\n6. View Doctor\n7. Update Doctor\n8. Delete Doctor");
            System.out.println("9. Add Appointment\n10. View Appointment\n11. Update Appointment\n12. Delete Appointment");
            System.out.println("13. Add Medical Case\n14. View Medical Case\n15. Update Medical Case\n16. Delete Medical Case");
            System.out.println("0. Exit");
            System.out.print("Choose an option: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter ID, Name, Age, Gender, Contact: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    String name = sc.nextLine();
                    int age = sc.nextInt();
                    sc.nextLine();
                    String gender = sc.nextLine();
                    String contact = sc.nextLine();
                    patientDAO.addPatient(new Patient(id, name, contact, age, gender));
                }
                case 2 -> {
                    System.out.print("Enter Patient ID: ");
                    int id = sc.nextInt();
                    Patient p = patientDAO.getPatientById(id);
                    if (p != null) p.showDetails();
                }
                case 3 -> {
                    System.out.print("Enter ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new Name, Age, Gender, Contact: ");
                    String name = sc.nextLine();
                    int age = sc.nextInt();
                    sc.nextLine();
                    String gender = sc.nextLine();
                    String contact = sc.nextLine();
                    patientDAO.updatePatient(new Patient(id, name, contact, age, gender));
                }
                case 4 -> {
                    System.out.print("Enter ID to delete: ");
                    patientDAO.deletePatient(sc.nextInt());
                }
                case 5 -> {
                    System.out.print("Enter ID, Name, Specialization, Contact: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    String name = sc.nextLine();
                    String specialization = sc.nextLine();
                    String contact = sc.nextLine();
                    doctorDAO.addDoctor(new Doctor(id, name, contact, specialization));
                }
                case 6 -> {
                    System.out.print("Enter Doctor ID: ");
                    int id = sc.nextInt();
                    Doctor d = doctorDAO.getDoctorById(id);
                    if (d != null) d.showDetails();
                }
                case 7 -> {
                    System.out.print("Enter ID to update: ");
                    int id = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter new Name, Specialization, Contact: ");
                    String name = sc.nextLine();
                    String specialization = sc.nextLine();
                    String contact = sc.nextLine();
                    doctorDAO.updateDoctor(new Doctor(id, name, contact, specialization));
                }
                case 8 -> {
                    System.out.print("Enter ID to delete: ");
                    doctorDAO.deleteDoctor(sc.nextInt());
                }
                case 9 -> {
                    System.out.print("Enter ID, PatientID, DoctorID, Time (yyyy-MM-dd HH:mm), Reason: ");
                    int id = sc.nextInt();
                    int pid = sc.nextInt();
                    int did = sc.nextInt();
                    sc.nextLine();
                    LocalDateTime time = AppointmentService.parseAppointmentTime(sc.nextLine());
                    String reason = sc.nextLine();
                    appointmentDAO.addAppointment(new Appointment(id, pid, did, time, reason));
                }
                case 10 -> {
                    System.out.print("Enter Appointment ID: ");
                    Appointment ap = appointmentDAO.getAppointmentById(sc.nextInt());
                    if (ap != null) ap.printAppointment();
                }
                case 11 -> {
                    System.out.print("Enter ID to update: ");
                    int id = sc.nextInt();
                    System.out.print("Enter new PatientID, DoctorID, Time (yyyy-MM-dd HH:mm), Reason: ");
                    int pid = sc.nextInt();
                    int did = sc.nextInt();
                    sc.nextLine();
                    LocalDateTime time = AppointmentService.parseAppointmentTime(sc.nextLine());
                    String reason = sc.nextLine();
                    appointmentDAO.updateAppointment(new Appointment(id, pid, did, time, reason));
                }
                case 12 -> {
                    System.out.print("Enter ID to delete: ");
                    appointmentDAO.deleteAppointment(sc.nextInt());
                }
                case 13 -> {
                    System.out.print("Enter CaseID, PatientID, Diagnosis, Treatment, Date (yyyy-MM-dd): ");
                    int caseId = sc.nextInt();
                    int patientId = sc.nextInt();
                    sc.nextLine();
                    String diagnosis = sc.nextLine();
                    String treatment = sc.nextLine();
                    LocalDate date = LocalDate.parse(sc.nextLine());
                    medicalCaseDAO.addMedicalCase(new MedicalCase(caseId, patientId, diagnosis, treatment, date));
                }
                case 14 -> {
                    System.out.print("Enter Case ID: ");
                    MedicalCase mc = medicalCaseDAO.getMedicalCaseById(sc.nextInt());
                    if (mc != null) System.out.println(mc);
                }
                case 15 -> {
                    System.out.print("Enter ID to update: ");
                    int caseId = sc.nextInt();
                    int patientId = sc.nextInt();
                    sc.nextLine();
                    String diagnosis = sc.nextLine();
                    String treatment = sc.nextLine();
                    LocalDate date = LocalDate.parse(sc.nextLine());
                    medicalCaseDAO.updateMedicalCase(new MedicalCase(caseId, patientId, diagnosis, treatment, date));
                }
                case 16 -> {
                    System.out.print("Enter Case ID to delete: ");
                    medicalCaseDAO.deleteMedicalCase(sc.nextInt());
                }
                case 0 -> System.exit(0);
                default -> System.out.println("Invalid option.");
            }
        }
    }
}
