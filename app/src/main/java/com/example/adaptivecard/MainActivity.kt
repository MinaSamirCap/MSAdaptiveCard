package com.example.adaptivecard

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import io.adaptivecards.objectmodel.*
import io.adaptivecards.renderer.AdaptiveCardRenderer
import io.adaptivecards.renderer.RenderedAdaptiveCard
import io.adaptivecards.renderer.actionhandler.ICardActionHandler


class MainActivity : AppCompatActivity(), ICardActionHandler {

    var adaptiveCardJson = """{
    "type": "AdaptiveCard",
    "version": "1.0",
    "body": [
        {
            "type": "TextBlock",
            "text": "Here is a ninja cat"
        },
        {
            "type": "Image",
            "url": "http://adaptivecards.io/content/cats/1.png"
        }
    ]
}"""

    var ex1 = """{
    "type": "AdaptiveCard",
    "body": [
        {
            "type": "TextBlock",
            "size": "Medium",
            "weight": "Bolder",
            "text": "Publish Adaptive Card Schema"
        },
        {
            "type": "ColumnSet",
            "columns": [
                {
                    "type": "Column",
                    "items": [
                        {
                            "type": "Image",
                            "style": "Person",
                            "url": "https://pbs.twimg.com/profile_images/3647943215/d7f12830b3c17a5a9e4afcc370e3a37e_400x400.jpeg",
                            "size": "Small"
                        }
                    ],
                    "width": "auto"
                },
                {
                    "type": "Column",
                    "items": [
                        {
                            "type": "TextBlock",
                            "weight": "Bolder",
                            "text": "Matt Hidinger",
                            "wrap": true
                        },
                        {
                            "type": "TextBlock",
                            "spacing": "None",
                            "text": "Created {{DATE(2017-02-14T06:08:39Z,SHORT)}}",
                            "isSubtle": true,
                            "wrap": true
                        }
                    ],
                    "width": "stretch"
                }
            ]
        },
        {
            "type": "TextBlock",
            "text": "Now that we have defined the main rules and features of the format, we need to produce a schema and publish it to GitHub. The schema will be the starting point of our reference documentation.",
            "wrap": true
        },
        {
            "type": "FactSet",
            "facts": [
                {
                    "data": "{properties}",
                    "title": "{key}:",
                    "value": "{value}"
                }
            ]
        }
    ],
    "actions": [
        {
            "type": "Action.ShowCard",
            "title": "Set due date",
            "card": {
                "type": "AdaptiveCard",
                "body": [
                    {
                        "type": "Input.Date",
                        "id": "dueDate"
                    },
                    {
                        "type": "Input.Text",
                        "id": "comment",
                        "placeholder": "Add a comment",
                        "isMultiline": true
                    }
                ],
                "actions": [
                    {
                        "type": "Action.Submit",
                        "title": "OK"
                    }
                ]
            }
        },
        {
            "type": "Action.OpenUrl",
            "title": "View",
            "url": "https://adaptivecards.io"
        }
    ],
    "version": "1.3"
}"""


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val contxt = ParseContext()
        val parseResult =
            AdaptiveCard.DeserializeFromString(ex1, AdaptiveCardRenderer.VERSION, contxt)

        val renderedCard = AdaptiveCardRenderer.getInstance()
            .render(
                this, supportFragmentManager,
                parseResult.GetAdaptiveCard(),
                this, HostConfig()
            )

        findViewById<ConstraintLayout>(R.id.constraintLayout).addView(renderedCard.view)
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