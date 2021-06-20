package co.global.gin.vo.converter;

import java.util.ArrayList;
import java.util.List;

import co.global.gin.model.User;
import co.global.gin.utils.Constants;
import co.global.gin.vo.request.paging.SortingAndPagingRequestVO;
import co.global.gin.vo.response.UserResponseVO;
import co.global.gin.vo.response.paging.PagedVO;
import co.global.gin.vo.response.paging.SortDirection;

public final class PagingConverter {

	private PagingConverter() {

	}

	public static SortingAndPagingRequestVO getUserPagingRequestVO(SortingAndPagingRequestVO pagingVO) {
		SortingAndPagingRequestVO sortingAndPagingRequestVO = new SortingAndPagingRequestVO();
		sortingAndPagingRequestVO
				.setPage(pagingVO.getPage() > 0 ? pagingVO.getPage() - 1 : Integer.valueOf(Constants.DEFAULT_PAGE));
		sortingAndPagingRequestVO
				.setSize(pagingVO.getSize() > 0 ? pagingVO.getSize() : Integer.valueOf(Constants.DEFAULT_SIZE));
		sortingAndPagingRequestVO.setSortKey(mapUserSortKey(pagingVO.getSortKey()));
		sortingAndPagingRequestVO.setSortDir(SortDirection.parseFromString(pagingVO.getSortDir()).toString());

		return sortingAndPagingRequestVO;
	}

	private static String mapUserSortKey(String sortKey) {
		if (sortKey == null) {
			return "createdAt";
		}

		switch (sortKey) {
		case "username":
			return "username";
		case "email":
			return "email";
		case "role":
			return "role";
		case "createdAt":
			return "created_at";
		case "updatedAt":
			return "updated_at";
		default:
			return null;
		}
	}

	public static List<UserResponseVO> mapUsersResponseVO(List<User> content) {

		List<UserResponseVO> usersResponseVO = new ArrayList<UserResponseVO>();
		for (int i = 0; i < content.size(); i++) {
			User user = content.get(i);
			UserResponseVO responseVO = new UserResponseVO();

			if (user != null) {
				responseVO.setUserName(user.getUserName());
				responseVO.setEmail(user.getEmail());
				responseVO.setCreatedAt(user.getCreatedAt());
				responseVO.setUpdatedAt(user.getUpdatedAt());
				responseVO.setRole(user.getRoles());
				usersResponseVO.add(responseVO);
			}

		}

		return usersResponseVO;
	}

	public static PagedVO getPagedResponseVO(long totalElement, SortingAndPagingRequestVO pagingRequestVO) {
		int totalPage = (int) Math.ceil((float) totalElement / pagingRequestVO.getSize());

		PagedVO pagedVO = new PagedVO();
		pagedVO.setCurrentPage(pagingRequestVO.getPage());
		pagedVO.setPageSize(pagingRequestVO.getSize());
		pagedVO.setTotalPage(totalPage);
		pagedVO.setTotalRecord(totalElement);

		return pagedVO;
	}

}
