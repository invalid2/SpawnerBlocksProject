package com.invalidname.spawnerblocks.entities;

import org.newdawn.slick.opengl.Texture;

import com.invalidname.spawnerblocks.SpawnerBlocksMain;

import dangerzone.DamageTypes;
import dangerzone.Effects;
import dangerzone.TextureMapper;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.entities.Entity;
import dangerzone.entities.EntityLiving;
import dangerzone.entities.TargetHelper;
import dangerzone.entities.Temperament;

public class Dracorat extends EntityLiving {
	
	public float myspeed = 0;
	
	public Dracorat(World w) {
		super(w);
		uniquename = "SpawnerBlocks:Dracorat";
		maxrenderdist = 128;
		this.height = 0.35f;
		this.width = 0.45f;
		moveSpeed = 0.25f;
		setMaxHealth(5.0f);
		setHealth(5.0f);
		setDefense(1.25f);
		setAttackDamage(1.0f);
		searchDistance = 16f;
		attackRange = 1.5f;
		movefrequency = 25;
		setExperience(10);
		canFly = true;
		setFlying(true);
		isImmuneToFire = true;
		takesFallDamage = false;
		temperament = Temperament.HOSTILE;
		enable_hostile = true;
		daytimespawn = false;
		daytimedespawn = true;
		nighttimespawn = true;
		nighttimedespawn = false;
		canridemaglevcart = true;
		tower_defense_enable = true;
	}
	
	public boolean isSuitableTarget(Entity e) {
		if(isIgnorable(e))return false;
		if(e instanceof Dracorat)return false;
		if(CanProbablySeeEntity(e))return true;
		//if(e.getWidth()*e.getHeight() > 0.063f)return true;
		
		return false;
	}
	
	public void doDeathDrops() {
		if(world.rand.nextInt(8) == 0) {
			Utils.doDropRand(world, 0, SpawnerBlocksMain.dracwing.itemID, 0f, dimension, posx, posy, posz);
		} else {
			int howmany = world.rand.nextInt(3);
			int i;
			for(i=0;i<howmany;i++){
				Utils.doDropRand(world, 0, world.rand.nextInt(2) == 0? SpawnerBlocksMain.dracmembrane.itemID : SpawnerBlocksMain.dracscale.itemID, 0f, dimension, posx, posy, posz);
			}
			
		}
		
		
		if(world.rand.nextInt(5)==1)Utils.doDropRand(world, 0, SpawnerBlocksMain.trophydracorat.itemID, 1f, dimension, posx, posy, posz);
		
		super.doDeathDrops();
	}
	
	public void doEntityAction(float deltaT){	
		int bid;
		int keep_trying = 25;
		
		if(target == null)target = new TargetHelper(posx, posy, posz);
		
		do_swarm_despawn();
		
		/*
		 * See if there is something around to chase...
		 */
		searchcounter++;
		if(searchcounter >= 10){ //re-target about a second
			searchcounter = world.rand.nextInt(4);
			if(hurtMe != null && hurtMe.deadflag)hurtMe = null;
			if(this.world.rand.nextInt(30) == 1)hurtMe = null;
			targetentity = hurtMe;
			if(targetentity == null)targetentity = findSomethingToAttack();
			if(targetentity != null){
				//System.out.printf("attackable entity found!\n");
				target = new TargetHelper(targetentity, targetentity.posx, targetentity.posy+1, targetentity.posz);
				setAttacking(true);
			}else{
				setAttacking(false);
			}
		}			
		/*
		 * See if we should attack!
		 */
		if(targetentity != null && this.world.rand.nextInt(8) == 1){ //about once a second
			if(this.getDistanceFromEntity(targetentity) < attackRange+targetentity.getWidth()/2+this.getWidth()/2){
				float dmg = this.getAttackDamage();
				int dt = DamageTypes.HOSTILE;
				targetentity.doAttackFrom(this, dt, dmg);
				this.world.playSound(getAttackSound(), dimension, posx, posy+getHeight()/2, posz, getLivingSoundVolume(), getLivingSoundPitch());	
			}
		}
		
		//make some noise!
		if(!this.isHurt() && !deadflag){
			if(this.world.rand.nextInt(50) == 1){
				this.world.playSound(getLivingSound(), dimension, posx, posy+getHeight()/2, posz, getLivingSoundVolume(), getLivingSoundPitch());					
			}				
		}
				
		//bounce off most objects, like trees
		bid = this.world.getblock(this.dimension, (int)this.target.targetx, (int)this.target.targety, (int)this.target.targetz);
		if(bid == 0){
			if(wouldBump(posx + motionx*deltaT, posy + motiony*deltaT + getHeight()/2, posz + motionz*deltaT, getWidth()))bid = 1;
		}
		
    	if(bid != 0 || this.world.rand.nextInt(50) == 1 || this.target.getDistanceToTarget(posx, posy, posz) < 2.0f)
    	{
    		int updown = 5;
    		//stay around 10 from ground...
			for(int k=1;k<10;k++){
				bid = world.getblock(dimension, (int)this.posx, (int)this.posy-k, (int)this.posz);
				if(bid != 0){
					updown = -1; //go up!
					break;
				}
			}			
			//find a new empty space we can go to
    		bid = 1;
    		while(bid != 0 && keep_trying != 0){
    			if(isSwarming()){
    				//fly in a +x and +z direction for a passing swarm...
    				this.target.setTarget(posx + this.world.rand.nextInt(15), posy + (this.world.rand.nextInt(7) - updown), posz + this.world.rand.nextInt(15));
    			}else{
    				this.target.setTarget(posx + this.world.rand.nextInt(15) - this.world.rand.nextInt(15), posy + (this.world.rand.nextInt(7) - updown), posz + this.world.rand.nextInt(15) - this.world.rand.nextInt(15));
    			}
    			bid = this.world.getblock(this.dimension, (int)this.target.targetx, (int)this.target.targety, (int)this.target.targetz);
    			if(bid == 0){
    				//try not to go through walls.
    				if(!CanProbablySee(dimension, target.targetx, target.targety, target.targetz, (int)Math.sqrt((posx-target.targetx)*(posx-target.targetx) + (posz-target.targetz)*(posz-target.targetz) + (posy-target.targety)*(posy-target.targety))))bid = 1;
    			}
    			keep_trying--;
    			myspeed = 0.35f + world.rand.nextFloat()*0.10f;
    		}
    		//System.out.printf("kt = %d\n", keep_trying);
    	}
    	
    	bid = this.world.getblock(this.dimension, (int)this.target.targetx, (int)this.target.targety, (int)this.target.targetz);
    	if(bid != 0){
    		this.target.setTarget(posx + (this.world.rand.nextInt(5) - this.world.rand.nextInt(5)), posy + (this.world.rand.nextInt(5)+3), posz + (this.world.rand.nextInt(5) - this.world.rand.nextInt(5)));
    	}
    	
    	//Now rotate into direction we should be heading, and update motion!
    	float dy = (float) (target.targety - this.posy);
    	motiony += 0.120f * deltaT; //Counter a little gravity!
    	dy = dy/20f;
		if(dy > 0.20f)dy = 0.20f;
		if(dy < -0.20f)dy = -0.20f;
		motiony += dy;
		if(motiony > 1)motiony = 1;
		if(motiony < -1)motiony = -1;
    	
    	float cdir = (float) Math.toRadians(rotation_yaw);
    	float tdir = (float) Math.atan2(target.targetx - posx, target.targetz - posz);
    	float ddiff = tdir - cdir;
    	while(ddiff>Math.PI)ddiff -= Math.PI*2;
    	while(ddiff<-Math.PI)ddiff += Math.PI*2;
    	rotation_yaw_motion += (ddiff*180f/Math.PI)/10f;
    	
    	float speedadjust = 1;
    	float effectspeed;
    	effectspeed = this.getTotalEffect(Effects.SPEED);
		if(effectspeed > 1){
			speedadjust *= effectspeed;
		}
		effectspeed = this.getTotalEffect(Effects.SLOWNESS);
		if(effectspeed > 1){
			speedadjust /= effectspeed;
		}
    	motionx += myspeed*speedadjust*Math.sin(tdir) * deltaT;
    	motionz += myspeed*speedadjust*Math.cos(tdir) * deltaT;
    	Utils.SpawnDustAlongPath(this);

	}
	
	public Texture getTexture(){
		if(texture == null)
			texture = TextureMapper.getTexture("spawnerblocksres/entities/"+ "dracorat_texture.png");
		return texture;
	}

}
