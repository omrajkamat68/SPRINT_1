package dao;

import model.MedicalCase;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MedicalCaseDAO {

    public void addMedicalCase(MedicalCase medicalCase) {
        String sql = "INSERT INTO MedicalCase (case_id, patient_id, diagnosis, treatment, case_date) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, medicalCase.getCaseId());
            stmt.setInt(2, medicalCase.getPatientId());
            stmt.setString(3, medicalCase.getDiagnosis());
            stmt.setString(4, medicalCase.getTreatment());
            stmt.setDate(5, Date.valueOf(medicalCase.getCaseDate()));
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
            System.err.println(e.getMessage());
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
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
        }
    }

    public void deleteMedicalCase(int id) {
        String sql = "DELETE FROM MedicalCase WHERE case_id = ?";
        try (Connection conn = DBConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println(e.getMessage());
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
            System.err.println(e.getMessage());
        }
        return cases;
    }
}

