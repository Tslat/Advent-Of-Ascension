package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.ItemStack;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAWeapons;
import net.tslat.aoa3.entity.projectile.thrown.GrenadeEntity;
import net.tslat.aoa3.util.EntityUtil;
import net.tslat.aoa3.util.ItemUtil;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.constant.AttackSpeed;

import javax.annotation.Nullable;
import java.util.List;

public class BaronGreatblade extends BaseGreatblade {
	public BaronGreatblade() {
		super(19.5f, AttackSpeed.GREATBLADE, 1200);
	}
	@Override
	protected void doMeleeEffect(ItemStack stack, LivingEntity attacker, Entity target, float dmgDealt) {
		if (!attacker.level.isClientSide && EntityUtil.getAttackCooldown(attacker) > 0.85f) {
			if (!(attacker instanceof PlayerEntity) || ((PlayerEntity)attacker).isCreative() || ItemUtil.findInventoryItem((PlayerEntity)attacker, new ItemStack(AoAWeapons.GRENADE.get()), true, 1)) {
				attacker.level.addFreshEntity(new GrenadeEntity(attacker, null));
				ItemUtil.damageItem(stack, attacker, 1, EquipmentSlotType.MAINHAND);
			}
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World worldIn, List<ITextComponent> tooltip, ITooltipFlag flagIn) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
	}
}
