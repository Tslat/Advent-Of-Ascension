package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.MutableComponent;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.common.registration.AoAExplosions;
import net.tslat.aoa3.content.entity.projectile.staff.HaunterShotEntity;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class HauntersStaff extends AoAStaff<Object> {
	public HauntersStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Object args) {
		fireProjectile(level, caster, staff, hand, HaunterShotEntity::new);
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		super.appendHoverText(stack, context, tooltip, flag);
		tooltip.add(2, LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));

		for (MutableComponent component : LocaleUtil.getExplosionInfoLocale(AoAExplosions.HAUNTER_SHOT, true, flag.isAdvanced(), false)) {
			tooltip.add(3, component);
		}
	}
}
