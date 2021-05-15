package co.uk.realisticgames.task.model.api;

import co.uk.realisticgames.task.model.dto.CarDataDto;

import io.swagger.annotations.ApiParam;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * CarDataItem class.
 *
 * @author Paulius Matulionis
 */
@Generated
@Data
@NoArgsConstructor
public class CarDataItem implements Serializable {

    private Integer id;

    @ApiParam("Property to specify country name")
    private String country;

    private String commonName;

    private String name;

    private List<VehicleTypeItem> types;

    public CarDataItem(CarDataDto dto) {
        this.id = dto.getId();
        this.country = dto.getCountry();
        this.commonName = dto.getCommonName();
        this.name = dto.getName();
        this.types = dto.getTypes().stream().map(VehicleTypeItem::new).toList();
    }
}
