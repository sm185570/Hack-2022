package com.machinemake.NCRed.data;

public class Card
{
    public String Name;
    public String Number;
    public String expiryDate;
    public int cvv;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getNumber() {
        return Number;
    }

    public void setNumber(String number) {
        Number = number;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    public int getCvv() {
        return cvv;
    }

    public void setCvv(int cvv) {
        this.cvv = cvv;
    }

    public Card(String name, String number, String expiryDate, int cvv) {
        Name = name;
        Number = number;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
    }
}
