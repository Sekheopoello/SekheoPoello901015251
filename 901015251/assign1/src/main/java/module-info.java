module com.example.assign1 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.assign1 to javafx.fxml;
    exports com.example.assign1;
}