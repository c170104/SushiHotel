package com.sushihotel.room;

import com.sushihotel.exception.DuplicateData;
import com.sushihotel.room.Room;
import com.sushihotel.room.RoomModel;

public class RoomMgr    {

    public void createRoom(Room room)    {
        try {
            if(RoomModel.create(room))
                System.out.println("Successfully added Room number " + room.getRoomNumber() + " Type: " + room.getRoomType());
            else
                System.out.println("System failed to add Room number" + room.getRoomNumber() + ".");
        } catch(DuplicateData dd)   {
            System.out.println(dd.getMessage());
        }
    } 
    public boolean editRoom(int roomNumber , Room room) {
        Room oldRoom;
        
        try {
            
        }
    } 
    // public boolean removeRoom(int roomNumber )  {

    // } 
    // public String checkRoomAvailability(int roomNumber)    {

    // } 
    // public String checkRoomStatus(int roomNumber)   {

    // } 
    // public void printRoomStatsOccRate(Enum roomStatus)  {

    // } 
    // public void printRoomStatsRoomStatus()  {

    // }  //list all room numbers  from each room status category
    // public void printRoomStatus()   {

    // } 
    // public void printRoomOccupancyPercentage(String date)   {

    // }
}