package net.asik.mod;

import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ModCommands {
    public static void register() {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("asik")
                .requires(source -> source.hasPermissionLevel(2)) // Needs OP/cheats enabled
                .then(CommandManager.literal("bow")
                    .executes(context -> giveItem(context.getSource(), Asik.LIGHTNING_BOW, "Lightning Bow")))
                .then(CommandManager.literal("pickaxe1")
                    .executes(context -> giveItem(context.getSource(), Asik.SIKAXE_1, "Sikaxe 1")))
                .then(CommandManager.literal("pickaxe2")
                    .executes(context -> giveItem(context.getSource(), Asik.SIKAXE_2, "Sikaxe 2")))
                .then(CommandManager.literal("pickaxe3")
                    .executes(context -> giveItem(context.getSource(), Asik.SIKAXE_3, "Sikaxe 3")))
            );
        });
    }

    private static int giveItem(ServerCommandSource source, Item item, String itemName) {
        try {
            PlayerEntity player = source.getPlayerOrThrow();
            ItemStack stack = new ItemStack(item);
            
            if (!player.getInventory().insertStack(stack)) {
                player.dropItem(stack, false);
            }
            
            // In 1.21.1, sendFeedback takes a Supplier<Text> lambda expression
            source.sendFeedback(() -> Text.literal("§a[AsikMod] Gave you " + itemName + "!"), false);
            return 1;
        } catch (Exception e) {
            source.sendError(Text.literal("Only players can use this command!"));
            return 0;
        }
    }
}