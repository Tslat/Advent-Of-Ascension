package net.tslat.aoa3.content.item.weapon.staff;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.common.registration.item.AoAItems;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.PlayerUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class CrystalStaff extends BaseStaff<List<Player>> {
	public CrystalStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_CRYSTEVIA_STAFF_CAST.get();
	}

	@Override
	public List<Player> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<Player> playerList = caster.level().getEntitiesOfClass(Player.class, caster.getBoundingBox().inflate(20), PlayerUtil::shouldPlayerBeAffected);

		if (playerList.isEmpty())
			return null;

		for (Player pl : playerList) {
			if (pl.getHealth() != pl.getMaxHealth())
				return playerList;
		}

		return null;
	}

	@Override
	protected void populateRunes(HashMap<Item, Integer> runes) {
		runes.put(AoAItems.DISTORTION_RUNE.get(), 2);
		runes.put(AoAItems.LIFE_RUNE.get(), 5);
	}

	@Override
	public void cast(Level world, ItemStack staff, LivingEntity caster, List<Player> args) {
		float currentTotalHealth = 0;
		float currentMaxHealth = 0;

		for (Player pl : args) {
			currentMaxHealth += pl.getMaxHealth();
			currentTotalHealth += pl.getHealth();
		}

		float healthPerPlayer = (currentMaxHealth * (currentTotalHealth / currentMaxHealth * 1.25f)) / (float)((List<Player>)args).size();

		for (Player pl : args) {
			pl.setHealth(healthPerPlayer);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 2));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
