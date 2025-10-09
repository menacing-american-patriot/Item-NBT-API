package org.jeffstein.nbtapi.plugin.tests.compounds;

import org.jeffstein.nbtapi.NBT;
import org.jeffstein.nbtapi.NBTType;
import org.jeffstein.nbtapi.NbtApiException;
import org.jeffstein.nbtapi.iface.ReadWriteNBT;
import org.jeffstein.nbtapi.plugin.tests.Test;

public class EnumTest implements Test {

    @Override
    public void test() throws Exception {
        ReadWriteNBT comp = NBT.createNBTObject();
        comp.setEnum("test", NBTType.NBTTagEnd);
        NBTType type = comp.getEnum("test", NBTType.class);
        NBTType typeNonNull = comp.getOrNull("test", NBTType.class);
        NBTType typeDefault = comp.getOrDefault("invalid", NBTType.NBTTagByte);
        NBTType typeDefaultFound = comp.getOrDefault("test", NBTType.NBTTagByte);
        if (type != NBTType.NBTTagEnd || typeNonNull != NBTType.NBTTagEnd || typeDefaultFound != NBTType.NBTTagEnd
                || typeDefault != NBTType.NBTTagByte)
            throw new NbtApiException("One enum did not match what it should have been!");
    }

}
