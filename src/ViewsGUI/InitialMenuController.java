package ViewsGUI;
import controller.Controller;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import repository.EmptyRepository;

import java.io.IOException;
import java.net.URL;

public class InitialMenuController {
    private Controller ctrl;
    public CheckBox checkLog;

    public void init(Controller ctrl) {

        this.ctrl = ctrl;
        checkLog.setSelected(ctrl.isLogFlag());
    }

    public void actionInputProgram(ActionEvent event) throws InterruptedException, EmptyRepository, IOException {
        Stage primaryStage = new Stage();
        URL location = InputProgramController.class.getResource("InputProgram.fxml");
try {

    FXMLLoader fxmlLoader = new FXMLLoader();
    fxmlLoader.setLocation(location);
    fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
    Parent root = fxmlLoader.load(location.openStream());

        InputProgramController ctrl1 = fxmlLoader.getController();
        ctrl1.init(ctrl);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
} catch (IOException exception) {
    throw new RuntimeException(exception);
}
        primaryStage.show();

    }

    public void actionAllStepExecution(ActionEvent event) throws InterruptedException, EmptyRepository, IOException {
        Stage primaryStage = new Stage();
        URL location = AllStepExecController.class.getResource("AllStepExec.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load(location.openStream());

        AllStepExecController ctrl1 = fxmlLoader.getController();
        ctrl1.init(ctrl);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public void actionOneStepExecution(ActionEvent event)throws InterruptedException, EmptyRepository, IOException {
        Stage primaryStage = new Stage();
        URL location = OneStepExecController.class.getResource("OneStepExec.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load(location.openStream());

        OneStepExecController ctrl1 = fxmlLoader.getController();
        ctrl1.init(ctrl);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public void actionSerialize(ActionEvent event) {
        ctrl.getRepo().serialize();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Serialized");
        alert.setContentText("Serialization complete!");

        alert.showAndWait();

    }


    public void actionDeserialize(ActionEvent event)throws InterruptedException,ClassNotFoundException, EmptyRepository, IOException {
        Stage primaryStage = new Stage();
        URL location = DeserializeController.class.getResource("Deserialize.fxml");

        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(location);
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load(location.openStream());

        DeserializeController ctrl1 = fxmlLoader.getController();
        ctrl1.init(ctrl);

        Scene scene = new Scene(root);

        primaryStage.setScene(scene);

        primaryStage.show();
    }


    public void actionCheckLog(ActionEvent event) {
        ctrl.changeLogFlag();

    }

    public void actionBtnExit(ActionEvent event){
        Platform.exit();
    }

}

