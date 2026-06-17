package net.asik.mod;

import net.fabricmc.api.ModInitializer;
import net.minecraft.item.Item;
import net.minecraft.item.ToolMaterials;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class Asik implements ModInitializer {
    public static final String MOD_ID = "asik";

    public static final Item LIGHTNING_BOW = new LightningBowItem(new Item.Settings().maxDamage(384));
    public static final Item SIKAXE_1 = new SikaxeItem(ToolMaterials.STONE, 2, new Item.Settings().fireResistant());
    public static final Item SIKAXE_2 = new SikaxeItem(ToolMaterials.DIAMOND, 4, new Item.Settings().fireResistant());
    public static final Item SIKAXE_3 = new SikaxeItem(ToolMaterials.NETHERITE, 5, new Item.Settings().fireResistant());

    @Override
    public void onInitialize() {
        // Register items perfectly
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "lightning_bow"), LIGHTNING_BOW);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "sikaxe_1"), SIKAXE_1);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "sikaxe_2"), SIKAXE_2);
        Registry.register(Registries.ITEM, Identifier.of(MOD_ID, "sikaxe_3"), SIKAXE_3);

        // Run your custom parameter-free registration method
        ModCommands.register();
    }
}