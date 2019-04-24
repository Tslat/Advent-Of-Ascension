package net.nevermine.event.player;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import cpw.mods.fml.common.gameevent.InputEvent;
import net.nevermine.assist.AddPackets;
import net.nevermine.assist.binding.*;
import net.nevermine.skill.expedition.reverseExpeditionMessage;

public class KeyPressEvent {
	public static boolean skills;
	public static boolean cinfo;
	public static int sshow;
	public static boolean ShowMessage;
	public static boolean ShowNotice;

	@SubscribeEvent
	public void onKeyPressed(final InputEvent.KeyInputEvent evt) {
		if (CreatureInfoBinding.cinfo.isPressed()) {
			KeyPressEvent.cinfo = !KeyPressEvent.cinfo;
		}
		if (SkillShowBinding.skills.isPressed()) {
			KeyPressEvent.skills = !KeyPressEvent.skills;
			KeyPressEvent.ShowMessage = false;
		}
		if (ExpeditionBinding.exped.isPressed()) {
			AddPackets.network.sendToServer(new reverseExpeditionMessage());
		}
		if (SkillNameBinding.skillnamesleft.isPressed()) {
			--KeyPressEvent.sshow;
			if (KeyPressEvent.sshow == -1) {
				KeyPressEvent.sshow = 16;
			}
		}
		if (SkillNameBinding.skillnamesright.isPressed()) {
			++KeyPressEvent.sshow;
			if (KeyPressEvent.sshow == 17) {
				KeyPressEvent.sshow = 0;
			}
		}
		if (LoginNoticeBinding.lognot.isPressed()) {
			KeyPressEvent.ShowNotice = false;
		}
	}

	static {
		KeyPressEvent.skills = false;
		KeyPressEvent.cinfo = true;
		KeyPressEvent.sshow = 0;
		KeyPressEvent.ShowMessage = true;
		KeyPressEvent.ShowNotice = true;
	}
}
