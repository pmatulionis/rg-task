package co.uk.realisticgames.task.controller;

import co.uk.realisticgames.task.model.api.CarDataItem;
import co.uk.realisticgames.task.model.api.ResponseItem;
import co.uk.realisticgames.task.model.search.FilterParams;
import co.uk.realisticgames.task.model.search.SortParams;
import co.uk.realisticgames.task.service.CarService;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static co.uk.realisticgames.task.utils.ApiDocUtils.*;

/**
 * CarController class.
 *
 * @author Paulius Matulionis
 */
@RestController
@RequestMapping("/api/v1")
public class CarController {

    private final CarService carService;

    public CarController(CarService carService) {
        this.carService = carService;
    }

    @ApiOperation(
            value = "Gets the list of car data",
            notes = "This HTTP operation provides an ability to retrieve all the data, sort it by name and country in " +
                    "ascending or descending orders and also allows the data to be filtered by name, country and type.",
            response = CarDataItem.class,
            responseContainer = "List")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 500, message = "Unexpected internal server error", response = ResponseItem.class)
    })
    @GetMapping("/car-data")
    @ResponseBody
    public List<CarDataItem> get(@ApiParam(SORT_PARAM) @RequestParam(value = "sort", required = false) String sort,
                                 @ApiParam(COUNTRY_PARAM) @RequestParam(value = "country", required = false) String country,
                                 @ApiParam(NAME_PARAM) @RequestParam(value = "name", required = false) String name,
                                 @ApiParam(TYPE_PARAM) @RequestParam(value = "type", required = false) String type) {
        return carService.findData(new SortParams(sort), new FilterParams(country, name, type));
    }

    @ApiOperation(
            value = "Updates the car data",
            notes = "This HTTP operation provides an ability to update certain car data record by the given ID.",
            response = ResponseItem.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 500, message = "Unexpected internal server error", response = ResponseItem.class)
    })
    @PutMapping("/car-data/{id}")
    @ResponseBody
    public ResponseItem put(@ApiParam(ID_PARAM) @PathVariable("id") Integer id,
                            @RequestBody CarDataItem item) {
        return carService.update(id, item);
    }

    @ApiOperation(
            value = "Creates the car data",
            notes = "This HTTP operation provides an ability to create a new car data record.",
            response = ResponseItem.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 500, message = "Unexpected internal server error", response = ResponseItem.class)
    })
    @PostMapping("/car-data")
    @ResponseBody
    public ResponseItem post(@RequestBody CarDataItem item) {
        return carService.create(item);
    }

    @ApiOperation(
            value = "Deletes the car data",
            notes = "This HTTP operation provides an ability to delete a car data by the given ID.",
            response = ResponseItem.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Successful request"),
            @ApiResponse(code = 500, message = "Unexpected internal server error", response = ResponseItem.class)
    })
    @DeleteMapping("/car-data/{id}")
    public ResponseItem delete(@ApiParam(ID_PARAM) @PathVariable("id") Integer id) {
        return carService.remove(id);
    }
}
