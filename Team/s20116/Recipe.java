package edu.pjatk.inn.coffeemaker.impl;

import sorcer.core.context.ServiceContext;
import sorcer.service.Context;
import sorcer.service.ContextException;

import java.io.Serializable;
import java.rmi.RemoteException;

/**
 * @author   s20086 Zhypargul Maraeva, s20116 Gunel Babaeva
 */
public class Recipe implements Serializable {
	/**Name of recipe**/
    private String name;
    /**Price for the beverage(recipe)**/
    private int price;
    /**amount of coffee**/
    private int amtCoffee;
    /**amount of milk**/
    private int amtMilk;
    /**amount of sugar**/
    private int amtSugar;
    /**amount of chocolate**/
    private int amtChocolate;
    
    /**
    * Recipe constructor default values
    **/
    public Recipe() {
    	this.name = "";
    	this.price = 0;
    	this.amtCoffee = 0;
    	this.amtMilk = 0;
    	this.amtSugar = 0;
    	this.amtChocolate = 0;
    }
    
    /**
     * method getAmtCholate
	 * @return   Returns the amtChocolate.
	 */
    public int getAmtChocolate() {
		return amtChocolate;
	}
    /**
     * method setAmtCholate 
	 * @param amtChocolate  int the amtChocolate to setValue.
	 */
    public void setAmtChocolate(int amtChocolate) {
		if (amtChocolate >= 0) {
			this.amtChocolate = amtChocolate;
		} 
	}
    /**
     * method getAmtCoffee - get amount of coffee for beverage(recipe)
	 * @return   Returns the amtCoffee.
	 */
    public int getAmtCoffee() {
		return amtCoffee;
	}
    /**
     * method setAmtCoffee set amount of coffee for beverage(recipe)
	 * @param amtCoffee  int The amtCoffee to setValue.
	 */
    public void setAmtCoffee(int amtCoffee) {
		if (amtCoffee >= 0) {
			this.amtCoffee = amtCoffee;
		} 
	}

    /**
     * get amount of milk for beverage(recipe)
	 * @return   Returns the amtMilk.
	 */
    public int getAmtMilk() {
		return amtMilk;
	}

    /**
     * set amount of milk for beverage(recipe)
	 * @param amtMilk   The amtMilk to setValue.
	 */
    public void setAmtMilk(int amtMilk) {
		if (amtMilk >= 0) {
			this.amtMilk = amtMilk;
		} 
	}

    /**
     * get amount of sugar for beverage(recipe)
	 * @return  Returns the amtSugar.
	 */
    public int getAmtSugar() {
		return amtSugar;
	}

    /**
     * set amount of sugar for beverage(recipe)
	 * @param amtSugar   The amtSugar to setValue.
	 */
    public void setAmtSugar(int amtSugar) {
		if (amtSugar >= 0) {
			this.amtSugar = amtSugar;
		} 
	}

    /**
     * return the name of recipe
	 * @return   Returns the key.
	 */
    public String getName() {
		return name;
	}

    /**
     * set the name of recipe
	 * @param name   The key to setValue.
	 */
    public void setName(String name) {
    	if(name != null) {
    		this.name = name;
    	}
	}

    /**
     * get price for the beverage
	 * @return   Returns the price.
	 */
    public int getPrice() {
		return price;
	}

    /**
     * set price for the beverage
	 * @param price   The price to setValue.
	 */
    public void setPrice(int price) {
		if (price >= 0) {
			this.price = price;
		} 
	} 

    /**
     * method checks for name uniqueness
     * @param Recipe r
	 * @return boolean value
	 */
    public boolean equals(Recipe r) {
    	// check if name already exists
        if((this.name).equals(r.getName())) {
            return true;
        }
        return false;
    }

    /**
     * method returns name of Recipe
	 * @return name String
	 */
    public String toString() {
    	return name;
    }

    /**
     * method return Recipe
     * @param Context context 
     * @throws ContextException if ContextException occurrs
	 * @return Recipe r
	 */
	static public Recipe getRecipe(Context context) throws ContextException {
		Recipe r = new Recipe();
        // get Recipe from context
		try {
			r.name         = (String)context.getValue("key");
			r.price        = (int)context.getValue("price");
			r.amtCoffee    = (int)context.getValue("amtCoffee");
			r.amtMilk      = (int)context.getValue("amtMilk");
			r.amtSugar     = (int)context.getValue("amtSugar");
			r.amtChocolate = (int)context.getValue("amtChocolate");
		} catch (RemoteException e) {
			throw new ContextException(e);
		}

		return r;
	}

    /**
     * method get Context return context with saved Recipe there
     * @param Recipe recipe
     * @throws ContextException if ContextException occurrs
	 * @return ctx Context
	 */
	static public Context getContext(Recipe recipe) throws ContextException {
		// saving Recipe into cxt
		Context cxt = new ServiceContext();
		cxt.putValue("key", recipe.getName());
		cxt.putValue("price", recipe.getPrice());
		cxt.putValue("amtCoffee", recipe.getAmtCoffee());
		cxt.putValue("amtMilk", recipe.getAmtMilk());
		cxt.putValue("amtSugar", recipe.getAmtSugar());
		cxt.putValue("amtChocolate", recipe.getAmtChocolate());

		return cxt;
	}

}
