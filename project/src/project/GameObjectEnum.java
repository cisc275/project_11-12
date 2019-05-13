package project;

public enum GameObjectEnum {
	g1Osprey("o_temp"),
	g2ClapperRail("cr_temp"),
	g1Fish1("striped_bass"),
	g1Fish2("striped_bass"),
	g1Fish3("trout_temp"),
	g1Seaweed("seaweed"),
	g2Food("food"),
	g2Food2("food2"),
	g2Trash("trash"),
	g2Trash2("trash2"),
	g2Fox("fox");

	String imgFileName;
	
	GameObjectEnum(String imgname){
		this.imgFileName = imgname;
	}

	public String getImageFileName() {
		return this.imgFileName;
	}

	public String getFullImagePath() {
		return "images/" + this.imgFileName + ".png";
	}

}