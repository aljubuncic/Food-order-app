package ba.unsa.etf.rpr.domain;

/**
 * Interface that forces all beans of entities to have id field
 */

public interface Identifiable {

    /**
     * Sets id of an entity
     * @param id
     */

    void setId(int id);

    /**
     * Returns id of an entty
     * @return id
     */

    int getId();
}
