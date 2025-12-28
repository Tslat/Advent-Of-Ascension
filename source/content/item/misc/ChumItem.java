package net.tslat.aoa3.content.item.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.NeoForgeMod;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.item.AoAFood;
import net.tslat.aoa3.content.skill.hauling.HaulingSpawnPool;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;
import net.tslat.tme.api.util.RandomUtil;

import java.util.List;
import java.util.Optional;

public class ChumItem extends Item {
	public ChumItem() {
		super(new Item.Properties()
				.food(AoAFood.CHUM));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player pPlayer, InteractionHand hand) {
		pPlayer.startUsingItem(hand);

		return InteractionResultHolder.consume(pPlayer.getItemInHand(hand));
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity user) {
		return super.getUseDuration(stack, user) + 3;
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity user, int timeLeft) {
		if (level instanceof ServerLevel serverLevel && getUseDuration(stack, user) - timeLeft < 5) {
			Vec3 velocityVector = EntityUtil.getVelocityVectorForFacing(user).multiply(1, 1.5, 1);
			List<BlockPos> positions = WorldUtil.getBlocksWithinAABB(serverLevel, user.getBoundingBox().move(velocityVector.x() * 3, velocityVector.y() * 3, velocityVector.z() * 3), (blockState, mutable) -> blockState.getFluidState().is(FluidTags.WATER) && blockState.getFluidState().getHeight(level, mutable) > 0.85f);

			if (!positions.isEmpty() && RandomUtil.oneInNChance(Math.max(100 / positions.size(), 1))) {
				BlockPos pos = RandomUtil.selection(positions);
				Entity fish = getFishEntity(user, serverLevel, pos);

				fish.setPos(pos.getX(), pos.getY(), pos.getZ());
				serverLevel.addFreshEntity(fish);
			}

			TMEParticlePacket packet = new TMEParticlePacket();
			ItemParticleOption particleData = new ItemParticleOption(AoAParticleTypes.FLOATING_ITEM_FRAGMENT.get(), stack);

			for (float i = -0.15f; i <= 0.15f; i += 0.05f) {
				packet.particle(ParticleBuilder.forPosition(particleData, user.getX(), user.getY() + user.getEyeHeight(), user.getZ()).velocity(velocityVector.x() + (i * 2 * velocityVector.z()), velocityVector.y(), velocityVector.z() + (i * 2 * velocityVector.x())));
			}

			packet.sendToAllPlayersNearby(serverLevel, user.position(), 32);

			if (!(user instanceof Player player) || !player.getAbilities().instabuild)
				stack.shrink(1);
		}
	}

	private Entity getFishEntity(LivingEntity user, Level level, BlockPos pos) {
		if (user instanceof ServerPlayer player) {
			Optional<Entity> fish = HaulingSpawnPool.getPoolForLocation(level, pos, NeoForgeMod.WATER_TYPE.value())
					.map(pool -> pool.getEntry(player, player.getLuck()))
					.filter(Optional::isPresent)
					.map(Optional::get)
					.filter(haulingEntity -> haulingEntity.object().right().isPresent())
					.map(haulingEntity -> haulingEntity.apply(level, false));

			if (fish.isPresent())
				return fish.get();
		}

		int selection = RandomUtil.numberUpTo(66);

		if (selection == 0)
			return new Dolphin(EntityType.DOLPHIN, level);

		if (selection <= 5)
			return new Squid(EntityType.SQUID, level);

		if (selection <= 15)
			return new Pufferfish(EntityType.PUFFERFISH, level);

		if (selection <= 30)
			return new Cod(EntityType.COD, level);

		if (selection <= 45)
			return new Salmon(EntityType.SALMON, level);

		return new TropicalFish(EntityType.TROPICAL_FISH, level);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flags) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
