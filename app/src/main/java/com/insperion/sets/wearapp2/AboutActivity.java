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

package com.insperion.sets.wearapp2;

import android.content.Intent;
import android.content.res.Resources;
import android.net.Uri;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {

    private FloatingActionButton license, website;
    private Resources resources;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        resources = getResources();

        // GUI Binding
        license = (FloatingActionButton)findViewById(R.id.fab_about_license);
        website = (FloatingActionButton)findViewById(R.id.fab_about_website);

        // Interface Binding
        license.setOnClickListener(this);
        website.setOnClickListener(this);

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
