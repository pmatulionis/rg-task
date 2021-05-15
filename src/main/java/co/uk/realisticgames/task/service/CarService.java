package co.uk.realisticgames.task.service;

import co.uk.realisticgames.task.model.api.CarDataItem;
import co.uk.realisticgames.task.model.api.ResponseItem;
import co.uk.realisticgames.task.model.search.FilterParams;
import co.uk.realisticgames.task.model.search.SortParams;

import java.util.List;

/**
 * CarService interface.
 *
 * @author Paulius Matulionis
 */
public interface CarService {

    /**
     * A service method which maps the data from the API model to DTO (data transfer) object when the data is being
     * filtered This operation might do some other manipulation with the data as well. For e.g. it could filter out the
     * records that do not have certain properties as longs as all the retrieval and data filtering happens in the data
     * storage level.
     *
     * @param sortParams   sort parameters
     * @param filterParams filter parameters
     * @return a list of car data objects
     */
    List<CarDataItem> findData(SortParams sortParams, FilterParams filterParams);

    /**
     * A service method which maps the data from the API model to DTO (data transfer) object when the data is being
     * updated. This method can also do other manipulation with the data before it is actually updated.
     *
     * @param id   id to find car data
     * @param item API model property which holds car data details
     * @return response object containing a response message
     */
    ResponseItem update(Integer id, CarDataItem item);

    /**
     * A service method which maps the data from the API model to DTO (data transfer) object when the data is being
     * created. This method can also do other manipulation with the data before it is actually updated.
     *
     * @param item API model property which holds car data details
     * @return response object containing a response message
     */
    ResponseItem create(CarDataItem item);

    /**
     * A service method which calls data storage API to actually remove the data from storage.
     *
     * @param id ID of the data that need to be deleted
     * @return response object containing a response message
     */
    ResponseItem remove(Integer id);
}
