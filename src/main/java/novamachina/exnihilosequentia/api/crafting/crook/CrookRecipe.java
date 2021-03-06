package novamachina.exnihilosequentia.api.crafting.crook;

import novamachina.exnihilosequentia.api.crafting.ItemStackWithChance;
import novamachina.exnihilosequentia.api.crafting.RecipeSerializer;
import novamachina.exnihilosequentia.api.crafting.SerializableRecipe;
import novamachina.exnihilosequentia.common.utility.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.RegistryObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CrookRecipe extends SerializableRecipe {
    public static IRecipeType<CrookRecipe> TYPE = IRecipeType.register(Constants.ModIds.EX_NIHILO_SEQUENTIA + ":crook");
    public static RegistryObject<RecipeSerializer<CrookRecipe>> SERIALIZER;
    private final Ingredient input;
    private final List<ItemStackWithChance> output;
    public CrookRecipe(ResourceLocation id, Ingredient input, List<ItemStackWithChance> output) {
        super(output.get(0).getStack(), TYPE, id);
        this.input = input;
        this.output = output;
    }

    public List<ItemStackWithChance> getOutput() {
        return output;
    }

    public Ingredient getInput() {
        return input;
    }

    @Override
    protected RecipeSerializer getENSerializer() {
        return SERIALIZER.get();
    }

    @Override
    public ItemStack getRecipeOutput() {
        return output.get(0).getStack().copy();
    }

    public List<ItemStack> getInputs() {
        return Arrays.asList(input.getMatchingStacks());
    }

    public List<ItemStack> getOutputsWithoutChance() {
        List<ItemStack> returnList = new ArrayList<>();
        output.forEach(stack -> returnList.add(stack.getStack()));
        return returnList;
    }

    public List<ItemStackWithChance> getOutputsWithChance() {
        return output;
    }
}
