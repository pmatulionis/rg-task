package co.uk.realisticgames.task.model.search;

/**
 * FilterParams record.
 *
 * @author Paulius Matulionis
 */
public record FilterParams(String country,
                           String name,
                           String type) {

    public boolean performFilter() {
        return country != null || name != null || type != null;
    }
}
