package com.example.betonit;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register Model for Cases
        ParseObject.registerSubclass(Case.class);

        // Connect to Parse server
        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("Jd9Mbe2J6LaTOfwhH4N7vV1fbcn5ML3ImH65Me4W")
                // if defined
                .clientKey("CqbgDG6Mc9BOIzsGzFltxxR1nAlSDlk2wzPMVSK5")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }

}
