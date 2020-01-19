package net.tslat.aoa3.item.weapon.greatblade;

import com.google.common.collect.Multimap;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.ai.attributes.AttributeModifier;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.item.weapon.AdventWeapon;
import net.tslat.aoa3.item.weapon.LongReachWeapon;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.library.misc.AoAAttributes;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.List;

public class PlutonScythe extends BaseGreatblade implements AdventWeapon, LongReachWeapon {
	public PlutonScythe(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("PlutonScythe");
		setRegistryName("aoa3:pluton_scythe");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase attacker, Entity target, float dmgDealt) {
		float damagePercent = (float)dmg / dmgDealt;

		if (attacker instanceof EntityPlayerMP)
			PlayerUtil.addResourceToPlayer((EntityPlayer)attacker, Enums.Resources.SOUL, 1 * damagePercent);

		if (target instanceof EntityPlayerMP)
			PlayerUtil.consumeResource((EntityPlayer)target, Enums.Resources.SOUL, 1 * damagePercent, true);
	}

	@Override
	public Multimap<String, AttributeModifier> getAttributeModifiers(EntityEquipmentSlot equipmentSlot, ItemStack stack) {
		Multimap<String, AttributeModifier> multimap = super.getAttributeModifiers(equipmentSlot, stack);

		if (equipmentSlot == EntityEquipmentSlot.MAINHAND)
			multimap.put(SharedMonsterAttributes.LUCK.getName(), AoAAttributes.LUXON_SCYTHE_LUCK);

		return multimap;
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.scythe", Enums.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
