import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    /**
     * Manages the user's selections.
     *
     * @return void
     */
    public static void mainMenu() {
        showMainMenu();
        int answer = getNumericInput();
        switch (answer) {
            case 1:
                System.out.println("Ingrese el termino a buscar.");
                Parking.searchParkedUser(getInput());
                mainMenu();
                break;
            case 2:
                mainMenu();
                break;
            case 3:
                System.out.println("Ingrese la posicion del usuario quitar.");
                //Parking.unparkUser(getNumericInput());
                mainMenu();
                break;
            case 4:
                System.out.println("4");
                userManagementMenu();
                mainMenu();
                break;
            case 0:
                System.out.println("Adios!");
                System.exit(0);
                break;
            default:
                System.out.println("Opcion inexistente. Porfavor intentelo nuevamente.");
                mainMenu();
        }
    }

    public static void userManagementMenu() {
        showUserManagementMenu();
        int answer = getNumericInput();
        switch (answer) {
            case 1:
                System.out.println("1");
                userManagementMenu();
                break;
            case 2:
                userManagementMenu();
                break;
            case 3:
                System.out.println("3");
                userManagementMenu();
                break;
            case 4:
                System.out.println("4");
                userManagementMenu();
                break;
            case 0:
                System.out.println("0");
                mainMenu();
                break;
            default:
                System.out.println("Opcion inexistente. Porfavor intentelo nuevamente.");
                userManagementMenu();
        }
    }

    /**
     * Shows the mainMenu options.
     *
     * @return void
     */
    public static void showMainMenu() {
        System.out.println("+-------------------------------------------------+\n" +
                "|MENU PRINCIPAL                                   |\n" +
                "+-------------------------------------------------+\n" +
                "|Ingresa 1 para buscar usuarios en el bicicletero.|\n" + //Tiene que ser a traves del rut.
                "|Ingresa 2 para agregar un usuario al bicicletero.|\n" + //Tiene que ser a traves del rut.
                "|Ingresa 3 para quitar un usuario del bicicletero.|\n" + //Tiene que ser a traves del rut.
                "|Ingresa 4 para gestionar usuarios.               |\n" +
                "|Ingresa 0 para salir.                            |\n" +
                "+-------------------------------------------------+\n");
    }

    public static void showUserManagementMenu() {
        System.out.println("+----------------------------------------+\n" +
                "|GESTIONAR USUARIOS                      |\n" +
                "+----------------------------------------+\n" +
                "|Ingresa 1 para buscar usuarios.         |\n" +
                "|Ingresa 2 para agregar un usuario.      |\n" +
                "|Ingresa 3 para eliminar un usuario.     |\n" +
                "|Ingresa 4 para editar un usuario.       |\n" + //Editar sus datos y añadir o quitar bicicletas
                "|Ingresa 0 para volver al menu principal.|\n" +
                "+----------------------------------------+\n");
    }

    /**
     * Reads user inputs.
     *
     * @return String
     */
    public static String getInput() {
        return new Scanner(System.in).nextLine();
    }

    /**
     * Reads numerical user inputs.
     *
     * @return String
     */
    public static int getNumericInput() {
        boolean error;
        int answer = 0;
        do {
            try {
                answer = Integer.parseInt(getInput());
                error = false;
            } catch (Exception e) {
                System.out.println("Entrada invalida. Porfavor intentelo nuevamente.");
                error = true;
            }
        } while (error);
        return answer;
    }
}
