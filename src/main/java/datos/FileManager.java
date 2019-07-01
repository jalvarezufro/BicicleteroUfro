import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

//Clase FileManager; posee metodos herramienta para gestionar archivos.
public class FileManager {

    //Metodo generico que captura un archivo de texto y retorna su contenido como un String.
    public static String readFile(String fileName) {
        Path file = Paths.get(fileName);
        String data = "";
        try {
            data = new String(Files.readAllBytes(file));
        } catch (Exception e) {
            System.out.println("The file could not be read.");
        }
        return data;
    }

    //Metodo generico que captura un archivo de texto y lo sobreescribe con otro texto.
    public static void writeFile(String fileName, String data) {
        Path file = Paths.get(fileName);
        try {
            Files.write(file, data.getBytes());
        } catch (Exception e) {
            System.out.println("The file could not be written");
        }
    }
}
