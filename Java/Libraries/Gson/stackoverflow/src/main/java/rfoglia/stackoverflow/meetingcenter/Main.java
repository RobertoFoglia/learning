package rfoglia.stackoverflow.meetingcenter;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args){
        Container container = meetingCenterInitialization();

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();
        builder.excludeFieldsWithoutExposeAnnotation();

        Gson gson = builder.create();

        String jsonString = gson.toJson(container);
        System.out.println(jsonString);


        Container container1 = gson.fromJson(jsonString, Container.class);
        System.out.println("\n\n\n\n" + container1.getData().get(0).getName());
    }

    private static Container meetingCenterInitialization() {
        MeetingCenter center = new MeetingCenter();
        center.setName("center name");

        MeetingRoom meetingRoom1 = new MeetingRoom();
        meetingRoom1.setCode("room 1");
        meetingRoom1.setMeetingCenter(center);
        meetingRoom1.setName("room 1");

        List<Reservation> reservations = new ArrayList<Reservation>(1);
        Reservation reservation = new Reservation();
        reservation.setOwner("reservation 1");
        reservation.setMeetingRoom(meetingRoom1);
        reservations.add(reservation);

        meetingRoom1.setReservationList(reservations);
        MeetingRoom meetingRoom2 = new MeetingRoom();
        meetingRoom2.setName("room 2");
        meetingRoom2.setCode("room 2");
        meetingRoom2.setMeetingCenter(center);

        List<MeetingRoom> meetingRooms = new ArrayList<MeetingRoom>(2);
        meetingRooms.add(meetingRoom1);
        meetingRooms.add(meetingRoom2);
        center.setMeetingRoomList(meetingRooms);

        Container container = new Container();
        ArrayList<MeetingCenter> meetingCenters = new ArrayList<MeetingCenter>();
        meetingCenters.add(center);

        container.setData(meetingCenters);
        container.setSchema("PLUS4U.EBC.MCS.MeetingRoom_Schedule_1.0");
        container.setUri("ues:UCL-BT:UCL.INF/DEMO_REZERVACE:EBC.MCS.DEMO/MR001/SCHEDULE");
        return container;
    }
}

