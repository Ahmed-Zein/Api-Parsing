package main;

import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
/**
 *
 * @author ahmed
 */
public class mainUI extends Application {

    ArrayList<BigObject> obs = new ArrayList<>();
    //BigObject ob1 = new BigObject("object1", "I");

    @Override
    public void start(Stage primaryStage) {
        obs.add(new BigObject("ob1", "I"));
        obs.add(new BigObject("ob2", "I"));
        obs.add(new BigObject("ob3", "I"));
        obs.add(new BigObject("ob4", "I"));
        obs.add(new BigObject("ob5", "I"));
        obs.add(new BigObject("ob6", "5"));
        obs.add(new BigObject("ob7", "5"));

        obs.get(0).addField(new Field("field1", "test", "123", "Y"));
        obs.get(0).addField(new Field("field2", "test", "123", "Y"));
        obs.get(0).addField(new Field("field3", "test", "123", "Y"));

        obs.get(1).addField(new Field("field1", "test", "123", "Y"));
        obs.get(1).addField(new Field("field2", "test", "123", "Y"));
        obs.get(1).addField(new Field("field3", "test", "123", "Y"));

        obs.get(2).addField(new Field("field1", "test", "123", "Y"));
        obs.get(2).addField(new Field("field2", "test", "123", "Y"));

        obs.get(3).addField(new Field("field1", "test", "123", "Y"));
        obs.get(3).addField(new Field("field2", "test", "123", "Y"));
        obs.get(3).addField(new Field("field3", "test", "123", "Y"));

        obs.get(4).addField(new Field("field1", "test", "123", "Y"));
        obs.get(4).addField(new Field("field2", "test", "123", "Y"));
        obs.get(4).addField(new Field("field3", "test", "123", "Y"));

        obs.get(5).addField(new Field("field1", "test", "123", "Y"));
        obs.get(5).addField(new Field("field2", "test", "123", "Y"));
        obs.get(5).addField(new Field("field3", "test", "123", "Y"));

        obs.get(6).addField(new Field("field1", "test", "123", "Y"));
        obs.get(6).addField(new Field("field2", "test", "123", "Y"));
        obs.get(6).addField(new Field("field3", "test", "123", "Y"));

        // intializing panes for the GUI
        VBox container = new VBox();

        // adding all fileds in the object to the obFields pane
        for (int j = 0; j < obs.size(); j++) {

            //intialize two dummy panes to hold objects data
            HBox tempHBox = new HBox();
            VBox tempVBox = new VBox(new Label(obs.get(j).getObjectName()), new Label(obs.get(j).getIo()));

            // configure panes
            tempHBox.setPadding(new Insets(10));
            tempHBox.setSpacing(50);

            tempHBox.getChildren().add(tempVBox);
            for (int i = 0; i < obs.get(j).getFields().size(); i++) {
                GridPane tempGrid = new GridPane();
                
                //add Try-Catch to check if the item is an BigObject or Field
                tempGrid.add(new Label(obs.get(j).getFields().get(i).getName()), i, 0);
                tempGrid.add(new Label(obs.get(j).getFields().get(i).getType()), i, 1);
                tempGrid.add(new Label(obs.get(j).getFields().get(i).getAllowedVals()), i, 2);
                tempGrid.add(new Label(obs.get(j).getFields().get(i).getMandatory()), i, 3);
                
                
                tempHBox.getChildren().add(tempGrid);
            }
            container.getChildren().add(tempHBox);
        }

        Scene scene = new Scene(container, 300, 250);

        //configuring scene
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
