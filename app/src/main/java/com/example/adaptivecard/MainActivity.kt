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


    val hostConfJson = """{
	"spacing": {
		"small": 3,
		"default": 8,
		"medium": 20,
		"large": 30,
		"extraLarge": 40,
		"padding": 10
	},
	"separator": {
		"lineThickness": 1,
		"lineColor": "#EEEEEE"
	},
	"supportsInteractivity": true,
	"fontTypes": {
		"default": {
			"fontFamily": "'Segoe UI', 'Roboto', 'Oxygen', 'Ubuntu', 'Cantarell', 'Fira Sans', 'Droid Sans', 'Helvetica Neue', sans-serif",
			"fontSizes": {
				"small": 12,
				"default": 14,
				"medium": 17,
				"large": 21,
				"extraLarge": 26
			},
			"fontWeights": {
				"lighter": 200,
				"default": 400,
				"bolder": 600
			}
		},
		"monospace": {
			"fontFamily": "'Courier New', Courier, monospace",
			"fontSizes": {
				"small": 12,
				"default": 14,
				"medium": 17,
				"large": 21,
				"extraLarge": 26
			},
			"fontWeights": {
				"lighter": 200,
				"default": 400,
				"bolder": 600
			}
		}
	},
	"containerStyles": {
		"default": {
			"backgroundColor": "#FFFF0000",
			"foregroundColors": {
				"default": {
					"default": "#FFFFFF",
					"subtle": "#767676"
				},
				"accent": {
					"default": "#0063B1",
					"subtle": "#0063B1"
				},
				"attention": {
					"default": "#FF0000",
					"subtle": "#DDFF0000"
				},
				"good": {
					"default": "#54a254",
					"subtle": "#DD54a254"
				},
				"warning": {
					"default": "#c3ab23",
					"subtle": "#DDc3ab23"
				}
			}
		},
		"emphasis": {
			"backgroundColor": "#F0F0F0",
			"foregroundColors": {
				"default": {
					"default": "#000000",
					"subtle": "#767676"
				},
				"accent": {
					"default": "#2E89FC",
					"subtle": "#882E89FC"
				},
				"attention": {
					"default": "#FF0000",
					"subtle": "#DDFF0000"
				},
				"good": {
					"default": "#54a254",
					"subtle": "#DD54a254"
				},
				"warning": {
					"default": "#c3ab23",
					"subtle": "#DDc3ab23"
				}
			}
		},
		"accent": {
			"backgroundColor": "#C7DEF9",
			"foregroundColors": {
				"default": {
					"default": "#333333",
					"subtle": "#EE333333"
				},
				"dark": {
					"default": "#000000",
					"subtle": "#66000000"
				},
				"light": {
					"default": "#FFFFFF",
					"subtle": "#33000000"
				},
				"accent": {
					"default": "#2E89FC",
					"subtle": "#882E89FC"
				},
				"attention": {
					"default": "#cc3300",
					"subtle": "#DDcc3300"
				},
				"good": {
					"default": "#54a254",
					"subtle": "#DD54a254"
				},
				"warning": {
					"default": "#e69500",
					"subtle": "#DDe69500"
				}
			}
		},
		"good": {
			"backgroundColor": "#CCFFCC",
			"foregroundColors": {
				"default": {
					"default": "#333333",
					"subtle": "#EE333333"
				},
				"dark": {
					"default": "#000000",
					"subtle": "#66000000"
				},
				"light": {
					"default": "#FFFFFF",
					"subtle": "#33000000"
				},
				"accent": {
					"default": "#2E89FC",
					"subtle": "#882E89FC"
				},
				"attention": {
					"default": "#cc3300",
					"subtle": "#DDcc3300"
				},
				"good": {
					"default": "#54a254",
					"subtle": "#DD54a254"
				},
				"warning": {
					"default": "#e69500",
					"subtle": "#DDe69500"
				}
			}
		},
		"attention": {
			"backgroundColor": "#FFC5B2",
			"foregroundColors": {
				"default": {
					"default": "#333333",
					"subtle": "#EE333333"
				},
				"dark": {
					"default": "#000000",
					"subtle": "#66000000"
				},
				"light": {
					"default": "#FFFFFF",
					"subtle": "#33000000"
				},
				"accent": {
					"default": "#2E89FC",
					"subtle": "#882E89FC"
				},
				"attention": {
					"default": "#cc3300",
					"subtle": "#DDcc3300"
				},
				"good": {
					"default": "#54a254",
					"subtle": "#DD54a254"
				},
				"warning": {
					"default": "#e69500",
					"subtle": "#DDe69500"
				}
			}
		},
		"warning": {
			"backgroundColor": "#FFE2B2",
			"foregroundColors": {
				"default": {
					"default": "#333333",
					"subtle": "#EE333333"
				},
				"dark": {
					"default": "#000000",
					"subtle": "#66000000"
				},
				"light": {
					"default": "#FFFFFF",
					"subtle": "#33000000"
				},
				"accent": {
					"default": "#2E89FC",
					"subtle": "#882E89FC"
				},
				"attention": {
					"default": "#cc3300",
					"subtle": "#DDcc3300"
				},
				"good": {
					"default": "#54a254",
					"subtle": "#DD54a254"
				},
				"warning": {
					"default": "#e69500",
					"subtle": "#DDe69500"
				}
			}
		}
	},
	"imageSizes": {
		"small": 300,
		"medium": 80,
		"large": 160
	},
	"actions": {
		"maxActions": 5,
		"spacing": "default",
		"buttonSpacing": 8,
		"showCard": {
			"actionMode": "inline",
			"inlineTopMargin": 8
		},
		"actionsOrientation": "horizontal",
		"actionAlignment": "stretch"
	},
	"adaptiveCard": {
		"allowCustomStyle": false
	},
	"imageSet": {
		"imageSize": "medium",
		"maxImageHeight": 100
	},
	"factSet": {
		"title": {
			"color": "default",
			"size": "default",
			"isSubtle": false,
			"weight": "bolder",
			"wrap": true,
			"maxWidth": 150
		},
		"value": {
			"color": "default",
			"size": "default",
			"isSubtle": false,
			"weight": "default",
			"wrap": true
		},
		"spacing": 8
	}
}"""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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