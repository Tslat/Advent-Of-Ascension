package net.nevermine.skill;

import cpw.mods.fml.common.network.simpleimpl.IMessage;
import cpw.mods.fml.common.network.simpleimpl.IMessageHandler;
import cpw.mods.fml.common.network.simpleimpl.MessageContext;
import io.netty.buffer.ByteBuf;
import net.nevermine.skill.anima.animaRenderer;
import net.nevermine.skill.augury.auguryRenderer;
import net.nevermine.skill.butchery.butcheryRenderer;
import net.nevermine.skill.creation.creationSkillRenderer;
import net.nevermine.skill.expedition.expeditionRenderer;
import net.nevermine.skill.extraction.extractionRenderer;
import net.nevermine.skill.foraging.foragingRenderer;
import net.nevermine.skill.hauling.haulingRenderer;
import net.nevermine.skill.hermetism.hermetismRenderer;
import net.nevermine.skill.hunter.hunterRenderer;
import net.nevermine.skill.infusion.infusionRenderer;
import net.nevermine.skill.innervation.innervationRenderer;
import net.nevermine.skill.logging.loggingRenderer;
import net.nevermine.skill.robbery.robberyRenderer;
import net.nevermine.skill.runation.runationRenderer;
import net.nevermine.skill.vulcanism.vulcanismRenderer;

public class SkillMessage implements IMessage {
	private int skill;
	private int level;
	private int barProgressPercent;
	private int optional;

	public SkillMessage() {
	}

	public SkillMessage(final int skillIndex, final int lvl, final int percent, final int opt) {
		skill = skillIndex;
		level = lvl;
		barProgressPercent = percent;
		optional = opt;
	}

	public void fromBytes(final ByteBuf buf) {
		skill = buf.readInt();
		level = buf.readInt();
		barProgressPercent = buf.readInt();
		optional = buf.readInt();
	}

	public void toBytes(final ByteBuf buf) {
		buf.writeInt(skill);
		buf.writeInt(level);
		buf.writeInt(barProgressPercent);
		buf.writeInt(optional);
	}

	public static class Handler implements IMessageHandler<SkillMessage, IMessage> {
		public IMessage onMessage(final SkillMessage msg, final MessageContext ctx) {
			switch (msg.skill) {
				case 0:
					animaRenderer.value = msg.level;
					animaRenderer.percent = msg.barProgressPercent;
					break;
				case 1:
					auguryRenderer.value = msg.level;
					auguryRenderer.percent = msg.barProgressPercent;
					break;
				case 2:
					butcheryRenderer.value = msg.level;
					butcheryRenderer.percent = msg.barProgressPercent;
					break;
				case 3:
					creationSkillRenderer.value = msg.level;
					creationSkillRenderer.percent = msg.barProgressPercent;
					break;
				case 4:
					expeditionRenderer.value = msg.level;
					expeditionRenderer.percent = msg.barProgressPercent;
					expeditionRenderer.power = msg.optional;
					break;
				case 5:
					extractionRenderer.value = msg.level;
					extractionRenderer.percent = msg.barProgressPercent;
					break;
				case 6:
					foragingRenderer.value = msg.level;
					foragingRenderer.percent = msg.barProgressPercent;
					break;
				case 7:
					haulingRenderer.value = msg.level;
					haulingRenderer.percent = msg.barProgressPercent;
					break;
				case 8:
					hermetismRenderer.value = msg.level;
					hermetismRenderer.percent = msg.barProgressPercent;
					break;
				case 9:
					hunterRenderer.value = msg.level;
					hunterRenderer.percent = msg.barProgressPercent;
					break;
				case 10:
					infusionRenderer.value = msg.level;
					infusionRenderer.percent = msg.barProgressPercent;
					break;
				case 11:
					innervationRenderer.value = msg.level;
					innervationRenderer.percent = msg.barProgressPercent;
					break;
				case 12:
					loggingRenderer.value = msg.level;
					loggingRenderer.percent = msg.barProgressPercent;
					break;
				case 13:
					robberyRenderer.value = msg.level;
					robberyRenderer.percent = msg.barProgressPercent;
					break;
				case 14:
					runationRenderer.value = msg.level;
					runationRenderer.percent = msg.barProgressPercent;
					break;
				case 15:
					vulcanismRenderer.value = msg.level;
					vulcanismRenderer.percent = msg.barProgressPercent;
					break;
				default:
					break;
			}
			return null;
		}
	}
}