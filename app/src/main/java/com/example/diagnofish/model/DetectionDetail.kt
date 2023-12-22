package com.example.diagnofish.model

data class DetectionDetail(
    var id: String = "",
    var image_filename: String = "",
    var fish_name: String = "",
    var result: String = "",
    var confidence_score: Double = 0.0,
    var description: String = "",
    var symptom: String = "",
    var cause: String = "",
    var treatment: String = "",
    var prevention: String = ""
)
