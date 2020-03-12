package Models;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random; 
import java.util.logging.Level;
import java.util.logging.Logger;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ia7ma
 */
public class Customer extends User {

    private ArrayList<Book> booked; // all the user previous booked.
    private Visa visa; 
    private ResultSet rs; // to store the data set each time in it, instead of define it more than ince in each method.
    static connectionDB con = new connectionDB();

    public ArrayList<Book> getBooked() {
        return booked;
    }

    public void setBooked(ArrayList<Book> booked) {
        this.booked = booked;
    }

    public Customer() {
        super();
        booked = new ArrayList<Book>();
        this.visa = new Visa();
        con.setConnection();
    }

    public Customer(Visa visa, String password, String ssn, String fname, String lname, String email, String phone) throws SQLException {
        super(password, ssn, fname, lname, email, phone);
        booked = new ArrayList<Book>();
        con.setConnection();
        this.visa = visa;
        Register(visa, password, ssn, fname, lname, email, phone);
    }

    @Override
    public boolean login(String ssn, String pass) {
        // go to DB to check if the entered pass and ssn is there or not, if they correct get the data and put it in the attributes.
        String sql = "select * from customer where ssn = " + ssn + " and password = " + pass;
        rs = con.select(sql);
        try {
            while (rs.next()) {
                this.setSsn(rs.getString("ssn"));
                this.setEmail(rs.getString("email"));
                this.setFname(rs.getString("fname"));
                this.setLname(rs.getString("lname"));
                this.setPhone(rs.getString("phone"));
                this.visa.setCard_num(rs.getString("visa_num"));
                this.visa.setExpire(rs.getDate("visa_exp"));
                this.visa.setCvc(rs.getInt("cvc"));
                System.out.println("came here");
                return true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(Customer.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    public void setVisa(Visa visa) {
        this.visa = visa;
    }

    public Visa getVisa() {
        return visa;
    }

    public static boolean isExist(String ssn) throws SQLException {
        // check if the user SSN exist in the system or not.
        String sql = "select * from customer where ssn = " + ssn;
        ResultSet rs = con.select(sql);
        if (rs.next()) {
            System.out.println("exist");
            return true;
        } else {
            System.out.println("new user");
        }
        return false;
    }

    public boolean Register(Visa visa, String password, String ssn, String fname, String lname, String email, String phone) throws SQLException {
        // for rigestration process of new customer.
        boolean status = Customer.isExist(ssn);
        if (!status) {
            status = con.insert("insert into customer values('" + ssn + "', '" + password + "', '" + fname + "', '" + lname + "', '" + email + "', '" + phone + "', "
                    + visa.getCard_num() + ", to_date('" + DateHelper.DatetoString(visa.getExpire()) + "','dd-MM-yyyy'), " + visa.getCvc() + ")");
            return true;
        } else {
            return false;
        }   
    }

    public void updateInfo(String selectedAttribute, String value) {
        // update customer information based on the selected field that he want to update from the GUI.
        String sql = "update customer set ";
        if (selectedAttribute.equalsIgnoreCase("First Name:")) {
            this.setFname(value);
            sql += "fname = '";
        } else if (selectedAttribute.equalsIgnoreCase("Last Name:")) {
            this.setLname(value);
            sql += "lname = '";
        } else if (selectedAttribute.equalsIgnoreCase("Phone:")) {
            this.setPhone(value);
            sql += "phone = '";
        } else if (selectedAttribute.equalsIgnoreCase("Email:")) {
            this.setEmail(value);
            sql += "email = '";
        }
        sql += value + "' where ssn = '" + this.getSsn() + "'";
        con.update(sql);

    }



    public boolean payment(int cvc) {
        // check if the entered CVC is equal to the existing CVC or not.
        if (cvc == this.visa.getCvc()) {
            return true;
        }
        return false;
    }

    public void book(Book book) {
        //creating new book, and generating unique id for that book.
        String sql = "insert into booking values (" + book.getId() + ", '" + this.getSsn() + "', " + book.getRoom().getRoomNum()
                + ", to_date('" + DateHelper.DatetoString(book.getIn()) + "','dd-MM-yyyy'), to_date('" + DateHelper.DatetoString(book.getOut()) + "','dd-MM-yyyy'))";
        con.insert(sql);
        booked.add(book);
    }
    

    
    public void getBookings() throws SQLException {
        // fetch all the bookings that user have in the database.
        booked.clear();
        String sql = "select * from booking where cus_id = '" + this.getSsn() + "'";
        ResultSet rs = con.select(sql);

        while (rs.next()) {
            System.out.println(rs);
            String room = rs.getString("room_id");
            ResultSet roomSet = con.select("select * from room where id = " + room);
            roomSet.next();
            Room roomTemp = new Room(roomSet.getString("id"), roomSet.getFloat("price"), roomSet.getString("type"), roomSet.getInt("guest"));
            ResultSet hotelSet = con.select("select * from hotel where id = " + roomSet.getInt("hotel_id"));
            hotelSet.next();
            Address addressTemp = new Address(hotelSet.getString("country"), hotelSet.getString("city"), hotelSet.getString("zip"));
            Hotel hotelTemp = new Hotel(addressTemp, hotelSet.getString("id"), hotelSet.getString("name"), hotelSet.getString("phone"), hotelSet.getString("email"));
            booked.add(new Book(rs.getInt("id"), this, hotelTemp, roomTemp, rs.getDate("check_in"), rs.getDate("check_out")));
        }

    }

    public boolean cancelBook(int bookId) {
        //cancel booked room, only if it's before the Check_in date.
        int index = -1;
        for (Book temp : this.booked) {
            index++;
            if (temp.getId() == bookId) {

                String sql = "delete from booking where id = " + bookId;
                con.delete(sql);
                this.booked.remove(index);
                return true;

            }
        }
        return false;
    }
}
