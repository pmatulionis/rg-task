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

}
