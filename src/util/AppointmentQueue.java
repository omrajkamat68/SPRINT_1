package util;

import model.Appointment;
import java.util.LinkedList;
import java.util.Queue;

public class AppointmentQueue {
    private Queue<Appointment> queue = new LinkedList<>();

    public void enqueue(Appointment a) {
        queue.add(a);
    }

    public void processAppointments() {
        while (!queue.isEmpty()) {
            Appointment a = queue.poll();
            a.printAppointment();
        }
    }
}
