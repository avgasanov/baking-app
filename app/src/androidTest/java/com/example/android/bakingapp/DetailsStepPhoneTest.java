package com.example.android.bakingapp;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.TypeSafeMatcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
/**
 * This test will pass for devices < 600sw with proper internet connection
 */
@LargeTest
@RunWith(AndroidJUnit4.class)
public class DetailsStepPhoneTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void detailsStepPhoneTest() {
        ViewInteraction appCompatTextView = onView(
                allOf(withId(R.id.tv_name), withText("Nutella Pie"),
                        childAtPosition(
                                allOf(withId(R.id.recipe_item_cv),
                                        childAtPosition(
                                                withId(R.id.rv_master_list_root),
                                                0)),
                                0),
                        isDisplayed()));
        appCompatTextView.perform(click());

        ViewInteraction textView = onView(
                allOf(withId(R.id.tv_name), withText("Recipe Ingredients"),
                        childAtPosition(
                                allOf(withId(R.id.recipe_item_cv),
                                        childAtPosition(
                                                withId(R.id.rv_master_list_root),
                                                0)),
                                0),
                        isDisplayed()));
        textView.check(matches(withText("Recipe Ingredients")));

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.tv_name), withText("Recipe Introduction"),
                        childAtPosition(
                                allOf(withId(R.id.recipe_item_cv),
                                        childAtPosition(
                                                withId(R.id.rv_master_list_root),
                                                0)),
                                0),
                        isDisplayed()));
        textView2.check(matches(withText("Recipe Introduction")));

    }

    private static Matcher<View> childAtPosition(
            final Matcher<View> parentMatcher, final int position) {

        return new TypeSafeMatcher<View>() {
            @Override
            public void describeTo(Description description) {
                description.appendText("Child at position " + position + " in parent ");
                parentMatcher.describeTo(description);
            }

            @Override
            public boolean matchesSafely(View view) {
                ViewParent parent = view.getParent();
                return parent instanceof ViewGroup && parentMatcher.matches(parent)
                        && view.equals(((ViewGroup) parent).getChildAt(position));
            }
        };
    }
}