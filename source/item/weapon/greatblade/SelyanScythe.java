package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.Resources;
import net.tslat.aoa3.util.player.PlayerDataManager;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.List;

public class SelyanScythe extends BaseGreatblade {
	public SelyanScythe() {
		super(19.0f, -3D, 1750);
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity attacker, Entity target, float dmgDealt) {
		float damagePercent = Math.min(dmgDealt / (float)getAttackDamage(), 1f);

		attacker.heal(1 * damagePercent);

		PlayerDataManager.PlayerStats targetStats = target instanceof ServerPlayerEntity ? PlayerUtil.getAdventPlayer((ServerPlayerEntity)target).stats() : null;
		float soulAmount = (targetStats != null ? Math.min(5, targetStats.getResourceValue(Resources.SOUL)) : 5) * damagePercent;

		if (soulAmount > 0) {
			if (targetStats != null && !targetStats.consumeResource(Resources.SOUL, soulAmount, true))
				return;

			if (attacker instanceof ServerPlayerEntity)
				PlayerUtil.addResourceToPlayer((ServerPlayerEntity)attacker, Resources.SOUL, soulAmount);
		}
	}

	@Override
	public void addInformation(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText("items.description.scythe", LocaleUtil.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
