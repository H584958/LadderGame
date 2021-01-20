package boardGame;
/***
 * Represents a Player
 * @author Simon Kobbenes, Matias Vedeler, Christian Evensen
 */
public class Player {
    private int position;
    private String id;

    /***
     * Creates a player with a specified id.
     * @param id The id of the player
     */
    public Player(String id) {
        this.id = id;
        position = -1;
    }

    /***
     * Gets the players Id
     * @return A String representing the player's Id
     */
    public String getId() {
        return id;
    }

    /***
     * Sets the players Id
     * @param id A String containing the player's Id
     */
    public void setId(String id) {
        this.id = id;
    }
    /***
     * Gets the players position
     * @return An Integer representing the player's position
     */
    public int getPosition() {
        return position;
    }
    /***
     * Sets the players Id
     * @param position An Integer containing the player's new position
     */
    public void setPosition(int position) {
        this.position = position;
    }

}
