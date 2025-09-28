package co.com.raullondono.domain;

import java.time.LocalDate;

public class Trainee extends User {

    private LocalDate dateOfBirth;
    private String address;
    private String phoneNumber;

    public Trainee() {

    }

    public Trainee(User user, LocalDate dateOfBirth, String address, String phoneNumber) {
        super(user.getUserId(),
                user.getFirstName(),
                user.getLastName(),
                user.getUsername(),
                user.getPassword(),
                user.getIsActive());
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public Trainee(Long userId,
                   String firstName,
                   String lastName,
                   String username,
                   String password,
                   Boolean isActive,
                   LocalDate dateOfBirth,
                   String address,
                   String phoneNumber) {
        super(userId, firstName, lastName, username, password, isActive);
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.phoneNumber = phoneNumber;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

}
