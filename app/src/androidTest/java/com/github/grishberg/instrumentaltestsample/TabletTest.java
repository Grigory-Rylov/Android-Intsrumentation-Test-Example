package com.github.grishberg.instrumentaltestsample;

import androidx.test.rule.ActivityTestRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import android.util.Log;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

/**
 * Test for main activity.
 */
@TabletOnly
@RunWith(AndroidJUnit4.class)
public class TabletTest {
    private static final String TAG = MainActivityTest.class.getSimpleName();

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() throws Exception {
        Log.d(TAG, "setUp: ");
    }

    @Test
    public void testTabletButton() {
        Log.d(TAG, "testTabletButton: ");
        onView(withId(R.id.buttonFor600dp)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textView))
                .check(matches(withText(R.string.text_for_600dp)));
    }

    @Ignore
    @Test
    public void ignoredTestTabletButton2() {
        Log.d(TAG, "testTabletButton2: ");
        onView(withId(R.id.buttonForTablet)).perform(click());

        // Check that the text was changed.
        onView(withId(R.id.textView))
                .check(matches(withText(R.string.text_for_tablet)));
        Log.d(TAG, "testTabletButton2: ended");
    }
}
