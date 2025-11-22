import java.util.*;
import java.time.LocalDate;

//this class is used to create the trip class which will be used to store the information about the trips, 
//this class uses a list of application to store the information about the applications it will also use lambda to filter the correct value to be used
//other then that it will operate the trip to check how many trips have been set, the space left for trips and the accepted applicant that will be added to the trip

public class Trip {
    private String tripID;
    private LocalDate date;
    private String destination;
    private String description;
    private String crisisType;
    private int volunteersRequired;

    //makes an array list to list out the application infos
    private List<Application> applications = new ArrayList<>();

    //In here ive decided not to use the setter method and only implementing the getter because the value of the trip is not supposed to be changed

    public Trip(String id, LocalDate date, String dest, String desc,String crisisType, int num) {
        this.tripID = id;
        this.date = date;
        this.destination = dest;
        this.description = desc;
        this.crisisType = crisisType;
        this.volunteersRequired = num;
    }

    public void addApplication(Application a) { applications.add(a); }//adds the application to the list

    //getter
    public String getTripID() {
        return tripID;
    }
    public LocalDate getDate() {
        return date;
    }

    //counts the accepted applications
    public long countAccepted() {
        return applications.stream()
            .filter(a -> a.getStatus().equals("ACCEPTED"))//uses lambda to filter out accepted applications
            .count();
    }

    //checks if there are still enough space in the trip
    public boolean hasSpace() {
        long accepted = applications.stream()
            .filter(a -> a.getStatus().equals("ACCEPTED"))//uses lambda to filter out accepted applications
            .count();//counts the number of accepted applications
        return accepted < volunteersRequired;
    }

    //list new applications for the staff
    public List<Application> getNewApplications() {
        return applications.stream()
            .filter(a -> a.getStatus().equals("NEW"))//uses lambda to filter out new applications
            .toList(); //returns a list of new applications
    }
}
