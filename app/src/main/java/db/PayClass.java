package db;

public class PayClass {

    String pay,date,id,name;

    public PayClass(String id,String pay, String date,String name) {
        this.id = id;
        this.pay = pay;
        this.date = date;
        this.name = name;
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


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }




}
