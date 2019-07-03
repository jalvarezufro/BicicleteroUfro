package domProblema;

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
}
