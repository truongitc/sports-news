package co.global.gin.controller.exception;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingRequestHeaderException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

import co.global.gin.utils.Translator;

@RestControllerAdvice
public class ExceptionResolver {
	private static final Logger logger = LogManager.getLogger(ExceptionResolver.class);

	private static final String MESSAGE = "message";

	@ExceptionHandler(NoHandlerFoundException.class)
	@ResponseStatus(HttpStatus.NOT_FOUND)
	public Map<String, String> handleNoHandlerFound(NoHandlerFoundException e, WebRequest request) {
		Map<String, String> response = new HashMap<>();
		response.put(MESSAGE, e.getLocalizedMessage());

		logger.error(e.getLocalizedMessage());
		return response;
	}

	@ExceptionHandler(MaxUploadSizeExceededException.class)
	@ResponseStatus(HttpStatus.PAYLOAD_TOO_LARGE)
	public Map<String, String> handleMaxUploadSizeExceededException(MaxUploadSizeExceededException e) {
		Map<String, String> response = new HashMap<>();
		String errorMessage = Translator.toLocale("msg_upload_fail");
		response.put(MESSAGE, errorMessage);

		logger.error(errorMessage);
		return response;
	}

	@ExceptionHandler(HttpRequestMethodNotSupportedException.class)
	@ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
	public Map<String, String> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
		Map<String, String> response = new HashMap<>();
		response.put(MESSAGE, e.getLocalizedMessage());

		logger.error(e.getLocalizedMessage());
		return response;
	}

	@ExceptionHandler(MissingServletRequestParameterException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleMissingServletRequestParameterException(
			MissingServletRequestParameterException e) {
		Map<String, String> response = new HashMap<>();
		response.put(MESSAGE, e.getLocalizedMessage());

		logger.error(e.getLocalizedMessage());
		return response;
	}

	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
		Map<String, String> response = new HashMap<>();
		response.put(MESSAGE, e.getLocalizedMessage());

		logger.error(e.getLocalizedMessage());
		return response;
	}

	@ExceptionHandler(MissingServletRequestPartException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleMissingServletRequestPartException(MissingServletRequestPartException e) {
		Map<String, String> response = new HashMap<>();
		response.put(MESSAGE, e.getLocalizedMessage());

		logger.error(e.getLocalizedMessage());
		return response;
	}

	@ExceptionHandler(MultipartException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleMultipartException(MultipartException e) {
		Map<String, String> response = new HashMap<>();
		response.put(MESSAGE, e.getLocalizedMessage());

		logger.error(e.getLocalizedMessage());
		return response;
	}

	@ExceptionHandler(HttpMessageNotReadableException.class)
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public Map<String, String> handleHttpMessageNotReadableException(HttpMessageNotReadableException e) {
		Map<String, String> response = new HashMap<>();
		response.put(MESSAGE, e.getLocalizedMessage());

		logger.error(e.getLocalizedMessage());
		return response;
	}

	@ExceptionHandler(MissingRequestHeaderException.class)
	@ResponseStatus(HttpStatus.UNAUTHORIZED)
	public Map<String, String> handleMissingRequestHeaderException(MissingRequestHeaderException e) {
		Map<String, String> response = new HashMap<>();
		response.put(MESSAGE, Translator.toLocale("msg_error_unauthorized"));

		logger.error(e.getLocalizedMessage());
		return response;
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
	public Map<String, String> handleInternalServerError(Exception e) {
		Map<String, String> response = new HashMap<>();
		response.put(MESSAGE, Translator.toLocale("msg_error_server"));

		logger.error("Exception: ", e);
		return response;
	}

}
