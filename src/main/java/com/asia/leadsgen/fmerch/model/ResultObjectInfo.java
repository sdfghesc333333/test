package com.asia.leadsgen.fmerch.model;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ResultObjectInfo<E> {

	private List<E> data;
	private int current_page;
	private long total;

}
