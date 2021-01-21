package com.invalidname.spawnerblocks.entities;

import org.newdawn.slick.opengl.Texture;

import com.invalidname.spawnerblocks.SpawnerBlocksMain;

import dangerzone.Player;
import dangerzone.TextureMapper;
import dangerzone.Utils;
import dangerzone.World;
import dangerzone.blocks.Blocks;
import dangerzone.entities.Entity;
import dangerzone.entities.EntityLiving;
import dangerzone.entities.Moose;
import dangerzone.entities.Temperament;
import dangerzone.items.Items;

public class Impostor extends EntityLiving {
	
	Entity target_entity = null;
	boolean isRecovering = false;
	boolean hasExtraGui = false;
	boolean enable_eat_drops = false;
	
	int start_recover = 20;
	int exit_recover = 8;
	
	public	Impostor(World w) {
		super(w);
		maxrenderdist = 180; //in blocks
		this.height = 3.75f;
		this.width = 1.33f;
		uniquename = "SpawnerBlocks:Impostor";
		moveSpeed = 0.33f;
		setMaxHealth(150.0f);
		setHealth(150.0f);
		setDefense(2.5f);
		setAttackDamage(20.0f);
		searchDistance = 16f;
		attackRange = 3.5f;
		movefrequency = 65;
		setExperience(200);
		canSwim = true;
		daytimespawn = true;
		nighttimespawn = true;
		setCanDespawn(false);
		temperament = Temperament.PASSIVE;
		enable_hostile = true;
		enable_findfoodblock = true;
		foodblockfreq = 60;
		foodblockdistxz = 16;
		foodblockdisty = 3;
		foodblockdisteat = 8;
		foodblockheal = 5;
		isMilkable = true;
		tower_defense_enable = true;

	}
	
	public void init() {
		super.init();
		
		//Properties prop = new Properties();
		
		//try {
		//	prop.load( new FileInputStream("./extraguiconfig.properties"));
		//	if(Boolean.parseBoolean(prop.getProperty("mobprofiler")))
		//		hasExtraGui = true;
			
		//} catch (IOException e) {
		//	hasExtraGui = false;
		//}
	}
	
	public boolean shouldAvoidEntity(Entity e) {
		if(isRecovering) {
			if(e == target_entity)return true; 
		}
		return false;
	}
	
	public boolean isFoodBlock(int bid) {
		if(bid == Blocks.grass.blockID)return true;
		return false;
	}
	
	public String getLivingSound() {
		return null;
	}
	
	public String getHurtSound() {
		return "DangerZone:moose_hit";
	}
	
	public String getDeathSound() {
		
		return getImpostorDeathSound("DangerZone:moose_death");
	}
	
	public String getImpostorDeathSound(String unique) {
		int rand = world.rand.nextInt(16);
		
		if(rand == 0) {
			if(world.rand.nextInt(2) == 0) {
				return "SpawnerBlocks:impostor1";
			} else {
				return "SpawnerBlocks:impostor2";
			}
		}
		return unique;
	}
	
	public void doDeathDrops() {
		int howmany = 5+world.rand.nextInt(4);
		int i;
		for(i=0;i<howmany;i++){
			Utils.doDropRand(world, 0, Items.moosemeat.itemID, 4f, dimension, posx, posy, posz);
		}
		howmany = 1+world.rand.nextInt(4);
		for(i=0;i<howmany;i++){
			Utils.doDropRand(world, 0, Items.moosebone.itemID, 3f, dimension, posx, posy, posz);
		}
		
		if(world.rand.nextInt(5)==1)Utils.doDropRand(world, 0, SpawnerBlocksMain.trophyimpostor.itemID, 1f, dimension, posx, posy, posz);
		if(world.rand.nextInt(5)==1)Utils.doDropRand(world, 0, Items.moosehead.itemID, 1f, dimension, posx, posy, posz);
		super.doDeathDrops();
	}
	
	//Override, because we attack hostiles that are NOT moose!
	public boolean isSuitableTarget(Entity e) {
		
		if(isIgnorable(e))return false;
		if(e instanceof Impostor)return false;
		if(e instanceof Moose)return false;
		
		if(CanProbablySeeEntity(e) && target_entity == null && !this.isInPOV(e) && !isRecovering) {
			this.temperament = Temperament.HOSTILE;
			target_entity = e;
			return true;
		}
		if(target_entity != null && this.getDistanceFromEntity(target_entity) < this.searchDistance) {
			this.temperament = Temperament.HOSTILE;
			return true;
		}
		if(target_entity != null && this.getDistanceFromEntity(target_entity) > this.searchDistance) {
			this.temperament = Temperament.PASSIVE;
			target_entity = null;
			return false;
		}
		return false;
	}
	
	//Model calls back out to see what texture to use.
	public Texture getTexture() {
		if(texture == null){
			//ENTITIES MUST USE TEXTUREMAPPER.GETTEXTURE()!!!!
			texture = TextureMapper.getTexture("res/skins/"+ "Moosetexture.png");	//this is not fast, so we keep our own pointer!
		}
		return texture;
	}
	
	public boolean isInPOV(Entity e) {
		
		float cdir;
		float tdir;	
		float ddiff;
		if(e.rotation_yaw > 180) {
			cdir = (float) Math.toRadians(e.rotation_yaw-360);
			tdir = (float) Math.atan2(e.posx - posx, e.posz - posz);	
			ddiff = (float) (Math.abs(tdir - cdir) % (Math.PI*2));
		} else {
			cdir = (float) Math.toRadians(e.rotation_yaw);
			tdir = (float) Math.atan2(e.posx - posx, e.posz - posz);	
			ddiff = (float) (Math.abs(tdir - cdir) % (Math.PI*2));
		}
		
		//System.out.println(tdir);
		//System.out.println("yaw"+e.rotation_yaw+" cdir"+cdir+", tdir:"+tdir+", ddiff:"+ddiff);
		if(ddiff > Math.PI/3 && ddiff < Math.PI*7/6) {
			//System.out.println("seeing me?");
			return true;
		}
		
		//e.rotation_yaw =0;
		
		return false;
		
	}
	
	public void do_something_else() {
		//If our health is too low then its time to lay down when recover...
		if(this.getHealth() < start_recover && !(target_entity instanceof Player)) {
			//Set to passive so no other mobs attack us
			this.temperament = Temperament.PASSIVE;
			//Stop attacking what we were attacking!
			isRecovering = true;
		}
		
		if(isRecovering) {
			//The higher our the easier it is for us to get out of recovery mode
			if(getHealth() > 0) {
				if(world.rand.nextInt((int) Math.ceil(getHealth()/10)) < exit_recover) {
					//Buff food recovery a little
					foodblockheal = 10;
					//Go find food!
					if(world.rand.nextInt(4) == 0)
						findFood();
				} else {
					//We are ready to fight again!
					foodblockheal = 5;
					isRecovering = false;
				}
			}
				
		}
		//System.out.println(hasExtraGui);
	}
	
	public void findFood() {
		if(this.enable_findfoodblock) {
			this.findBlockFood(this.foodblockdistxz, this.foodblockdisty, this.foodblockheal, this.foodblockdisteat);
		} 
		if(this.enable_droppedfood) {
			this.do_hungry();
		}
	}

}
