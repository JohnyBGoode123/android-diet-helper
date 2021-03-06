package com.example.diethelperapp.dietlist

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.diethelperapp.databinding.FragmentDietplanBinding
import com.example.diethelperapp.db2.App
import com.example.diethelperapp.dietplan.DietPlanAdapter
import com.example.diethelperapp.dietplan.DietPlanButtonClickNavigator
import com.example.diethelperapp.dietplan.DietPlanRepository
import com.example.diethelperapp.dietplan.DietPlanViewModel
import kotlinx.android.synthetic.main.fragment_dietlist.*

class DietPlanFragment :  Fragment(), DietPlanButtonClickNavigator  {

    val args: DietPlanFragmentArgs by navArgs()

    private val viewModel: DietPlanViewModel by viewModels {
        object: ViewModelProvider.Factory {
            override fun <T : ViewModel?> create(modelClass: Class<T>): T =
                DietPlanViewModel(args.dietId, App.repositories.calendar(), this@DietPlanFragment,mContext) as T
        }
    }

    private lateinit var dataBinding: FragmentDietplanBinding
    private  var mContext: Context? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onDetach() {
        super.onDetach()
        mContext = null
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dataBinding = FragmentDietplanBinding.inflate(inflater, container, false)
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.navigator = this
        dataBinding.viewModel = viewModel
        dataBinding.lifecycleOwner = viewLifecycleOwner

        recyclerViewDiet.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.HORIZONTAL, false)


        val dietPlanObserver = Observer<List<DietPlanRepository.DietPlanDay>> { it ->
            recyclerViewDiet.adapter = DietPlanAdapter(it, viewModel)

        }
        viewModel.dietPlan.observe(viewLifecycleOwner, dietPlanObserver)
    }

    override fun onRecipeAddClick(day: Int, timeOfDay: Int) {
            val action = DietPlanFragmentDirections
                .actionDietPlanFragmentToRecipeListFragment(day, timeOfDay, 1)
            this.findNavController().navigate(action)
        }

    override fun onRecipeRemoveClick(day: Int, timeOfDay: Int) {
        TODO("Not yet implemented")
    }

    override fun onRecipeReplaceClick(day: Int, timeOfDay: Int) {
        TODO("Not yet implemented")
    }
}