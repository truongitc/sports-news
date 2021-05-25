package co.global.gin.service.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import co.global.gin.model.User;
import co.global.gin.repository.UserRepository;
import co.global.gin.utils.Constants;
import co.global.gin.vo.converter.PagingConverter;
import co.global.gin.vo.request.paging.SortingAndPagingRequestVO;
import co.global.gin.vo.response.CommonResponse;
import co.global.gin.vo.response.UserResponseVO;
import co.global.gin.vo.response.paging.PagedResult;
import co.global.gin.vo.response.paging.PagedVO;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public CommonResponse<PagedResult<UserResponseVO>> getPagingUsers(SortingAndPagingRequestVO pagingRequestVO) {

		CommonResponse<PagedResult<UserResponseVO>> result = new CommonResponse<>();
		PagedResult<UserResponseVO> pagedResult = new PagedResult<>();
		PagedVO pagedVO = new PagedVO();

		SortingAndPagingRequestVO sort = PagingConverter.getUserPagingRequestVO(pagingRequestVO);
		Pageable paging = PageRequest.of(sort.getPage(), sort.getSize(), Sort.by(sort.getSortKey()));
		Page<User> page = userRepository.findAll(paging);

		List<UserResponseVO> responseVO = PagingConverter.mapUsersResponseVO(page.getContent());

		// set PagedVO
		pagedVO.setTotalPage(page.getTotalPages());
		pagedVO.setPageSize(page.getTotalPages());
		pagedVO.setTotalRecord(page.getTotalElements());
		pagedVO.setCurrentPage(page.getPageable().getPageNumber() + Constants.PAGE_NUM);
		pagedVO.setNextPage(page.nextPageable().next().getPageNumber());
		pagedVO.setPreviousPage(page.getPageable().getPageNumber());
		pagedVO.setFirstPage(page.previousOrFirstPageable().previousOrFirst().getPageNumber() + Constants.PAGE_NUM);
		pagedVO.setLastPage(page.nextOrLastPageable().next().getPageNumber() + Constants.PAGE_NUM);

		// set PagedResult
		pagedResult.setPaging(pagedVO);
		pagedResult.setElements(responseVO);

		// set result data
		result.setData(pagedResult);

		return result;
	}

}
