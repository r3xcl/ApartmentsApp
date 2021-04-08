package db.Client;



public class ClientClass {

    String id,surname,name,patronymic,number,email,datestart,dateend,pay,zastava,busyness,user;

    public ClientClass() {
    }



    public ClientClass(String id, String surname, String name, String patronymic, String number, String email, String datestart, String dateend, String pay, String zastava, String busyness, String user) {
        this.id = id;
        this.surname = surname;
        this.email = email;
        this.name = name;
        this.patronymic = patronymic;
        this.number = number;
        this.datestart = datestart;
        this.dateend = dateend;
        this.pay = pay;
        this.zastava = zastava;
        this.busyness = busyness;
        this.user = user;
    }

    public String getSurname(){return surname;}
    public void setSurname(String surname){this.surname = surname;}

    public String getEmail(){return email;}
    public void setEmail(String email){this.email = email;}

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

    public String getBusyness() {
        return busyness;
    }

    public void setBusyness(String busyness) {
        this.busyness = busyness;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
}
