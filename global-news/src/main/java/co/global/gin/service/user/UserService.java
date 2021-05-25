package co.global.gin.service.user;

import co.global.gin.vo.request.paging.SortingAndPagingRequestVO;
import co.global.gin.vo.response.CommonResponse;
import co.global.gin.vo.response.UserResponseVO;
import co.global.gin.vo.response.paging.PagedResult;

public interface UserService {

	CommonResponse<PagedResult<UserResponseVO>> getPagingUsers(SortingAndPagingRequestVO pagingRequestVO);

}
