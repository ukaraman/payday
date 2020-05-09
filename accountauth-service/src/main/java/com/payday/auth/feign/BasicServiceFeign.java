package com.payday.auth.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.payday.common.dto.PostDTO;

@FeignClient(contextId = "AuthCodeBasicClient", name="accountlogin-service", configuration=FeignConfiguration.class)
public interface BasicServiceFeign {
	
	@GetMapping("/api/latestPosts")
	List <PostDTO> getLatestPosts();

}