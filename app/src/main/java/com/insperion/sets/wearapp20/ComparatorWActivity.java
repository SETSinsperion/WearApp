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

package com.insperion.sets.wearapp20;

import android.content.Intent;
import android.content.res.Resources;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import java.util.ArrayList;

public class ComparatorWActivity extends AppCompatActivity implements View.OnTouchListener {

    private ScrollView sv_comparator;
    private FloatingActionButton fab_arrow, fab_arrow_tooltip;
    private TextView m1, m1c1, m1c2, m1c3, m1b1, m1w1, m1g1, m2, m2c1, m2c2, m2c3, m2b1, m2w1, m2g1,
            m3, m3c1, m3c2, m3c3, m3b1, m3w1, m3g1, combi1, combi2, combi3, sample1, sample2,
            tooltip1;
    private ArrayList<TextView> array_m;
    private Resources resources;

    private int[] mix_1, mix_2, mix_3;
    private boolean is_needed_tooltip;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        setContentView(R.layout.activity_comparator_w);

        // Primary variables
        resources = getResources();
        is_needed_tooltip = true;

        // Getting comparator
        Bundle bundle = getIntent().getExtras();
        mix_1 = bundle.getIntArray("comparator_c1");
        mix_2 = bundle.getIntArray("comparator_c2");
        mix_3 = bundle.getIntArray("comparator_c3");

        // GUI Binding
        tooltip1 = (TextView)findViewById(R.id.tooltip1_comparator);
        fab_arrow = (FloatingActionButton)findViewById(R.id.fab_arrow_comparator);
        fab_arrow_tooltip = (FloatingActionButton)findViewById(R.id.fab_arrow_tooltip_comparator);
        sv_comparator = (ScrollView)findViewById(R.id.sv_comparator);
        combi1 = (TextView)findViewById(R.id.m1);
        m1 = (TextView)findViewById(R.id.ms1);
        m1c1 = (TextView)findViewById(R.id.mycolor_c11);
        m1c2 = (TextView)findViewById(R.id.mycolor_c12);
        m1c3 = (TextView)findViewById(R.id.mycolor_c13);
        m1b1 = (TextView)findViewById(R.id.mycolor_cb1);
        m1w1 = (TextView)findViewById(R.id.mycolor_cw1);
        m1g1 = (TextView)findViewById(R.id.mycolor_cg1);
        combi2 = (TextView)findViewById(R.id.m2);
        m2 = (TextView)findViewById(R.id.ms2);
        m2c1 = (TextView)findViewById(R.id.mycolor_c21);
        m2c2 = (TextView)findViewById(R.id.mycolor_c22);
        m2c3 = (TextView)findViewById(R.id.mycolor_c23);
        m2b1= (TextView)findViewById(R.id.mycolor_cb2);
        m2w1 = (TextView)findViewById(R.id.mycolor_cw2);
        m2g1 = (TextView)findViewById(R.id.mycolor_cg2);
        combi3 = (TextView)findViewById(R.id.m3);
        m3 = (TextView)findViewById(R.id.ms3);
        m3c1 = (TextView)findViewById(R.id.mycolor_c31);
        m3c2 = (TextView)findViewById(R.id.mycolor_c32);
        m3c3 = (TextView)findViewById(R.id.mycolor_c33);
        m3b1 = (TextView)findViewById(R.id.mycolor_cb3);
        m3w1 = (TextView)findViewById(R.id.mycolor_cw3);
        m3g1 = (TextView)findViewById(R.id.mycolor_cg3);
        sample1 = (TextView)findViewById(R.id.palette1);
        sample2 = (TextView)findViewById(R.id.palette2);

        set_ColorsComparator();
        set_ArrayEasyM();

        for(int i=0; i<array_m.size(); i++)
            array_m.get(i).setOnTouchListener(this);

        // Interface Binding
        sv_comparator.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (sv_comparator != null) {
                    if (sv_comparator.getChildAt(0).getBottom() <=
                            (sv_comparator.getHeight() + sv_comparator.getScrollY())) {
                        fab_arrow.hide();
                    }
                    else {
                        fab_arrow.show();
                    }
                }
            }
        });

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

        outState.putInt("sample1", sample1.getCurrentTextColor());
        outState.putInt("sample2", sample2.getCurrentTextColor());

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

        sample1.setTextColor(savedInstanceState.getInt("sample1"));
        sample1.setBackgroundColor(savedInstanceState.getInt("sample1"));
        sample2.setTextColor(savedInstanceState.getInt("sample2"));
        sample2.setBackgroundColor(savedInstanceState.getInt("sample2"));

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

    private void set_ArrayEasyM(){

        array_m = new ArrayList<>();

        array_m.add(m1c1);
        array_m.add(m1c2);
        array_m.add(m1c3);
        array_m.add(m1b1);
        array_m.add(m1w1);
        array_m.add(m1g1);
        array_m.add(m2c1);
        array_m.add(m2c2);
        array_m.add(m2c3);
        array_m.add(m2b1);
        array_m.add(m2w1);
        array_m.add(m2g1);
        array_m.add(m3c1);
        array_m.add(m3c2);
        array_m.add(m3c3);
        array_m.add(m3b1);
        array_m.add(m3w1);
        array_m.add(m3g1);

    }

    private void set_TooltipOut() {
        fab_arrow_tooltip.hide();
        tooltip1.setVisibility(View.GONE);
        is_needed_tooltip = false;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        TextView tv_sample_extractor = (TextView)v;
        sample2.setTextColor(tv_sample_extractor.getCurrentTextColor());
        sample2.setBackgroundColor(tv_sample_extractor.getCurrentTextColor());

        LinearLayout hsv = (LinearLayout) tv_sample_extractor.getParent();

        if  (hsv.getId() == R.id.ll_1) {
            sample1.setTextColor(m1.getCurrentTextColor());
            sample1.setBackgroundColor(m1.getCurrentTextColor());
            set_TooltipOut();
        }
        else if (hsv.getId() == R.id.ll_2) {
            sample1.setTextColor(m2.getCurrentTextColor());
            sample1.setBackgroundColor(m2.getCurrentTextColor());
            set_TooltipOut();
        }
        else {
            sample1.setTextColor(m3.getCurrentTextColor());
            sample1.setBackgroundColor(m3.getCurrentTextColor());
            set_TooltipOut();
        }

        return false;

    }
}

