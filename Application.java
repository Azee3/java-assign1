import java.time.LocalDate;//to easily import the LocalDate class

//this class is used to create the application class which will be used to store the information about the applications the user/volunteed has made
//the class serves as a child class of the trip class

public class Application {
    private String applicationID;
    private LocalDate date;
    private String status;
    private String remarks;
    private Trip trip;
    private Volunteer volunteer;

    //just to add there is no need for setter in this class because the value of the variables are set in the constructor, 
    // in this class the value of the variables are set in the constructor

    //Getter application
    public String getApplicationID() {
        return applicationID;
    }
    public LocalDate getDate() {
        return date;
    }
    public String getStatus() {
        return status;
    }
    public String getRemarks() {
        return remarks;
    }
    public Trip getTrip() {
        return trip;
    }
    public Volunteer getVolunteer() {
        return volunteer; 
    }

    public Application(String id, Trip t, Volunteer vol) {//the constructor will be created to make the application class and used the value to determine its outcome
        this.applicationID = id;
        this.trip = t;
        this.volunteer = vol;
        this.date = LocalDate.now();//when the application is created it will automatically set the date as the current date
        this.status = "NEW";//when makeing a new application it will automatically set the status as new
    }

    public void accept(String remarks) {//this method is used to accept the application
        this.status = "ACCEPTED";//when the application is accepted it will automatically set the status as accepted
        this.remarks = remarks;//this code will let the staff give the volunteer a reason for its decision
    }

    public void reject(String remarks) {//this method is used to reject the application
        this.status = "REJECTED";//when the application is rejected it will automatically set the status as rejected
        this.remarks = remarks;//this code will let the staff give the volunteer a reason for its decision
    }
}
