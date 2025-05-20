module com.example.progetto_sad_gruppo14_ah {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.progetto_sad_gruppo14_ah to javafx.fxml;
    exports com.example.progetto_sad_gruppo14_ah;
    exports com.example.Model;
    exports com.example.Factory;
    exports com.example.Command;
}