package co.uk.realisticgames.task.model.dto;

import co.uk.realisticgames.task.model.api.CarDataItem;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * CarDataDto class.
 *
 * @author Paulius Matulionis
 */
@Generated
@Data
@NoArgsConstructor
public class CarDataDto implements Serializable {

    @JsonProperty("Mfr_ID")
    private Integer id;

    @JsonProperty("Country")
    private String country;

    @JsonProperty("Mfr_CommonName")
    private String commonName;

    @JsonProperty("Mfr_Name")
    private String name;

    @JsonProperty("VehicleTypes")
    private List<VehicleTypeDto> types;

    public CarDataDto(CarDataItem item) {
        this.id = item.getId();
        this.country = item.getCountry();
        this.commonName = item.getCommonName();
        this.name = item.getName();
        this.types = item.getTypes() != null ? item.getTypes().stream().map(VehicleTypeDto::new).toList() : Collections.emptyList();
    }
}
