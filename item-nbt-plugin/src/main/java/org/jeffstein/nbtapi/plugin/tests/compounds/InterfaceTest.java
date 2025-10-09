package org.jeffstein.nbtapi.plugin.tests.compounds;

import org.jeffstein.nbtapi.NBT;
import org.jeffstein.nbtapi.NbtApiException;
import org.jeffstein.nbtapi.iface.ReadWriteNBT;
import org.jeffstein.nbtapi.plugin.tests.Test;

public class InterfaceTest implements Test {

    @Override
    public void test() throws Exception {
        ReadWriteNBT src = NBT.createNBTObject();
        src.setString("foo", "bar");
        ReadWriteNBT target = NBT.createNBTObject();
        target.mergeCompound(src);
        if (!"bar".equals(target.getString("foo"))) {
            throw new NbtApiException("Wasn't able to check the key! The Item-NBT-API may not work!");
        }
    }

}
