package co.global.gin.service.user;

import java.util.Date;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import co.global.gin.model.User;
import co.global.gin.repository.UserRepository;
import co.global.gin.vo.converter.PagingConverter;
import co.global.gin.vo.converter.UserConverter;
import co.global.gin.vo.request.UserRequestVO;
import co.global.gin.vo.request.paging.SortingAndPagingRequestVO;
import co.global.gin.vo.response.CommonResponse;
import co.global.gin.vo.response.UserResponseVO;
import co.global.gin.vo.response.paging.PagedResult;
import co.global.gin.vo.response.paging.PagedVO;

@Service
public class UserServiceImpl implements UserService {
	private static final Logger logger = LogManager.getLogger(UserServiceImpl.class);

	@Autowired
	private UserRepository userRepository;

	@Override
	public CommonResponse<PagedResult<UserResponseVO>> getPagingUsers(SortingAndPagingRequestVO pagingRequestVO) {
		logger.info("Get paging users: pagingRequestVO {}", pagingRequestVO);

		CommonResponse<PagedResult<UserResponseVO>> result = new CommonResponse<>();
		PagedResult<UserResponseVO> pagedResult = new PagedResult<>();
		try {
			SortingAndPagingRequestVO sort = PagingConverter.getUserPagingRequestVO(pagingRequestVO);
			Pageable paging = PageRequest.of(sort.getPage(), sort.getSize(),
					Sort.by(Direction.fromString(sort.getSortDir()), sort.getSortKey()));
			Page<User> page = userRepository.findAll(paging);

			List<UserResponseVO> responseVO = PagingConverter.mapUsersResponseVO(page.getContent());

			// set PagedVO
			PagedVO pagedVO = PagingConverter.getPagedResponseVO(page.getTotalElements(), pagingRequestVO);

			// set PagedResult
			pagedResult.setPaging(pagedVO);
			pagedResult.setElements(responseVO);

			// set result data
			result.setData(pagedResult);
			result.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			logger.error("Get paging users error: {}", e);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}

		return result;
	}

	@Override
	public CommonResponse<UserResponseVO> getUserInfo(Integer id) {
		CommonResponse<UserResponseVO> result = new CommonResponse<>();
		UserResponseVO responseVO = new UserResponseVO();
		try {

			User user = userRepository.findUserById(id);
			responseVO = UserConverter.mapUserResponseVO(user);

			result.setData(responseVO);
			result.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			logger.error("Get user info error: {}", e);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		return result;
	}

	@Override
	public CommonResponse<UserResponseVO> updateUserInfo(Integer id, UserRequestVO requestVO) {
		CommonResponse<UserResponseVO> result = new CommonResponse<>();
		UserResponseVO responseVO = new UserResponseVO();
		try {

			User user = userRepository.findUserById(id);
			if (user == null) {
				result.setStatusCode(HttpStatus.NOT_FOUND.value());
				return result;
			}

			user.setUpdatedAt(new Date());

			User userUpdate = userRepository.save(user);
			responseVO = UserConverter.mapUserResponseVO(userUpdate);

			result.setData(responseVO);
			result.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			logger.error("Update user info error: {}", e);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		return result;
	}

	@Override
	public CommonResponse<UserResponseVO> deeleteUser(Integer id) {
		CommonResponse<UserResponseVO> result = new CommonResponse<>();
		try {

			User user = userRepository.findUserById(id);
			if (user == null) {
				result.setStatusCode(HttpStatus.NOT_FOUND.value());
				return result;
			}

			userRepository.delete(user);

			result.setStatusCode(HttpStatus.OK.value());
		} catch (Exception e) {
			logger.error("Update user info error: {}", e);
			result.setStatusCode(HttpStatus.NOT_FOUND.value());
		}
		return result;
	}

}
