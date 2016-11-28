package spacebeatzgame;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

/**
 *About Hud Class
 * 
 * 
 */
public class Hud {

    /**
     * hudRect represents the heads up display of the game and contains
     * pertinent game statistics
     */
    private Pane hudPane;

    /**
     * Hud Color
     */
    private final Color TEXT_COLOR = Color.WHITE;

    /**
     * Holds elapsed time of game
     */
    private Label etLabel = new Label();

    /**
     * Holds current game score
     */
    private Label scoreLabel= new Label();
    
    /**
     * Holds current game score
     */
    private Label collisionLabel= new Label();

    /**
     * Holds String for Elapsed Time
     */
    private final String ET_STRING = "Elapsed Time: ";

    /**
     * Holds String for Score
     */
    private final String SCORE_STRING = "Score: ";
    
    /**
     * Holds String collisions
     */
    private final String COLLISION_STRING = "Collisions: ";

    /**
     * Holds game font
     */
    private final Font GAME_FONT = Font.font("Agency FB Bold", FontWeight.BOLD, 24);

    /**
     * Holds statistic for hud
     */
    private HBox timeBox = new HBox();

    /**
     * Holds statistic for hud
     */
    private HBox scoreBox = new HBox();

    /**
     * Holds statistic for hud
     */
    private HBox collisionBox = new HBox();
    
    /**
     * Holds statistic for hud
     */
    private HBox statBox = new HBox(150);

    /**
     * Hud x position.
     */
    protected double positionX;

    /**
     * Hud y position.
     */
    protected double positionY;
    
    /**
     * Current Score
     */
    int currentScore = 0;
    
    /**
     * Constructor for Hud
     */
    public Hud() {

        //Set Attributes of the hud
        hudPane = new Pane();

        //Set Attributes of labels
        etLabel.setFont(GAME_FONT);
        etLabel.setTextFill(TEXT_COLOR);
        etLabel.setText(ET_STRING + "--:--");
        timeBox.getChildren().add(etLabel);
         
        scoreLabel.setFont(GAME_FONT);
        scoreLabel.setTextFill(TEXT_COLOR);
        scoreLabel.setText(SCORE_STRING + "0");
        scoreBox.getChildren().add(scoreLabel);
        
        collisionLabel.setFont(GAME_FONT);
        collisionLabel.setTextFill(TEXT_COLOR);
        collisionLabel.setText(COLLISION_STRING + "0");
        collisionBox.getChildren().add(collisionLabel);
        
        

        //add children to statBox
        statBox.getChildren().addAll(timeBox,collisionBox,scoreBox);
        statBox.setFocusTraversable(false);
        statBox.setPadding(new Insets(5,0,0,20));
        
        hudPane.getChildren().add(statBox);
    }

    /**
     * Sets position of hud.
     *
     * @param xPos x coordinate position
     * @param yPos y coordinate position
     */
    public void setHudPos(double xPos, double yPos) {
        hudPane.setLayoutX(xPos);
        hudPane.setLayoutY(yPos);
    }

    /**
     * Get the hud
     *
     * @return the rectangle node containing formated hud.
     */
    public Pane getHud() {
        return hudPane;
    }
    /**
     * updates the hud statistics and scores the player
     * 
     * @param collisions number of collisions
     * @param player mediaPlayer
     */
    public void updateHud(int collisions, MediaPlayer player) {

        //Get minutes and seconds from player
        int minutes = (int) (player.getCurrentTime().toMillis() / 1000) / 60;
        int seconds = (int) (player.getCurrentTime().toMillis() / 1000) % 60;

        String eTime = String.format("%02d:%02d", minutes, seconds);

        etLabel.setText(ET_STRING + eTime);
        collisionLabel.setText(COLLISION_STRING + collisions);

        //calculate Score
        //always get 1 point per second
        if (seconds > 0 && minutes < 1) {
            currentScore = seconds * 5;
        }

        //get extra point based of minutes passed
        switch (minutes) {
            case 1:
                currentScore += 1000;
                break;
            case 2:
                currentScore += 2000;
                break;
            case 3:
                currentScore += seconds * 3000;
                break;
            default:
                currentScore += 0;
                break;
        }

        scoreLabel.setText(SCORE_STRING + currentScore);

    }


}