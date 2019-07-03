package domProblema;

import java.util.ArrayList;

public class UserList {
    private static ArrayList<User> users = new ArrayList<>();

    public static ArrayList<User> getUsers() {
        return users;
    }

    public static void setUsers(ArrayList<User> users) {
        UserList.users = users;
    }

    public static User findUserByRut(String rut){
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getRut().equalsIgnoreCase(rut)) {
                return users.get(i);
            }
        }
        return null;
    }
}
