package com.dvm.appd.bosm.dbg.auth.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager

import com.dvm.appd.bosm.dbg.R
import kotlinx.android.synthetic.main.fragment_onboarding1.*

interface onboardingFragmentButtonClickListener {
    fun onSkipButtonPressed()
    fun onNextButtonClicked()
}

class Onboarding1Fragment(val listener: onboardingFragmentButtonClickListener, val image: Int, val heading: String, val background:Int) : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_onboarding1, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        activity!!.window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        activity!!.window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        activity!!.window.statusBarColor = background
        img_onBoarding.setImageDrawable(resources.getDrawable(image))
        text_onBoarding_heading.text = heading
        parent.setBackgroundColor(background)
        bttn_next_onBoarding.setTextColor(background)
        text_bttn_skip.setOnClickListener {
            it.isClickable = false
            listener.onSkipButtonPressed()
        }
        bttn_next_onBoarding.setOnClickListener {
            it.isClickable = false
            listener.onNextButtonClicked()
        }
        super.onViewCreated(view, savedInstanceState)
    }

}
