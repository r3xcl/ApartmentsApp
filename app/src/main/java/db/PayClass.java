package db;

public class PayClass {

    String pay,date,id;

    public PayClass(String id,String pay, String date) {
        this.id = id;
        this.pay = pay;
        this.date = date;
    }

    public PayClass(){

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPay() {
        return pay;
    }

    public void setPay(String pay) {
        this.pay = pay;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }





}
