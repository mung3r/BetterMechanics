package net.edoxile.bettermechanics.utils;

/**
 * Created by IntelliJ IDEA.
 * User: Edoxile
 */

import net.edoxile.bettermechanics.BetterMechanics;
import net.edoxile.bettermechanics.exceptions.KeyNotFoundException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.logging.Logger;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * Store of recipes.
 *
 * @author sk89q, Edoxile
 */
public class CauldronCookbook {
    private List<Recipe> recipes = new ArrayList<Recipe>();
    private static final Logger log = Logger.getLogger("Minecraft");
    private FileConfiguration config;
    private InputStream defaultConfig;
    private File configFile;

    @SuppressWarnings("unchecked")
    public CauldronCookbook(BetterMechanics plugin) {
        //Load in the configuration and it's defaults
        configFile = new File(plugin.getDataFolder(), "cauldron-recipes.yml");
        config = YamlConfiguration.loadConfiguration(configFile);
        defaultConfig = plugin.getResource("cauldron-recipes.yml");
        YamlConfiguration defaults = YamlConfiguration.loadConfiguration(defaultConfig);
        config.setDefaults(defaults);
        config.options().copyDefaults(true);
        try {
            config.save(configFile);
        } catch (IOException e1) {
        }


        ConfigurationSection rSection = config.getConfigurationSection("recipes");
        if (rSection != null) {
            Set<String> recipeNames = rSection.getKeys(false);
            if (recipeNames == null) {
                log.warning("[BetterMechanics] Error loading cauldron recipes: no recipes found! (you probably messed up the yml format somewhere)");
                return;
            }
            for (String name : recipeNames) {
                MaterialMap ingredients = new MaterialMap();
                MaterialMap results = new MaterialMap();
                try {
                    List<List<Integer>> list = (List<List<Integer>>) rSection.get("recipes." + name + ".ingredients");
                    for (List<Integer> l : list) {
                        ingredients.put(l.get(0), l.get(1));
                    }
                    list = (List<List<Integer>>) rSection.get("recipes." + name + ".results");
                    for (List<Integer> l : list) {
                        results.put(l.get(0), l.get(1));
                    }
                } catch (Exception e) {
                    recipes.clear();
                    log.warning("[BetterMechanics] Error loading cauldron recipes: " + e.getMessage() + "(" + e.getClass().getName() + ") (you probably messed up the yml format somewhere)");
                    return;
                }

                add(new Recipe(name, ingredients, results));
            }
            log.info("[BetterMechanics] Cauldron loaded " + size() + " recipes.");
        }
    }

    public void add(Recipe recipe) {
        recipes.add(recipe);
    }

    public Recipe find(MaterialMap ingredients) {
        for (Recipe recipe : recipes) {
            if (recipe.hasAllIngredients(ingredients)) {
                return recipe;
            }
        }
        return null;
    }

    public int size() {
        return recipes.size();
    }

    public static final class Recipe {
        private final String name;
        private final MaterialMap ingredients;
        private final MaterialMap results;

        public Recipe(String name, MaterialMap ingredients, MaterialMap results) {
            this.name = name;
            this.ingredients = ingredients;
            this.results = results;
        }

        public String getName() {
            return name;
        }

        public boolean hasAllIngredients(MaterialMap check) {
            MaterialMapIterator iterator = ingredients.iterator();
            do {
                iterator.next();
                try {
                    if (check.get(iterator.key()) < iterator.value()) {
                        return false;
                    }
                } catch (KeyNotFoundException e) {
                    return false;
                }
            } while (iterator.hasNext());
            return true;
        }

        public MaterialMap getResults() {
            return results;
        }

        public MaterialMap getIngredients() {
            return ingredients;
        }
    }
}