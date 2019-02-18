package stackoverflow.questions;

import java.util.List;

public class MeetingCenter {
    private String name;
    private List<MeetingRoom> meetingRoomList;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MeetingRoom> getMeetingRoomList() {
        return meetingRoomList;
    }

    public void setMeetingRoomList(List<MeetingRoom> meetingRoomList) {
        this.meetingRoomList = meetingRoomList;
    }
}
