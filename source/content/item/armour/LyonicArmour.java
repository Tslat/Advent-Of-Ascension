package net.tslat.aoa3.content.item.armour;

import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.AbstractArrow;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.item.AoAArmourMaterials;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.tme.api.util.EntityRetrievalUtil;

import java.util.EnumSet;
import java.util.List;

public class LyonicArmour extends AdventArmour {
	public LyonicArmour(ArmorItem.Type slot) {
		super(AoAArmourMaterials.LYONIC, slot, 56);
	}

	@Override
	public void onArmourTick(LivingEntity entity, EnumSet<Piece> equippedPieces) {
		if (entity.level().getGameTime() % 2 == 0) {
			final boolean fullSet = equippedPieces.contains(Piece.FULL_SET);

			for (Entity attractedEntity : EntityRetrievalUtil.getEntities(entity, perPieceValue(equippedPieces, 2.5f), Entity.class, target -> target.isAlive() && (canPullItem(target, entity) || (fullSet && canPullOther(target, entity))))) {
				EntityUtil.pullEntityIn(entity, attractedEntity, 0.05f, true);
			}
		}
	}

	private boolean canPullItem(Entity entity, LivingEntity wearer) {
		return entity instanceof ItemEntity item && !item.getItem().isEmpty() && !item.hasPickUpDelay();
	}

	private boolean canPullOther(Entity entity, LivingEntity wearer) {
		if (entity instanceof ExperienceOrb)
			return true;

		if (entity instanceof AbstractArrow arrow && arrow.lastState != null && (!(wearer instanceof Player pl) || (arrow.pickup == AbstractArrow.Pickup.ALLOWED || pl.hasInfiniteMaterials()))) {
			arrow.inGround = false;
			arrow.setBaseDamage(0);
			arrow.addDeltaMovement(new Vec3(0, 0.1f, 0));
			arrow.hurtMarked = true;
			return true;
		}

		return false;
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag tooltipFlag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lyonic_armour.desc.1", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(pieceEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lyonic_armour.desc.2", LocaleUtil.ItemDescriptionType.BENEFICIAL));
		tooltip.add(setEffectHeader());
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("item.aoa3.lyonic_armour.desc.3", LocaleUtil.ItemDescriptionType.BENEFICIAL));
	}
}
