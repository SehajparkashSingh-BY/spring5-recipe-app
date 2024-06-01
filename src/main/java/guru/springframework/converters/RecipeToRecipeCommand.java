package guru.springframework.converters;

import guru.springframework.commands.RecipeCommand;
import guru.springframework.domain.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeToRecipeCommand implements Converter<Recipe, RecipeCommand> {

    private final NotesToNotesCommand toNotesCommand;
    private final IngredientToIngredientCommand toIngredientCommand;
    private final CategoryToCategoryCommand toCategoryCommand;

    public RecipeToRecipeCommand(NotesToNotesCommand toNotesCommand, IngredientToIngredientCommand toIngredientCommand, CategoryToCategoryCommand toCategoryCommand) {
        this.toNotesCommand = toNotesCommand;
        this.toIngredientCommand = toIngredientCommand;
        this.toCategoryCommand = toCategoryCommand;
    }

    @Synchronized
    @Nullable
    @Override
    public RecipeCommand convert(Recipe source) {
        if(source == null) {
            return null;
        }
        final RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(source.getId());
        recipeCommand.setUrl(source.getUrl());
        recipeCommand.setSource(source.getSource());
        recipeCommand.setCookTime(source.getCookTime());
        recipeCommand.setPrepTime(source.getPrepTime());
        recipeCommand.setDirections(source.getDirections());
        recipeCommand.setDifficulty(source.getDifficulty());
        recipeCommand.setDescription(source.getDescription());
        recipeCommand.setServings(source.getServings());

        recipeCommand.setNotes(toNotesCommand.convert(source.getNotes()));

        if(source.getIngredients() != null && !source.getIngredients().isEmpty()){
            source.getIngredients().forEach(ingredient -> {
                recipeCommand.getIngredients().add(toIngredientCommand.convert(ingredient));
            });
        }

        if(source.getCategories() != null && !source.getCategories().isEmpty()){
            source.getCategories().forEach(category -> {
                recipeCommand.getCategories().add(toCategoryCommand.convert(category));
            });
        }

        return recipeCommand;
    }
}
