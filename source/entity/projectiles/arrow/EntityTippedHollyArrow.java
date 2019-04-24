package net.tslat.aoa3.entity.projectiles.arrow;

import com.google.common.collect.Sets;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.potion.PotionType;
import net.minecraft.potion.PotionUtils;
import net.minecraft.util.EnumParticleTypes;
import net.minecraft.world.World;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.projectiles.HardProjectile;
import net.tslat.aoa3.item.weapon.bow.BaseBow;

import java.util.Collection;
import java.util.Set;

public class EntityTippedHollyArrow extends EntityHollyArrow implements HardProjectile {
	private static final DataParameter<Integer> colour = EntityDataManager.<Integer>createKey(EntityTippedHollyArrow.class, DataSerializers.VARINT);
	private PotionType potion = PotionTypes.EMPTY;
	private final Set<PotionEffect> customPotionEffects = Sets.<PotionEffect>newHashSet();
	private boolean fixedColour;

	public EntityTippedHollyArrow(World worldIn) {
		super(worldIn);
	}

	public EntityTippedHollyArrow(World worldIn, double x, double y, double z) {
		super(worldIn, x, y, z);
	}

	public EntityTippedHollyArrow(World world, BaseBow bow, EntityLivingBase shooter, double damageBase) {
		super(world, bow, shooter, damageBase);
	}

	public void setPotionEffect(ItemStack ammo) {
		if (ammo.getItem() == ItemRegister.hollyArrowTipped) {
			potion = PotionUtils.getPotionFromItem(ammo);
			Collection<PotionEffect> collection = PotionUtils.getFullEffectsFromItem(ammo);

			if (!collection.isEmpty()) {
				for (PotionEffect effect : collection) {
					customPotionEffects.add(new PotionEffect(effect));
				}
			}

			int i = getCustomColour(ammo);

			if (i == -1) {
				refreshColour();
			}
			else {
				setFixedColour(i);
			}
		}
	}

	public static int getCustomColour(ItemStack ammo) {
		NBTTagCompound nbt = ammo.getTagCompound();
		return nbt != null && nbt.hasKey("CustomPotionColour", 99) ? nbt.getInteger("CustomPotionColour") : -1;
	}

	private void refreshColour() {
		fixedColour = false;
		dataManager.set(colour, PotionUtils.getPotionColorFromEffectList(PotionUtils.mergeEffects(potion, customPotionEffects)));
	}

	public void addEffect(PotionEffect effect) {
		customPotionEffects.add(effect);
		getDataManager().set(colour, PotionUtils.getPotionColorFromEffectList(PotionUtils.mergeEffects(this.potion, this.customPotionEffects)));
	}

	@Override
	protected void entityInit() {
		super.entityInit();
		dataManager.register(colour, -1);
	}

	@Override
	public void onUpdate() {
		super.onUpdate();

		if (world.isRemote) {
			if (inGround) {
				if (timeInGround % 5 == 0)
					spawnPotionParticles(1);
			}
			else {
				spawnPotionParticles(2);
			}
		}
		else if (inGround && timeInGround != 0 && !customPotionEffects.isEmpty() && timeInGround >= 600) {
			world.setEntityState(this, (byte)0);
			potion = PotionTypes.EMPTY;
			customPotionEffects.clear();
			dataManager.set(colour, -1);
		}
	}

	private void spawnPotionParticles(int particleCount) {
		int colour = getColour();

		if (colour != -1 && particleCount > 0) {
			double red = (double)(colour >> 16 & 255) / 255.0D;
			double green = (double)(colour >> 8 & 255) / 255.0D;
			double blue = (double)(colour & 255) / 255.0D;

			for (int j = 0; j < particleCount; ++j) {
				world.spawnParticle(EnumParticleTypes.SPELL_MOB, posX + (rand.nextDouble() - 0.5D) * (double)width, posY + rand.nextDouble() * (double)height, posZ + (rand.nextDouble() - 0.5D) * (double)width, red, green, blue);
			}
		}
	}

	public int getColour() {
		return dataManager.get(colour);
	}

	private void setFixedColour(int value) {
		fixedColour = true;
		dataManager.set(colour, value);
	}

	@Override
	protected void arrowHit(EntityLivingBase target) {
		super.arrowHit(target);

		for (PotionEffect effect : potion.getEffects()) {
			target.addPotionEffect(new PotionEffect(effect.getPotion(), Math.max(effect.getDuration() / 2, 1), effect.getAmplifier(), effect.getIsAmbient(), effect.doesShowParticles()));
		}

		if (!this.customPotionEffects.isEmpty()) {
			for (PotionEffect effect : this.customPotionEffects)	{
				target.addPotionEffect(effect);
			}
		}
	}

	@Override
	protected ItemStack getArrowStack() {
		if (customPotionEffects.isEmpty() && potion == PotionTypes.EMPTY) {
			return new ItemStack(ItemRegister.hollyArrow);
		}
		else {
			ItemStack arrowStack = new ItemStack(ItemRegister.hollyArrowTipped);

			PotionUtils.addPotionToItemStack(arrowStack, potion);
			PotionUtils.appendEffects(arrowStack, customPotionEffects);

			if (fixedColour) {
				NBTTagCompound nbt = arrowStack.getTagCompound();

				if (nbt == null) {
					nbt = new NBTTagCompound();
					arrowStack.setTagCompound(nbt);
				}

				nbt.setInteger("CustomPotionColour", getColour());
			}

			return arrowStack;
		}
	}

	public Potion getBasePotionEffect() {
		return potion.getEffects().get(0).getPotion();
	}

	@Override
	public void doImpactEffect() {}

	@SideOnly(Side.CLIENT)
	public void handleStatusUpdate(byte id) {
		if (id == 0) {
			int colour = getColour();

			if (colour != -1) {
				double red = (double)(colour >> 16 & 255) / 255.0D;
				double green = (double)(colour >> 8 & 255) / 255.0D;
				double blue = (double)(colour & 255) / 255.0D;

				for (int j = 0; j < 20; ++j) {
					this.world.spawnParticle(EnumParticleTypes.SPELL_MOB, this.posX + (this.rand.nextDouble() - 0.5D) * (double)this.width, this.posY + this.rand.nextDouble() * (double)this.height, this.posZ + (this.rand.nextDouble() - 0.5D) * (double)this.width, red, green, blue);
				}
			}
		}
		else {
			super.handleStatusUpdate(id);
		}
	}
}
