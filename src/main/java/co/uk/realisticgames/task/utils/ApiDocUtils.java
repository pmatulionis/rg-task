package co.uk.realisticgames.task.utils;

/**
 * ApiDocUtils class.
 *
 * @author Paulius Matulionis
 */
public final class ApiDocUtils {

    private ApiDocUtils() {
    }

    public static final String SORT_PARAM = "Sort parameter to specify the sort option. Value structure must be provided like this: field:sortDirection, for e.g.: country:desc, if nothing after colon is provided - asc sort direction is assumed. Currently supported fields are: country, name.";

    public static final String COUNTRY_PARAM = "Parameter to filter the data by country. If provided, the system will return all the data that matches exact value of the provided country.";

    public static final String NAME_PARAM = "Parameter to filter the data by name. If provided, the system will return all the data that matches exact value of the provided name.";

    public static final String TYPE_PARAM = "Parameter to filter the data by vehicle type. If provided, the system will return all the data that matches exact value of the provided vehicle type.";

    public static final String ID_PARAM = "ID parameter to find certain car data records. ID is not unique and duplicate elements might be matched.";
}
