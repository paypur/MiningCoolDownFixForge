package me.paypur.mcdf.config;

import net.minecraftforge.common.ForgeConfigSpec;

public class MCDFCommonConfigs {
    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;
    public static final ForgeConfigSpec.IntValue MINING_DELAY;

    static {
        BUILDER.push("Mining Cool Down Fix [Forge] common config");
        BUILDER.pop();

        MINING_DELAY = BUILDER.comment("Minimum delay between mining blocks in ticks").defineInRange("mining_delay", 0, 0, Integer.MAX_VALUE);

        SPEC = BUILDER.build();
    }
}
