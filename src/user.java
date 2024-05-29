import java.sql.*;

public class user {
    public static user user;
    private int userId;
    private int tripId;
    private String username;
    private  String password;
    private  String email;
    private static Connection conn;
    private static final String url = "jdbc:mysql://127.0.0.1:3307/db";
    private static final String usernamee= "root";
    private static final String passwordd= "Example@2021!";

    public user() throws SQLException {
        conn = DriverManager.getConnection(url, usernamee, passwordd);
    }
    public user(int userId, String username, String password, String email) {
        this.userId = userId;
        this.username = username;
        this.password = password;
        this.email = email;
    }




    // Getter and setter methods for the User class

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public int getTripId() {
        return tripId;
    }

    public void setTripId(int tripId) {
        this.tripId = tripId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean signUp() {
        try {
            // Create a connection to the database
            Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
            // Prepare the SQL statement
            String sql = "INSERT INTO user (user_id, Username, Password, Email) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, userId);
            statement.setString(2, username);
            statement.setString(3, password);
            statement.setString(4, email);

            // Execute the query
            int rowsAffected = statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            conn.close();

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean userlogin(String email, String password) throws SQLException {
        // Check if the email and password are correct
        String name;
        Connection conn = DriverManager.getConnection(url, usernamee, passwordd);        String sqlcheckLogin = "SELECT * FROM user WHERE Email=? AND Password=?";
        PreparedStatement statement = conn.prepareStatement(sqlcheckLogin);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet checkLoginResult = statement.executeQuery();

        if (checkLoginResult.next()) {
            name = checkLoginResult.getString("Username");

            return true;
        } else {
            System.out.println("Invalid email or password. Please try again.");
            return false;
        }
    }
    public static void editProfile(String newName, String newPassword, String email) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection(url, usernamee, passwordd);            String sqlUpdateUser = "UPDATE user SET Username = ?, Password = ? WHERE Email = ?";
            PreparedStatement updateUser = conn.prepareStatement(sqlUpdateUser);
            updateUser.setString(1, newName);
            updateUser.setString(2, newPassword);
            updateUser.setString(3, email);
            updateUser.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static String viewProfile(String email) throws SQLException {
        String sqlViewProfile = "SELECT * FROM user WHERE Email = ?";
        try (Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
             PreparedStatement pViewProfile = conn.prepareStatement(sqlViewProfile)) {
            pViewProfile.setString(1, email);
            ResultSet output = pViewProfile.executeQuery();
            if (output.next()) {
                int id = output.getInt("user_id");
                String name = output.getString("Username");
                String password = output.getString("Password");
                String profileInfo = "User ID: " + id + "\n"
                        + "Name: " + name + "\n"
                        + "Email: " + email + "\n"
                        + "Password: " + password;
                return profileInfo;
            } else {
                return "No user found with email: " + email;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return "Error occurred while fetching the profile.";
        }
    }
    public static void showAvailableSeats(int tripId) throws SQLException {
        String sqlShowSeats = "SELECT seat_id FROM seat WHERE trip_id = ? AND availability = 'available'";
        try (Connection conn = DriverManager.getConnection(url, usernamee, passwordd);
             PreparedStatement pstmt = conn.prepareStatement(sqlShowSeats)) {
            pstmt.setInt(1, tripId);
            ResultSet resultSet = pstmt.executeQuery();
            System.out.println("Available seats for trip ID " + tripId + ":");
            while (resultSet.next()) {
                int seatNumber = resultSet.getInt("seat_id");
                System.out.println("Seat number: " + seatNumber);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static void performBooking(String email, int tripId, int seatId) throws SQLException {
        // Get the current date
        java.sql.Date bookingDate = new java.sql.Date(System.currentTimeMillis());

        try (Connection conn = DriverManager.getConnection(url, usernamee, passwordd)) {
            // Get the user ID based on the email
            int userId = getUserIdByEmail(conn, email);

            // Check if the user and trip exist
            if (userId == -1 || !isTripExists(conn, tripId)) {
                System.out.println("Invalid email or trip ID. Booking failed.");
                return;
            }

            // Check if the seat is available
            if (!isSeatAvailable(conn, tripId, seatId)) {
                System.out.println("Seat is not available for booking. Booking failed.");
                return;
            }

            // Check if the seat is already unavailable
            if (!isSeatAvailableForBooking(conn, seatId)) {
                System.out.println("Seat is already unavailable for booking. Booking failed.");
                return;
            }

            // Generate a new booking ID only if the seat is available for booking
            int bookingId = isSeatAvailableForBooking(conn, seatId) ? generateBookingId(conn) : -1;

            // Insert the booking into the database if a valid booking ID is generated
            if (bookingId != -1) {
                String sqlInsertBooking = "INSERT INTO booking (booking_id, user_id, trip_id, booking_date, seat_id) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement pstmt = conn.prepareStatement(sqlInsertBooking)) {
                    pstmt.setInt(1, bookingId);
                    pstmt.setInt(2, userId);
                    pstmt.setInt(3, tripId);
                    pstmt.setDate(4, new java.sql.Date(bookingDate.getTime()));
                    pstmt.setInt(5, seatId);
                    pstmt.executeUpdate();
                    System.out.println("Booking successful. Booking ID: " + bookingId);
                }
            } else {
                System.out.println("Seat is not available for booking. Booking failed.");
            }

            // Update the seat availability to unavailable
            String sqlUpdateSeat = "UPDATE seat SET availability = 'unavailable' WHERE seat_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlUpdateSeat)) {
                pstmt.setInt(1, seatId);
                pstmt.executeUpdate();
                System.out.println("Seat updated to unavailable.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private static boolean isSeatAvailableForBooking(Connection conn, int seatId) throws SQLException {
        String sql = "SELECT availability FROM seat WHERE seat_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, seatId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String availability = rs.getString("availability");
                    return !"unavailable".equals(availability);
                }
            }
        }
        return false;
    }


    private boolean isUserExists(Connection conn, int userId) throws SQLException {
        String sqlCheckUser = "SELECT user_id FROM user WHERE user_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlCheckUser)) {
            pstmt.setInt(1, userId);
            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next(); // User exists if result set has at least one row
        }
    }

    private static boolean isTripExists(Connection conn, int tripId) throws SQLException {
        String sqlCheckTrip = "SELECT trip_id FROM trip WHERE trip_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlCheckTrip)) {
            pstmt.setInt(1, tripId);
            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next(); // Trip exists if result set has at least one row
        }
    }

    private static boolean isSeatAvailable(Connection conn, int tripId, int seatId) throws SQLException {
        String sqlCheckSeat = "SELECT availability FROM seat WHERE trip_id = ? AND availability = 'Available'";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlCheckSeat)) {
            pstmt.setInt(1, tripId);
            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next(); // Seat is available if result set has at least one row
        }
    }

    private static int generateBookingId(Connection conn) throws SQLException {
        String sqlGetMaxBookingId = "SELECT MAX(booking_id) AS max_id FROM booking";
        try (Statement stmt = conn.createStatement()) {
            ResultSet resultSet = stmt.executeQuery(sqlGetMaxBookingId);
            if (resultSet.next()) {
                int maxId = resultSet.getInt("max_id");
                return maxId + 1; // Generate new booking ID by incrementing the maximum ID
            }
        }
        // If no existing bookings, start with ID 1
        return 1;
    }
    private static int getUserIdByEmail(Connection conn, String email) throws SQLException {
        String sqlGetUserId = "SELECT user_id FROM user WHERE email = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlGetUserId)) {
            pstmt.setString(1, email);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("user_id");
            } else {
                return -1; // Return -1 if no user found with the given email
            }
        }
    }
    public static void cancelBooking(int bookingId) throws SQLException {
        try (Connection conn = DriverManager.getConnection(url, usernamee, passwordd)) {
            // Check if the booking exists
            if (!isBookingExists(conn, bookingId)) {
                System.out.println("Invalid booking ID. Cancellation failed.");
                return;
            }

            // Get the seat ID associated with the booking
            int seatId = -1;
            String sqlSelectSeat = "SELECT b.seat_id FROM booking b JOIN seat s ON b.seat_id = s.seat_id WHERE b.booking_id = ?";
            try (PreparedStatement selectPstmt = conn.prepareStatement(sqlSelectSeat)) {
                selectPstmt.setInt(1, bookingId);
                ResultSet resultSet = selectPstmt.executeQuery();
                if (resultSet.next()) {
                    seatId = resultSet.getInt("seat_id");
                }
            }

            // Delete the booking from the database
            String sqlDeleteBooking = "DELETE FROM booking WHERE booking_id = ?";
            try (PreparedStatement pstmt = conn.prepareStatement(sqlDeleteBooking)) {
                pstmt.setInt(1, bookingId);
                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Booking cancellation successful. Booking ID: " + bookingId);

                    // Update the seat availability to available
                    if (seatId != -1) {
                        String sqlUpdateSeat = "UPDATE seat SET availability = 'Available' WHERE seat_id = ?";
                        try (PreparedStatement updatePstmt = conn.prepareStatement(sqlUpdateSeat)) {
                            updatePstmt.setInt(1, seatId);
                            updatePstmt.executeUpdate();
                            System.out.println("Seat updated to available.");
                        }
                    }
                } else {
                    System.out.println("Failed to cancel booking.");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }



    private static boolean isBookingExists(Connection conn, int bookingId) throws SQLException {
        String sqlCheckBooking = "SELECT booking_id FROM booking WHERE booking_id = ?";
        try (PreparedStatement pstmt = conn.prepareStatement(sqlCheckBooking)) {
            pstmt.setInt(1, bookingId);
            ResultSet resultSet = pstmt.executeQuery();
            return resultSet.next(); // Booking exists if result set has at least one row
        }
    }



}