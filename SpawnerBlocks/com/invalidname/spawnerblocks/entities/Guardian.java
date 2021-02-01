package com.invalidname.spawnerblocks.entities;

import org.newdawn.slick.opengl.Texture;

import com.invalidname.spawnerblocks.SpawnerBlocksMain;

import dangerzone.DamageTypes;
import dangerzone.DangerZone;
import dangerzone.Effects;
import dangerzone.GameModes;
import dangerzone.Player;
import dangerzone.TextureMapper;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.entities.Entity;
import dangerzone.entities.EntityLiving;
import dangerzone.entities.TargetHelper;
import dangerzone.entities.Temperament;
import dangerzone.items.Items;

public class Guardian extends EntityLiving {
	
	//int perimeter = DangerZone.rand.nextInt(16)+8;
	int perimeter = 5;
	int safe_distance = 5;
	double spawnx,spawny,spawnz;
	public float myspeed = 0;
	int attempts = 5;
	
	public boolean isSearching = false;
	boolean toStone = false;
	
	public Guardian(World w){
		super(w);
		maxrenderdist = 130; //in blocks
		this.height = 1.1f;
		this.width = 1.05f;
		uniquename = "SpawnerBlocks:Guardian";
		moveSpeed = 0.15f;
		canBreateUnderWater = true;
		this.searchDistance = 32;
		setMaxHealth(20.0f);
		setHealth(20.0f);
		setDefense(4.0f);
		setAttackDamage(5.0f);
		movefrequency = 45;
		attackRange = 2;
		setExperience(100);
		setCanDespawn(false);
		canFly = true;
		setFlying(true);
		canBreateUnderWater = true;
		isImmuneToFire = true;
		takesFallDamage = false;
		canSwim = false;
		this.enableAvoidEntity(4);
		temperament = Temperament.PASSIVE_AGGRESSIVE;
		enable_hostile = true;
	}
	
	public void doFromSpawner() {
		spawnx = this.posx;
		spawny = this.posy;
		spawnz = this.posz;
		attempts = 5;
		setVarInt(31, attempts);
		setVarFloat(31, (float) spawnx);
		setVarFloat(32, (float) spawny);
		setVarFloat(33, (float) spawnz);
		
		System.out.println(spawnx+" "+spawny+" "+spawnz);
	}
	public void init(){
		super.init();
		eyeheight = 0.55f;
		
		attempts = getVarInt(31);
		spawnx = getVarFloat(31);
		spawny = getVarFloat(32);
		spawnz = getVarFloat(33);
	}
	public boolean shouldAvoidEntity(Entity e) {
		if(e == targetentity)return true;
		return false;
	}
	public void doEntityAction(float deltaT) {	
		
		int keep_trying = 25;
		if(target == null)target = new TargetHelper(posx, posy+3, posz);
		
		
		searchcounter++;
		if(searchcounter >= 10) { //re-target about a second
			searchcounter = 0;
			if(target.targetx-posx < 0.2 && target.targetx-posx > -0.2 && target.targetz-posz < 0.2 && target.targetz-posz > -0.2) {
				if(hurtMe != null && hurtMe.deadflag)hurtMe = null;
				//if(this.world.rand.nextInt(30) == 1)hurtMe = null;
				targetentity = hurtMe;
				if(targetentity == null && attempts >= 0 ) {
					double angle = Math.toRadians(world.rand.nextInt(360));
					target = new TargetHelper(spawnx+perimeter*Math.sin(angle), spawny+3, spawnz+perimeter*Math.cos(angle));
					System.out.println("dddd");
					targetentity = findSomethingToAttack();
					if(targetentity != null) {
						float tdir = (float) Math.atan2(targetentity.posx - posx, targetentity.posz - posz);
				    	if(tdir < 0)
				    		tdir += 360;
						target = new TargetHelper(targetentity, targetentity.posx, targetentity.posy+1, targetentity.posz);
						setAttacking(true);
					}else{
						attempts--;
						setAttacking(false);
					}
					
					setVarInt(31, attempts);
				}
				
				if(targetentity != null) {
					target = new TargetHelper(targetentity, targetentity.posx, targetentity.posy+1, targetentity.posz);
					setAttacking(true);
				}else{
					setAttacking(false);
				}
				
				if(toStone) {
					world.setblock(dimension, (int) (spawnx-0.5), (int) (spawny), (int) (spawnz-0.5), SpawnerBlocksMain.blockguardian.blockID);
					this.deadflag = true;
				}
				if(attempts < 0) {
					target = new TargetHelper(spawnx, spawny, spawnz);
					System.out.println(target.targetx+" "+target.targety+" "+target.targetz);
					targetentity = null;
					toStone = true;
				}
			}
			
		}
		
		/*
		 * See if we should attack!
		 */
		if(targetentity != null && this.world.rand.nextInt(8) == 1){ //about once a second
			if(isAtOptimalDistance(this.getDistanceFromEntity(targetentity))) {
				float dmg = this.getAttackDamage();
				int dt = DamageTypes.HOSTILE;
				targetentity.doAttackFrom(this, dt, dmg);
				this.world.playSound(getAttackSound(), dimension, posx, posy+getHeight()/2, posz, getLivingSoundVolume(), getLivingSoundPitch());	
			} 
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
    	if(tdir < 0)
    		tdir += 360;
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
    	motionx += moveSpeed*speedadjust*Math.sin(tdir) * deltaT;
    	motionz += moveSpeed*speedadjust*Math.cos(tdir) * deltaT;
	}
	
	//we attack all small things!
	public boolean isSuitableTarget(Entity e){
		if(isIgnorable(e))return false;
		if(!CanProbablySeeEntity(e))return false;
		if(e instanceof Guardian)return false;
		if(e.temperament != Temperament.PASSIVE)return true;
		if(e instanceof Player && CanProbablySeeEntity(e))return true;
		
		return true;
	}
	
	public boolean isAtOptimalDistance(double distance) {
		if(distance <= attackRange+targetentity.getWidth()/2+this.getWidth()/2 && distance > 4+targetentity.getWidth()/2+this.getWidth()/2)return true;
		return false;
	}
	
	public boolean isFoodItem(int foodid) {
		return false;
	}
	public String getLivingSound() {
		return null;
	}
	
	public String getHurtSound() {
		return "DangerZone:stonehit";
	}
	
	public String getDeathSound() {
		return "DangerZone:stonebreak";
	}
	
	public void doDeathDrops() {		
		//if(world.rand.nextInt(10)==1)Utils.doDropRand(world, 0, Items.trophyanteater.itemID, 1f, dimension, posx, posy, posz);
		super.doDeathDrops();
	}
	
	//Model calls back out to see what texture to use.
	public Texture getTexture() {
		if(texture == null) {
			//ENTITIES MUST USE TEXTUREMAPPER.GETTEXTURE()!!!!
			texture = TextureMapper.getTexture("spawnerblocksres/entities/"+ "guardian_texture.png");	//this is not fast, so we keep our own pointer!
		}
		return texture;
	}

}
