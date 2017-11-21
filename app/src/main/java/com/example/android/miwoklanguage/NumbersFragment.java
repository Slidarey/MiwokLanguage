package com.example.android.miwoklanguage;


import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class NumbersFragment extends Fragment {

    private MediaPlayer mMediaPlayer;
    //Another way of creating OnCompletionListener(Assigning it  to a name)
    private MediaPlayer.OnCompletionListener mCompletionListener = new MediaPlayer.OnCompletionListener() {
        @Override
        public void onCompletion(MediaPlayer mediaPLayer) {
            releaseMediaPlayer();// calling a method that is declared private in another class
        }
    };

    private AudioManager mAudioManager;

    private AudioManager.OnAudioFocusChangeListener mAudioFocusChange = new AudioManager.OnAudioFocusChangeListener() {
        @Override
        public void onAudioFocusChange(int focusChange) {
            //written as AudioManager.AUDIOFOCUS_GAIN because AUDIOFOCUS_GAIN is a constant in class AudioManager
            if (focusChange == AudioManager.AUDIOFOCUS_GAIN) {
                mMediaPlayer.start();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS) {
                mMediaPlayer.stop();
                releaseMediaPlayer();
            } else if (focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT || focusChange == AudioManager.AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK) {
                mMediaPlayer.pause();
                mMediaPlayer.seekTo(0);
            }
        }
    };


    public NumbersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.word_list, container, false);


        mAudioManager = ( AudioManager ) getActivity().getSystemService(Context.AUDIO_SERVICE);
        //to ensure that the volume control keys controls the right stream(being music, notifications or dtmf
        getActivity().setVolumeControlStream(AudioManager.STREAM_MUSIC);

        final ArrayList<Word> words = new ArrayList<>();

        words.add(new Word("one", "lutti", R.drawable.number_one, R.raw.number_one));
        words.add(new Word("two", "otiiko", R.drawable.number_two, R.raw.number_two));
        words.add(new Word("three", "tolookosu", R.drawable.number_three, R.raw.number_three));
        words.add(new Word("four", "oyyisa", R.drawable.number_four, R.raw.number_four));
        words.add(new Word("five", "massokka", R.drawable.number_five, R.raw.number_five));
        words.add(new Word("six", "temmokka", R.drawable.number_six, R.raw.number_six));
        words.add(new Word("seven", "kenekaku", R.drawable.number_seven, R.raw.number_seven));
        words.add(new Word("eight", "kawinta", R.drawable.number_eight, R.raw.number_eight));
        words.add(new Word("nine", "wo'e", R.drawable.number_nine, R.raw.number_nine));
        words.add(new Word("ten", "na'aacha", R.drawable.number_ten, R.raw.number_ten));

        //Create an ArrayAdapter, whose data source is a list of Strings. The adapter knows how to create layouts
        //for each item in the list, using the simple_list_item_1.xml layout resource defined in the Android framework.
        //This list item layout contains a single TextView, which the adapter will set to display a single word.
        WordAdapter adapter = new WordAdapter(getActivity(), words, R.color.category_numbers);

        //Find the ListView object in the view hierarchy of the Activity. There should be a ListView with the view ID
        //called list, which is declared in the word_list.xmlfile.
        //Fragments:calling the findViewById method on rootView because Fragments dont have findViewById method
        ListView listView = (ListView) rootView.findViewById(R.id.list);


        //Make the ListView use the ArrayAdapter we created above, so that the ListView will display list items for
        //each word in the list of words. Do this by calling the setAdapter method on the ListView object and pass in
        //1 argument, which is the ArrayAdapter with the variable name itemsAdapter.
        listView.setAdapter(adapter);

        //the setOnItemClickListener is best used after the array adapter is combined with the list view
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                //release the media player if it exist because we are about to play a different sound file
                releaseMediaPlayer();

                Word word = words.get(position);//just like words.add();

                int result = mAudioManager.requestAudioFocus(mAudioFocusChange, AudioManager.STREAM_MUSIC, AudioManager.AUDIOFOCUS_GAIN_TRANSIENT);

                if (result == AudioManager.AUDIOFOCUS_REQUEST_GRANTED) {


                    Log.e("NumbersActivity", "Current Word Object is: " + word);// OR word.toString()
                    mMediaPlayer = MediaPlayer.create(getActivity(), word.getAudioFilesId());
                    mMediaPlayer.start();
                    mMediaPlayer.setOnCompletionListener(mCompletionListener);
                    //Another way of creating it is shown above
//                mMediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(){
//
//                    @Override
//                    public void onCompletion(MediaPlayer mediaPlayer){
//                        releaseMediaPlayer();
//                    }
//                });
                }
            }
        });
        return rootView;
    }



    private void releaseMediaPlayer() {
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
        }

        mMediaPlayer = null;

        mAudioManager.abandonAudioFocus(mAudioFocusChange);

    }

    @Override
    public void onStop() {
        super.onStop();
        releaseMediaPlayer();
    }
}
