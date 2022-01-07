/**
 **  Author:        Mitra Rouhipour
 **  Date:          March 18, 2021
 **  Course:        CPSC 1150
 **  Compiler:       JDK 1.8
 */
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.shape.Ellipse;
import javafx.scene.shape.Rectangle;

public class Tree {
    private Rectangle trunk;
    private Ellipse leaves;
    private Rectangle box;

    /**
     * the first Constructor of Tree
     *
     * @param x an Integer number in x axis
     * @param y an Integer number in y axis
     */
    public Tree(int x, int y){
        trunk = new Rectangle(x-10, y -100, 20, 100);
        trunk.setFill(Color.SADDLEBROWN);

        box = new Rectangle(x, y, 5, 5);

        leaves = new Ellipse(x, y-100, 40, 60);
        leaves.setFill(Color.rgb(30, 120, 80));
    }

     /**
      * the second Constructor of Tree
      * @param x an Integer number in x axis
      * @param y an Integer number in y axis
      * @param width an Integer number in y axis
      * @param height an Integer number in y axis
      * @param color the color
     */
    public Tree(int x, int y,int width, int height, Color color){

        //h is the height * the ratio of trunk height to all the tree
        double h = height * 100/160;

        //trunk of the tree
        trunk = new Rectangle(x- width/4,y - h , width/4, h);
        trunk.setFill(Color.SADDLEBROWN);

        //the box bellow the tree
        box = new Rectangle(x-width/6 , y, 5, 5);

        //the leaves
        leaves = new Ellipse(x - width /6, y-h, width / 2, height * 60/160);
        leaves.setFill(color);
    }
    public Node[] getAllNodes(){
        return new Node[] {trunk, leaves, box};
    }
}

