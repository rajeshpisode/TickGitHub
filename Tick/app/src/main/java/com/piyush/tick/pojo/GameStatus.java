package com.piyush.tick.pojo;

/**
 * Created by hp on 8/3/2015.
 */
public class GameStatus {
    private int position;
    private boolean status;

    public GameStatus(int position, boolean status) {
        this.position = position;
        this.status = status;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
}
