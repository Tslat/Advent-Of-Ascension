package net.tslat.aoa3.item.armour;

import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.inventory.EntityEquipmentSlot;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.event.entity.living.LivingHurtEvent;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.EntityUtil;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.player.PlayerDataManager;

import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.List;

import static net.tslat.aoa3.common.registration.MaterialsRegister.ARMOUR_EXOPLATE;

public class ExoplateArmour extends AdventArmour {
	public ExoplateArmour(String name, String registryName, EntityEquipmentSlot slot) {
		super(ARMOUR_EXOPLATE, name, registryName, slot);
	}

	@Override
	public Enums.ArmourSets setType() {
		return Enums.ArmourSets.EXOPLATE;
	}

	@Override
	public void onAttackReceived(PlayerDataManager plData, @Nullable HashSet<EntityEquipmentSlot> slots, LivingHurtEvent event) {
		if (slots != null && !EntityUtil.isEnvironmentalDamage(event.getSource())) {
			EntityLivingBase entity = event.getEntityLiving();
			BlockPos playerPos = entity.getPosition();
			Chunk chunk = entity.world.getChunk(playerPos);
			int lightLvl = MathHelper.clamp(2 + Math.max(chunk.getLightFor(EnumSkyBlock.SKY, playerPos) - entity.world.getSkylightSubtracted(), entity.world.getLightFor(EnumSkyBlock.BLOCK, playerPos)), 0, 15);

			event.setAmount(event.getAmount() * (1 - (1 - (lightLvl / 15f)) * 0.0625f * slots.size()));
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void addInformation(ItemStack stack, World world, List<String> tooltip, ITooltipFlag flag) {
		tooltip.add(pieceEffectHeader());
		tooltip.add(ItemUtil.getFormattedDescriptionText("item.ExoplateArmour.desc.1", Enums.ItemDescriptionType.POSITIVE));
	}
}
