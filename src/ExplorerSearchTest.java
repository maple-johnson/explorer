import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
    @Test
    public void testExplorerLocation_Caldera()
    {
        int[][] island = {
            {3, 3, 3, 3, 3},
            {3, 2, 2, 2, 3},
            {3, 2, 0, 2, 3},
            {3, 2, 2, 2, 3},
            {3, 3, 3, 3, 3}
        };

        int[] expected = {2, 2};
        int[] actual = ExplorerSearch.explorerLocation(island);

        assertArrayEquals(expected, actual);
    }

// Location Check - Bottom Right
    @Test
    public void testExplorerLocation_BottomRight()
    {
        int[][] island = {
            {1, 2, 3, 1, 2},
            {1, 2, 3, 1, 2},
            {1, 2, 3, 1, 2},
            {1, 2, 3, 1, 2},
            {1, 2, 3, 1, 0}
        };

        int[] expected = {4, 4};
        int[] actual = ExplorerSearch.explorerLocation(island);

        assertArrayEquals(expected, actual);
    }

// Location Check - Different Row and Column Coordinates
    @Test
    public void testExplorerLocation_DifferingCoordinates()
    {
        int[][] island = {
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1},
            {1, 1, 1, 1, 1, 0, 1, 1},
            {1, 1, 1, 1, 1, 1, 1, 1}
        };

        int[] expected = {2, 5};
        int[] actual = ExplorerSearch.explorerLocation(island);

        assertArrayEquals(expected, actual);
    }

// Possible Moves - Surrounded by Water
    @Test
    public void testPossibleMoves_SurroundedByWater()
    {
        int[][] island = {
            {2, 2, 2},
            {2, 0, 2},
            {2, 2, 2}
        };

        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);

        assertTrue(moves.isEmpty());
    }

// Possible Moves - Surrounded by Mountains
    @Test
    public void testPossibleMoves_SurroundedByMountains()
    {
        int[][] island = {
            {3, 3, 3},
            {3, 0, 3},
            {3, 3, 3}
        };

        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);

        assertTrue(moves.isEmpty());
    }

// Possible Moves - Surrounded by Out of Bounds
    @Test
    public void testPossibleMoves_SurroundedByOutOfBounds()
    {
        int[][] island = {
            {0}
        };

        int[] location = {0, 0};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);

        assertTrue(moves.isEmpty());
    }

// Possible Moves - Surrounded by Fields
    @Test
    public void testPossibleMoves_SurroundedByFields()
    {
        int[][] island = {
            {1, 1, 1},
            {1, 0, 1},
            {1, 1, 1}
        };

        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(4, moves.size());
        assertTrue(moveSet.contains("0,1"));
        assertTrue(moveSet.contains("1,2"));
        assertTrue(moveSet.contains("2,1"));
        assertTrue(moveSet.contains("1,0"));
    }

// Possible Moves - Different Options in All Directions
    @Test
    public void testPossibleMoves_DifferentOptionsInAllDirections()
    {
        int[][] island = {
            {1, 1, 1},
            {2, 0, 3}
        };

        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(1, moves.size());
        assertTrue(moveSet.contains("0,1"));
    }

// Possible Moves - Two Movement Options
    @Test
    public void testPossibleMoves_TwoMovementOptions()
    {
        int[][] island = {
            {2, 2, 2},
            {1, 0, 1},
            {3, 3, 3}
        };

        int[] location = {1, 1};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(2, moves.size());
        assertTrue(moveSet.contains("1,0"));
        assertTrue(moveSet.contains("1,2"));
    }

// Possible Moves - Three Movement Options
    @Test
    public void testPossibleMoves_ThreeMovementOptions()
    {
        int[][] island = {
            {1, 1, 1, 1, 1},
            {2, 2, 2, 2, 2},
            {3, 3, 1, 0, 1},
            {3, 3, 2, 1, 1},
            {2, 2, 2, 1, 1}
        };

        int[] location = {2, 3};
        List<int[]> moves = ExplorerSearch.possibleMoves(island, location);
        Set<String> moveSet = toSet(moves);

        assertEquals(3, moveSet.size());
        assertTrue(moveSet.contains("2,2"));
        assertTrue(moveSet.contains("2,4"));
        assertTrue(moveSet.contains("3,3"));
    }


    private Set<String> toSet(List<int[]> list)
    {
        Set<String> set = new HashSet<>();
        for (int[] arr : list) set.add(arr[0] + "," + arr[1]);
        return set;
    }

}
