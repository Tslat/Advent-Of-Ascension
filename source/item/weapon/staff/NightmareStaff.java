package net.nevermine.item.weapon.staff;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.Vec3;
import net.minecraft.world.World;
import net.nevermine.assist.StringUtil;
import net.nevermine.item.ItemRune;
import net.nevermine.izer.Itemizer;
import net.nevermine.izer.equipment.Weaponizer;
import net.nevermine.projectiles.staff.EntityNightmareFall;

import java.util.HashMap;
import java.util.List;

public class NightmareStaff extends BaseStaff {
	private static HashMap<ItemRune, Integer> runes = new HashMap<ItemRune, Integer>();
	private double posX;
	private double posY;
	private double posZ;

	public NightmareStaff() {
		super(runes);
		setCreativeTab(Weaponizer.StaffTab);
		setMaxDamage(400);
	}

	public boolean getIsRepairable(final ItemStack par1ItemStack, final ItemStack par2ItemStack) {
		return Itemizer.IngotRosite == par2ItemStack.getItem() || super.getIsRepairable(par1ItemStack, par2ItemStack);
	}

	@Override
	public boolean checkConditions(final World world, final ItemStack stack, final EntityPlayer player) {
		if (stack.getItem() == Weaponizer.NightmareStaff) {
			final Vec3 worldVector = Vec3.createVectorHelper(player.posX, player.posY + 1.62 - player.yOffset, player.posZ);
			final float yawAngleCos = MathHelper.cos(-player.rotationYaw * 0.01745329f - 3.1415927f);
			final float yawAngleSin = MathHelper.sin(-player.rotationYaw * 0.01745329f - 3.1415927f);
			final float pitchAngle = -MathHelper.cos(-player.rotationPitch * 0.01745329f);
			final Vec3 worldVector2 = worldVector.addVector(yawAngleSin * pitchAngle * 30, MathHelper.sin(-player.rotationPitch * 0.01745329f) * 30, yawAngleCos * pitchAngle * 30);
			final MovingObjectPosition objPos = world.rayTraceBlocks(worldVector, worldVector2);

			if (objPos != null) {
				int blockPosX;
				int blockPosY;
				int blockPosZ;

				if (objPos.typeOfHit == MovingObjectPosition.MovingObjectType.BLOCK) {
					blockPosX = objPos.blockX;
					blockPosY = objPos.blockY;
					blockPosZ = objPos.blockZ;

					switch (objPos.sideHit) {
						case 0:
							--blockPosY;
							break;
						case 1:
							++blockPosY;
							break;
						case 2:
							--blockPosZ;
							break;
						case 3:
							++blockPosZ;
							break;
						case 4:
							--blockPosX;
							break;
						case 5:
							++blockPosX;
							break;
						default:
							break;
					}

					posX = blockPosX + 0.5;
					posY = blockPosY + 25.0;
					posZ = blockPosZ + 0.5;

					return true;
				}
			}
		}

		return false;
	}

	@Override
	public void fireStaff(final World world, final ItemStack stack, final EntityPlayer player) {
		if (!world.isRemote) {
			for (int i = 0; i < 8; ++i) {
				world.spawnEntityInWorld(new EntityNightmareFall(world, posX, posY, posZ, player));
			}

			player.worldObj.playSoundAtEntity(player, "nevermine:NightmareStaff", 1.0f, 1.0f);
		}

		stack.damageItem(1, player);
	}

	@SideOnly(Side.CLIENT)
	public void addInformation(final ItemStack stack, final EntityPlayer player, final List list, final boolean bool) {
		list.add(StringUtil.getColourLocaleString("item.NightmareStaff.desc.1", EnumChatFormatting.DARK_GREEN));
		list.add(StringUtil.getColourLocaleString("items.description.runes.required", EnumChatFormatting.LIGHT_PURPLE));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(2), StringUtil.getLocaleString("item.WindRune.name")));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(1), StringUtil.getLocaleString("item.PowerRune.name")));
		list.add(StringUtil.getColourLocaleStringWithArguments("items.description.runes.specific", EnumChatFormatting.WHITE, Integer.toString(1), StringUtil.getLocaleString("item.StormRune.name")));
	}

	static {
		runes.put((ItemRune)Itemizer.WindRune, 2);
		runes.put((ItemRune)Itemizer.PowerRune, 1);
		runes.put((ItemRune)Itemizer.StormRune, 1);
	}
}
