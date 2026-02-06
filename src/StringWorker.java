import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public class StringWorker {
    private static final List<FileData> dataFromFileList = new ArrayList<>();
    private static final Logger log = Logger.getLogger(StringWorker.class.getName());

    protected static List<FileData> getDataFromFileList() {
        var temp = dataFromFileList.stream().toList();
        dataFromFileList.clear();
        return temp;
    }

    protected static boolean checkInput(String input) throws IllegalArgumentException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty.");
        }

        if (!input.matches(".*\\..+")) {
            throw new IllegalArgumentException("Input must contain a valid file path. Example: C:\\folder\\file.txt");
        }

        return true;
    }

    protected static void processInputFromFile(String input, String filename) throws IllegalArgumentException, NotProperAgeException {
        if (input == null || input.trim().isEmpty()) {
            throw new IllegalArgumentException("Input cannot be null or empty.");
        }
        var str = input.split(",");

        Arrays.stream(str).map(String::trim).forEach(x -> {
            if (x.isEmpty()) {
                throw new IllegalArgumentException("Input must contain a valid format. Example: id,email,age");
            }
        });

        try {
            if (str[0].isEmpty() || !str[0].matches("^[0-9]+$")) {
                throw new IllegalArgumentException("Input must contain a valid id. Id must be a number. Example: 123");
            }
            if (str[1].isEmpty() || !str[1].matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
                throw new IllegalArgumentException("Input must contain a valid email address. Example: email@email.com");
            }
            if (str[2].isEmpty() || !str[2].matches("^[0-9]+$") || Integer.parseInt(str[2]) < 18 || Integer.parseInt(str[2]) > 120) {
                throw new NotProperAgeException("Input must contain a valid age. Age must be a number between 18 and 120. Example: 25");
            }
            dataFromFileList.add(new FileData(Integer.parseInt(str[0]), str[1], Integer.parseInt(str[2]), filename));

        } catch (ArrayIndexOutOfBoundsException e) {
            throw new ArrayIndexOutOfBoundsException("Input must contain a valid format. Example: id,email,age");
        } catch (NotProperAgeException e) {
            log.info(e.getMessage());
        }
    }

}
