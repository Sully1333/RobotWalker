package robotWalker;

public class Walker {

	public static int[] locArray = new int[] {0,0};
	public static void main(String[] args) {
		
		int[] myInstructions = new int[] {1,2,4,1,5};
		RobotWalker myBot = new RobotWalker();
		myBot.robotWalker(myInstructions);

	}

}
