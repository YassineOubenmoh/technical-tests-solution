package org.yassine.models;

import org.yassine.enums.RoomType;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.Objects;

public class Booking {
    private static int autoIncrementId = 0;

    private int bookingId;
    private LocalDate checkIn;
    private LocalDate checkOut;
    private int userId;
    private int roomNumber;
    private RoomType roomType;
    private int roomPricePerNight;
    private long nightsNumber;
    private long totalPrice;
    private LocalDate createdAt;


    public Booking(LocalDate checkIn, LocalDate checkOut, int userId, int roomNumber, RoomType roomType, int roomPricePerNight, LocalDate createdAt) {
        autoIncrementId++;
        this.bookingId = autoIncrementId;
        this.checkIn = checkIn;
        this.checkOut = checkOut;
        this.userId = userId;
        this.roomNumber = roomNumber;
        this.roomType = roomType;
        this.roomPricePerNight = roomPricePerNight;
        this.nightsNumber = ChronoUnit.DAYS.between(checkIn, checkOut);
        this.totalPrice = nightsNumber * roomPricePerNight;
        this.createdAt = createdAt;
    }

    public int getBookingId() {
        return bookingId;
    }

    public void setBookingId(int bookingId) {
        this.bookingId = bookingId;
    }

    public LocalDate getCheckIn() {
        return checkIn;
    }

    public long getNightsNumber() {
        return nightsNumber;
    }

    public void setNightsNumber(int nightsNumber) {
        this.nightsNumber = nightsNumber;
    }

    public void setCheckIn(LocalDate checkIn) {
        this.checkIn = checkIn;
    }

    public LocalDate getCheckOut() {
        return checkOut;
    }

    public void setCheckOut(LocalDate checkOut) {
        this.checkOut = checkOut;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getRoomPricePerNight() {
        return roomPricePerNight;
    }

    public void setRoomPricePerNight(int roomPricePerNight) {
        this.roomPricePerNight = roomPricePerNight;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    public long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return bookingId == booking.bookingId && userId == booking.userId && roomNumber == booking.roomNumber && roomPricePerNight == booking.roomPricePerNight && Objects.equals(checkIn, booking.checkIn) && Objects.equals(checkOut, booking.checkOut) && roomType == booking.roomType && Objects.equals(createdAt, booking.createdAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(bookingId, checkIn, checkOut, userId, roomNumber, roomType, roomPricePerNight, createdAt);
    }
}
