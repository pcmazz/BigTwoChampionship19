package com.falcotech.mazz.bigtwochampionship;

import android.util.Log;

import java.util.HashMap;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by phima on 4/24/2017.
 * Need to make the table a big activity that has cards and chips and all
 */
@Singleton
public final class Utils {
    public static final String BIG_PREFS = "bigPrefs";
    public static final String ACTIVITY_FOCUS = "activity_focus";
    public static final String PLAYER_SCREEN_WIDTH = "screen_width";
    public static final String PLAYER_CARD_WIDTH = "card_width";
    public static final String TABLE_SCREEN_WIDTH = "screen_width";
    public static final String TABLE_CARD_WIDTH = "card_width";
    public static final String PLAYER_CARDS = "player_cards";
    public static final String DUMMY = "dummy";

    @Inject
    public Utils() {
    }

    /**
     * Toggles the Log debugging for each class. Defaults to true
     *
     * @param classTag String for the Activity name
     * @return boolean value for whether to run log print or not
     */
    private static boolean buggerOn(String classTag) {
        HashMap<String, Boolean> map = new HashMap<>();
        map.put("SplashActivity", true);
        if (!map.containsKey(classTag)) {
            return true;
        } else {
            return map.get(classTag);
        }
    }

    /**
     * Writes a debug statement using the class or string to set the tag
     *
     * @param parentClass an Object representing a Class or a String
     * @param method      a String for the method name
     * @param msg         String for the message text
     */
    public static void bugger(Object parentClass, String method, String msg) {
        if (parentClass instanceof String) {
            String parent = (String) parentClass;
            if (buggerOn(parent)) {
                Log.d(parent + " - " + method, msg);
            }
        } else {
            String parent = ((Class) parentClass).getSimpleName();
            if (buggerOn(parent)) {
                Log.d(parent + " - " + method, msg);
            }
        }
    }
}
