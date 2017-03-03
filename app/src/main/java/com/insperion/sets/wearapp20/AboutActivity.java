/*

    AboutActivity is part of WearApp.

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
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ScrollView;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton license, website, fab_arrow_about;
    private ScrollView sv_about;
    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if ((getIntent().getFlags() & Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT) != 0) {
            finish();
            return;
        }

        setContentView(R.layout.activity_about);

        resources = getResources();

        // GUI Binding
        license = (FloatingActionButton)findViewById(R.id.fab_about_license);
        website = (FloatingActionButton)findViewById(R.id.fab_about_website);
        fab_arrow_about = (FloatingActionButton)findViewById(R.id.fab_arrow_about);
        sv_about = (ScrollView)findViewById(R.id.sv_about);

        // Interface Binding
        license.setOnClickListener(this);
        website.setOnClickListener(this);
        sv_about.getViewTreeObserver().addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() {
            @Override
            public void onScrollChanged() {
                if (sv_about != null) {
                    if (sv_about.getChildAt(0).getBottom() <=
                            (sv_about.getHeight() + sv_about.getScrollY())) {
                        fab_arrow_about.hide();
                    }
                    else {
                        fab_arrow_about.show();
                    }
                }
            }
        });

    }

    @Override
    public void onClick(View v) {

        Intent browserIntent = null;

        switch (v.getId()) {
            case R.id.fab_about_website:
                browserIntent =
                        new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.insperion.com.mx"));
                break;
            case R.id.fab_about_license:
                browserIntent =
                        new Intent(Intent.ACTION_VIEW, Uri.parse(
                                "https://www.gnu.org/licenses/gpl.html"));
                break;
        }

        startActivity(browserIntent);

    }

}
