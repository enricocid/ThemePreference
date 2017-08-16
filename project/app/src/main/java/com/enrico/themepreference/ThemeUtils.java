package com.enrico.themepreference;

import android.content.Context;
import android.preference.PreferenceManager;
import android.support.v7.view.ContextThemeWrapper;
import android.util.TypedValue;

class ThemeUtils {

    static final String THEME_KEY = "pref_lightOrDark";
    static final String CHOOSE_ACCENT_KEY = "pref_chooseAccent";


    //is dark theme enabled?
    private static boolean isDarkThemeEnabled(Context context) {
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(THEME_KEY,
                false);
    }

    //method to apply selected theme
    static void applyTheme(ContextThemeWrapper contextThemeWrapper, Context context) {
        int theme = resolveTheme(context);
        contextThemeWrapper.setTheme(theme);

    }

    static int getColorAccent(Context context) {
        final TypedValue value = new TypedValue();
        context.getTheme().resolveAttribute(android.R.attr.colorAccent, value, true);
        return value.data;

    }

    //multi-preference dialog for theme options
    private static int resolveTheme(Context context) {

        //light theme
        int def = isDarkThemeEnabled(context) ? R.style.Base_Theme_Dark : R.style.Base_Theme;

        //other themes
        int red = isDarkThemeEnabled(context) ? R.style.Base_Theme_Red_Dark : R.style.Base_Theme_Red;
        int pink = isDarkThemeEnabled(context) ? R.style.Base_Theme_Pink_Dark : R.style.Base_Theme_Pink;
        int purple = isDarkThemeEnabled(context) ? R.style.Base_Theme_Purple_Dark : R.style.Base_Theme_Purple;
        int deepPurple = isDarkThemeEnabled(context) ? R.style.Base_Theme_DeepPurple_Dark : R.style.Base_Theme_DeepPurple;
        int indigo = isDarkThemeEnabled(context) ? R.style.Base_Theme_Indigo_Dark : R.style.Base_Theme_Indigo;
        int blue = isDarkThemeEnabled(context) ? R.style.Base_Theme_Blue_Dark : R.style.Base_Theme_Blue;
        int lightBlue = isDarkThemeEnabled(context) ? R.style.Base_Theme_LightBlue_Dark : R.style.Base_Theme_LightBlue;
        int cyan = isDarkThemeEnabled(context) ? R.style.Base_Theme_Cyan_Dark : R.style.Base_Theme_Cyan;
        int teal = isDarkThemeEnabled(context) ? R.style.Base_Theme_Teal_Dark : R.style.Base_Theme_Teal;
        int green = isDarkThemeEnabled(context) ? R.style.Base_Theme_Green_Dark : R.style.Base_Theme_Green;
        int amber = isDarkThemeEnabled(context) ? R.style.Base_Theme_Amber_Dark : R.style.Base_Theme_Amber;
        int orange = isDarkThemeEnabled(context) ? R.style.Base_Theme_Orange_Dark : R.style.Base_Theme_Orange;
        int deepOrange = isDarkThemeEnabled(context) ? R.style.Base_Theme_DeepOrange_Dark : R.style.Base_Theme_DeepOrange;
        int brown = isDarkThemeEnabled(context) ? R.style.Base_Theme_Brown_Dark : R.style.Base_Theme_Brown;
        int blueGrey = isDarkThemeEnabled(context) ? R.style.Base_Theme_BlueGrey_Dark : R.style.Base_Theme_BlueGrey;

        String choice = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(CHOOSE_ACCENT_KEY, String.valueOf(0));

        switch (Integer.parseInt(choice)) {
            default:
            case 0:
                return def;

            case 1:
                return red;

            case 2:
                return pink;

            case 3:
                return purple;

            case 4:
                return deepPurple;

            case 5:
                return indigo;

            case 6:
                return blue;

            case 7:
                return lightBlue;

            case 8:
                return cyan;

            case 9:
                return teal;

            case 10:
                return green;

            case 11:
                return amber;

            case 12:
                return orange;

            case 13:
                return deepOrange;

            case 14:
                return brown;

            case 15:
                return blueGrey;

        }
    }
}
