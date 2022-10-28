package com.example.navigationsample

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

/**
 * ViewModel: it is a lifecycle aware architecture component
 * it saves the data and keeps it safe even when the view(activity/fragment) dies
 *
 * This is going to maintain data for Fragment One.
 *
 * Step 1 : Make a view model class and extend ViewModel()
 */
class FragmentOneViewModel : ViewModel()
{
    private val TAG = FragmentOneViewModel::class.java.simpleName
    
    //add a backing property for the variable that you want to use.
    //Backing property lets the variable be private to that own class which can update and manipulate it
    //and also makes it visible / gettable for other classes other classes can not manipulate that variable
    //Make the private var of type MutableLiveData
    private var _count = MutableLiveData<Int> (0)
    //make the public val of type LiveData
    //because LiveData is not Mutable and this variable should be private and hence should be immutable to outside classes.
    val count: LiveData<Int> get() = _count
    
    /**
     * Updates the counter value and keeps it here.
     * to show this on screen this function will be accessed by some view(activity/fragment)
     * and displayed from that view on to the screen.
     */
    fun updateCounter()
    {
        _count.value = _count.value?.plus(1)
    }
}