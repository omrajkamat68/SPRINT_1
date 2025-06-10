CREATE TABLE Patient (
    patient_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    age NUMBER,
    gender VARCHAR2(10),
    contact VARCHAR2(20)
);

CREATE TABLE Doctor (
    doctor_id NUMBER PRIMARY KEY,
    name VARCHAR2(100),
    specialization VARCHAR2(50),
    contact VARCHAR2(20)
);

CREATE TABLE Appointment (
    appointment_id NUMBER PRIMARY KEY,
    patient_id NUMBER,
    doctor_id NUMBER,
    appointment_time TIMESTAMP,
    reason VARCHAR2(255),
    CONSTRAINT fk_patient FOREIGN KEY (patient_id) REFERENCES Patient(patient_id),
    CONSTRAINT fk_doctor FOREIGN KEY (doctor_id) REFERENCES Doctor(doctor_id)
);

CREATE TABLE MedicalCase (
    case_id NUMBER PRIMARY KEY,
    patient_id NUMBER,
    diagnosis CLOB,
    treatment CLOB,
    case_date DATE,
    CONSTRAINT fk_case_patient FOREIGN KEY (patient_id) REFERENCES Patient(patient_id)
);
