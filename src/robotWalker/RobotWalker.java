package robotWalker;

import java.util.ArrayList;
import java.util.List;

public class RobotWalker {
	String x_y = "start";
	int curX = 0;
	int curY=0;
	int xAxisMoves=0;
	int yAxisMoves=0;
	int posNeg = 1;
	List<Integer> locArray = new ArrayList();
	List<ArrayList<Integer>> myJourney = new ArrayList<ArrayList<Integer>>();
	
	public void move(int moveHere, int indexLoc) {
		
		int cursor = 0;
		int curMove = 0;
		if ( indexLoc % 2 == 0 ) {
			x_y = "y";
			if( yAxisMoves++ % 2 == 0 ) {
				posNeg = 1;
			} else {
				posNeg = -1;
			}
		} else {
			x_y = "x";
			if( xAxisMoves++ % 2 == 0 ) {
				posNeg = 1;
			} else {
				posNeg = -1;
			}
		}
		
		while ( cursor < moveHere ) {
			curMove = posNeg;
			if ( x_y == "x" ) {
				curX += curMove;
			} else {
				curY += curMove;
			}
			locArray.add(curX);
			locArray.add(curY);
			if ( myJourney.contains(locArray)) {
				updateJourney(new ArrayList(locArray),1);
				break;
			}			
			
			updateJourney(new ArrayList(locArray),0);
			
			locArray.clear();
			cursor++;
		}
		
	}
	
	public void updateJourney(List<Integer> newSpot, int type) {
		if ( type > 0 ) {
			myJourney.clear();
		}
		myJourney.add(new ArrayList(newSpot));
	}
	
	//class construct
	public RobotWalker() {
		locArray.add(0);
		locArray.add(0);
		updateJourney(new ArrayList(locArray),0);
		locArray.clear();
	}
	
	public void robotWalker(int[] myInstructions) {
		
		int instructionCount = 0;
		for (int move : myInstructions ) {
			move(move,instructionCount++);
		}
		System.out.println(myJourney.get(myJourney.size()-1));
		
	}

	

}
