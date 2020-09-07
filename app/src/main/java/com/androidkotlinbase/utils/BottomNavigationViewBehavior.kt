package com.androidkotlinbase.utils

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.bottomnavigation.BottomNavigationView

class BottomNavigationViewBehavior(context: Context, attributeSet: AttributeSet) : CoordinatorLayout.Behavior<BottomNavigationView>(context, attributeSet) {

    private val height: Int = AppHelper.getToolbarHeight(context)

    override fun layoutDependsOn(parent: CoordinatorLayout, child: BottomNavigationView, dependency: View): Boolean {
        return dependency is AppBarLayout
    }

    override fun onDependentViewChanged(parent: CoordinatorLayout, child: BottomNavigationView, dependency: View): Boolean {
        if (dependency is AppBarLayout) {
            val lp = child.layoutParams as CoordinatorLayout.LayoutParams
            val bottomMargin = lp.bottomMargin
            val distanceToScroll = child.height + bottomMargin
            val ratio = dependency.y / height
            child.translationY = -distanceToScroll * ratio
        }
        return true
    }
}