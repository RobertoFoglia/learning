package rfoglia.stackoverflow.meetingcenter;

import com.google.gson.annotations.Expose;

import java.util.List;

public class MeetingRoom {
    private MeetingCenter meetingCenter;
    @Expose
    private String code;
    @Expose
    private String name;
    @Expose
    private List<Reservation> reservationList;

    public MeetingCenter getMeetingCenter() {
        return meetingCenter;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public List<Reservation> getReservationList() {
        return reservationList;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setMeetingCenter(MeetingCenter meetingCenter) {
        this.meetingCenter = meetingCenter;
    }

    public void setReservationList(List<Reservation> reservationList) {
        this.reservationList = reservationList;
    }
}
