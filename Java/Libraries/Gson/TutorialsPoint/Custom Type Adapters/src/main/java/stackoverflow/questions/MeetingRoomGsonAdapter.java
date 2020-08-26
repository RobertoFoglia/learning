package stackoverflow.questions;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class MeetingRoomGsonAdapter extends TypeAdapter<MeetingRoom> {

    @Override
    public void write(JsonWriter jsonWriter, MeetingRoom meetingRoom) throws IOException {
        jsonWriter.beginObject();



        jsonWriter.endObject();
    }

    @Override
    public MeetingRoom read(JsonReader jsonReader) throws IOException {
        return null;
    }
}
