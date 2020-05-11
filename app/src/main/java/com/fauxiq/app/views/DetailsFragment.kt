package com.fauxiq.app.views


import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import com.fauxiq.app.R
import com.fauxiq.app.data.local.TaskModel
import com.fauxiq.app.databinding.FragmentDetailsBinding
import com.fauxiq.app.databinding.TaskItemBinding
import kotlinx.android.synthetic.main.fragment_details.*
import org.jetbrains.anko.toast

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

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val actionbar = (activity as MainActivity).supportActionBar
        actionbar?.setDisplayShowHomeEnabled(true)
        actionbar?.setDisplayHomeAsUpEnabled(true)
        actionbar?.show()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                activity?.toast("hell")
                activity?.onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
    }

}
