package dao;

import model.MedicalCase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalCaseDAO {

    public boolean exists(int caseId) {
        String sql = "SELECT 1 FROM MedicalCase WHERE case_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, caseId);
            ResultSet rs = stmt.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            System.out.println("Error checking existence: " + e.getMessage());
            return false;
        }
    }

    public void addMedicalCase(MedicalCase medicalCase) {
        if (exists(medicalCase.getCaseId())) {
            System.out.println("Medical case ID already exists.");
            return;
        }
        String sql = "INSERT INTO MedicalCase (case_id, patient_id, diagnosis, treatment, case_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, medicalCase.getCaseId());
            stmt.setInt(2, medicalCase.getPatientId());
            stmt.setString(3, medicalCase.getDiagnosis());
            stmt.setString(4, medicalCase.getTreatment());
            stmt.setDate(5, Date.valueOf(medicalCase.getCaseDate()));
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Medical case added successfully.");
        } catch (SQLException e) {
            System.out.println("Error adding medical case: " + e.getMessage());
        }
    }

    public MedicalCase getMedicalCaseById(int id) {
        String sql = "SELECT * FROM MedicalCase WHERE case_id = ?";
        MedicalCase medicalCase = null;
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                medicalCase = new MedicalCase(
                        rs.getInt("case_id"),
                        rs.getInt("patient_id"),
                        rs.getString("diagnosis"),
                        rs.getString("treatment"),
                        rs.getDate("case_date").toLocalDate()
                );
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving case: " + e.getMessage());
        }
        return medicalCase;
    }

    public void updateMedicalCase(MedicalCase medicalCase) {
        String sql = "UPDATE MedicalCase SET patient_id = ?, diagnosis = ?, treatment = ?, case_date = ? WHERE case_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, medicalCase.getPatientId());
            stmt.setString(2, medicalCase.getDiagnosis());
            stmt.setString(3, medicalCase.getTreatment());
            stmt.setDate(4, Date.valueOf(medicalCase.getCaseDate()));
            stmt.setInt(5, medicalCase.getCaseId());
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Medical case updated.");
            else System.out.println("No update occurred.");
        } catch (SQLException e) {
            System.out.println("Error updating medical case: " + e.getMessage());
        }
    }

    public void deleteMedicalCase(int id) {
        String sql = "DELETE FROM MedicalCase WHERE case_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            int rows = stmt.executeUpdate();
            if (rows > 0) System.out.println("Medical case deleted.");
            else System.out.println("No deletion occurred.");
        } catch (SQLException e) {
            System.out.println("Error deleting medical case: " + e.getMessage());
        }
    }

    public List<MedicalCase> getAllMedicalCases() {
        String sql = "SELECT * FROM MedicalCase";
        List<MedicalCase> cases = new ArrayList<>();
        try (Connection conn = DBConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                MedicalCase medicalCase = new MedicalCase(
                        rs.getInt("case_id"),
                        rs.getInt("patient_id"),
                        rs.getString("diagnosis"),
                        rs.getString("treatment"),
                        rs.getDate("case_date").toLocalDate()
                );
                cases.add(medicalCase);
            }
        } catch (SQLException e) {
            System.out.println("Error listing cases: " + e.getMessage());
        }
        return cases;
    }
}



