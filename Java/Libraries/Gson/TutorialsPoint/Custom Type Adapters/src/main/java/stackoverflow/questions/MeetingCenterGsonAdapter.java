package stackoverflow.questions;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.util.stream.Collectors;

public class MeetingCenterGsonAdapter extends TypeAdapter<MeetingCenter> {

    public void write(JsonWriter jsonWriter, MeetingCenter meetingCenter) throws IOException {
        jsonWriter.beginObject();

        jsonWriter.name("schema");
        jsonWriter.value("PLUS4U.EBC.MCS.MeetingRoom_Schedule_1.0");
        jsonWriter.name("uri");
        jsonWriter.value("ues:UCL-BT:UCL.INF/DEMO_REZERVACE:EBC.MCS.DEMO/MR001/SCHEDULE");
        jsonWriter.name("data");

        String s = toJson(meetingCenter);
        jsonWriter.value(s);
//        GsonBuilder builder = new GsonBuilder();
//        Gson gson = builder.create();
//        builder.setPrettyPrinting();
//        jsonWriter.beginArray();
//        jsonWriter.beginObject();
//        String value = meetingCenter.getMeetingRoomList().stream().map(meetingRoom -> {
//            return  gson.toJson(meetingRoom);
//        }).collect(Collectors.toList()).toString();
//        jsonWriter.value(value);
//        jsonWriter.endObject();
//        meetingCenter.getMeetingRoomList().forEach(meetingRoom -> {
//
//            try {
//                jsonWriter.beginObject();
//                jsonWriter.name("name");
//                jsonWriter.value(meetingRoom.getName());
//                jsonWriter.name("code");
//                jsonWriter.value(meetingRoom.getCode());
//                jsonWriter.endObject();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        });
//        jsonWriter.endArray();

        jsonWriter.endObject();
    }

    public MeetingCenter read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
