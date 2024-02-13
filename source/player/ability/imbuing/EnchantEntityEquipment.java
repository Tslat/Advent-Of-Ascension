package net.tslat.aoa3.player.ability.imbuing;

import com.google.gson.JsonObject;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.neoforged.neoforge.event.entity.player.PlayerInteractEvent;
import net.tslat.aoa3.common.registration.custom.AoAAbilities;
import net.tslat.aoa3.content.item.misc.PowerStone;
import net.tslat.aoa3.player.ability.AoAAbility;
import net.tslat.aoa3.player.skill.AoASkill;
import net.tslat.aoa3.util.PlayerUtil;
import net.tslat.effectslib.api.particle.ParticleBuilder;
import net.tslat.effectslib.networking.packet.TELParticlePacket;

public class EnchantEntityEquipment extends AoAAbility.Instance {
	private static final ListenerType[] LISTENERS = new ListenerType[] {ListenerType.ENTITY_INTERACT};

	public EnchantEntityEquipment(AoASkill.Instance skill, JsonObject data) {
		super(AoAAbilities.ENCHANT_ENTITY_EQUIPMENT.get(), skill, data);
	}

	public EnchantEntityEquipment(AoASkill.Instance skill, CompoundTag data) {
		super(AoAAbilities.ENCHANT_ENTITY_EQUIPMENT.get(), skill, data);
	}

	@Override
	public ListenerType[] getListenerTypes() {
		return LISTENERS;
	}

	@Override
	public void handleEntityInteraction(PlayerInteractEvent.EntityInteractSpecific ev) {
		if (ev.getItemStack().getItem() instanceof PowerStone powerStone) {
			final ServerPlayer player = (ServerPlayer)ev.getEntity();
			final ServerLevel level = player.serverLevel();
			final Entity target = ev.getTarget();

			final int enchantLevel = powerStone.getEnchantLevel();
			int totalEnchants = 0;

			for (ItemStack heldItem : target.getHandSlots()) {
				if (heldItem.isEnchantable() && heldItem.getItem() != Items.BOOK) {
					EnchantmentHelper.enchantItem(player.getRandom(), heldItem, enchantLevel, false);
					totalEnchants++;
				}
			}

			for (ItemStack armourItem : target.getArmorSlots()) {
				if (armourItem.isEnchantable() && armourItem.getItem() != Items.BOOK) {
					EnchantmentHelper.enchantItem(player.getRandom(), armourItem, enchantLevel, false);
					totalEnchants++;
				}
			}

			if (totalEnchants > 0) {
				PlayerUtil.giveTimeBasedXpToPlayer(player, this.skill.type(), 100 * totalEnchants * enchantLevel,  false);

				if (!player.getAbilities().instabuild)
					ev.getItemStack().shrink(1);

				level.playSound(null, target.blockPosition(), SoundEvents.ENCHANTMENT_TABLE_USE, SoundSource.PLAYERS, 1, 1);

				TELParticlePacket packet = new TELParticlePacket();

				for (int i = 0; i < 100; i++) {
					packet.particle(ParticleBuilder.forRandomPosInEntity(ParticleTypes.GLOW, target)
							.colourOverride(player.getRandom().nextIntBetweenInclusive(100, 220), 0, 255, 255));
				}

				packet.sendToAllPlayersTrackingEntity(level, target);

				ev.setCanceled(true);
			}
		}
	}
}
