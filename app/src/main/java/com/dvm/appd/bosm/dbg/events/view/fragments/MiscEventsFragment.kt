package com.dvm.appd.bosm.dbg.events.view.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dvm.appd.bosm.dbg.MainActivity
import com.dvm.appd.bosm.dbg.R
import com.dvm.appd.bosm.dbg.events.view.adapters.MiscDayAdapter
import com.dvm.appd.bosm.dbg.events.view.adapters.MiscEventsAdapter
import com.dvm.appd.bosm.dbg.events.viewmodel.MiscEventsViewModel
import com.dvm.appd.bosm.dbg.events.viewmodel.MiscEventsViewModelFactory
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fra_misc_events.view.*
import java.text.SimpleDateFormat
import java.util.*

class MiscEventsFragment : Fragment(), MiscEventsAdapter.OnMarkFavouriteClicked, MiscDayAdapter.OnDaySelected {

    private lateinit var miscEventsViewViewModel: MiscEventsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        miscEventsViewViewModel = ViewModelProviders.of(this, MiscEventsViewModelFactory())[MiscEventsViewModel::class.java]

        val view = inflater.inflate(R.layout.fra_misc_events, container, false)

        (activity!! as MainActivity).hideCustomToolbarForLevel2Fragments()


        val sdf = SimpleDateFormat("dd MM yyyy")
        val c = Calendar.getInstance()

        when(sdf.format(c.time)){
            "13 09 2019" -> {
                (miscEventsViewViewModel.daySelected as MutableLiveData).postValue("Day 1")
                miscEventsViewViewModel.getMiscEventsData("Day 1")
            }

            "14 09 2019" -> {
                (miscEventsViewViewModel.daySelected as MutableLiveData).postValue("Day 2")
                miscEventsViewViewModel.getMiscEventsData("Day 2")
            }

            "15 09 2019" -> {
                (miscEventsViewViewModel.daySelected as MutableLiveData).postValue("Day 3")
                miscEventsViewViewModel.getMiscEventsData("Day 3")
            }

            "16 09 2019" -> {
                (miscEventsViewViewModel.daySelected as MutableLiveData).postValue("Day 4")
                miscEventsViewViewModel.getMiscEventsData("Day 4")
            }

            "17 09 2019" -> {
                (miscEventsViewViewModel.daySelected as MutableLiveData).postValue("Day 5")
                miscEventsViewViewModel.getMiscEventsData("Day 5")
            }

            else -> {
                (miscEventsViewViewModel.daySelected as MutableLiveData).postValue("Day 1")
                miscEventsViewViewModel.getMiscEventsData("Day 1")
            }
        }


        view.dayRecycler.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        view.dayRecycler.adapter = MiscDayAdapter(this)
        miscEventsViewViewModel.eventDays.observe(this, Observer {
            Log.d("MiscEventsFrag", "Observed")
            (view.dayRecycler.adapter as MiscDayAdapter).miscDays = it
            (view.dayRecycler.adapter as MiscDayAdapter).notifyDataSetChanged()
        })

        view.miscEventRecycler.adapter = MiscEventsAdapter(this)
        miscEventsViewViewModel.miscEvents.observe(this, Observer {
            Log.d("MiscEventsFrag", "Observed")
            (view.miscEventRecycler.adapter as MiscEventsAdapter).miscEvents = it
            (view.miscEventRecycler.adapter as MiscEventsAdapter).notifyDataSetChanged()
        })

        miscEventsViewViewModel.daySelected.observe(this, Observer {

            (view.dayRecycler.adapter as MiscDayAdapter).daySelected = it
            (view.dayRecycler.adapter as MiscDayAdapter).notifyDataSetChanged()
        })

        view.backBtn.setOnClickListener {
            it.findNavController().popBackStack()
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        activity!!.mainView.isVisible = true
        activity!!.fragmentName.isVisible = true
        activity!!.cart.isVisible = true
        activity!!.profile.isVisible = true
        activity!!.notifications.isVisible = true
    }

    override fun updateIsFavourite(eventId: String, favouriteMark: Int) {
        miscEventsViewViewModel.markEventFavourite(eventId, favouriteMark)
    }

    override fun daySelected(day: String, position: Int) {
        (miscEventsViewViewModel.daySelected as MutableLiveData).postValue(day)
        miscEventsViewViewModel.currentSubsciption.dispose()
        miscEventsViewViewModel.getMiscEventsData(day)
        view!!.dayRecycler.smoothScrollToPosition(position)
    }
}
