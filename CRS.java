import java.util.*;
import java.time.LocalDate;

//the class is used to store the people who have registered as staff and the information about the trips that the staff has created
//it makes a list to store the data and also to get and sort the data

public class CRS {
    private List<User> users = new ArrayList<>();//creates a variable for the list of users
    private List<Trip> trips = new ArrayList<>();//creates a variable for the list of trips

    //adds the user to the list of users
    public void addUser(User u) {
        users.add(u);
    }

    //finds the user by username using lambda
    public User findUser(String username) {
        return users.stream()
            .filter(u -> u.getUsername().equals(username))
            .findFirst()//finds the first user that has the username
            .orElse(null);//if there is no user with the username then return null
    }

    //add the trip to the list of trips
    public void addTrip(Trip t) {
        trips.add(t);
    }

    //finds the trip by using the id given and using lambda to filter out the trips that do not have the same id
    public Trip findTrip(String tripID) {
        return trips.stream()
            .filter(t -> t.getTripID().equals(tripID))//uses lambda to filter
            .findFirst()
            .orElse(null);
    }

    //gets all the upcoming trips
    public List<Trip> getUpcomingTrips() {
        return trips.stream()
            .filter(t -> t.getDate().isAfter(LocalDate.now()))//uses lambda to filter out the trips that are not in the future
            .toList();
    }
}
