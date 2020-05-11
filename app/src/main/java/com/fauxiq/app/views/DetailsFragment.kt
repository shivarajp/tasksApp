package com.fauxiq.app.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.fauxiq.app.R
import com.fauxiq.app.data.local.TaskModel
import com.fauxiq.app.databinding.FragmentDetailsBinding
import com.fauxiq.app.databinding.TaskItemBinding
import kotlinx.android.synthetic.main.fragment_details.*

/**
 * A simple [Fragment] subclass.
 */
class DetailsFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding: FragmentDetailsBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_details,
            container,
            false
        )
        arguments?.let {
            val task = DetailsFragmentArgs.fromBundle(it).task
            binding.task = task
        }
        return binding.root
    }

}
