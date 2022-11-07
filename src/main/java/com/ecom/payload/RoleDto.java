package com.ecom.payload;

import java.util.HashSet;
import java.util.Set;

import com.ecom.entities.User;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDto {
	
	private Integer id;
	private String name;

}
