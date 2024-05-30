package guru.springframework.Bootstrap;

import guru.springframework.domain.*;
import guru.springframework.repositories.CategoryRepository;
import guru.springframework.repositories.RecipeRepository;
import guru.springframework.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Slf4j
@Component
public class DataLoader implements CommandLineRunner {

    private final RecipeRepository recipeRepository;
    private final CategoryRepository categoryRepository;
    private final UnitOfMeasureRepository unitOfMeasureRepository;

    public DataLoader(RecipeRepository recipeRepository, CategoryRepository categoryRepository, UnitOfMeasureRepository unitOfMeasureRepository) {
        this.recipeRepository = recipeRepository;
        this.categoryRepository = categoryRepository;
        this.unitOfMeasureRepository = unitOfMeasureRepository;
    }

    @Override
    @Transactional
    public void run(String... args) throws Exception {

        log.debug("LOADING ..........................");
        Optional <UnitOfMeasure> uomTablespoonOptional = unitOfMeasureRepository.findByDescription("Tablespoon");
        Optional <UnitOfMeasure> uomTeaspoonOptional = unitOfMeasureRepository.findByDescription("Teaspoon");
        Optional <UnitOfMeasure> uomEachOptional = unitOfMeasureRepository.findByDescription("Each");
        Optional <UnitOfMeasure> uomCupOptional = unitOfMeasureRepository.findByDescription("Cup");
        Optional <UnitOfMeasure> uomPinchOptional = unitOfMeasureRepository.findByDescription("Pinch");
        Optional <UnitOfMeasure> uomOunceOptional = unitOfMeasureRepository.findByDescription("Ounce");

        Optional<Category> categoryAmericanOptional = categoryRepository.findByDescription("American");
        Optional<Category> categoryMexicanOptional = categoryRepository.findByDescription("Mexican");

        Recipe recipe1 = new Recipe();
        recipe1.setDescription("Perfect Guacamole");
        recipe1.setPrepTime(10);
        recipe1.setCookTime(0);
        recipe1.setServings(4);
        recipe1.setDifficulty(Difficulty.valueOf("EASY"));
        recipe1.setSource("Simplyrecipes.com");
        recipe1.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/#toc-ingredients-for-easy-guacamole");
        recipe1.setDirections("1. Cut the avocados:\n" +
                "Cut the avocados in half. Remove the pit. Score the inside of the avocado with a blunt knife and scoop out the flesh with a spoon. (See How to Cut and Peel an Avocado.) Place in a bowl." +
                "2. Mash the avocado flesh:\n" +
                "Using a fork, roughly mash the avocado. (Don't overdo it! The guacamole should be a little chunky.)" +
                "3. Add the remaining ingredients to taste:\n" +
                "Sprinkle with salt and lime (or lemon) juice. The acid in the lime juice will provide some balance to the richness of the avocado and will help delay the avocados from turning brown.\n" +
                "\n" +
                "Add the chopped onion, cilantro, black pepper, and chilis. Chili peppers vary individually in their spiciness. So, start with a half of one chili pepper and add more to the guacamole to your desired degree of heat.\n" +
                "\n" +
                "Remember that much of this is done to taste because of the variability in the fresh ingredients. Start with this recipe and adjust to your taste.\n" +
                "\n" +
                "4. Serve immediately:\n" +
                "If making a few hours ahead, place plastic wrap on the surface of the guacamole and press down to cover it to prevent air reaching it. (The oxygen in the air causes oxidation which will turn the guacamole brown.)\n" +
                "\n" +
                "Garnish with slices of red radish or jigama strips. Serve with your choice of store-bought tortilla chips or make your own homemade tortilla chips.\n" +
                "\n" +
                "Refrigerate leftover guacamole up to 3 days.\n" +
                "\n");

        Notes notes1 = new Notes();
        notes1.setRecipeNotes("Chilling tomatoes hurts their flavor. So, if you want to add chopped tomato to your guacamole, add it just before serving.");
        notes1.setRecipe(recipe1);
        recipe1.setNotes(notes1);

        recipe1.getIngredients().add(new Ingredient("ripe avocados", new BigDecimal(2),recipe1,uomEachOptional.get()));
        recipe1.getIngredients().add(new Ingredient("kosher salt", new BigDecimal(1/4), recipe1, uomTeaspoonOptional.get()));
        recipe1.getIngredients().add(new Ingredient("lemon juice",new BigDecimal(1),recipe1,uomTablespoonOptional.get()));
        recipe1.getIngredients().add(new Ingredient("minced red onion", new BigDecimal(2), recipe1, uomTablespoonOptional.get()));
        recipe1.getIngredients().add(new Ingredient("serrano chills", new BigDecimal(1), recipe1, uomEachOptional.get()));
        recipe1.getIngredients().add(new Ingredient("cilantro", new BigDecimal(2), recipe1, uomTablespoonOptional.get()));
        recipe1.getIngredients().add(new Ingredient("ground black pepper", new BigDecimal(1), recipe1, uomPinchOptional.get()));
        recipe1.getIngredients().add(new Ingredient("ripe tomato", new BigDecimal(1/2), recipe1, uomEachOptional.get()));
        recipe1.getIngredients().add(new Ingredient("red radish", new BigDecimal(1), recipe1, uomEachOptional.get()));

        recipe1.getCategories().add(categoryAmericanOptional.get());

        recipeRepository.save(recipe1);

        Recipe recipe2 = new Recipe();
        recipe2.setDescription("Spicy Grilled Chicken Tacos");
        recipe2.setPrepTime(20);
        recipe2.setCookTime(15);
        recipe2.setServings(4);
        recipe2.setDifficulty(Difficulty.valueOf("EASY"));
        recipe2.setSource("Simplyrecipes.com");
        recipe2.setUrl("https://www.simplyrecipes.com/recipes/perfect_guacamole/#toc-ingredients-for-easy-guacamole");
        recipe2.setDirections("1. Prepare the grill:\n" +
                "Prepare either a gas or charcoal grill for medium-high, direct heat.\n" +
                "\n" +
                "2. Make the marinade and coat the chicken:\n" +
                "In a large bowl, stir together the chili powder, oregano, cumin, sugar, salt, garlic and orange zest. Stir in the orange juice and olive oil to make a loose paste. Add the chicken to the bowl and toss to coat all over.\n" +
                "\n" +
                "Set aside to marinate while the grill heats and you prepare the rest of the toppings." +
                "3. Grill the chicken:\n" +
                "Grill the chicken for 3 to 4 minutes per side, or until a thermometer inserted into the thickest part of the meat registers 165°F. Transfer to a plate and rest for 5 minutes.\n" +
                "\n" +
                "4. Thin the sour cream with milk:\n" +
                "Stir together the sour cream and milk to thin out the sour cream to make it easy to drizzle.\n" +
                "\n" +
                "5. Assemble the tacos:\n" +
                "Slice the chicken into strips. On each tortilla, place a small handful of arugula. Top with chicken slices, sliced avocado, radishes, tomatoes, and onion slices. Drizzle with the thinned sour cream. Serve with lime wedges.\n" +
                "\n" +
                "6. Warm the tortillas:\n" +
                "Place each tortilla on the grill or on a hot, dry skillet over medium-high heat. As soon as you see pockets of the air start to puff up in the tortilla, turn it with tongs and heat for a few seconds on the other side.\n" +
                "\n" +
                "Wrap warmed tortillas in a tea towel to keep them warm until serving.");

        Notes notes2 = new Notes();
        notes2.setRecipeNotes("Look for ancho chile powder with the Mexican ingredients at your grocery store, on buy it online. (If you can't find ancho chili powder, you replace the ancho chili, the oregano, and the cumin with 2 1/2 tablespoons regular chili powder, though the flavor won't be quite the same.)");
        notes2.setRecipe(recipe2);
        recipe2.setNotes(notes2);

        recipe2.getIngredients().add(new Ingredient("ancho chili powder", new BigDecimal(2),recipe2,uomTablespoonOptional.get()));
        recipe2.getIngredients().add(new Ingredient("dried oregano", new BigDecimal(1), recipe2, uomTeaspoonOptional.get()));
        recipe2.getIngredients().add(new Ingredient("dried cumin",new BigDecimal(1),recipe2,uomTeaspoonOptional.get()));
        recipe2.getIngredients().add(new Ingredient("sugar", new BigDecimal(1), recipe2, uomTeaspoonOptional.get()));
        recipe2.getIngredients().add(new Ingredient("kosher salt", new BigDecimal(1/2), recipe2, uomTeaspoonOptional.get()));
        recipe2.getIngredients().add(new Ingredient("clove garlic", new BigDecimal(1), recipe2, uomEachOptional.get()));
        recipe2.getIngredients().add(new Ingredient("finely grated orange zest", new BigDecimal(1), recipe2, uomTablespoonOptional.get()));
        recipe2.getIngredients().add(new Ingredient("orange juice", new BigDecimal(3), recipe2, uomTablespoonOptional.get()));
        recipe2.getIngredients().add(new Ingredient("olive oil", new BigDecimal(2), recipe2, uomTablespoonOptional.get()));
        recipe2.getIngredients().add(new Ingredient("boneless chicken thighs", new BigDecimal(4), recipe2, uomEachOptional.get()));

        recipe2.getCategories().add(categoryAmericanOptional.get());
        recipe2.getCategories().add(categoryMexicanOptional.get());
//        categoryAmericanOptional.get().getRecipes().add(recipe2);
        recipeRepository.save(recipe2);

    }
}
