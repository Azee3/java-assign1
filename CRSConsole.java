import java.util.*;//this is used to be able to use the scanner class to input information
import java.time.LocalDate;//this is used to be able to input a date

//this class serves as the main class of the program where everything will be displayed and where the user will interact with.
//a main loop is present to let the user interact with the main menu section in which they will pick the options listed

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
            System.out.print("Welcome to CRS Console");
            System.out.print("Choose: ");

            int opt = getInt();//gets the user input and checks if it is valid


            switch (opt) {//this is going to be the switch to be able to access the specific options, the usage of the switch function is to shorten the need for a logner code and
            //for the ease of use to select the options, inside the the case theres are other methods calling for it to be able to use the the option
                case 1: staffLogin(); break;
                case 2: volunteerLogin(); break;
                case 3: volunteerSignup(); break;
                case 0: System.out.println("Thank for using CRS"); return;
                default: System.out.println("Invalid options");
            }
        }
    }

    //the getint and readdate method is used to be able to input numbers and dates it will varify it and makes it so the code is more readable and easier to understand
    //it also serves as a way to detech errors and make sure the user to input the correct information/value in it
    private static int getInt() {
        while (true) {
            try {//the try variable is used to catch the exception and make sure the user to input the correct number
                return Integer.parseInt(board.nextLine()); 
            }catch (Exception e) {//the catch variable is used when if the try method detected an error it will send out a message to the user and asked it to retry again
                System.out.print("Enter number: "); 
            }
        }
    }

    //this readdate method is used to input dates
    private static LocalDate readDate() {
        while (true) {//it calls for a while loop so if the user inputs an invalid value over and over this code will loop it self until the correct value is inputted
            try {//the try variable lets the user input a date which then will be checked for its validity
                System.out.print("Enter date (YYYY-MM-DD): ");
                return LocalDate.parse(board.nextLine());
            } catch (Exception e) {//in a case the date is typed wrong this code will appear and the user will be asked to input the correct date
                System.out.println("Invalid date");
            }
        }
    }

    //staff login
    //the stafflogin method asks the user (staff in this case) to input their username and password to see if the person logging in has a staff account or not
    private static void staffLogin() {
        System.out.print("Username: ");
        String u = board.nextLine();
        System.out.print("Password: ");
        String p = board.nextLine();

        User user = crs.findUser(u);//finds the user with the username
        if (user != null && user instanceof Staff) {//checks if the user is a staff
            Staff st = (Staff) user;//makes sure the user is a staff
            if (st.getPassword().equals(p)) {//checks if the password is correct
                staffMenu(st);//calls the staff menu
                return;
            }
        }
        System.out.println("Invalid login.");//in a case if the login is wrong this code will appear
    }

    //staff menu
    //staffmenu method will appear when first the staff login is correct and casts the user as a staff
    //afterwards this menu will appear and have the specific staff options they can choose from
    private static void staffMenu(Staff staff) {
        while (true) {//while loop is again used to make sure it loops until the user wants to logout
            System.out.println("     STAFF MENU        ");
            System.out.println("1. Add CRS Staff");
            System.out.println("2. Organize Trip");
            System.out.println("3. Manage Applications");
            System.out.println("0. Logout");
            System.out.println("=======================");
            System.out.print("Choose: ");

            int opt1 = getInt();//a variable is made and calls the getint method so be able to input the options and validate that option

            switch (opt1) {//the switch will call other method depending on the specific options called
                case 1: addStaff(); break;
                case 2: organizeTrip(staff); break;
                case 3: manageApplications(staff); break;
                case 0: return;
                default: System.out.println("Invalid");
            }
        }
    }

    //this method is used to add new staff to the system this will be only available at first by the admin
    //the staff will be able to add their information
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

        Staff s = new Staff(user, pass, name, phone, pos, joinDate);//afterinputting the information it will call the user class to add a new person to staff member
        crs.addUser(s);//the code will add the staff to the list of staff

        System.out.println("Staff added");//to make sure the code runs and confirming the addition of a new staff
    }

    //this method will be used for the staff or admin to create a trip
    //it calls upon other classes such as the user class and the trip class
    private static void organizeTrip(Staff staff) {
        System.out.println("    ORGANIZE NEW TRIP    ");

        LocalDate date = readDate();//it will read the date from the user
        System.out.print("Destination: ");
        String dest = board.nextLine();//it will read the destination from the user
        System.out.print("Description: ");
        String desc = board.nextLine();//it will read the description from the user

        System.out.print("Crisis Type (FLOOD/EARTHQUAKE/WILDFIRE): ");
        String type = board.nextLine();//reads the crisis type from the user

        System.out.print("Volunteers Required: ");
        int req = getInt();//gets the value of how many volunteers are required

        String id = "T" + (int)(Math.random() * 9000 + 1000);//this is going to be the id of the trip it generated a random number between 1000 and 9000

        Trip t = new Trip(id, date, dest, desc, type, req);//after inputting the information it will call the trip class to add a new person
        crs.addTrip(t);//the code will add the trip to the list of trips in the CRS
        staff.addTrip(t);//the code will add the trip to the list of trips that the staff has created by using the staff class

        System.out.println("Trip created with ID: " + id);//shows a massage confirming the creation of the trip
    }

    //this method manages the staff applications for trips that have been created
    //it uses the staff class and the trip class to get the trips that the staff has created
    private static void manageApplications(Staff staff) {
        List<Trip> trips = staff.getTrips();//gets the trips that the staff has created

        if (trips.isEmpty()) {//checks if the staff has no trips
            System.out.println("You have no trips.");
            return;
        }

        System.out.println("    YOUR TRIPS    ");
        for (int i = 0; i < trips.size(); i++) {//this will be the menu that will pop up to show the different trips that the staff created
            Trip tr = trips.get(i);//gets the trip from the list
            System.out.println((i+1) + ". " + tr.getTripID() + " (" + tr.getDate() + ")");
        }

        System.out.print("Select trip: ");//this will show the user which trip they want to view
        int opt = getInt() - 1;//gets the user input and checks if it is valid
        if (opt < 0 || opt >= trips.size()) {//checks if the user input is valid and prints out the massage invalid if it is not
            System.out.println("Invalid.");
            return;
        }

        Trip t = trips.get(opt);//this will be the trip that the user selected

        List<Application> apps = t.getNewApplications();//this will be used to list of new people for the trip

        if (apps.isEmpty()) {//checks if the trip has no new applications or not
            System.out.println("No new applications.");
            return;
        }

        for (Application a : apps) {//the code will be used to show the information about the new people that applied by using the application class
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
                a.accept(rem);//the code will accept the application
                System.out.println("Accepted.");
            } else {
                a.reject(rem);//the code will reject the application
                System.out.println("Rejected.");
            }
        }
    }

    //For Volunteer
    //the volunteer will be able to login and view the applications that they have made by using the volunteer class
    private static void volunteerLogin() {//this is going to be the way the volunteer who made an account login
        System.out.print("Username: ");
        String name = board.nextLine();
        System.out.print("Password: ");
        String pass = board.nextLine();

        User user = crs.findUser(name);//it will find the user and check if the user is a volunteer or has an account for it as well as checking if the password is correct
        if (user != null && user instanceof Volunteer) {
            Volunteer vol = (Volunteer) user;//makes sure the user is a volunteer
            if (vol.getPassword().equals(pass)) {//checks if the password is correct
                volunteerMenu(vol);//calls the volunteer menu
                return;
            }
        }
        System.out.println("Invalid login");//shows this massage if the login is invalid
    }
    
    //this screen or menu will appear when the user succesfully logged in, this menu is going to be used for volunteer to interact change or apply for trips
    //it uses the volunteer class to interact with the user and the application class to interact with the trip class
    private static void volunteerMenu(Volunteer vol) {
        while (true) {
            System.out.println("     VOLUNTEER MENU     ");
            System.out.println("1. Edit Profile");
            System.out.println("2. Upload Document");
            System.out.println("3. Apply for Trip");
            System.out.println("4. View Application Status");
            System.out.println("0. Logout");
            System.out.print("Choose: ");

            int opt2 = getInt();//gets the user input and checks if it is valid

            switch (opt2) {//call upon other methods depending on the specific options called
                case 1: editProfile(vol); break;
                case 2: uploadDocument(vol); break;
                case 3: applyTrip(vol); break;
                case 4: viewAppStatus(vol); break;
                case 0: return;
                default: System.out.println("Invalid.");//shows the message that the user input is invalid
            }
        }
    }

    //this is used for user to sign up as volunteer and creating their account
    //the volunteer will be able to sign up and create their account by using the volunteer class
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

        crs.addUser(v);//the code will add the volunteer to the list of volunteers

        System.out.println("Signup complete");//cinfirming the signup
    }

    //in the menu the 1st option will be to have the ability to change the volunteer information it will use this method to change their information
    //it uses the volunteer class to change the information and the user class to get the user input as well as the document class to store the information
    private static void editProfile(Volunteer vol) {
        System.out.println("1. Change Password");
        System.out.println("2. Change Name");
        System.out.println("3. Change Phone");
        System.out.print("Choose: ");

        int opt3 = getInt();//validates the user input

        switch (opt3) {//the code will be used to change the information by using the volunteer class and the user input
            case 1 -> {
                System.out.print("New password: ");
                vol.setPassword(board.nextLine());//it will set the password by using the user input
            }
            case 2 -> {
                System.out.print("New name: ");
                vol.setName(board.nextLine());//sets the name depending on the user input
            }
            case 3 -> {
                System.out.print("New phone: ");
                vol.setPhone(board.nextLine());//sets the phone depending on the user input
            }
            default -> System.out.println("Invalid.");//shows the message that the user input is invalid
        }
        System.out.println("Updated");//confirmation that the information has been updated
    }

    //this will be used to uploead document the volunteer has selected
    //uses the volunteer class to upload the document and the document class to store the information
    // just to note image i dont know how to work around and just put it as a massage on where it is located without confirming it
    private static void uploadDocument(Volunteer vol) {
        System.out.print("Type (PASSPORT/CERTIFICATE/VISA): ");//it will show this for the type of document
        String doctyp = board.nextLine();

        System.out.print("Expiry date (blank if none, YYYY-MM-DD): ");//as well as show this for the expirty date, 
                                                            // when adding a date it will be more specific when adding because it is code sesitive and can brake the program
        String expStr = board.nextLine();
        LocalDate exp = expStr.isBlank() ? null : LocalDate.parse(expStr);//this will analyze the expiry date and if it is blank then it will be null

        System.out.print("Image path: ");
        String img = board.nextLine();

        Document d = new Document(doctyp, exp, img);//after inputting the information it will call the document class to add a new person
        vol.addDocument(d);//the code will add the document to the list of documents that the volunteer has

        System.out.println("Document uploaded");//shows the message that the document has been uploaded
    }

    //its for volunteer to apply trip\
    //the classe used here is the trip class, the volunteer class and the application class to interact with the trip class
    private static void applyTrip(Volunteer vol) {

        List<Trip> list = crs.getUpcomingTrips();//this is used to call the list of upcoming trips

        if (list.isEmpty()) {//checks if the list is empty or not
            System.out.println("No upcoming trips");
            return;
        }

        System.out.println("     AVAILABLE TRIPS    ");//this menu will pop up to show the trips that are available
        for (int i = 0; i < list.size(); i++) {//this is the loop that will be used to show the trips that are available
            Trip t = list.get(i);//gets the trip from the list
            System.out.println((i+1) + ". " + t.getTripID() + " - " + t.getDate());//shows the trip id and the date by using the trip class and the loop
        }

        System.out.print("Choose trip: ");
        int opt = getInt() - 1;//gets the user input and checks if it is valid

        if (opt < 0 || opt >= list.size()) {//checks if the user input is valid
            System.out.println("Invalid");//it will show the message that the user input is invalid
            return;
        }

        Trip t = list.get(opt);//when the user already selected a trip this will be the trip that the user selected

        if (!t.hasSpace()) {//checks if the trip has space or not
            System.out.println("Trip full");//it will show the message that the trip is full
            return;
        }

        String id = "A" + (int)(Math.random() * 9000 + 1000);//this is going to be the id of the application it generated a random number between 1000 and 9000

        Application app = new Application(id, t, vol);//after inputting the information it will call the application class to add a new person

        t.addApplication(app);//the code will add the application of the user to the trip

        vol.addApplication(app);//the code will add the application of the volunteer to the trip

        System.out.println("Application submitted with ID: " + id);//it will show the message that the application has been submitted with the id
    }

    //the user or volunteer will use this to see the trips they have selected and applied
    private static void viewAppStatus(Volunteer vol) {

        System.out.println("    MY APPLICATIONS    ");

        List<Application> apps = vol.getApplications();//it will list of the trips the voluteer has applied
        if (apps.isEmpty()) {//and if the list is empty it will the there are no trips
            System.out.println("No applications");//it will show the message that there are no applications
            return;
        }

        for (Application a : apps) {//this will be the menu that will pop up to show the different trips that the volunteer has applied
            System.out.println("Trip: " + a.getTrip().getTripID());//it will show the trip id by using the trip class
            System.out.println("Date submitted: " + a.getDate());//it will show the date submitted by using the application class
            System.out.println("Status: " + a.getStatus());//it will show the status by using the application class
            System.out.println("Remarks: " + (a.getRemarks() == null ? "" : a.getRemarks()));//it will show the remarks by using the application class and 
                                                                                            //if the remarks is null then it will show an empty string
        }
    }
}
