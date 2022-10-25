package com.example.navigationsample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.navigationsample.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity()
{
    
    //create NavController object should be lateinit var to map with navFragment
    //as NavController will control the movements of NavFragment
    private lateinit var navController: NavController
    private lateinit var binding: ActivityMainBinding
    
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        
        //map UI of main activity --> fragment container
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fcv_fragment_container) as NavHostFragment
        navController = navHostFragment.navController
        
        //updates the app bar along with changing fragment
        setupActionBarWithNavController(navController)
    }
    
    /**
     * This takes care of the back button from any fragment that is on the screen.
     */
    override fun onSupportNavigateUp(): Boolean
    {
        return navController.navigateUp() || super.onSupportNavigateUp()
    }
}