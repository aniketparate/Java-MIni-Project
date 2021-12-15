package application;

import java.sql.Time;

public class passenger {
    String passengerName, gender, trainName, Source, Destination, Username, Status;
    int age, trainNo;
    Time sourceTime, destinationTime;

    public String getPassengerName() {
        return passengerName;
    }

    public String getGender() {
        return gender;
    }

    public String getTrainName() {
        return trainName;
    }

    public String getSource() {
        return Source;
    }

    public String getDestination() {
        return Destination;
    }

    public String getUsername() {
        return Username;
    }

    public String getStatus() {
        return Status;
    }

    public int getAge() {
        return age;
    }

    public int getTrainNo() {
        return trainNo;
    }

    public Time getSourceTime() {
        return sourceTime;
    }

    public Time getDestinationTime() {
        return destinationTime;
    }

    public passenger(String passengerName, String gender, String trainName, String source, String destination, String username, String status, int age, int trainNo, Time sourceTime, Time destinationTime) {
        this.passengerName = passengerName;
        this.gender = gender;
        this.trainName = trainName;
        this.Source = source;
        this.Destination = destination;
        this.Username = username;
        this.Status = status;
        this.age = age;
        this.trainNo = trainNo;
        this.sourceTime = sourceTime;
        this.destinationTime = destinationTime;
    }
}
