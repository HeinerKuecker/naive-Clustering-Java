package de.heinerkuecker.clustering;

public interface ClusterCriteria<E>
{
    boolean isInSameCluster(
            final E e0 ,
            final E e1 );
}
