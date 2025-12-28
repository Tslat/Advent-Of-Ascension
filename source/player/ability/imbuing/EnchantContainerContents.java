package net.tslat.aoa3.player.ability.imbuing;

import com.google.gson.JsonObject;
import net.minecraft.core.BlockPos;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.block.ChestBlock;
import net.minecraft.world.level.block.entity.ChestBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.ChestType;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.content.item.misc.PowerStone;
import net.tslat.aoa3.event.dynamic.DynamicEventSubscriber;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.tme.api.particle.ParticleBuilder;
import net.tslat.tme.internal.networking.packet.TMEParticlePacket;

import java.util.List;
import java.util.Optional;

public class EnchantContainerContents extends AoAAbility.Instance {
	private final List<DynamicEventSubscriber<?>> eventSubscribers = List.of(
			listener(PlayerInteractEvent.RightClickBlock.class, PlayerInteractEvent.RightClickBlock::getEntity, serverOnly(this::handleBlockInteraction)));

	public EnchantContainerContents(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.ENCHANT_CONTAINER_CONTENTS.get(), skill, data);
	}

	public EnchantContainerContents(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.ENCHANT_CONTAINER_CONTENTS.get(), skill, data);
	}

	@Override
	public List<DynamicEventSubscriber<?>> getEventSubscribers() {
		return this.eventSubscribers;
	}

	private void handleBlockInteraction(PlayerInteractEvent.RightClickBlock ev) {
		if (ev.getItemStack().getItem() instanceof PowerStone powerStone) {
			final ServerPlayer player = (ServerPlayer)ev.getEntity();

			if (!player.isCrouching())
				return;

			final ServerLevel level = player.serverLevel();
			final BlockPos pos = ev.getPos();

			if (level.getBlockEntity(pos) instanceof Container container) {
				BlockState blockState = level.getBlockState(pos);

				if (container instanceof ChestBlockEntity && blockState.getBlock() instanceof ChestBlock chestBlock) {
					container = ChestBlock.getContainer(chestBlock, blockState, level, pos, true);

					if (container == null)
						return;
				}

				final int enchantLevel = powerStone.getEnchantLevel();
				int enchantedCount = 0;

				for (int i = 0; i < container.getContainerSize(); i++) {
					ItemStack stack = container.getItem(i);

					if (stack.isEnchantable() && stack.getItem() != Items.BOOK) {
						EnchantmentHelper.enchantItem(player.getRandom(), stack, enchantLevel, level.registryAccess(), Optional.empty());
						enchantedCount++;
					}
				}

				if (enchantedCount > 0) {
					PlayerUtil.giveTimeBasedXpToPlayer(player, this.skill.type(), 100 * enchantedCount * enchantLevel,  false);

					if (!player.getAbilities().instabuild)
						ev.getItemStack().shrink(1);

					level.playSound(null, pos, SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1, 1);

					TMEParticlePacket packet = new TMEParticlePacket();

					for (int i = 0; i < 100; i++) {
						packet.particle(ParticleBuilder.forRandomPosInBlock(ParticleTypes.GLOW, pos)
								.colourTint(player.getRandom().nextIntBetweenInclusive(100, 220), 0, 255, 255));
					}

					if (blockState.getBlock() instanceof ChestBlock && blockState.getValue(ChestBlock.TYPE) != ChestType.SINGLE) {
						BlockPos attachedPos = pos.relative(ChestBlock.getConnectedDirection(blockState));

						for (int i = 0; i < 100; i++) {
							packet.particle(ParticleBuilder.forRandomPosInBlock(ParticleTypes.GLOW, attachedPos)
									.colourTint(player.getRandom().nextIntBetweenInclusive(100, 220), 0, 255, 255));
						}
					}

					packet.sendToAllPlayersTrackingBlock(level, pos);

					ev.setCanceled(true);
				}
			}
		}
	}
}
