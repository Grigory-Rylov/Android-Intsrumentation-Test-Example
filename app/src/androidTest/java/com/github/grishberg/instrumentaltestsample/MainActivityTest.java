package com.github.grishberg.instrumentaltestsample;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

/**
 * Test for main activity.
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityTest {
    private static final String TAG = MainActivityTest.class.getSimpleName();

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        Log.d(TAG, "setUp: ");
    }

    @Test
    public void testPhoneButton() {
        Log.d(TAG, "testPhoneButton: ");
        onView(withId(R.id.buttonForPhone)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textView))
                .check(matches(withText(R.string.text_for_phone)));
    }

    @Test
    public void testPhoneButton2() {
        Log.d(TAG, "testPhoneButton2: ");
        onView(withId(R.id.buttonForPhone)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textView))
                .check(matches(withText(R.string.text_for_phone)));
        Log.d(TAG, "testPhoneButton2: ended");
    }
}