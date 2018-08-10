package Practice1.serializationDeserialization;
//Serializing and deserializing object in/from JSON using Jackson library

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.IOException;

public class Json_Jackson {
    public static void main(String[] args) throws IOException {
        Student student = new Student();

        System.out.println("Initial student object:");
        System.out.println(student);
        System.out.println();

        ObjectMapper om = new ObjectMapper();
        //Student to JSON Conversion
        String jsonFromStudent = om.writerWithDefaultPrettyPrinter().writeValueAsString(student);
        System.out.println("Json for the above Student:");
        System.out.println(jsonFromStudent);
        System.out.println();

        //JSON to Student Conversion
        Student studentfromJson = om.readValue(jsonFromStudent, Student.class);
        System.out.println("Student from the above json:");
        System.out.println(studentfromJson);
    }
}
