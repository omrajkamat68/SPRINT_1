package dao;

import model.Doctor;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DoctorDAO {

    public void addDoctor(Doctor doctor) {
        if (getDoctorById(doctor.getId()) != null) {
            System.out.println("Doctor ID already exists.");
            return;
        }
        String sql = "INSERT INTO Doctor (doctor_id, name, specialization, contact) VALUES (?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, doctor.getId());
            stmt.setString(2, doctor.getName());
            stmt.setString(3, doctor.getSpecialization());
            stmt.setString(4, doctor.getContact());
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Doctor inserted successfully.");
        } catch (SQLException e) {
            System.err.println("Error inserting doctor: " + e.getMessage());
        }
    }

    public Doctor getDoctorById(int id) {
        String sql = "SELECT * FROM Doctor WHERE doctor_id = ?";
        Doctor doctor = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                doctor = new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("specialization")
                );
            }
        } catch (SQLException e) {
            System.err.println("Error retrieving doctor: " + e.getMessage());
        }
        return doctor;
    }

    public void updateDoctor(Doctor doctor) {
        if (getDoctorById(doctor.getId()) == null) {
            System.out.println("Doctor not found.");
            return;
        }
        String sql = "UPDATE Doctor SET name = ?, specialization = ?, contact = ? WHERE doctor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, doctor.getName());
            stmt.setString(2, doctor.getSpecialization());
            stmt.setString(3, doctor.getContact());
            stmt.setInt(4, doctor.getId());
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Doctor updated successfully.");
        } catch (SQLException e) {
            System.err.println("Error updating doctor: " + e.getMessage());
        }
    }

    public void deleteDoctor(int id) {
        if (getDoctorById(id) == null) {
            System.out.println("Doctor not found.");
            return;
        }
        String sql = "DELETE FROM Doctor WHERE doctor_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) {
                System.out.println("Doctor deleted successfully.");
            }
        } catch (SQLException e) {
            if (e.getMessage().contains("ORA-02292")) {
                System.out.println("Cannot delete doctor. They have associated appointments.");
            } else {
                System.err.println("Error deleting doctor: " + e.getMessage());
            }
        }
    }

    public List<Doctor> getAllDoctors() {
        String sql = "SELECT * FROM Doctor";
        List<Doctor> doctors = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Doctor doctor = new Doctor(
                        rs.getInt("doctor_id"),
                        rs.getString("name"),
                        rs.getString("contact"),
                        rs.getString("specialization")
                );
                doctors.add(doctor);
            }
        } catch (SQLException e) {
            System.err.println("Error fetching doctors: " + e.getMessage());
        }
        return doctors;
    }
}
