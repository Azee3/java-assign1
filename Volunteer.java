import java.util.*;

//the class is used to store the people who have registered as volunteers and the information about the documents that the volunteer has
//it makes a list to store the data and also to get and sort the data

public class Volunteer extends User {
    //these variables are used to store documents using an arraylist
    private List<Document> documents = new ArrayList<>();
    private List<Application> applications = new ArrayList<>();

    public Volunteer(String user, String psw, String name, String phnum) {
        super(user, psw, name, phnum);//calls the constructor of the parent class which is from the user class
    }

    //adds the document to the list of documents
    public void addDocument(Document doc) {
        documents.add(doc);
    }

    //adds the application to the list of applications
    public void addApplication(Application app) {
        applications.add(app);
    }

    //getters for the documents
    public List<Document> getDocuments() {
        return List.copyOf(documents);
    }

    //getters for the applications
    public List<Application> getApplications() {
        return List.copyOf(applications);
    }

    //getter to return the valid documents
    public List<Document> getValidDocuments() {
        return documents.stream().filter(Document::isValid).toList();//uses lambda to filter out the invalid documents
    }

    //getter to return the valid applications
    public Application getApplicationForTrip(String tripID) {
        return applications.stream()
            .filter(a -> a.getTrip().getTripID().equals(tripID))//uses lambda to filter out the applications that are not for the trip
            .findFirst()
            .orElse(null);
    }
}
