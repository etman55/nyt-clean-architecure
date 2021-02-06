package com.example.nytcleanarcitecture.base

import androidx.annotation.Keep
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter

/**
 * @author Atef Etman
 * @email etman850@gmail.com
 * @phone +201090705106
 */

class PagesAdapter(
    fm: FragmentManager,
    sectionPages: LinkedHashSet<SectionPage>
) : FragmentStatePagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    private val sectionsPagesList = sectionPages.toList()
    override fun getItem(position: Int): Fragment = sectionsPagesList[position].fragment
    override fun getCount(): Int = sectionsPagesList.size
    override fun getPageTitle(position: Int): CharSequence? = sectionsPagesList[position].title
}

@Keep
data class SectionPage(
    val fragment: Fragment,
    val title: String
) {
    private val fragmentName = fragment.javaClass.simpleName

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as SectionPage

        if (title != other.title) return false
        if (fragmentName != other.fragmentName) return false

        return true
    }

    override fun hashCode(): Int {
        var result = fragment.hashCode()
        result = 31 * result + fragmentName.hashCode()
        return result
    }
}
