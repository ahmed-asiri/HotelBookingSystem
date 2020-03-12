/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Models;

import static Models.Customer.con;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author ia7ma
 */
public class HotelSearch {
    
    private static ArrayList<Hotel> hotels; // when the user search in specific date and city, this list will get all the available hotels on his filtered search.
    
    
        // this method will return all the hotels available accroding to the customer search filters.
        public static ArrayList<Hotel> Search(Date in, Date out, String city) throws SQLException {
        
        // the process of serching in the database for specific room on specific data and city.
        hotels = new ArrayList<Hotel>();
        //getting the hotels that have available rooms in that day, and in the same city he choose.
        ResultSet rs1 = con.select("select * from hotel where id = any (select hotel_id from room where id not in \n"
                + "(select room_id from booking where check_in between to_date('" + DateHelper.DatetoString(in)
                + "','dd-MM-yyyy')\n" + " and to_date('" + DateHelper.DatetoString(out)
                + "','dd-MM-yyyy') or check_out between \n" + "to_date('" + DateHelper.DatetoString(in)
                + "','dd-MM-yyyy') \n" + "and to_date('" + DateHelper.DatetoString(out)
                + "','dd-MM-yyyy'))) and city = '" + city + "'");

        while (rs1.next()) {
            // in this loop, we get all the available rooms in a hotel.
            Hotel hotel = new Hotel(new Address(rs1.getString("country"), rs1.getString("city"), rs1.getString("zip")), rs1.getString("id"), rs1.getString("name"), rs1.getString("phone"), rs1.getString("email"));
            System.out.println(hotel.getName());
            // this query to get all the available rooms in the hotel from the previous hotel object.
            ResultSet rs2 = con.select("select * from room where id not in(select room_id from booking where check_in between to_date('"
                    + DateHelper.DatetoString(in) + "','dd-MM-yyyy') and to_date('" + DateHelper.DatetoString(out)
                    + "','dd-MM-yyyy') or check_out between to_date('" + DateHelper.DatetoString(in)
                    + "','dd-MM-yyyy') and to_date('" + DateHelper.DatetoString(out)
                    + "','dd-MM-yyyy')) and hotel_id =" + Integer.parseInt(rs1.getString("id")));
            List<Room> rooms = hotel.getRooms();
            while (rs2.next()) {
                
                Room room = new Room(rs2.getString("id"), rs2.getFloat("price"), rs2.getString("type"), rs2.getInt("guest"));
                System.out.println(room.getRoomNum());
                // adding all the rooms into the list
                rooms.add(room);

            }
            // after getting all the available rooms in a hotel, we add the hotel in the hotels list.
            hotels.add(hotel);
        }
        return hotels;
    }
}
