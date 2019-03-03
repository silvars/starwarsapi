package com.b2w.starwars.util;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.exception.PlanetIdUninformedException;
import com.b2w.starwars.exception.PlanetNameUninformedException;
import com.b2w.starwars.exception.PlanetUninformedException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

@Slf4j
public class Validator {

    public static void validatePlanetId(Long planetId) throws PlanetIdUninformedException {
        try {
            Objects.requireNonNull(planetId);
        } catch (NullPointerException e) {
            log.error("Id do planeta não foi informado");
            throw new PlanetIdUninformedException("Id do planeta não foi informado");
        }
    }

    public static void validatePlanetName(String planetName) throws PlanetNameUninformedException {
        if(StringUtils.isBlank(planetName)) {
            log.error("Nome do planeta não foi informado");
            throw new PlanetNameUninformedException("Nome do planeta não foi informado");
        }
    }

    public static void validatePlanetVO(PlanetVO planetVO) throws PlanetUninformedException {
        try {
            Objects.requireNonNull(planetVO);
        } catch (NullPointerException e) {
            log.error("Dados do planeta não informados");
            throw new PlanetUninformedException("Dados do planeta não informados");
        }
    }
}
