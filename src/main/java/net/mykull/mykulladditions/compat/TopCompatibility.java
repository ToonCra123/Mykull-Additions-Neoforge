package net.mykull.mykulladditions.compat;

import net.mykull.mykulladditions.MykullsAdditions;
import mcjty.theoneprobe.api.*;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.neoforged.fml.InterModComms;
import net.neoforged.fml.ModList;

import javax.annotation.Nullable;
import java.util.function.Function;

public class TopCompatibility {

    public static void register() {
        if (!ModList.get().isLoaded("theoneprobe")) {
            return;
        }
        InterModComms.sendTo("theoneprobe", "getTheOneProbe", GetTheOneProbe::new);
    }


    public static class GetTheOneProbe implements Function<ITheOneProbe, Void> {

        public static ITheOneProbe probe;

        @Nullable
        @Override
        public Void apply(ITheOneProbe theOneProbe) {
            probe = theOneProbe;
            MykullsAdditions.LOGGER.info("Enabled support for The One Probe");
            probe.registerProvider(new IProbeInfoProvider() {
                @Override
                public ResourceLocation getID() {
                    return ResourceLocation.tryParse("mykulladditions:default");
                }

                @Override
                public void addProbeInfo(ProbeMode mode, IProbeInfo probeInfo, Player player, Level world, BlockState blockState, IProbeHitData data) {
                    // Do Something eventually maybe
                }
            });
            return null;
        }
    }
}