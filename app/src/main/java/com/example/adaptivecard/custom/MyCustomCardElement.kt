package com.example.adaptivecard.custom

import io.adaptivecards.objectmodel.*
import org.json.JSONException
import org.json.JSONObject

class MyCustomCardElement(type: CardElementType) : BaseCardElement(type) {

    var myTypeData: String? = null
}

class MyCardElementParser : BaseCardElementParser() {

    override fun Deserialize(context: ParseContext?, value: JsonValue): BaseCardElement {

        val element = MyCustomCardElement(CardElementType.Custom)
        element.SetElementTypeString("MyType")
        val data = value.string
        try {
            val obj = JSONObject(data)
            element.myTypeData = obj.getString("secret")
        } catch (e: JSONException) {
            e.printStackTrace()
            element.myTypeData = "Fail"
        }

        return element
    }


}