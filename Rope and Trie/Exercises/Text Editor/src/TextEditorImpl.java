import java.util.*;
import java.util.stream.Collectors;

public class TextEditorImpl implements TextEditor {

    private Trie<Stack<String>> usersText;
    private Map<String, Boolean> loggedUsers;

    public TextEditorImpl() {
        this.loggedUsers = new LinkedHashMap<>();
        this.usersText = new Trie<>();
    }

    @Override
    public void login(String username) {
        if (loggedUsers.containsKey(username)) {
            if (loggedUsers.get(username)) {
                usersText.getValue(username).push("");
            } else {
                loggedUsers.put(username, true);
            }
        } else {
            loggedUsers.put(username, true);
            Stack<String> userText = new Stack<>();
            userText.push("");
            usersText.insert(username, userText);
        }
    }

    @Override
    public void logout(String username) {
        if (loggedUsers.containsKey(username)) {
            loggedUsers.put(username, false);
            usersText.getValue(username).push("");
        }
    }

    @Override
    public void prepend(String username, String string) {
        if (loggedUsers.containsKey(username)) {
            String oldValue = usersText.getValue(username).peek();
            usersText.getValue(username).push(string + oldValue);
        }
    }

    @Override
    public void insert(String username, int index, String string) {
        if (loggedUsers.containsKey(username)) {
            String oldValue = usersText.getValue(username).peek();
            if (index >= 0 && index <= oldValue.length()) {
                StringBuilder builder = new StringBuilder(oldValue);
                builder.insert(index, string);
                usersText.getValue(username).push(builder.toString());
            }
        }
    }

    @Override
    public void substring(String username, int startIndex, int length) {
        if (loggedUsers.containsKey(username)) {
            String oldValue = usersText.getValue(username).peek();
            String newValue = oldValue.substring(startIndex, Math.min(startIndex + length, oldValue.length()));
            usersText.getValue(username).push(newValue);
        }
    }

    @Override
    public void delete(String username, int startIndex, int length) {
        if (loggedUsers.containsKey(username)) {
            String oldValue = usersText.getValue(username).peek();
            StringBuilder builder = new StringBuilder(oldValue);
            builder.delete(startIndex, Math.min(startIndex + length, oldValue.length()));
            usersText.getValue(username).push(builder.toString());
        }
    }

    @Override
    public void clear(String username) {
        if (loggedUsers.containsKey(username)) {
            usersText.getValue(username).push("");
        }
    }

    @Override
    public int length(String username) {
        if (loggedUsers.containsKey(username)) {
            return usersText.getValue(username).peek().length();
        }
        return 0;
    }

    @Override
    public String print(String username) {
        if (loggedUsers.containsKey(username)) {
            return usersText.getValue(username).peek();
        }
        return "";
    }

    @Override
    public void undo(String username) {
        if (loggedUsers.containsKey(username)) {
            if (usersText.getValue(username).size() > 1) {
                usersText.getValue(username).pop();
            }
        }
    }

    @Override
    public Iterable<String> users(String prefix) {
        if (prefix.equals("")) {
            return loggedUsers.entrySet().stream().filter(kv -> kv.getValue()).map(kv -> kv.getKey()).collect(Collectors.toList());
        }
        List<String> prefixUsers = new LinkedList<>();
        usersText.getByPrefix(prefix).forEach(prefixUsers::add);
        return loggedUsers.entrySet().stream().filter(kv -> kv.getValue() && prefixUsers.contains(kv.getKey())).map(kv -> kv.getKey()).collect(Collectors.toList());
    }
}
