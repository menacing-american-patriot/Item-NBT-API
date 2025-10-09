package org.jeffstein.nbtapi.plugin.tests.proxy;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import org.jeffstein.nbtapi.NBT;
import org.jeffstein.nbtapi.NBTItem;
import org.jeffstein.nbtapi.NbtApiException;
import org.jeffstein.nbtapi.handler.NBTHandlers;
import org.jeffstein.nbtapi.wrapper.NBTProxy;
import org.jeffstein.nbtapi.wrapper.NBTTarget;
import org.jeffstein.nbtapi.wrapper.ProxyList;
import org.jeffstein.nbtapi.wrapper.NBTTarget.Type;
import org.jeffstein.nbtapi.plugin.tests.Test;

public class SimpleProxyTest implements Test {

    @Override
    public void test() throws Exception {
        ItemStack item = new ItemStack(Material.STONE);
        NBT.modify(item, TestInterface.class, ti -> {
            if (ti.hasKills()) {
                throw new NbtApiException("Item reported to have kills before setting data!");
            }
            ti.setKills(42);
            ti.addKill();
            if (!ti.hasKills()) {
                throw new NbtApiException("Item reported to not have kills after setting data!");
            }
            if (!"{Kills:43}".equals(ti.toString())) {
                throw new NbtApiException("ToString returned the wrong string. " + ti.toString());
            }
        });
        if (new NBTItem(item).getInteger("Kills") != 43) {
            throw new NbtApiException("The item was not modified correctly by the proxy!");
        }
        NBT.modify(item, TestInterface.class, ti -> {
            if (ti.theKillsWithADifferentMethodNameAndNoGet() != 43) {
                throw new NbtApiException("The annotation didn't work correctly!");
            }
            Statistic jumps = ti.getJumps();
            jumps.setPoints(9000);
            jumps.addPoint();
            if (ti.getJumps().getPoints() != 9001) {
                throw new NbtApiException("The stacked proxy didn't work correctly!");
            }
            ItemStack stack = new ItemStack(Material.STONE, 42);
            ti.setItem(stack);
            if (!stack.equals(ti.getItem())) {
                throw new NbtApiException("The handler in the proxy didn't work correctly!");
            }
        });
        NBT.modify(item, TestInterface.class, ti -> {
            Statistic a = ti.getStatistics().addCompound();
            a.setPoints(1);
            a.addPoint();
            a.addPoint();
            ti.getStatistics().addCompound();
            if (ti.getStatistics().size() != 2) {
                throw new NbtApiException("List size not as expected!");
            }
            ti.getStatistics().remove(1);
            if (ti.getStatistics().size() != 1) {
                throw new NbtApiException("List size not as expected!");
            }
            if (ti.getStatistics().iterator().next().getPoints() != 3) {
                throw new NbtApiException("Points not as expected!");
            }
        });
    }

    public interface TestInterface extends NBTProxy {

        @Override
        default void init() {
            registerHandler(ItemStack.class, NBTHandlers.ITEM_STACK);
        }

        public boolean hasKills();

        public void setKills(int amount);

        public int getKills();

        public default void addKill() {
            setKills(getKills() + 1);
        }

        @NBTTarget(value = "Kills", type = Type.GET)
        public int theKillsWithADifferentMethodNameAndNoGet();

        public Statistic getJumps();

        public ItemStack getItem();

        public void setItem(ItemStack item);

        public ProxyList<Statistic> getStatistics();

    }

    public interface Statistic extends NBTProxy {
        public void setPoints(int amount);

        public int getPoints();

        public default void addPoint() {
            setPoints(getPoints() + 1);
        }

    }

}
