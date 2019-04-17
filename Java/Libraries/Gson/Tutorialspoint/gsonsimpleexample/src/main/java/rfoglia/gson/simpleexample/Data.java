package rfoglia.gson.simpleexample;

import java.util.List;

public class Data {
    private String url;
    private List<Student> data;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setData(List<Student> data) {
        this.data = data;
    }

    public List<Student> getData() {
        return data;
    }
}

