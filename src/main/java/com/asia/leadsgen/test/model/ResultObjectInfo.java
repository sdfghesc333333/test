package com.asia.leadsgen.test.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResultObjectInfo<E> {

	private List<E> data;
	private int current_page;
	private long total;

}
