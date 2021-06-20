package co.global.gin.service.user;

import co.global.gin.vo.request.UserRequestVO;
import co.global.gin.vo.request.paging.SortingAndPagingRequestVO;
import co.global.gin.vo.response.CommonResponse;
import co.global.gin.vo.response.UserResponseVO;
import co.global.gin.vo.response.paging.PagedResult;

public interface UserService {

	CommonResponse<PagedResult<UserResponseVO>> getPagingUsers(SortingAndPagingRequestVO pagingRequestVO);

	CommonResponse<UserResponseVO> getUserInfo(Integer id);

	CommonResponse<UserResponseVO> updateUserInfo(Integer id, UserRequestVO requestVO);

	CommonResponse<UserResponseVO> deeleteUser(Integer id);

}
