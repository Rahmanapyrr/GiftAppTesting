package e.rahmanapyrr.friends_management;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

import e.rahmanapyrr.friends_management.models.Friend;

public class FriendsView extends AppCompatActivity {

    ArrayList<Friend> friends;
    Friend_adapter adapter;
    RecyclerView rvFriends;
    Bitmap.Config config;
    private SwipeRefreshLayout swipeContainer;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.friends_recycler_view);

        friends = new ArrayList<>();

        adapter = new Friend_adapter(friends);
        rvFriends = findViewById(R.id.rvFriends);
        rvFriends.setLayoutManager(new LinearLayoutManager(this));
        rvFriends.setAdapter(adapter);

        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                fetchTimelineAsync(0);
            }
        });

        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        populateTimeline();

    }

    public void fetchTimelineAsync(int page) {
        adapter.clear();
        populateTimeline();
        swipeContainer.setRefreshing(false);
    }

    public void populateTimeline() {
        ParseQuery<Friend> query = ParseQuery.getQuery(Friend.class).include("user");
        //ParseQuery<Friend> query = ParseQuery.getQuery(Friend.class).include("user");
        // Execute the find asynchronously
        //query.setLimit(20);
//        query.whereEqualTo("user", ParseUser.getCurrentUser());
        //query.orderByAscending("createdAt");


        //friendsQuery.getTop().withUser();
        query.setLimit(20);

        final Friend.Query friendsQuery = new Friend.Query();
        friendsQuery.findInBackground(new FindCallback<Friend>() {
            @Override
            public void done(List<Friend> objects, com.parse.ParseException e) {
                if (e == null) {
                    friends.clear();
                    friends.addAll(objects);
                    adapter.notifyDataSetChanged();
                } else {
                    e.printStackTrace();
                }
            }
        });

//        public void done(List<Post> posts, ParseException e) {
//            if (e == null) {
//                // Access the array of results here
//                posts.addAll(posts);
//
//                adapter.notifyDataSetChanged();
//            } else {
//                Log.d("item", "Error: " + e.getMessage());
//            }
//        }
//    });

    }


}