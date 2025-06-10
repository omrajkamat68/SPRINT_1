package dao;

import model.Appointment;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class AppointmentDAO {

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
            System.out.println("Inserted Appointment successfully.");

        } catch (SQLException e) {
            System.err.println("Error inserting appointment: " + e.getMessage());
        }
    }

    public Appointment getAppointmentById(int id) {
        String sql = "SELECT * FROM Appointment WHERE appointment_id = ?";
        Appointment appointment = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                appointment = new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getTimestamp("appointment_time").toLocalDateTime(),
                        rs.getString("reason")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving appointment: " + e.getMessage());
        }

        return appointment;
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
            System.out.println("Updated Appointment, rows affected: " + rows);

        } catch (SQLException e) {
            System.err.println("Error updating appointment: " + e.getMessage());
        }
    }

    public void deleteAppointment(int id) {
        String sql = "DELETE FROM Appointment WHERE appointment_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println("Deleted Appointment, rows affected: " + rows);

        } catch (SQLException e) {
            System.err.println("Error deleting appointment: " + e.getMessage());
        }
    }

    public List<Appointment> getAllAppointments() {
        String sql = "SELECT * FROM Appointment";
        List<Appointment> appointments = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Appointment appointment = new Appointment(
                        rs.getInt("appointment_id"),
                        rs.getInt("patient_id"),
                        rs.getInt("doctor_id"),
                        rs.getTimestamp("appointment_time").toLocalDateTime(),
                        rs.getString("reason")
                );
                appointments.add(appointment);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching appointments: " + e.getMessage());
        }

        return appointments;
    }
}
