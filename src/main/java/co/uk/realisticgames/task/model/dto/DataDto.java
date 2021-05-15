package co.uk.realisticgames.task.model.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Generated;

import java.util.List;

/**
 * DataDto class.
 *
 * @author Paulius Matulionis
 */
@Generated
@Data
public class DataDto {

    @JsonProperty("Data")
    private List<CarDataDto> data;
}
