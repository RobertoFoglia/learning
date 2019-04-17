package rfoglia.stackoverflow.meetingcenter;

import com.google.gson.annotations.Expose;

public class Reservation {
    private MeetingRoom meetingRoom;
    @Expose
    private String owner;

    public MeetingRoom getMeetingRoom() {
        return meetingRoom;
    }

    public void setMeetingRoom(MeetingRoom meetingRoom) {
        this.meetingRoom = meetingRoom;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
}
