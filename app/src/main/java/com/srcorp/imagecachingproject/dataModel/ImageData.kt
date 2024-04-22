package com.srcorp.imagecachingproject.dataModel

import com.google.gson.annotations.SerializedName

data class ImageData(
    @SerializedName("id"            ) var id            : String?        = null,
    @SerializedName("title"         ) var title         : String?        = null,
    @SerializedName("language"      ) var language      : String?        = null,
    @SerializedName("thumbnail"     ) var thumbnail     : Thumbnail?     = Thumbnail(),
    @SerializedName("mediaType"     ) var mediaType     : Int?           = null,
    @SerializedName("coverageURL"   ) var coverageURL   : String?        = null,
    @SerializedName("publishedAt"   ) var publishedAt   : String?        = null,
    @SerializedName("publishedBy"   ) var publishedBy   : String?        = null,
    @SerializedName("backupDetails" ) var backupDetails : BackupDetails? = BackupDetails()
)
