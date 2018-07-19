package e.rahmanapyrr.friends_management.models;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

@ParseClassName("Friend")
public class Friend extends ParseObject {

        public static final String KEY_USER = "user";

        public ParseUser getUser(){
            return getParseUser(KEY_USER);
        }

        public void setUser(ParseUser user){
            put(KEY_USER, user);
        }

        public Friend(){}

        public static class Query extends ParseQuery<Friend> {
            public Query(){
                super(Friend.class);
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

