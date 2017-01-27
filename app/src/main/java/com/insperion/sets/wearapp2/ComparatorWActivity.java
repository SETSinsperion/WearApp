/*

    ImageAnalysis is part of WearApp.

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

package com.insperion.sets.wearapp2;

import android.content.res.Resources;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class ComparatorWActivity extends AppCompatActivity {

    private TextView m1, m1c1, m1c2, m1c3, m2, m2c1, m2c2, m2c3, m3, m3c1, m3c2, m3c3,
                     combi1, combi2, combi3;
    private Resources resources;

    private int[] mix_1, mix_2, mix_3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_comparator_w);

        // Primary variables
        resources = getResources();

        // Getting comparator
        Bundle bundle = getIntent().getExtras();
        mix_1 = bundle.getIntArray("comparator_c1");
        mix_2 = bundle.getIntArray("comparator_c2");
        mix_3 = bundle.getIntArray("comparator_c3");

        // GUI Binding
        combi1 = (TextView)findViewById(R.id.m1);
        m1 = (TextView)findViewById(R.id.ms1);
        m1c1 = (TextView)findViewById(R.id.mycolor_c11);
        m1c2 = (TextView)findViewById(R.id.mycolor_c12);
        m1c3 = (TextView)findViewById(R.id.mycolor_c13);
        combi2 = (TextView)findViewById(R.id.m2);
        m2 = (TextView)findViewById(R.id.ms2);
        m2c1 = (TextView)findViewById(R.id.mycolor_c21);
        m2c2 = (TextView)findViewById(R.id.mycolor_c22);
        m2c3 = (TextView)findViewById(R.id.mycolor_c23);
        combi3 = (TextView)findViewById(R.id.m3);
        m3 = (TextView)findViewById(R.id.ms3);
        m3c1 = (TextView)findViewById(R.id.mycolor_c31);
        m3c2 = (TextView)findViewById(R.id.mycolor_c32);
        m3c3 = (TextView)findViewById(R.id.mycolor_c33);

        set_ColorsComparator();

    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {

        super.onSaveInstanceState(outState);
        if (mix_1 != null)
            outState.putIntArray("combi1", mix_1);
        if (mix_2 != null)
            outState.putIntArray("combi2", mix_2);
        if (mix_3 != null)
            outState.putIntArray("combi3", mix_3);

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);
        mix_1 = savedInstanceState.getIntArray("combi1");
        mix_2 = savedInstanceState.getIntArray("combi2");
        mix_3 = savedInstanceState.getIntArray("combi3");

        if (mix_1 != null) {
            m1.setBackgroundColor(mix_1[0]);
            m1.setTextColor(mix_1[0]);
            m1c1.setBackgroundColor(mix_1[1]);
            m1c1.setTextColor(mix_1[1]);
            m1c2.setBackgroundColor(mix_1[2]);
            m1c2.setTextColor(mix_1[2]);
            m1c3.setBackgroundColor(mix_1[3]);
            m1c3.setTextColor(mix_1[3]);
            combi1.setText(resources.getString(R.string.tv_mixing_1));
        }
        else
            combi1.setText(String.format("%s %d", resources.getString(R.string.tv_not_combination), 1));

        if (mix_2 != null) {
            m2.setBackgroundColor(mix_2[0]);
            m2.setTextColor(mix_2[0]);
            m2c1.setBackgroundColor(mix_2[1]);
            m2c1.setTextColor(mix_2[1]);
            m2c2.setBackgroundColor(mix_2[2]);
            m2c2.setTextColor(mix_2[2]);
            m2c3.setBackgroundColor(mix_2[3]);
            m2c3.setTextColor(mix_2[3]);
            combi2.setText(resources.getString(R.string.tv_mixing_2));
        }
        else
            combi2.setText(String.format("%s %d", resources.getString(R.string.tv_not_combination), 2));

        if (mix_3 != null) {
            m3.setBackgroundColor(mix_3[0]);
            m3.setTextColor(mix_3[0]);
            m3c1.setBackgroundColor(mix_3[1]);
            m3c1.setTextColor(mix_3[1]);
            m3c2.setBackgroundColor(mix_3[2]);
            m3c2.setTextColor(mix_3[2]);
            m3c3.setBackgroundColor(mix_3[3]);
            m3c3.setTextColor(mix_3[3]);
            combi3.setText(resources.getString(R.string.tv_mixing_3));
        }
        else
            combi3.setText(String.format("%s %d", resources.getString(R.string.tv_not_combination), 3));

    }

    private void set_ColorsComparator() {

        // Setting colors to compare

        // Combination 1
        if (mix_1 != null) {
            m1.setBackgroundColor(mix_1[0]);
            m1.setTextColor(mix_1[0]);
            m1c1.setBackgroundColor(mix_1[1]);
            m1c1.setTextColor(mix_1[1]);
            m1c2.setBackgroundColor(mix_1[2]);
            m1c2.setTextColor(mix_1[2]);
            m1c3.setBackgroundColor(mix_1[3]);
            m1c3.setTextColor(mix_1[3]);
            combi1.setText(resources.getString(R.string.tv_mixing_1));
        }
        else
            combi1.setText(String.format("%s %d", resources.getString(R.string.tv_not_combination), 1));

        // Combination 2
        if (mix_2 != null) {
            m2.setBackgroundColor(mix_2[0]);
            m2.setTextColor(mix_2[0]);
            m2c1.setBackgroundColor(mix_2[1]);
            m2c1.setTextColor(mix_2[1]);
            m2c2.setBackgroundColor(mix_2[2]);
            m2c2.setTextColor(mix_2[2]);
            m2c3.setBackgroundColor(mix_2[3]);
            m2c3.setTextColor(mix_2[3]);
            combi2.setText(resources.getString(R.string.tv_mixing_2));
        }
        else
            combi2.setText(String.format("%s %d", resources.getString(R.string.tv_not_combination), 2));

        // Combination 3
        if (mix_3 != null) {
            m3.setBackgroundColor(mix_3[0]);
            m3.setTextColor(mix_3[0]);
            m3c1.setBackgroundColor(mix_3[1]);
            m3c1.setTextColor(mix_3[1]);
            m3c2.setBackgroundColor(mix_3[2]);
            m3c2.setTextColor(mix_3[2]);
            m3c3.setBackgroundColor(mix_3[3]);
            m3c3.setTextColor(mix_3[3]);
            combi3.setText(resources.getString(R.string.tv_mixing_3));
        }
        else
            combi3.setText(String.format("%s %d", resources.getString(R.string.tv_not_combination), 3));

    }

}

