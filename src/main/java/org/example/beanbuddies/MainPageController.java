package org.example.beanbuddies;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Locale;
import java.util.Optional;

public class MainPageController {
    @FXML private TextField searchField;
    @FXML private Button loginBtn;
    @FXML private Button signupBtn;
    @FXML private Button myCoursesBtn;
    @FXML private Label userLabel;
    @FXML private ComboBox<String> levelFilter;
    @FXML private ComboBox<String> priceFilter;
    @FXML private Label courseCountLabel;
    @FXML private GridPane coursesGrid;

    private final ObservableList<Course> allCourses = FXCollections.observableArrayList();
    private FilteredList<Course> filteredCourses;
    private String currentUser = null;

    @FXML
    public void initialize() {
        // Populate sample courses with prices
        allCourses.addAll(
                new Course("Java Programming Masterclass", "Tim Buchalka", "80h 30m", "Beginner",
                        "Learn Java programming from scratch. Master Java 17, object-oriented programming, data structures, and more.",
                        "https://via.placeholder.com/340x190.png?text=Java+Programming",
                        "https://www.example.com", 89.99, 4.6, 256430),
                new Course("The Complete Web Development Bootcamp", "Angela Yu", "60h 15m", "Beginner",
                        "Become a full-stack web developer with just one course. HTML, CSS, JavaScript, Node, React, MongoDB, and more!",
                        "https://via.placeholder.com/340x190.png?text=Web+Development",
                        "https://www.example.com", 94.99, 4.7, 182340),
                new Course("Advanced JavaFX UI Development", "Jane Smith", "35h 20m", "Intermediate",
                        "Build modern desktop applications with JavaFX, FXML, CSS styling, and popular UI libraries.",
                        "https://via.placeholder.com/340x190.png?text=JavaFX+UI",
                        "https://www.example.com", 79.99, 4.5, 45230),
                new Course("Spring Boot Microservices", "John Thompson", "45h 0m", "Advanced",
                        "Master Spring Boot and microservices architecture. Build scalable REST APIs with Spring Cloud.",
                        "https://via.placeholder.com/340x190.png?text=Spring+Boot",
                        "https://www.example.com", 99.99, 4.8, 89450),
                new Course("Python for Data Science", "Jose Portilla", "52h 30m", "Intermediate",
                        "Learn Python for data analysis, visualization, and machine learning with NumPy, Pandas, and Matplotlib.",
                        "https://via.placeholder.com/340x190.png?text=Python+Data+Science",
                        "https://www.example.com", 84.99, 4.6, 123890),
                new Course("React - The Complete Guide", "Maximilian Schwarzmüller", "48h 15m", "Intermediate",
                        "Master React 18+ with hooks, Redux, React Router, Next.js, and build amazing modern web apps.",
                        "https://via.placeholder.com/340x190.png?text=React+Guide",
                        "https://www.example.com", 89.99, 4.7, 198760),
                new Course("Machine Learning A-Z", "Kirill Eremenko", "44h 0m", "Advanced",
                        "Learn to create Machine Learning Algorithms in Python and R from industry experts.",
                        "https://via.placeholder.com/340x190.png?text=Machine+Learning",
                        "https://www.example.com", 94.99, 4.5, 167230),
                new Course("AWS Certified Solutions Architect", "Stephane Maarek", "27h 45m", "Intermediate",
                        "Pass the AWS Solutions Architect Associate exam. Learn AWS cloud computing and architecture.",
                        "https://via.placeholder.com/340x190.png?text=AWS+Architect",
                        "https://www.example.com", 79.99, 4.7, 145670),
                new Course("The Complete SQL Bootcamp", "Jose Portilla", "9h 0m", "Beginner",
                        "Master SQL database queries with PostgreSQL. Perfect for data analysis and backend development.",
                        "https://via.placeholder.com/340x190.png?text=SQL+Bootcamp",
                        "https://www.example.com", 69.99, 4.6, 89340),
                new Course("Docker and Kubernetes", "Stephen Grider", "22h 30m", "Advanced",
                        "Build, test, and deploy Docker applications with Kubernetes while learning production-best practices.",
                        "https://via.placeholder.com/340x190.png?text=Docker+Kubernetes",
                        "https://www.example.com", 84.99, 4.8, 76540),
                new Course("iOS App Development Bootcamp", "Angela Yu", "55h 30m", "Beginner",
                        "Learn iOS app development with Swift 5 and build real apps including a Pokemon clone.",
                        "https://via.placeholder.com/340x190.png?text=iOS+Development",
                        "https://www.example.com", 94.99, 4.7, 134560),
                new Course("Ethical Hacking from Scratch", "Zaid Sabih", "15h 30m", "Intermediate",
                        "Learn penetration testing, ethical hacking, and cybersecurity from scratch with practical examples.",
                        "https://via.placeholder.com/340x190.png?text=Ethical+Hacking",
                        "https://www.example.com", 89.99, 4.5, 98760)
        );

        filteredCourses = new FilteredList<>(allCourses, p -> true);

        levelFilter.setItems(FXCollections.observableArrayList("All Levels", "Beginner", "Intermediate", "Advanced"));
        levelFilter.setValue("All Levels");
        priceFilter.setItems(FXCollections.observableArrayList("All Prices", "Under $75", "$75 - $90", "Over $90", "Free"));
        priceFilter.setValue("All Prices");

        searchField.textProperty().addListener((obs, oldV, newV) -> applyFilters());
        levelFilter.valueProperty().addListener((obs, oldV, newV) -> applyFilters());
        priceFilter.valueProperty().addListener((obs, oldV, newV) -> applyFilters());

        Platform.runLater(() -> {
            renderCourses();
            updateCourseCount();
        });
    }

    private void applyFilters() {
        String searchText = searchField.getText() == null ? "" : searchField.getText().toLowerCase(Locale.ROOT);
        String level = levelFilter.getValue();
        String price = priceFilter.getValue();

        filteredCourses.setPredicate(course -> {
            boolean matchesSearch = searchText.isEmpty() ||
                    course.getTitle().toLowerCase(Locale.ROOT).contains(searchText) ||
                    course.getInstructor().toLowerCase(Locale.ROOT).contains(searchText) ||
                    course.getDescription().toLowerCase(Locale.ROOT).contains(searchText);

            boolean matchesLevel = level == null || level.equals("All Levels") || course.getLevel().equals(level);

            boolean matchesPrice = true;
            if (price != null && !price.equals("All Prices")) {
                double coursePrice = course.getPrice();
                matchesPrice = switch (price) {
                    case "Under $75" -> coursePrice < 75;
                    case "$75 - $90" -> coursePrice >= 75 && coursePrice <= 90;
                    case "Over $90" -> coursePrice > 90;
                    case "Free" -> coursePrice == 0;
                    default -> true;
                };
            }

            return matchesSearch && matchesLevel && matchesPrice;
        });

        renderCourses();
        updateCourseCount();
    }

    @FXML
    private void clearFilters() {
        searchField.clear();
        levelFilter.setValue("All Levels");
        priceFilter.setValue("All Prices");
    }

    private void renderCourses() {
        coursesGrid.getChildren().clear();
        int col = 0;
        int row = 0;

        for (Course course : filteredCourses) {
            VBox courseCard = createCourseCard(course);
            coursesGrid.add(courseCard, col, row);
            col++;
            if (col == 3) {
                col = 0;
                row++;
            }
        }
    }

    private VBox createCourseCard(Course course) {
        VBox card = new VBox(10.0);
        card.setStyle("-fx-border-color: #d1d7dc; -fx-border-width: 1px; -fx-background-color: white; -fx-cursor: hand;");
        card.setPrefWidth(280.0);
        card.setMaxWidth(280.0);

        ImageView imageView = new ImageView();
        imageView.setFitWidth(280.0);
        imageView.setFitHeight(150.0);
        imageView.setPreserveRatio(true);
        imageView.setImage(new Image(course.getImageUrl(), 560, 300, true, true, true));

        VBox infoBox = new VBox(6.0);
        infoBox.setPadding(new Insets(10.0));

        Label title = new Label(course.getTitle());
        title.setStyle("-fx-font-weight: bold; -fx-font-size: 14px;");
        title.setWrapText(true);
        title.setMaxWidth(260.0);

        Label instructor = new Label(course.getInstructor());
        instructor.setStyle("-fx-text-fill: #6a6f73; -fx-font-size: 12px;");

        HBox ratingBox = new HBox(8.0);
        ratingBox.setAlignment(Pos.CENTER_LEFT);
        Label ratingLabel = new Label(String.format("%.1f", course.getRating()));
        ratingLabel.setStyle("-fx-font-weight: bold; -fx-text-fill: #b4690e; -fx-font-size: 13px;");

        Label stars = new Label("★★★★★");
        stars.setStyle("-fx-text-fill: #b4690e; -fx-font-size: 12px;");

        Label enrollCount = new Label(String.format("(%,d)", course.getEnrollments()));
        enrollCount.setStyle("-fx-text-fill: #6a6f73; -fx-font-size: 11px;");

        ratingBox.getChildren().addAll(ratingLabel, stars, enrollCount);

        HBox metaBox = new HBox(10.0);
        metaBox.setAlignment(Pos.CENTER_LEFT);
        Label priceLabel = new Label(course.getPrice() == 0 ? "Free" : String.format("$%.2f", course.getPrice()));
        priceLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16px;");

        Label duration = new Label(course.getDuration());
        duration.setStyle("-fx-text-fill: #6a6f73; -fx-font-size: 11px;");

        Region spacer = new Region();
        HBox.setHgrow(spacer, Priority.ALWAYS);

        metaBox.getChildren().addAll(priceLabel, spacer, duration);

        Label levelBadge = new Label(course.getLevel());
        levelBadge.setStyle("-fx-background-color: #eceb98; -fx-padding: 3px 8px; -fx-font-size: 11px; -fx-background-radius: 3px;");

        infoBox.getChildren().addAll(title, instructor, ratingBox, metaBox, levelBadge);
        card.getChildren().addAll(imageView, infoBox);

        card.setOnMouseClicked(e -> showCourseDetail(course));
        card.setOnMouseEntered(e -> card.setStyle("-fx-border-color: #1c1d1f; -fx-border-width: 2px; -fx-background-color: white; -fx-cursor: hand;"));
        card.setOnMouseExited(e -> card.setStyle("-fx-border-color: #d1d7dc; -fx-border-width: 1px; -fx-background-color: white; -fx-cursor: hand;"));

        return card;
    }

    private void showCourseDetail(Course course) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(course.getTitle());
        alert.setHeaderText("Course Details");

        String content = String.format(
            "Instructor: %s\n" +
            "Level: %s\n" +
            "Duration: %s\n" +
            "Rating: %.1f (%,d students)\n" +
            "Price: $%.2f\n\n" +
            "%s",
            course.getInstructor(),
            course.getLevel(),
            course.getDuration(),
            course.getRating(),
            course.getEnrollments(),
            course.getPrice(),
            course.getDescription()
        );

        alert.setContentText(content);

        ButtonType enrollBtn = new ButtonType("Enroll Now", ButtonBar.ButtonData.OK_DONE);
        ButtonType cancelBtn = new ButtonType("Close", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(enrollBtn, cancelBtn);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == enrollBtn) {
            if (currentUser != null) {
                showAlert("Enrollment Successful", "You have been enrolled in: " + course.getTitle(), Alert.AlertType.INFORMATION);
            } else {
                showLoginDialog();
            }
        }
    }

    private void updateCourseCount() {
        courseCountLabel.setText(filteredCourses.size() + " courses");
    }

    @FXML
    private void showLoginDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Log In to BeanBuddies");
        dialog.setHeaderText("Enter your credentials");

        ButtonType loginButtonType = new ButtonType("Log In", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(loginButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField username = new TextField();
        username.setPromptText("Username or Email");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");

        grid.add(new Label("Email:"), 0, 0);
        grid.add(username, 1, 0);
        grid.add(new Label("Password:"), 0, 1);
        grid.add(password, 1, 1);

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(username::requestFocus);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == loginButtonType) {
            if (!username.getText().isEmpty() && !password.getText().isEmpty()) {
                currentUser = username.getText();
                updateUIForLoggedInUser();
                showAlert("Login Successful", "Welcome back, " + currentUser + "!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Login Failed", "Please enter both email and password.", Alert.AlertType.ERROR);
            }
        }
    }

    @FXML
    private void showSignupDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Sign Up for BeanBuddies");
        dialog.setHeaderText("Create your account");

        ButtonType signupButtonType = new ButtonType("Sign Up", ButtonBar.ButtonData.OK_DONE);
        dialog.getDialogPane().getButtonTypes().addAll(signupButtonType, ButtonType.CANCEL);

        GridPane grid = new GridPane();
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(20, 150, 10, 10));

        TextField fullName = new TextField();
        fullName.setPromptText("Full Name");
        TextField email = new TextField();
        email.setPromptText("Email");
        PasswordField password = new PasswordField();
        password.setPromptText("Password");
        PasswordField confirmPassword = new PasswordField();
        confirmPassword.setPromptText("Confirm Password");

        grid.add(new Label("Full Name:"), 0, 0);
        grid.add(fullName, 1, 0);
        grid.add(new Label("Email:"), 0, 1);
        grid.add(email, 1, 1);
        grid.add(new Label("Password:"), 0, 2);
        grid.add(password, 1, 2);
        grid.add(new Label("Confirm:"), 0, 3);
        grid.add(confirmPassword, 1, 3);

        dialog.getDialogPane().setContent(grid);
        Platform.runLater(fullName::requestFocus);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == signupButtonType) {
            if (!fullName.getText().isEmpty() && !email.getText().isEmpty() &&
                !password.getText().isEmpty() && password.getText().equals(confirmPassword.getText())) {
                currentUser = email.getText();
                updateUIForLoggedInUser();
                showAlert("Registration Successful", "Welcome to BeanBuddies, " + fullName.getText() + "!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Registration Failed", "Please fill all fields and ensure passwords match.", Alert.AlertType.ERROR);
            }
        }
    }

    private void updateUIForLoggedInUser() {
        loginBtn.setVisible(false);
        loginBtn.setManaged(false);
        signupBtn.setVisible(false);
        signupBtn.setManaged(false);

        myCoursesBtn.setVisible(true);
        myCoursesBtn.setManaged(true);
        userLabel.setText(currentUser);
        userLabel.setVisible(true);
        userLabel.setManaged(true);
    }

    @FXML
    private void openDashboard() {
        showAlert("Dashboard", "Dashboard feature coming soon!", Alert.AlertType.INFORMATION);
    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}

