package co.global.gin.vo.request.paging;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortingAndPagingRequestVO {

	private int page;

	private int size;

	private String sortKey;

	private String sortDir;
}