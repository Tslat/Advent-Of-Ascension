package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.tslat.aoa3.util.LocaleUtil;

import java.util.List;

public class HiveStaff extends AoAStaff<Object> {
	public HiveStaff(Item.Properties properties) {
		super(properties);
	}

	@Override
	public void cast(ServerLevel level, LivingEntity caster, ItemStack staff, InteractionHand hand, Object args) {
		/*HiveSoldierEntity hiveSoldier = new HiveSoldierEntity(AoAEntities.Minions.HIVE_SOLDIER.get(), caster.level);

		if (caster instanceof Player)
			hiveSoldier.tame((Player)caster);

		hiveSoldier.setPos(caster.getX(), caster.getY(), caster.getZ());
		caster.level.addFreshEntity(hiveSoldier);*/ // TODO
	}

	@Override
	public void appendHoverText(ItemStack stack, TooltipContext context, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		super.appendHoverText(stack, context, tooltip, flag);
	}
}
