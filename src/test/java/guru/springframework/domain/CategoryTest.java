package guru.springframework.domain;

import junit.framework.TestCase;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CategoryTest {

    Category category;
    @Before
    public void setUp() throws Exception {
        category = new Category();
    }

    @Test
    public void GetId() throws Exception{
        Long idVal = 4L;
        category.setId(idVal);
        assertEquals(category.getId(),new Long(4L));
    }

    @Test
    public void GetDescription() throws Exception{
    }

    @Test
    public void GetRecipes() throws Exception{
    }
}