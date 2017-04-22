package com.zw.kedamahelper.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by ZW on 2017/4/22.
 */

public class Data {
    @SerializedName("_seen")
    private String lastSee;
    @SerializedName("time_start")
    private long timeStart;
    @SerializedName("time_last")
    private long timeLast;
    @SerializedName("time_lived")
    private long timeLived;
    private String uuid;
    @SerializedName("uuid_short")
    private String uuidShort;
    @SerializedName("player_name")
    private String playerName;
    private ArrayList<Name> names;
    private boolean banned;

    public String getLastSee() {
        return lastSee;
    }

    public void setLastSee(String lastSee) {
        this.lastSee = lastSee;
    }

    public long getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(long timeStart) {
        this.timeStart = timeStart;
    }

    public long getTimeLast() {
        return timeLast;
    }

    public void setTimeLast(long timeLast) {
        this.timeLast = timeLast;
    }

    public long getTimeLived() {
        return timeLived;
    }

    public void setTimeLived(long timeLived) {
        this.timeLived = timeLived;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getUuidShort() {
        return uuidShort;
    }

    public void setUuidShort(String uuidShort) {
        this.uuidShort = uuidShort;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public ArrayList<Name> getNames() {
        return names;
    }

    public void setNames(ArrayList<Name> names) {
        this.names = names;
    }

    public boolean isBanned() {
        return banned;
    }

    public void setBanned(boolean banned) {
        this.banned = banned;
    }
}
