package domProblema;

import java.util.ArrayList;
import datos.FileManager;

public class Parking {

    private static int capacity = 25;
    private static ArrayList<User> users = new ArrayList<>();
    private static User[] bikeRack = new User[capacity];

    public static int getCapacity() {
        return capacity;
    }

    public static void setCapacity(int capacity) {
        Parking.capacity = capacity;
    }

    public static User[] getBikeRack() {
        return bikeRack;
    }

    public static void setBikeRack(User[] bikeRack) {
        Parking.bikeRack = bikeRack;
    }

    public static void parkBike(String rut) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRut().equalsIgnoreCase(rut)) {
                if (0 < howManySpaces()) {
                    for (int j = bikeRack.length - 1; 0 <= j; j--) {
                        if (bikeRack[i] == null) {
                            bikeRack[i] = users.get(i);
                            break;
                        }
                    }
                } else {
                    System.out.println("El bicicletero esta lleno");
                    break;
                }
            } else if (i == users.size()) {
                System.out.println("No se encontro usuario con el rut indicado.");
            }
        }
    }

    public static void searchParkedUser(String criteria) {
        System.out.println("Coincidencias por nombre de usuario:");
        for (int i = 0; i < bikeRack.length; i++) {
            if (bikeRack[i] != null && bikeRack[i].getName().toLowerCase().contains(criteria.toLowerCase())) {
                System.out.println(i + "° " + bikeRack[i].toString());
            }
        }
        System.out.println("Coincidencias por rut de usuario:");
        for (int i = 0; i < bikeRack.length; i++) {
            if (bikeRack[i] != null && bikeRack[i].getRut().toLowerCase().contains(criteria.toLowerCase())) {
                System.out.println(i + "° " + bikeRack[i].toString());
            }
        }
    }

    public static void unparkUser(String rut) {
        for (int i = 0; i < bikeRack.length; i++) {
            if (bikeRack[i].getRut().equalsIgnoreCase(rut)) {
                bikeRack[i] = null;
            } else if (i == users.size()) {
                System.out.println("No se encontro usuario en el bicicletero con el rut indicado.");
            }
        }
    }

    public static int howManyParkedUsers() {
        int counter = 0;
        for (int i = 0; i < bikeRack.length; i++) {
            if (bikeRack[i] != null) {
                counter++;
            }
        }
        return counter;
    }

    public static int howManySpaces() {
        return capacity - howManyParkedUsers();
    }

    public static void showAll() {
        for (int i = 0; i < bikeRack.length; i++) {
            if (bikeRack[i] != null) {
                System.out.println(i + "° " + bikeRack[i].toString());
            }
        }
        if (howManyParkedUsers() == 0) {
            System.out.println("No hay usuarios en el bicicletero.");
        }
    }

    public static String[][] loadModel() {

        FileManager fMan = new FileManager();
        String texto = fMan.readFile("pantalla.csv");
        System.out.println(texto);
        String[] separacion = texto.split("[\n]");
        String[][] screen = new String[separacion.length - 1][3];
        for (int i = 1; i < separacion.length; i++) {
            String[] screenTemp = separacion[i].split(";");
            screen[i - 1][0] = screenTemp[0];
            screen[i - 1][1] = screenTemp[1];
            screen[i - 1][2] = screenTemp[2];

        }
        return screen;
    }

}
