package com.android.chaldhal.ui.userlist

import AGE
import FIRST
import LAST
import TITLE
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import com.android.chaldhal.R
import com.android.chaldhal.ui.base.BaseFragment
import com.android.chaldhal.databinding.FragmentHomeBinding
import com.android.chaldhal.ui.listeners.HomeDataListener
import com.android.chaldhal.data.models.Result
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(),HomeDataListener {

    private val homeViewModel: HomeViewModel by viewModels()
    private var homeDataAdapter: HomeDataAdapter = HomeDataAdapter(this)



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()
        homeDataRequest()
        observeHomeData()

    }

    private fun initRecyclerView() {
        binding.rvHome.adapter = homeDataAdapter
    }

    private fun observeHomeData() {
        viewLifecycleOwner.lifecycleScope.launch {
            homeViewModel.getHomeList().observe(viewLifecycleOwner, {
                homeDataAdapter.submitData(lifecycle, it)
            })
        }
    }

    private fun homeDataRequest() {
        homeViewModel.homeDataRequest()
    }




    override fun onItemClick(position: Int, item: Result?, view: View) {
        val bundle = Bundle()
        bundle.putString(TITLE,item?.name?.title)
        bundle.putString(FIRST,item?.name?.first)
        bundle.putString(LAST,item?.name?.last)
        item?.dob?.age?.let { bundle.putInt(AGE, it) }
        Navigation.findNavController(view).navigate(R.id.action_homeFragment_to_detailsFragment,bundle)
    }

    override fun getFragmentBinding(inflater: LayoutInflater, container: ViewGroup?) =
        FragmentHomeBinding.inflate(inflater, container, false)
}