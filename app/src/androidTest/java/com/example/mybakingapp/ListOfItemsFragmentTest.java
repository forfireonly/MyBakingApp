package com.example.mybakingapp;

import android.support.test.rule.ActivityTestRule;
import android.view.View;
import android.widget.RelativeLayout;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static junit.framework.TestCase.assertNotNull;

public class ListOfItemsFragmentTest {

    @Rule
    public ActivityTestRule<TestActivity> mActivityTestRule = new ActivityTestRule<TestActivity>(TestActivity.class);

    private TestActivity mActivity = null;

    @Before
    public void setUp() throws Exception {

        mActivity = mActivityTestRule.getActivity();
    }

    @Test
    public void testLaunch(){

        // test if fragment launched or not
        RelativeLayout rlContainer = mActivity.findViewById(R.id.test_container);
        assertNotNull(rlContainer);

        ListOfItemsFragment fragment = new ListOfItemsFragment();
        mActivity.getSupportFragmentManager().beginTransaction().replace(rlContainer.getId(), fragment).commitAllowingStateLoss();

        getInstrumentation().waitForIdleSync();

        View view=fragment.getView().findViewById(R.id.recycler_view);

        assertNotNull(view);
    }

    @After
    public void tearDown() throws Exception {

        mActivity = null;
    }
}