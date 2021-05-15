package co.uk.realisticgames.task.model.api;

import co.uk.realisticgames.task.model.dto.CarDataDto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

import static co.uk.realisticgames.task.utils.ApiDocUtils.*;

/**
 * CarDataItem class.
 *
 * @author Paulius Matulionis
 */
@Generated
@Data
@NoArgsConstructor
@ApiModel(description = "Main object to hold the details of a single car data")
public class CarDataItem implements Serializable {

    @ApiModelProperty(value = ID_PARAM)
    private Integer id;

    @ApiModelProperty("Property to specify country name")
    private String country;

    @ApiModelProperty("Property to specify a common name")
    private String commonName;

    @ApiModelProperty("Property to specify a name")
    private String name;

    @ApiModelProperty("Property to specify a list of possible types")
    private List<VehicleTypeItem> types;

    public CarDataItem(CarDataDto dto) {
        this.id = dto.getId();
        this.country = dto.getCountry();
        this.commonName = dto.getCommonName();
        this.name = dto.getName();
        this.types = dto.getTypes().stream().map(VehicleTypeItem::new).toList();
    }
}
