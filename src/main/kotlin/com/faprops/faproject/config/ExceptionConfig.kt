package com.faprops.faproject.config

import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.ModelAndView

@ControllerAdvice
class ExceptionConfig {
    @ExceptionHandler(Exception::class)
    fun handleException(ex: Exception?): ModelAndView {
        val modelAndView = ModelAndView("error") // Nombre de la página de error personalizada
        //modelAndView.addObject("error", )
        return modelAndView
    }
}
