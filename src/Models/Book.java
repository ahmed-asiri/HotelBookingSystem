package Models;


import java.util.ArrayList;
import java.util.Date;
import java.util.Random;
import static java.util.concurrent.TimeUnit.DAYS;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ia7ma
 */
public class Book {

    private int id;
    private Customer cus;
    private Hotel hotel;
    private Room room;
    private float total_price;
    private Date in;
    private Date out;
    ArrayList <Room> rooms;
     
    public Book( Customer cus, Hotel hotel, Room room, Date in, Date out) {
        // we use this cunstructor when we create the book after confirming payment process.
        this.id = bookIDGenerator();
        this.cus = cus;
        this.hotel = hotel;
        this.room = room;
        this.in = in;
        this.out = out;
        
    }
    public Book(int id, Customer cus, Hotel hotel, Room room, Date in, Date out) {
        // this cunstructor used when we get it from the database.
        this.id = id;
        this.cus = cus;
        this.hotel = hotel;
        this.room = room;
        this.in = in;
        this.out = out;
        
    }
    public Book() {
        // using this in some cases or temporary cases.
        this.id = bookIDGenerator();
    }

    public float getTotalPrice() {
        // calculating the total price of the all nights that user have been choosed.
        long days = out.getTime() - in.getTime();
        this.total_price = (days/ (1000*60*60*24)) * room.getPrice(); 
        return total_price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Customer getCus() {
        return cus;
    }

    public void setCus(Customer cus) {
        this.cus = cus;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public Date getIn() {
        return in;
    }

    public void setIn(Date in) {
        this.in = in;
    }

    public Date getOut() {
        return out;
    }

    public void setOut(Date out) {
        this.out = out;
    }
    
    private static int bookIDGenerator(){
    // generate random number, it's used for the book ID.
    Random random = new Random();
    return random.ints(0000000000,(999999999+1)).findFirst().getAsInt();
}
    
}
