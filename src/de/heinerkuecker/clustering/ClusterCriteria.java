package de.heinerkuecker.clustering;

/**
 * Cluster criteria.
 * Interface to implement.
 *
 * @param <E>
 * 		Type of elements to be checked whether they belong to the same cluster.
 * 		Typ der Elemente, die geprüft werden sollen, ob sie zum selben Cluster gehören.
 *
 * @author Heiner K&uuml;cker
 */
public interface ClusterCriteria<E>
{
    /**
     * @param e0 element to check
     * @param e1 element to check
     * @return given elements belong to the same cluster or not
     */
    boolean isInSameCluster(
            final E e0 ,
            final E e1 );
}
