package com.payday.auth.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.payday.common.dto.PostDTO;

@FeignClient(contextId = "clientFlowBasicClient", name="accountlogin-service", configuration=FeignClientConfiguration.class)
public interface BasicServiceFeignClientFlow {
	@GetMapping("/api/latestPosts")
	List <PostDTO> getLatestPosts();
}
