package com.faprops.faproject.models

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "location")
data class Location(val province: String, val locality: String, val street: String, val height: Int) {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idLocation: Long = 0
}