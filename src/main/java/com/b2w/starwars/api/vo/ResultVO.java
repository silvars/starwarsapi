package com.b2w.starwars.api.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class ResultVO {

    private String next;
    private List<PlanetVO> results;

    public Boolean hasNext() {
        return !StringUtils.isBlank(next);
    }
}
