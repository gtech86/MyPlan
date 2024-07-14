package service;

import com.grabowski.MyPlan.TestDataHelper;
import com.grabowski.MyPlan.model.Star;
import com.grabowski.MyPlan.repository.InMemoryStarRepository;
import com.grabowski.MyPlan.service.IStarService;
import com.grabowski.MyPlan.service.StarServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class StarServiceImplTest {

    @InjectMocks
    private StarServiceImpl starService;

    @Mock
    private InMemoryStarRepository starRepository;

    @Test
    void findClosestStarsTest() {
        //given
        List<Star> stars = TestDataHelper.getStars();

        List<Star> result = starService.findClosestStars(stars, 3);
        assertEquals(3, result.size());
        assertEquals("Alpha Centauri", result.get(0).getName());
        assertEquals("Beta", result.get(1).getName());
        assertEquals("Gamma", result.get(2).getName());
    }

    @Test
    void getNumberOfStarsByDistancesTest() {
        List<Star> stars = TestDataHelper.getStars();

        Map<Long, Integer> result = starService.getNumberOfStarsByDistances(stars);
        assertEquals(4, result.size());
        assertEquals(1, result.get(4L));
        assertEquals(1, result.get(5L));
        assertEquals(1, result.get(7L));
        assertEquals(2, result.get(8L));
    }

    @Test
    void getNumberOfStarsByDistancesTest_NullList() {
        assertThrows(IllegalArgumentException.class, () -> starService.getNumberOfStarsByDistances(null));
    }

    @Test
    void getUniqueStarsTest() {
        //given
        List<Star> stars = TestDataHelper.getStars();

        //when
        Collection<Star> result = starService.getUniqueStars(stars);

        //then
        assertEquals(5, stars.size());
        assertEquals(4, result.size());
        assertTrue(result.contains(new Star("Alpha Centauri", 4L)));
        assertTrue(result.contains(new Star("Beta", 5L)));
        assertTrue(result.contains(new Star("Gamma", 7L)));
        assertTrue(result.contains(new Star("Sirius", 8L)));
    }

    @Test
    void getUniqueStarsTest_NullList() {
        assertThrows(IllegalArgumentException.class, () -> starService.getUniqueStars(null));
    }
}