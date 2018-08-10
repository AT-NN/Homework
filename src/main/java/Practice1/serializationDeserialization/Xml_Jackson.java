package Practice1.serializationDeserialization;
//Serializing and deserializing object in/from XML using Jackson library

import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import java.io.IOException;

public class Xml_Jackson {
    public static void main(String[] args) throws IOException {
        Student student = new Student();

        System.out.println("Initial student object:");
        System.out.println(student);
        System.out.println();

        XmlMapper xm = new XmlMapper();
        //Student to XML Conversion
        String jsonFromStudent = xm.writerWithDefaultPrettyPrinter().writeValueAsString(student);
        System.out.println("XML for the above Student:");
        System.out.println(jsonFromStudent);
        System.out.println();

        //XML to Student Conversion
        Student studentfromJson = xm.readValue(jsonFromStudent, Student.class);
        System.out.println("Student from the above XML:");
        System.out.println(studentfromJson);
    }
}
