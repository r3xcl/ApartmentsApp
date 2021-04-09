package db;

public class ApartmentsClass {

    public String id,address,city,district,rooms,floor,dateown,name,size;


    public ApartmentsClass() {
    }

    public ApartmentsClass(String id,String address,String city,String district, String rooms, String floor, String dateown,String name,String size) {
        this.id = id;
        this.address = address;
        this.city = city;
        this.district = district;
        this.rooms = rooms;
        this.floor = floor;
        this.dateown = dateown;
        this.name = name;
        this.size = size;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }
}
