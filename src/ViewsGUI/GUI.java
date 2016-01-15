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


        /**private Button btnAllStep, btnOneStep,btnSer, btnDeser, btnExit, btnAddPrg;
         private Stage window;
         private Scene sceneMain, sceneSec;

         public static void main(String[] args) {
         launch(args);
         }

         @Override public void start(Stage mainStage) throws RepositoryException{
         IRepository repo = new Repository();
         Controller ctrl = new Controller(repo);

         mainStage.setTitle("Toy Language Interpreter");
         mainStage.setResizable(false);
         window = mainStage;

         /** SCENES AND LAYOUTS ** /
         HBox boxUp = new HBox();
         boxUp.setAlignment(Pos.TOP_CENTER);
         boxUp.setMaxSize(350,200);
         HBox boxDown = new HBox();
         boxDown.setAlignment(Pos.BOTTOM_CENTER);
         boxDown.setMaxSize(500, 200);
         VBox boxL = new VBox();

         HBox layoutMain = new HBox();
         sceneMain = new Scene(layoutMain, 500, 500);
         HBox layoutSec = new HBox();
         sceneSec = new Scene(layoutSec, 500,500);

         /** BUTTONS * /
         btnAddPrg = new Button("Input program");
         btnAddPrg.setOnAction(e->handleButton(e));
         btnAllStep = new Button("Execute");
         btnAllStep.setOnAction(e->handleButton(e));
         btnOneStep = new Button("One step");
         btnSer = new Button("Serialize");
         btnDeser = new Button("Deserialize");
         btnExit = new Button("Exit");
         btnExit.setOnAction(e->handleButton(e));

         btnAllStep.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
         btnOneStep.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
         btnDeser.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
         btnSer.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);
         btnExit.setMaxSize(Double.MAX_VALUE, Double.MAX_VALUE);

         /** Lists* /

         TextArea textArea = TextAreaBuilder.create()
         .prefWidth(350)
         .prefHeight(200)
         .editable(false)
         .wrapText(true)
         .build();

         ScrollPane scrollPane = new ScrollPane();
         scrollPane.setContent(textArea);
         scrollPane.setMaxHeight(200);
         scrollPane.setMaxWidth(350);

         TextArea textAreaConsole = TextAreaBuilder.create()
         .prefWidth(500)
         .prefHeight(200)
         .editable(false)
         .wrapText(true)
         .build();

         ScrollPane scrollPaneConsole = new ScrollPane();
         scrollPaneConsole.setContent(textArea);
         scrollPaneConsole.setMaxWidth(500);
         scrollPaneConsole.setMaxHeight(200);
         //scrollPaneConsole.setFitToWidth(true);
         //scrollPaneConsole.setFitToHeight(true);
         //scrollPaneConsole.setPrefWidth(500);
         //scrollPaneConsole.setPrefHeight(200);

         /** Manipulation* /

         boxUp.getChildren().add(boxL);
         boxUp.setSpacing(10);
         boxUp.setPadding(new Insets(20));
         //boxUp.getChildren().add(scrollPane);


         boxDown.setSpacing(10);
         boxDown.setPadding(new Insets(20));
         boxDown.getChildren().add(scrollPaneConsole);


         boxL.setAlignment(Pos.TOP_LEFT);
         boxL.getChildren().addAll(btnAddPrg,btnAllStep, btnOneStep, btnSer, btnDeser, btnExit);

         layoutMain.getChildren().addAll(boxUp,boxDown);

         window.setScene(sceneMain);
         window.show();
         }

         private void handleButton(ActionEvent event){
         Button b = (Button)event.getSource();
         if(b == btnExit){Platform.exit();}
         else if(b==btnAddPrg){window.setScene(sceneSec);}
         else if(b==btnAllStep){System.out.println("ALL STEP");}

         } */
    }
}
