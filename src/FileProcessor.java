import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;


public class FileProcessor implements Closeable {
    private static final Logger log = Logger.getLogger(FileProcessor.class.getName());
    FileReader fileReader;
    BufferedReader bufferedReader;
    File file;

    FileProcessor(File file) throws FileNotFoundException {
        if (file == null || !checkFile(file)) {
            String path = (file != null) ? file.getPath() : "null";
            throw new FileNotFoundException("File not found: " + path);
        }

        try {
            if (!file.canRead() || !file.canWrite() || !file.isFile()) {
                throw new MyExceptions1("File is not readable/writible or is not a file: " + file.getPath(), "Check file permissions and type");
            }
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
        } catch (IOException e) {
            log.warning("IO exception: " + e.getMessage());
            close();
        } catch (MyExceptions1 e) {
            log.warning("Custom exception: " + e.getMessage() + " Cause: " + e.getMyCause() + " SerialVersionUID: " + MyExceptions1.getUID());
            close();
        } catch (Exception e) {
            log.warning("Error initializing FileProcessor: " + e.getMessage());
            close();
        }
    }

    public FileReader getFileReader() {
        return fileReader;
    }

    public FileProcessor setFileReader(FileReader fileReader) {
        this.fileReader = fileReader;
        return this;
    }

    public FileProcessor setFile(File file) {
        this.file = file;
        return this;
    }

    public boolean checkFile(File file) throws NullPointerException {
        if (file == null) {
            throw new NullPointerException("File is null");
        }
        return file.exists();
    }

    public List<String> getFileContent() throws IOException {
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            lines.add(line);
        }
        return lines;
    }

    @Override
    public void close() {
        try {
            if (bufferedReader != null) {
                bufferedReader.close();
            }
            if (fileReader != null) {
                fileReader.close();
            }
        } catch (IOException e) {
            System.out.println("Error during cleanup: " + e.getMessage());
        }
    }
}
