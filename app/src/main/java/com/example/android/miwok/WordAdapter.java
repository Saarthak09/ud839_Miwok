package com.example.android.miwok;

import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(@NonNull Context context, ArrayList<Word> resource,int colorResourceId ) {
        super(context,0, resource);
        mColorResourceId=colorResourceId;

    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
//        return super.getView(position, convertView, parent);


        // Check if the existing view is being reused, otherwise inflate the view
        View listItemView = convertView;

        if(listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }

        final Word local_word = getItem(position);

        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.txt1);
        miwokTextView.setText(local_word.getMiwokTranslation());

        TextView defaultTextView = (TextView) listItemView.findViewById(R.id.txt2);
        defaultTextView.setText(local_word.getDefaultTranslation());

        ImageView defaultImageView=(ImageView) listItemView.findViewById(R.id.image);

       if(local_word.hasImage()) {
           defaultImageView.setImageResource(local_word.getImageResourceId());
       }
       else {
           defaultImageView.setVisibility(View.GONE);
       }

        View textContainer=listItemView.findViewById(R.id.text_container);
        int color= ContextCompat.getColor(getContext(),mColorResourceId);
        textContainer.setBackgroundColor(color);

        LinearLayout menu_photos = (LinearLayout) listItemView.findViewById(R.id.text_container);
        menu_photos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MediaPlayer mediaPlayer = MediaPlayer.create(getContext(),local_word.getAudioResourceId() );
                mediaPlayer.start();
            }
        });

        return listItemView;

    }
}
