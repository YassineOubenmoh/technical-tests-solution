package org.yassine;

import org.yassine.enums.RoomType;
import org.yassine.models.Room;
import org.yassine.models.User;
import org.yassine.services.Service;

import java.time.Clock;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {

        ArrayList<Room> rooms = new ArrayList<>();
        ArrayList<User> users = new ArrayList<>();
        Service service = new Service(rooms, users);

        service.setRoom(1, RoomType.standard, 1000);
        service.setRoom(2, RoomType.junior, 2000);
        service.setRoom(3, RoomType.suite, 3000);

        service.setUser(1,5000, LocalDate.of(2025, 4, 10));
        service.setUser(2,10000, LocalDate.of(2025, 5, 13));


        service.bookRoom(
                LocalDate.of(2026, 6, 30),
                LocalDate.of(2026, 7, 7),
                1,
                2,
                RoomType.junior,
                2000,
                LocalDate.of(2026, 6, 28)
        );

        service.bookRoom(
                LocalDate.of(2026, 7, 7),
                LocalDate.of(2026, 6, 30),
                1,
                2,
                RoomType.junior,
                2000,
                LocalDate.of(2026, 6, 28)
        );

        service.bookRoom(
                LocalDate.of(2026, 7, 7),
                LocalDate.of(2026, 7, 8),
                1,
                1,
                RoomType.standard,
                1000,
                LocalDate.of(2026, 6, 28)
        );


        service.bookRoom(
                LocalDate.of(2026, 7, 7),
                LocalDate.of(2026, 7, 9),
                2,
                1,
                RoomType.standard,
                1000,
                LocalDate.of(2026, 6, 28)
        );


        service.bookRoom(
                LocalDate.of(2026, 7, 7),
                LocalDate.of(2026, 7, 9),
                2,
                3,
                RoomType.suite,
                3000,
                LocalDate.of(2026, 6, 28)
        );


        service.printAll();

        System.out.println("############# After Updating Room #############");

        service.setRoom(1, RoomType.suite, 10000);



        service.printAll();
        service.printAllUsers();













    }
}
