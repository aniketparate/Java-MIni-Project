package application;

import java.sql.Time;

public class confirmPassenger {
    String name, gender, source, destination, booking_status;
    int age;
    Time scheduled_departure, scheduled_arrival;

    public String getName() {
        return name;
    }

    public String getGender() {
        return gender;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getBooking_status() {
        return booking_status;
    }

    public int getAge() {
        return age;
    }

    public Time getScheduled_departure() {
        return scheduled_departure;
    }

    public Time getScheduled_arrival() {
        return scheduled_arrival;
    }

    public confirmPassenger(String name, String gender, String source, String destination, String booking_status, int age, Time scheduled_departure, Time scheduled_arrival) {
        this.name = name;
        this.gender = gender;
        this.source = source;
        this.destination = destination;
        this.booking_status = booking_status;
        this.age = age;
        this.scheduled_departure = scheduled_departure;
        this.scheduled_arrival = scheduled_arrival;
    }
}
