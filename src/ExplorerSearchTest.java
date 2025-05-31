import static org.junit.Assert.*;
import org.junit.Test;

public class ExplorerSearchTest
{
// Original Test    
    @Test
    public void testReachableArea_someUnreachable() {
        int[][] island = {
            {1,1,1,3,1,1},
            {3,2,3,1,3,1},
            {1,1,1,1,3,3},
            {3,1,2,1,0,1},
            {1,1,1,2,1,1},
        };
        int actual = ExplorerSearch.reachableArea(island);
        assertEquals(14, actual);
    }

// Location Check - No Explorer
    @Test
    public void testExplorerLocation_NoExplorer()
    {
        int[][] island = {
            {1,1,1,1,1},
            {2,2,2,2,2},
            {3,3,3,3,3},
            {2,2,2,2,2},
            {1,1,1,1,1}
        };
        
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ExplorerSearch.explorerLocation(island);
        });
        assertEquals("No explorer on the island.", exception.getMessage());
    }

// Location Check - Top Left
    @Test
    public void testExplorerLocation_TopLeft()
    {
        int[][] island = {
            {0, 1, 1, 1, 1},
            {1, 1, 1, 1, 1},
            {3, 1, 2, 2, 3},
            {1, 1, 2, 2, 3},
            {1, 1, 1, 1, 1}
        };

        int[] expected = {0, 0};
        int[] actual = ExplorerSearch.explorerLocation(island);

        assertArrayEquals(expected, actual);
    }

// Location Check - Caldera


// Location Check - Bottom Right


}
