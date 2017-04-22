package com.zw.kedamahelper.model;

/**
 * Created by ZW on 2017/4/22.
 */

public class Player {
    private Stats stats;
    private Data data;

    public void setStats(Stats stats) {
        this.stats = stats;
    }
    public void setData(Data data) {
        this.data = data;
    }

    public Stats getStats() {

        return stats;
    }
    public Data getData() {
        return data;
    }
}
