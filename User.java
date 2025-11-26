//this class is used to create the user class which is an abstract class
//it allows for the user(people using crs) to add and also alter there information
//it is also used to register new the user to the system as well, this will later on be used to register the new staff that the admin will include
//for the staff this is going to be used as the baseline login system as well

public abstract class User {
    //the variables that will be used to store the information about the user
    private String username;
    private String password;
    private String name;
    private String phone;

    public User(String username, String password, String name, String phone) {//it will use the constructor to create the user class
        this.username = username;
        this.password = password;
        this.name = name;
        this.phone = phone;
    }

    //Getters
    //it is used here to get the data from other classes that are using the user class
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
    //it is used here to set the data from other classes so its able to modify the data
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
