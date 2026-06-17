package com.example;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.LightningEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BowItem;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.Vec3d;

public class LightningBowItem extends BowItem {
    public LightningBowItem(Settings settings) {
        super(settings);
    }

    @Override
    public void onStoppedUsing(ItemStack stack, World world, LivingEntity user, int remainingUseTicks) {
        super.onStoppedUsing(stack, world, user, remainingUseTicks);
        
        if (user instanceof PlayerEntity) {
            // Check where the player is looking
            HitResult hit = user.raycast(100.0, 0.0F, false);
            if (hit.getType() == HitResult.Type.BLOCK || hit.getType() == HitResult.Type.ENTITY) {
                Vec3d pos = hit.getPos();
                LightningEntity lightning = EntityType.LIGHTNING_BOLT.create(world);
                if (lightning != null) {
                    lightning.refreshPositionAfterTeleport(pos.x, pos.y, pos.z);
                    world.spawnEntity(lightning);
                }
            }
        }
    }
}
