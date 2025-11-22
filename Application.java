import java.time.LocalDate;

public class Application {
    private String applicationID;
    private LocalDate date;
    private String status;
    private String remarks;
    private Trip trip;
    private Volunteer volunteer;

    //just to add there is no need for setter in this class because the value of the variables are set in the constructor, in this class the value of the variables are set in the constructor
    //this class is used to create the application class which will be used to store the information about the applications

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

    public Application(String id, Trip t, Volunteer vol) {
        this.applicationID = id;
        this.trip = t;
        this.volunteer = vol;
        this.date = LocalDate.now();
        this.status = "NEW";
    }

    public void accept(String remarks) {//this method is used to accept the application
        this.status = "ACCEPTED";
        this.remarks = remarks;
    }

    public void reject(String remarks) {//this method is used to reject the application
        this.status = "REJECTED";
        this.remarks = remarks;
    }
}
