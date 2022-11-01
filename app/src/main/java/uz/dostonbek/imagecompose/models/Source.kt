package uz.dostonbek.imagecompose.models

import uz.dostonbek.imagecompose.models.Ancestry
import uz.dostonbek.imagecompose.models.CoverPhoto

data class Source(
    val ancestry: Ancestry,
    val cover_photo: CoverPhoto,
    val description: String,
    val meta_description: String,
    val meta_title: String,
    val subtitle: String,
    val title: String
)