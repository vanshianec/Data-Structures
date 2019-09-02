import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader consoleReader = new BufferedReader(new InputStreamReader(System.in));
        HashTable<String, String> contacts = new HashTable<>();
        String contactInfo = consoleReader.readLine();
        while (!contactInfo.equals("search")) {
            String contactName = contactInfo.split("-")[0];
            String contactNumber = contactInfo.split("-")[1];
            contacts.addOrReplace(contactName, contactNumber);
            contactInfo = consoleReader.readLine();
        }
        String contactName = consoleReader.readLine();
        while (!contactName.equals("end")) {
            if (!contacts.containsKey(contactName)) {
                System.out.printf("Contact %s does not exist.%n", contactName);
            } else {
                System.out.printf("%s -> %s%n", contactName, contacts.get(contactName));
            }
            contactName = consoleReader.readLine();
        }
    }
}
