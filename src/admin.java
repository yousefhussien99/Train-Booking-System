import java.sql.*;

public class admin {
    private int AdminID;
    private String username;
    private String password;
    private String email;

    public admin() {

    }

    public admin(int AdminID, String username, String password, String email) {
        this.AdminID = AdminID;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    // Getter and setter methods for the User class

    public int getAdminID() {
        return AdminID;
    }

    public void setAdminID(int adminID) {
        this.AdminID = AdminID;
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
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db", "root", "Example@2021!");
            // Prepare the SQL statement
            String sql = "INSERT INTO admin (admin_id, username, password, email) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, AdminID);
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

    public static boolean Adminlogin(String email, String password) throws SQLException {
        // Check if the email and password are correct
        String name;
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db", "root", "Example@2021!");
        String sqlcheckLogin = "SELECT * FROM admin WHERE email=? AND password=?";
        PreparedStatement statement = conn.prepareStatement(sqlcheckLogin);
        statement.setString(1, email);
        statement.setString(2, password);
        ResultSet checkLoginResult = statement.executeQuery();

        if (checkLoginResult.next()) {
            name = checkLoginResult.getString("username");
            System.out.println("Login Successful! Welcome, " + name);
            return true;
        } else {
            System.out.println("Invalid email or password. Please try again.");
            return false;
        }
    }
    public static void editProfile(String newName, String newPassword, String email) throws SQLException {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db", "root", "Example@2021!");
            String sqlUpdateUser = "UPDATE Admin SET username = ?, password = ? WHERE email = ?";
            PreparedStatement updateUser = conn.prepareStatement(sqlUpdateUser);
            updateUser.setString(1, newName);
            updateUser.setString(2, newPassword);
            updateUser.setString(3, email);
            updateUser.executeUpdate();
            System.out.println("profile successfully updated");
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }

    public static boolean addStation(int stationID, String stationName, String city, String state) {
        try {
            // Create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db", "root", "Example@2021!");
            // Prepare the SQL statement
            String sql = "INSERT INTO station (station_id, station_name, city, state) " +
                    "VALUES (?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, stationID);
            statement.setString(2, stationName);
            statement.setString(3, city);
            statement.setString(4, state);

            // Execute the query
            int rowsAffected = statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            conn.close();
            System.out.println("station successfully added");
            return rowsAffected > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addTrain(int trainID, String trainName, int departureStation, int arrivalStation, String adminEmail) {
        try {
            // Create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db", "root", "Example@2021!");

            // Retrieve the admin ID based on the email
            String sqlGetAdminID = "SELECT admin_id FROM admin WHERE email = ?";
            PreparedStatement getAdminIDStatement = conn.prepareStatement(sqlGetAdminID);
            getAdminIDStatement.setString(1, adminEmail);
            ResultSet adminIDResult = getAdminIDStatement.executeQuery();

            int adminID = 0;
            if (adminIDResult.next()) {
                adminID = adminIDResult.getInt("admin_id");
            }

            // Prepare the SQL statement
            String sql = "INSERT INTO train (train_id, train_name,departure_station, arrival_station,adminid) " +
                    "VALUES (?, ?, ?, ?, ?)";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, trainID);
            statement.setString(2, trainName);
            statement.setInt(3, departureStation);
            statement.setInt(4, arrivalStation);
            statement.setInt(5, adminID);

            // Execute the query
            int rowsAffected = statement.executeUpdate();

            // Close the statement and connection
            statement.close();
            conn.close();
            System.out.println("train successfully added");

            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean addTrip(int tripID, int trainID, String tripDate, String adminEmail, int numberOfSeats) {
        try {
            // Create a connection to the database
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db", "root", "Example@2021!");

            // Retrieve admin ID based on email
            String getAdminIDSql = "SELECT admin_id FROM admin WHERE email = ?";
            PreparedStatement getAdminIDStatement = conn.prepareStatement(getAdminIDSql);
            getAdminIDStatement.setString(1, adminEmail);
            ResultSet adminIDResult = getAdminIDStatement.executeQuery();

            int adminID;
            if (adminIDResult.next()) {
                adminID = adminIDResult.getInt("admin_id");

                // Prepare the SQL statement with inner join between train and admin tables
                String sql = "INSERT INTO trip (trip_id, train_id, trip_date, adminid) " +
                        "VALUES (?, ?, ?, ?)";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, tripID);
                statement.setInt(2, trainID);
                statement.setString(3, tripDate);
                statement.setInt(4, adminID);

                // Execute the query
                int rowsAffected = statement.executeUpdate();

                // Close the statement
                statement.close();

                if (rowsAffected > 0) {
                    // Add seats to the seat table
                    for (int i = 1; i <= numberOfSeats; i++) {
                        int seatID = tripID * 10 + i; // Generate seat ID based on trip ID
                        String insertSeatSql = "INSERT INTO seat (seat_id, train_id, trip_id, availability) " +
                                "VALUES (?, ?, ?, 'Available')";
                        PreparedStatement insertSeatStatement = conn.prepareStatement(insertSeatSql);
                        insertSeatStatement.setInt(1, seatID);
                        insertSeatStatement.setInt(2, trainID);
                        insertSeatStatement.setInt(3, tripID);
                        insertSeatStatement.executeUpdate();
                        insertSeatStatement.close();
                    }
                    // Close the connection
                    conn.close();
                    System.out.println("Trip successfully added");
                    return true;
                } else {
                    // Trip insertion failed
                    conn.close();
                    return false;
                }
            } else {
                // Admin with the given email not found
                System.out.println("Admin with email " + adminEmail + " not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }




    public static boolean editTrainID(String adminEmail, int tripID, int newTrainID) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db", "root", "Example@2021!");

            // Retrieve admin ID based on email
            String getAdminIDSql = "SELECT admin_id FROM admin WHERE email = ?";
            PreparedStatement getAdminIDStatement = conn.prepareStatement(getAdminIDSql);
            getAdminIDStatement.setString(1, adminEmail);
            ResultSet adminIDResult = getAdminIDStatement.executeQuery();

            int adminID;
            if (adminIDResult.next()) {
                adminID = adminIDResult.getInt("admin_id");

                String sql = "UPDATE trip SET train_id = ?, adminid = ? WHERE trip_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, newTrainID);
                statement.setInt(2, adminID);
                statement.setInt(3, tripID);

                // Execute the query
                int rowsAffected = statement.executeUpdate();

                statement.close();
                conn.close();

                if (rowsAffected > 0) {
                    System.out.println("Train ID updated successfully. Rows affected: " + rowsAffected);
                    return true;
                } else {
                    System.out.println("Train ID update failed. No rows affected.");
                    return false;
                }
            } else {
                // Admin with the given email not found
                System.out.println("Admin with email " + adminEmail + " not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean editTripDate(String adminEmail, int tripID, String newTripDate) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db", "root", "Example@2021!");

            // Retrieve admin ID based on email
            String getAdminIDSql = "SELECT admin_id FROM admin WHERE email = ?";
            PreparedStatement getAdminIDStatement = conn.prepareStatement(getAdminIDSql);
            getAdminIDStatement.setString(1, adminEmail);
            ResultSet adminIDResult = getAdminIDStatement.executeQuery();

            int adminID;
            if (adminIDResult.next()) {
                adminID = adminIDResult.getInt("admin_id");

                String sql = "UPDATE trip SET trip_date = ?, adminid = ? WHERE trip_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, newTripDate);
                statement.setInt(2, adminID);
                statement.setInt(3, tripID);

                // Execute the query
                int rowsAffected = statement.executeUpdate();

                statement.close();
                conn.close();

                if (rowsAffected > 0) {
                    System.out.println("Trip date updated successfully. Rows affected: " + rowsAffected);
                    return true;
                } else {
                    System.out.println("Trip date update failed. No rows affected.");
                    return false;
                }
            } else {
                // Admin with the given email not found
                System.out.println("Admin with email " + adminEmail + " not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static boolean deleteTrip(int tripID) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db", "root", "Example@2021!");

            String sql = "DELETE FROM trip WHERE trip_id = ?";
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.setInt(1, tripID);

            int rowsAffected = statement.executeUpdate();

            statement.close();
            conn.close();
            System.out.println("trip deleted successfully");
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean editTrainName(String adminEmail, int trainId, String newTrainName) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db", "root", "Example@2021!");

            // Retrieve admin ID based on email
            String getAdminIDSql = "SELECT admin_id FROM admin WHERE email = ?";
            PreparedStatement getAdminIDStatement = conn.prepareStatement(getAdminIDSql);
            getAdminIDStatement.setString(1, adminEmail);
            ResultSet adminIDResult = getAdminIDStatement.executeQuery();

            int adminID;
            if (adminIDResult.next()) {
                adminID = adminIDResult.getInt("admin_id");

                String sql = "UPDATE train SET train_name = ?, adminid = ? WHERE train_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setString(1, newTrainName);
                statement.setInt(2, adminID);
                statement.setInt(3, trainId);

                // Execute the query
                int rowsAffected = statement.executeUpdate();

                statement.close();
                conn.close();

                if (rowsAffected > 0) {
                    System.out.println("Train name updated successfully. Rows affected: " + rowsAffected);
                    return true;
                } else {
                    System.out.println("Train name update failed. No rows affected.");
                    return false;
                }
            } else {
                // Admin with the given email not found
                System.out.println("Admin with email " + adminEmail + " not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean editDepartureStation(String adminEmail, int trainId, int newDepartureStation) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db", "root", "Example@2021!");

            // Retrieve admin ID based on email
            String getAdminIDSql = "SELECT admin_id FROM admin WHERE email = ?";
            PreparedStatement getAdminIDStatement = conn.prepareStatement(getAdminIDSql);
            getAdminIDStatement.setString(1, adminEmail);
            ResultSet adminIDResult = getAdminIDStatement.executeQuery();

            int adminID;
            if (adminIDResult.next()) {
                adminID = adminIDResult.getInt("admin_id");

                String sql = "UPDATE train SET departure_station = ?, adminid = ? WHERE train_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, newDepartureStation);
                statement.setInt(2, adminID);
                statement.setInt(3, trainId);

                // Execute the query
                int rowsAffected = statement.executeUpdate();

                statement.close();
                conn.close();

                if (rowsAffected > 0) {
                    System.out.println("Departure station updated successfully. Rows affected: " + rowsAffected);
                    return true;
                } else {
                    System.out.println("Departure station update failed. No rows affected.");
                    return false;
                }
            } else {
                // Admin with the given email not found
                System.out.println("Admin with email " + adminEmail + " not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
    public static boolean editArrivalStation(String adminEmail, int trainId, int newArrivalStation) {
        try {
            Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db", "root", "Example@2021!");

            // Retrieve admin ID based on email
            String getAdminIDSql = "SELECT admin_id FROM admin WHERE email = ?";
            PreparedStatement getAdminIDStatement = conn.prepareStatement(getAdminIDSql);
            getAdminIDStatement.setString(1, adminEmail);
            ResultSet adminIDResult = getAdminIDStatement.executeQuery();

            int adminID;
            if (adminIDResult.next()) {
                adminID = adminIDResult.getInt("admin_id");

                String sql = "UPDATE train SET arrival_station = ?, adminid = ? WHERE train_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, newArrivalStation);
                statement.setInt(2, adminID);
                statement.setInt(3, trainId);

                // Execute the query
                int rowsAffected = statement.executeUpdate();

                statement.close();
                conn.close();

                if (rowsAffected > 0) {
                    System.out.println("arrival station updated successfully. Rows affected: " + rowsAffected);
                    return true;
                } else {
                    System.out.println("arrival station update failed. No rows affected.");
                    return false;
                }
            } else {
                // Admin with the given email not found
                System.out.println("Admin with email " + adminEmail + " not found.");
                return false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }}

        public static boolean deleteTrain(int trainID) {
            try {
                Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3307/db", "root", "Example@2021!");

                String sql = "DELETE FROM train WHERE train_id = ?";
                PreparedStatement statement = conn.prepareStatement(sql);
                statement.setInt(1, trainID);

                int rowsAffected = statement.executeUpdate();

                statement.close();
                conn.close();
                System.out.println("train deleted successfully");
                return rowsAffected > 0;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }



