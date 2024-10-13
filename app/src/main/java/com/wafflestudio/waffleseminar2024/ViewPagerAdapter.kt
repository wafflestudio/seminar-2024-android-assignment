package com.wafflestudio.waffleseminar2024

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.wafflestudio.waffleseminar2024.viewPagerFragments.SearchFragment
import com.wafflestudio.waffleseminar2024.viewPagerFragments.AppFragment
import com.wafflestudio.waffleseminar2024.viewPagerFragments.GameFragment
import com.wafflestudio.waffleseminar2024.viewPagerFragments.UserInformationFragment

class ViewPagerAdapter(fragmentActivity: FragmentActivity, private val slackWorkspaceUrl: String) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int {
        return 4
    }

    override fun createFragment(position: Int): Fragment {
        return when (position) {
            0 -> GameFragment()
            1 -> AppFragment()
            2 -> SearchFragment()
            3 -> {
                val fragment = UserInformationFragment()
                val bundle = Bundle()
                bundle.putString("slackWorkspaceUrl", slackWorkspaceUrl)
                fragment.arguments = bundle
                fragment
            }
            else -> throw IllegalStateException("Unexpected position $position")
        }
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

}
