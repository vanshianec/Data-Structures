import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        TextEditorImpl textEditor = new TextEditorImpl();
        String line = reader.readLine();
        while (!line.equals("end")) {
            String[] params = line.split(" ");
            String userCommand = params[0];
            switch (userCommand) {
                case "login":
                    textEditor.login(params[1]);
                    break;
                case "logout":
                    textEditor.logout(params[1]);
                    break;
                case "users":
                    if (params.length == 2) {
                        textEditor.users(params[1]).forEach(System.out::println);
                    } else {
                        textEditor.users("").forEach(System.out::println);
                    }
                    break;
            }
            if (params.length > 1) {
                String editorCommand = params[1];
                switch (editorCommand) {
                    case "insert":
                        textEditor.insert(params[0], Integer.parseInt(params[2]), line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\"")));
                        break;
                    case "prepend":
                        textEditor.prepend(params[0], line.substring(line.indexOf("\"") + 1, line.lastIndexOf("\"")));
                        break;
                    case "substring":
                        textEditor.substring(params[0], Integer.parseInt(params[2]), Integer.parseInt(params[3]));
                        break;
                    case "delete":
                        textEditor.delete(params[0], Integer.parseInt(params[2]), Integer.parseInt(params[3]));
                        break;
                    case "clear":
                        textEditor.clear(params[0]);
                        break;
                    case "length":
                        System.out.println(textEditor.length(params[0]));
                        break;
                    case "print":
                        System.out.println(textEditor.print(params[0]));
                        break;
                    case "undo":
                        textEditor.undo(params[0]);
                        break;
                }
            }

            line = reader.readLine();
        }
    }
}
