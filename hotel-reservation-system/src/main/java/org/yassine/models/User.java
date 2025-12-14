package org.yassine.models;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class User {
    private int id;
    private int balance;
    private List<Booking> bookingList;
    private LocalDate createdAt;

    public User(int id, int balance, List<Booking> bookingList, LocalDate createdAt) {
        this.id = id;
        this.balance = balance;
        this.bookingList = bookingList;
        this.createdAt = createdAt;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getBalance() {
        return balance;
    }

    public void updateUser(int balance) {
        this.balance = balance;
    }

    public List<Booking> getBookingList() {
        return bookingList;
    }

    public void setBookingList(List<Booking> bookingList) {
        this.bookingList = bookingList;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && balance == user.balance && Objects.equals(bookingList, user.bookingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, balance, bookingList);
    }
}
