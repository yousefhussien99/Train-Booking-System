
import javafx.application.Application;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.application.Platform;
import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ButtonType;
public class MyJavaFXApp extends Application {
    private user user; // Reference to the User class
    private JPanel panelmain;
    private JTextField txtname;
    private JButton clickmeButton;
    private JLabel availableSeatsLabel;
    private JTextArea availableSeatsTextArea;
    private static Connection conn;
    private static final String url = "jdbc:mysql://127.0.0.1:3307/db";
    private static final String username = "root";
    private static final String passwordd = "Example@2021!";
    private String message;
    private java.awt.Label seatIdLabel;

    public MyJavaFXApp() throws SQLException {
        conn = DriverManager.getConnection(url, username, passwordd);
    }



    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Train Booking System");
        // Create a grid pane for the layout
        VBox vbox = new VBox();
        vbox.setSpacing(10);
        vbox.setPadding(new Insets(10));;
        vbox.setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                "-fx-background-repeat: no-repeat; " +
                "-fx-background-size: cover;");

        Text welcomeText = new Text("Welcome to the Train Booking System");
        welcomeText.setFont(Font.font("Arial", FontWeight.BOLD, 20));
        welcomeText.setFill(Color.DARKBLUE);
        vbox.getChildren().add(welcomeText);


        // Add components to the grid pane
        Button signUpUserButton = new Button("Sign Up User");
        Button signUpAdminButton = new Button("Sign Up Admin");
        Button loginUserButton = new Button("Login User");
        Button loginAdminButton = new Button("Login Admin");
        Button generateReportButton = new Button("Generate database Report");
        Button generateAggregatedReportButton = new Button("Generate aggregated Report");
        Button exitButton = new Button("Exit"); // Add the exit button
        signUpUserButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
        signUpAdminButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
        loginUserButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
        loginAdminButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");        signUpAdminButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
        generateReportButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
        generateAggregatedReportButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");

        exitButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");

        signUpUserButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(signUpUserButton, Priority.ALWAYS);

        signUpAdminButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(signUpAdminButton, Priority.ALWAYS);

        loginUserButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(loginUserButton, Priority.ALWAYS);

        loginAdminButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(loginAdminButton, Priority.ALWAYS);

        signUpUserButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(signUpUserButton, Priority.ALWAYS);

        generateReportButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(generateReportButton, Priority.ALWAYS);

        generateAggregatedReportButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(generateAggregatedReportButton, Priority.ALWAYS);

        exitButton.setMaxWidth(Double.MAX_VALUE);
        VBox.setVgrow(exitButton, Priority.ALWAYS);

        // Define the action for the Sign Up User button
        signUpUserButton.setOnAction(e -> {
            Stage signUpStage = new Stage();
            signUpStage.setTitle("Sign Up User");

            // Create a VBox for the layout
            VBox vboxx = new VBox();
            vboxx.setSpacing(10);
            vboxx.setPadding(new Insets(10));


            Label userIdLabel = new Label("User ID:");
            TextField userIdTextField = new TextField();
            Label usernameLabel = new Label("Username:");
            TextField usernameTextField = new TextField();
            Label passwordLabel = new Label("Password:");
            TextField passwordTextField = new TextField();
            Label emailLabel = new Label("Email:");
            TextField emailTextField = new TextField();

            // Apply CSS class to the labels
            userIdLabel.getStyleClass().add("label");
            usernameLabel.getStyleClass().add("label");
            passwordLabel.getStyleClass().add("label");
            emailLabel.getStyleClass().add("label");

            // Apply CSS class to the text fields
            userIdTextField.getStyleClass().add("text-field");
            usernameTextField.getStyleClass().add("text-field");
            passwordTextField.getStyleClass().add("text-field");
            emailTextField.getStyleClass().add("text-field");

            Button confirmButton = new Button("Sign Up");
            confirmButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");

            // Apply CSS classes to the button
            confirmButton.getStyleClass().add("confirm-button");
            // Set background color and add welcome text
            vboxx.setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                    "-fx-background-repeat: no-repeat; " +
                    "-fx-background-size: cover;");

            vboxx.getChildren().add(welcomeText);


            confirmButton.setOnAction(confirmEvent -> {
                int userId = Integer.parseInt(userIdTextField.getText());
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                String email = emailTextField.getText();

                user newUser = new user(userId, username, password, email);
                boolean signUpSuccess = newUser.signUp();
                if (signUpSuccess) {
                    System.out.println("User signed up successfully!");
                } else {
                    System.out.println("Failed to sign up user.");
                }
                signUpStage.close();
            });

            // Add labels, text fields, and button to the VBox
            vboxx.getChildren().addAll(userIdLabel, userIdTextField, usernameLabel, usernameTextField, passwordLabel,
                    passwordTextField, emailLabel, emailTextField, confirmButton);

            Scene scene = new Scene(vboxx, 300, 250);

            // Apply CSS styles to the scene
            scene.getStylesheets().add("styles.css");

            primaryStage.setScene(scene);
            primaryStage.show();
        });


        // Define the action for the Sign Up Admin button
        // Define the action for the Sign Up Admin button
        signUpAdminButton.setOnAction(e -> {
            Stage signUpStage = new Stage();
            signUpStage.setTitle("Sign Up Admin");

            VBox vbox2 = new VBox();
            vbox2.setSpacing(10);
            vbox2.setPadding(new Insets(10));

            Label adminIdLabel = new Label("Admin ID:");
            TextField adminIdTextField = new TextField();
            Label usernameLabel = new Label("Username:");
            TextField usernameTextField = new TextField();
            Label passwordLabel = new Label("Password:");
            TextField passwordTextField = new TextField();
            Label emailLabel = new Label("Email:");
            TextField emailTextField = new TextField();

            // Apply CSS class to the labels
            adminIdLabel.getStyleClass().add("label");
            usernameLabel.getStyleClass().add("label");
            passwordLabel.getStyleClass().add("label");
            emailLabel.getStyleClass().add("label");

            // Apply CSS class to the labels
            adminIdLabel.getStyleClass().add("label");
            usernameLabel.getStyleClass().add("label");
            passwordLabel.getStyleClass().add("label");
            emailLabel.getStyleClass().add("label");

            vbox2.setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                    "-fx-background-repeat: no-repeat; " +
                    "-fx-background-size: cover;");

            vbox2.getChildren().add(welcomeText);

            Button confirmButton = new Button("Sign Up");
            confirmButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");

            // Apply CSS classes to the button
            confirmButton.getStyleClass().add("button");

            confirmButton.setOnAction(confirmEvent -> {
                int adminId = Integer.parseInt(adminIdTextField.getText());
                String username = usernameTextField.getText();
                String password = passwordTextField.getText();
                String email = emailTextField.getText();

                admin newAdmin = new admin(adminId, username, password, email);
                boolean signUpSuccess = newAdmin.signUp();
                if (signUpSuccess) {
                    System.out.println("Admin signed up successfully!");
                } else {
                    System.out.println("Failed to sign up admin.");
                }
                signUpStage.close();
            });

            // Add labels, text fields, and button to the VBox
            vbox2.getChildren().addAll(adminIdLabel, adminIdTextField, usernameLabel, usernameTextField, passwordLabel,
                    passwordTextField, emailLabel, emailTextField, confirmButton);

            Scene scene = new Scene(vbox2, 300, 250);

            // Apply CSS styles to the scene
            scene.getStylesheets().add("styles.css");

            primaryStage.setScene(scene);
            primaryStage.show();
        });

        // Define the action for the Login User button
        loginUserButton.setOnAction(e -> {
            Stage loginStage = new Stage();
            loginStage.setTitle("Login User");

            // Create a VBox for the layout
            VBox vbox3 = new VBox();
            vbox3.setSpacing(10);
            vbox3.setPadding(new Insets(10));

            Label emailLabel = new Label("Email:");
            TextField emailTextField = new TextField();
            Label passwordLabel = new Label("Password:");
            TextField passwordTextField = new TextField();

            // Apply CSS class to the labels
            emailLabel.getStyleClass().add("label");
            passwordLabel.getStyleClass().add("label");


            // Apply CSS class to the text fields
            emailTextField.getStyleClass().add("text-field");
            passwordTextField.getStyleClass().add("text-field");


            Button loginButton = new Button("Login");
            loginButton.getStyleClass().add("confirm-button");
            loginButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");


            vbox3.setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                    "-fx-background-repeat: no-repeat; " +
                    "-fx-background-size: cover;");
            vbox3.getChildren().add(welcomeText);

            loginButton.setOnAction(loginEvent -> {
                String email = emailTextField.getText();
                String password = passwordTextField.getText();

                try {
                    boolean loginSuccess = user.userlogin(email, password);
                    if (loginSuccess) {
                        System.out.println("User login successful!");

                        Stage userOptionsStage = new Stage();
                        userOptionsStage.setTitle("User Options");
                        // Create a VBox for the layout
                        VBox vboxx = new VBox();
                        vboxx.setSpacing(10);
                        vboxx.setPadding(new Insets(10));
                        // Set background color and add welcome text
                        vboxx.setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                                "-fx-background-repeat: no-repeat; " +
                                "-fx-background-size: cover;");


                        Button updateDetailsButton = new Button("update profile");
                        Button viewProfileButton = new Button("View Profile");
                        Button showAvailableSeatsButton = new Button("Show Available Seats");
                        Button performBookingButton = new Button("Perform Booking");
                        Button cancelBookingButton = new Button("Cancel Booking");
                        updateDetailsButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                        viewProfileButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                        showAvailableSeatsButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                        performBookingButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                        cancelBookingButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");

                        updateDetailsButton.setMaxWidth(Double.MAX_VALUE);
                        VBox.setVgrow(updateDetailsButton, Priority.ALWAYS);

                        viewProfileButton.setMaxWidth(Double.MAX_VALUE);
                        VBox.setVgrow(viewProfileButton, Priority.ALWAYS);

                        showAvailableSeatsButton.setMaxWidth(Double.MAX_VALUE);
                        VBox.setVgrow(showAvailableSeatsButton, Priority.ALWAYS);

                        performBookingButton.setMaxWidth(Double.MAX_VALUE);
                        VBox.setVgrow(performBookingButton, Priority.ALWAYS);

                        cancelBookingButton.setMaxWidth(Double.MAX_VALUE);
                        VBox.setVgrow(cancelBookingButton, Priority.ALWAYS);




                        updateDetailsButton.setOnAction(updateEvent -> {
                            System.out.println("\n=== Update Profile ===");

                            // Create a dialog to prompt for new name and new password
                            TextInputDialog newNameDialog = new TextInputDialog();
                            newNameDialog.setHeaderText("Update Name");
                            newNameDialog.setContentText("Please enter your new name (or press Enter to skip):");
                            DialogPane newNameDialogPane = newNameDialog.getDialogPane();
                            newNameDialogPane.setStyle("-fx-background-color: #f2f2f2;");
                            newNameDialogPane.getStyleClass().add("custom-dialog-pane");
                            // Apply additional style to the dialog box
                            newNameDialogPane.lookup(".dialog-pane").setStyle("-fx-background-color: #d7d7d8;");
                            Optional<String> newNameResult = newNameDialog.showAndWait();

                            // Check if the user entered a new name
                            if (newNameResult.isPresent()) {
                                String newName = newNameResult.get();

                                // Create a dialog to prompt for new password
                                TextInputDialog newPasswordDialog = new TextInputDialog();
                                newPasswordDialog.setHeaderText("Update Password");
                                newPasswordDialog.setContentText("Please enter your new password (or press Enter to skip):");
                                DialogPane newPasswordDialogPane = newPasswordDialog.getDialogPane();
                                newPasswordDialogPane.setStyle("-fx-background-color: #f2f2f2;");
                                newPasswordDialogPane.getStyleClass().add("custom-dialog-pane");
                                // Apply additional style to the dialog box
                                newPasswordDialogPane.lookup(".dialog-pane").setStyle("-fx-background-color: #d7d7d8;");
                                Optional<String> newPasswordResult = newPasswordDialog.showAndWait();

                                // Check if the user entered a new password
                                if (newPasswordResult.isPresent()) {
                                    String newPassword = newPasswordResult.get();

                                    try {
                                        // Call the editProfile method to update the profile
                                        user.editProfile(newName, newPassword, email);
                                        System.out.println("Profile updated!");

                                        // Display a message box
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Profile Updated");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Profile updated!");
                                        alert.showAndWait();


                                    } catch (SQLException ex) {
                                        System.out.println("Error: " + ex.getMessage());
                                    }
                                }
                            }
                        });


                        viewProfileButton.setOnAction(viewEvent -> {
                            try {
                                String profileInfo = user.viewProfile(email);
                                showProfileDialog("User Profile", profileInfo);
                            } catch (SQLException ex) {
                                System.out.println("Error: " + ex.getMessage());
                            }
                        });
                        viewProfileButton.setOnAction(viewEvent -> {
                            try {
                                String profileInfo = user.viewProfile(email);

                                // Create the profile dialog
                                Alert showProfileDialog = new Alert(Alert.AlertType.INFORMATION);

                                // CSS styles for the showProfileDialog
                                String dialogStyle = "-fx-background-color: #2a5996;";
                                String dialogPaneStyle = "-fx-background-color: #07bb07;";
                                String headerStyle = "-fx-background-color: #2a5996; -fx-text-fill: white; -fx-font-weight: bold;";
                                String contentStyle = "-fx-background-color: #2a5996; -fx-text-fill: white; -fx-font-weight: bold; -fx-font-size: 14px;";

                                // Set the properties and styles of the profile dialog
                                showProfileDialog.setTitle("User Profile");
                                showProfileDialog.getDialogPane().setStyle(dialogStyle);
                                showProfileDialog.getDialogPane().lookup(".dialog-pane > .content.label").setStyle(contentStyle);
                                showProfileDialog.getDialogPane().lookup(".dialog-pane > .header-panel").setStyle(headerStyle);

                                // Set the profile information as the content of the dialog
                                showProfileDialog.setContentText(profileInfo);

                                // Show the profile dialog
                                showProfileDialog.showAndWait();
                            } catch (SQLException ex) {
                                System.out.println("Error: " + ex.getMessage());
                            }
                        });



                        showAvailableSeatsButton.setOnAction(showSeatsEvent -> {
                            TextArea availableSeatsTextArea = new TextArea();
                            availableSeatsTextArea.setEditable(false);

                            TextInputDialog tripIdDialog = new TextInputDialog();
                            tripIdDialog.setHeaderText("Enter the trip ID");
                            tripIdDialog.showAndWait().ifPresent(tripIdString -> {
                                int tripId = Integer.parseInt(tripIdString);

                                try (Connection conn = DriverManager.getConnection(url, username, passwordd);
                                     PreparedStatement pstmt = conn.prepareStatement("SELECT seat_id FROM seat WHERE trip_id = ? AND availability = 'available'")) {
                                    pstmt.setInt(1, tripId);

                                    StringBuilder availableSeats = new StringBuilder();
                                    try (ResultSet resultSet = pstmt.executeQuery()) {
                                        while (resultSet.next()) {
                                            int seatId = resultSet.getInt("seat_id");
                                            availableSeats.append(seatId).append("\n");
                                        }
                                    }

                                    availableSeatsTextArea.setText(availableSeats.toString());

                                    Stage availableSeatsStage = new Stage();
                                    availableSeatsStage.setTitle("Available Seats");

                                    VBox availableSeatsVBox = new VBox(10);
                                    availableSeatsVBox.setPadding(new Insets(10));

                                    // Apply inline CSS styles to the VBox
                                    availableSeatsVBox.setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                                            "-fx-background-repeat: no-repeat; " +
                                            "-fx-background-size: cover;" +
                                                    "-fx-padding: 10px;" +
                                                    "-fx-border-color: #386396;" +
                                                    "-fx-border-width: 2px;" +
                                                    "-fx-border-radius: 5px;"
                                    );

                                    // Apply inline CSS styles to the TextArea
                                    availableSeatsTextArea.setStyle(
                                            "-fx-font-family: 'Arial'; " +
                                                    "-fx-font-size: 16px; " +
                                                    "-fx-text-fill: #333333;" +
                                                    "-fx-font-weight: bold;" +
                                                    "-fx-background-color: #2a5996;" +
                                                    "-fx-border-color: #CCCCCC;" +
                                                    "-fx-border-width: 1px;" +
                                                    "-fx-border-radius: 5px;"
                                    );

                                    availableSeatsVBox.getChildren().add(availableSeatsTextArea);

                                    Scene availableSeatsScene = new Scene(availableSeatsVBox, 400, 300);

                                    availableSeatsStage.setScene(availableSeatsScene);
                                    availableSeatsStage.show();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                }
                            });
                        });





                        performBookingButton.setOnAction(event -> {
                            Stage bookingStage = new Stage();
                            bookingStage.setTitle("Perform Booking");

                            GridPane bookingGridPane = new GridPane();
                            bookingGridPane.setPadding(new Insets(10));
                            bookingGridPane.setVgap(5);
                            bookingGridPane.setHgap(5);

                            Label tripIdLabel = new Label("Trip ID:");
                            TextField tripIdTextField = new TextField();

                            Label seatIdLabel = new Label("Seat ID:");
                            TextField seatIdTextField = new TextField();

                            Button confirmButton = new Button("Perform Booking");
                            confirmButton.setDisable(true); // Initially disable confirmButton

                            tripIdTextField.setOnKeyReleased(ea -> {
                                // Enable confirmButton when both trip ID and seat ID are provided
                                confirmButton.setDisable(tripIdTextField.getText().isEmpty() || seatIdTextField.getText().isEmpty());
                            });

                            seatIdTextField.setOnKeyReleased(ea -> {
                                // Enable confirmButton when both trip ID and seat ID are provided
                                confirmButton.setDisable(tripIdTextField.getText().isEmpty() || seatIdTextField.getText().isEmpty());
                            });

                            confirmButton.setOnAction(confirmEvent -> {
                                int tripId = Integer.parseInt(tripIdTextField.getText());
                                int seatId = Integer.parseInt(seatIdTextField.getText());

                                try {
                                    user.performBooking(email, tripId, seatId);

                                    // Show a success alert message
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Booking Successful");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Booking has been successfully performed!");
                                    alert.showAndWait();
                                } catch (SQLException ex) {
                                    ex.printStackTrace();
                                    // Handle the exception as per your requirements
                                }

                                bookingStage.close();
                            });

                            // Apply inline CSS styles
                            bookingGridPane.setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                                    "-fx-background-repeat: no-repeat; " +
                                    "-fx-background-size: cover;");

                            tripIdLabel.setStyle(
                                    "-fx-font-size: 14px;" +
                                            "-fx-font-weight: bold;"
                            );

                            seatIdLabel.setStyle(
                                    "-fx-font-size: 14px;" +
                                            "-fx-font-weight: bold;"
                            );

                            tripIdTextField.setStyle(
                                    "-fx-font-size: 14px;" +
                                            "-fx-background-color: #FFFFFF;" +
                                            "-fx-border-color: #CCCCCC;" +
                                            "-fx-border-width: 1px;" +
                                            "-fx-border-radius: 5px;"
                            );

                            seatIdTextField.setStyle(
                                    "-fx-font-size: 14px;" +
                                            "-fx-background-color: #FFFFFF;" +
                                            "-fx-border-color: #CCCCCC;" +
                                            "-fx-border-width: 1px;" +
                                            "-fx-border-radius: 5px;"
                            );

                            confirmButton.setStyle(
                                    "-fx-font-size: 14px;" +
                                            "-fx-font-weight: bold;" +
                                            "-fx-background-color: #2a5996;" +
                                            "-fx-text-fill: #FFFFFF;" +
                                            "-fx-padding: 5px 10px;" +
                                            "-fx-border-color: transparent;" +
                                            "-fx-border-width: 2px;" +
                                            "-fx-border-radius: 5px;"
                            );


                            bookingGridPane.add(tripIdLabel, 0, 0);
                            bookingGridPane.add(tripIdTextField, 1, 0);
                            bookingGridPane.add(seatIdLabel, 0, 1);
                            bookingGridPane.add(seatIdTextField, 1, 1);
                            bookingGridPane.add(confirmButton, 1, 2);

                            Scene bookingScene = new Scene(bookingGridPane, 400, 150);
                            bookingStage.setScene(bookingScene);
                            bookingStage.show();
                        });

                        cancelBookingButton.setOnAction(cancelEvent -> {
                            Stage cancelStage = new Stage();
                            cancelStage.setTitle("Cancel Booking");

                            GridPane cancelGridPane = new GridPane();
                            cancelGridPane.setPadding(new Insets(10));
                            cancelGridPane.setVgap(10);
                            cancelGridPane.setHgap(10);

                            Label bookingIdLabel = new Label("Booking ID:");
                            TextField bookingIdTextField = new TextField();
                            Button confirmButton = new Button("Confirm");

                            // Apply CSS styles to the GridPane
                            cancelGridPane.setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                                    "-fx-background-repeat: no-repeat; " +
                                    "-fx-background-size: cover;");
                            // Apply CSS styles to the labels
                            bookingIdLabel.setStyle(
                                    "-fx-font-weight: bold;"
                            );

                            // Apply CSS styles to the text field
                            bookingIdTextField.setStyle(
                                    "-fx-pref-width: 150px;"
                            );

                            // Apply CSS styles to the confirm button
                            confirmButton.setStyle(
                                    "-fx-background-color: #2a5996; " +
                                            "-fx-text-fill: white; " +
                                            "-fx-font-weight: bold;"
                            );

                            confirmButton.setOnAction(confirmEvent -> {
                                int bookingId = Integer.parseInt(bookingIdTextField.getText());

                                try {
                                    user.cancelBooking(bookingId);

                                    // Show a success alert message
                                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                    alert.setTitle("Cancellation Successful");
                                    alert.setHeaderText(null);
                                    alert.setContentText("Booking with ID " + bookingId + " has been successfully cancelled!");
                                    alert.showAndWait();
                                } catch (SQLException ea) {
                                    ea.printStackTrace();
                                    // Handle the exception as per your requirements
                                }

                                cancelStage.close();
                            });

                            cancelGridPane.add(bookingIdLabel, 0, 0);
                            cancelGridPane.add(bookingIdTextField, 1, 0);
                            cancelGridPane.add(confirmButton, 1, 1);

                            Scene cancelScene = new Scene(cancelGridPane, 300, 100);
                            cancelStage.setScene(cancelScene);
                            cancelStage.show();
                        });



                        // Add components to the grid pane
                        vboxx.getChildren().addAll(updateDetailsButton, viewProfileButton, showAvailableSeatsButton, performBookingButton, cancelBookingButton);

                        // Create the scene
                        Scene scene = new Scene(vboxx, 300, 250);

                        // Set the scene to the primary stage
                        primaryStage.setScene(scene);
                        primaryStage.show();
                        loginStage.close();
                    } else {
                        System.out.println("Invalid email or password. Please try again.");
                    }
                } catch (SQLException ex) {
                    System.out.println("An error occurred during user login: " + ex.getMessage());
                }
            });

            // Add labels, text fields, and button to the VBox
            vbox3.getChildren().addAll(  emailLabel, emailTextField, passwordLabel, passwordTextField,loginButton);

            Scene scene = new Scene(vbox3, 300, 250);

            // Apply CSS styles to the scene
            scene.getStylesheets().add("styles.css");

            primaryStage.setScene(scene);
            primaryStage.show();
        });



        // Define the action for the Login Admin button
        loginAdminButton.setOnAction(e -> {
            Stage loginStage = new Stage();
            loginStage.setTitle("Login Admin");

            // Create a VBox for the layout
            VBox vbox4 = new VBox();
            vbox4.setSpacing(10);
            vbox4.setPadding(new Insets(10));

            Label emailLabel = new Label("email:");
            TextField emailTextField = new TextField();
            Label passwordLabel = new Label("Password:");
            TextField passwordTextField = new TextField();

            // Apply CSS class to the labels
            passwordLabel.getStyleClass().add("label");
            emailLabel.getStyleClass().add("label");

            // Apply CSS class to the text fields
            passwordTextField.getStyleClass().add("text-field");
            emailTextField.getStyleClass().add("text-field");


            Button adminLoginButton = new Button("Login");
            adminLoginButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");

            // Apply CSS classes to the button
            adminLoginButton.getStyleClass().add("button");


            vbox4.setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                    "-fx-background-repeat: no-repeat; " +
                    "-fx-background-size: cover;");
            vbox4.getChildren().add(welcomeText);

            adminLoginButton.setOnAction(adminLoginEvent -> {
                String email = emailTextField.getText();
                String password = passwordTextField.getText();

                try {
                    boolean loginSuccess = admin.Adminlogin(email, password);
                    if (loginSuccess) {
                        System.out.println("Admin login successful!");
                        Stage optionsStage = new Stage();
                        optionsStage.setTitle("Admin Options");

                        VBox vboxx = new VBox();
                        vboxx.setSpacing(10);
                        vboxx.setPadding(new Insets(10));
                        // Set background color and add welcome text
                        vboxx.setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                                "-fx-background-repeat: no-repeat; " +
                                "-fx-background-size: cover;");



                        Button addStationButton = new Button("Add a Station");
                        Button addTrainButton = new Button("Add a Train");
                        Button updateTrainButton = new Button("Update a Train's Details");
                        Button addTripButton = new Button("Add a Trip");
                        Button updateTripButton = new Button("Update a Trip's Details");
                        Button updateProfileButton = new Button("Update Profile");
                        addStationButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                        addTrainButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                        updateTrainButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                        addTripButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                        updateTripButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                        updateProfileButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                        addStationButton.setMaxWidth(Double.MAX_VALUE);
                        VBox.setVgrow(addStationButton, Priority.ALWAYS);

                        addTrainButton.setMaxWidth(Double.MAX_VALUE);
                        VBox.setVgrow(addTrainButton, Priority.ALWAYS);

                        updateTrainButton.setMaxWidth(Double.MAX_VALUE);
                        VBox.setVgrow(updateTrainButton, Priority.ALWAYS);

                        addTripButton.setMaxWidth(Double.MAX_VALUE);
                        VBox.setVgrow(addTripButton, Priority.ALWAYS);

                        updateTripButton.setMaxWidth(Double.MAX_VALUE);
                        VBox.setVgrow(updateTripButton, Priority.ALWAYS);

                        updateProfileButton.setMaxWidth(Double.MAX_VALUE);
                        VBox.setVgrow(updateProfileButton, Priority.ALWAYS);
                        addStationButton.setOnAction(stationEvent -> {
                            Stage addStationStage = new Stage();
                            addStationStage.setTitle("Add a Station");

                            GridPane addStationGridPane = new GridPane();
                            addStationGridPane.setPadding(new Insets(10));
                            addStationGridPane.setVgap(10);
                            addStationGridPane.setHgap(10);

                            Label stationIdLabel = new Label("Station ID:");
                            TextField stationIdTextField = new TextField();
                            Label stationNameLabel = new Label("Station Name:");
                            TextField stationNameTextField = new TextField();
                            Label cityLabel = new Label("City:");
                            TextField cityTextField = new TextField();
                            Label stateLabel = new Label("State:");
                            TextField stateTextField = new TextField();

                            Button addStationConfirmButton = new Button("Add Station");
                            addStationConfirmButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                            addStationConfirmButton.setOnAction(confirmEvent -> {
                                int stationId;
                                String stationName, city, state;

                                try {
                                    stationId = Integer.parseInt(stationIdTextField.getText());
                                    stationName = stationNameTextField.getText();
                                    city = cityTextField.getText();
                                    state = stateTextField.getText();

                                    // Call the admin's addStation method with the provided inputs
                                    admin.addStation(stationId, stationName, city, state);

                                    System.out.println("Station added successfully!");

                                    addStationStage.close();
                                } catch (NumberFormatException ex) {
                                    System.out.println("Invalid station ID. Please enter a valid integer.");
                                }
                            });

                            addStationGridPane.add(stationIdLabel, 0, 0);
                            addStationGridPane.add(stationIdTextField, 1, 0);
                            addStationGridPane.add(stationNameLabel, 0, 1);
                            addStationGridPane.add(stationNameTextField, 1, 1);
                            addStationGridPane.add(cityLabel, 0, 2);
                            addStationGridPane.add(cityTextField, 1, 2);
                            addStationGridPane.add(stateLabel, 0, 3);
                            addStationGridPane.add(stateTextField, 1, 3);
                            addStationGridPane.add(addStationConfirmButton, 1, 4);

                            Scene addStationScene = new Scene(addStationGridPane);
                            addStationScene.getRoot().setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                                    "-fx-background-repeat: no-repeat; " +
                                    "-fx-background-size: cover;");

                            addStationScene.getStylesheets().add("styles.css"); // Replace with your own stylesheet path
                            addStationStage.setScene(addStationScene);
                            addStationStage.setResizable(false);
                            addStationStage.show();

                        });


                        addTrainButton.setOnAction(trainEvent -> {
                            Stage addTrainStage = new Stage();
                            addTrainStage.setTitle("Add a Train");

                            GridPane addTrainGridPane = new GridPane();
                            addTrainGridPane.setPadding(new Insets(10));
                            addTrainGridPane.setVgap(10);
                            addTrainGridPane.setHgap(10);

                            Label trainIdLabel = new Label("Train ID:");
                            TextField trainIdTextField = new TextField();
                            Label trainNameLabel = new Label("Train Name:");
                            TextField trainNameTextField = new TextField();
                            Label departureStationLabel = new Label("Departure Station ID:");
                            TextField departureStationTextField = new TextField();
                            Label arrivalStationLabel = new Label("Arrival Station ID:");
                            TextField arrivalStationTextField = new TextField();

                            Button addTrainConfirmButton = new Button("Add Train");
                            addTrainConfirmButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                            addTrainConfirmButton.setOnAction(confirmEvent -> {
                                int trainId, departureStation, arrivalStation;
                                String trainName;

                                try {
                                    trainId = Integer.parseInt(trainIdTextField.getText());
                                    trainName = trainNameTextField.getText();
                                    departureStation = Integer.parseInt(departureStationTextField.getText());
                                    arrivalStation = Integer.parseInt(arrivalStationTextField.getText());

                                    admin.addTrain(trainId, trainName, departureStation, arrivalStation, email);

                                    System.out.println("Train added successfully!");

                                    addTrainStage.close();
                                } catch (NumberFormatException exe) {
                                    System.out.println("Invalid input. Please enter valid integers for Train ID, Departure Station ID, and Arrival Station ID.");
                                }
                            });

                            addTrainGridPane.add(trainIdLabel, 0, 0);
                            addTrainGridPane.add(trainIdTextField, 1, 0);
                            addTrainGridPane.add(trainNameLabel, 0, 1);
                            addTrainGridPane.add(trainNameTextField, 1, 1);
                            addTrainGridPane.add(departureStationLabel, 0, 2);
                            addTrainGridPane.add(departureStationTextField, 1, 2);
                            addTrainGridPane.add(arrivalStationLabel, 0, 3);
                            addTrainGridPane.add(arrivalStationTextField, 1, 3);
                            addTrainGridPane.add(addTrainConfirmButton, 1, 4);

                            Scene addTrainScene = new Scene(addTrainGridPane);
                            addTrainScene.getRoot().setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                                    "-fx-background-repeat: no-repeat; " +
                                    "-fx-background-size: cover;");
                            // Set the background color to grey
                            addTrainStage.setScene(addTrainScene);
                            addTrainStage.setResizable(false);
                            addTrainStage.show();
                        });

                        updateTrainButton.setOnAction(updateTrainEvent -> {
                            TextInputDialog trainIdDialog = new TextInputDialog();
                            trainIdDialog.setHeaderText("Enter the ID of the train you want to update");
                            DialogPane dialogPane1 = trainIdDialog.getDialogPane();
                            dialogPane1.setStyle("-fx-background-color: D7D7D8FF;");
                            dialogPane1.getStyleClass().add("custom-dialog-pane");
                            trainIdDialog.showAndWait().ifPresent(trainIdString -> {
                                int trainId;

                                try {
                                    trainId = Integer.parseInt(trainIdString);

                                    optionsStage.setTitle("Update Train Options");

                                    VBox optionsVBox = new VBox(10);
                                    optionsVBox.setPadding(new Insets(10));

                                    Button updateTrainNameButton = new Button("Update Train Name");
                                    Button updateDepartureStationButton = new Button("Update Departure Station");
                                    Button updateArrivalStationButton = new Button("Update Arrival Station");
                                    Button deleteTrainButton = new Button("Delete Train");
                                    updateTrainNameButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                                    updateDepartureStationButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                                    updateArrivalStationButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                                    deleteTrainButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                                    updateTrainNameButton.setMaxWidth(Double.MAX_VALUE);
                                    VBox.setVgrow(updateTrainNameButton, Priority.ALWAYS);

                                    updateDepartureStationButton.setMaxWidth(Double.MAX_VALUE);
                                    VBox.setVgrow(updateDepartureStationButton, Priority.ALWAYS);

                                    updateArrivalStationButton.setMaxWidth(Double.MAX_VALUE);
                                    VBox.setVgrow(updateArrivalStationButton, Priority.ALWAYS);

                                    deleteTrainButton.setMaxWidth(Double.MAX_VALUE);
                                    VBox.setVgrow(deleteTrainButton, Priority.ALWAYS);


                                    updateTrainNameButton.setOnAction(updateTrainNameEvent -> {
                                        TextInputDialog trainNameDialog = new TextInputDialog();
                                        trainNameDialog.setHeaderText("Enter the new train name");

                                        // Set the style for the dialog pane
                                        DialogPane dialogPane = trainNameDialog.getDialogPane();
                                        dialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                        dialogPane.getStyleClass().add("custom-dialog-pane");

                                        trainNameDialog.showAndWait().ifPresent(newTrainName -> {
                                            admin.editTrainName(email, trainId, newTrainName);

                                            // Display a success message
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("Train Name Updated");
                                            alert.setHeaderText(null);
                                            alert.setContentText("Train name updated successfully!");

                                            // Set the style for the dialog pane
                                            DialogPane alertDialogPane = alert.getDialogPane();
                                            alertDialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                            alertDialogPane.getStyleClass().add("custom-dialog-pane");

                                            alert.showAndWait();
                                        });
                                    });


                                    updateDepartureStationButton.setOnAction(updateDepartureStationEvent -> {
                                        TextInputDialog departureStationDialog = new TextInputDialog();
                                        departureStationDialog.setHeaderText("Enter the new departure station ID");

                                        // Set the style for the dialog pane
                                        DialogPane dialogPane = departureStationDialog.getDialogPane();
                                        dialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                        dialogPane.getStyleClass().add("custom-dialog-pane");

                                        departureStationDialog.showAndWait().ifPresent(departureStationIdString -> {
                                            int departureStationId;

                                            try {
                                                departureStationId = Integer.parseInt(departureStationIdString);
                                                admin.editDepartureStation(email, trainId, departureStationId);

                                                // Display a success message
                                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                alert.setTitle("Departure Station Updated");
                                                alert.setHeaderText(null);
                                                alert.setContentText("Departure station updated successfully!");

                                                // Set the style for the dialog pane
                                                DialogPane alertDialogPane = alert.getDialogPane();
                                                alertDialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                                alertDialogPane.getStyleClass().add("custom-dialog-pane");

                                                alert.showAndWait();
                                            } catch (NumberFormatException exe) {
                                                // Display an error message for invalid input
                                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                                alert.setTitle("Invalid Input");
                                                alert.setHeaderText(null);
                                                alert.setContentText("Invalid input. Please enter a valid integer for the departure station ID.");

                                                // Set the style for the dialog pane
                                                DialogPane alertDialogPane = alert.getDialogPane();
                                                alertDialogPane.setStyle("-fx-background-color:D7D7D8FF;");
                                                alertDialogPane.getStyleClass().add("custom-dialog-pane");

                                                alert.showAndWait();
                                            }
                                        });
                                    });


                                    updateArrivalStationButton.setOnAction(updateArrivalStationEvent -> {
                                        TextInputDialog arrivalStationDialog = new TextInputDialog();
                                        arrivalStationDialog.setHeaderText("Enter the new arrival station ID");

                                        // Set the style for the dialog pane
                                        DialogPane dialogPane = arrivalStationDialog.getDialogPane();
                                        dialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                        dialogPane.getStyleClass().add("custom-dialog-pane");

                                        arrivalStationDialog.showAndWait().ifPresent(arrivalStationIdString -> {
                                            int arrivalStationId;

                                            try {
                                                arrivalStationId = Integer.parseInt(arrivalStationIdString);
                                                admin.editArrivalStation(email, trainId, arrivalStationId);

                                                // Display a success message
                                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                alert.setTitle("Arrival Station Updated");
                                                alert.setHeaderText(null);
                                                alert.setContentText("Arrival station updated successfully!");

                                                // Set the style for the dialog pane
                                                DialogPane alertDialogPane = alert.getDialogPane();
                                                alertDialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                                alertDialogPane.getStyleClass().add("custom-dialog-pane");

                                                alert.showAndWait();
                                            } catch (NumberFormatException eexe) {
                                                // Display an error message for invalid input
                                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                                alert.setTitle("Invalid Input");
                                                alert.setHeaderText(null);
                                                alert.setContentText("Invalid input. Please enter a valid integer for the arrival station ID.");

                                                // Set the style for the dialog pane
                                                DialogPane alertDialogPane = alert.getDialogPane();
                                                alertDialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                                alertDialogPane.getStyleClass().add("custom-dialog-pane");

                                                alert.showAndWait();
                                            }
                                        });
                                    });


                                    deleteTrainButton.setOnAction(deleteTrainEvent -> {
                                        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                                        confirmationAlert.setTitle("Confirmation");
                                        confirmationAlert.setHeaderText("Delete Train");
                                        confirmationAlert.setContentText("Are you sure you want to delete this train?");

                                        // Set the style for the dialog pane
                                        DialogPane dialogPane = confirmationAlert.getDialogPane();
                                        dialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                        dialogPane.getStyleClass().add("custom-dialog-pane");

                                        Optional<ButtonType> result = confirmationAlert.showAndWait();
                                        if (result.isPresent() && result.get() == ButtonType.OK) {
                                            admin.deleteTrain(trainId);

                                            // Display a success message
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("Train Deleted");
                                            alert.setHeaderText(null);
                                            alert.setContentText("Train deleted successfully!");

                                            // Set the style for the dialog pane
                                            DialogPane alertDialogPane = alert.getDialogPane();
                                            alertDialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                            alertDialogPane.getStyleClass().add("custom-dialog-pane");

                                            alert.showAndWait();

                                            optionsStage.close();
                                        }
                                    });


                                    optionsVBox.getChildren().addAll(updateTrainNameButton, updateDepartureStationButton, updateArrivalStationButton, deleteTrainButton);

                                    Scene optionsScene = new Scene(optionsVBox);
                                    optionsScene.getRoot().setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                                            "-fx-background-repeat: no-repeat; " +
                                            "-fx-background-size: cover;"); // Set the background color to grey
                                    optionsStage.setScene(optionsScene);
                                    optionsStage.setResizable(true);
                                    optionsStage.show();
                                } catch (NumberFormatException exee) {
                                    System.out.println("Invalid input. Please enter a valid integer for the train ID.");
                                }
                            });
                        });

                        addTripButton.setOnAction(addTripEvent -> {
                            Stage addTripStage = new Stage();
                            addTripStage.setTitle("Add a Trip");

                            GridPane addTripGridPane = new GridPane();
                            addTripGridPane.setPadding(new Insets(10));
                            addTripGridPane.setVgap(5);
                            addTripGridPane.setHgap(5);

                            Label tripIdLabel = new Label("Trip ID:");
                            TextField tripIdTextField = new TextField();
                            tripIdTextField.setPrefWidth(200);

                            Label trainIdLabel = new Label("Train ID:");
                            TextField trainIdTextField = new TextField();
                            trainIdTextField.setPrefWidth(200);

                            Label tripDateLabel = new Label("Trip Date:");
                            TextField tripDateTextField = new TextField();
                            tripDateTextField.setPrefWidth(200);

                            Label numSeatsLabel = new Label("Number of Seats:");
                            TextField numSeatsTextField = new TextField();
                            numSeatsTextField.setPrefWidth(200);

                            Button addTripDetailsButton = new Button("Add Trip");
                            addTripDetailsButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");

                            addTripDetailsButton.setOnAction(addTripDetailsEvent -> {
                                int tripId = Integer.parseInt(tripIdTextField.getText());
                                int trainId = Integer.parseInt(trainIdTextField.getText());
                                String tripDate = tripDateTextField.getText();
                                int numSeats = Integer.parseInt(numSeatsTextField.getText());

                                admin.addTrip(tripId, trainId, tripDate, email, numSeats);
                                System.out.println("Trip added successfully!");
                            });

                            addTripGridPane.add(tripIdLabel, 0, 0);
                            addTripGridPane.add(tripIdTextField, 1, 0);
                            addTripGridPane.add(trainIdLabel, 0, 1);
                            addTripGridPane.add(trainIdTextField, 1, 1);
                            addTripGridPane.add(tripDateLabel, 0, 2);
                            addTripGridPane.add(tripDateTextField, 1, 2);
                            addTripGridPane.add(numSeatsLabel, 0, 3);
                            addTripGridPane.add(numSeatsTextField, 1, 3);
                            addTripGridPane.add(addTripDetailsButton, 0, 4);
                            Scene addTripScene = new Scene(addTripGridPane);
                            addTripScene.getRoot().setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                                    "-fx-background-repeat: no-repeat; " +
                                    "-fx-background-size: cover;");

                            addTripStage.setScene(addTripScene);
                            addTripStage.show();
                        });



                        updateTripButton.setOnAction(updateTripEvent -> {
                            TextInputDialog tripIdDialog = new TextInputDialog();
                            tripIdDialog.setHeaderText("Enter the ID of the trip you want to update");
                            DialogPane dialogPane3 = tripIdDialog.getDialogPane();
                            dialogPane3.setStyle("-fx-background-color: D7D7D8FF;");
                            dialogPane3.getStyleClass().add("custom-dialog-pane");
                            tripIdDialog.showAndWait().ifPresent(tripIdString -> {
                                int tripId;

                                try {
                                    tripId = Integer.parseInt(tripIdString);

                                    optionsStage.setTitle("Update Trip Options");

                                    VBox optionsVBox = new VBox(10);
                                    optionsVBox.setPadding(new Insets(10));

                                    Button updateTrainIdButton = new Button("Update Train ID");
                                    Button updateTripDateButton = new Button("Update Trip Date");
                                    Button deleteTripButton = new Button("Delete Trip");
                                    updateTrainIdButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                                    updateTripDateButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                                    deleteTripButton.setStyle("-fx-background-color: #2a5996; -fx-text-fill: white;");
                                    updateTrainIdButton.setMaxWidth(Double.MAX_VALUE);
                                    VBox.setVgrow(updateTrainIdButton, Priority.ALWAYS);

                                    updateTripDateButton.setMaxWidth(Double.MAX_VALUE);
                                    VBox.setVgrow(updateTripDateButton, Priority.ALWAYS);

                                    deleteTripButton.setMaxWidth(Double.MAX_VALUE);
                                    VBox.setVgrow(deleteTripButton, Priority.ALWAYS);

                                    updateTrainIdButton.setOnAction(updateTrainIdEvent -> {
                                        TextInputDialog trainIdDialog = new TextInputDialog();
                                        trainIdDialog.setHeaderText("Enter the new train ID");

                                        // Set the style for the dialog pane
                                        DialogPane dialogPane = trainIdDialog.getDialogPane();
                                        dialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                        dialogPane.getStyleClass().add("custom-dialog-pane");

                                        trainIdDialog.showAndWait().ifPresent(newTrainIdString -> {
                                            int newTrainId;

                                            try {
                                                newTrainId = Integer.parseInt(newTrainIdString);
                                                admin.editTrainID(email, tripId, newTrainId);

                                                // Display a success message
                                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                                alert.setTitle("Train ID Updated");
                                                alert.setHeaderText(null);
                                                alert.setContentText("Train ID updated successfully!");

                                                // Set the style for the dialog pane
                                                DialogPane alertDialogPane = alert.getDialogPane();
                                                alertDialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                                alertDialogPane.getStyleClass().add("custom-dialog-pane");

                                                alert.showAndWait();
                                            } catch (NumberFormatException exee) {
                                                // Display an error message
                                                Alert alert = new Alert(Alert.AlertType.ERROR);
                                                alert.setTitle("Error");
                                                alert.setHeaderText(null);
                                                alert.setContentText("Invalid input. Please enter a valid integer for the new train ID.");

                                                // Set the style for the dialog pane
                                                DialogPane errorDialogPane = alert.getDialogPane();
                                                errorDialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                                errorDialogPane.getStyleClass().add("custom-dialog-pane");

                                                alert.showAndWait();
                                            }
                                        });
                                    });


                                    updateTripDateButton.setOnAction(updateTripDateEvent -> {
                                        TextInputDialog tripDateDialog = new TextInputDialog();
                                        tripDateDialog.setHeaderText("Enter the new trip date");

                                        // Set the style for the dialog pane
                                        DialogPane dialogPane = tripDateDialog.getDialogPane();
                                        dialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                        dialogPane.getStyleClass().add("custom-dialog-pane");

                                        tripDateDialog.showAndWait().ifPresent(newTripDate -> {
                                            admin.editTripDate(email, tripId, newTripDate);

                                            // Display a success message
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("Trip Date Updated");
                                            alert.setHeaderText(null);
                                            alert.setContentText("Trip date updated successfully!");

                                            // Set the style for the dialog pane
                                            DialogPane alertDialogPane = alert.getDialogPane();
                                            alertDialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                            alertDialogPane.getStyleClass().add("custom-dialog-pane");

                                            alert.showAndWait();


                                        });
                                    });



                                    deleteTripButton.setOnAction(deleteTripEvent -> {
                                        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
                                        confirmationAlert.setTitle("Confirmation");
                                        confirmationAlert.setHeaderText("Delete Trip");
                                        confirmationAlert.setContentText("Are you sure you want to delete this trip?");

                                        // Set the style for the dialog pane
                                        DialogPane dialogPane = confirmationAlert.getDialogPane();
                                        dialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                        dialogPane.getStyleClass().add("custom-dialog-pane");

                                        Optional<ButtonType> result = confirmationAlert.showAndWait();
                                        if (result.isPresent() && result.get() == ButtonType.OK) {
                                            admin.deleteTrip(tripId);

                                            // Display a success message
                                            Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                            alert.setTitle("Trip Deleted");
                                            alert.setHeaderText(null);
                                            alert.setContentText("Trip deleted successfully!");

                                            // Set the style for the dialog pane
                                            DialogPane alertDialogPane = alert.getDialogPane();
                                            alertDialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                            alertDialogPane.getStyleClass().add("custom-dialog-pane");

                                            alert.showAndWait();
                                        }

                                        optionsStage.close();
                                    });


                                    optionsVBox.getChildren().addAll(updateTrainIdButton, updateTripDateButton, deleteTripButton);

                                    Scene optionsScene = new Scene(optionsVBox);
                                    optionsScene.getRoot().setStyle("-fx-background-image: url('file:///C:/train.jpeg'); " +
                                            "-fx-background-repeat: no-repeat; " +
                                            "-fx-background-size: cover;");
                                    // Set the background color to grey
                                    optionsStage.setScene(optionsScene);
                                    optionsStage.setResizable(true);
                                    optionsStage.show();
                                } catch (NumberFormatException exee) {
                                    System.out.println("Invalid input. Please enter a valid integer for the trip ID.");
                                }
                            });
                        });


                        Stage updateProfileStage = new Stage();


                        updateProfileButton.setOnAction(updateProfileEvent -> {
                            System.out.println("\n=== Update Profile ===");

                            // Create a dialog to prompt for new name and new password
                            TextInputDialog newNameDialog = new TextInputDialog();
                            newNameDialog.setHeaderText("Update Name");
                            newNameDialog.setContentText("Please enter your new name (or press Enter to skip):");
                            DialogPane newNameDialogPane = newNameDialog.getDialogPane();
                            newNameDialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                            newNameDialogPane.getStyleClass().add("custom-dialog-pane");
                            // Apply additional style to the dialog box
                            newNameDialogPane.lookup(".dialog-pane").setStyle("-fx-background-color:D7D7D8FF;");
                            Optional<String> newNameResult = newNameDialog.showAndWait();

                            // Check if the user entered a new name
                            if (newNameResult.isPresent()) {
                                String newName = newNameResult.get();

                                // Create a dialog to prompt for new password
                                TextInputDialog newPasswordDialog = new TextInputDialog();
                                newPasswordDialog.setHeaderText("Update Password");
                                newPasswordDialog.setContentText("Please enter your new password (or press Enter to skip):");
                                DialogPane newPasswordDialogPane = newPasswordDialog.getDialogPane();
                                newPasswordDialogPane.setStyle("-fx-background-color: D7D7D8FF;");
                                newPasswordDialogPane.getStyleClass().add("custom-dialog-pane");
                                // Apply additional style to the dialog box
                                newPasswordDialogPane.lookup(".dialog-pane").setStyle("-fx-background-color: D7D7D8FF;");
                                Optional<String> newPasswordResult = newPasswordDialog.showAndWait();

                                // Check if the user entered a new password
                                if (newPasswordResult.isPresent()) {
                                    String newPassword = newPasswordResult.get();

                                    try {
                                        // Call the editProfile method to update the profile
                                        admin.editProfile(newName, newPassword, email);
                                        System.out.println("Profile updated successfully!");

                                        // Close the update profile screen
                                        updateProfileStage.close();

                                        // Display a message box
                                        Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                        alert.setTitle("Profile Updated");
                                        alert.setHeaderText(null);
                                        alert.setContentText("Profile updated!");
                                        alert.showAndWait();
                                    } catch (SQLException ex) {
                                        System.out.println("Error: " + ex.getMessage());
                                    }
                                }
                            }
                        });

                        exitButton.setOnAction(exitevent -> {
                            optionsStage.close();
                        });

                        vboxx.getChildren().addAll(addStationButton, addTrainButton, updateTrainButton, addTripButton, updateTripButton,updateProfileButton);
                        Scene scene = new Scene(vboxx, 300, 250);
                        primaryStage.setScene(scene);
                        primaryStage.show();

                        loginStage.close();
                    } else {
                        System.out.println("Invalid username or password. Please try again.");
                    }
                } catch (SQLException ex) {
                    System.out.println("An error occurred during admin login: " + ex.getMessage());
                }
            });




            // Add labels, text fields, and button to the VBox
            vbox4.getChildren().addAll(  emailLabel, emailTextField,  passwordLabel, passwordTextField,adminLoginButton);

            Scene scene = new Scene(vbox4, 300, 250);

            // Apply CSS styles to the scene
            scene.getStylesheets().add("styles.css");

            primaryStage.setScene(scene);
            primaryStage.show();

        });
        generateReportButton.setOnAction(event -> {
                    TabPane tabPane = new TabPane();

                    TableView<Station> tableView = new TableView<>();
                    tableView.setEditable(false);

                    TableColumn<Station, Integer> stationIdColumn = new TableColumn<>("Station ID");
                    stationIdColumn.setCellValueFactory(new PropertyValueFactory<>("stationId"));

                    TableColumn<Station, String> stationNameColumn = new TableColumn<>("Station Name");
                    stationNameColumn.setCellValueFactory(new PropertyValueFactory<>("stationName"));

                    TableColumn<Station, String> cityColumn = new TableColumn<>("City");
                    cityColumn.setCellValueFactory(new PropertyValueFactory<>("city"));

                    TableColumn<Station, String> stateColumn = new TableColumn<>("State");
                    stateColumn.setCellValueFactory(new PropertyValueFactory<>("state"));

                    tableView.getColumns().addAll(stationIdColumn, stationNameColumn, cityColumn, stateColumn);

                    try (Connection conn = DriverManager.getConnection(url, username, passwordd)) {
                        String sqlQuery = "SELECT * FROM station";
                        try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
                            ResultSet resultSet = pstmt.executeQuery();

                            while (resultSet.next()) {
                                int stationId = resultSet.getInt("station_id");
                                String stationName = resultSet.getString("station_name");
                                String city = resultSet.getString("city");
                                String state = resultSet.getString("state");

                                Station station = new Station(stationId, stationName, city, state);
                                tableView.getItems().add(station);
                            }

                            resultSet.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    Tab stationTab = new Tab("Station");
                    stationTab.setContent(tableView);
                    TableView<user> tableView2 = new TableView<>();
                    tableView2.setEditable(false);

                    TableColumn<user, Integer> userIdColumn = new TableColumn<>("User ID");
                    userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

                    TableColumn<user, String> usernameColumn = new TableColumn<>("Username");
                    usernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

                    TableColumn<user, String> passwordColumn = new TableColumn<>("Password");
                    passwordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

                    TableColumn<user, String> emailColumn = new TableColumn<>("Email");
                    emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

                    tableView2.getColumns().addAll(userIdColumn, usernameColumn, passwordColumn, emailColumn);

                    try (Connection conn = DriverManager.getConnection(url, username, passwordd)) {
                        String sqlQuery = "SELECT * FROM User";
                        try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
                            ResultSet resultSet = pstmt.executeQuery();

                            while (resultSet.next()) {
                                int userId = resultSet.getInt("user_id");
                                String username = resultSet.getString("Username");
                                String password = resultSet.getString("Password");
                                String email = resultSet.getString("Email");

                                user user = new user(userId, username, password, email);
                                tableView2.getItems().add(user);
                            }

                            resultSet.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        // Handle any error occurred while generating the report
                    }
                    Tab userTab = new Tab("User");
                    userTab.setContent(tableView2);
                    TableView<admin> tableView3 = new TableView<>();
                    tableView3.setEditable(false);

                    TableColumn<admin, Integer> adminIdColumn = new TableColumn<>("Admin ID");
                    adminIdColumn.setCellValueFactory(new PropertyValueFactory<>("AdminID"));

                    TableColumn<admin, String> adminUsernameColumn = new TableColumn<>("Username");
                    adminUsernameColumn.setCellValueFactory(new PropertyValueFactory<>("username"));

                    TableColumn<admin, String> adminEmailColumn = new TableColumn<>("Email");
                    adminEmailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

                    TableColumn<admin, String> adminPasswordColumn = new TableColumn<>("Password");
                    adminPasswordColumn.setCellValueFactory(new PropertyValueFactory<>("password"));

                    tableView3.getColumns().addAll(adminIdColumn, adminUsernameColumn, adminEmailColumn, adminPasswordColumn);

                    try (Connection conn = DriverManager.getConnection(url, username, passwordd)) {
                        String sqlQuery = "SELECT * FROM admin";
                        try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
                            ResultSet resultSet = pstmt.executeQuery();

                            while (resultSet.next()) {
                                int adminId = resultSet.getInt("admin_id");
                                String adminUsername = resultSet.getString("username");
                                String adminEmail = resultSet.getString("email");
                                String adminPassword = resultSet.getString("password");

                                admin admin = new admin(adminId, adminUsername, adminEmail, adminPassword);
                                tableView3.getItems().add(admin);
                            }

                            resultSet.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                        // Handle any error occurred while generating the report
                    }

                    Tab adminTab = new Tab("Admin");
                    adminTab.setContent(tableView3);
                    TableView<Train> tableView4 = new TableView<>();
                    tableView4.setEditable(false);

                    TableColumn<Train, Integer> trainIdColumn = new TableColumn<>("Train ID");
                    trainIdColumn.setCellValueFactory(new PropertyValueFactory<>("trainId"));

                    TableColumn<Train, String> trainNameColumn = new TableColumn<>("Train Name");
                    trainNameColumn.setCellValueFactory(new PropertyValueFactory<>("trainName"));

                    TableColumn<Train, Integer> adminIdColumn2 = new TableColumn<>("Admin ID");
                    adminIdColumn2.setCellValueFactory(new PropertyValueFactory<>("adminId"));

                    TableColumn<Train, String> departureStationColumn = new TableColumn<>("Departure Station");
                    departureStationColumn.setCellValueFactory(new PropertyValueFactory<>("departureStation"));

                    TableColumn<Train, String> arrivalStationColumn = new TableColumn<>("Arrival Station");
                    arrivalStationColumn.setCellValueFactory(new PropertyValueFactory<>("arrivalStation"));

                    tableView4.getColumns().addAll(trainIdColumn, trainNameColumn, adminIdColumn2, departureStationColumn, arrivalStationColumn);

                    try (Connection conn = DriverManager.getConnection(url, username, passwordd)) {
                        String sqlQuery = "SELECT * FROM train";
                        try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
                            ResultSet resultSet = pstmt.executeQuery();

                            while (resultSet.next()) {
                                int trainId = resultSet.getInt("train_id");
                                String trainName = resultSet.getString("train_name");
                                int adminId = resultSet.getInt("adminid");
                                String departureStation = resultSet.getString("departure_station");
                                String arrivalStation = resultSet.getString("arrival_station");

                                Train train = new Train(trainId, trainName, adminId, departureStation, arrivalStation);
                                tableView4.getItems().add(train);
                            }

                            resultSet.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    Tab trainTab = new Tab("Train");
                    trainTab.setContent(tableView4);
                    TableView<Trip> tableView5 = new TableView<>();
                    tableView5.setEditable(false);

                    TableColumn<Trip, Integer> tripIdColumn = new TableColumn<>("Trip ID");
                    tripIdColumn.setCellValueFactory(new PropertyValueFactory<>("tripId"));

                    TableColumn<Trip, Integer> trainIdColumn2 = new TableColumn<>("Train ID");
                    trainIdColumn2.setCellValueFactory(new PropertyValueFactory<>("trainId"));

                    TableColumn<Trip, String> tripDateColumn = new TableColumn<>("Trip Date");
                    tripDateColumn.setCellValueFactory(new PropertyValueFactory<>("tripDate"));

                    TableColumn<Trip, Integer> adminIdColumn3 = new TableColumn<>("Admin ID");
                    adminIdColumn3.setCellValueFactory(new PropertyValueFactory<>("adminId"));

                    tableView5.getColumns().addAll(tripIdColumn, trainIdColumn2, tripDateColumn, adminIdColumn3);

                    try (Connection conn = DriverManager.getConnection(url, username, passwordd)) {
                        String sqlQuery = "SELECT * FROM trip";
                        try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
                            ResultSet resultSet = pstmt.executeQuery();

                            while (resultSet.next()) {
                                int tripId = resultSet.getInt("trip_id");
                                int trainId = resultSet.getInt("train_id");
                                String tripDate = resultSet.getString("trip_date");
                                int adminId = resultSet.getInt("adminid");

                                Trip trip = new Trip(tripId, trainId, tripDate, adminId);
                                tableView5.getItems().add(trip);
                            }

                            resultSet.close();
                        }
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }

                    Tab tripTab = new Tab("Trip");
                    tripTab.setContent(tableView5);
            TableView<Seat> tableView6 = new TableView<>();
            // Set up columns and data for the seats table
            TableColumn<Seat, Integer> seatIdColumn = new TableColumn<>("Seat ID");
            seatIdColumn.setCellValueFactory(new PropertyValueFactory<>("seatId"));

            TableColumn<Seat, Integer> trainIdColumn3 = new TableColumn<>("Train ID");
            trainIdColumn3.setCellValueFactory(new PropertyValueFactory<>("trainId"));

            TableColumn<Seat, Integer> tripIdColumn2 = new TableColumn<>("Trip ID");
            tripIdColumn2.setCellValueFactory(new PropertyValueFactory<>("tripId"));


            TableColumn<Seat, String> availabilityColumn = new TableColumn<>("Availability");
            availabilityColumn.setCellValueFactory(new PropertyValueFactory<>("availability"));

            tableView6.getColumns().addAll(seatIdColumn, trainIdColumn3, tripIdColumn2, availabilityColumn);

            try (Connection conn = DriverManager.getConnection(url, username, passwordd)) {
                String sqlQuery = "SELECT * FROM seat";
                try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
                    ResultSet resultSet = pstmt.executeQuery();

                    while (resultSet.next()) {
                        int seatId = resultSet.getInt("seat_id");
                        int trainId = resultSet.getInt("train_id");
                        int tripId = resultSet.getInt("trip_id");
                        String availability = resultSet.getString("availability");

                        Seat seat = new Seat(seatId, trainId, tripId,availability);
                        tableView6.getItems().add(seat);
                    }

                    resultSet.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            // Create tabs for each table


            Tab seatTab = new Tab("Seats");
            seatTab.setContent(tableView6);
            TableView<Booking> tableView7 = new TableView<>();
// Set up columns and data for the booking table
            TableColumn<Booking, Integer> bookingIdColumn = new TableColumn<>("Booking ID");
            bookingIdColumn.setCellValueFactory(new PropertyValueFactory<>("bookingId"));

            TableColumn<Booking, Integer> userIdColumn2 = new TableColumn<>("User ID");
            userIdColumn2.setCellValueFactory(new PropertyValueFactory<>("userId"));

            TableColumn<Booking, Integer> tripIdColumn3 = new TableColumn<>("Trip ID");
            tripIdColumn3.setCellValueFactory(new PropertyValueFactory<>("tripId"));

            TableColumn<Booking, String> bookingDateColumn = new TableColumn<>("Booking Date");
            bookingDateColumn.setCellValueFactory(new PropertyValueFactory<>("bookingDate"));

            TableColumn<Booking, Integer> SeatIdColumn = new TableColumn<>("Seat ID");
            SeatIdColumn.setCellValueFactory(new PropertyValueFactory<>("seatId"));

            tableView7.getColumns().addAll(bookingIdColumn, userIdColumn2, tripIdColumn3, bookingDateColumn, SeatIdColumn);

            try (Connection conn = DriverManager.getConnection(url, username, passwordd)) {
                String sqlQuery = "SELECT * FROM Booking";
                try (PreparedStatement pstmt = conn.prepareStatement(sqlQuery)) {
                    ResultSet resultSet = pstmt.executeQuery();

                    while (resultSet.next()) {
                        int bookingId = resultSet.getInt("booking_id");
                        int userId = resultSet.getInt("user_id");
                        int tripId = resultSet.getInt("trip_id");
                        String bookingDate = resultSet.getString("booking_date");
                        int seatId = resultSet.getInt("seat_id");

                        Booking booking = new Booking(bookingId, userId, tripId, bookingDate, seatId);
                        tableView7.getItems().add(booking);
                    }

                    resultSet.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

// Create tabs for each table
            seatTab.setContent(tableView6);

            Tab bookingTab = new Tab("Booking");
            bookingTab.setContent(tableView7);

// Add the tabs to the tab pane
            tabPane.getTabs().addAll(stationTab, userTab, adminTab, trainTab, tripTab, seatTab, bookingTab);

// Create a new window or dialog to display the report
            Stage reportStage = new Stage();
            reportStage.setTitle("Database Report");
            reportStage.setScene(new Scene(tabPane, 800, 600));
            reportStage.show();

        });
        generateAggregatedReportButton.setOnAction(event -> {
            // Create a new tab pane to hold the report
            TabPane tabPane = new TabPane();

            // Create a tab for Station report
            Tab stationReportTab = new Tab("Station Report");
            TableView<ObservableList<String>> stationTableView = new TableView<>();

            try (Connection conn = DriverManager.getConnection(url, username, passwordd)) {
                String stationQuery = "SELECT station_name, COUNT(*) AS station_count FROM Station GROUP BY station_name";
                try (PreparedStatement pstmt = conn.prepareStatement(stationQuery)) {
                    ResultSet resultSet = pstmt.executeQuery();

                    // Get the column names from the result set
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    List<String> columnNames = new ArrayList<>();
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames.add(metaData.getColumnName(i));
                    }

                    // Create table columns dynamically based on column names
                    for (String columnName : columnNames) {
                        TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnName);
                        column.setCellValueFactory(param -> new SimpleStringProperty(param.getValue().get(columnNames.indexOf(columnName))));
                        stationTableView.getColumns().add(column);
                    }

                    // Populate the data rows
                    while (resultSet.next()) {
                        ObservableList<String> rowData = FXCollections.observableArrayList();
                        for (String columnName : columnNames) {
                            rowData.add(resultSet.getString(columnName));
                        }
                        stationTableView.getItems().add(rowData);
                    }

                    resultSet.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            // Set the table view as the content of the Station report tab
            stationReportTab.setContent(stationTableView);

            Tab userReportTab = new Tab("User Report");
            TableView<ObservableList<String>> userTableView = new TableView<>();

            try (Connection conn = DriverManager.getConnection(url, username, passwordd)) {
                String userQuery = "SELECT COUNT(*) AS user_count FROM User";
                try (PreparedStatement pstmt = conn.prepareStatement(userQuery)) {
                    ResultSet resultSet = pstmt.executeQuery();

                    // Get the column names from the result set
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    List<String> columnNames = new ArrayList<>();
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames.add(metaData.getColumnName(i));
                    }

                    // Create table columns dynamically based on column names
                    for (String columnName : columnNames) {
                        TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnName);
                        column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(columnNames.indexOf(columnName))));
                        userTableView.getColumns().add(column);
                    }

                    // Populate the table with data
                    ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
                    while (resultSet.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (String columnName : columnNames) {
                            row.add(resultSet.getString(columnName));
                        }
                        data.add(row);
                    }
                    userTableView.setItems(data);

                    resultSet.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

// Add the user report table to the tab
            userReportTab.setContent(userTableView);
            Tab adminReportTab = new Tab("Admin Report");
            TableView<ObservableList<String>> adminTableView = new TableView<>();

            try (Connection conn = DriverManager.getConnection(url, username, passwordd)) {
                String adminQuery = "SELECT COUNT(*) AS admin_count FROM Admin";
                try (PreparedStatement pstmt = conn.prepareStatement(adminQuery)) {
                    ResultSet resultSet = pstmt.executeQuery();

                    // Get the column names from the result set
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    List<String> columnNames = new ArrayList<>();
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames.add(metaData.getColumnName(i));
                    }

                    // Create table columns dynamically based on column names
                    for (String columnName : columnNames) {
                        TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnName);
                        column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(columnNames.indexOf(columnName))));
                        adminTableView.getColumns().add(column);
                    }

                    // Populate the table with data
                    ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
                    while (resultSet.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (String columnName : columnNames) {
                            row.add(resultSet.getString(columnName));
                        }
                        data.add(row);
                    }
                    adminTableView.setItems(data);

                    resultSet.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

            adminReportTab.setContent(adminTableView);
// Add the admin report table to the tab
            Tab trainReportTab = new Tab("Train Report");
            TableView<ObservableList<String>> trainTableView = new TableView<>();

            try (Connection conn = DriverManager.getConnection(url, username, passwordd)) {
                // Count trains by departure station
                String departureQuery = "SELECT departure_station, COUNT(*) AS DepartureCount FROM train GROUP BY departure_station";
                try (PreparedStatement pstmt = conn.prepareStatement(departureQuery)) {
                    ResultSet resultSet = pstmt.executeQuery();

                    // Get the column names from the result set
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    List<String> columnNames = new ArrayList<>();
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames.add(metaData.getColumnName(i));
                    }

                    // Create table columns dynamically based on column names
                    for (String columnName : columnNames) {
                        TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnName);
                        column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(columnNames.indexOf(columnName))));
                        trainTableView.getColumns().add(column);
                    }

                    // Populate the table with data
                    ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
                    while (resultSet.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (String columnName : columnNames) {
                            row.add(resultSet.getString(columnName));
                        }
                        data.add(row);
                    }
                    trainTableView.setItems(data);

                    resultSet.close();
                }

                // Count trains by arrival station
                String arrivalQuery = "SELECT arrival_station, COUNT(*) AS ArrivalCount FROM train GROUP BY arrival_station";
                try (PreparedStatement pstmt = conn.prepareStatement(arrivalQuery)) {
                    ResultSet resultSet = pstmt.executeQuery();

                    // Get the column names from the result set
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    List<String> columnNames = new ArrayList<>();
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames.add(metaData.getColumnName(i));
                    }

                    // Create table columns dynamically based on column names
                    for (String columnName : columnNames) {
                        TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnName);
                        column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(columnNames.indexOf(columnName))));
                        trainTableView.getColumns().add(column);
                    }

                    // Populate the table with data
                    ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
                    while (resultSet.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (String columnName : columnNames) {
                            row.add(resultSet.getString(columnName));
                        }
                        data.add(row);
                    }
                    trainTableView.setItems(data);

                    resultSet.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

// Add the train report table to the tab
            trainReportTab.setContent(trainTableView);
            Tab tripReportTab = new Tab("Trip Report");
            TableView<ObservableList<String>> tripTableView = new TableView<>();

            try (Connection conn = DriverManager.getConnection(url, username, passwordd)) {
// Count trips by trip date
                String tripDateQuery = "SELECT trip_date, COUNT(*) AS TripCount FROM trip GROUP BY trip_date";
                try (PreparedStatement pstmt = conn.prepareStatement(tripDateQuery)) {
                    ResultSet resultSet = pstmt.executeQuery();
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    List<String> columnNames = new ArrayList<>();
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames.add(metaData.getColumnName(i));
                    }

                    // Create table columns dynamically based on column names
                    for (String columnName : columnNames) {
                        TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnName);
                        column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(columnNames.indexOf(columnName))));
                        tripTableView.getColumns().add(column);
                    }

                    // Populate the table with data
                    ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
                    while (resultSet.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (String columnName : columnNames) {
                            row.add(resultSet.getString(columnName));
                        }
                        data.add(row);
                    }
                    tripTableView.setItems(data);

                    resultSet.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

// Add the trip report table to the tab
            tripReportTab.setContent(tripTableView);

            Tab seatReportTab = new Tab("Seat Report");
            TableView<ObservableList<String>> seatTableView = new TableView<>();

            try (Connection conn = DriverManager.getConnection(url, username, passwordd)) {

// Count seats by train id
                String trainIdQuery = "SELECT train_id, COUNT(*) AS SeatCount FROM seat GROUP BY train_id";
                try (PreparedStatement pstmt = conn.prepareStatement(trainIdQuery)) {
                    ResultSet resultSet = pstmt.executeQuery();

                    // Get the column names from the result set
                    ResultSetMetaData metaData = resultSet.getMetaData();
                    int columnCount = metaData.getColumnCount();
                    List<String> columnNames = new ArrayList<>();
                    for (int i = 1; i <= columnCount; i++) {
                        columnNames.add(metaData.getColumnName(i));
                    }

                    // Create table columns dynamically based on column names
                    for (String columnName : columnNames) {
                        TableColumn<ObservableList<String>, String> column = new TableColumn<>(columnName);
                        column.setCellValueFactory(param -> new ReadOnlyObjectWrapper<>(param.getValue().get(columnNames.indexOf(columnName))));
                        seatTableView.getColumns().add(column);
                    }

                    // Populate the table with data
                    ObservableList<ObservableList<String>> data = FXCollections.observableArrayList();
                    while (resultSet.next()) {
                        ObservableList<String> row = FXCollections.observableArrayList();
                        for (String columnName : columnNames) {
                            row.add(resultSet.getString(columnName));
                        }
                        data.add(row);
                    }
                    seatTableView.setItems(data);

                    resultSet.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
            }

// Add the seat report table to the tab
            seatReportTab.setContent(seatTableView);

// Add the seat report tab to the tab pane

// Add the user report tab to the tab pane
            // Add the report tabs to the tab pane
            tabPane.getTabs().addAll(stationReportTab, userReportTab,adminReportTab,trainReportTab,tripReportTab,seatReportTab);

            // Create a new window or dialog to display the report
            Stage reportStage = new Stage();
            reportStage.setTitle("Aggregated Report");
            reportStage.setScene(new Scene(tabPane, 800, 600));
            reportStage.show();
        });


        // Define the action for the exit button
        exitButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Are you sure you want to exit?");
            alert.setContentText("Any unsaved changes will be lost.");

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                Platform.exit();
            }
        });


        // Add components to the grid pane
        vbox.getChildren().addAll(signUpUserButton, signUpAdminButton, loginUserButton, loginAdminButton,generateReportButton,generateAggregatedReportButton ,exitButton);

        // Create the scene
        Scene scene = new Scene(vbox, 300, 250);

        // Set the scene to the primary stage
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    private void showProfileDialog(String title, String profileInfo) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(profileInfo);
        alert.showAndWait();
    }
}

