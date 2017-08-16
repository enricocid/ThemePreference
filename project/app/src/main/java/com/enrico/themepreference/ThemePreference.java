package com.enrico.themepreference;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.preference.Preference;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;

public class ThemePreference extends Preference {

    private String[] themeOptions;
    private String[] themeValues;
    private Context mContext;

    public ThemePreference(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    private ThemePreference(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLayoutResource(R.layout.preference_theme);
        mContext = context;
    }

    private static int resolveColor(int pos) {

        //light theme
        int def = R.color.accent;

        //other themes
        int red = R.color.material_red_400;
        int pink = R.color.material_pink_400;
        int purple = R.color.material_purple_400;
        int deepPurple = R.color.material_deepPurple_400;
        int indigo = R.color.material_indigo_400;
        int blue = R.color.material_blue_400;
        int lightBlue = R.color.material_lightBlue_400;
        int cyan = R.color.material_cyan_400;
        int teal = R.color.material_teal_400;
        int green = R.color.material_green_400;
        int amber = R.color.material_amber_400;
        int orange = R.color.material_orange_400;
        int deepOrange = R.color.material_deepOrange_400;
        int brown = R.color.material_brown_400;
        int blueGrey = R.color.material_blueGrey_400;

        switch (pos) {
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

    public static void createCircularPreferenceBitmap(Boolean isImage, Preference preference, ImageView imageView, Context context, int color) {

        Bitmap.Config conf = Bitmap.Config.ARGB_8888;
        int dimen = (int) context.getResources().getDimension(android.R.dimen.app_icon_size);
        Bitmap bmp = Bitmap.createBitmap(dimen, dimen, conf);

        if (isImage) {
            imageView.setBackground(createRoundedBitmapDrawable(bmp, color, context.getResources()));
        } else {
            preference.setIcon(createRoundedBitmapDrawable(bmp, color, context.getResources()));

        }
    }

    private static RoundedBitmapDrawable createRoundedBitmapDrawable(Bitmap bitmap, int color, Resources mResources) {

        bitmap.eraseColor(color);

        // Create a new RoundedBitmapDrawable
        RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(mResources, bitmap);

        roundedBitmapDrawable.setCircular(true);

        roundedBitmapDrawable.setAntiAlias(true);

        // Return the RoundedBitmapDrawable
        return roundedBitmapDrawable;
    }

    @Override
    protected View onCreateView(ViewGroup parent) {
        init();
        return super.onCreateView(parent);
    }

    @Override
    protected void onClick() {
        super.onClick();
        showDialog();
    }

    private void showDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        final ThemeAdapter adapter = new ThemeAdapter(mContext, themeValues, themeOptions, getPersistedString("0"));

        builder.setTitle(R.string.pref_theme_title);
        builder.setNegativeButton(android.R.string.cancel, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int position) {
                String item = adapter.getItem(position);
                persistString(item);
            }
        });

        builder.show();
    }

    private void init() {

        themeOptions = mContext.getResources().getStringArray(R.array.theme_options);
        themeValues = mContext.getResources().getStringArray(R.array.pref_theme_list_values);
        int color = ThemeUtils.getColorAccent(mContext);
        createCircularPreferenceBitmap(false, this, null, mContext, color);

        setDefaultValue(0);
    }

    private static class ThemeAdapter extends BaseAdapter {

        LayoutInflater mLayoutInflater;
        String[] themeValues;
        Context context;
        String selectedTheme;
        String[] themeOptions;

        ThemeAdapter(Context context, String[] themeValues, String[] themeOptions, String currentTheme) {

            mLayoutInflater = LayoutInflater.from(context);
            this.themeValues = themeValues;
            this.themeOptions = themeOptions;
            this.context = context;
            selectedTheme = currentTheme;
        }

        @Override
        public int getCount() {
            return themeOptions.length;
        }

        @Override
        public String getItem(int position) {
            return themeValues[position];
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                final ViewGroup nullParent = null;
                convertView = mLayoutInflater.inflate(R.layout.theme_dialog, nullParent);
            }

            String theme = themeOptions[position];

            TextView txtView = convertView.findViewById(R.id.title);
            txtView.setText(theme);

            RadioButton radioButton = convertView.findViewById(R.id.radio);

            int itemColor = ContextCompat.getColor(context, resolveColor(position));

            ColorStateList colorStateList = new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_enabled}, //enabled
                            new int[]{android.R.attr.state_enabled} //disabled

                    },
                    new int[]{itemColor, itemColor}
            );

            txtView.setShadowLayer(1.5f, -1, 1, itemColor);
            radioButton.setButtonTintList(colorStateList);

            radioButton.setChecked(themeValues[position].equals(selectedTheme));
            return convertView;
        }
    }
}

