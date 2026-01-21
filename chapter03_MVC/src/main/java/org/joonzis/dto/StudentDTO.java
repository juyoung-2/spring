package org.joonzis.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor //기본 생성자
@AllArgsConstructor // 풀 생성자
@Data
public class StudentDTO {
	private String name, dept;
	private int gradeNo, classNo;
}
