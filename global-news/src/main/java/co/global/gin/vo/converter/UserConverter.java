package co.global.gin.vo.converter;

import co.global.gin.model.User;
import co.global.gin.vo.response.UserResponseVO;

public class UserConverter {

	public UserConverter() {

	}

	public static UserResponseVO mapUserResponseVO(User user) {
		UserResponseVO responseVO = new UserResponseVO();

		if (user != null) {
			responseVO.setUserName(user.getUserName());
			responseVO.setEmail(user.getEmail());
			responseVO.setCreatedAt(user.getCreatedAt());
			responseVO.setUpdatedAt(user.getUpdatedAt());
			responseVO.setRole(user.getRoles());
		}

		return responseVO;
	}
}
