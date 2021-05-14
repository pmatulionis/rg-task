package co.uk.realisticgames.task.model.search;

import lombok.Data;

import java.util.Arrays;
import java.util.List;

/**
 * SortParams class.
 *
 * @author Paulius Matulionis
 */
@Data
public class SortParams {

    public static class Direction {
        public static final String ASC = "asc";
        public static final String DESC = "desc";
    }

    public static class Field {
        public static final String COUNTRY = "country";
        public static final String NAME = "name";
    }

    private static final List<String> ALLOWED_FIELDS = Arrays.asList(Field.COUNTRY, Field.NAME);

    private static final List<String> ALLOWED_DIRECTIONS = Arrays.asList(Direction.ASC, Direction.DESC);

    private String field;
    private String direction;

    public SortParams(String sort) {
        if (sort != null) {
            if (sort.contains(":")) {
                String[] split = sort.split(":");
                if (ALLOWED_FIELDS.contains(split[0])) {
                    this.field = split[0];
                }
                if (ALLOWED_DIRECTIONS.contains(split[1])) {
                    this.direction = split[1];
                }

                if (this.field != null && this.direction == null) {
                    this.direction = Direction.ASC;
                }
            } else {
                if (ALLOWED_FIELDS.contains(sort)) {
                    this.field = sort;
                    this.direction = Direction.ASC;
                }
            }
        }
    }

    public boolean performSort() {
        return this.field != null && this.direction != null;
    }
}
