package com.example.navigationsample

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.navigationsample.databinding.FragmentOneBinding

class FragmentOne : Fragment()
{
    private val TAG = FragmentOne::class.java.simpleName
    //create nullable _binding reference when any variable is prefixed with '_' that means it is not to be used publicly
    //create another binding variable which stores the non null binding reference to be used in the file.
    private var _binding: FragmentOneBinding? = null
    private val binding get() = _binding!!
    
    //A View (Fragment/Activity) should have ONE WAY reference to ViewModel.
    //that instance should be taken care of by viewModels() delegating class. (it should not be our responsibility)
    //Format : <variable_name>: <class_name(that extends ViewModel)> by <delegating_class_name(class that does the delegation work)>
    private val viewModel: FragmentOneViewModel by viewModels()
    
    /**
     * onCreate for Fragment happens way before the view of the fragment is created.
     * Hence, we need to initialize the _binding variable in onCreateView where the View is actually created.
     */
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
    }
    
    /**
     * This is where the view actually gets created and hence we initialize the _binding variable here.
     * onCreateView is where you do the binding
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        //once you have made the xml file follow the data binding layout format
        //then you can use DataBindingUtil here to inflate the layouts
        //Why use DataBindingUtil over viewBinding --> it gives ability to do two-way binding
        //which in turn makes code be observed directly from the xml file.
        _binding = DataBindingUtil.inflate(inflater, R.layout.fragment_one, container, false)
    
        Log.d(TAG, "onCreateView: ")
        
        return binding.root
    }
    
    /**
     * This is where the view is fully generated and ready to be used
     * This is where you map the components on the screen with the Kotlin file
     *
     * onViewCreated is where you use the UI elements, set onclick listeners etc.
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        binding.btnClickOne.setOnClickListener {
            val action = FragmentOneDirections.actionFragmentOneToFragmentTwo()
            findNavController().navigate(action)
        }
        
        //This right here connects the variables in the xml file with this file
        // this gives them the power of two - way - binding and self - observing
        binding.lifecycleOwner = viewLifecycleOwner
        binding.fragOneViewModel = viewModel
        
        //This call starts the observer --> needs to be done only once after the view has been created.
        // it is either this or the two-way binding in xml file. (both do the same thing)
//        viewModel.count.observe(viewLifecycleOwner) {
//            num -> binding.tvFragOneCounter.text = getString(R.string.txt_count, num)
//        }
//
        //Set on click listeners on the UI element which in this case is a button
        //it is a UI element so it has to happen in Fragment/Activity
        //But the work it is supposed to do, whatever part of that work does not involve UI
        //should be happening in a function in the ViewModel.
        binding.btnFragOneClicker.setOnClickListener {
            viewModel.updateCounter()
        }
    }
    
    /**
     * Always remember to destroy the object of binding you created before exiting the fragment
     * How? by assigning it to null before Destroy happens which is in onDestroyView
     */
    override fun onDestroyView()
    {
        super.onDestroyView()
        _binding = null
    }
}