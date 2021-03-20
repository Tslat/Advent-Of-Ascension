package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.world.World;
import net.tslat.aoa3.common.registration.AoAItems;
import net.tslat.aoa3.common.registration.AoASounds;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.util.LocaleUtil;
import net.tslat.aoa3.util.player.PlayerUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class CrystalStaff extends BaseStaff<List<PlayerEntity>> {
	public CrystalStaff(int durability) {
		super(durability);
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return AoASounds.ITEM_CRYSTEVIA_STAFF_CAST.get();
	}

	@Override
	public List<PlayerEntity> checkPreconditions(LivingEntity caster, ItemStack staff) {
		List<PlayerEntity> playerList = caster.level.getEntitiesOfClass(PlayerEntity.class, caster.getBoundingBox().inflate(20), PlayerUtil::shouldPlayerBeAffected);

		if (playerList.isEmpty())
			return null;

		for (PlayerEntity pl : playerList) {
			if (pl.getHealth() != pl.getMaxHealth())
				return playerList;
		}

		return null;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(AoAItems.DISTORTION_RUNE.get(), 2);
		runes.put(AoAItems.LIFE_RUNE.get(), 5);
	}

	@Override
	public void cast(World world, ItemStack staff, LivingEntity caster, List<PlayerEntity> args) {
		float currentTotalHealth = 0;
		float currentMaxHealth = 0;

		for (PlayerEntity pl : args) {
			currentMaxHealth += pl.getMaxHealth();
			currentTotalHealth += pl.getHealth();
		}

		float healthPerPlayer = (currentMaxHealth * (currentTotalHealth / currentMaxHealth * 1.25f)) / (float)((List<PlayerEntity>)args).size();

		for (PlayerEntity pl : args) {
			pl.setHealth(healthPerPlayer);
		}
	}

	@Override
	public void appendHoverText(ItemStack stack, @Nullable World world, List<ITextComponent> tooltip, ITooltipFlag flag) {
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 1));
		tooltip.add(LocaleUtil.getFormattedItemDescriptionText(this, LocaleUtil.ItemDescriptionType.BENEFICIAL, 2));
		super.appendHoverText(stack, world, tooltip, flag);
	}
}
