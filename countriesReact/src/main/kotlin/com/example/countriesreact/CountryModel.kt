package com.example.countriesreact

import jakarta.persistence.*

@Entity
data class CountryModel(
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long? = null,
    val name: String,
    val capital: String,
    val population: Long,
    val area: Long,
    val region: String,
    val subregion: String,
    @ElementCollection
    @CollectionTable(name = "country_languages", joinColumns = [JoinColumn(name = "country_id")])
    @Column(name = "language")
    val languages: List<String>,
    val codeChar: String,
    val currency: String
)
