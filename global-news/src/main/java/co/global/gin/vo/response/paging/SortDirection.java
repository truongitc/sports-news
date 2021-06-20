package co.global.gin.vo.response.paging;

public enum SortDirection {
	DESC("DESC"), ASC("ASC"), NONE("NONE");

	private String value;

	SortDirection(String value) {
		this.value = value;
	}

	public String getValue() {
		return this.value;
	}

	public static SortDirection parseFromString(String value) {
		for (SortDirection sortDir : values()) {
			if (sortDir.getValue().equalsIgnoreCase(value)) {
				return sortDir;
			}
		}

		return DESC;
	}
}