package com.example;

import net.minecraft.block.BlockState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SikaxeItem extends PickaxeItem {
    public SikaxeItem(ToolMaterial material, int attackDamage, Settings settings) {
        super(material, attackDamage, -2.8f, settings);
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!world.isClient) {
            int radius = 0;
            if (this.getMaterial() == ToolMaterials.STONE) radius = 1;      // 2x2 logic (1 block extra)
            else if (this.getMaterial() == ToolMaterials.DIAMOND) radius = 2; // 4x4 logic (approx)
            else if (this.getMaterial() == ToolMaterials.NETHERITE) radius = 3; // 5x5 logic (2 blocks extra)

            if (radius > 0) {
                for (int x = -radius; x <= radius; x++) {
                    for (int y = -radius; y <= radius; y++) {
                        for (int z = -radius; z <= radius; z++) {
                            BlockPos target = pos.add(x, y, z);
                            if (world.getBlockState(target).getHardness(world, target) >= 0) {
                                world.breakBlock(target, true, miner);
                            }
                        }
                    }
                }
            }
        }
        return super.postMine(stack, world, state, pos, miner);
    }
}
