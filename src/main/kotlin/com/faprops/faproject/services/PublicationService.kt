package com.faprops.faproject.services

import com.faprops.faproject.dao.PublicationDAO
import com.faprops.faproject.models.Publication

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*
import kotlin.collections.ArrayList

@Service
class PublicationService {
    @Autowired
    private var pDAO: PublicationDAO? = null

    @Transactional(readOnly = true)
    fun findByType(typePub: String): List<Publication>? {
        return pDAO?.findAllByTypePub(typePub)
    }

    @Transactional(readOnly = true)
    fun findById(id: Long): Optional<Publication>? {
        return pDAO?.findById(id)
    }

    @Transactional(readOnly = true)
    fun listDestacados() : List<Publication> {
        val pubs = pDAO?.findAll()
        val destacados = ArrayList<Publication>()

        destacados.add(pubs!![0])
        destacados.add(pubs!![1])
        destacados.add(pubs!![2])
        destacados.add(pubs!![3])

        return destacados
    }
}