/**
 **  Author:        Mitra Rouhipour
 **  Date:          March 18, 2021
 **  Course:        CPSC 1150
 **  Compiler:       JDK 1.8
 */
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.shape.Arc;



public class Scenery extends Application {
    private CheckBox treeCheckBox;
    private CheckBox animalCheckBox;
    private RadioButton zeroDegree;
    private RadioButton ninetyDegree;
    private RadioButton oneHundredEightyDegree;
    private RadioButton twoHundredSeventyDegree;
    private Button closeButton;
    private Button changeText;
    private Text caption;
    private Text background;
    private TextField textField;
    private ToggleGroup rotate;
    private Group animal;
    private Group tree;
    private Rectangle clip;
    private Text backgroundText;
    private Pane painting;
    private BorderPane root;

    public static void main(String[] args) {
        launch(args);
    }//main method

    @Override
    public void start(Stage primaryStage) {

        root = new BorderPane();
        painting = new Pane();
        //set width and height of the painting Pane
        painting.setPrefWidth(600);
        painting.setPrefHeight(500);

        //clip the rainbow
        clip = new Rectangle(0,0,600,500);
        painting.setClip(clip);

        Rectangle ground = new Rectangle(0, 400, 600, 100);
        ground.setFill(Color.DARKGREEN);
        painting.getChildren().add(ground);

        Tree t1 = new Tree (100, 420);
        painting.getChildren().addAll(t1.getAllNodes());

        Tree t2 = new Tree(200, 440, 100, 100, Color.rgb(100, 100, 80));
        painting.getChildren().addAll(t2.getAllNodes());

        Tree t3 = new Tree(250, 440, 100, 250, Color.rgb(120, 120, 10));
        //painting.getChildren().addAll(t3.getAllNodes());

        //group object of tree
        tree = new Group();
        tree.getChildren().addAll(t3.getAllNodes());
        painting.getChildren().add(tree);


        Animal mouse = new Animal(500,435);

        //group object of animal
        animal = new Group();
        animal.getChildren().addAll(mouse.getAllNodes());
        painting.getChildren().add(animal);

        //painting.getChildren().addAll(mouse.getAllNodes());
        drawRainbow(painting);


        //set the painting pain in the center of the root
        root.setCenter(painting);
        root.setAlignment(painting, Pos.CENTER_RIGHT);


        //text message
        background = new Text(20,80,"Background");
        background.setFont(Font.font(12));

        //create the First VBox
        VBox vBoxOne = new VBox(10,background);
        vBoxOne.setAlignment(Pos.CENTER);

        //bottom close
        closeButton = new Button("Close");
        //put the button at bottom right.
        root.setBottom(closeButton);
        root.setAlignment(closeButton, Pos.BOTTOM_RIGHT);

        //add an event handler to the close butoon to close the primaryStage
        closeButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle (ActionEvent e){
                primaryStage.close();
            }
        });

        //create checkboxes
        treeCheckBox = new CheckBox("tree");
        animalCheckBox = new CheckBox("animal");

        //create an object of HiddenObjectsEventHandler()
        HideAndRotateAnimalEventHandler hiddenAndRotateAnimal = new HideAndRotateAnimalEventHandler();
        animalCheckBox.setOnAction(hiddenAndRotateAnimal);

        HideAndRotateTreeEventHandler hiddenAndRotateTree= new HideAndRotateTreeEventHandler();
        treeCheckBox.setOnAction(hiddenAndRotateTree);

        //add CheckBoxes to a HBox
        HBox hBox = new HBox(10, treeCheckBox, animalCheckBox);
        //set hBox at the left side of the root
        root.setLeft(hBox);


        //create radioButtons
        zeroDegree = new RadioButton("0 degree");
        ninetyDegree = new RadioButton("90 degree");
        oneHundredEightyDegree = new RadioButton("180 degree");
        twoHundredSeventyDegree = new RadioButton("270 degree");

        //group radio buttons
        rotate = new ToggleGroup();
        zeroDegree.setToggleGroup(rotate);
        ninetyDegree.setToggleGroup(rotate);
        oneHundredEightyDegree.setToggleGroup(rotate);
        twoHundredSeventyDegree.setToggleGroup(rotate);
        //put default on zeroDegree
        rotate.selectToggle(zeroDegree);


        //create the second VBox
        VBox vBoxTwo = new VBox(10,zeroDegree, ninetyDegree,
                oneHundredEightyDegree, twoHundredSeventyDegree);

        //add margin for the Vbox of radio buttons
        VBox.setMargin(vBoxTwo, new Insets(10,0,10,0));

        caption = new Text(30,90,"Caption");

        //create text field
        textField = new TextField("Caption");
        textField.prefHeight(10);
        textField.prefWidth(150);


        // create change Text button
        changeText = new Button("Change text");
        //add listener
        changeText.setOnAction(new UpdateTextEventHandler());

        //create the third VBox
        VBox vBoxThree = new VBox(10,caption,textField,changeText);
        vBoxThree.setAlignment(Pos.CENTER);


        //create the main VBox
        VBox mainVBox = new VBox(10, vBoxOne,hBox,vBoxTwo,vBoxThree);



        //set vBox in the left
        root.setLeft(mainVBox);

        root.setPadding(new Insets(10, 0,0,15));
        Scene scene = new Scene(root);
        primaryStage.setTitle("JavaFX Trees");
        primaryStage.setScene(scene);
        primaryStage.show();


    }// end of main method

    /**
     * drawRainbow method that draw a rainbow
     * @param root is an object of the Pane
     */
    public static void drawRainbow(Pane root) {

        double gap = 7;

        //an array of colors of the rainbow
        Color[] rainbowColors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.BLUE, Color.INDIGO, Color.PURPLE};

        //a for loop to draw of arc of each color of rainbow and add it to the root
        for (int i = 0; i < rainbowColors.length; i++) {
            Arc a = new Arc(300, 350 + (i * gap), 320, 230, 0, 180);
            a.setStroke(rainbowColors[i]);
            a.setStrokeWidth(gap + 2);
            a.setFill(Color.TRANSPARENT);
            root.getChildren().add(a);

        }

    }

    // write a class for text EventHandler
    private class UpdateTextEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {

            backgroundText = new Text (200, 480,"");

            //set font, color, and size of the text
            FontWeight ft = FontWeight.BOLD;
            backgroundText.setFont(Font.font("Arial",ft,40));
            backgroundText.setFill(Color.WHITE);
            backgroundText.setText(textField.getText());

            //add background text to the painting Pane
            painting.getChildren().add(backgroundText);
        }

    }


    // write a class for hide and rotate animal EventHandler
    private class HideAndRotateAnimalEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (animalCheckBox.isSelected()) {
                animal.setVisible(true);
                if(zeroDegree.isSelected()){
                    animal.setRotate(0);
                }
                if(ninetyDegree.isSelected()){
                    animal.setRotate(90);
                }
                if(oneHundredEightyDegree.isSelected()){
                    animal.setRotate(180);
                }
                if(twoHundredSeventyDegree.isSelected()){
                    animal.setRotate(270);
                }
            } else {
                animal.setVisible(false);
            }
        }
    }

    // write a class for hide and rotate the tree EventHandler
    private class HideAndRotateTreeEventHandler implements EventHandler<ActionEvent> {
        @Override
        public void handle(ActionEvent e) {
            if (treeCheckBox.isSelected()) {
                tree.setVisible(true);
                if(zeroDegree.isSelected()){
                    tree.setRotate(0);
                }
                if(ninetyDegree.isSelected()){
                    tree.setRotate(90);
                }
                if(oneHundredEightyDegree.isSelected()){
                    tree.setRotate(180);
                }
                if(twoHundredSeventyDegree.isSelected()){
                    tree.setRotate(270);
                }
            } else {
                tree.setVisible(false);
            }
        }
    }
}


