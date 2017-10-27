import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
/**
 * The class <b>GameController</b> is the controller of the game. It has a method
 * <b>selectColor</b> which is called by the view when the player selects the next
 * color. It then computesthe next step of the game, and  updates model and view.
 *
 * @author Guy-Vincent Jourdan, University of Ottawa
 */


public class GameController implements ActionListener {

    private GameModelOne gameModel;
    private GameView gameView;

    /**
     * Constructor used for initializing the controller. It creates the game's view 
     * and the game's model instances
     * 
     * @param size
     *            the size of the board on which the game will be played
     */
    public GameController(int size) {
        gameModel = new GameModelOne(size);
        gameView = new GameView(gameModel,this);
        captureDots(gameModel.getCurrentSelectedColor());
        gameView.update();
        while (! gameModel.isFinished()) {
             selectColor(gameModel.getCurrentSelectedColor());
             gameView.update();
         }
    }

    /**
     * resets the game
     */
    public void reset(){
        gameModel.reset();
    }

    /**
     * Callback used when the user clicks a button (reset or quit)
     *
     * @param e
     *            the ActionEvent
     */

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() instanceof DotButton) {
            DotButton click = (DotButton)(e.getSource());
            if(click.getText().equals("grey")) {
                selectColor(0);
                gameView.update();
                
            } else if(click.getText().equals("orange")) {
                selectColor(1);
                gameView.update();
                
            } else if(click.getText().equals("blue")) {
                selectColor(2);
                gameView.update();
                
            } else if(click.getText().equals("green")) {
                selectColor(3);
                gameView.update();
                
            } else if(click.getText().equals("purple")) {
                selectColor(4);
                gameView.update();
                
            } else if(click.getText().equals("red")) {
                selectColor(5);
                gameView.update();
                
            }
        } else if (e.getSource() instanceof JButton) {
            JButton click = (JButton)(e.getSource());
            if(click.getText().equals("Quit")) {
                System.exit(0);
            } else if(click.getText().equals("Reset")) {
                reset();
                gameView.update();
            }
        } 
    }
    

    /**
     * <b>selectColor</b> is the method called when the user selects a new color.
     * If that color is not the currently selected one, then it applies the logic
     * of the game to capture possible locations. It then checks if the game
     * is finished, and if so, congratulates the player, showing the number of
     * moves, and gives two options: start a new game, or exit
     * @param color
     *            the newly selected color
     */
    public void selectColor(int color){
        if (color != gameModel.getCurrentSelectedColor()) {
            gameModel.setCurrentSelectedColor(color);
            gameModel.step();
            captureDots(color);
            if (gameModel.isFinished()) {
                System.out.println("The game is over. You Win!");
            }
        }
    }

    private void captureDots(int selectedColor) {
        boolean flag = false;
        DotInfo d;
        int sizeOne = gameModel.getSize();
        int sizeTwo = gameModel.getSize();
        MyStack<DotInfo> myStack = new MyStack<DotInfo>(sizeTwo*sizeTwo);
        for (int i = 0; i < sizeTwo; i++) {
            for (int j = 0; j < sizeTwo; j++) {
                if (gameModel.isCaptured(i,j)) {
                    myStack.push(gameModel.get(i,j));
                }
            }
        }
        while (!myStack.isEmpty()) {
            DotInfo dot = (DotInfo) myStack.pop();
            int x = dot.getX();
            int y = dot.getY();

            if (y != sizeOne - 1) {
                flag = true;
                d = gameModel.get(x, y + 1);
                if (!(d.isCaptured()) && d.getColor() == selectedColor) {
                    gameModel.capture(d.getX(), d.getY());
                    myStack.push(d);
                }
                if (x != sizeOne - 1) {
                    d = gameModel.get(x + 1,y);
                    if (!(d.isCaptured()) && d.getColor() == selectedColor) {
                        gameModel.capture(d.getX(), d.getY());
                        myStack.push(d);
                    }
                } 
                if (x != 0) {
                    d = gameModel.get(x - 1,y);
                    if (!(d.isCaptured()) && d.getColor() == selectedColor) {
                        gameModel.capture(d.getX(), d.getY());
                        myStack.push(d);
                    }
                } 
            }
            if (y != 0) {
                d = gameModel.get(x,y - 1);
                if (!(d.isCaptured()) && d.getColor() == selectedColor) {
                    gameModel.capture(d.getX(), d.getY());
                    myStack.push(d);
                }
                if (x != sizeOne - 1 && flag == false) {
                    d = gameModel.get(x + 1,y);
                    if (!(d.isCaptured()) && d.getColor() == selectedColor) {
                        gameModel.capture(d.getX(), d.getY());
                        myStack.push(d);
                    }
                } 
                if (x != 0 && flag == false) {

                    d = gameModel.get(x - 1,y);
                    if (!(d.isCaptured()) && d.getColor() == selectedColor) {
                        gameModel.capture(d.getX(), d.getY());
                        myStack.push(d);
                    }
                }  
            }
            flag = false;
        }   
    }
}