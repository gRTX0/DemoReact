package com.example.countriesreact

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.core.io.ClassPathResource
import java.io.File

@SpringBootApplication
class CountriesReactApplication(private val countryRepository: CountryRepository){
    fun saveCountries(countries: List<CountryModel>) {
        countryRepository.saveAll(countries)
    }
}

fun main(args: Array<String>) {
    val jsonFile = ClassPathResource("country.json").file
    val objectMapper = ObjectMapper()
    val data: List<CountryModel> = objectMapper.readValue(jsonFile, objectMapper.typeFactory.constructCollectionType(List::class.java, CountryModel::class.java))
    val context = runApplication<CountriesReactApplication>(*args)

    val countryRepository = context.getBean(CountriesReactApplication::class.java)
    countryRepository.saveCountries(data)
}
