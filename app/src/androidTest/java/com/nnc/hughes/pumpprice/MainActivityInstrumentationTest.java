package com.nnc.hughes.pumpprice;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.assertion.ViewAssertions.selectedDescendantsMatch;
import static android.support.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static android.support.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static android.support.test.espresso.matcher.ViewMatchers.hasDescendant;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;

import com.forkingcode.espresso.contrib.DescendantViewActions;
import com.nnc.hughes.pumpprice.model.Station;
import com.nnc.hughes.pumpprice.network.GasAPI;
import com.nnc.hughes.pumpprice.ui.stationlist.GasListPresenter;
import com.nnc.hughes.pumpprice.ui.stationlist.MainActivity;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import java.util.List;

public class MainActivityInstrumentationTest {
    private List<Station> stations;

    @Rule
    public ActivityTestRule<MainActivity> activityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Test
    public void chaining() {
        // Chaining several actions together on the recycler view
        onView(withId(R.id.activity_stations_recyclerView)).perform(

                // First position the recycler view
                scrollToPosition(1),

                // With the descendant actions provided, you can check the status of a descendant view using
                // a standard check
                actionOnItemAtPosition(1, DescendantViewActions.checkViewAction(
                        selectedDescendantsMatch(withId(R.id.station),
                                withContentDescription("BP"))))
        );
    }
    @Test
    public void clickOnPositionTwo() {
        onView(withId(R.id.activity_stations_recyclerView)).perform(scrollToPosition(10));
        onView(withId(R.id.activity_stations_recyclerView)).perform(actionOnItemAtPosition(2, click()));

    }

}
