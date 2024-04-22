package com.srcorp.imagecachingproject.dataModel

import com.google.gson.annotations.SerializedName

data class BackupDetails(
    @SerializedName("pdfLink"       ) var pdfLink       : String? = null,
    @SerializedName("screenshotURL" ) var screenshotURL : String? = null
)
