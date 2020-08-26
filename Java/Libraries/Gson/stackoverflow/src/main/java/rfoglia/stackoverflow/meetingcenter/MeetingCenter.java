package rfoglia.stackoverflow.meetingcenter;

import com.google.gson.annotations.Expose;

import java.util.List;

public class MeetingCenter {
    @Expose
    private String name;
    @Expose
    private List<MeetingRoom> meetingRoomList;

    public List<MeetingRoom> getMeetingRoomList() {
        return meetingRoomList;
    }

    public void setMeetingRoomList(List<MeetingRoom> meetingRoomList) {
        this.meetingRoomList = meetingRoomList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
