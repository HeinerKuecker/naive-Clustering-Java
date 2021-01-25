package de.heinerkuecker.clustering;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

/**
 * Junit4 test case for class {@link Clustering}.
 *
 * @author Heiner K&uuml;cker
 */
public class ClusteringTest
{

    /**
     * Test method for {@link Clustering#clustering(Collection, ClusterCriteria)}.
     */
    @Test
    public void testClustering_empty()
    {
        final List<List<Object>> clusters =
                Clustering.clustering(
                        //elementsToCluster
                        Collections.emptyList() ,
                        //clusterCriteria
                        null );

        //System.out.println( clusters );

        Assert.assertTrue(
                clusters.isEmpty() );
    }

    /**
     * Test method for {@link Clustering#clustering(Collection, ClusterCriteria)}.
     */
    @Test
    public void testClustering_Integer()
    {
        final ClusterCriteria<Integer> clusterCriteria =
                new ClusterCriteria<Integer>()
        {
            @Override
            public boolean isInSameCluster(
                    final Integer e0 ,
                    final Integer e1 )
            {
                return Math.abs( e0 - e1 ) < 3;
            }
        };

        final List<Integer> elementsToCluster =
                Arrays.asList(
                        1 ,
                        2 ,
                        4 ,
                        16 ,
                        17 ,
                        18 ,
                        20 ,
                        23 ,
                        24 );

        Collections.shuffle( elementsToCluster );

        final List<List<Integer>> clusters =
                Clustering.clustering(
                        elementsToCluster ,
                        clusterCriteria );

        //System.out.println( clusters );

        Assert.assertEquals(
                //expected
                3 ,
                //actual
                clusters.size() );

        assertContainsCluster(
                clusters ,
                //expectedValues
                1 ,
                2 ,
                4 );

        assertContainsCluster(
                clusters ,
                //expectedValues
                16 ,
                17 ,
                18 ,
                20 );

        assertContainsCluster(
                clusters ,
                //expectedValues
                23 ,
                24 );
    }

    private static void assertContainsCluster(
            final List<List<Integer>> clusters ,
            final Integer... expectedValues )
    {
        final Collection<Integer> expectedValuesSet =
                        Arrays.asList(
                                expectedValues );

        for ( List<Integer> cluster : clusters )
        {
            if ( cluster.size() == expectedValuesSet.size() )
            {
                if ( cluster.containsAll( expectedValuesSet ) )
                {
                    return;
                }
            }
        }

        Assert.fail();
    }

}
