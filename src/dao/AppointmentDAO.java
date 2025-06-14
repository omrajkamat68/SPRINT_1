package dao;

import model.Appointment;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AppointmentDAO {

    public void addAppointmentInteractive() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter Appointment ID: ");
        int id = scanner.nextInt();

        if (getAppointmentById(id) != null) {
            System.out.println("This ID is already used. Try another.");
            return;
        }

        System.out.print("Enter Patient ID: ");
        int patientId = scanner.nextInt();
        System.out.print("Enter Doctor ID: ");
        int doctorId = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter Appointment Time (yyyy-MM-dd HH:mm): ");
        String timeInput = scanner.nextLine();
        System.out.print("Enter Reason: ");
        String reason = scanner.nextLine();

        try {
            LocalDateTime time = LocalDateTime.parse(timeInput.replace(" ", "T"));
            Appointment appointment = new Appointment(id, patientId, doctorId, time, reason);
            addAppointment(appointment);
        } catch (Exception e) {
            System.out.println("Invalid input: " + e.getMessage());
        }
    }

    public void addAppointment(Appointment appointment) {
        String sql = "INSERT INTO Appointment (appointment_id, patient_id, doctor_id, appointment_time, reason) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointment.getId());
            stmt.setInt(2, appointment.getPatientId());
            stmt.setInt(3, appointment.getDoctorId());
            stmt.setTimestamp(4, Timestamp.valueOf(appointment.getAppointmentTime()));
            stmt.setString(5, appointment.getReason());
            stmt.executeUpdate();
            System.out.println("Appointment has been added.");
        } catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("ID already exists or doctor/patient does not exist.");
        } catch (SQLException e) {
            System.out.println("Failed to add appointment: " + e.getMessage());
        }
    }

    public Appointment getAppointmentById(int id) {
        String sql = "SELECT * FROM Appointment WHERE appointment_id = ?";
        Appointment result = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                result = new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getTimestamp("appointment_time").toLocalDateTime(),
                        rs.getString("reason")
                );
            }
        } catch (SQLException e) {
            System.out.println("Error fetching appointment: " + e.getMessage());
        }
        return result;
    }

    public void updateAppointment(Appointment appointment) {
        String sql = "UPDATE Appointment SET patient_id = ?, doctor_id = ?, appointment_time = ?, reason = ? WHERE appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, appointment.getPatientId());
            stmt.setInt(2, appointment.getDoctorId());
            stmt.setTimestamp(3, Timestamp.valueOf(appointment.getAppointmentTime()));
            stmt.setString(4, appointment.getReason());
            stmt.setInt(5, appointment.getId());
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Appointment updated.");
            } else {
                System.out.println("Appointment with this ID does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Failed to update appointment: " + e.getMessage());
        }
    }

    public void deleteAppointment(int id) {
        String sql = "DELETE FROM Appointment WHERE appointment_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Appointment removed.");
            } else {
                System.out.println("Appointment with this ID does not exist.");
            }
        } catch (SQLException e) {
            System.out.println("Error removing appointment: " + e.getMessage());
        }
    }

    public List<Appointment> getAllAppointments() {
        String sql = "SELECT * FROM Appointment";
        List<Appointment> list = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Appointment appt = new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getTimestamp("appointment_time").toLocalDateTime(),
                        rs.getString("reason")
                );
                list.add(appt);
            }
        } catch (SQLException e) {
            System.out.println("Could not retrieve appointments: " + e.getMessage());
        }
        return list;
    }
}
