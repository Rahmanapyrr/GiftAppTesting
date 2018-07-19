package e.rahmanapyrr.friends_management.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@ParseClassName("User")
public class User extends ParseObject {
    public static final String KEY_NAME = "username";
    public static final String KEY_IMAGE = "image";
    public static final String KEY_USER = "user";

    public ParseFile getUsername(){
        return getParseFile(KEY_NAME);
    }

    public void setUsername(ParseFile username){
        put(KEY_NAME, username);
    }

    public ParseFile getImage(){
        return getParseFile(KEY_IMAGE);
    }

    public void setImage(ParseFile image){
        put(KEY_IMAGE, image);
    }

    public ParseUser getUser(){
        return getParseUser(KEY_USER);
    }

    public void setUser(ParseUser user){
        put(KEY_USER, user);
    }

    public User(){}

    public static class Query extends ParseQuery<User>{
        public Query(){
            super(User.class);
        }

        public Query getTop(){
            setLimit(20);
            return this;
        }

        public Query withUser(){
            include("user");
            return this;
        }

    }
}

