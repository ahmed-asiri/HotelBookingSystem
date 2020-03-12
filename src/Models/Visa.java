package Models;


import java.util.Date;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ia7ma
 */
public class Visa {
    
    private Date expire;
    private String card_num;
    private int cvc;

    public Visa(Date expire, String card_num, int cvc) {
        this.expire = expire;
        this.card_num = card_num;
        this.cvc = cvc;
    }
    
    public Visa() {
        
    }
    public Date getExpire() {
        return expire;
    }

    public void setExpire(Date expire) {
        this.expire = expire;
    }

    public String getCard_num() {
        return card_num;
    }

    public void setCard_num(String card_num) {
        this.card_num = card_num;
    }

    public int getCvc() {
        return cvc;
    }

    public void setCvc(int cvc) {
        this.cvc = cvc;
    }
    
}
