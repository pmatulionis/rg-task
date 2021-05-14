package co.uk.realisticgames.task.controller;

import co.uk.realisticgames.task.model.api.CarDataItem;
import co.uk.realisticgames.task.model.api.ResponseItem;
import co.uk.realisticgames.task.model.search.FilterParams;
import co.uk.realisticgames.task.model.search.SortParams;
import co.uk.realisticgames.task.service.CarService;

import io.swagger.annotations.ApiParam;

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

    @GetMapping("/car-data")
    @ResponseBody
    public List<CarDataItem> get(@ApiParam(SORT_PARAM) @RequestParam(value = "sort", required = false) String sort,
                                 @RequestParam(value = "country", required = false) String country,
                                 @RequestParam(value = "name", required = false) String name,
                                 @RequestParam(value = "type", required = false) String type) {
        return carService.findData(new SortParams(sort), new FilterParams(country, name, type));
    }

    @PutMapping("/car-data/{id}")
    @ResponseBody
    public ResponseItem put(@PathVariable("id") Integer id,
                            @RequestBody CarDataItem item) {
        return carService.update(id, item);
    }

    @PostMapping("/car-data")
    @ResponseBody
    public ResponseItem post(@RequestBody CarDataItem item) {
        return carService.create(item);
    }

    @DeleteMapping("/car-data/{id}")
    public ResponseItem delete(@PathVariable("id") Integer id) {
        return carService.remove(id);
    }
}
