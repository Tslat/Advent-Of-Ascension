package net.tslat.aoa3.content.item.weapon.bow;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.api.util.RandomUtil;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SkydriverBow extends AoABow {
	public SkydriverBow(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void tickArrow(Projectile projectile, @Nullable Entity shooter, ItemStack stack) {
		Level level = projectile.level();

		if (!level.isClientSide && projectile instanceof AbstractArrow arrow && !arrow.inGround && arrow.tickCount > 1) {

			BlockPos.MutableBlockPos testPos = arrow.blockPosition().mutable();

			while (testPos.move(Direction.DOWN).getY() >= level.getMinBuildHeight() && level.isEmptyBlock(testPos)) {
				;
			}

			ParticleBuilder.forRandomPosInEntity(ParticleTypes.SPIT, arrow)
					.colourTint(0xA53A00)
					.velocity(0, RandomUtil.valueBetween(-0.3f, -0.5f), 0)
					.spawnNTimes(3)
					.lifespan(arrow.blockPosition().getY() - testPos.getY() * 4)
					.sendToAllPlayersTrackingEntity(arrow);

			if (level.getBlockState(testPos).isFaceSturdy(level, testPos, Direction.UP) && level.getBlockState(testPos.above()).canBeReplaced() && WorldUtil.canPlaceBlock(level, testPos.above(), shooter, null))
				level.setBlockAndUpdate(testPos.above(), AoABlocks.ORANGE_ACID.get().defaultBlockState());
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
