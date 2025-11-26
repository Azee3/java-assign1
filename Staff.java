import java.util.*;//to easily import the list class

//this class is used to create the staff class which is a child class of the user class
//in this class it stores information about the staff by creating a list it also stores the information about the trips that the staff has created

public class Staff extends User {
    //the variables that will be used to store the information about the staff
    private String position;
    private String dateJoined;
    private List<Trip> organizedTrips = new ArrayList<>();//list of trips organized by the staff

    //the constructor will be created to call the constructor of the parent class which is from the user class
    public Staff(String username, String pwd, String name, String phone,String position, String dateJoined) {
        super(username, pwd, name, phone);//calls the constructor of the parent class which is from the user class
        this.position = position;
        this.dateJoined = dateJoined;
    }

    public void addTrip(Trip t) {//adds trip to the list of trips organized by the staff
        organizedTrips.add(t);//adds the trip to the list of trips organized by the staff
    }

    //get the trips organize by the staff
    public List<Trip> getTrips() {
        return organizedTrips.stream().toList();//returns a list of trips organized by the staff
    }
}
