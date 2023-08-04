package com.faprops.faproject.controllers

import com.faprops.faproject.models.Publication
import com.faprops.faproject.services.PublicationService
import lombok.extern.slf4j.Slf4j
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
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

        val files = contFiles("/src/main/resources/static/img/publications/${id}")

        if (inmueble != null && files > 0) {
            model["inmueble"] = inmueble.get()
            model["files"] = files
        } else {
            return "index"
        }

        return "publication"
    }

    fun contFiles(rutaDirectorio: String): Int {
        val directorio = File(Paths.get("").toAbsolutePath().toString() + "/" + rutaDirectorio)

        if (!directorio.isDirectory) {
            System.err.println("La ruta proporcionada no es un directorio válido.")
            return 0
        }

        val archivos = directorio.listFiles()
        if (archivos == null) {
            System.err.println("No se puede acceder al contenido del directorio.")
            return 0
        }
        var cantidadArchivos = 0
        for (archivo in archivos) {
            if (archivo.isFile) {
                cantidadArchivos++
            }
        }
        return cantidadArchivos
    }
}