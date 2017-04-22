package com.zw.kedamahelper.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ZW on 2017/4/22.
 */

public class Server {
    private String update;
    @SerializedName("world_lived")
    private String worldLived;
    private ArrayList<Player> players;

    public void setUpdate(String update) {
        this.update = update;
    }
    public void setWorldLived(String worldLived) {
        this.worldLived = worldLived;
    }
    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public String getUpdate() {

        return update;
    }
    public String getWorldLived() {
        return worldLived;
    }
    public ArrayList<Player> getPlayers() {
        return players;
    }
}
