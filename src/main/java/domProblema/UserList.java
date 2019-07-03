package domProblema;
import datos.FileManager;




import java.util.ArrayList;

public class UserList {

    private static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        
        return users;
    }

    public static User findUserByRut(String rut) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRut().equalsIgnoreCase(rut)) {
                return users.get(i);
            }
        }
        return null;
    }

    public static void setUsers(ArrayList<User> users) {
        UserList.users = users;
    }

    public static void addUser(String eMail, String name, String rut, String phone, int warnings) {
        users.add(new User(new ArrayList<Bike>(), eMail, name, rut, phone, warnings));
    }

    public static void deleteUserByRut(String rut) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRut().equalsIgnoreCase(rut)) {
                users.remove(i);
            }
        }
    }

    public static void loadUsers() {
        
        int j = 1;
        String datosUser = "Bicicletas;Email;Nombre;Rut;Numero;Advertencias";
        String userText = FileManager.readCreate("users.csv", datosUser);

        String datosBike = "Marca;Color";
        String bikeText = FileManager.readCreate("bikes.csv", datosBike);

        String[] userSplit = userText.split("\n");
        String[] bikeSplit = bikeText.split("\n");

        for (int i = 1; i < userSplit.length; i++) {
            System.out.println(i);
            ArrayList<Bike> bikesTemp = new ArrayList<>();
            String[] userSplitTemp = userSplit[i].split(";");
            int bikeCounter = 0;
            int userBikes = Integer.parseInt(userSplitTemp[0]);

            while (bikeCounter < userBikes) {
                String[] bikeSplitTemp = bikeSplit[i].split(";");
                String marca = bikeSplitTemp[0];
                String color = bikeSplitTemp[1];
                Bike tempBike = new Bike(marca, color);
                bikesTemp.add(tempBike);
                
                bikeCounter++;
                j++;
            }
            String email = userSplitTemp[1];
            String nombre = userSplitTemp[2];
            String rut = userSplitTemp[3];
            String numero = userSplitTemp[4];
            int advertencia = Integer.parseInt("0");

            User userTemp = new User(bikesTemp, email, nombre, rut, numero, advertencia);
            users.add(userTemp);

        }
    }
}

/*
public static void loadUsers() {
        int j = 1;
        String datosUser = "Bicicletas;Email;Nombre;Rut;Numero;Advertencias";
        String userText = FileManager.readCreate("users.csv", datosUser);

        String datosBike = "Marca;Color";
        String bikeText = FileManager.readCreate("bikes.csv", datosBike);

        String[] userSplit = userText.split("\n");
        String[] bikeSplit = bikeText.split("\n");

        for (int i = 1; i < userSplit.length; i++) {
            ArrayList<Bike> bikesTemp = new ArrayList<>();
            String[] userSplitTemp = userSplit[i].split(";");
            int bikeCounter = 0;
            int userBikes = Integer.parseInt(userSplitTemp[0]);

            while (bikeCounter < userBikes) {
                String[] bikeSplitTemp = bikeSplit[i].split(";");
                String marca = bikeSplitTemp[0];
                String color = bikeSplitTemp[1];
                Bike tempBike = new Bike(marca, color);
                bikesTemp.add(tempBike);

                j++;
            }
            String email = userSplitTemp[1];
            String nombre = userSplitTemp[2];
            String rut = userSplitTemp[3];
            String numero = userSplitTemp[4];
            int advertencia = Integer.parseInt(userSplitTemp[5]);
            
            User userTemp = new User(bikesTemp, email, nombre, rut, numero, advertencia);
            users.add(userTemp);
        }
 */
