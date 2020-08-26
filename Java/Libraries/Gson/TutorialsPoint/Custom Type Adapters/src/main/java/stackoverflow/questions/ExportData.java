package stackoverflow.questions;

public class ExportData {
    private MeetingCenter data;
    private String schema;
    private String uRI;

    public MeetingCenter getData() {
        return data;
    }

    public void setData(MeetingCenter data) {
        this.data = data;
    }

    public String getSchema() {
        return schema;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getuRI() {
        return uRI;
    }

    public void setuRI(String uRI) {
        this.uRI = uRI;
    }
}
