package com.example.navigationsample

import androidx.fragment.app.testing.FragmentScenario
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest
{
    //This becomes available only after you add dependencies
    //This is a part of Navigation testing dependencies
    private lateinit var navController: TestNavHostController
    private lateinit var fragOneScenario: FragmentScenario<FragmentOne>
    
    /**
     *@Before tag is used to do something before every test starts.
     * Anything annotated with @Before will before every test.
     */
    @Before
    fun setup()
    {
        //instantiate Test Nav Host Controller
        //This controls the navigation between fragments
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        
        //instantiate a scenario where you launch FragmentOne in a Container to keep it completely separate from the application
        //this lets you access the fragment independent of an activity.
        fragOneScenario = launchFragmentInContainer<FragmentOne>(themeResId = R.style.Theme_NavigationSample)
        
        //once we setup the fragment scenario where we launch the fragment in a container then we need to tell it how to behave
        //hence we give it which nav graph to follow
        fragOneScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }
    
    /**
     * this tests the navigation from one frag to the other by clicking the button on the frag one.
     * the assertEquals check if the destination id after clicking the button matches the other one.
     */
    @Test
    fun navigate_fragmentone_to_fragmenttwo()
    {
        onView(withId(R.id.btn_click_one))
            .perform(click())
        
        assertEquals(navController.currentDestination?.id, R.id.fragmentTwo)
    }
}