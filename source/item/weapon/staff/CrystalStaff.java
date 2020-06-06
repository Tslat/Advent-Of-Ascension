package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class CrystalStaff extends BaseStaff {
	public CrystalStaff(int durability) {
		super(durability);
		setTranslationKey("CrystalStaff");
		setRegistryName("aoa3:crystal_staff");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.CRYSTEVIA_STAFF_CAST;
	}

	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		List<EntityPlayer> playerList = caster.world.getEntitiesWithinAABB(EntityPlayer.class, caster.getEntityBoundingBox().grow(20), PredicateUtil.IS_VULNERABLE_PLAYER);

		if (playerList.isEmpty())
			return null;

		for (EntityPlayer pl : playerList) {
			if (pl.getHealth() != pl.getMaxHealth())
				return playerList;
		}

		return null;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.DISTORTION_RUNE, 2);
		runes.put(ItemRegister.LIFE_RUNE, 5);
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		float currentTotalHealth = 0;
		float currentMaxHealth = 0;

		for (EntityPlayer pl : (List<EntityPlayer>)args) {
			currentMaxHealth += pl.getMaxHealth();
			currentTotalHealth += pl.getHealth();
		}

		float healthPerPlayer = (currentMaxHealth * (currentTotalHealth / currentMaxHealth * 1.25f)) / (float)((List<EntityPlayer>)args).size();

		for (EntityPlayer pl : (List<EntityPlayer>)args) {
			pl.setHealth(healthPerPlayer);
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CrystalStaff.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.CrystalStaff.desc.2", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
