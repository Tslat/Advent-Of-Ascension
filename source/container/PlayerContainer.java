package net.nevermine.container;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.entity.Entity;
import net.minecraft.entity.monster.EntityMob;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.world.World;
import net.minecraftforge.common.IExtendedEntityProperties;
import net.nevermine.assist.AddPackets;
import net.nevermine.resource.revenge.revengeMessage;
import net.nevermine.resource.tribute.TributeMessage;
import net.nevermine.skill.SkillMessage;

import java.util.HashMap;

import static net.nevermine.container.PlayerContainer.Deities.*;
import static net.nevermine.container.PlayerContainer.Skills.*;

public class PlayerContainer implements IExtendedEntityProperties {
	private final EntityPlayer player;

	private HashMap<Skills, Float> skillsXp = new HashMap<Skills, Float>();
	private HashMap<Skills, Integer> skillsLvl = new HashMap<Skills, Integer>();
	private HashMap<Deities, Integer> tribute = new HashMap<Deities, Integer>();

	private int expeditionBoost = 1;

	public int runicCounter = 600;
	public int rosidCounter = 300;
	public int hydroplateCounter = 15;
	public int candyCounter = 1800;
	public int archaicCounter = 1600;
	public int mercurialCounter = 10;
	public int battlebornCounter = 300;
	public int omniCounter = 120;
	public int knightCounter = 2;

	private int revengeCounter = 0;
	private EntityMob revengeTarget = null;

	private PortalCoordinatesContainer portalReturnLocation = new PortalCoordinatesContainer(0, 0, 0);

	public PlayerContainer(final EntityPlayer pl) {
		player = pl;

		for (Skills sk : Skills.values()) {
			skillsXp.put(sk, 0.0f);
			skillsLvl.put(sk, 1);
		}

		for (Deities de : Deities.values()) {
			tribute.put(de, 0);
		}
	}

	public void saveNBTData(final NBTTagCompound n) {
		for (Skills sk : Skills.values()) {
			saveNBTSkillData(sk, n);
		}

		saveNBTTributeData(n);
		saveNBTPortalReturnData(n);
	}

	public void saveNBTSkillData(Skills skill, NBTTagCompound baseTag) {
		final NBTTagCompound tag = baseTag.getCompoundTag("PlayerPersisted");
		final NBTTagCompound skillTag = new NBTTagCompound();
		if (skillsLvl.get(skill) == 0)
			return;

		skillTag.setInteger("Lvl", skillsLvl.get(skill));
		skillTag.setFloat("Xp", skillsXp.get(skill));

		if (skill == Skills.Expedition)
			skillTag.setInteger("Opt", expeditionBoost);

		tag.setTag(skill.name(), skillTag);
		baseTag.setTag("PlayerPersisted", tag);
		player.getEntityData().setTag("PlayerPersisted", tag);
	}

	public void saveNBTTributeData(NBTTagCompound baseTag) {
		final NBTTagCompound tag = baseTag.getCompoundTag("PlayerPersisted");
		final NBTTagCompound tributeTag = new NBTTagCompound();

		tributeTag.setInteger(Luxon.name(), getTribute(Luxon));
		tributeTag.setInteger(Pluton.name(), getTribute(Pluton));
		tributeTag.setInteger(Selyan.name(), getTribute(Selyan));
		tributeTag.setInteger(Erebon.name(), getTribute(Erebon));
		tag.setTag("Tribute", tributeTag);
		baseTag.setTag("PlayerPersisted", tag);
		player.getEntityData().setTag("PlayerPersisted", tag);
	}

	public void saveNBTPortalReturnData(NBTTagCompound baseTag) {
		final NBTTagCompound tag = baseTag.getCompoundTag("PlayerPersisted");

		final NBTTagCompound portalTag = new NBTTagCompound();

		portalTag.setDouble("posX", portalReturnLocation.x);
		portalTag.setDouble("posY", portalReturnLocation.y);
		portalTag.setDouble("posZ", portalReturnLocation.z);

		tag.setTag("PortalReturnLoc", portalTag);
		baseTag.setTag("PlayerPersisted", tag);
		player.getEntityData().setTag("PlayerPersisted", tag);
	}

	public void loadNBTData(final NBTTagCompound n) {
		final NBTTagCompound tag = n.getCompoundTag("PlayerPersisted");

		if (n.hasKey("animaSkill")) {
			doLegacyImport();
			return;
		}

		for (Skills sk : Skills.values()) {
			if (!tag.hasKey(sk.name()))
				continue;

			NBTTagCompound skillTag = tag.getCompoundTag(sk.name());
			Float skillXp = 0.0f;
			Integer skillLvl = 1;

			skillXp = Math.max(skillTag.getFloat("Xp"), 0);
			skillLvl = Math.max(skillTag.getInteger("Lvl"), 1);

			if (sk == Skills.Expedition)
				expeditionBoost = skillTag.getInteger("Opt");

			skillsXp.put(sk, skillXp);
			skillsLvl.put(sk, skillLvl);
		}

		if (tag.hasKey("PortalReturnLoc")) {
			NBTTagCompound portalTag = tag.getCompoundTag("PortalReturnLoc");
			portalReturnLocation = new PortalCoordinatesContainer(portalTag.getDouble("posX"), portalTag.getDouble("posY"), portalTag.getDouble("posZ"));
		}

		if (tag.hasKey("Tribute")) {
			NBTTagCompound tributeTag = tag.getCompoundTag("Tribute");

			for (Deities de : Deities.values()) {
				Integer val = 0;

				if (tributeTag != null)
					val = tributeTag.getInteger(de.name());

				tribute.put(de, val);
			}
		}

		player.getEntityData().setTag("PlayerPersisted", tag);
	}

	public void tickPlayer() {
		if (revengeCounter > 0) {
			--revengeCounter;

			if (revengeCounter <= 0 && player instanceof EntityPlayerMP)
				AddPackets.network.sendTo(new revengeMessage(false), (EntityPlayerMP)player);
		}
	}

	public int getExpeditionBoost() {
		return expeditionBoost;
	}

	public void setExpeditionBoost(final int val) {
		expeditionBoost = val;
	}

	public void expeditionBoostUp() {
		++expeditionBoost;

		if (expeditionBoost > 3)
			expeditionBoost = 0;

		if (player instanceof EntityPlayerMP)
			AddPackets.network.sendTo(new SkillMessage(skillIndex(Skills.Expedition), getLevel(Skills.Expedition), progressPercentage(Skills.Expedition), expeditionBoost), (EntityPlayerMP)player);
	}

	public int getLevel(Skills skill) {
		return skillsLvl.get(skill);
	}

	public float getExperience(Skills skill) {
		return skillsXp.get(skill);
	}

	public float getExpRequired(Skills skill) {
		return (float)(Math.pow(1.1, skillsLvl.get(skill) - 1) * 50.0);
	}

	public void addExperience(final float amount, final Skills sk) {
		if (getLevel(sk) == 100 && progressPercentage(sk) == 100)
			return;

		float remaining = amount;

		while (remaining >= getExpLeft(sk)) {
			remaining -= getExpLeft(sk);
			if (getLevel(sk) < 100)
				levelUp(sk);
			else {
				return;
			}
		}

		skillsXp.put(sk, skillsXp.get(sk) + remaining);

		if (player instanceof EntityPlayerMP)
			AddPackets.network.sendTo(new SkillMessage(skillIndex(sk), getLevel(sk), progressPercentage(sk), expeditionBoost), (EntityPlayerMP)player);
	}

	public void setExperience(final float val, final Skills sk) {
		skillsXp.put(sk, val);

		if (player instanceof EntityPlayerMP)
			AddPackets.network.sendTo(new SkillMessage(skillIndex(sk), getLevel(sk), progressPercentage(sk), expeditionBoost), (EntityPlayerMP)player);
	}

	public void setLevel(final int val, final Skills sk) {
		skillsLvl.put(sk, val);

		if (player instanceof EntityPlayerMP)
			AddPackets.network.sendTo(new SkillMessage(skillIndex(sk), getLevel(sk), progressPercentage(sk), expeditionBoost), (EntityPlayerMP)player);
	}

	public int progressPercentage(final Skills sk) {
		return (int)Math.floor(skillsXp.get(sk) / getExpRequired(sk) * 100.0f);
	}

	public void levelUp(final Skills sk) {
		int level = skillsLvl.get(sk);

		if (level < 100) {
			player.worldObj.playSoundAtEntity(player, "nevermine:LevelUp", 1.1f - (level / 100), 1.0f);

			++level;
			skillsLvl.put(sk, level);
		}

		if (level >= 100) {
			player.worldObj.playSoundAtEntity(player, "nevermine:Level100", 1.3f, 1.0f);
		}

		skillsXp.put(sk, 0.0f);

		if (player instanceof EntityPlayerMP)
			AddPackets.network.sendTo(new SkillMessage(skillIndex(sk), getLevel(sk), progressPercentage(sk), expeditionBoost), (EntityPlayerMP)player);
	}

	public float getExpLeft(final Skills sk) {
		return getExpRequired(sk) - skillsXp.get(sk);
	}

	public Skills getLowestSkillWithLimit(final int limit) {
		Skills lowestSkill = null;
		int lowestVal = 101;

		for (Skills sk : Skills.values()) {
			int temp = skillsLvl.get(sk);

			if (temp >= limit && temp < lowestVal) {
				lowestVal = temp;
				lowestSkill = sk;
			}
		}

		if (lowestVal < limit || lowestVal == 100) {
			return null;
		}
		else {
			return lowestSkill;
		}
	}

	public int getTribute(final Deities deity) {
		return tribute.get(deity);
	}

	public void resetTribute(final Deities deity) {
		tribute.put(deity, 0);

		if (player instanceof EntityPlayerMP)
			AddPackets.network.sendTo(new TributeMessage(getTribute(Selyan), getTribute(Luxon), getTribute(Erebon), getTribute(Pluton)), (EntityPlayerMP)player);
	}

	public void resetAllTribute() {
		for (Deities de : Deities.values()) {
			tribute.put(de, 0);
		}

		if (player instanceof EntityPlayerMP)
			AddPackets.network.sendTo(new TributeMessage(getTribute(Selyan), getTribute(Luxon), getTribute(Erebon), getTribute(Pluton)), (EntityPlayerMP)player);
	}

	public void adjustTribute(final Deities deity, final int amount) {
		int value = Math.max(tribute.get(deity) + amount, 0);

		tribute.put(deity, Math.min(value, 200));

		if (player instanceof EntityPlayerMP)
			AddPackets.network.sendTo(new TributeMessage(getTribute(Selyan), getTribute(Luxon), getTribute(Erebon), getTribute(Pluton)), (EntityPlayerMP)player);
	}

	public void beginRevenge() {
		revengeCounter = 100;

		if (player instanceof EntityPlayerMP)
			AddPackets.network.sendTo(new revengeMessage(true), (EntityPlayerMP)player);
	}

	public boolean revengeActive() {
		return revengeCounter > 0;
	}

	public void revengeEnacted() {
		revengeCounter = 0;
		revengeTarget = null;

		if (player instanceof EntityPlayerMP)
			AddPackets.network.sendTo(new revengeMessage(false), (EntityPlayerMP)player);
	}

	public void setRevengeTarget(final EntityMob target) {
		revengeTarget = target;
	}

	public EntityMob getRevengeTarget() {
		return revengeTarget;
	}

	public void setPortalReturnLocation(final PortalCoordinatesContainer loc) {
		portalReturnLocation = loc;
	}

	public PortalCoordinatesContainer getPortalReturnLocation() {
		return portalReturnLocation;
	}

	@SubscribeEvent
	public void init(final Entity entity, final World world) {
	}

	public static PlayerContainer getProperties(final EntityPlayer pl) {
		return (PlayerContainer)pl.getExtendedProperties("AoAPlayer");
	}

	public static void addProperties(final EntityPlayer pl) {
		pl.registerExtendedProperties("AoAPlayer", new PlayerContainer(pl));
	}

	public enum Skills {
		Anima,
		Augury,
		Butchery,
		Creation,
		Expedition,
		Extraction,
		Foraging,
		Hauling,
		Hermetism,
		Hunter,
		Infusion,
		Innervation,
		Logging,
		Robbery,
		Runation,
		Vulcanism
	}

	public enum Deities {
		Erebon,
		Luxon,
		Pluton,
		Selyan
	}

	private static final Skills[] skillsArray = Skills.values();

	public static int skillIndex(final Skills sk) {
		return sk.ordinal();
	}

	public static Skills getSkillFromIndex(final int index) {
		return skillsArray[index];
	}

	private void doLegacyImport() {
		final NBTTagCompound entityData = player.getEntityData();
		final NBTTagCompound tag = entityData.getCompoundTag("PlayerPersisted");

		if (tag.hasKey("AnLevel")) {
			skillsXp.put(Anima, tag.getFloat("AnExp"));
			skillsLvl.put(Anima, tag.getInteger("AnLevel"));
		}

		if (tag.hasKey("ALevel")) {
			skillsXp.put(Augury, tag.getFloat("AExp"));
			skillsLvl.put(Anima, tag.getInteger("ALevel"));
		}

		if (tag.hasKey("BuLevel")) {
			skillsXp.put(Butchery, tag.getFloat("BuExp"));
			skillsLvl.put(Butchery, tag.getInteger("BuLevel"));
		}

		if (tag.hasKey("CLevel")) {
			skillsXp.put(Creation, tag.getFloat("CExp"));
			skillsLvl.put(Creation, tag.getInteger("CLevel"));
		}

		if (tag.hasKey("ExLevel")) {
			skillsXp.put(Expedition, tag.getFloat("ExExp"));
			skillsLvl.put(Expedition, tag.getInteger("ExLevel"));
			expeditionBoost = tag.getInteger("ExBoost");
		}

		if (tag.hasKey("VtLevel")) {
			skillsXp.put(Extraction, tag.getFloat("VtExp"));
			skillsLvl.put(Extraction, tag.getInteger("VtLevel"));
		}

		if (tag.hasKey("FLevel")) {
			skillsXp.put(Foraging, tag.getFloat("FExp"));
			skillsLvl.put(Foraging, tag.getInteger("FLevel"));
		}

		if (tag.hasKey("HaLevel")) {
			skillsXp.put(Hauling, tag.getFloat("HaExp"));
			skillsLvl.put(Hauling, tag.getInteger("HaLevel"));
		}

		if (tag.hasKey("HeLevel")) {
			skillsXp.put(Hermetism, tag.getFloat("HeExp"));
			skillsLvl.put(Hermetism, tag.getInteger("HeLevel"));
		}

		if (tag.hasKey("HLevel")) {
			skillsXp.put(Hunter, tag.getFloat("HExp"));
			skillsLvl.put(Hunter, tag.getInteger("HLevel"));
		}

		if (tag.hasKey("ILevel")) {
			skillsXp.put(Infusion, tag.getFloat("IExp"));
			skillsLvl.put(Infusion, tag.getInteger("ILevel"));
		}

		if (tag.hasKey("InLevel")) {
			skillsXp.put(Innervation, tag.getFloat("InExp"));
			skillsLvl.put(Innervation, tag.getInteger("InLevel"));
		}

		if (tag.hasKey("LoLevel")) {
			skillsXp.put(Logging, tag.getFloat("LoExp"));
			skillsLvl.put(Logging, tag.getInteger("LoLevel"));
		}

		if (tag.hasKey("RoLevel")) {
			skillsXp.put(Robbery, tag.getFloat("RoExp"));
			skillsLvl.put(Robbery, tag.getInteger("RoLevel"));
		}

		if (tag.hasKey("RuLevel")) {
			skillsXp.put(Runation, tag.getFloat("RuExp"));
			skillsLvl.put(Runation, tag.getInteger("RuLevel"));
		}

		if (tag.hasKey("VuLevel")) {
			skillsXp.put(Vulcanism, tag.getFloat("VuExp"));
			skillsLvl.put(Vulcanism, tag.getInteger("VuLevel"));
		}
	}
}
