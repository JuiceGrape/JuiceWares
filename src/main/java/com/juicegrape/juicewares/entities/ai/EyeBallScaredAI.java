package com.juicegrape.juicewares.entities.ai;

import net.minecraft.entity.ai.EntityAIBase;
import net.minecraft.entity.ai.RandomPositionGenerator;
import net.minecraft.util.Vec3;

import com.juicegrape.juicewares.entities.EntityEyeball;

public class EyeBallScaredAI extends EntityAIBase {
	
	private EntityEyeball runner;
	private int runningTime;
	
	public EyeBallScaredAI(EntityEyeball mob) {
		runner = mob;
		runningTime = 0;
	}
	
	@Override
	public boolean isInterruptible() {
		return false;
	}

	@Override
	public boolean shouldExecute() {
		return runner.isScared();
	}
	
	@Override
	public boolean continueExecuting() {
		return runningTime < 80;
	}
	
	@Override
	public void startExecuting() {
		runningTime = 0;
		Vec3 vec3 = RandomPositionGenerator.findRandomTarget(runner, 10, 8);
		if (vec3 != null) {
			runner.getNavigator().tryMoveToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord, 1);
		}
	}
	
	@Override
	public void updateTask() {
		runningTime++;
		if (runner.getNavigator().noPath()) {
			Vec3 vec3 = RandomPositionGenerator.findRandomTarget(runner, 10, 8);
			if (vec3 != null) {
				runner.getNavigator().tryMoveToXYZ(vec3.xCoord, vec3.yCoord, vec3.zCoord, 1);
			}
		}
	}
	
	@Override
	public void resetTask() {
		runner.getNavigator().clearPathEntity();
		runningTime = 0;
		runner.setScared(false);
	}

}
