package com.example.adaptivecard.custom

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.fragment.app.FragmentManager
import com.example.adaptivecard.R
import io.adaptivecards.objectmodel.BaseCardElement
import io.adaptivecards.objectmodel.HostConfig
import io.adaptivecards.renderer.BaseCardElementRenderer
import io.adaptivecards.renderer.RenderArgs
import io.adaptivecards.renderer.RenderedAdaptiveCard
import io.adaptivecards.renderer.actionhandler.ICardActionHandler

class MyCardElementRenderer : BaseCardElementRenderer() {
    override fun render(
        p0: RenderedAdaptiveCard?,
        context: Context,
        p2: FragmentManager?,
        viewGroup: ViewGroup,
        baseCardElement: BaseCardElement,
        p5: ICardActionHandler?,
        p6: HostConfig?,
        p7: RenderArgs?
    ): View {
        val element = baseCardElement.findImplObj() as MyCustomCardElement
        val tv = TextView(context)
        tv.text = element.myTypeData
        tv.isAllCaps = true
        viewGroup.setBackgroundColor(ContextCompat.getColor(context, R.color.teal_200))
        viewGroup.addView(tv)
        return tv
    }
}