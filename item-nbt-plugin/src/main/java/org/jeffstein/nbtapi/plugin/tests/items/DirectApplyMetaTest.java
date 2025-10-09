package org.jeffstein.nbtapi.plugin.tests.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import org.jeffstein.nbtapi.NBT;
import org.jeffstein.nbtapi.NBTItem;
import org.jeffstein.nbtapi.NbtApiException;
import org.jeffstein.nbtapi.iface.ReadableNBT;
import org.jeffstein.nbtapi.utils.MinecraftVersion;
import org.jeffstein.nbtapi.plugin.tests.Test;

public class DirectApplyMetaTest implements Test {

    @Override
    public void test() throws Exception {
        if (MinecraftVersion.isAtLeastVersion(MinecraftVersion.MC1_20_R4)) {
            return; // skip
        }
        ItemStack baseItem = new ItemStack(Material.STONE);
        NBTItem nbti = new NBTItem(baseItem, true);
        nbti.setString("SomeKey", "SomeValue");
        nbti.modifyMeta(this::modifyMeta);

        if (!new NBTItem(baseItem).hasTag("SomeKey") || !"SomeValue".equals(baseItem.getItemMeta().getDisplayName())) {
            throw new NbtApiException("The item was not modified correctly! " + NBT.itemStackToNBT(baseItem));
        }

    }

    private void modifyMeta(ReadableNBT nbt, ItemMeta meta) {
        meta.setDisplayName(nbt.getString("SomeKey"));
    }

}
