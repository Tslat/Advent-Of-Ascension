package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.core.BlockPos;
import net.minecraft.core.GlobalPos;
import net.minecraft.core.Holder;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.item.misc.TooltipItem;
import net.tslat.aoa3.content.world.teleporter.AoAPortal;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;

public abstract class BossSpawningItem<T extends Entity> extends TooltipItem implements BossTokenItem {
	public BossSpawningItem() {
		this(2, new Item.Properties().rarity(Rarity.UNCOMMON));
	}

	public BossSpawningItem(int tooltipLines, Properties itemProperties) {
		super(tooltipLines, itemProperties);
	}

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.BOW;
	}

	@Override
	public int getUseDuration(ItemStack stack, LivingEntity user) {
		return 60;
	}

	@Override
	public boolean isValidRepairItem(ItemStack stack, ItemStack repairCandidate) {
		return false;
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return false;
	}

	@Override
	public boolean isBookEnchantable(ItemStack stack, ItemStack book) {
		return false;
	}

	@Override
	public boolean isPrimaryItemFor(ItemStack stack, Holder<Enchantment> enchantment) {
		return false;
	}

	@Override
	public boolean canGrindstoneRepair(ItemStack stack) {
		return false;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		if (WorldUtil.isWorld(level, AoADimensions.NOWHERE))
			return InteractionResultHolder.pass(player.getItemInHand(hand));

		return ItemUtils.startUsingInstantly(level, player, hand);
	}

	@Override
	public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
		if (level instanceof ServerLevel serverLevel) {
			TMEParticlePacket packet = new TMEParticlePacket();

			for (int i = 0; i < 3; i++) {
				float colorMod = level.random.nextFloat() * 0.7f + 0.3f;

				packet.particle(ParticleBuilder.forRandomPosInEntity(AoAParticleTypes.GENERIC_DUST.get(), livingEntity)
						.colourTint(colorMod * 209 / 255f, colorMod * 177 / 255f, 0, 1f));
			}

			packet.sendToAllPlayersNearby(serverLevel, livingEntity.position(), 20);
		}
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (WorldUtil.isWorld(level, AoADimensions.NOWHERE) || !(level instanceof ServerLevel serverLevel) || !(entity instanceof ServerPlayer pl))
			return stack;

		ServerLevel nowhere = serverLevel.getServer().getLevel(AoADimensions.NOWHERE);

		if (nowhere == null)
			return stack;

		BlockPos fromPos = entity.blockPosition();

		AoAScheduler.schedule(1, tick -> {
			if (AdvancementUtil.isAdvancementCompleted(pl, AdventOfAscension.id("nowhere/root"))) {
				ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
				GlobalPos returnLoc = plData.storage.getPortalReturnFor(nowhere.dimension());

				pl.changeDimension(AoAPortal.getTransitionForLevel(nowhere, pl, AoABlocks.NOWHERE_PORTAL.get()));
				pl.connection.teleport(17.5d, 502.5d, 3.5d, 0, pl.getXRot());

				plData.storage.setPortalReturnLocation(nowhere.dimension(), returnLoc == null ? new GlobalPos(level.dimension(), fromPos) : returnLoc);
			}
			else {
				PlayerUtil.getAdventPlayer(pl).storage.setPortalReturnLocation(nowhere.dimension(), pl.level().dimension(), pl.blockPosition());
				pl.changeDimension(AoAPortal.getTransitionForLevel(nowhere, pl, AoABlocks.NOWHERE_PORTAL.get()));
				pl.connection.teleport(17.5d, 452.5d, 3.5d, 0, pl.getXRot());
			}
		});

		return stack;
	}
}
