package stackoverflow.questions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        GsonBuilder builder = new GsonBuilder();
        builder.registerTypeAdapter(MeetingCenter.class, new MeetingCenterGsonAdapter());
        builder.setPrettyPrinting();
        Gson gson = builder.create();

        MeetingRoom room = new MeetingRoom();
        room.setCode("room code");
        room.setName("room name");

        MeetingCenter meetingCenter = new MeetingCenter();
        meetingCenter.setName("meetingcenter name");
//        room.setMeetingCenter(meetingCenter);

        ArrayList<MeetingRoom> list = new ArrayList<>(1);
        list.add(room);
        meetingCenter.setMeetingRoomList(list);

        System.out.println(gson.toJson(meetingCenter));
    }
}
