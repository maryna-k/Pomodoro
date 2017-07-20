package com.example.android.pomodoro;



import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.example.android.pomodoro.pomodorolist.PomodoroListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

@RunWith(AndroidJUnit4.class)
public class PomodoroListViewTest {

    @Rule
    public ActivityTestRule<PomodoroListActivity> activityTestRule = new ActivityTestRule<>(PomodoroListActivity.class);

    @Test
    public void clickOnFloatingActionButton_ShouldOpenAddPomodoroActivity(){
        onView(withId(R.id.add_fab_button)).perform(click());
        onView(withId(R.id.add_pomodoro_text_field)).check(matches(isDisplayed()));
    }

}
