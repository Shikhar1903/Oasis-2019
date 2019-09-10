package com.dvm.appd.bosm.dbg.wallet.views.fragments

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import com.dvm.appd.bosm.dbg.MainActivity
import com.dvm.appd.bosm.dbg.R
import com.dvm.appd.bosm.dbg.wallet.data.room.dataclasses.StallData
import com.dvm.appd.bosm.dbg.wallet.viewmodel.StallsViewModel
import com.dvm.appd.bosm.dbg.wallet.viewmodel.StallsViewModelFactory
import com.dvm.appd.bosm.dbg.wallet.views.StallResult
import com.dvm.appd.bosm.dbg.wallet.views.adapters.StallsAdapter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fra_wallet_stalls.view.*
import android.util.DisplayMetrics
import android.view.*


class StallsFragment : Fragment(), StallsAdapter.OnStallSelectedListener {

    private lateinit var stallsViewModel: StallsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        stallsViewModel = ViewModelProviders.of(this, StallsViewModelFactory())[StallsViewModel::class.java]

        val rootview = inflater.inflate(R.layout.fra_wallet_stalls, container, false)
        //activity!!.my_toolbar.setBackgroundResource(R.drawable.gradient_stalls_toolbar)
        activity!!.mainView.setBackgroundResource(R.drawable.stalls_title)
        activity!!.fragmentName.text = "Stalls"
        rootview.stalls_recycler.adapter = StallsAdapter(this)
        activity!!.search.isVisible = false
        activity!!.textView7.isVisible = true
        activity!!.textView7.text = "To flavor your taste buds"
        activity!!.textView7.setTextColor(Color.rgb(5, 197, 109))

        activity!!.cart.setOnClickListener {
            this.findNavController().navigate(R.id.action_action_food_to_action_cart)
        }

        activity!!.profile.setOnClickListener {
            this.findNavController().navigate(R.id.action_action_food_to_action_profile)
        }

        activity!!.notifications.setOnClickListener {
            this.findNavController().navigate(R.id.action_action_food_to_notificationFragment)
        }

        stallsViewModel.stalls.observe(this, Observer {
            (rootview.stalls_recycler.adapter as StallsAdapter).stalls = it
            (rootview.stalls_recycler.adapter as StallsAdapter).notifyDataSetChanged()
        })

        stallsViewModel.result.observe(this, Observer {
            when (it!!) {
                StallResult.Success -> {
                    rootview.progressBar.visibility = View.GONE
                }
                StallResult.Failure -> {
                    rootview.progressBar.visibility = View.VISIBLE
                }
            }
        })

        return rootview
    }

        override fun stallSelected(stall: StallData) {
        val bundle = bundleOf("stallId" to stall.stallId, "stallName" to stall.stallName)
        view!!.findNavController().navigate(com.dvm.appd.bosm.dbg.R.id.action_action_food_to_stallItemsFragment2, bundle)
        // view!!.findNavController().navigate(StallsFragmentDirections.actionActionFoodToStallItemsFragment2(stallId))
    }

    override fun onResume() {
        (activity!! as MainActivity).showCustomToolbar()
        super.onResume()
    }
}