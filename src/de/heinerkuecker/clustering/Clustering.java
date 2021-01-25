package de.heinerkuecker.clustering;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Clustering.
 *
 * @author Heiner K&uuml;cker
 */
public class Clustering
{
    /**
     * Clustering.
     *
     * @param elementsToCluster elements (objects) to cluster
     * @param clusterCriteria cluster criteria
     * @return list of clusters
     * @param <E> type of elements (objects) to cluster
     */
    public static <E> List<List<E>> clustering(
            final Collection<? extends E> elementsToCluster ,
            final ClusterCriteria<? super E> clusterCriteria )
    {
        final List<E> elementList = new ArrayList<>( elementsToCluster ); // keep given collection unchanged

        final List<List<E>> clusters = new ArrayList<>();

        //int countOfNewAddedSingleCluster = 0;

        VALUE_LOOP : while ( ! elementList.isEmpty() )
        {
            final E element = elementList.remove( elementList.size() - 1 );

            for ( final List<E> cluster : clusters )
            {
                for ( final E clusterValue : cluster )
                {
                    if ( clusterCriteria.isInSameCluster(
                            element ,
                            clusterValue ) )
                    {
                        cluster.add( element );
                        continue VALUE_LOOP;
                    }
                }
            }

            // new cluster
            clusters.add( new ArrayList<>( Arrays.asList( element ) ) );
            //countOfNewAddedSingleCluster++;

            // Possible improvement in performance, not measured.
            // Mögliche Verbesserung Performance, nicht gemessen.
            //if ( countOfNewAddedSingleCluster > 3 )
            //{
            //    joinAdjacentCluster(
            //            clusters ,
            //            clusterCriteria );
            //    countOfNewAddedSingleCluster = 0;
            //}
        }

        joinAdjacentClusters(
                clusters ,
                clusterCriteria );

        return clusters;
    }

    private static <E> void joinAdjacentClusters(
            final List<List<E>> clusters ,
            final ClusterCriteria<? super E> clusterCriteria )
    {
        REPEAT: do
        {
            for ( int i = 0 ; i < clusters.size() - 1 ; ++i )
            {
                final List<E> clusterToJoin = clusters.get( i );

                for ( int j = i + 1 ; j < clusters.size() ; ++j )
                {
                    final List<E> otherCluster = clusters.get( j );

                    if ( isAdjacent(
                            clusterToJoin ,
                            otherCluster ,
                            clusterCriteria ) )
                    {
                        clusterToJoin.addAll( otherCluster );
                        clusters.remove( j );
                        // cluster joined, repeat from start
                        continue REPEAT;
                    }
                }
            }

            // no cluster joined
            return;
        }
        while ( true );
    }

    private static <E> boolean isAdjacent(
            final List<? extends E> cluster0 ,
            final List<? extends E> cluster1 ,
            final ClusterCriteria<? super E> clusterCriteria )
    {
        for ( final E value0 : cluster0 )
        {
            for ( final E value1 : cluster1 )
            {
                if ( clusterCriteria.isInSameCluster(
                        value0 ,
                        value1 ) )
                {
                    return true;
                }
            }
        }
        return false;
    }
}
