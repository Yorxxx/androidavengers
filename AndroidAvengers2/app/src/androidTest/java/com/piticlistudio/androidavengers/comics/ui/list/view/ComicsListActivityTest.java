package com.piticlistudio.androidavengers.comics.ui.list.view;

import android.app.Instrumentation;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.piticlistudio.androidavengers.AndroidApplication;
import com.piticlistudio.androidavengers.CustomMatchers;
import com.piticlistudio.androidavengers.R;
import com.piticlistudio.androidavengers.comics.ComicsFactory;
import com.piticlistudio.androidavengers.comics.MarvelComicComponent;
import com.piticlistudio.androidavengers.comics.model.entity.Comic;
import com.piticlistudio.androidavengers.comics.ui.list.adapter.ComicListAdapter;
import com.piticlistudio.androidavengers.comics.ui.list.presenter.ComicsListPresenter;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.core.IsNot.not;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Test case for ComicsListActivity
 * Created by jorge.garcia on 13/07/2016.
 */
@RunWith(AndroidJUnit4.class)
public class ComicsListActivityTest {

    @Rule
    public ActivityTestRule<ComicsListActivity> activityTestRule = new ActivityTestRule<>(ComicsListActivity
            .class, true, false);

    @Mock
    MarvelComicComponent comicComponent;

    @Mock
    ComicsListPresenter presenter;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        Instrumentation instrumentation = InstrumentationRegistry.getInstrumentation();
        AndroidApplication app = (AndroidApplication) instrumentation.getTargetContext().getApplicationContext();
        when(comicComponent.listPresenter()).thenReturn(presenter);
        when(comicComponent.listAdapter()).thenReturn(new ComicListAdapter(app.getApplicationComponent().picasso()));
        app.setComicComponent(comicComponent);
        activityTestRule.launchActivity(new Intent());
    }

    @Test
    public void viewsStatesAreHiddenOnStart() {

        // Assert
        onView(withId(R.id.loadingView)).check(matches(CustomMatchers.withAlpha(0)));
        onView(withId(R.id.errorview)).check(matches(not(isDisplayed())));
        onView(withId(R.id.emptyview)).check(matches(not(isDisplayed())));
    }

    @Test
    public void requestsDataToPresenterOnStart() {

        // Assert
        verify(presenter).loadData("1009215", false);
    }

    @Test
    public void showsLoading() throws InterruptedException {
        activityTestRule.getActivity().runOnUiThread(() -> {
            activityTestRule.getActivity().showLoading(false);
        });

        Thread.sleep(1000);
        onView(withId(R.id.loadingView)).check(matches(CustomMatchers.withAlpha(1)));
        onView(withId(R.id.errorview)).check(matches(not(isDisplayed())));
        onView(withId(R.id.emptyview)).check(matches(not(isDisplayed())));
    }

    @Test
    public void showsNonBlockingError() throws InterruptedException {

        // Change viewstate
        List<Comic> data = new ArrayList<>();
        data.add(ComicsFactory.generate("title1"));
        data.add(ComicsFactory.generate("title2"));
        data.add(ComicsFactory.generate("title3"));
        activityTestRule.getActivity().runOnUiThread(() -> {
            activityTestRule.getActivity().setData(data);
            activityTestRule.getActivity().showContent();
        });


        Thread.sleep(1000);

        // Act
        Exception error = new Exception("bla");
        activityTestRule.getActivity().runOnUiThread(() -> {
            activityTestRule.getActivity().showError(error, true);
        });

        Thread.sleep(1000);
        onView(withId(R.id.loadingView)).check(matches(CustomMatchers.withAlpha(0)));
        onView(withId(R.id.errorview)).check(matches(not(isDisplayed())));
        onView(withText("bla")).check(matches(isDisplayed()));
        onView(withText("title1")).check(matches(isDisplayed()));
    }

    @Test
    public void showsBlockingError() throws InterruptedException {

        // Change viewstate
        activityTestRule.getActivity().runOnUiThread(() -> {
            activityTestRule.getActivity().showLoading(false);
        });

        Thread.sleep(1000);

        // Act
        Exception error = new Exception("bla");
        activityTestRule.getActivity().runOnUiThread(() -> {
            activityTestRule.getActivity().showError(error, false);
        });

        Thread.sleep(1000);
        onView(withId(R.id.loadingView)).check(matches(CustomMatchers.withAlpha(0)));
        onView(withId(R.id.errorview)).check(matches(isDisplayed()));
    }

    @Test
    public void showsData() throws InterruptedException {

        // Change viewstate
        activityTestRule.getActivity().runOnUiThread(() -> {
            activityTestRule.getActivity().showLoading(false);
        });

        Thread.sleep(1000);

        // Act
        List<Comic> data = new ArrayList<>();
        data.add(ComicsFactory.generate("title1"));
        data.add(ComicsFactory.generate("title2"));
        data.add(ComicsFactory.generate("title3"));
        activityTestRule.getActivity().runOnUiThread(() -> {
            activityTestRule.getActivity().setData(data);
            activityTestRule.getActivity().showContent();
        });


        Thread.sleep(1000);
        onView(withId(R.id.loadingView)).check(matches(CustomMatchers.withAlpha(0)));
        onView(withId(R.id.errorview)).check(matches(not(isDisplayed())));
        onView(withId(R.id.emptyview)).check(matches(not(isDisplayed())));
        onView(withText("title1")).check(matches(isDisplayed()));
    }

    @Test
    public void showsEmptyData() throws InterruptedException {
        // Change viewstate
        activityTestRule.getActivity().runOnUiThread(() -> {
            activityTestRule.getActivity().showLoading(false);
        });

        Thread.sleep(1000);

        // Act
        List<Comic> data = new ArrayList<>();
        activityTestRule.getActivity().runOnUiThread(() -> {
            activityTestRule.getActivity().setData(data);
            activityTestRule.getActivity().showContent();
        });


        Thread.sleep(1000);
        onView(withId(R.id.loadingView)).check(matches(CustomMatchers.withAlpha(0)));
        onView(withId(R.id.errorview)).check(matches(not(isDisplayed())));
        onView(withId(R.id.emptyview)).check(matches(isDisplayed()));
    }
}
