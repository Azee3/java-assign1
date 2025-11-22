import java.util.*;

//this class is used to create the staff class which is a child class of the user class
//in this class it stores information about the staff by creating a list it also stores the information about the trips that the staff has created

public class Staff extends User {
    private String position;
    private String dateJoined;
    private List<Trip> organizedTrips = new ArrayList<>();//list of trips organized by the staff

    public Staff(String username, String pwd, String name, String phone,String position, String dateJoined) {
        super(username, pwd, name, phone);//calls the constructor of the parent class which is from the user class
        this.position = position;
        this.dateJoined = dateJoined;
    }

    public void addTrip(Trip t) {//adds trip to the list of trips organized by the staff
        organizedTrips.add(t);
    }

    //get the trips organize by the staff
    public List<Trip> getTrips() {
        return organizedTrips.stream().toList();
    }
}
