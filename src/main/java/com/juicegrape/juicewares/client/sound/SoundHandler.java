package com.juicegrape.juicewares.client.sound;

import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class SoundHandler {
	
	@ForgeSubscribe
	public void onSound(SoundLoadEvent event) {
		// You add them the same way as you add blocks.
		event.manager.addSound("juicewares:eyeballhit1.ogg");
		event.manager.addSound("juicewares:eyeballhit2.ogg");
		event.manager.addSound("juicewares:timeclock.ogg");
	}

}
