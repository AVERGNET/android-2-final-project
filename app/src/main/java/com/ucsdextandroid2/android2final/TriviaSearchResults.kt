package com.ucsdextandroid2.android2final

import com.google.gson.annotations.SerializedName

class TriviaSearchResults {

    @SerializedName("results")
    var questions: List<Question>? = null

}