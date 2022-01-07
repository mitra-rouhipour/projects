/**
 **  Author:        Mitra Rouhipour
 **  Date:          March 18, 2021
 **  Course:        CPSC 1150
 **  Compiler:       JDK 1.8
 */
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.ArcType;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Arc;
import javafx.scene.shape.Rectangle;

public class Animal {
    private Ellipse body;
    private Ellipse leftEar;
    private Ellipse rightEar;
    private Ellipse leftEye;
    private Ellipse rightEye;
    private Ellipse nose;
    private Ellipse leftFoot;
    private Ellipse rightFoot;
    private Arc leftHand;
    private Arc rightHand;
    private Arc lip;
    private Arc tail;
    private Ellipse innerLeftEar;
    private Ellipse innerRightEar;
    private Ellipse innerBody;
    private Rectangle leftTooth;
    private Rectangle rightTooth;

    /**
     * the first Constructor of Animal
     *
     * @param x an Integer number in x axis
     * @param y an Integer number in y axis
     */
    public Animal(int x, int y) {
        //the body of the mouse
        body = new Ellipse(x, y - 40, 36, 40);
        body.setFill(Color.GRAY);

        //the lip of the mouse
        lip = new Arc(x, y - 55, 15, 15, 210, 115);
        lip.setStrokeWidth(2);
        lip.setFill(Color.TRANSPARENT);
        lip.setStroke(Color.RED);

        //the nose of the mouse
        nose = new Ellipse(x, y - 50, 5, 3);
        nose.setFill(Color.PINK);

        //the left eype of the mouse
        leftEye = new Ellipse(x - 10, y - 60, 4, 7);
        leftEye.setFill(Color.BLUE);

        //the right eye of the mouse
        rightEye = new Ellipse(x + 10, y - 60, 4, 7);
        rightEye.setFill(Color.BLUE);

        //the inner ellipse in the body of the mouse
        innerBody = new Ellipse(x, y - 20, 11, 13);
        innerBody.setFill(Color.BLUEVIOLET);

        //the tail of the mouse
        tail = new Arc(x + 32, y, 15, 15, -20, 130);
        tail.setStrokeWidth(4);
        tail.setFill(Color.TRANSPARENT);
        tail.setStroke(Color.GRAY);

        //the left foot of the mouse
        leftFoot = new Ellipse(x - 12, y, 5, 5);
        leftFoot.setFill(Color.GRAY);

        //the right foot of the mouse
        rightFoot = new Ellipse(x + 12, y, 5, 5);
        rightFoot.setFill(Color.GRAY);

        //the left hand of the mouse
        leftHand = new Arc(x - 36, y - 34, 7, 7, 90, 360);
        leftHand.setType(ArcType.CHORD);
        leftHand.setFill(Color.GRAY);

        //the right hand of the mouse
        rightHand = new Arc(x + 36, y - 34, 7, 7, 90, 360);
        rightHand.setType(ArcType.CHORD);
        rightHand.setFill(Color.GRAY);

        //the left ear of the mouse
        leftEar = new Ellipse(x - 20, y - 85, 15, 17);
        leftEar.setFill(Color.GRAY);

        //the right ear of the mouse
        rightEar = new Ellipse(x + 20, y - 85, 15, 17);
        rightEar.setFill(Color.GRAY);

        //the inner ellipse in the left ear
        innerLeftEar = new Ellipse(x - 20, y - 85, 5, 5);
        innerLeftEar.setFill(Color.CORAL);

        //the inner ellipse in the right ear
        innerRightEar = new Ellipse(x + 20, y - 85, 5, 5);
        innerRightEar.setFill(Color.CORAL);

        //the left tooth of the mouse
        leftTooth = new Rectangle(x- 7,y- 40 , 5,5);
        leftTooth.setFill(Color.WHITE);

        //the right tooth of the mouse
        rightTooth = new Rectangle(x +2 ,y- 40 , 5,5);
        rightTooth.setFill(Color.WHITE);


    }

    /**
     * a method to add all nodes
     *
     * @return node[] an array of all nodes
     */
        public Node[] getAllNodes(){
            return new Node[] {body, innerBody, lip, nose, leftEye, rightEye,
                    tail,leftFoot, rightFoot, leftHand, rightHand,leftEar,
                    rightEar, innerLeftEar, innerRightEar, leftTooth,rightTooth };
        }

}
