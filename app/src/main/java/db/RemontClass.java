package db;

public class RemontClass {

    String date,sum,id,name;

    public RemontClass(String id,String sum, String date,String name) {
        this.id = id;
        this.sum = sum;
        this.date = date;
        this.name = name;
    }

    public RemontClass(){

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
