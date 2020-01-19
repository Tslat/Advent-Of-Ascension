package net.tslat.aoa3.entity.mobs.runandor.templars;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityCreature;
import net.minecraft.entity.SharedMonsterAttributes;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.ItemStack;
import net.minecraft.network.datasync.DataParameter;
import net.minecraft.network.datasync.DataSerializers;
import net.minecraft.network.datasync.EntityDataManager;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.SoundEvent;
import net.minecraft.world.World;
import net.minecraft.world.WorldServer;
import net.tslat.aoa3.common.registration.ItemRegister;
import net.tslat.aoa3.entity.properties.BossEntity;
import net.tslat.aoa3.entity.properties.SpecialPropertyEntity;
import net.tslat.aoa3.item.misc.RuneItem;
import net.tslat.aoa3.library.Enums;
import net.tslat.aoa3.utils.ItemUtil;
import net.tslat.aoa3.utils.LootUtil;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.HashSet;
import java.util.TreeSet;

public abstract class EntityRuneTemplar extends EntityCreature implements SpecialPropertyEntity, BossEntity {
	private static final ResourceLocation bossBarTexture = new ResourceLocation("aoa3", "textures/gui/bossbars/runic_minigame.png");
	private static final DataParameter<Boolean> DISABLED = EntityDataManager.<Boolean>createKey(EntityRuneTemplar.class, DataSerializers.BOOLEAN);
	private final TreeSet<Enums.MobProperties> mobProperties = new TreeSet<Enums.MobProperties>();
	private HashSet<EntityRunicLifeform> lifeforms = new HashSet<EntityRunicLifeform>();
	public static final float entityWidth = 1.125f;

	public EntityRuneTemplar(World world) {
		super(world);

		setSize(entityWidth, 2f);
		this.mobProperties.add(Enums.MobProperties.SPECIAL_COMBAT_ENTITY);
	}

	@Override
	public float getEyeHeight() {
		return 1.8125f;
	}

	@Override
	protected void entityInit() {
		super.entityInit();

		this.dataManager.register(DISABLED, true);
	}

	@Override
	protected void applyEntityAttributes() {
		super.applyEntityAttributes();
		getEntityAttribute(SharedMonsterAttributes.MAX_HEALTH).setBaseValue(400);
		getEntityAttribute(SharedMonsterAttributes.MOVEMENT_SPEED).setBaseValue(0);
		getEntityAttribute(SharedMonsterAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1);
	}

	@Nonnull
	@Override
	public ResourceLocation getBossBarTexture() {
		return bossBarTexture;
	}

	@Nullable
	@Override
	protected SoundEvent getDeathSound() {
		return SoundEvents.BLOCK_ANVIL_LAND;
	}

	@Nullable
	@Override
	protected SoundEvent getHurtSound(DamageSource damageSourceIn) {
		return SoundEvents.BLOCK_ANVIL_LAND;
	}

	private void changeState(boolean disabled) {
		this.dataManager.set(DISABLED, disabled);
	}

	public boolean isDisabled() {
		return this.dataManager.get(DISABLED);
	}

	@Override
	public boolean isEntityInvulnerable(DamageSource source) {
		return source != DamageSource.OUT_OF_WORLD;
	}

	@Override
	public boolean getIsInvulnerable() {
		return true;
	}

	@Override
	public boolean canBePushed() {
		return false;
	}

	@Override
	protected boolean canBeRidden(Entity entityIn) {
		return false;
	}

	@Override
	protected boolean canDespawn() {
		return false;
	}

	@Override
	public void addVelocity(double x, double y, double z) {}

	protected abstract EntityRunicLifeform getLifeForm();

	protected abstract RuneItem getActivationRune();

	@Override
	public boolean isImmuneToExplosions() {
		return true;
	}

	@Override
	protected boolean processInteract(EntityPlayer player, EnumHand hand) {
		ItemStack heldStack = player.getHeldItem(hand);

		if (isDisabled() && heldStack.getItem() == getActivationRune()) {
			if (ItemUtil.consumeItem(player, new ItemStack(ItemRegister.runicEnergy)))
				changeState(false);

			return true;
		}
		else {
			return super.processInteract(player, hand);
		}
	}

	@Override
	public void onLivingUpdate() {
		super.onLivingUpdate();

		if (!world.isRemote && !isDisabled()) {
			setHealth(getHealth() - 0.25f);

			if (getHealth() < 1) {
				doDrops();
				changeState(true);
				setHealth(getMaxHealth());

				for (EntityRunicLifeform lifeforms : lifeforms) {
					lifeforms.setDead();
				}
			}
			else if (rand.nextInt(125) == 0) {
				EntityRunicLifeform lifeform = getLifeForm();

				int coordX = (int)posX - 3 + rand.nextInt(6);
				int coordZ = (int)posZ - 3 + rand.nextInt(6);

				lifeform.setLocationAndAngles(coordX, posY, coordZ, rand.nextFloat() * 360, 0);
				world.spawnEntity(lifeform);
				lifeforms.add(lifeform);
			}
		}
	}

	@Override
	public void onDeath(DamageSource cause) {}

	private void doDrops() {
		float luck = 0;

		for (EntityPlayer pl : world.getEntitiesWithinAABB(EntityPlayer.class, getEntityBoundingBox().grow(8))) {
			float plLuck = pl.getLuck();

			if (plLuck > luck)
				luck = plLuck;
		}

		for (ItemStack stack : LootUtil.generateLootWithCustomLuck(getLootTable(), (WorldServer)world, luck)) {
			entityDropItem(stack, 0);
		}
	}

	@Nonnull
	@Override
	public TreeSet<Enums.MobProperties> getMobProperties() {
		return mobProperties;
	}
}
