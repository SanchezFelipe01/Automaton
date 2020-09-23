package model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

public class Automaton {
	
	private String type;
	private ArrayList<State> states;
	private char[] stimuli;
	private char[] outputs;

	private HashMap<State, Integer> index;
	
	public Automaton(String type, ArrayList<State> states, char[] stimuli, char[] outputs) {
		
		this.type = type;
		this.states = states;
		this.stimuli = stimuli;
		this.outputs = outputs;
		index = new HashMap<>();
		initialize_index();
		
	}

	private void initialize_index(){
		for (int i = 0; i < states.size(); i++) {
			index.put(states.get(i), i);
		}
	}

	public String getType(){
		return type;
	}

	public ArrayList<State> getStates(){
		return states;
	}

	public char[] getStimuli(){
		return stimuli;
	}

	public char[] getOutputs(){
		return outputs;
	}

	public ArrayList<State> getConnected(){
		dfs();
		ArrayList<State> connectedStates = new ArrayList<>();
		for (State s : states) {
			if (s.getVisited()) {
				connectedStates.add(s);
			}
		}

		return connectedStates;
	}

	private void dfs(){

		for (State s : states) {
			s.setVisited(false);
		}

		Stack<State> stack = new Stack<State>();
		boolean[] visited = new boolean[states.size()];
		State start = states.get(0);
		stack.push(start);

		while (!stack.isEmpty()){
			State current = stack.pop();
			int ind = index.get(current);
			visited[ind] = true;
			states.get(ind).setVisited(true);

			for (State s : current.getSuccessorStates()) {
				if (!visited[index.get(s)]) {
					stack.push(s);
				}
			}
			
		}
	}
	
	public ArrayList<ArrayList<State>> getFirstPartition(){

		ArrayList<State> cState = getConnected();

		for (State state : cState) {
			state.setVisited(false);
		}

		ArrayList<ArrayList<State>> list = new ArrayList<>();
		ArrayList<State> block;
		String c1;
		String c2;

		for (int i = 0; i < cState.size()-1; i++) {
			
			if (!cState.get(i).getVisited()) {
				
				block = new ArrayList<>();
				block.add(cState.get(i));
				cState.get(i).setVisited(true);

				for (int j = i+1; j < cState.size(); j++) {
					if (!cState.get(j).getVisited()) {
						
						c1 = String.valueOf(cState.get(i).getResponses());
						c2 = String.valueOf(cState.get(j).getResponses());

						if (c1.equals(c2)) {
							block.add(cState.get(j));
							cState.get(j).setVisited(true);
						}

					}
				}

				list.add(block);

			}


		}

		return list;
	}

}
