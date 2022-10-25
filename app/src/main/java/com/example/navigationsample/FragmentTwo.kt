package com.example.navigationsample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.navigationsample.databinding.FragmentTwoBinding

class FragmentTwo : Fragment()
{
    //create nullable _binding reference when any variable is prefixed with '_' that means it is not to be used publicly
    //create another binding variable which stores the non null binding reference to be used in the file.
    private var _binding: FragmentTwoBinding? = null
    private val binding get() = _binding!!
    
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
     */
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View?
    {
        _binding = FragmentTwoBinding.inflate(inflater, container, false)
        return binding.root
    }
    
    /**
     * This is where the view is fully generated and ready to be used
     * This is where you map the components on the screen with the Kotlin file
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?)
    {
        super.onViewCreated(view, savedInstanceState)
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