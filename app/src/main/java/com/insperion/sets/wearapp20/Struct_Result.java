/*

    Struct_Result is part of WearApp.

    WearApp is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    WearApp is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with Foobar.  If not, see <http://www.gnu.org/licenses/>.

*/

/*
	Developed by: Insperion
	Team: EDS Insperion
	Developer(s):
	    ISC. Sergio Ernesto Tostado Sánchez
	Contact: contacto@insperion.com.mx
*/

package com.insperion.sets.wearapp20;

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
