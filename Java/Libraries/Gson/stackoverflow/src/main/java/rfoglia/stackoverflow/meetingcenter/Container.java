package rfoglia.stackoverflow.meetingcenter;

import com.google.gson.annotations.Expose;

import java.util.List;

public class Container {
    @Expose
    private String schema;
    @Expose
    private String uri;
    @Expose
    private List<MeetingCenter> data;

    public String getSchema() {
        return schema;
    }

    public String getUri() {
        return uri;
    }

    public List<MeetingCenter> getData() {
        return data;
    }

    public void setData(List<MeetingCenter> data) {
        this.data = data;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }
}
