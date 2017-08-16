# ThemePreference
Custom preference to apply themes

![ScreenShot](https://raw.githubusercontent.com/enricocid/ThemePreference/master/art/preview.png)

You can download the example APK from this repo here: https://github.com/enricocid/ThemePreference/raw/master/project/app/app-release.apk


###Add the preference to your /res/xml/preferences.xml

``` java
<SwitchPreference android:defaultValue="false" android:key="pref_lightOrDark" android:title="@string/dark_theme"/>

<com.enrico.themepreference.ThemePreference android:defaultValue="0" android:key="pref_chooseAccent" android:persistent="true" android:summary="?attr/colorAccent" android:title="@string/pref_theme_title"/>
```


###In Your activity

``` java
ContextThemeWrapper contextThemeWrapper = new ContextThemeWrapper(getBaseContext(), this.getTheme());
```


###And use the util to apply the theme:

``` java
ThemeUtils.applyTheme(contextThemeWrapper, this);
```
