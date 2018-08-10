package Practice1.serializationDeserialization;

public class Parents {
    String father;
    String mother;

    public Parents() {
    }

    public Parents(String father, String mother) {
        this.father = father;
        this.mother = mother;
    }

    public String getMother() {
        return mother;
    }

    public void setMother(String mother) {
        this.mother = mother;
    }

    public String getFather() {
        return father;
    }

    public void setFather(String father) {
        this.father = father;
    }

    @Override
    public String toString() {
        return "Parents{" +
                "mother='" + mother + '\'' +
                ", father='" + father + '\'' +
                '}';
    }
}
