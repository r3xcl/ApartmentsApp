package db;

public class PayClass {

    String pay,date;

    public PayClass(String pay, String date) {
        this.pay = pay;
        this.date = date;
    }

    public PayClass(){

    }

    public String getPay() {
        return pay;
    }

    public void setPay(String email) {
        this.pay = email;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String pass) {
        this.date = date;
    }
}
