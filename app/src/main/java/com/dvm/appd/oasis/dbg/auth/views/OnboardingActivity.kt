package com.dvm.appd.oasis.dbg.auth.views

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import com.dvm.appd.oasis.dbg.MainActivity
import com.dvm.appd.oasis.dbg.R
import kotlinx.android.synthetic.main.activity_onboarding.*
import kotlinx.android.synthetic.main.fragment_onboarding1.*

class OnboardingActivity : AppCompatActivity(), onboardingFragmentButtonClickListener {


    lateinit var pageAdapter: FragmentStatePagerAdapter
    val NO_OF_PAGES = 4

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
        pageAdapter = object : FragmentStatePagerAdapter(supportFragmentManager) {
            override fun getItem(position: Int): Fragment {
                when (position) {
                    0 -> {

                        return Onboarding1Fragment(
                            this@OnboardingActivity,
                            R.drawable.ic_undraw_happy_feeling_slmw,
                            "Kind Store",
                            resources.getColor(R.color.OnBoarding_colour),
                            resources.getString(R.string.onBoarding_kind)
                            )
                    }

                    1 -> {

                        return Onboarding1Fragment(
                            this@OnboardingActivity,
                            R.drawable.ic_undraw_discount_d4bd,
                            "Discounts",
                            resources.getColor(R.color.OnBoarding_colour),
                            resources.getString(R.string.onBoarding_discounts)
                        )
                    }

                    2 -> {

                        return Onboarding1Fragment(
                            this@OnboardingActivity,
                            R.drawable.ic_undraw_referral_4ki4,
                            "Referral",
                            resources.getColor(R.color.OnBoarding_colour),
                            resources.getString(R.string.onBoarding_referral)
                        )
                    }

                    3 -> {

                        return Onboarding1Fragment(
                            this@OnboardingActivity,
                            R.drawable.ic_undraw_order_confirmed_1m3v,
                            "Order Food",
                            resources.getColor(R.color.OnBoarding_colour),
                            resources.getString(R.string.onBoarding_Order)
                        )
                    }

                    4 ->{
                        return Onboarding1Fragment(
                            this@OnboardingActivity,
                            R.drawable.ic_undraw_events_2p66,
                            "Track Events",
                            resources.getColor(R.color.OnBoarding_colour),
                            resources.getString(R.string.onBoarding_Order)
                        )
                    }
                }
                return Onboarding1Fragment(
                    this@OnboardingActivity,
                    R.drawable.ic_undraw_happy_feeling_slmw,
                    "Kind Store",
                    resources.getColor(R.color.OnBoarding_colour),
                    resources.getString(R.string.onBoarding_kind)
                )

            }

            override fun getCount(): Int {
                return NO_OF_PAGES
            }
        }
        viewPager_onBoarding.adapter = pageAdapter
        viewPager_onBoarding.setOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(state: Int) {
                Log.d("Onboarding Activity", "State Variable = $state")
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
                Log.d("Onboarding Activity", "Position1 Variable = $position")
            }

            override fun onPageSelected(position: Int) {
                Log.d("Onboarding Activity", "Position Variable = $position")
                if (position == (NO_OF_PAGES - 1)) {
                    Log.d("Onboarding Activity", "Entered if condition")
                    this@OnboardingActivity.bttn_next_onBoarding.text = "Done"
                } else {
                    this@OnboardingActivity.bttn_next_onBoarding.text = "Next"
                }
            }

        })
    }

    override fun onSkipButtonPressed() {
        finishOnBoarding()
    }


    private fun finishOnBoarding() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun onNextButtonClicked() {
        if (viewPager_onBoarding.currentItem == 3) {
            //bttn_next_onBoarding.isClickable = false
            bttn_next_onBoarding.text ="Done"
            Log.d("checkon","called")
            finishOnBoarding()
        } else {
            viewPager_onBoarding.setCurrentItem(viewPager_onBoarding.currentItem + 1, false)
        }
    }
}
