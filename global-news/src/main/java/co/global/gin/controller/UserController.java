package co.global.gin.controller;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.global.gin.service.user.UserService;
import co.global.gin.utils.Constants;
import co.global.gin.utils.ResponseUtil;
import co.global.gin.vo.request.UserRequestVO;
import co.global.gin.vo.request.paging.SortingAndPagingRequestVO;
import co.global.gin.vo.response.CommonResponse;
import co.global.gin.vo.response.UserResponseVO;
import co.global.gin.vo.response.paging.PagedResult;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(Constants.API_DOMAIN + "users")
public class UserController {

	private static final Logger logger = LogManager.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<Map<String, Object>> getPagingUsers(
			@RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE) Integer page,
			@RequestParam(required = false, defaultValue = Constants.DEFAULT_SIZE) Integer size,
			@RequestParam(required = false) String sortKey, @RequestParam(required = false) String sortDir) {

		logger.info("Get all user: page {}, size {}, sortKey {}, sortDir: {}", page, size, sortKey, sortDir);

		// Get paging request VO
		SortingAndPagingRequestVO pagingRequestVO = new SortingAndPagingRequestVO(page, size, sortKey, sortDir);

		CommonResponse<PagedResult<UserResponseVO>> userPagedResponse = userService.getPagingUsers(pagingRequestVO);

		logger.info("Get all user response: userPagedResponse", userPagedResponse);
		return ResponseUtil.setResponseData(userPagedResponse.getStatusCode(), userPagedResponse.getData(),
				userPagedResponse.getMessage());
	}

	@GetMapping(path = "/{id}")
	public ResponseEntity<Map<String, Object>> getInfoUser(@PathVariable Integer id) {
		logger.info("Get user info by id: {}", id);

		// validate
		// TODO:

		CommonResponse<UserResponseVO> userInfo = userService.getUserInfo(id);

		logger.info("Response user info: {}", userInfo);
		return ResponseUtil.setResponseData(userInfo.getStatusCode(), userInfo.getData(), userInfo.getMessage());
	}

	@PutMapping(path = "/{id}")
	public ResponseEntity<Map<String, Object>> updateInfoUser(@PathVariable Integer id,
			@RequestBody(required = true) UserRequestVO requestVO) {
		logger.info("Update user info by id: {}, requestVO: {}", id, requestVO);

		// validate
		// TODO:

		CommonResponse<UserResponseVO> userInfo = userService.updateUserInfo(id, requestVO);

		logger.info("Response update user info : {}", userInfo);
		return ResponseUtil.setResponseData(userInfo.getStatusCode(), userInfo.getData(), userInfo.getMessage());
	}

	@DeleteMapping(path = "/{id}")
	public ResponseEntity<Map<String, Object>> deleteUser(@PathVariable Integer id) {
		logger.info("Delete user info by id: {}", id);

		// validate
		// TODO:

		CommonResponse<UserResponseVO> user = userService.deeleteUser(id);

		logger.info("Response delete user: {}", user);
		return ResponseUtil.setResponseData(user.getStatusCode(), user.getData(), user.getMessage());
	}

}
