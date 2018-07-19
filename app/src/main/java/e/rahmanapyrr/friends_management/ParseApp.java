package e.rahmanapyrr.friends_management;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import e.rahmanapyrr.friends_management.models.Friend;
import e.rahmanapyrr.friends_management.models.User;
import e.rahmanapyrr.friends_management.models.User;

public class ParseApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        ParseObject.registerSubclass(Friend.class);
        final Parse.Configuration configuration = new Parse.Configuration.Builder(this)
                .applicationId("giftapp")
                .clientKey("fbuniversity")
                .server("http://fbu-giftapp.herokuapp.com/parse")
                .build();

        Parse.initialize(configuration);
    }

}
