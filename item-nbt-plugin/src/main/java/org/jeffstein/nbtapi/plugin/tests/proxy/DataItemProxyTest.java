// package org.jeffstein.nbtapi.plugin.tests.proxy;
//
// import org.bukkit.Material;
// import org.bukkit.inventory.ItemStack;
// import org.bukkit.inventory.meta.ItemMeta;
//
// import org.jeffstein.nbtapi.NBT;
// import org.jeffstein.nbtapi.NBTItem;
// import org.jeffstein.nbtapi.NbtApiException;
// import org.jeffstein.nbtapi.data.proxy.NBTItemMeta;
// import org.jeffstein.nbtapi.iface.ReadWriteNBT;
// import org.jeffstein.nbtapi.utils.MinecraftVersion;
// import org.jeffstein.nbtapi.plugin.tests.Test;
//
// public class DataItemProxyTest implements Test {
//
// @Override
// public void test() throws Exception {
// if(MinecraftVersion.isAtLeastVersion(MinecraftVersion.MC1_20_R4)) {
// return; // skip
// }
// ItemStack item = new ItemStack(Material.STONE);
// ItemMeta meta = item.getItemMeta();
// meta.setDisplayName("Test");
// item.setItemMeta(meta);
// NBT.modify(item, NBTItemMeta.class, nmeta -> {
// nmeta.setCustomModelData(123);
// nmeta.setUnbreakable(true);
// if (!nmeta.getDisplayData().getRawName().contains("Test")) {
// throw new NbtApiException("Raw name didn't containg the expected String: " +
// nmeta);
// }
// ReadWriteNBT container = NBT.createNBTObject();
// container.setString("foo", "bar");
// nmeta.setBlockStateTag(container);
// if (!container.equals(nmeta.getBlockStateTag())) {
// throw new NbtApiException("BlockStateTag did not match! " + nmeta);
// }
// });
//
// if (MinecraftVersion.isAtLeastVersion(MinecraftVersion.MC1_16_R1)) {
// meta = item.getItemMeta();
// if (!meta.hasCustomModelData() || meta.getCustomModelData() != 123) {
// throw new NbtApiException("Custom Model Data did not match! " + new
// NBTItem(item));
// }
// if (!meta.isUnbreakable()) {
// throw new NbtApiException("Unbreakable did not set!");
// }
// }
// }
//
// }
