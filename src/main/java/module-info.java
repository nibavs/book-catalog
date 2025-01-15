module com.github.nibavs.bookcatalog {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.github.nibavs.bookcatalog to javafx.fxml;
    exports com.github.nibavs.bookcatalog;
}