package org.jeffstein.nbtapi.data.proxy;

import org.jeffstein.nbtapi.handler.NBTHandlers;
import org.jeffstein.nbtapi.iface.ReadWriteNBT;
import org.jeffstein.nbtapi.iface.ReadableNBT;
import org.jeffstein.nbtapi.wrapper.NBTProxy;
import org.jeffstein.nbtapi.wrapper.NBTTarget;
import org.jeffstein.nbtapi.wrapper.NBTTarget.Type;

public interface NBTItemMeta extends NBTProxy {

    @Override
    default void init() {
        registerHandler(ReadableNBT.class, NBTHandlers.STORE_READABLE_TAG);
        registerHandler(ReadWriteNBT.class, NBTHandlers.STORE_READWRITE_TAG);
    }

    public int getCustomModelData();

    public void setCustomModelData(int customModelData);

    @NBTTarget(type = Type.GET, value = "Unbreakable")
    public boolean isUnbreakable();

    public void setUnbreakable(boolean unbreakable);

    public ReadWriteNBT getBlockStateTag();

    public void setBlockStateTag(ReadableNBT blockState);

    @NBTTarget(value = "display")
    public DisplayData getDisplayData();

    public interface DisplayData extends NBTProxy {

        @NBTTarget(value = "Name")
        public void setRawName(String rawName);

        @NBTTarget(value = "Name")
        public String getRawName();

    }

}
