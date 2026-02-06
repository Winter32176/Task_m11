public class FileData {
    private String fileName;
    private int id;
    private String email;
    private int age;

    public FileData(int id, String email, int age, String fileName) {
        this.id = id;
        this.email = email;
        this.age = age;
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {}

    public int getId() {
        return id;
    }

    public FileData setId(int id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public FileData setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public FileData setAge(int age) {
        this.age = age;
        return this;
    }
}
