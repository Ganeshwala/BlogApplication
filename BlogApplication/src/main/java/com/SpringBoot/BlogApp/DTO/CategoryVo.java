package com.SpringBoot.BlogApp.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryVo {

	private int categoryId;
	private String categoryTitle;
	private String description;
}
