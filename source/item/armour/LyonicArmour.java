package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.item.EntityXPOrb;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_LYONIC;

public class LyonicArmour extends AdventArmour {
	public LyonicArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_LYONIC, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.LYONIC;
	}

	@Override
	public void onDamageDealt(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {
		if (slots != null) {
			EntityPlayer pl = plData.player();
			int pulledCount = 0;
			float range = 1.5f * (float)slots.size();
			EntityItem item;
			Iterator<EntityItem> iterator = plData.player().world.getEntitiesWithinAABB(EntityItem.class, new AxisAlignedBB(pl.posX - range, pl.posY - range, pl.posZ - range, pl.posX + range, pl.posY + range, pl.posZ + range)).iterator();

			while (iterator.hasNext() && pulledCount <= 200 && canPullItem(item = iterator.next())) {
				EntityUtil.pullEntityIn(pl, item, 0.1f);
				pulledCount++;
			}
		}
		else {
			EntityPlayer pl = plData.player();
			int pulledCount = 0;
			Iterator<EntityXPOrb> iterator = plData.player().world.getEntitiesWithinAABB(EntityXPOrb.class, new AxisAlignedBB(pl.posX - 6, pl.posY - 6, pl.posZ - 6, pl.posX + 6, pl.posY + 6, pl.posZ + 6)).iterator();

			while (iterator.hasNext() && pulledCount <= 200) {
				EntityUtil.pullEntityIn(pl, iterator.next(), 0.1f);
				pulledCount++;
			}
		}
	}

	private boolean canPullItem(EntityItem item) {
		return !item.isDead && !item.getItem().isEmpty() && !item.cannotPickup();
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.LyonicArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.LyonicArmour.desc.2", Enums.ItemDescriptionType.POSITIVE));
		tooltip.add(setEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.LyonicArmour.desc.3", Enums.ItemDescriptionType.POSITIVE));
	}
}
