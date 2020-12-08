package ru.medvedev.pract16.background;

public final class Address {

    private String cityName;
    private String streetName;
    private int    buildingNumber;
    private int    apartmentNumber;
    private int    zipCode;
    private char   buildingLetter;
    public final Address EMPTY_ADDRESS = null;

    public Address(){
    }

    public Address(String cityName, String streetName, int buildingNumber, int apartmentNumber, int zipCode, char buildingLetter){
        this.cityName = cityName;
        this.streetName = streetName;
        this.buildingNumber = buildingNumber;
        this.apartmentNumber = apartmentNumber;
        this.zipCode = zipCode;
        this.buildingLetter = buildingLetter;

    }


    public char getBuildingLetter() {
        return buildingLetter;
    }

    public int getApartmentNumber() {
        return apartmentNumber;
    }

    public int getBuildingNumber() {
        return buildingNumber;
    }

    public int getZipCode() {
        return zipCode;
    }

    public String getCityName() {
        return cityName;
    }

    public String getStreetName() {
        return streetName;
    }
}
