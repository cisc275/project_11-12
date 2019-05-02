package project;

public enum GameObjectEnum {
	g1Osprey("o_temp"),
	g2ClapperRail("cr_temp"),
	g1Fish1("striped_bass"),
	g1Fish2("striped_bass"),
	g1Fish3("striped_bass"),
	g1Seaweed("seaweed"),
	g2Food("striped_bass"),
	g2Trash("seaweed");

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