package uz.dostonbek.imagecompose.models

data class Unsplash(
    val results: List<Result>,
    val total: Int,
    val total_pages: Int
)