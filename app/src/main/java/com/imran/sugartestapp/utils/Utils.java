package com.imran.sugartestapp.utils;

import com.imran.sugartestapp.R;

import java.util.ArrayList;
import java.util.Random;

public class Utils {

    private static ArrayList<Integer> mSkeltonBackgroundColor = getSkeltonBackgroundColor();

    public static ArrayList<Integer> getSkeltonBackgroundColor() {
        ArrayList<Integer> mSkeltonBackgroundColor = new ArrayList<>();
        mSkeltonBackgroundColor.add(R.color.placeholder_one);
        mSkeltonBackgroundColor.add(R.color.placeholder_two);
        mSkeltonBackgroundColor.add(R.color.placeholder_three);
        mSkeltonBackgroundColor.add(R.color.placeholder_four);
        mSkeltonBackgroundColor.add(R.color.placeholder_five);
        mSkeltonBackgroundColor.add(R.color.placeholder_six);
        mSkeltonBackgroundColor.add(R.color.placeholder_seven);
        mSkeltonBackgroundColor.add(R.color.placeholder_eight);
        mSkeltonBackgroundColor.add(R.color.placeholder_nine);
        mSkeltonBackgroundColor.add(R.color.placeholder_ten);
        mSkeltonBackgroundColor.add(R.color.placeholder_eleven);
        mSkeltonBackgroundColor.add(R.color.placeholder_twelve);
        return mSkeltonBackgroundColor;
    }

    public static int setplaceholder(int pos) {

        if (pos < mSkeltonBackgroundColor.size()) {
            return mSkeltonBackgroundColor.get(pos);
        } else {
            Random rand = new Random();
            int value = rand.nextInt(12);
            return mSkeltonBackgroundColor.get(value);
        }
    }
}
