package com.example.examplemod;

import net.minecraft.world.entity.player.Player;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import java.util.UUID;

import static com.example.examplemod.CommonClass.*;

@Mod(Constants.MOD_ID)
@Mod.EventBusSubscriber
public class ExampleMod {
    public ExampleMod() {
        CommonClass.init();
    }

    @SubscribeEvent
    public static void onPlayerLogin(PlayerEvent.PlayerLoggedInEvent event) {
        if (!event.getEntity().level().isClientSide()) {
            Player player = event.getEntity();
            UUID uuid = player.getUUID();
            commonOnLogin(uuid);
        }
    }

    @SubscribeEvent
    public static void onPlayerLogout(PlayerEvent.PlayerLoggedOutEvent event) {
        if (!event.getEntity().level().isClientSide()) {
            Player player = event.getEntity();
            UUID uuid = player.getUUID();
            commonOnLogout(uuid);
        }
    }
}