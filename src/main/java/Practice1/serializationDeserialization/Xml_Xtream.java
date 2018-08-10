package Practice1.serializationDeserialization;
//Serializing and deserializing object in/from XML using Xtream library

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Xml_Xtream {
    public static void main(String[] args) {
        Student student = new Student();

        System.out.println("Initial student object:");
        System.out.println(student);
        System.out.println();

        XStream xstream = new XStream(new StaxDriver());
        xstream.processAnnotations(Student.class);

        //Student to XML Conversion
        String xmlFromStudent = xstream.toXML(student);
        System.out.println("XML for the above Student:");
        System.out.println(xmlFromStudent);
        System.out.println();

        //XML to Student Conversion
        Student studentfromXml = (Student)xstream.fromXML(xmlFromStudent);
        System.out.println("Student from the above XML:");
        System.out.println(studentfromXml);

    }
}
