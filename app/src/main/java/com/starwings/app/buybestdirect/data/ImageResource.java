package com.starwings.app.buybestdirect.data;

import java.io.Serializable;

public class ImageResource  implements Serializable {


    private int slno;

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

    public String getImgPath() {
        return imgPath;
    }

    public void setImgPath(String imgPath) {
        this.imgPath = imgPath;
    }

    private int adID;
    private String imgPath;
}
