package com.example.countriesreact

import org.springframework.data.jpa.repository.JpaRepository

interface CountryRepository: JpaRepository<CountryModel, Long> {
}