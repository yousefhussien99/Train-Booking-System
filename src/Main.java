import java.sql.*;
import java.util.Scanner;

public class Main {
    private static Connection conn;
    private static final String url = "jdbc:mysql://127.0.0.1:3307/db";
    private static final String username = "root";
    private static final String password = "Example@2021!";

    public Main() throws SQLException {
        conn = DriverManager.getConnection(url, username, password);
    }

    public static void main(String[] args) {
        try {
            Main main = new Main();
            Scanner scanner = new Scanner(System.in);
            boolean exit = false;

            user user=new user();
            admin admin=new admin();
            while (!exit) {
                System.out.println("\n=== Train Booking Menu ===");
                System.out.println("1. Sign up a new customer");
                System.out.println("2. Sign up a new admin");
                System.out.println("3. Log in as a customer");
                System.out.println("4. Log in as an admin");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline character

                switch (choice) {
                    case 1:
                        System.out.println("=== Sign Up User ===");
                        System.out.print("Enter user ID: ");
                        int userId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        System.out.print("Enter username: ");
                        String username = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String password = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String email = scanner.nextLine();

                        user newUser = new user(userId, username, password, email);
                        boolean signUpSuccess = newUser.signUp();

                        if (signUpSuccess) {
                            System.out.println("User signed up successfully!");
                        } else {
                            System.out.println("Failed to sign up user.");
                        }
                        break;



                        // User sign up logic


                    case 2:
                        System.out.println("=== Sign Up ADMIN ===");
                        System.out.print("Enter admin ID: ");
                        int adminId = scanner.nextInt();
                        scanner.nextLine(); // Consume newline character
                        System.out.print("Enter username: ");
                        String adminUsername = scanner.nextLine();
                        System.out.print("Enter password: ");
                        String adminPassword = scanner.nextLine();
                        System.out.print("Enter email: ");
                        String adminEmail = scanner.nextLine();

                        admin newAdmin = new admin(adminId, adminUsername, adminPassword, adminEmail);
                        boolean signUpSuccessadmin = newAdmin.signUp();


                        if (signUpSuccessadmin) {
                            System.out.println("Admin signed up successfully!");
                        } else {
                            System.out.println("Failed to sign up admin.");
                        }
                        break;



                    case 3:
                        System.out.println("\n=== Login as customer ===");
                        System.out.print("Enter email: ");
                         email = scanner.nextLine();
                        System.out.print("Enter password: ");
                         password = scanner.nextLine();
                         user.userlogin(email,password);
                         user customer=new user();
                         if(user.userlogin(email,password)) {
                             while (!exit)
                             {
                                 System.out.println("1. show available seats");
                                 System.out.println("2. perform booking");
                                 System.out.println("3. cancel booking");
                                 System.out.println("4. update profile");
                                 System.out.println("5. view profile");
                                 System.out.println("0. Exit");
                                 choice = scanner.nextInt();
                                 switch(choice)
                                 {
                                     case 1:
                                         System.out.println("\n=== Show Available Seats ===");
                                         System.out.println("enter the trip id");
                                         int trip_id = scanner.nextInt();
                                         user.showAvailableSeats(trip_id);
                                         break;

                                     case 2:
                                         System.out.println("\n=== Perform Booking ===");
                                         System.out.println("enter the trip id");
                                          trip_id =scanner.nextInt() ;
                                         user.showAvailableSeats(trip_id);
                                         System.out.println("enter the seat id you want to book");
                                         int seat_id=scanner.nextInt();
                                         user.performBooking(email,trip_id,seat_id);
                                         break;

                                     case 3:
                                         System.out.println("\n=== Cancel Booking ===");
                                         System.out.println("enter the booking id");
                                         int bookingid = scanner.nextInt();
                                         user.cancelBooking(bookingid);
                                         break;
                                     case 4:
                                         System.out.println("\n=== Update Profile ===");
                                         System.out.println("Please enter your new name (or press Enter to skip):");
                                         scanner.nextLine();
                                         String newname = scanner.nextLine();
                                         System.out.println("Please enter your new pw (or press Enter to skip):");
                                         String newpw = scanner.nextLine();
                                         user.editProfile(newname, newpw, email);
                                         System.out.println("Profile updated!");
                                         break;
                                     case 5:
                                         System.out.println("\n=== View Profile ===");
                                         System.out.println(user.viewProfile(email));
                                         break;

                                     case 0:
                                         exit = true;
                                         break;

                                 }
                             }
                         }
                        break;

                    case 4:
                        System.out.println("\n=== Login as Admin ===");
                        System.out.print("Enter email: ");
                        adminEmail = scanner.nextLine();
                        System.out.print("Enter password: ");
                        adminPassword = scanner.nextLine();
                        if(admin.Adminlogin(adminEmail,adminPassword))
                        {
                            while(!exit)
                            {

                                System.out.println("1. Add a station");
                                System.out.println("2. Add a train");
                                System.out.println("3. Update a train's details");
                                System.out.println("4. Add a trip");
                                System.out.println("5. Update a trip's details");
                                System.out.println("6. Update profile");
                                System.out.println("0. Exit");
                                choice = scanner.nextInt();
                                scanner.nextLine();
                                switch(choice)
                                {
                                    case 1:
                                        System.out.println("\n=== Add a station ===");
                                        System.out.println("enter station id");
                                        int station_id=scanner.nextInt();
                                        System.out.println("enter station name");
                                        scanner.nextLine();
                                        String station_name=scanner.nextLine();
                                        System.out.println("enter city");
                                        String city=scanner.nextLine();
                                        System.out.println("enter state");
                                        String state=scanner.nextLine();
                                        admin.addStation(station_id,station_name,city,state);
                                        break;



                                    case 2:
                                        System.out.println("\n=== Add a Train ===");
                                        System.out.println("enter train id");
                                        int train_id=scanner.nextInt();
                                        System.out.println("enter train name");
                                        scanner.nextLine();
                                        String train_name=scanner.nextLine();
                                        System.out.println("enter departure station id");
                                        int departure_station = scanner.nextInt();
                                        System.out.println("enter arrival station id");
                                        int arrival_station=scanner.nextInt();

                                        admin.addTrain(train_id,train_name,departure_station,arrival_station,adminEmail);


                                        // Add a train logic
                                        break;

                                    case 3:
                                        System.out.println("\n=== Update Train's Details ===");
                                        System.out.println("enter the id of the train you want to update");
                                         train_id=scanner.nextInt();
                                        while(!exit){
                                            System.out.println("1. update train name");
                                            System.out.println("2. update departure station");
                                            System.out.println("3. update arrival station");
                                            System.out.println("4. delete a train");
                                            System.out.println("0. Exit");
                                            choice = scanner.nextInt();
                                            scanner.nextLine();
                                            switch(choice) {
                                                case 1:
                                                    System.out.println("enter the new train name");
                                                    String newTrainName = scanner.nextLine();
                                                    admin.editTrainName(adminEmail, train_id, newTrainName);
                                                    break;
                                                case 2:
                                                    System.out.println("enter the new departure station id");
                                                    int departurestation = scanner.nextInt();
                                                    admin.editDepartureStation(adminEmail, train_id, departurestation);
                                                    break;
                                                case 3:
                                                    System.out.println("enter the new arrival station id");
                                                    int arrivalstation = scanner.nextInt();
                                                    admin.editArrivalStation(adminEmail, train_id, arrivalstation);
                                                    break;
                                                case 4:
                                                    admin.deleteTrain(train_id);
                                                    break;
                                                case 0:
                                                    exit = true;
                                                    break;
                                            }



                                            }
                                        break;

                                    case 4:
                                        System.out.println("\n=== Add a Trip ===");
                                        System.out.println("Enter trip ID:");
                                        int tripID = scanner.nextInt();
                                        System.out.println("Enter train ID:");
                                        int trainID = scanner.nextInt();
                                        scanner.nextLine(); // Consume the newline character left by nextInt()
                                        System.out.println("Enter trip date:");
                                        String tripDate = scanner.nextLine();
                                        System.out.println("Enter number of seats:");
                                        int numofseats=scanner.nextInt();

                                        admin.addTrip(tripID, trainID, tripDate, adminEmail,numofseats);

                                        break;

                                    case 5:
                                        System.out.println("\n=== Update Trip's Details ===");
                                        System.out.println("enter the id of the trip you want to update");
                                        int trip_id=scanner.nextInt();
                                        while(!exit){
                                            System.out.println("1. update train id");
                                            System.out.println("2. update trip date");
                                            System.out.println("3. delete a trip");
                                            System.out.println("0. Exit");
                                            choice = scanner.nextInt();
                                            scanner.nextLine();
                                            switch(choice)
                                            {
                                                case 1:
                                                    System.out.println("enter the new train id");
                                                    int newTrainId=scanner.nextInt();
                                                    admin.editTrainID(adminEmail,trip_id,newTrainId);
                                                    break;
                                                case 2:
                                                    System.out.println("enter the new trip date");
                                                    String newTripDate=scanner.nextLine();
                                                    admin.editTripDate(adminEmail,trip_id,newTripDate);
                                                    break;
                                                case 3:
                                                    admin.deleteTrip(trip_id);
                                                    break;
                                                case 0:
                                                    exit=true;
                                                    break;



                                            }
                                        }

                                        break;
                                    case 6:
                                        System.out.println("\n=== Update Profile ===");
                                        System.out.println("enter your new username");
                                         username=scanner.nextLine();
                                        System.out.println("enter your new password");
                                        password=scanner.nextLine();
                                        admin.editProfile(username,password,adminEmail);
                                        break;


                                    case 0:
                                        exit = true;
                                        break;
                                }
                            }
                        }


                        break;


                    default:
                        System.out.println("Invalid choice. Please try again.");
                }
            }

            // Close the database connection
            conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



}