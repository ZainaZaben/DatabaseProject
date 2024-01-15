package sample;

import java.io.InputStream;

public class mylist {
    private String ssn;
    private String fname;
    private String lname;
    private String donate_for;
    private int money;
    private String money_or_item;


    public mylist() {
        this.ssn = "";
        this.fname = "";
        this.lname = "";
        this.donate_for = "";
        this.money = 0;
        this.money_or_item = "";

    }

    public mylist(String ssn, String fname, String lname, String donate_for, int money, String money_or_item) {
        this.ssn = ssn;
        this.fname = fname;
        this.lname = lname;
        this.donate_for = donate_for;
        this.money = money;
        this.money_or_item = money_or_item;
    }

    public String getSsn() {
        return ssn;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getDonate_for() {
        return donate_for;
    }

    public int getMoney() {
        return money;
    }

    public String getMoney_or_item() {
        return money_or_item;
    }



}

