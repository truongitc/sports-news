package co.global.gin.vo.response.paging;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedResult<T> {

	private PagedVO paging;

	private List<T> elements;

}