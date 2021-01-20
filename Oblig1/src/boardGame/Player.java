package boardGame;

public class Player {
    private int position;
    private String id;

    public Player(String id) {
        this.id = id;
        position = -1;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

}
