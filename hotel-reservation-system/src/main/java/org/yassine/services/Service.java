package org.yassine.services;

import org.yassine.enums.RoomType;
import org.yassine.exceptions.*;
import org.yassine.models.Booking;
import org.yassine.models.Room;
import org.yassine.models.User;

import java.time.LocalDate;
import java.util.*;

public class Service {

    private final List<Room> rooms;
    private final List<User> users;

    public Service(List<Room> rooms, List<User> users) {
        this.rooms = rooms;
        this.users = users;
    }


    public boolean roomIsOccupied(LocalDate checkIn, LocalDate checkOut, int roomNumber) {

        List<Booking> bookingList = findBookingByRoomNumber(roomNumber)
                .orElse(Collections.emptyList());

        return bookingList.stream().anyMatch(booking ->
                booking.getCheckIn().isBefore(checkOut) &&
                        booking.getCheckOut().isAfter(checkIn)
        );
    }

    public Optional<Room> findRoomById(int roomId) {
        return rooms.stream()
                .filter(room -> room.getRoomId() == roomId)
                .findFirst();
    }

    public void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {

        Optional<Room> optionalRoom = findRoomById(roomNumber);

        if (optionalRoom.isPresent()) {
            optionalRoom.get().updateRoom(roomType, roomPricePerNight);
        } else {
            rooms.add(new Room(roomType, roomNumber, roomPricePerNight));
        }
    }


    public Optional<User> findUserById(int userId) {
        return users.stream()
                .filter(user -> user.getId() == userId)
                .findFirst();
    }

    public void setUser(int id, int balance, LocalDate createdAt) {
        users.add(new User(id, balance, new ArrayList<>(), createdAt));
    }


    public Optional<List<Booking>> findBookingByRoomNumber(int roomNumber) {
        List<Booking> result = new ArrayList<>();

        for (User user : users) {
            for (Booking booking : user.getBookingList()) {
                if (booking.getRoomNumber() == roomNumber) {
                    result.add(booking);
                }
            }
        }
        return Optional.of(result);
    }

    public void bookRoom(
            LocalDate checkIn,
            LocalDate checkOut,
            int userId,
            int roomNumber,
            RoomType roomType,
            int roomPricePerNight,
            LocalDate createdAt
    ) {

        if (!checkOut.isAfter(checkIn)) {
            throw new CheckOutDateBeforeCheckInDateException("Check-out must be after check-in");
        }

        if (roomIsOccupied(checkIn, checkOut, roomNumber)) {
            throw new RoomIsOccupiedException("The room is occupied in this span of time!");
        }

        User user = findUserById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found!"));

        findRoomById(roomNumber)
                .orElseThrow(() -> new ResourceNotFoundException("Room not found!"));

        Booking booking = new Booking(
                checkIn,
                checkOut,
                userId,
                roomNumber,
                roomType,
                roomPricePerNight,
                createdAt
        );

        if (user.getBookingList().contains(booking)) {
            throw new BookingAlreadyExistException("Booking already exists!");
        }

        user.getBookingList().add(booking);
    }

    public void printAll() {
        for (Room room : rooms) {

            List<Booking> bookingsRoom = findBookingByRoomNumber(room.getRoomId())
                    .orElse(Collections.emptyList());

            System.out.println(
                    "Room Number : " + room.getRoomId() +
                            " || Room Type : " + room.getRoomType() +
                            " || Price per night : " + room.getPricePerNight()
            );

            if (bookingsRoom.isEmpty()) {
                System.out.println("No bookings for this room.");
            } else {
                bookingsRoom.sort(
                        Comparator.comparing(Booking::getCreatedAt).reversed()
                );

                System.out.println("CreatedAt || User Id || Check In || Check Out || Nights || Price");

                for (Booking booking : bookingsRoom) {
                    System.out.println(
                            booking.getCreatedAt() + " || " +
                                    booking.getUserId() + " || " +
                                    booking.getCheckIn() + " || " +
                                    booking.getCheckOut() + " || " +
                                    booking.getNightsNumber() + " || " +
                                    booking.getRoomPricePerNight()
                    );
                }
            }

            System.out.println("---------------------------------------------");
        }
    }

    public void printAllUsers() {
        System.out.println("Date || UserId || Balance");

        users.sort(Comparator.comparing(User::getCreatedAt).reversed());

        for (User user : users) {
            System.out.println(
                    user.getCreatedAt() + " || " +
                            user.getId() + " || " +
                            user.getBalance()
            );
        }
    }
}
