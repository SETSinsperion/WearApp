package com.insperion.sets.wearapp2;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Environment;
import android.util.Log;

import java.io.File;

/**
 * Created by sets on 23/01/17.
 */

public class ImageAnalysis {

    public ImageAnalysis() {

    }

    public Struct_Result get_AverageColor() {

        final String path_to_ap =
                Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES) +
                        "/WearApp/AFINITY_PHOTO.png";
        final int qt_pixel = 90000, area_half = 150;

        // RGB are 3
        int[] colors = new int[3];
        int sr=0, sg=0, sb=0;
        Bitmap bm = BitmapFactory.decodeFile(path_to_ap);
        File file_ap = new File(path_to_ap);

        // Delimiting the analysis square
        int w = bm.getWidth(), h = bm.getHeight();
        int h1 = ((h/2) - area_half), h2 = ((h/2) + area_half),
                w1 = ((w/2) - area_half), w2 = ((w/2) + area_half);
        Log.d("Dimens: ",w+" X "+h);Log.d("P:: ",h1+"-"+h2+"-"+w1+"-"+w2);
        // Getting average of color's components
        for(int i=w1; i<w2; i++){
            for(int j=h1; j<h2; j++){
                sr += Color.red(bm.getPixel(i, j));
                sg += Color.green(bm.getPixel(i, j));
                sb += Color.blue(bm.getPixel(i, j));
            }
        }

        // Getting the average RGB
        colors[0] = sr/qt_pixel;
        colors[1] = sg/qt_pixel;
        colors[2] = sb/qt_pixel;

        return new Struct_Result(colors, file_ap);

    }

    // Method to adjust a vector inside a toroidal solution space
    public int get_ToroidalAdjustment(int color){

        // RGB range
        int lower_limit = -16777216, upper_limit = -1, adjusted_color = color;

        if (color > upper_limit)
            adjusted_color = lower_limit + Math.abs((color % (upper_limit - lower_limit)));
        else if (color < lower_limit)
            adjusted_color = upper_limit - Math.abs((color % (upper_limit - lower_limit)));

        return adjusted_color;

    }

}
