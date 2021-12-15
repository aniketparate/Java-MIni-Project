package application;

import java.sql.Time;

public class train {
    String train_name, source_name, destination_name;
    int train_no;
    Time source_time, destination_time;
    public int getTrain_no() {
        return train_no;
    }

    public String getTrain_name() {
        return train_name;
    }

    public String getSource_name() {
        return source_name;
    }

    public Time getSource_time() {
        return source_time;
    }

    public String getDestination_name() {
        return destination_name;
    }

    public Time getDestination_time() {
        return destination_time;
    }

    public train(int train_no, String train_name, String source_name, Time source_time, String destination_name, Time destination_time) {
        this.train_no = train_no;
        this.train_name = train_name;
        this.source_name = source_name;
        this.source_time = source_time;
        this.destination_name = destination_name;
        this.destination_time = destination_time;
    }
}
