package stackoverflow.questions;

import com.google.gson.annotations.Expose;

public class Reservation {
    @Expose(serialize = false, deserialize = false)
    private MeetingRoom meetingRoom;
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