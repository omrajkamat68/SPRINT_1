package model;

import java.time.LocalDate;

public class MedicalCase {
    private int caseId;
    private int patientId;
    private String diagnosis;
    private String treatment;
    private LocalDate caseDate;

    public MedicalCase(int caseId, int patientId, String diagnosis, String treatment, LocalDate caseDate) {
        this.caseId = caseId;
        this.patientId = patientId;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.caseDate = caseDate;
    }

    public int getCaseId() { return caseId; }
    public int getPatientId() { return patientId; }
    public String getDiagnosis() { return diagnosis; }
    public String getTreatment() { return treatment; }
    public LocalDate getCaseDate() { return caseDate; }

    public void printCaseDetails() {
        System.out.printf("Case ID: %d | Patient ID: %d | Date: %s\nDiagnosis: %s\nTreatment: %s\n",
                caseId, patientId, caseDate.toString(), diagnosis, treatment);
    }

    @Override
    public String toString() {
        return String.format(
                "Case ID: %d | Patient ID: %d | Date: %s\nDiagnosis: %s\nTreatment: %s",
                caseId, patientId, caseDate.toString(), diagnosis, treatment
        );
    }
}
