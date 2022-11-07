package com.ecom.config;
// this class for constant values
public class AppConstants {
	
	public static final String PAGE_NUMBER = "0";
	public static final String PAGE_SIZE = "5";
	public static final String SORT_BY = "productId";
	public static final String SORT_DIR = "asc";

	// security
	public static String[] PUBLIC_URLS = { 
										  "/users/",
										  "/auth/login",
										  "/v3/api-docs",
										  "/v2/api-docs",
										  "/swagger-resources/**",
										  "/swagger-ui/**",
										  "/webjars/**"
	};
	
	
	
	

}
