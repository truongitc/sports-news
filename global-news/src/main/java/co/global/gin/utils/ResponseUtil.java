package co.global.gin.utils;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * @author Arisedev01
 *
 */
public final class ResponseUtil {
	private ResponseUtil() {
	}

	public static ResponseEntity<Map<String, Object>> setResponseData(Integer statusCodeAsInt, Object data,
			String message) {
		Map<String, Object> responseData = new HashMap<>();

		String responseMessage = null;
		if (message != null) {
			responseMessage = message;
		} else {
			responseMessage = getResponseMessage(statusCodeAsInt, false);
		}
		responseData.put("message", responseMessage);

		if (statusCodeAsInt == null || HttpStatus.resolve(statusCodeAsInt) == null
				|| HttpStatus.resolve(statusCodeAsInt).is5xxServerError()) {
			return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		HttpStatus statusCode = HttpStatus.resolve(statusCodeAsInt);
		if (statusCode.is2xxSuccessful() && data != null) {
			responseData.put("data", data);
		}

		return new ResponseEntity<>(responseData, statusCode);
	}

	public static ResponseEntity<Map<String, Object>> setLogonResponseData(Integer statusCodeAsInt, Object data,
			String message) {
		Map<String, Object> responseData = new HashMap<>();

		String responseMessage = null;
		if (message != null) {
			responseMessage = message;
		} else {
			responseMessage = getResponseMessage(statusCodeAsInt, true);
		}
		responseData.put("message", responseMessage);

		if (statusCodeAsInt == null || HttpStatus.resolve(statusCodeAsInt) == null
				|| HttpStatus.resolve(statusCodeAsInt).is5xxServerError()) {
			return new ResponseEntity<>(responseData, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		HttpStatus statusCode = HttpStatus.resolve(statusCodeAsInt);
		if (statusCode.is2xxSuccessful() && data != null) {
			responseData.put("data", data);
		}

		return new ResponseEntity<>(responseData, statusCode);
	}

	public static ResponseEntity<Map<String, Object>> setEmptyDataResponse(Integer statusCodeAsInt) {
		return setResponseData(statusCodeAsInt, null, null);
	}

	public static ResponseEntity<Map<String, Object>> setResponseInvalidData(String message) {
		Map<String, Object> responseData = new HashMap<>();
		responseData.put("message", message);

		return new ResponseEntity<>(responseData, HttpStatus.BAD_REQUEST);
	}

	private static String getResponseMessage(Integer statusCodeAsInt, boolean isLogon) {
		if (statusCodeAsInt == null || HttpStatus.resolve(statusCodeAsInt) == null
				|| HttpStatus.resolve(statusCodeAsInt).is5xxServerError()) {
			return Translator.toLocale("msg_error_server");
		}

		HttpStatus statusCode = HttpStatus.resolve(statusCodeAsInt);
		if (statusCode.is2xxSuccessful()) {
			return Translator.toLocale("msg_success");
		}

		if (statusCode.is4xxClientError()) {
			if (statusCode.equals(HttpStatus.UNAUTHORIZED)) {
				return isLogon ? Translator.toLocale("msg_error_login_fail")
						: Translator.toLocale("msg_error_unauthorized");
			}

			if (statusCode.equals(HttpStatus.FORBIDDEN)) {
				return Translator.toLocale("msg_forbidden");
			}

			if (statusCode.equals(HttpStatus.CONFLICT)) {
				return Translator.toLocale("msg_error_conflict");
			}

			return Translator.toLocale("msg_error_wagby_server") + statusCode.value();
		}

		return Translator.toLocale("msg_error_unknown");
	}

	public static boolean isSuccessResponse(Integer statusCode) {
		if (statusCode == null) {
			return false;
		}

		return statusCode >= 200 && statusCode < 300;
	}

}
