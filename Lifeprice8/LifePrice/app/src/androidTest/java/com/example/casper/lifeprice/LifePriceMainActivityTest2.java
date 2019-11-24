package com.example.casper.lifeprice;


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
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.longClick;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class LifePriceMainActivityTest2 {

    @Rule
    public ActivityTestRule<LifePriceMainActivity> mActivityTestRule = new ActivityTestRule<>(LifePriceMainActivity.class);

    @Test
    public void lifePriceMainActivityTest2() {

        ViewInteraction linearLayout2 = onView(
                allOf(childAtPosition(
                        withId(R.id.list_view_goods),
                        0),
                        isDisplayed()));
        linearLayout2.perform(longClick());

        ViewInteraction textView = onView(
                allOf(withId(android.R.id.title), withText("新建"), isDisplayed()));
        textView.perform(click());

        ViewInteraction appCompatEditText = onView(
                allOf(withId(R.id.edit_text_good_name), isDisplayed()));
        appCompatEditText.perform(click());

        ViewInteraction appCompatEditText2 = onView(
                allOf(withId(R.id.edit_text_good_name), isDisplayed()));
        appCompatEditText2.perform(replaceText("eeee"), closeSoftKeyboard());

        ViewInteraction appCompatEditText3 = onView(
                allOf(withId(R.id.edit_text_good_name), withText("eeee"), isDisplayed()));
        appCompatEditText3.perform(click());

        ViewInteraction appCompatEditText4 = onView(
                allOf(withId(R.id.edit_text_good_price), isDisplayed()));
        appCompatEditText4.perform(replaceText("11"), closeSoftKeyboard());

        ViewInteraction appCompatButton = onView(
                allOf(withId(R.id.button_ok), withText("确定"), isDisplayed()));
        appCompatButton.perform(click());

        ViewInteraction textView2 = onView(
                allOf(withId(R.id.text_view_name), withText("商品：eeee"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_view_goods),
                                        0),
                                1),
                        isDisplayed()));
        textView2.check(matches(withText("商品：eeee")));

        ViewInteraction textView3 = onView(
                allOf(withId(R.id.text_view_price), withText("价格：11.0元"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_view_goods),
                                        0),
                                2),
                        isDisplayed()));
        textView3.check(matches(withText("价格：11.0元")));

        ViewInteraction imageView = onView(
                allOf(withId(R.id.image_view_good),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_view_goods),
                                        0),
                                0),
                        isDisplayed()));
        imageView.check(matches(isDisplayed()));

        ViewInteraction imageView2 = onView(
                allOf(withId(R.id.image_view_good),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.list_view_goods),
                                        0),
                                0),
                        isDisplayed()));
        imageView2.check(matches(isDisplayed()));

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
