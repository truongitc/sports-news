package co.global.gin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import co.global.gin.service.user.UserService;
import co.global.gin.utils.Constants;
import co.global.gin.vo.request.paging.SortingAndPagingRequestVO;
import co.global.gin.vo.response.CommonResponse;
import co.global.gin.vo.response.UserResponseVO;
import co.global.gin.vo.response.paging.PagedResult;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(Constants.API_DOMAIN + "users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	public ResponseEntity<Object> getPagingUsers(
			@RequestParam(required = false, defaultValue = Constants.DEFAULT_PAGE) Integer page,
			@RequestParam(required = false, defaultValue = Constants.DEFAULT_SIZE) Integer size,
			@RequestParam(required = false) String sortKey) {

		// Get paging request VO
		SortingAndPagingRequestVO pagingRequestVO = new SortingAndPagingRequestVO(page, size, sortKey);

		CommonResponse<PagedResult<UserResponseVO>> userPagedResponse = userService.getPagingUsers(pagingRequestVO);

		return new ResponseEntity<>(userPagedResponse, HttpStatus.OK);
	}
}
