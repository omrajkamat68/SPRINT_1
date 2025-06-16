package util;

import model.Appointment;
import java.util.LinkedList;
import java.util.Queue;

public class AppointmentQueue {
    private final Queue<Appointment> queue = new LinkedList<>();

    public void enqueue(Appointment a) {
        queue.add(a);
    }

    public void clear() {
        queue.clear();
    }


    public Appointment peek() {
        return queue.peek();
    }

    public Appointment dequeue() {
        return queue.poll();
    }

    public void processAppointments() {
        while (!queue.isEmpty()) {
            Appointment a = queue.poll();
            a.printAppointment();
        }
    }
}

