package service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AppointmentService {
    public static LocalDateTime parseAppointmentTime(String timeStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        return LocalDateTime.parse(timeStr, formatter);
    }

    public static void displayScheduledTime(LocalDateTime time) {
        System.out.println("Scheduled Appointment Time: " + time.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
    }
}
