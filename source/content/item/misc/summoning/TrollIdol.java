package net.tslat.aoa3.content.item.misc.summoning;

import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.MobSpawnType;
import net.minecraft.world.entity.MobType;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import net.tslat.aoa3.common.registration.AoACreativeModeTabs;
import net.tslat.aoa3.common.registration.entity.AoAMobs;
import net.tslat.aoa3.content.entity.boss.SmashEntity;
import net.tslat.aoa3.util.EntitySpawningUtil;
import net.tslat.aoa3.util.LocaleUtil;

import javax.annotation.Nullable;
import java.util.List;

public class TrollIdol extends BossSpawningItem<SmashEntity> {
	public TrollIdol() {
		super(0, new Item.Properties().tab(AoACreativeModeTabs.MISC_ITEMS).rarity(Rarity.RARE).durability(20).setNoRepair());
	}

	@Override
	public SmashEntity spawnBoss(ServerLevel level, Vec3 position) {
		return EntitySpawningUtil.spawnEntity(level, AoAMobs.SMASH.get(), position, MobSpawnType.TRIGGERED);
	}

	@Override
	public EntityType<SmashEntity> getEntityType(ItemStack stack) {
		return stack.isDamaged() ? null : AoAMobs.SMASH.get();
	}

	@Override
	public boolean hurtEnemy(ItemStack stack, LivingEntity target, LivingEntity attacker) {
		if (!stack.isDamaged() || target.getMobType() != MobType.UNDEAD || target.getHealth() > 0)
			return false;

		stack.setDamageValue(stack.getDamageValue() - 1);

		return true;
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level worldIn, List<Component> tooltip, TooltipFlag flagIn) {
		if (!stack.isDamaged()) {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 1));
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.UNIQUE, 2));
		}
		else {
			tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.NEUTRAL, 3));
		}
	}
}
