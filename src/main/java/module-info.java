module org.example.beanbuddies {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    opens org.example.beanbuddies to javafx.fxml;
    exports org.example.beanbuddies;
}

