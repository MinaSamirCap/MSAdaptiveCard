package com.example.adaptivecard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.adaptivecard.custom.MyCardElementParser
import com.example.adaptivecard.custom.MyCardElementRenderer
import io.adaptivecards.objectmodel.*
import io.adaptivecards.renderer.AdaptiveCardRenderer
import io.adaptivecards.renderer.RenderedAdaptiveCard
import io.adaptivecards.renderer.actionhandler.ICardActionHandler
import io.adaptivecards.renderer.registration.CardRendererRegistration


class MainActivity : AppCompatActivity(), ICardActionHandler {


    lateinit var constraintLayout: ConstraintLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        constraintLayout = findViewById(R.id.constraintLayout)
        //constraintLayout.addView(renderCard())
        constraintLayout.addView(customParser())
    }

    private fun customParser(): View? {
        val elementParser = ElementParserRegistration().apply {
            AddParser(myType, MyCardElementParser())
        }
        val contxt = ParseContext()
        contxt.elementParserRegistration = elementParser

        CardRendererRegistration.getInstance().registerRenderer(myType, MyCardElementRenderer())

        val parseResult =
            AdaptiveCard.DeserializeFromString(myJson, AdaptiveCardRenderer.VERSION, contxt)
        val renderedCard = AdaptiveCardRenderer.getInstance()
            .render(
                this, supportFragmentManager,
                parseResult.GetAdaptiveCard(),
                this, HostConfig()
            )
        return renderedCard.view

    }

    private fun renderCard(): View? {
        val contxt = ParseContext()
        val parseResult =
            AdaptiveCard.DeserializeFromString(ex1, AdaptiveCardRenderer.VERSION, contxt)

        val txtConf = TextConfig()
        txtConf.size = TextSize.ExtraLarge
        txtConf.color = ForegroundColor.Warning

        val setFact = FactSetConfig()
        setFact.title = txtConf

        val hostConfig = HostConfig()
        hostConfig.SetFactSet(setFact)
        hostConfig.GetContainerStyles().goodPalette.backgroundColor = "#FF00FF00"

        val hostConf = HostConfig.DeserializeFromString(hostConfJson)

        val renderedCard = AdaptiveCardRenderer.getInstance()
            .render(
                this, supportFragmentManager,
                parseResult.GetAdaptiveCard(),
                this, hostConf
            )
        return renderedCard.view
    }

    override fun onAction(actionElement: BaseActionElement, renderedCard: RenderedAdaptiveCard) {
        when (actionElement.GetElementType()) {
            ActionType.OpenUrl -> onOpenUrl(actionElement)
        }
    }

    override fun onMediaPlay(actionElement: BaseCardElement, renderedCard: RenderedAdaptiveCard) {

    }

    override fun onMediaStop(actionElement: BaseCardElement, renderedCard: RenderedAdaptiveCard) {

    }


    private fun onOpenUrl(actionElement: BaseActionElement) {
        var openUrlAction: OpenUrlAction? = null
        if (actionElement is ShowCardAction) {
            openUrlAction = actionElement as OpenUrlAction
        } else if (OpenUrlAction.dynamic_cast(actionElement).also { openUrlAction = it } == null) {
            throw InternalError("Unable to convert BaseActionElement to ShowCardAction object model.")
        }
        openUrl(openUrlAction!!.GetUrl())
    }

    fun openUrl(url: String) {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
        startActivity(browserIntent)
    }
}