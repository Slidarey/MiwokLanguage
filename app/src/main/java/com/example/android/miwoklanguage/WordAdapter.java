package com.example.android.miwoklanguage;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zahir on 08/08/2017.
 */

public class WordAdapter extends ArrayAdapter<Word> {

    private int mColorResourceId;

    public WordAdapter(Context context, ArrayList<Word> words, int colorResourceId) {
        super(context, 0, words);
        mColorResourceId = colorResourceId;
    }


    //@param parent refers to the parent view group for all the list items which is the ListView itself
    //the purpose of the getView method is to return a list item view and return it to the ListView
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        //this if condition will check if there is no view to be reused, if yes,
        // the statement in the if condition will create a new view.
        //the false parameter means that we dont want to attach a list item view to the parent list view yet
        // (list item view will attached to the parent list view with the code provided below the if condition)
        //IN OTHER WORDS
        //The if condition helps us to inflate the view, so inflating the list item view means that we create
        //new list item layout from the R.dfsbdf layout resource and we will store this in the listItemView variable,
        //
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.list_item, parent, false);
        }

        //Get the Word object located at this position in the list
        //NB There is no need for the keyword 'new' because getItem() method is used
        //return type is Word
        Word currentWord = getItem(position);

        //find the TextView in the dfsbdfl layout with the ID miwok_text_view
        TextView miwokTextView = (TextView) listItemView.findViewById(R.id.miwok_text_view);

        //Get the miwok Language from the currentWord object and
        //set this text on miwokTextView
        miwokTextView.setText(currentWord.getMiwokLanguage());

        //find the TextView in the dfsbdfl layout with the ID english_text_view
        TextView englishTextView = (TextView) listItemView.findViewById(R.id.english_text_view);

        //Get the english Language from the currentWord object and
        //Set this text on englishTextView
        englishTextView.setText(currentWord.getDefaultLanguage());

        ImageView imageView = (ImageView) listItemView.findViewById(R.id.image);
        if (currentWord.hasImage()) {
            imageView.setImageResource(currentWord.getImageResourceId());
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.GONE);
        }

        //Set the theme color for the list item
        LinearLayout linearLayout = (LinearLayout) listItemView.findViewById(R.id.text_container);
        //Find the color that the resource ID maps to
        //
        //Set the background color of the text container
        linearLayout.setBackgroundResource(mColorResourceId);

        //Return the whole list item Layout(containing 2 TextViews and 1 ImageView)
        //so that it can be shown in the ListView

        return listItemView;
    }
}