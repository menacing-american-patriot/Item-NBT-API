package org.jeffstein.nbtapi.plugin.tests.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import org.jeffstein.nbtapi.NBT;
import org.jeffstein.nbtapi.NBTItem;
import org.jeffstein.nbtapi.NbtApiException;
import org.jeffstein.nbtapi.plugin.tests.Test;

public class DirectApplyTest implements Test {

    @Override
    public void test() throws Exception {
        ItemStack baseItem = new ItemStack(Material.STONE);
        NBTItem nbti = new NBTItem(baseItem, true);
        nbti.setString("SomeKey", "SomeValue");
        if (!baseItem.equals(nbti.getItem()) || !new NBTItem(baseItem).hasTag("SomeKey")) {
            throw new NbtApiException("The item's where not equal!");
        }
        baseItem = new ItemStack(Material.STONE);
        String inside = NBT.modify(baseItem, nbt -> {
            nbt.setString("SomeKey", "SomeValue");
            return nbt.getString("SomeKey");
        });
        baseItem = NBT.itemStackFromNBT(NBT.itemStackToNBT(baseItem)); // trick to force the item to be "real", not a
                                                                       // Spigot only item
        String outside = NBT.get(baseItem, nbt -> {
            return nbt.getString("SomeKey");
        });
        if (!new NBTItem(baseItem).hasTag("SomeKey")) {
            throw new NbtApiException("The data was not applied!");
        }
        if (!"SomeValue".equals(inside)) {
            throw new NbtApiException("Inside returned the wrong value!");
        }
        if (!"SomeValue".equals(outside)) {
            throw new NbtApiException("Outside returned the wrong value!");
        }
    }

}
