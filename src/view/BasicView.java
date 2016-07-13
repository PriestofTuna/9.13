package view;

import com.sun.corba.se.impl.io.TypeMismatchException;
import com.sun.xml.internal.ws.api.model.ExceptionType;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import objectPackage.Location;


import static javafx.geometry.Pos.CENTER;

/**
 * Created by lytte on 7/11/2016.
 * @author lytte
 */
public class BasicView extends Application {
    Scene scene1, scene2, scene3;
    TextField rowTF, colTF;
    Button rowAndCol;
    public static void main(String[] args) {launch(args); }
    public void start(Stage primaryStage) throws Exception {
        rowAndCol = new Button("set bounds");
        rowTF = new TextField();
        rowTF.setPromptText("Enter the array's rows (Max total 20)");

        colTF = new TextField();
        colTF.setPromptText("Enter the columns ");
        rowAndCol.setOnAction(e -> {
            int col = Integer.parseInt(rowTF.getText());
            //input for rows and columns
            int row = Integer.parseInt(colTF.getText());
            int totalFields = col * row;
            Group root = new Group();
            final ScrollBar scroll = new ScrollBar(); //initialzies scrollbar
            double array[][] = new double[row][col];
            //variables above, fields and GUI below
            TextField input[] = new TextField[totalFields];
            Label explain = new Label("input the numbers below");
            Button pull = new Button("evaluate");
            //ScrollBar
            Screen screen = Screen.getPrimary();
            Rectangle2D bounds = screen.getVisualBounds();
            scene2 = new Scene(root, bounds.getWidth(), bounds.getHeight());
            //scene set as group root
            scroll.setLayoutX(scene2.getWidth()-scroll.getWidth());
            scroll.setOrientation(Orientation.VERTICAL);
            scroll.setPrefHeight(bounds.getHeight());
            scroll.setPrefWidth(350);
            scroll.setMax(450);
            //beginning of gridshowInput
            GridPane gridshowInput = new GridPane();
            gridshowInput.setAlignment(CENTER);
            gridshowInput.setHgap(10);
            gridshowInput.setVgap(8);
            gridshowInput.setPadding(new Insets(5,5,5,5));
            gridshowInput.add(explain, 0, 0);
            gridshowInput.add(pull, 0, 1 + totalFields);
            for(int i = 0; i < totalFields; ++i) {
                input[i] = new TextField("");
                input[i].setPromptText("Number " + (i + 1));
                gridshowInput.add(input[i], 0, i);
                //initializes the TextFields
            }
            //scrollbar Method
            scroll.valueProperty().addListener(new ChangeListener<Number>() {
                public void changed(ObservableValue<? extends Number> ov, Number old_val, Number new_val) {
                    gridshowInput.setLayoutY(-new_val.doubleValue());
                }
            });
            root.getChildren().addAll(gridshowInput, scroll);
            primaryStage.setScene(scene2);
            primaryStage.show(); //shows gridshowInput, doesn't implement Scrollbar

            pull.setOnAction(e2 -> {
                GridPane gridshow = new GridPane();
                gridshow.setAlignment(CENTER);
                gridshow.setPadding(new Insets(5,5,5,5));
                int inputLocation = 0;
                    for(int getrow = 0; getrow < array.length; getrow++) {
                        for(int getcol = 0; getcol < array[getrow].length; getcol++) {
                            try {
                                array[getrow][getcol] = Double.parseDouble(input[inputLocation].getText());
                                inputLocation += 1;
                            } catch(TypeMismatchException exception) {
                                array[getrow][getcol] = 0.0;
                            }
                            }
                    }


                Location locate = Location.locateLargest(array); // calls locateLargest
                TextArea out = new TextArea();
                String output = ("the size of the Matrix is " + array.length + " by " + array[0].length + "\n" + "largest element in Matrix " + locate.maxValue + "\n" + "Largest's location "
                        + "\n" + "(" + (locate.arRow + 1) + ", "  + (locate.arCol + 1) + ")");
                //output sets the String that will go into the Text Area
                out.setText(output);
                gridshow.add(out, 0, 1);
                scene3 = new Scene(gridshow, 400, 500);
                primaryStage.setScene(scene3);
                primaryStage.show(); //changes GUI to gridShow
            });
        });
        Screen screen = Screen.getPrimary();
        Rectangle2D bounds = screen.getVisualBounds();
        GridPane gridA = new GridPane();
        gridA.setAlignment(CENTER);
        gridA.setVgap(15.0);
        gridA.setHgap(10.0);
        gridA.add(rowTF, 0, 0);
        gridA.add(colTF, 0, 1);
        gridA.add(rowAndCol, 1, 0);
        scene1 = new Scene(gridA, bounds.getWidth(), bounds.getHeight());
        primaryStage.setScene(scene1);
        primaryStage.show();
    }

}
