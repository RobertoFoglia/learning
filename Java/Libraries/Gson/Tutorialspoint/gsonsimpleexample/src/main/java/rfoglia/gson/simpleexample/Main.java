package rfoglia.gson.simpleexample;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args){
        String jsonString = "{\"name\":\"Mahesh\", \"age\":21}";

        GsonBuilder builder = new GsonBuilder();
        builder.setPrettyPrinting();

        Gson gson = builder.create();
        Student student = gson.fromJson(jsonString, Student.class);
        System.out.println(student);

        Data data = new Data();
        ArrayList<Student> students = new ArrayList<Student>();
        students.add(student);
        data.setData(students);

        data.setUrl("url");

        jsonString = gson.toJson(data);
        System.out.println(jsonString);



    }
}
