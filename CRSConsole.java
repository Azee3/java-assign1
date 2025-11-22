import java.util.*;
import java.time.LocalDate;

public class CRSConsole {

    private static Scanner board = new Scanner(System.in);//used to be able to input a value
    private static CRS crs = new CRS();//used to store data or values later on

    public static void main(String[] args) {

        crs.addUser(new Staff("admin", "admin123", "System Admin", "0123456789", "Manager", "2025-01-01"));//i had to hard code this into the program/code to be able to access
                                                                                                         //the 1st staff/admin account the admin account will be the 1st account
                                                                                                         //and anything tht follows will be a staff account that shares the same
                                                                                                         //controls as the admin

        while (true) {//this is going to be the first menu that will pop up to show the different option to login and register either as a staff or user
            System.out.println("      CRS MAIN MENU      ");
            System.out.println("1. Staff login");
            System.out.println("2. Login");
            System.out.println("3. Sign Up");
            System.out.println("0. Exit");
            System.out.println("=========================");
            System.out.print("Choose: ");

            int opt = getInt();

            switch (opt) {//this is going to be the switch to be able to access the specific options
                case 1: staffLogin(); break;
                case 2: volunteerLogin(); break;
                case 3: volunteerSignup(); break;
                case 0: System.out.println("Thank for using CRS"); return;
                default: System.out.println("Invalid options");
            }
        }
    }

    //the getint and readdate method is used to be able to input numbers and dates i will varify it and makes it so the code is more readable and easier to understand
    //because there is no need to put addition code to the code to be able to input a number or date
    private static int getInt() {
        while (true) {
            try { return Integer.parseInt(board.nextLine()); }
            catch (Exception e) { System.out.print("Enter number: "); }
        }
    }

    private static LocalDate readDate() {
        while (true) {
            try {
                System.out.print("Enter date (YYYY-MM-DD): ");
                return LocalDate.parse(board.nextLine());
            } catch (Exception e) {
                System.out.println("Invalid date");
            }
        }
    }

    //staff login
    private static void staffLogin() {
        System.out.print("Username: ");
        String u = board.nextLine();
        System.out.print("Password: ");
        String p = board.nextLine();

        User user = crs.findUser(u);//finds the user with the username
        if (user != null && user instanceof Staff) {//checks if the user is a staff
            Staff st = (Staff) user;
            if (st.getPassword().equals(p)) {//checks if the password is correct
                staffMenu(st);
                return;
            }
        }
        System.out.println("Invalid login.");
    }

    //staff menu
    private static void staffMenu(Staff staff) {
        while (true) {
            System.out.println("     STAFF MENU        ");
            System.out.println("1. Add CRS Staff");
            System.out.println("2. Organize Trip");
            System.out.println("3. Manage Applications");
            System.out.println("0. Logout");
            System.out.println("=======================");
            System.out.print("Choose: ");

            int opt1 = getInt();

            switch (opt1) {
                case 1: addStaff(); break;
                case 2: organizeTrip(staff); break;
                case 3: manageApplications(staff); break;
                case 0: return;
                default: System.out.println("Invalid.");
            }
        }
    }

    //this method is used to add new staff to the system this will be only available at first by the admin
    private static void addStaff() {
        System.out.println("    RECORD NEW STAFF    ");

        System.out.print("Username: ");
        String user = board.nextLine();

        System.out.print("Password: ");
        String pass = board.nextLine();

        System.out.print("Name: ");
        String name = board.nextLine();

        System.out.print("Phone: ");
        String phone = board.nextLine();

        System.out.print("Position: ");
        String pos = board.nextLine();

        System.out.print("Date Joined: ");
        String joinDate = board.nextLine();

        Staff s = new Staff(user, pass, name, phone, pos, joinDate);//afterinputting the information it will call the user class to add a new person
        crs.addUser(s);

        System.out.println("Staff added");
    }

    //this method will be used for the staff or admin to create a trip
    private static void organizeTrip(Staff staff) {
        System.out.println("    ORGANIZE NEW TRIP    ");

        LocalDate date = readDate();
        System.out.print("Destination: ");
        String dest = board.nextLine();
        System.out.print("Description: ");
        String desc = board.nextLine();

        System.out.print("Crisis Type (FLOOD/EARTHQUAKE/WILDFIRE): ");
        String type = board.nextLine();

        System.out.print("Volunteers Required: ");
        int req = getInt();

        String id = "T" + (int)(Math.random() * 9000 + 1000);//this is going to be the id of the trip it generated a random number between 1000 and 9000

        Trip t = new Trip(id, date, dest, desc, type, req);//after inputting the information it will call the trip class to add a new person
        crs.addTrip(t);
        staff.addTrip(t);

        System.out.println("Trip created with ID: " + id);
    }

    //this method manages the staff applications for trips that have been created
    private static void manageApplications(Staff staff) {
        List<Trip> trips = staff.getTrips();//gets the trips that the staff has created

        if (trips.isEmpty()) {//checks if the staff has no trips
            System.out.println("You have no trips.");
            return;
        }

        System.out.println("    YOUR TRIPS    ");
        for (int i = 0; i < trips.size(); i++) {//this will be the menu that will pop up to show the different trips that the staff created
            Trip tr = trips.get(i);
            System.out.println((i+1) + ". " + tr.getTripID() + " (" + tr.getDate() + ")");
        }

        System.out.print("Select trip: ");//this will show the user which trip they want to view
        int opt = getInt() - 1;
        if (opt < 0 || opt >= trips.size()) {
            System.out.println("Invalid.");
            return;
        }

        Trip t = trips.get(opt);//this will be the trip that the user selected

        List<Application> apps = t.getNewApplications();//this will be used to list of new people for the trip

        if (apps.isEmpty()) {//checks if the trip has no new applications or not
            System.out.println("No new applications.");
            return;
        }

        for (Application a : apps) {//the code will be used to show the information about the new people that applied
            System.out.println("\nApplication ID: " + a.getApplicationID());
            System.out.println("Volunteer: " + a.getVolunteer().getName() + " (" + a.getVolunteer().getPhone() + ")");
            System.out.println("Submitted on: " + a.getDate());
            System.out.println("Status: " + a.getStatus());

            System.out.print("Approve (A) or Reject (R)? ");//it will show this for the staff to either reject or approve the application
            String inp = board.nextLine();

            System.out.print("Remarks: ");//in here the staff can give their input or reason behind it
            String rem = board.nextLine();

            //depending on the input this code will run to accept or reject it
            if (inp.equalsIgnoreCase("A")) {
                a.accept(rem);
                System.out.println("Accepted.");
            } else {
                a.reject(rem);
                System.out.println("Rejected.");
            }
        }
    }

    //For Volunteer
    private static void volunteerLogin() {//this is going to be the way the volunteer who made an account login
        System.out.print("Username: ");
        String name = board.nextLine();
        System.out.print("Password: ");
        String pass = board.nextLine();

        User user = crs.findUser(name);//it will find the user and check if the user is a volunteer or has an account for it as well as checking if the password is correct
        if (user != null && user instanceof Volunteer) {
            Volunteer vol = (Volunteer) user;
            if (vol.getPassword().equals(pass)) {
                volunteerMenu(vol);
                return;
            }
        }
        System.out.println("Invalid login.");
    }
    
    //this screen or menu will appear when the user succesfully logged in, this menu is going to be used for volunteer to interact change or apply for trips
    private static void volunteerMenu(Volunteer vol) {
        while (true) {
            System.out.println("     VOLUNTEER MENU     ");
            System.out.println("1. Edit Profile");
            System.out.println("2. Upload Document");
            System.out.println("3. Apply for Trip");
            System.out.println("4. View Application Status");
            System.out.println("0. Logout");
            System.out.print("Choose: ");

            int opt2 = getInt();

            switch (opt2) {
                case 1: editProfile(vol); break;
                case 2: uploadDocument(vol); break;
                case 3: applyTrip(vol); break;
                case 4: viewAppStatus(vol); break;
                case 0: return;
                default: System.out.println("Invalid.");
            }
        }
    }

    //this is used for user to sign up as volunteer and creating their account
    private static void volunteerSignup() {
        System.out.println("    VOLUNTEER SIGN UP    ");

        System.out.print("Username: ");
        String user = board.nextLine();

        System.out.print("Password: ");
        String pass = board.nextLine();

        System.out.print("Name: ");
        String name = board.nextLine();

        System.out.print("Phone: ");
        String phone = board.nextLine();

        Volunteer v = new Volunteer(user, pass, name, phone);//after inputting the information it will call the user class to add a new person
        crs.addUser(v);

        System.out.println("Signup complete");
    }

    //in the menu the 1st option will be to have the ability to change the volunteer information it will use this method to change their information
    private static void editProfile(Volunteer vol) {
        System.out.println("1. Change Password");
        System.out.println("2. Change Name");
        System.out.println("3. Change Phone");
        System.out.print("Choose: ");

        int opt3 = getInt();

        switch (opt3) {
            case 1 -> {
                System.out.print("New password: ");
                vol.setPassword(board.nextLine());
            }
            case 2 -> {
                System.out.print("New name: ");
                vol.setName(board.nextLine());
            }
            case 3 -> {
                System.out.print("New phone: ");
                vol.setPhone(board.nextLine());
            }
            default -> System.out.println("Invalid.");
        }
        System.out.println("Updated.");
    }

    //this will be used to uploead document the volunteer has selected
    private static void uploadDocument(Volunteer vol) {
        System.out.print("Type (PASSPORT/CERTIFICATE/VISA): ");//it will show this for the type of document
        String doctyp = board.nextLine();

        System.out.print("Expiry date (blank if none, YYYY-MM-DD): ");//as well as show this for the expirty date, when adding a date it will be more specific when adding because it is code sesitive and can brake the program
        String expStr = board.nextLine();
        LocalDate exp = expStr.isBlank() ? null : LocalDate.parse(expStr);//this will analyze the expiry date and if it is blank then it will be null

        System.out.print("Image path: ");
        String img = board.nextLine();

        Document d = new Document(doctyp, exp, img);//after inputting the information it will call the document class to add a new person
        vol.addDocument(d);

        System.out.println("Document uploaded");
    }

    //its for volunteer to apply trip
    private static void applyTrip(Volunteer vol) {

        List<Trip> list = crs.getUpcomingTrips();//this is used to call the list of upcoming trips

        if (list.isEmpty()) {//checks if the list is empty or not
            System.out.println("No upcoming trips");
            return;
        }

        System.out.println("     AVAILABLE TRIPS    ");//this menu will pop up to show the trips that are available
        for (int i = 0; i < list.size(); i++) {//this is the loop that will be used to show the trips that are available
            Trip t = list.get(i);
            System.out.println((i+1) + ". " + t.getTripID() + " - " + t.getDate());
        }

        System.out.print("Choose trip: ");
        int opt = getInt() - 1;

        if (opt < 0 || opt >= list.size()) {//checks if the user input is valid
            System.out.println("Invalid");
            return;
        }

        Trip t = list.get(opt);//when the user already selected a trip this will be the trip that the user selected

        if (!t.hasSpace()) {//checks if the trip has space or not
            System.out.println("Trip full");
            return;
        }

        String id = "A" + (int)(Math.random() * 9000 + 1000);//this is going to be the id of the application it generated a random number between 1000 and 9000

        Application app = new Application(id, t, vol);//after inputting the information it will call the application class to add a new person

        t.addApplication(app);//the code will add the application of the user to the trip

        vol.addApplication(app);//the code will add the application of the volunteer to the trip

        System.out.println("Application submitted with ID: " + id);
    }

    //the user or volunteer will use this to see the trips they have selected and applied
    private static void viewAppStatus(Volunteer vol) {

        System.out.println("    MY APPLICATIONS    ");

        List<Application> apps = vol.getApplications();//it will list of the trips the voluteer has applied
        if (apps.isEmpty()) {//and if the list is empty it will the there are no trips
            System.out.println("No applications");
            return;
        }

        for (Application a : apps) {//this will be the menu that will pop up to show the different trips that the volunteer has applied
            System.out.println("Trip: " + a.getTrip().getTripID());
            System.out.println("Date submitted: " + a.getDate());
            System.out.println("Status: " + a.getStatus());
            System.out.println("Remarks: " + (a.getRemarks() == null ? "" : a.getRemarks()));
        }
    }
}
