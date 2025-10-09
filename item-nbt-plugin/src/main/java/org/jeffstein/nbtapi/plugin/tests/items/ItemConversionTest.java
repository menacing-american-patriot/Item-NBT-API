package org.jeffstein.nbtapi.plugin.tests.items;

import org.jeffstein.nbtapi.NBT;
import org.jeffstein.nbtapi.NBTType;
import org.jeffstein.nbtapi.iface.ReadWriteNBT;
import org.jeffstein.nbtapi.utils.MinecraftVersion;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import com.google.common.collect.Lists;

import org.jeffstein.nbtapi.NbtApiException;
import org.jeffstein.nbtapi.plugin.tests.Test;

public class ItemConversionTest implements Test {

    @Override
    public void test() throws Exception {
        ItemStack item = new ItemStack(Material.STONE, 1);
        ItemMeta meta = item.getItemMeta();
        meta.setLore(Lists.newArrayList("Firest Line", "Second Line"));
        item.setItemMeta(meta);

        ReadWriteNBT nbt = NBT.itemStackToNBT(item);
        if (MinecraftVersion.isAtLeastVersion(MinecraftVersion.MC1_12_R1)
                && !nbt.hasTag("DataVersion", NBTType.NBTTagInt)) {
            throw new NbtApiException("The item nbt '" + nbt + "' didn't contain the data version");
        }

        String nbtString = nbt.toString();
        if (!nbtString.contains("Firest Line") || !nbtString.contains("Second Line"))
            throw new NbtApiException("The Item nbt '" + nbtString + "' didn't contain the lore");
        ItemStack rebuild = NBT.itemStackFromNBT(NBT.parseNBT(nbtString));
        if (!item.isSimilar(rebuild))
            throw new NbtApiException("Rebuilt item did not match the original!");

        ReadWriteNBT cont = NBT.createNBTObject();
        cont.setItemStack("testItem", item);
        if (!item.isSimilar(cont.getItemStack("testItem")))
            throw new NbtApiException("Rebuilt item did not match the original!");
    }

}
