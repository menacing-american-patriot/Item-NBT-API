package org.jeffstein.nbtapi.plugin.tests.compounds;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import org.jeffstein.nbtapi.NBT;
import org.jeffstein.nbtapi.NbtApiException;
import org.jeffstein.nbtapi.iface.ReadWriteNBT;
import org.jeffstein.nbtapi.plugin.tests.Test;

public class StreamTest implements Test {

    @Override
    public void test() throws Exception {
        ReadWriteNBT base = NBT.createNBTObject();
        base.getOrCreateCompound("sub").setString("hello", "world");
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        base.getOrCreateCompound("sub").writeCompound(outStream);
        byte[] data = outStream.toByteArray();
        ByteArrayInputStream inputStream = new ByteArrayInputStream(data);
        ReadWriteNBT container = NBT.readNBT(inputStream);
        if (!container.toString().equals(base.getOrCreateCompound("sub").toString())) {
            throw new NbtApiException("Component content did not match! " + base.getCompound("sub") + " " + container);
        }
        ItemStack item = new ItemStack(Material.STICK);

        NBT.modify(item, nbt -> {
            nbt.writeCompound(outStream);
        });
    }

}
