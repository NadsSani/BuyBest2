package com.starwings.app.buybestdirect.data;

import java.io.Serializable;

public class VideoResource implements Serializable {

    public int getSlno() {
        return slno;
    }

    public void setSlno(int slno) {
        this.slno = slno;
    }

    public int getAdID() {
        return adID;
    }

    public void setAdID(int adID) {
        this.adID = adID;
    }

    public String getVidPath() {
        return vidPath;
    }

    public void setVidPath(String vidPath) {
        this.vidPath = vidPath;
    }

    private int slno;
    private int adID;
            private String vidPath;
}
