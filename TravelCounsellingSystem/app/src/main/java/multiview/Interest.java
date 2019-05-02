package multiview;

import android.graphics.Bitmap;

public class Interest {

    private String name;
    private String cityName;
    private Bitmap image;

    public Interest(String name, Bitmap image, String cityName) {
        this.name = name;
        this.image = image;
        this.cityName = cityName;
    }

    public String getName() {
        return name;
    }
    public String getCityName() {
        return cityName;
    }

    public Bitmap getImageId() {
        return image;
    }

}