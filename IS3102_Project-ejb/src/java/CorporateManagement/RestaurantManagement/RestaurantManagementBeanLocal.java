package CorporateManagement.RestaurantManagement;

import EntityManager.ItemEntity;
import EntityManager.MenuItemEntity;
import EntityManager.RawIngredientEntity;
import EntityManager.RecipeEntity;
import java.util.List;
import javax.ejb.Local;

@Local
public interface RestaurantManagementBeanLocal {

    public boolean addRawIngredients(String SKU, String name, String category, String description, Integer _length, Integer width, Integer height, Integer lotSize, Integer leadTime, Double price, Long supplierId);

    public boolean editRawIngredients(String id, String SKU, String name, String category, String description, Integer lotSize, Integer leadTime, Double price, Long supplierId);

    public boolean removeRawIngredients(String SKU);

    public RawIngredientEntity viewRawIngredients(String SKU);

    public boolean addMenuItem(String SKU, String name, String category, String description, String imageURL, Integer _length, Integer width, Integer height);

    public boolean editMenuItem(String id, String SKU, String name, String category, String description, String imageURL);

    public boolean removeMenuItem(String SKU);

    public MenuItemEntity viewMenuItem(String SKU);

    public List<RawIngredientEntity> listAllRawIngridients();

    public List<MenuItemEntity> listAllMenuItem();

    public List<MenuItemEntity> listAllMenuItemWithoutRecipe();

    public boolean createRecipe(String name, String description);

    public boolean editRecipe(Long recipeId, String name, String description);

    public boolean deleteRecipe(Long recipeId);

    public RecipeEntity viewSingleRecipe(Long recipeId);

    public boolean addLineItemToRecipe(String SKU, Integer qty, Long recipeId);

    public boolean deleteLineItemFromRecipe(Long lineItemId, Long recipeId);

    public boolean linkRecipeAndMenuItem(Long recipeId, Long menuItemId);

    public List<RecipeEntity> listAllRecipe();

    public ItemEntity getItemBySKU(String SKU);

    public boolean checkSKUExists(String SKU);
}
