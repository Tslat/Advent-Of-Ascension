package net.tslat.aoa3.item.weapon.greatblade;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;
import net.tslat.aoa3.utils.player.PlayerUtil;

import java.util.List;

public class LuxonScythe extends BaseGreatblade {
	public LuxonScythe(double dmg, double speed, int durability) {
		super(dmg, speed, durability);
		setTranslationKey("LuxonScythe");
		setRegistryName("aoa3:luxon_scythe");
	}

	@Override
	protected void doMeleeEffect(ItemStack stack, EntityLivingBase attacker, Entity target, float dmgDealt) {
		if (!attacker.world.isRemote) {
			float damagePercent = dmgDealt / (float)getDamage();
			PlayerDataManager.PlayerStats targetStats = target instanceof EntityPlayerMP ? PlayerUtil.getAdventPlayer((EntityPlayer)target).stats() : null;
			float soulAmount = (targetStats != null ? Math.min(5, targetStats.getResourceValue(Enums.Resources.SOUL)) : 5) * damagePercent;

			if (soulAmount > 0) {
				if (targetStats != null && !targetStats.consumeResource(Enums.Resources.SOUL, soulAmount, true))
					return;

				if (attacker instanceof EntityPlayerMP)
					PlayerUtil.addResourceToPlayer((EntityPlayer)attacker, Enums.Resources.SOUL, soulAmount);
			}
		}
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(ItemStack stack, World worldIn, List<String> tooltip, ITooltipFlag flagIn) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("items.description.scythe", Enums.ItemDescriptionType.ITEM_TYPE_INFO));
	}
}
