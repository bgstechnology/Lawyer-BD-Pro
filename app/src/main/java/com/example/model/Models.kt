package com.example.model

data class LawSection(
    val sectionNumber: String,
    val title: String,
    val description: String,
    val actName: String
)

object DummyData {
    val laws = listOf(
        LawSection("302", "Punishment for murder", "Whoever commits murder shall be punished with death, or imprisonment for life, and shall also be liable to fine.", "Bangladesh Penal Code"),
        LawSection("138", "Dishonour of cheque", "Where any cheque drawn by a person on an account maintained by him with a banker for payment of any amount of money to another person from out of that account is returned by the bank unpaid...", "Negotiable Instruments Act"),
        LawSection("420", "Cheating and dishonestly inducing delivery of property", "Whoever cheats and thereby dishonestly induces the person deceived to deliver any property to any person...", "Bangladesh Penal Code"),
        LawSection("376", "Punishment for rape", "Whoever commits rape shall be punished with rigorous imprisonment for life or for a term which may extend to ten years and shall also be liable to fine.", "Bangladesh Penal Code")
    )
}
