package tests.model.menu;

import model.menu.Menu;
import model.menu.MenuPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

/**
 * The type Menu test.
 */
public class MenuTest {
    /**
     * The Menu.
     */
    private Menu menu;

    /**
     * Sets up.
     */
    @Before
    public void setUp() {
        this.menu = new Menu();
    }

    /**
     * Tear down.
     */
    @After
    public void tearDown() {
        this.menu = null;
    }

    /**
     * Test menu constructor.
     */
    @Test
    public void testMenuConstructor() {
        Menu menu = new Menu();
    }

    /**
     * Test add actual page.
     */
    @Test
    public void testAddActualPage() {
        this.menu.addActualPage(MenuPage.CREDITS);
        assertEquals(this.menu.getActualPage(), MenuPage.CREDITS);
    }

    /**
     * Test back previous page.
     */
    @Test
    public void testBackPreviousPage() {
        this.menu.addActualPage(MenuPage.CHANGE_SETTINGS);
        this.menu.addActualPage(MenuPage.CREDITS);
        this.menu.backPreviousPage();
        assertEquals(this.menu.getActualPage(), MenuPage.CHANGE_SETTINGS);
    }

    /**
     * Test get actual page.
     */
    @Test
    public void TestGetActualPage() {
        this.menu.addActualPage(MenuPage.CHANGE_SETTINGS);
        assertEquals(this.menu.getActualPage(), MenuPage.CHANGE_SETTINGS);
    }

    /**
     * Test play offline.
     */
    @Test
    public void testPlayOffline() {
        this.menu.playOffline();
        assertFalse(this.menu.isOnlineClient());
        assertFalse(this.menu.isOnlineServer());
    }
}
