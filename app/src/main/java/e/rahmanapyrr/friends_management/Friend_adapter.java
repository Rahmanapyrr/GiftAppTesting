package e.rahmanapyrr.friends_management;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.parse.ParseUser;

import org.parceler.Parcels;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import e.rahmanapyrr.friends_management.models.Friend;
import e.rahmanapyrr.friends_management.models.GlideApp;

public class Friend_adapter extends RecyclerView.Adapter<Friend_adapter.ViewHolder> {

    private List<Friend> friends;
    Context context;

    public Friend_adapter(List<Friend> friends){this.friends = friends; }


    @Override
    public Friend_adapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        View picView = inflater.inflate(R.layout.item_friend, parent, false);
        // ViewHolder viewHolder = new ViewHolder(picView);
        return new ViewHolder(picView);
    }

    @Override
    public void onBindViewHolder(Friend_adapter.ViewHolder holder, int position) {
        // get data
        Friend friend = friends.get(position);
//        holder.Description.setText(post.getDescription());
//        String myTime = TimeFormat.getTimeDifference(post.getCreatedAt().toString());
//        holder.time.setText(myTime);
//        //holder.tvDate.setText(getRelativeTimeAgo(post.getCreatedAt().toString()));

        ParseUser user = friend.getUser();
        try {
            holder.Username.setText(user.fetchIfNeeded().getUsername());
            //System.out.println(user.fetchIfNeeded().getUsername());
        } catch (com.parse.ParseException e) {
            e.printStackTrace();
        }

        //holder.createdAt.setText(getRelativeTimeAgo(.createdAt));
    }

    @Override
    public int getItemCount() {
        return friends.size();
    }

    public String getRelativeTimeAgo(String rawJsonDate) {
        String twitterFormat = "EEE MMM dd HH:mm:ss ZZZZZ yyyy";
        SimpleDateFormat sf = new SimpleDateFormat(twitterFormat, Locale.ENGLISH);
        sf.setLenient(true);

        String relativeDate = "";
        try {
            long dateMillis = sf.parse(rawJsonDate).getTime();
            relativeDate = DateUtils.getRelativeTimeSpanString(dateMillis,
                    System.currentTimeMillis(), DateUtils.SECOND_IN_MILLIS).toString();
        } catch (ParseException e) {
            e.printStackTrace();
        }

        return relativeDate;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
//        public ImageView Picture;
//        public TextView Description;
        public TextView Username;
        //public TextView tvDate;
//        public TextView time;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

//            Picture = (ImageView) itemView.findViewById(R.id.insta_pic);
//            Description = (TextView) itemView.findViewById(R.id.description_post);
            Username = (TextView) itemView.findViewById(R.id.tvUserName);
            //tvDate = (TextView) itemView.findViewById(R.id.time_post);
//            time = (TextView) itemView.findViewById(R.id.time_post);

            itemView.setOnClickListener(this);
        }

        //when the user clicks on a row, show MovieDetailsActivity for the selected movie
        @Override
        public void onClick(View v) {

            int position = getAdapterPosition();
            // make sure the position is valid, i.e. actually exists in the view
            if (position != RecyclerView.NO_POSITION) {
                // get the movie at the position, this won't work if the class is static
                Friend friend = friends.get(position);
                // create intent for the new activity
                Intent intent = new Intent(context, DetailActivity.class);
                // serialize the movie using parceler, use its short name as a key
                intent.putExtra(Friend.class.getSimpleName(), Parcels.wrap(friend));
//                intent.putExtra("photo", post.getImage().getUrl());
//                intent.putExtra("description", post.getDescription());
                try {
                    intent.putExtra("username", friend.getUser().fetchIfNeeded().getUsername());
                } catch (com.parse.ParseException e) {
                    e.printStackTrace();
                }

//time_stuff
//                String myTime = TimeFormat.getTimeDifference(post.getCreatedAt().toString());
//                intent.putExtra("time", myTime);

                // show the activity
                context.startActivity(intent);
            }

        }
    }


    // Clean all elements of the recycler
    public void clear() {
        friends.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Friend> list) {
        friends.addAll(list);
        notifyDataSetChanged();
    }

}

