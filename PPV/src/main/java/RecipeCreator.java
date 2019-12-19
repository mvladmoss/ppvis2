public class RecipeCreator {

    public void create() {
        Recipe recipe = new Recipe();
        recipe.setIngredientsList(paramIngridients);
        recipe.setName(paramName);
        recipe.setRecipeDescription(paramDescription);
        recipe.setLabelSelected(paramLabel);
    }

}
