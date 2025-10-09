package org.jeffstein.nbtapi.utils;

import org.jeffstein.nbtapi.NbtApiException;

public class CheckUtil {

    private CheckUtil() {
        // util
    }

    public static void assertAvailable(MinecraftVersion version) {
        if (!MinecraftVersion.isAtLeastVersion(version))
            throw new NbtApiException(
                    "This Method is only avaliable for the version " + version.name() + " and above!");
    }

}
