package com.faprops.faproject.models

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "property")
data class Property(val type: String,
               val cantBedrooms: Int, val cantAmbient: Int, val cantGarages: Int, val cantBathrooms: Int,
               val timeAntiquity: Int, val priceExpenses: Int, val coveredArea: Double, val totalArea: Double, val cantFloors: Int) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idProperty: Long = 0
}