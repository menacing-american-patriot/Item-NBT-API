package org.jeffstein.nbtapi.plugin.tests.compounds;

import org.jeffstein.nbtapi.NBT;
import org.jeffstein.nbtapi.NBTType;
import org.jeffstein.nbtapi.NbtApiException;
import org.jeffstein.nbtapi.iface.ReadWriteNBT;
import org.jeffstein.nbtapi.plugin.tests.Test;

public class TypeTest implements Test {

    @Override
    public void test() throws Exception {
        ReadWriteNBT comp = NBT.createNBTObject();
        comp.setString("s", "test");
        comp.setInteger("i", 42);
        comp.getOrCreateCompound("c");
        if (comp.getType("s") != NBTType.NBTTagString || comp.getType("i") != NBTType.NBTTagInt
                || comp.getType("c") != NBTType.NBTTagCompound)
            throw new NbtApiException("One parsed type did not match what it should have been!");
    }

}
