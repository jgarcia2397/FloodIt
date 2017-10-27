import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.*;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;

/**
 * The class <b>GameView</b> provides the current view of the entire Game. It extends
 * <b>JFrame</b> and lays out the actual game and 
 * two instances of JButton. The action listener for the buttons is the controller.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */

public class GameView extends JFrame {

    private GameModelOne model;
    private GameController controller;
    private DotButton button;
    private DotButton[][] game;

    /**
     * Constructor used for initializing the Frame
     * 
     * @param model
     *            the model of the game (already initialized)
     * @param gameController
     *            the controller
     */

    public GameView(GameModelOne model, GameController controller) {

        this.model = model;
        this.controller = controller;

        JFrame frame = new JFrame();
        frame.setTitle("Flood It");

        JPanel panelThree = new JPanel();
        panelThree.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        JPanel panelOne = new JPanel();
        JPanel panelTwo = new JPanel();

        JButton reset = new JButton();
        reset.add(new Label("Reset"));
        reset.setText("Reset");
        reset.addActionListener(controller);
        panelOne.add(reset);

        JButton quit = new JButton();
        quit.add(new Label("Quit"));
        quit.setText("Quit");
        quit.addActionListener(controller);
        panelOne.add(quit);

        DotButton grey = new DotButton(0,0);
        grey.setText("grey");
        grey.addActionListener(controller);
        panelTwo.add(grey);

        DotButton orange = new DotButton(1,0);
        orange.setText("orange");
        orange.addActionListener(controller);
        panelTwo.add(orange);

        DotButton blue = new DotButton(2,0);
        blue.setText("blue");
        blue.addActionListener(controller);
        panelTwo.add(blue);

        DotButton green = new DotButton(3,0);
        green.setText("green");
        green.addActionListener(controller);
        panelTwo.add(green);

        DotButton purple = new DotButton(4,0);
        purple.setText("purple");
        purple.addActionListener(controller);
        panelTwo.add(purple);

        DotButton red = new DotButton(5,0);
        red.setText("red");
        red.addActionListener(controller);
        panelTwo.add(red);

        panelThree.add(panelTwo,gbc);
        panelThree.add(panelOne,gbc);
        frame.add(panelThree,BorderLayout.SOUTH);


        JPanel panelFour = new JPanel();
        panelFour.setLayout(new GridLayout(model.getSize(),model.getSize()));
        game = new DotButton[model.getSize()][model.getSize()];

        for(int row = 0; row < model.getSize(); row++) {
            for(int column = 0; column < model.getSize(); column++) {
                game[row][column] = new DotButton(row,column,model.getColor(row,column),0);
                panelFour.add(game[row][column]);
            }
        }
        frame.add(panelFour,BorderLayout.CENTER);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 600);

        frame.setVisible(true);
    }

    /**
     * update the status of the board's DotButton instances based on the current game model
     */

    public void update(){
        for(int i = 0; i < model.getSize(); i++) {
            for(int j = 0; j < model.getSize(); j++) {
                if(model.get(i,j).isCaptured() == true) {
                    game[i][j].setColor(model.getCurrentSelectedColor());
                }
            }
        }
    }

    public DotButton buttonIcon(int i, int j){
        return(game[i][j]);
    }
}