package Models;


import java.util.ArrayList;


public class Room {
    
    private String roomNum;
    private float price;
    private String roomType;
    private int maxGuest;
    private ArrayList <Book> booked;
    private boolean status;




    public Room(String id, float price, String type, int maxGuest) {
        this.roomNum = id;
        this.price = price;
        this.roomType = type;
        this.maxGuest = maxGuest;
    }

    public String getRoomNum() {
        return roomNum;
    }

    public void setRoomNum(String roomNum) {
        this.roomNum = roomNum;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public int getMaxGuest() {
        return maxGuest;
    }

    public void setMaxGuest(int maxGuest) {
        this.maxGuest = maxGuest;
    }
    
    public ArrayList<Book> getBooked() {
        return booked;
    }

    public void setBooked(ArrayList<Book> booked) {
        this.booked = booked;
    }
    
    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }    
}
