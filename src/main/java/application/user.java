package application;

public class user {
    String u_username, u_password, u_firstname, u_lastname, u_email, u_phone, u_state, u_city;
    int u_pincode;

    public String getU_username() {
        return u_username;
    }

    public String getU_password() {
        return u_password;
    }

    public String getU_firstname() {
        return u_firstname;
    }

    public String getU_lastname() {
        return u_lastname;
    }

    public String getU_email() {
        return u_email;
    }

    public String getU_phone() {
        return u_phone;
    }

    public String getU_state() {
        return u_state;
    }

    public String getU_city() {
        return u_city;
    }

    public int getU_pincode() {
        return u_pincode;
    }

    public user(String u_username, String u_password, String u_firstname, String u_lastname, String u_email, String u_phone, String u_state, String u_city, int u_pincode) {
        this.u_username = u_username;
        this.u_password = u_password;
        this.u_firstname = u_firstname;
        this.u_lastname = u_lastname;
        this.u_email = u_email;
        this.u_phone = u_phone;
        this.u_state = u_state;
        this.u_city = u_city;
        this.u_pincode = u_pincode;
    }
}