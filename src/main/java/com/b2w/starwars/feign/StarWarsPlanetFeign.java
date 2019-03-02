package com.b2w.starwars.feign;

import com.b2w.starwars.api.vo.ResultVO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "starwarsPlanetClient", url = "${starwars.url}")
public interface StarWarsPlanetFeign {

    @GetMapping("api/planets/")
    ResultVO fetch(@RequestHeader("Accept") String accept,
                   @RequestHeader("user-agent") String userAgent,
                   @RequestParam(value = "page") Integer page);

    @GetMapping("api/planets/")
    ResultVO fetchById(@RequestHeader("Accept") String accept,
                       @RequestHeader("user-agent") String userAgent);

    @GetMapping("api/planets/")
    ResultVO fetchByName(@RequestHeader("Accept") String accept,
                         @RequestHeader("user-agent") String userAgent,
                         @RequestParam("search") String search);
}