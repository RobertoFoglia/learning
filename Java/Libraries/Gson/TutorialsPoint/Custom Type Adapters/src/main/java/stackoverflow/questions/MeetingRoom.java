package stackoverflow.questions;

import com.google.gson.annotations.Expose;

import java.util.ArrayList;
import java.util.List;

public class MeetingRoom {
    @Expose(serialize = false, deserialize = false)
    private MeetingCenter meetingCenter;
    private String code;
    private String name;
    private List<Reservation> reservationList = new ArrayList<>();

    public MeetingCenter getMeetingCenter() {
        return meetingCenter;
    }

    public void setMeetingCenter(MeetingCenter meetingCenter) {
        this.meetingCenter = meetingCenter;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
