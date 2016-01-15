package ViewsGUI;/**
 * Created by Dutzi on 1/8/2016.
 */

import controller.Controller;
import domain.DomainException;
import domain.PrgState;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.JavaFXBuilderFactory;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import repository.IRepository;
import repository.Repository;
import repository.RepositoryException;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class GUI extends Application {
    private Stage window;


    public static void main(String[] args) {
        launch(args);
    }
    public void start(Stage mainStage) throws IOException, RepositoryException {
        IRepository repo = new Repository();
        Controller ctrl = new Controller(repo);
        List<PrgState> programs = new ArrayList<>();
        ctrl.getRepo().setPrgList(programs);

        mainStage.setTitle("Toy Language Interpreter");
        window = mainStage;

        FXMLLoader fxmlLoader = new FXMLLoader(InitialMenuController.class.getResource("InitialMenu.fxml"));
        fxmlLoader.setBuilderFactory(new JavaFXBuilderFactory());
        Parent root = fxmlLoader.load(InitialMenuController.class.getResource("InitialMenu.fxml").openStream());

        InitialMenuController ctrl1 = fxmlLoader.getController();
        ctrl1.init(ctrl);

        Scene scene = new Scene(root);
        window.setScene(scene);

        window.show();

    }
}
