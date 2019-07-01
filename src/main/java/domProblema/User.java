import java.util.ArrayList;

public class User {

    private ArrayList<Bike> bikes;
    private String eMail;
    private String name;
    private String rut;
    private String phone;
    private int warnings;

    public User(ArrayList<Bike> bikes, String eMail, String name, String rut, String phone, int warnings) {
        this.bikes = bikes;
        this.eMail = eMail;
        this.name = name;
        this.rut = rut;
        this.phone = phone;
        this.warnings = warnings;
    }

    public ArrayList<Bike> getBikes() {
        return bikes;
    }

    public void setBikes(ArrayList<Bike> bikes) {
        this.bikes = bikes;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRut() {
        return rut;
    }

    public void setRut(String rut) {
        this.rut = rut;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public int getWarnings() {
        return warnings;
    }

    public void setWarnings(int warnings) {
        this.warnings = warnings;
    }

    @Override
    public String toString() {
        return "User{" +
                "bikes=" + bikes +
                ", eMail='" + eMail + '\'' +
                ", name='" + name + '\'' +
                ", rut='" + rut + '\'' +
                ", phone='" + phone + '\'' +
                ", warnings=" + warnings +
                '}';
    }
}