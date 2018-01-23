package com.lombardodier.controller;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.lombardodier.exception.DrinkDispenserException;

/**
 * 
 * @author Samuel PETIT
 */
@ControllerAdvice
public class ExceptionHandlingController {

	protected Logger logger;

	public ExceptionHandlingController() {
		logger = LoggerFactory.getLogger(getClass());
	}

	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */
	/* . . . . . . . . . . . . . EXCEPTION HANDLERS . . . . . . . . . . . . . . */
	/* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

	/**
	 * Handle exception of type {@link DrinkDispenserException}
	 * 
	 * @param req
	 *            current HTTP request.
	 * @param exception
	 *            the exception thrown (@link DrinkDispenserException}
	 * @return The model and view used by the DispatcherServlet to generate
	 *         output.
	 */
	@ExceptionHandler(DrinkDispenserException.class)
	public ModelAndView handleError(HttpServletRequest req, Exception exception) {
		logger.error("Request: " + req.getRequestURI() + " raised " + exception);

		ModelAndView mav = new ModelAndView();
		mav.addObject("exception", exception);

		mav.setViewName("error");
		return mav;
	}
}