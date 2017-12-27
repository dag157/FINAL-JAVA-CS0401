package proj5;

public class SoccerPlayer {

	private String name;
	private int goals = 0;
	
	//PLAYER CONSTRUCTOR
	public SoccerPlayer(String name) {
		this.name = name;
	}
	
	//TOTAL GOALS STATIC CLASS
	private static int goalsTotal = 0; 
	
	//GRABS GOALS OF PLAYER
	public int getgoals(){
		return goals;
	}
	
	//ADDS GOALS OF A PLAYER
	public void setGoals(int g) {
		goals = goals + g;
	}
	
	//REMOVE GOALS FROM  TOTAL NOT USED
	public void removeGoalsFromTotal(int e) {
		//goalsTotal -= e;
	}
	
	//SET GOALS OF A PLAYER
	public void settGoals(int h) {
		goals = h;
		
	}
	
	//GET TOTAL GOALS OF ALL PLAYERS
	public static int getTotal() {
		return goalsTotal;
	}
	
	//GET NAME OF A PLAYER
	public String getName() {
		return name;
	}
	
	//SET NAME OF A PLAYER
	public void setName(String s) {
		name = s;
		
	}

	
}
