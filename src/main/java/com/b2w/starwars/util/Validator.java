package com.b2w.starwars.util;

import com.b2w.starwars.api.vo.PlanetVO;
import com.b2w.starwars.exception.PlanetDataUninformedException;
import com.b2w.starwars.exception.PlanetIdUninformedException;
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

    public static void validatePlanetVO(PlanetVO planetVO)
            throws PlanetUninformedException, PlanetDataUninformedException {
        try {
            Objects.requireNonNull(planetVO);
            validatePlanetData(planetVO);
        } catch (NullPointerException e) {
            log.error("Dados do planeta não informados");
            throw new PlanetUninformedException("Dados do planeta não informados");
        }
    }

    public static void validatePlanetName(String planetName) throws PlanetDataUninformedException {
        if(StringUtils.isBlank(planetName)) {
            log.error("Nome do planeta não foi informado");
            throw new PlanetDataUninformedException("Nome do planeta não foi informado");
        }
    }

    public static void validatePlanetName(PlanetVO planetVO)
            throws PlanetDataUninformedException, PlanetUninformedException {
        try {
            Objects.requireNonNull(planetVO);
            validatePlanetName(planetVO.getName());
        } catch (NullPointerException e) {
            log.error("Dados do planeta não informados");
            throw new PlanetUninformedException("Dados do planeta não informados");
        }
    }

    public static void validatePlanetClimate(String planetClimate) throws PlanetDataUninformedException {
        if(StringUtils.isBlank(planetClimate)) {
            log.error("Clima do planeta não foi informado");
            throw new PlanetDataUninformedException("Clima do planeta não foi informado");
        }
    }

    public static void validatePlanetTerrain(String planetTerrain) throws PlanetDataUninformedException {
        if(StringUtils.isBlank(planetTerrain)) {
            log.error("Terreno do planeta não foi informado");
            throw new PlanetDataUninformedException("Terreno do planeta não foi informado");
        }
    }

    private static void validatePlanetData(PlanetVO planetVO) throws PlanetDataUninformedException {
        validatePlanetName(planetVO.getName());
        validatePlanetTerrain(planetVO.getTerrain());
        validatePlanetClimate(planetVO.getClimate());
    }
}
