package com.piticlistudio.androidavengers;


import android.view.View;
import android.widget.EditText;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;

/**
 * Custom view matches for Espresso
 * Created by jorge.garcia on 13/07/2016.
 */

public class CustomMatchers {

    public static Matcher<View> withHint(final String expectedHint) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                if (!(view instanceof EditText)) {
                    return false;
                }

                String hint = ((EditText) view).getHint().toString();

                return expectedHint.equals(hint);
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }

    public static Matcher<View> withAlpha(final float expectedAlpha) {
        return new TypeSafeMatcher<View>() {

            @Override
            public boolean matchesSafely(View view) {
                float alpha = view.getAlpha();

                return alpha == expectedAlpha;
            }

            @Override
            public void describeTo(Description description) {
            }
        };
    }
}
