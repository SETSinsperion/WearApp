package com.insperion.sets.wearapp2;

import java.io.File;

/**
 * Created by sets on 22/01/17.
 */

public class Struct_Result {

    private int[] colors;
    private File bitmap;

    public Struct_Result(int[] colors, File bitmap) {
        this.colors = colors;
        this.bitmap = bitmap;
    }

    public int[] get_Colors(){
        return this.colors;
    }

    public File get_Bitmap(){
        return this.bitmap;
    }

}
