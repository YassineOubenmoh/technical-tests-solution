package org.yassine.models;

import org.yassine.enums.RoomType;

import java.util.Objects;

public class Room {
    private int roomId;
    private RoomType roomType;
    private int pricePerNight;

    public Room(RoomType roomType, int roomId, int pricePerNight) {
        this.roomType = roomType;
        this.roomId = roomId;
        this.pricePerNight = pricePerNight;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public RoomType getRoomType() {
        return roomType;
    }

    public void setRoomType(RoomType roomType) {
        this.roomType = roomType;
    }

    public int getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(int pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public void updateRoom(RoomType roomType, int pricePerNight){
        this.roomType = roomType;
        this.pricePerNight = pricePerNight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Room room = (Room) o;
        return roomId == room.roomId && pricePerNight == room.pricePerNight && roomType == room.roomType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(roomId, roomType, pricePerNight);
    }
}
