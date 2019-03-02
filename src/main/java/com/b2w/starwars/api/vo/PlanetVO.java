package com.b2w.starwars.api.vo;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class PlanetVO {

    private Long planetId;

    @NotEmpty(message = "Informe o nome do planeta")
    private String name;

    @NotNull(message = "Informe o clima do planeta")
    private String climate;

    @NotNull(message = "Informe o tipo de terreno do planeta")
    private String terrain;

    private List<String> films;

    private Integer apparitions;
}
