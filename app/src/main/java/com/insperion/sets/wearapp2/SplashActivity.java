/*

    SplashActivity is part of WearApp.

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
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

public class SplashActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_splash);

        Thread splashThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(3000);
                    Intent main = new Intent(getApplicationContext(), MainActivity.class);
                    startActivity(main);
                    finish();
                }
                catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        splashThread.start();

    }
}
