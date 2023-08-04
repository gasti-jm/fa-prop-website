package com.faprops.faproject.models

import jakarta.persistence.*
import lombok.Data

@Entity
@Data
@Table(name = "publication")
data class Publication(val title: String, val description: String,
                       val typePub: String, val price: Int) {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var idPublication: Long = 0

    @OneToOne
    @JoinColumn(name = "id_data_property")
    var dataProperty: Property? = null

    @OneToOne
    @JoinColumn(name = "id_data_location")
    var dataLocation: Location? = null

}