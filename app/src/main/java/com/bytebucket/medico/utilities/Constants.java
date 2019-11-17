package com.bytebucket.medico.utilities;

import com.bytebucket.medico.R;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Constants {
    final public static int WORDS_PER_MINUTE = 150;
    final public static int[] PROFILE_IMAGES = {
            R.drawable.ic_doctor_one,
            R.drawable.ic_doctor_two,
            R.drawable.ic_doctor_three,
            R.drawable.ic_doctor_four,
            R.drawable.ic_doctor_five,
    };

    final public static int[] ARTICLE_IMAGES = {
            R.drawable.ic_article_image_one,
            R.drawable.ic_article_image_two,
            R.drawable.ic_article_image_three,
            R.drawable.ic_article_image_four,
            R.drawable.ic_article_image_five,
            R.drawable.ic_article_image_six,
            R.drawable.ic_article_image_seven,
            R.drawable.ic_article_image_eight
    };


    public static int getRandomProfilePic(){
        int len = PROFILE_IMAGES.length;
        int randomInt = (int)(Math.random() * ((len - 0)));
        return PROFILE_IMAGES[randomInt];
    }


    public static int getRandomArticlePic(){
        int len = ARTICLE_IMAGES.length;
        int randomInt = (int)(Math.random() * ((len - 0)));
        return ARTICLE_IMAGES[randomInt];
    }


    public static String MEDICINE_NAME = "test";
    public static int DOSAGE = 10;

}
