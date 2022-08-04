package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.*;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.packet.AoAPackets;
import net.tslat.aoa3.common.packet.packets.ServerParticlePacket;
import net.tslat.aoa3.common.particletype.CustomisableParticleType;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.aoa3.common.registration.AoAParticleTypes;
import net.tslat.aoa3.common.registration.block.AoABlocks;
import net.tslat.aoa3.common.registration.worldgen.AoADimensions;
import net.tslat.aoa3.content.block.functional.portal.NowhereActivityPortal;
import net.tslat.aoa3.content.item.misc.TooltipItem;
import net.tslat.aoa3.util.WorldUtil;

public abstract class BossSpawningItem<T extends Entity> extends TooltipItem {
	public BossSpawningItem() {
		this(2, new Item.Properties().tab(AoACreativeModeTabs.MISC_ITEMS).rarity(Rarity.UNCOMMON));
	}

	public BossSpawningItem(int tooltipLines, Properties itemProperties) {
		super(tooltipLines, itemProperties);
	}

	public abstract T spawnBoss(ServerLevel level, Vec3 position);

	public abstract EntityType<T> getEntityType();

	@Override
	public UseAnim getUseAnimation(ItemStack stack) {
		return UseAnim.SPYGLASS;
	}

	@Override
	public int getUseDuration(ItemStack stack) {
		return 60;
	}

	@Override
	public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
		if (!WorldUtil.isWorld(level, AoADimensions.NOWHERE.key))
			return InteractionResultHolder.pass(player.getItemInHand(hand));

		return ItemUtils.startUsingInstantly(level, player, hand);
	}

	@Override
	public void onUseTick(Level level, LivingEntity livingEntity, ItemStack stack, int remainingUseDuration) {
		if (!level.isClientSide()) {
			ServerParticlePacket packet = new ServerParticlePacket();

			for (int i = 0; i < 3; i++) {
				packet.particle(new CustomisableParticleType.Data(AoAParticleTypes.FLICKERING_SPARKLER.get(), 0xB27C00), livingEntity);
			}

			AoAPackets.messageNearbyPlayers(packet, (ServerLevel)level, livingEntity.position(), 20);
		}
	}

	@Override
	public ItemStack finishUsingItem(ItemStack stack, Level level, LivingEntity entity) {
		if (!WorldUtil.isWorld(level, AoADimensions.NOWHERE.key))
			return stack;

		AoABlocks.NOWHERE_PORTAL.get().entityInside(AoABlocks.NOWHERE_PORTAL.get().defaultBlockState(), level, entity.blockPosition(), entity);

		if (entity instanceof ServerPlayer pl)
			NowhereActivityPortal.Activity.BOSSES.teleport(pl);

		return stack;
	}
}
