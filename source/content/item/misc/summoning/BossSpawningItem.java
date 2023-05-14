package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.advent.AdventOfAscension;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.block.functional.portal.PortalBlock;
import net.tslat.aoa3.content.item.misc.TooltipItem;
import net.tslat.aoa3.content.world.teleporter.PortalCoordinatesContainer;
import net.tslat.aoa3.library.builder.ParticleBuilder;
import net.tslat.aoa3.player.ServerPlayerDataManager;
import net.tslat.aoa3.scheduling.AoAScheduler;
import net.tslat.aoa3.util.AdvancementUtil;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.aoa3.util.WorldUtil;

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
	public int getUseDuration(ItemStack stack) {
		return 60;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		if (WorldUtil.isWorld(level, AoADimensions.NOWHERE.key))
			return InteractionResultHolder.pass(player.getItemInHand(hand));

		return ItemUtils.startUsingInstantly(level, player, hand);
	}

	@Override
	public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
		if (!level.isClientSide()) {
			ServerParticlePacket packet = new ServerParticlePacket(3);

			for (int i = 0; i < 3; i++) {
				packet.particle(ParticleBuilder.forRandomPosInEntity(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 0.5f, 1, 0xD1B100), livingEntity));
			}

			AoAPackets.messageNearbyPlayers(packet, (ServerLevel)level, livingEntity.position(), 20);
		}
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (WorldUtil.isWorld(level, AoADimensions.NOWHERE.key) || !(level instanceof ServerLevel serverLevel) || !(entity instanceof ServerPlayer pl))
			return stack;

		ServerLevel nowhere = serverLevel.getServer().getLevel(AoADimensions.NOWHERE.key);

		if (nowhere == null)
			return stack;

		AoAScheduler.scheduleSyncronisedTask(() -> {
			if (AdvancementUtil.isAdvancementCompleted(pl, AdventOfAscension.id("nowhere/root"))) {
				ServerPlayerDataManager plData = PlayerUtil.getAdventPlayer(pl);
				PortalCoordinatesContainer returnLoc = plData.getPortalReturnLocation(nowhere.dimension());

				pl.changeDimension(nowhere, PortalBlock.getTeleporterForWorld(nowhere));
				pl.connection.teleport(17.5d, 502.5d, 3.5d, 0, pl.getXRot());

				if (returnLoc != null)
					plData.setPortalReturnLocation(nowhere.dimension(), returnLoc);
			}
			else {
				PlayerUtil.getAdventPlayer(pl).setPortalReturnLocation(nowhere.dimension(), new PortalCoordinatesContainer(level.dimension(), pl.getX(), pl.getY(), pl.getZ()));
				pl.changeDimension(nowhere, PortalBlock.getTeleporterForWorld(nowhere));
				pl.connection.teleport(17.5d, 452.5d, 3.5d, 0, pl.getXRot());
			}
		}, 1);

		return stack;
	}
}
