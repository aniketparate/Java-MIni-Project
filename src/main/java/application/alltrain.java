package application;

public class alltrain {
    int train_no;
    String train_name, route;

    public int getTrain_no() {
        return train_no;
    }

    public String getTrain_name() {
        return train_name;
    }

    public String getRoute() {
        return route;
    }

    public alltrain(int train_no, String train_name, String route) {
        this.train_no = train_no;
        this.train_name = train_name;
        this.route = route;
    }
}
