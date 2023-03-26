/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendModel;

/**
 *
 * @author mahir
 */
public class silverMember implements State {

    @Override
    public double calculateDiscount(double bookPrice, int currentPoints) {
        //check if the customer can buy the book
        if (bookPrice * 100 <= currentPoints) {
            //the book is bought with points only
            currentPoints = (int) (currentPoints - (bookPrice * 100.00));
            return bookPrice;
        } else if (bookPrice * 100 > currentPoints) {
            //Only a part of the price is payed off with points
            bookPrice = bookPrice - currentPoints / 100;
            //REMEMBER TO GIVE POINTS FOR BOUGHT BOOK
            return bookPrice;
        } else {
            return 0;
        }

    }
    //REMEMBER TO IMPLMENT THE CHANGE TO GOLD MEMBER IS MORE THAN 1K POITNS!
}
