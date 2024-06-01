package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes toNotes;
    private final IngredientCommandToIngredient toIngredient;
    private final CategoryCommandToCategory toCategory;

    public RecipeCommandToRecipe(NotesCommandToNotes toNotes, IngredientCommandToIngredient toIngredient, CategoryCommandToCategory toCategory) {
        this.toNotes = toNotes;
        this.toIngredient = toIngredient;
        this.toCategory = toCategory;
    }

    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {
        if(source == null) {
            return null;
        }
        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setUrl(source.getUrl());
        recipe.setSource(source.getSource());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setDescription(source.getDescription());
        recipe.setServings(source.getServings());

        recipe.setNotes(toNotes.convert(source.getNotes()));

        if(source.getIngredients() != null && !source.getIngredients().isEmpty()){
            source.getIngredients().forEach(ingredientCommand ->{
                recipe.getIngredients().add(toIngredient.convert(ingredientCommand));
            });
        }

        if(source.getCategories() != null && !source.getCategories().isEmpty()) {
            source.getCategories().forEach(categoryCommand -> {
                recipe.getCategories().add(toCategory.convert(categoryCommand));
            });
        }
        return recipe;
    }
}
