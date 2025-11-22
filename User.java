public abstract class User {
    private String username;
    private String password;
    private String name;
    private String phone;

    //in this class it uses the abstract class to create the user class it allows for the user(people using crs) to add and also alter there information
    //it is also used to register new the user to the system as well, this will later on be used to register the new staff that the admin will include
    //for the staff this is going to be used as the baseline login system as well

    public User(String username, String password, String name, String phone) {
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    //Getters
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    //Setters
    public void setPassword(String password) {
        this.password = password;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
    
}
