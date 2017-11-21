package com.example.android.miwoklanguage;

/**
 * Created by Zahir on 08/08/2017.
 */

public class Word {
    // these are the member variables
    private String mDefaultTranslation;

    private String mMiwokTranslation;

    private int mAudioFilesId;
    //we use -1 as the value for the constant because no image resource id can have the value
    private static final int NO_IMAGE_PROVIDED = -1;

    private int mImageResourceId = NO_IMAGE_PROVIDED;

    @Override
    public String toString() {
        return "Word{" +
                "mDefaultTranslation='" + mDefaultTranslation + '\'' +
                ", mMiwokTranslation='" + mMiwokTranslation + '\'' +
                ", mAudioFilesId=" + mAudioFilesId +
                ", mImageResourceId=" + mImageResourceId +
                '}';
    }

    public Word(String dLanguage, String mLanguage, int resourceId, int audioFilesId) {
        mDefaultTranslation = dLanguage;
        mMiwokTranslation = mLanguage;
        mImageResourceId = resourceId;
        mAudioFilesId = audioFilesId;
    }

    public Word(String dLanguage, String mLanguage,int audioFilesId) {
        mDefaultTranslation = dLanguage;
        mMiwokTranslation = mLanguage;
        mAudioFilesId = audioFilesId;
    }

    public String getDefaultLanguage() {
        return mDefaultTranslation;
    }

    public String getMiwokLanguage() {
        return mMiwokTranslation;
    }

    public int getImageResourceId() {
        return mImageResourceId;
    }

    public int getAudioFilesId(){return mAudioFilesId;}

    public boolean hasImage() {
        return mImageResourceId != NO_IMAGE_PROVIDED;
    }
}
