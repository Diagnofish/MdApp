package com.example.diagnofish.model

data class DetectionHistory(
    var id: String = "",
    var image_filename: String = "",
    var fish_name: String = "",
    var is_success: Boolean = false,
    var result: String = "",
    var confidence_score: Double = 0.0,
    var created_at: String = "",
    var updated_at: String = "",
    var user_id: String = ""
)
