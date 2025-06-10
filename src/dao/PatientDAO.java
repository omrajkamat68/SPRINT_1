package dao;

import model.Patient;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PatientDAO {

    public void addPatient(Patient patient) {
        String sql = "INSERT INTO Patient (patient_id, name, age, gender, contact) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, patient.getId());
            stmt.setString(2, patient.getName());
            stmt.setInt(3, patient.getAge());
            stmt.setString(4, patient.getGender());
            stmt.setString(5, patient.getContact());

            stmt.executeUpdate();
            System.out.println("Inserted Patient successfully.");

        } catch (SQLException e) {
            System.err.println("Error inserting patient: " + e.getMessage());
        }
    }

    public Patient getPatientById(int id) {
        String sql = "SELECT * FROM Patient WHERE patient_id = ?";
        Patient patient = null;

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                patient = new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getInt("age"),
                        rs.getString("gender")
                );
            }

        } catch (SQLException e) {
            System.err.println("Error retrieving patient: " + e.getMessage());
        }

        return patient;
    }

    public void updatePatient(Patient patient) {
        String sql = "UPDATE Patient SET name = ?, age = ?, gender = ?, contact = ? WHERE patient_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, patient.getName());
            stmt.setInt(2, patient.getAge());
            stmt.setString(3, patient.getGender());
            stmt.setString(4, patient.getContact());
            stmt.setInt(5, patient.getId());

            int rows = stmt.executeUpdate();
            System.out.println("Updated Patient, rows affected: " + rows);

        } catch (SQLException e) {
            System.err.println("Error updating patient: " + e.getMessage());
        }
    }

    public void deletePatient(int id) {
        String sql = "DELETE FROM Patient WHERE patient_id = ?";

        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            System.out.println("Deleted Patient, rows affected: " + rows);

        } catch (SQLException e) {
            System.err.println("Error deleting patient: " + e.getMessage());
        }
    }

    public List<Patient> getAllPatients() {
        String sql = "SELECT * FROM Patient";
        List<Patient> patients = new ArrayList<>();

        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Patient patient = new Patient(
                        rs.getInt("patient_id"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getInt("age"),
                        rs.getString("gender")
                );
                patients.add(patient);
            }

        } catch (SQLException e) {
            System.err.println("Error fetching patients: " + e.getMessage());
        }

        return patients;
    }
}