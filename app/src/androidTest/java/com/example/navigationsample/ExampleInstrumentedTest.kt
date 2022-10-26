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
    
    @Before
    fun setup()
    {
        navController = TestNavHostController(ApplicationProvider.getApplicationContext())
        fragOneScenario = launchFragmentInContainer<FragmentOne>(themeResId = R.style.Theme_NavigationSample)
        
        fragOneScenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }
    
    @Test
    fun navigate_fragmentone_to_fragmenttwo()
    {
        onView(withId(R.id.btn_click_one))
            .perform(click())
        
        assertEquals(navController.currentDestination?.id, R.id.fragmentTwo)
    }
}