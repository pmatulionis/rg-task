package co.uk.realisticgames.task.model.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Generated;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * ResponseItem class.
 *
 * @author Paulius Matulionis
 */
@Generated
@Data
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(description = "Main object to hold the details of a single response message")
public class ResponseItem implements Serializable {

    @ApiModelProperty("Property to specify the message of the response")
    private String message;
}
