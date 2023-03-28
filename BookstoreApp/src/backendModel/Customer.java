/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backendModel;

/**
 *
 * @author lijac
 */
public class Customer extends Account{
    private String name;
    private String password;
    private int points = 0;
    public Customer(String name, String password, int points) {
        this.name = name;
        this.password = password;
        this.points = points;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public int getPoints() {
        return this.points;
    }
    @Override
    public String toString() {
        return "" + this.name + "," + this.password + "," + this.points;
    }

}
