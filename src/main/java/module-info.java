module com.examle.task2 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.examle.task2 to javafx.fxml;
    exports com.examle.task2;
}