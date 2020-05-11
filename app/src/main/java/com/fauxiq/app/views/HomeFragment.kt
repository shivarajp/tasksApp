package com.fauxiq.app.views


import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.fauxiq.app.R
import com.fauxiq.app.data.local.TaskModel
import com.fauxiq.app.data.reposiotry.Status
import com.fauxiq.app.databinding.FragmentDetailsBinding
import com.fauxiq.app.viewmodels.MainViewModel
import com.google.android.material.textfield.TextInputEditText
import kotlinx.android.synthetic.main.fragment_home.*
import org.jetbrains.anko.toast


/**
 * A simple [Fragment] subclass.
 */
class HomeFragment : Fragment(), OnRowClickListener {

    private lateinit var mAdapter: TaskAdapter
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        loadTasks()

        floatingActionButton.setOnClickListener {
            showAddTaskDialog()
        }
    }

    private fun addTask(taskModel: TaskModel) {

        viewModel.addTask(taskModel).observe(viewLifecycleOwner, Observer {

            when (it.status) {

                Status.ERROR -> {
                    activity?.toast("Database Error")
                    progressBar.visibility = View.GONE

                }

                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE
                    activity?.toast("Task created successfully")
                    mAdapter.addTask(taskModel)
                    mAdapter.notifyDataSetChanged()
                    recyclerView.scrollToPosition(mAdapter.getSize() - 1)
                }

                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE

                }
            }

        })
    }

    private fun loadTasks() {

        viewModel.loadTasks().observe(viewLifecycleOwner, Observer {

            when (it.status) {

                Status.ERROR -> {
                    activity?.toast("Database Error")
                    progressBar.visibility = View.GONE

                }

                Status.SUCCESS -> {
                    progressBar.visibility = View.GONE

                    activity?.toast("Tasks loaded successfully")
                    mAdapter.clear()

                    it.data?.let { it1 -> mAdapter.addTasks(it1 as ArrayList<TaskModel>) }
                    mAdapter.notifyDataSetChanged()
                }

                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE

                }
            }
        })
    }

    private fun deleteTask(taskId: TaskModel) {

        viewModel.deleteTask(taskId).observe(viewLifecycleOwner, Observer {

            when (it.status) {

                Status.ERROR -> {
                    activity?.toast("Database Error")
                    progressBar.visibility = View.GONE
                }

                Status.SUCCESS -> {
                    loadTasks()
                    progressBar.visibility = View.GONE
                }

                Status.LOADING -> {
                    progressBar.visibility = View.VISIBLE
                }
            }

        })
    }

    private fun showAddTaskDialog() {

        val alert = AlertDialog.Builder(activity as MainActivity)
        alert.setTitle(R.string.add_task)
        alert.setCancelable(false)
        val dialogLayout = layoutInflater.inflate(R.layout.add_dialog_layout, null)
        alert.setView(dialogLayout)
        val name = dialogLayout?.findViewById<TextInputEditText>(R.id.nameTV)
        val rate = dialogLayout?.findViewById<TextInputEditText>(R.id.rateTv)
        val quantity = dialogLayout?.findViewById<TextInputEditText>(R.id.qtyTv)
        val amount = dialogLayout?.findViewById<TextInputEditText>(R.id.amountTv)

        quantity?.addTextChangedListener(object : TextWatcher {

            override fun afterTextChanged(qty: Editable?) {
                val quantity = qty.toString().toFloat()
                val rateItem = rate?.text.toString().toFloat()
                amount?.setText("${rateItem * quantity}")
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

            }

        })

        alert.setPositiveButton(R.string.add) { dialog, whichButton ->
            dialog.cancel()

            val name = name?.text.toString()
            val rate = rate?.text.toString()
            val quantity = quantity?.text.toString()
            val amount = amount?.text.toString()

            if (isEnteredDataIsValid(name, rate, quantity, amount)) {
                val task = TaskModel(0, name, rate.toFloat(), quantity.toFloat(), amount.toFloat())
                addTask(task)
            } else {
                activity?.toast("Please fill all fields")
            }

        }

        alert.setNegativeButton(R.string.cancel) { dialog, whichButton ->

            dialog.cancel()
            activity?.toast("Cancelled")

        }

        alert.show()
    }

    private fun isEnteredDataIsValid(
        name: String,
        rate: String,
        qty: String,
        amount: String
    ): Boolean {

        return name.isNotEmpty() &&
                rate.isNotEmpty() &&
                qty.isNotEmpty() &&
                amount.isNotEmpty()

    }


    fun gotoTaskDetails(taskModel: TaskModel) {
        val directions =
            HomeFragmentDirections.actionHomeFragmentToDetailsFragment(taskModel)
        NavHostFragment.findNavController(this).navigate(directions)
    }

    private fun initView() {

        viewModel = ViewModelProviders.of(activity as MainActivity).get(MainViewModel::class.java)
        val layoutManager = LinearLayoutManager(activity)
        recyclerView.layoutManager = layoutManager

        mAdapter = TaskAdapter(ArrayList(), this)
        recyclerView.adapter = mAdapter
    }



    override fun onDeleteClick(position: Int, taskId: TaskModel) {

        deleteTask(taskId)
    }

    override fun onCardClicked(position: Int, taskModel: TaskModel) {

        gotoTaskDetails(taskModel)
    }
}
