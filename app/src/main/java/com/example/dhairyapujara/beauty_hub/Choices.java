package com.example.dhairyapujara.beauty_hub;


import android.util.Log;

import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Dhairya Pujara on 6/28/2015.
 */
public class Choices
{

    public static ArrayList<String> brands = new ArrayList<>();
    public static ArrayList<String> prices = new ArrayList<>();
    public static ArrayList<String> discounts = new ArrayList<>();



    public Choices(){
    }


    public static ArrayList<String> getPrices() {


        return prices;
    }

    public static void setPrices(ArrayList<String> price) {
        prices = price;
        //Log.d("Choice_pri", String.valueOf(price));
    }

    public static ArrayList<String> getBrands() {
        return brands;
    }

    public static void setBrands(ArrayList<String> brand) {
        brands = brand;
        //Log.d("Choice_bra", String.valueOf(brands));
    }

    public static ArrayList<String> getDiscounts() {
        return discounts;
    }

    public static void setDiscounts(ArrayList<String> discount) {
        discounts = discount;
    }
}

class A
{
    private static ArrayList<String> str = new ArrayList<>();
    private static ArrayList<String> str1 = new ArrayList<>();


    public static void main(String[]args)throws IOException
    {
        str.add("dp");
        Choices ob = new Choices();
        ob.setBrands(str);

        str1 = ob.getBrands();
        System.out.println(""+str1);

        String str=" abc d 1234567890pqr 54897";
        Pattern pattern = Pattern.compile("\\w+([0-9]+)\\w+([0-9]+)");
        Matcher matcher = pattern.matcher(str);
        for(int i = 0 ; i < matcher.groupCount(); i++) {
            matcher.find();
            System.out.println(matcher.group());
        }


    }
}
