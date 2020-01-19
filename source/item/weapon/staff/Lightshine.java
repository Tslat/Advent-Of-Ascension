package net.tslat.aoa3.item.weapon.staff;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.item.ItemStack;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.common.registration.SoundsRegister;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.PredicateUtil;

import javax.annotation.Nullable;
import java.util.HashMap;
import java.util.List;

public class Lightshine extends BaseStaff {
	public Lightshine(int durability) {
		super(durability);
		setTranslationKey("Lightshine");
		setRegistryName("aoa3:lightshine");
	}

	@Nullable
	@Override
	public SoundEvent getCastingSound() {
		return SoundsRegister.staffLightshine;
	}

	@Override
	protected void populateRunes(HashMap<RuneItem, Integer> runes) {
		runes.put(ItemRegister.runePower, 3);
		runes.put(ItemRegister.runeLife, 3);
		runes.put(ItemRegister.runeDistortion, 2);
	}

	@Override
	public Object checkPreconditions(EntityLivingBase caster, ItemStack staff) {
		List<EntityLivingBase> list = caster.world.getEntitiesWithinAABB(EntityLivingBase.class, caster.getEntityBoundingBox().grow(10), PredicateUtil.IS_HOSTILE_MOB);

		if (!list.isEmpty())
			return list;

		return null;
	}

	@Override
	public void cast(World world, ItemStack staff, EntityLivingBase caster, Object args) {
		((List<EntityLivingBase>)args).removeIf(entityLivingBase -> !EntityUtil.dealMagicDamage(null, caster, entityLivingBase, 1.0f, true));
		EntityUtil.healEntity(caster, Math.min(15, ((List<EntityLivingBase>)args).size()));
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.Lightshine.desc.1", Enums.ItemDescriptionType.POSITIVE));
		super.addInformation(stack, world, tooltip, flag);
	}
}
