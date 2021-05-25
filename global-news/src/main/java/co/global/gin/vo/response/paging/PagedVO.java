package co.global.gin.vo.response.paging;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PagedVO {

	@JsonProperty("page")
	private int currentPage;

	private int totalPage;

	private long totalRecord;

	@JsonProperty("size")
	private int pageSize;

	private int nextPage;

	private int previousPage;

	private int lastPage;

	private int firstPage;

}