public class User {
    private int id;
    private String email;
    private int age;

    public User(int id, String email, int age) {
        this.id = id;
        this.email = email;
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public User setId(int id) {
        this.id = id;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public User setEmail(String email) {
        this.email = email;
        return this;
    }

    public int getAge() {
        return age;
    }

    public User setAge(int age) {
        this.age = age;
        return this;
    }
}
