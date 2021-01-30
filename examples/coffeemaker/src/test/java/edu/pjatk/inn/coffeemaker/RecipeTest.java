package edu.pjatk.inn.coffeemaker;

import edu.pjatk.inn.coffeemaker.impl.CoffeeMaker;
import edu.pjatk.inn.coffeemaker.impl.Inventory;
import edu.pjatk.inn.coffeemaker.impl.Recipe;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sorcer.test.ProjectContext;
import org.sorcer.test.SorcerTestRunner;
import sorcer.service.ContextException;

import static org.junit.Assert.*;


@RunWith(SorcerTestRunner.class)
@ProjectContext("examples/coffeemaker")
public class RecipeTest {
    private final static Logger logger = LoggerFactory.getLogger(CoffeeMakerTest.class);

    private CoffeeMaker coffeeMaker;
    private Inventory inventory;
    private Recipe espresso, mocha, macchiato, americano;

    @Before
    public void setUp() throws ContextException {
        coffeeMaker = new CoffeeMaker();
        inventory = coffeeMaker.checkInventory();

        espresso = new Recipe();
        espresso.setName("espresso");
        espresso.setPrice(50);
        espresso.setAmtCoffee(6);
        espresso.setAmtMilk(1);
        espresso.setAmtSugar(1);
        espresso.setAmtChocolate(0);

        mocha = new Recipe();
        mocha.setName("mocha");
        mocha.setPrice(100);
        mocha.setAmtCoffee(8);
        mocha.setAmtMilk(1);
        mocha.setAmtSugar(1);
        mocha.setAmtChocolate(2);

        macchiato = new Recipe();
        macchiato.setName("macchiato");
        macchiato.setPrice(40);
        macchiato.setAmtCoffee(7);
        macchiato.setAmtMilk(1);
        macchiato.setAmtSugar(2);
        macchiato.setAmtChocolate(0);

        americano = new Recipe();
        americano.setName("americano");
        americano.setPrice(40);
        americano.setAmtCoffee(7);
        americano.setAmtMilk(1);
        americano.setAmtSugar(2);
        americano.setAmtChocolate(0);
    }

    
    @Test
    public void testAddRecipe() {
        assertTrue(coffeeMaker.addRecipe(espresso));
        assertTrue(coffeeMaker.addRecipe(macchiato));
        assertTrue(coffeeMaker.addRecipe(americano));
    }

    
    @Test
    public void testDeleteRecipe() {
        assertFalse(coffeeMaker.deleteRecipe(espresso));
        assertTrue(coffeeMaker.addRecipe(espresso));
        assertTrue(coffeeMaker.deleteRecipe(espresso));
        assertFalse(coffeeMaker.deleteRecipe(espresso)); 
    }

    
    @Test
    public void testEditRecipe() {
        assertTrue(coffeeMaker.addRecipe(espresso));
        assertTrue(coffeeMaker.addRecipe(mocha));
    }

   // Test change recipe name passed successfully
    @Test
    public void testChangeRecipeName() {

        String oldName = espresso.getName();
        espresso.setName("ChangedName");
        Assert.assertTrue(coffeeMaker.editRecipe(espresso, espresso));

        Assert.assertEquals(oldName, recipe.getName());
    }


    /**
    * All test with negative insertion passed successfully
    *
    */
    @Test
    public void testSetNegativePrice() {
        espresso.setPrice(-3);
        Assert.assertEquals(0, espresso.getPrice());
    }

    @Test
    public void testAddRecipeWithNegativeAmountChocolate() {
        espresso.setAmtChocolate(-7);
        Assert.assertEquals(0, espresso.getAmtChocolate());
    }

    @Test
    public void testAddRecipeWithNegativeAmountCoffee() {
        espresso.setAmtCoffee(-7);
        Assert.assertEquals(0, espresso.getAmtCoffee());
    }

    @Test
    public void testAddRecipeWithNegativeAmountMilk() {
        espresso.setAmtMilk(-7);
        Assert.assertEquals(0, espresso.getAmtMilk());
    }

    @Test
    public void testAddRecipeWithNegativeAmountSugar() {
        espresso.setAmtSugar(-7);
        Assert.assertEquals(0, espresso.getAmtSugar()); // fixed bug in CoffeeMaker, run is OK
    }
}