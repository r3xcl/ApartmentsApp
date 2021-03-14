package db.Client;



public class ClientClass {

    String id,surname,name,patronymic,number,datestart,dateend,pay,zastava;

    public ClientClass() {
    }

    public ClientClass(String id, String surname, String name, String patronymic, String number, String datestart, String dateend, String pay,String zastava) {
        this.id = id;
        this.surname = surname;
        this.name = name;
        this.patronymic = patronymic;
        this.number = number;
        this.datestart = datestart;
        this.dateend = dateend;
        this.pay = pay;
        this.zastava = zastava;
    }

    public String getSurname(){return surname;}
    public void setSurname(String surname){this.surname = surname;}

    public String getPatronymic(){return patronymic;}
    public void setPatronymic(String patronymic){this.patronymic = patronymic;}

    public String getName(){return name;}
    public void setName(String name){this.name = name;}

    public String getNumber(){return number;}
    public void setNumber(String number){this.number = number;}

    public String getDatestart(){return datestart;}
    public void setDatestart(String datestart){this.datestart = datestart;}

    public String getDateend(){return dateend;}
    public void setDateend(String dateend){this.dateend = dateend;}

    public String getPay(){return pay;}
    public void setPay(String pay){this.pay = pay;}

    public String getZastava(){return zastava;}
    public void setZastava(String zastava){this.zastava = zastava;}
}
