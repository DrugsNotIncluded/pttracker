package com.example.examplemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.networking.v1.ServerPlayConnectionEvents;

import static com.example.examplemod.CommonClass.commonOnLogin;
import static com.example.examplemod.CommonClass.commonOnLogout;

public class ExampleMod implements ModInitializer {
    
    @Override
    public void onInitialize() {
        CommonClass.init();
        ServerPlayConnectionEvents.JOIN.register((handler, sender, server) -> {
            var player = handler.player;
            var uuid = player.getUUID();
            commonOnLogin(uuid);
        });

        ServerPlayConnectionEvents.DISCONNECT.register((handler, sender) -> {
            var player = handler.player;
            var uuid = player.getUUID();
            commonOnLogout(uuid);
        });
    }
}
