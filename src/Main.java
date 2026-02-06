import java.io.File;
import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Logger;

public class Main {
    private static final Logger log = Logger.getLogger(Main.class.getName());

    static void main(String[] args) {
        List<FileData> data = new ArrayList<>();
        List<User> users = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {

                Path projectDir = Paths.get("").toAbsolutePath();       // папка проекта (обычно)
                File file;

                System.out.println("Enter the file path (or 'exit' to quit or 'output' for displaying) : ");
                String input = scanner.nextLine();
                if (input.equalsIgnoreCase("exit")) {
                    break;
                }
                if (input.equalsIgnoreCase("output")) {
                    if (!data.isEmpty()) {
                        for (var d : users) {
                            System.out.println("ID: " + d.getId() + ", Email: " + d.getEmail() + ", Age: " + d.getAge());
                        }
                    }
                    continue;
                }
                if (StringWorker.checkInput(input)) {

                    if (!input.contains("C:") && !input.contains("D:") && !input.contains("E:")) {
                        var path=projectDir.toUri().getPath() + "/src/" + input;
                        file = new File(path);

                    } else {
                        file = new File(input);
                    }

                    if (data.stream().anyMatch(d -> d.getFileName().equals(file.getName()))) {
                        log.info("File already processed");
                        continue;
                    }


                    try (FileProcessor fp = new FileProcessor(file)) {
                        var cont = fp.getFileContent();

                        for (var line : cont) {
                            try {
                                StringWorker.processInputFromFile(line, file.getName());
                            } catch (IllegalArgumentException | NotProperAgeException e) {
                                log.info(e.getMessage());
                            }
                        }

                        data.addAll(StringWorker.getDataFromFileList());
                        for (var d : data) {
                            users.add(new User(d.getId(), d.getEmail(), d.getAge()));
                        }
                        System.out.println("File processed successfully: " + file.getPath());
                    } catch (FileNotFoundException e) {
                        log.info(e.getMessage());
                    } catch (Exception e) {
                        log.severe("An unexpected error occurred: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
                    }
                }
            } catch (IllegalArgumentException e) {
                log.info("Invalid input. Please enter a valid file path.");
            } catch (Exception e) {
                log.severe("An unexpected error occurred: " + e.getMessage() + "\n" + Arrays.toString(e.getStackTrace()));
            }
        }

        scanner.close();

    }


}
