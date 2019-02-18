package stackoverflow.questions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Main2 {
    public static void main(String[] args){
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        builder.setPrettyPrinting();

        MeetingRoom room = new MeetingRoom();
        room.setCode("room code");
        room.setName("room name");

        MeetingCenter meetingCenter = new MeetingCenter();
        meetingCenter.setName("meetingcenter name");
        room.setMeetingCenter(meetingCenter);

        ArrayList<MeetingRoom> list = new ArrayList<>(1);
        list.add(room);
        meetingCenter.setMeetingRoomList(list);

        ArrayList<Reservation> reservationList = new ArrayList<>(1);
        Reservation e = new Reservation();
        e.setMeetingRoom(room);
        e.setOwner("owner");
        reservationList.add(e);
        room.setReservationList(reservationList);

        ExportData dataTosend = new ExportData();
        dataTosend.setData(meetingCenter);
        dataTosend.setSchema("PLUS4U.EBC.MCS.MeetingRoom_Schedule_1.0");
        dataTosend.setuRI("ues:UCL-BT:UCL.INF/DEMO_REZERVACE:EBC.MCS.DEMO/MR001/SCHEDULE");

        System.out.println(gson.toJson(dataTosend));
    }
}
