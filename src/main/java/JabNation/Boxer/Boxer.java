package JabNation.Boxer;
public class Boxer {
    private String name;
    private String nickname;
    private String surname;
    private int age;
    private String record;
    private String division;
    private double weight;
    private double height;
    private double reach;
    private String nation;
    private boolean active;
    private String photoPath;

    public Boxer(String name, String nickname, String surname, int age, String record, String division, double weight, double height, double reach, String nation, boolean active, String photoPath) {
        this.name = name;
        this.nickname = nickname;
        this.surname = surname;
        this.age = age;
        this.record = record;
        this.division = division;
        this.weight = Double.parseDouble(String.valueOf(weight));
        this.height = Double.parseDouble(String.valueOf(height));
        this.reach = Double.parseDouble(String.valueOf(reach));
        this.nation = nation;
        this.active = active;
        this.photoPath = photoPath;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getRecord() {
        return record;
    }

    public void setRecord(String record) {
        this.record = record;
    }

    public String getDivision() {
        return division;
    }

    public void setDivision(String division) {
        this.division = division;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = Double.parseDouble(String.valueOf(weight));
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = Double.parseDouble(String.valueOf(height));
    }

    public double getReach() {
        return reach;
    }

    public void setReach(double reach) {
        this.reach = Double.parseDouble(String.valueOf(reach));
    }

    public String getNation() {
        return nation;
    }

    public void setNation(String nation) {
        this.nation = nation;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    public String getPhotoPath() {
        return photoPath;
    }
    @Override
    public String toString() {
        return "Boxer{" +
                "name='" + name + '\'' +
                ", nickname='" + nickname + '\'' +
                ", surname='" + surname + '\'' +
                ", age=" + age +
                ", record='" + record + '\'' +
                ", division='" + division + '\'' +
                ", weight=" + weight +
                ", height=" + height +
                ", reach=" + reach +
                ", nation='" + nation + '\'' +
                ", active=" + active +
                '}';
    }

}

