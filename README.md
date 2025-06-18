# 🏥 MediConnect CRM – Sprint 1

MediConnect CRM is a Java-based backend project for managing patient interactions and case tracking in a healthcare setup. This sprint focused on building and testing the core modules including Patient, Doctor, Appointment, and Medical Case management using OracleDB and JDBC.

---

## 🚀 Sprint Goal

> To build and test the core backend modules of the MediConnect CRM, including patient, doctor, appointment, and medical case management, with full CRUD support and appointment queueing — integrated via JDBC with OracleDB.

---

## 🔗 Video Demonstration

🎥 Watch the working demo here: https://drive.google.com/file/d/1-5fdk-hM85HSI3kE7323xyU3WB2UgYp1/view?usp=sharing  

---

## ✅ Features Implemented

- 👨‍⚕️ **Doctor Management**  
  - Add, View, Update, Delete Doctor

- 🧑‍⚕️ **Patient Management**  
  - Add, View, Update, Delete Patient

- 📅 **Appointment System**  
  - Add, View, Update, Delete Appointments  
  - Queue system for processing appointments  
  - Real-time appointment display in CLI

- 📝 **Medical Case Management**  
  - Add, View, Update, Delete Medical Cases linked to Patients

- 📤 **OracleDB Integration**  
  - JDBC-based integration for all modules  
  - SQL schema-based table setup

- 🛡️ **Validation & Error Handling**  
  - Input validations (ID existence, date/time formats)  
  - Try-catch for parsing and DB operations

---

## 🗂️ Project Structure

```
src/
├── dao/               # DAO classes (DoctorDAO, PatientDAO, etc.)
├── model/             # POJO models (Doctor, Patient, Appointment, etc.)
├── service/           # Business logic (AppointmentService)
├── util/              # AppointmentQueue structure
├── Main.java          # CLI entry point
└── sql/schema.sql     # SQL table creation scripts
```

---

## 🛠️ Tech Stack

- **Java** – Core Java with OOP principles  
- **OracleDB** – Relational database  
- **JDBC** – Java Database Connectivity  
- **IntelliJ IDEA** – IDE  
- **SQL Developer** – Oracle DB management

---

## 🧪 How to Run

1. 📦 Clone the repo:  
   ```bash
   git clone https://github.com/omrajkamat68/SPRINT_1.git
   cd SPRINT_1
   ```

2. 🛠️ Setup OracleDB:  
   - Execute the `sql/schema.sql` file to create tables

3. ⚙️ Configure DB credentials in `DBConnection.java`

4. ▶️ Run `Main.java`  
   - Use the CLI menu to interact with the system

---

## 📸 Screenshots

![Screenshot 2025-06-17 101841](https://github.com/user-attachments/assets/c992da79-69a5-40fd-8822-761d1b52b763) 
![Screenshot 2025-06-17 101912](https://github.com/user-attachments/assets/95141684-4e1e-403b-b7ea-e6fc6536b55a)
![Screenshot 2025-06-17 102118](https://github.com/user-attachments/assets/f61a6b48-2b57-4ebd-aa44-195b68224ecd)
![Screenshot 2025-06-17 102152](https://github.com/user-attachments/assets/bcf1f026-7b15-498d-8401-308139a600b2)
![Screenshot 2025-06-17 102235](https://github.com/user-attachments/assets/e1d621a6-1ab5-46ae-ae47-34e5c25151b7)
![Screenshot 2025-06-17 102248](https://github.com/user-attachments/assets/33df327e-d4de-4c99-b9b8-9e177ebee9ba)
![image](https://github.com/user-attachments/assets/7bfe0d13-519e-49f5-8d62-90e9d663f9de)

---

## 🧑‍💻 Sprint Pod Members

- Mohammed Saif Ali  
- Nachu Praveen Kumar  
- Neela Bhavana  
- Omraj Kamat  
- Pasupuleti Venkata Vasantha Lakshmi  
- Pavan Kumar  
- Prachi Arya  
- Prateek Rane

---

## 📅 Submission Date

> **17/06/2025**

---

> 🧠 _This README reflects the backend deliverables for Sprint 1 of MediConnect CRM. Future sprints may extend into UI, authentication, or analytics modules._
