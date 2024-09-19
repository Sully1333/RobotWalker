package robotWalker;

import java.util.ArrayList;
import java.util.List;

public class RobotWalker {
	// variable to know what axis we are traveling on
	String x_y = "start";
	// have to keep track of location for both x and y axis bc we know only incrementing on one of them at each iteration
	int curX = 0;
	int curY=0;
	int xAxisMoves=0;
	int yAxisMoves=0;
	// this keeps track of which way the cursor would move
	int posNeg = 1;
	List<Integer> locArray = new ArrayList();
	
	// the list of a list of integers ; basically storing each coordinate we have traversed
	List<ArrayList<Integer>> myJourney = new ArrayList<ArrayList<Integer>>();
	
	public void move(int moveHere, int indexLoc) {
		
		int cursor = 0;
		int curMove = 0;
		// identifying if we are moving on which axis based on the index of the instructions
		// i.e. we always know we are moving on the x axis on an odd index and y on an even numbered index
		// posNeg just keeps track of which way we are moving either up/down or left/right
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
		
		
		/*
		 * 
		 * We move at each instruction here. The previous does the hard work to know where we should be going
		 * This moves the position of the cursor based on the instruction of moveHere
		 * This also updates myJourney to store the coordinates OR breaks the loop because we already know we 
		 * visited the current coordinate we are being told to move to.
		 * 
		 */
		while ( cursor < moveHere ) {
			curMove = posNeg;
			if ( x_y == "x" ) {
				curX += curMove;
			} else {
				curY += curMove;
			}
			
			// probably can move this into the above because we are only modifying the x or y axis each iteration; TODO
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
	/*
	 * 
	 * This method always adds the newSpot value to the journey
	 * We already know if we have seen the newSpot in the journey based on the type variable.
	 * The myJourney.clear is necessary to essentially reset the list and only store the current already visited location 
	 * to tell the robotWalker to only report that coordinate.
	 */
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
		
		//report back the last index of the array.
		//if we have already traversed the coordinate it is trying to get to in the move function this list is only 
		//one size long therefore needing the index at 0 to report where we already visited.
		System.out.println(myJourney.get(myJourney.size()-1));
		
	}

	

}
