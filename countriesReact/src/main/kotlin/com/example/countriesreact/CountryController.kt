package com.example.countriesreact

import org.springframework.http.HttpStatus
import org.springframework.http.HttpStatusCode
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = ["http://localhost:3000"])
class CountryController(val countryRepository: CountryRepository) {
    
    @GetMapping("/countries")
    fun getCountries(): MutableList<CountryModel> = countryRepository.findAll()

    @GetMapping("/countries/{id}")
    fun getCountryById(@PathVariable id: Long) = countryRepository.findById(id)

    @PostMapping("/countries")
    fun postCountry(@RequestBody country: CountryModel): ResponseEntity<CountryModel> {
        val savedCountry = countryRepository.save(country)
        return ResponseEntity.ok(savedCountry)
    }

    @DeleteMapping("/countries/{id}")
    fun deleteCountryById(@PathVariable id: Long): ResponseEntity<String> {
        val country = countryRepository.findById(id)
        return if (country.isPresent){
            countryRepository.delete(country.get())
            ResponseEntity.status(HttpStatus.OK).body("Item deleted successfully.")
        } else ResponseEntity.status(HttpStatus.NOT_FOUND).body("Item not found.")
    }
}