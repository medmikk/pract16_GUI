package ru.medvedev.pract16.background;

public final class Customer {
    private final String firstName;
    private final String secondName;
    private final int age;
    private final Address address;
    //private final Customer MATURE_UNKNOWN_CUSTOMER     = new Customer(null, null, 18, null);
    //private final Customer NOT_MATURE_UNKNOWN_CUSTOMER = new Customer(null, null, 10, null);

    public Customer(String firstName, String secondName, int age, Address address){
        this.address = address;
        this.age = age;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public int getAge() {
        return age;
    }

    public Address getAddress() {
        return address;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    @Override
    public String toString() {
        return firstName + " " + secondName;
    }
}
