package net.tslat.aoa3.content.item.misc;

import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.animal.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.data.server.AoAHaulingFishReloadListener;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.RandomUtil;
import net.tslat.aoa3.util.WorldUtil;

import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

public class ChumItem extends Item {
	public ChumItem() {
		super(new Item.Properties()
				.tab(AoACreativeModeTabs.FOOD)
				.food(new FoodProperties.Builder()
						.nutrition(1)
						.saturationMod(0.1f)
						.alwaysEat()
						.effect(() -> new MobEffectInstance(MobEffects.CONFUSION, 60), 1)
						.effect(() -> new MobEffectInstance(MobEffects.POISON, 60), 1).build()));
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player pPlayer, InteractionHand hand) {
		pPlayer.startUsingItem(hand);

		return InteractionResultHolder.consume(pPlayer.getItemInHand(hand));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return super.getUseDuration(stack) + 3;
	}

	@Override
	public void releaseUsing(ItemStack stack, Level level, LivingEntity user, int timeLeft) {
		if (!level.isClientSide() && getUseDuration(stack) - timeLeft < 5) {
			if (!(user instanceof Player) || !((Player)user).isCreative())
				stack.shrink(1);

			Vec3 velocityVector = EntityUtil.getVelocityVectorForFacing(user).multiply(1, 1.5, 1);
			ArrayList<BlockPos> positions = WorldUtil.getBlocksWithinAABB(level, user.getBoundingBox().move(velocityVector.x() * 3, velocityVector.y() * 3, velocityVector.z() * 3), (blockState, mutable) -> blockState.getFluidState().is(FluidTags.WATER) && blockState.getFluidState().getHeight(level, mutable) > 0.85f);

			if (!positions.isEmpty() && RandomUtil.oneInNChance(Math.max(100 / positions.size(), 1))) {
				BlockPos pos = RandomUtil.getRandomSelection(positions);
				Entity fish = getFishEntity(user, level, pos);

				fish.setPos(pos.getX(), pos.getY(), pos.getZ());
				level.addFreshEntity(fish);
			}

			ServerParticlePacket packet = new ServerParticlePacket();
			ItemParticleOption particleData = new ItemParticleOption(AoAParticleTypes.FLOATING_ITEM_FRAGMENT.get(), stack);

			for (float i = -0.15f; i <= 0.15f; i += 0.05f) {
				packet.particle(particleData, user.getX(), user.getY() + user.getEyeHeight(), user.getZ(), velocityVector.x() + (i * 2 * velocityVector.z()), velocityVector.y(), velocityVector.z() + (i * 2 * velocityVector.x()));
			}

			AoAPackets.messageNearbyPlayers(packet, (ServerLevel)level, user.position(), 32);
		}
	}

	private Entity getFishEntity(LivingEntity user, Level world, BlockPos pos) {
		if (user instanceof ServerPlayer player) {
			Entity entity = AoAHaulingFishReloadListener.getFishListForBiome(user.level.getBiome(pos).value(), false).getRandomElement(player, player.getLuck()).apply(user.level);

			if (!(entity instanceof ItemEntity))
				return entity;
		}

		int selection = RandomUtil.randomNumberUpTo(66);

		if (selection == 0)
			return new Dolphin(EntityType.DOLPHIN, world);

		if (selection <= 5)
			return new Squid(EntityType.SQUID, world);

		if (selection <= 15)
			return new Pufferfish(EntityType.PUFFERFISH, world);

		if (selection <= 30)
			return new Cod(EntityType.COD, world);

		if (selection <= 45)
			return new Salmon(EntityType.SALMON, world);

		return new TropicalFish(EntityType.TROPICAL_FISH, world);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level pLevel, List<Component> tooltip, TooltipFlag flags) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 1));
	}
}
