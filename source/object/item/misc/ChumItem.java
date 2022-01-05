package net.tslat.aoa3.object.item.misc;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.DolphinEntity;
import net.minecraft.entity.passive.SquidEntity;
import net.minecraft.entity.passive.fish.CodEntity;
import net.minecraft.entity.passive.fish.PufferfishEntity;
import net.minecraft.entity.passive.fish.SalmonEntity;
import net.minecraft.entity.passive.fish.TropicalFishEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Food;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particles.ItemParticleData;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.registration.AoAItemGroups;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
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
				.tab(AoAItemGroups.FOOD)
				.food(new Food.Builder()
						.nutrition(1)
						.saturationMod(0.1f)
						.alwaysEat()
						.effect(() -> new EffectInstance(Effects.CONFUSION, 60), 1)
						.effect(() -> new EffectInstance(Effects.POISON, 60), 1).build()));
	}

	@Override
	public ActionResult<ItemStack> use(World level, PlayerEntity pPlayer, Hand hand) {
		pPlayer.startUsingItem(hand);

		return ActionResult.consume(pPlayer.getItemInHand(hand));
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return super.getUseDuration(stack) + 3;
	}

	@Override
	public void releaseUsing(ItemStack stack, World level, LivingEntity user, int timeLeft) {
		if (!level.isClientSide() && getUseDuration(stack) - timeLeft < 5) {
			if (!(user instanceof PlayerEntity) || !((PlayerEntity)user).isCreative())
				stack.shrink(1);

			Vector3d velocityVector = EntityUtil.getVelocityVectorForFacing(user).multiply(1, 1.5, 1);
			ArrayList<BlockPos> positions = WorldUtil.getBlocksWithinAABB(level, user.getBoundingBox().move(velocityVector.x() * 3, velocityVector.y() * 3, velocityVector.z() * 3), (blockState, mutable) -> blockState.getFluidState().getHeight(level, mutable) > 0.85f);

			if (!positions.isEmpty() && RandomUtil.oneInNChance(Math.max(100 / positions.size(), 1))) {
				Entity fish = getFishEntity(level);
				BlockPos pos = RandomUtil.getRandomSelection(positions);

				fish.setPos(pos.getX(), pos.getY(), pos.getZ());
				level.addFreshEntity(fish);
			}

			ServerParticlePacket packet = new ServerParticlePacket();
			ItemParticleData particleData = new ItemParticleData(AoAParticleTypes.FLOATING_ITEM_FRAGMENT.get(), stack);

			for (float i = -0.15f; i <= 0.15f; i += 0.05f) {
				packet.particle(particleData, user.getX(), user.getY() + user.getEyeHeight(), user.getZ(), velocityVector.x() + (i * 2 * velocityVector.z()), velocityVector.y(), velocityVector.z() + (i * 2 * velocityVector.x()));
			}

			AoAPackets.messageNearbyPlayers(packet, (ServerWorld)level, user.position(), packet.isLongRange() ? 512 : 32);
		}
	}

	private Entity getFishEntity(World world) {
		int selection = RandomUtil.randomNumberUpTo(66);

		if (selection == 0)
			return new DolphinEntity(EntityType.DOLPHIN, world);

		if (selection <= 5)
			return new SquidEntity(EntityType.SQUID, world);

		if (selection <= 15)
			return new PufferfishEntity(EntityType.PUFFERFISH, world);

		if (selection <= 30)
			return new CodEntity(EntityType.COD, world);

		if (selection <= 45)
			return new SalmonEntity(EntityType.SALMON, world);

		return new TropicalFishEntity(EntityType.TROPICAL_FISH, world);
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World pLevel, List<ITextComponent> tooltip, ITooltipFlag flags) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.GENERAL_INFO, 1));
	}
}
