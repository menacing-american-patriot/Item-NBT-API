package org.jeffstein.nbtapi.plugin.tests.items;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;

import org.jeffstein.nbtapi.NBT;
import org.jeffstein.nbtapi.NbtApiException;
import org.jeffstein.nbtapi.utils.MinecraftVersion;
import org.jeffstein.nbtapi.plugin.tests.Test;

public class MetaTest implements Test {

    @Override
    public void test() throws Exception {
        if (MinecraftVersion.isAtLeastVersion(MinecraftVersion.MC1_20_R4)) {
            return; // skip
        }
        ItemStack item = new ItemStack(Material.STONE);
        NBT.modify(item, nbt -> {
            nbt.setInteger("HideFlags", 1);
            nbt.modifyMeta((rnbt, meta) -> {
                if (!meta.hasItemFlag(ItemFlag.HIDE_ENCHANTS) || rnbt.getInteger("HideFlags") != 1) {
                    throw new NbtApiException("The meta did not correctly update or read! " + rnbt);
                }
            });
        });

    }

}
