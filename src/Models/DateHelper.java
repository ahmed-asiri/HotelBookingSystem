package Models;


import Models.connectionDB;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


//this class is for date calculation methods
public class DateHelper {
 
    
    public static String DatetoString(Date date){
        //convert the date into String and return the result.
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        String DateString = formatter.format(date);
        return DateString;
    }

    

}
