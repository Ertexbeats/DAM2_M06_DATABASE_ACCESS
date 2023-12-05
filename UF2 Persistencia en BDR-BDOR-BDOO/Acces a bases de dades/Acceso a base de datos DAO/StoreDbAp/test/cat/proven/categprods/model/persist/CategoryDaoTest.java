/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package cat.proven.categprods.model.persist;

import cat.proven.categprods.model.Category;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author dax
 */
public class CategoryDaoTest {
    
    public CategoryDaoTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of selectAll method, of class CategoryDao.
     */
    @Test
    public void testSelectAll() {
        System.out.println("selectAll");
        CategoryDao instance = new CategoryDao();
        List<Category> expResult = null;
        List<Category> result = instance.selectAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of selectWhereCode method, of class CategoryDao.
     */
    @Test
    public void testSelectWhereCode() {
        System.out.println("selectWhereCode");
        String code = "";
        CategoryDao instance = new CategoryDao();
        Category expResult = null;
        Category result = instance.selectWhereCode(code);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of insert method, of class CategoryDao.
     */
    @Test
    public void testInsert() {
        System.out.println("insert");
        Category category = null;
        CategoryDao instance = new CategoryDao();
        int expResult = 0;
        int result = instance.insert(category);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
