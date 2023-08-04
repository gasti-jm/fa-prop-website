package com.faprops.faproject.dao

import com.faprops.faproject.models.Publication
import org.springframework.data.jpa.repository.JpaRepository

interface PublicationDAO: JpaRepository<Publication, Long> {
    fun findAllByTypePub(typePub: String): List<Publication>?
}