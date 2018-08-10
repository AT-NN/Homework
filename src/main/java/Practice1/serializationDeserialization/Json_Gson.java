package Practice1.serializationDeserialization;
//Serializing and deserializing object in/from JSON using Gson library

import com.google.gson.Gson;

public class Json_Gson {
    public static void main(String[] args) {
        Student student = new Student();

        System.out.println("Initial student object:");
        System.out.println(student);
        System.out.println();

        Gson gson = new Gson();
        //Student to JSON Conversion
        String jsonFromStudent = gson.toJson(student);
        System.out.println("Json for the above Student:");
        System.out.println(jsonFromStudent);
        System.out.println();

        //JSON to Student Conversion
        Student studentfromJson = gson.fromJson(jsonFromStudent, Student.class);
        System.out.println("Student from the above json:");
        System.out.println(studentfromJson);
    }
}
