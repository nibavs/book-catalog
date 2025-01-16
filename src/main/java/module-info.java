module com.github.nibavs.bookcatalog {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires org.postgresql.jdbc;
    requires java.desktop;


    opens com.github.nibavs.bookcatalog to javafx.fxml;
    exports com.github.nibavs.bookcatalog;
    exports com.github.nibavs.bookcatalog.model;
    opens com.github.nibavs.bookcatalog.model to javafx.fxml;
    exports com.github.nibavs.bookcatalog.controller;
    opens com.github.nibavs.bookcatalog.controller to javafx.fxml;
}