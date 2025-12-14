package org.yassine.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yassine.enums.RoomType;
import org.yassine.exceptions.BookingAlreadyExistException;
import org.yassine.exceptions.CheckOutDateBeforeCheckInDateException;
import org.yassine.exceptions.RoomIsOccupiedException;
import org.yassine.models.Room;

import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    private Service service;

    @BeforeEach
    void setUp() {
        service = new Service(
                new ArrayList<>(),
                new ArrayList<>()
        );

        service.setRoom(1, RoomType.standard, 1000);
        service.setRoom(2, RoomType.junior, 2000);
        service.setRoom(3, RoomType.suite, 3000);

        service.setUser(1,5000, LocalDate.of(2025, 4, 10));
        service.setUser(2,10000, LocalDate.of(2025, 5, 13));
    }

    /* ===================== ROOM TESTS ===================== */

    @Test
    void shouldFindRoomById() {
        assertTrue(service.findRoomById(1).isPresent());
        assertFalse(service.findRoomById(99).isPresent());
    }

    @Test
    void shouldUpdateRoom() {
        service.setRoom(1, RoomType.suite, 3000);

        Room room = service.findRoomById(1).orElseThrow();
        assertEquals(RoomType.suite, room.getRoomType());
        assertEquals(3000, room.getPricePerNight());
    }

    /* ===================== USER TESTS ===================== */

    @Test
    void shouldFindUserById() {
        assertTrue(service.findUserById(1).isPresent());
        assertFalse(service.findUserById(99).isPresent());
    }

    /* ===================== BOOKING TESTS ===================== */

    @Test
    void shouldBookRoomSuccessfully() {
        service.bookRoom(
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 5),
                1,
                1,
                RoomType.standard,
                1000,
                LocalDate.now()
        );

        assertEquals(1,
                service.findBookingByRoomNumber(1).orElseThrow().size());
    }

    @Test
    void shouldNotAllowOverlappingBookings() {
        service.bookRoom(
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 5),
                1,
                1,
                RoomType.standard,
                1000,
                LocalDate.now()
        );

        assertThrows(RoomIsOccupiedException.class, () ->
                service.bookRoom(
                        LocalDate.of(2025, 6, 3),
                        LocalDate.of(2025, 6, 7),
                        2,
                        1,
                        RoomType.standard,
                        1000,
                        LocalDate.now()
                )
        );
    }

    @Test
    void shouldAllowNonOverlappingBookings() {
        service.bookRoom(
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 5),
                1,
                1,
                RoomType.standard,
                1000,
                LocalDate.now()
        );

        service.bookRoom(
                LocalDate.of(2025, 6, 5),
                LocalDate.of(2025, 6, 10),
                2,
                1,
                RoomType.standard,
                1000,
                LocalDate.now()
        );

        assertEquals(2,
                service.findBookingByRoomNumber(1).orElseThrow().size());
    }

    @Test
    void shouldRejectInvalidDates() {
        assertThrows(CheckOutDateBeforeCheckInDateException.class, () ->
                service.bookRoom(
                        LocalDate.of(2025, 6, 10),
                        LocalDate.of(2025, 6, 5),
                        1,
                        1,
                        RoomType.standard,
                        1000,
                        LocalDate.now()
                )
        );
    }


    @Test
    void roomIsOccupiedShouldReturnFalseIfNoBookings() {
        assertFalse(
                service.roomIsOccupied(
                        LocalDate.of(2025, 7, 1),
                        LocalDate.of(2025, 7, 5),
                        2
                )
        );
    }

    @Test
    void roomIsOccupiedShouldReturnTrueIfOverlapping() {
        service.bookRoom(
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 5),
                1,
                2,
                RoomType.junior,
                2000,
                LocalDate.now()
        );

        assertTrue(
                service.roomIsOccupied(
                        LocalDate.of(2025, 6, 3),
                        LocalDate.of(2025, 6, 6),
                        2
                )
        );
    }
}
