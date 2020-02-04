package testat;

import java.io.Serializable;

public class Person implements Serializable {
    private String name;
    private String firstName;
    private String street;
    private int number;
    private String zipcode;
    private String city;

    public Person(String name, String firstName, String street, int number, String zipcode, String city) {
        this.name = name;
        this.firstName = firstName;
        this.street = street;
        this.number = number;
        this.zipcode = zipcode;
        this.city = city;
    }
    public Person(){}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        String temp= this.street +" "+number;
        return temp;
    }
}
