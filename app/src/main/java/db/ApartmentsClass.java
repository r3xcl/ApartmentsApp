package db;

public class ApartmentsClass {

    public String id,address,rooms,floor,dateown;

    public ApartmentsClass() {
    }

    public ApartmentsClass(String id,String address, String rooms, String floor, String dateown) {
        this.id = id;
        this.address = address;
        this.rooms = rooms;
        this.floor = floor;
        this.dateown = dateown;
    }
}
