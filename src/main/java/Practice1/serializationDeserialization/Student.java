package Practice1.serializationDeserialization;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.thoughtworks.xstream.annotations.XStreamAlias;

import java.util.*;
@XStreamAlias("student")
public class Student {
    String firstName;
    String lastName;
    Address address;
    Parents parents;
    @JacksonXmlElementWrapper(localName = "marks")
    @JacksonXmlProperty(localName = "mark")
    int marks[];

    public Student() {
        this.firstName = "Ivan";
        this.lastName = "Ivanov";
        this.address = new Address("Moldova","Chisinau","Ismail");
        this.parents = new Parents("Ivanov Vasile","Ivanova Maria");
        this.marks = new int[]{7,8,9,10};
    }

    public Student(String fisrstName, String lastName, Address address, Parents parents, int[] marks) {
        this.firstName = fisrstName;
        this.lastName = lastName;
        this.address = address;
        this.parents = parents;
        this.marks = marks;
    }

    public String getFisrstName() {
        return firstName;
    }

    public void setFisrstName(String fisrstName) {
        this.firstName = fisrstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public Parents getParents() {
        return parents;
    }

    public void setParents(Parents parents) {
        this.parents = parents;
    }

    public int[] getMarks() {
        return marks;
    }

    public void setMarks(int[] marks) {
        this.marks = marks;
    }

    @Override
    public String toString() {
        return "Student{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ",\n\t address=" + address +
                ",\n\t parents=" + parents +
                ",\n\t marks=" + Arrays.toString(marks) +
                '}';
    }
}
