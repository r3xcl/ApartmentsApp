package db.Repair;

public class RepairClass {

    String date,sum,id,name,user;

    public RepairClass(String id, String sum, String date, String name,String user) {
        this.id = id;
        this.sum = sum;
        this.date = date;
        this.name = name;
        this.user = user;
    }

    public RepairClass(){

    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSum() {
        return sum;
    }

    public void setSum(String sum) {
        this.sum = sum;
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
