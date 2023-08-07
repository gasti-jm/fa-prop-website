package com.faprops.faproject.controllers

import com.faprops.faproject.services.PublicationService
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.io.Resource
import org.springframework.core.io.support.PathMatchingResourcePatternResolver
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import java.io.File
import java.nio.file.Paths
import java.util.*


@Controller
@Slf4j
class InitialController {
    private val logger = LoggerFactory.getLogger(javaClass)

    @Autowired
    private var publicationService: PublicationService? = null

    @GetMapping("/")
    fun indexWeb(model: Model): String {
        val destacados = publicationService?.listDestacados()

        if (destacados != null) {
            model["destacados"] = destacados
        }

        return "index"
    }

    @GetMapping("/alquileres")
    fun alquileresWeb(model: Model): String {
        val inmuebles = publicationService?.findByType("Alquiler")

        if (inmuebles != null) {
            model["inmuebles"] = inmuebles
        }

        return "search"
    }

    @GetMapping("/nosotros")
    fun nosotrosWeb(): String {
        return "nosotros"
    }

    @GetMapping("/ventas")
    fun ventasWeb(model: Model): String {
        val inmuebles = publicationService?.findByType("Venta")

        if (inmuebles != null) {
            model["inmuebles"] = inmuebles
        }

        return "search"
    }

    @GetMapping("/publicacion/{id}")
    fun publicacionWeb(model: Model, @PathVariable id: Long): String{
        val inmueble = publicationService?.findById(id)

        val files = contFiles("classpath:static/img/publications/${id}/")

        if (inmueble != null && files > 0) {
            if (!inmueble.isEmpty){
                model["inmueble"] = inmueble.get()
                model["files"] = files
            }
        }

        return "publication"
    }

    fun contFiles(rutaDirectorio: String): Int {
        // Utiliza PathMatchingResourcePatternResolver para buscar los archivos en la carpeta
        val resourceResolver = PathMatchingResourcePatternResolver()
        val resources: Array<Resource> = resourceResolver.getResources("$rutaDirectorio*")

        return resources.size
    }
}