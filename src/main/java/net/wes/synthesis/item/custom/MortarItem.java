package net.wes.synthesis.item.custom;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.fabricmc.api.*;

public class MortarItem extends Item {
    public MortarItem(Settings settings) {
        super(settings);
    }

    @Override
    public ItemStack getRecipeRemainder(ItemStack stack) {
        ItemStack copy = stack.copy();
        copy.setDamage(stack.getDamage() + 1);
        if (copy.getDamage() >= copy.getMaxDamage()) {
            return ItemStack.EMPTY;
        }
        return copy;
    }
}
