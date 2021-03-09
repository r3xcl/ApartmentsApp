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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getDateown() {
        return dateown;
    }

    public void setDateown(String dateown) {
        this.dateown = dateown;
    }



}
