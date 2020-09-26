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
	
	public ArrayList<ArrayList<State>> getFirstPartition(){ //Cambiar a private

		ArrayList<State> cState = getConnected();

		for (State state : cState) {
			state.setVisited(false);
		}

		ArrayList<ArrayList<State>> list = new ArrayList<>();
		ArrayList<State> block;
		String c1;
		String c2;
		int count = 0;

		for (int i = 0; i < cState.size()-1; i++) {
			
			if (!cState.get(i).getVisited()) {

				cState.get(i).setVisited(true);
				cState.get(i).setBlock(count);
				cState.get(i).changeBlocks();
				block = new ArrayList<>();
				block.add(cState.get(i));
				

				for (int j = i+1; j < cState.size(); j++) {
					if (!cState.get(j).getVisited()) {
						
						c1 = String.valueOf(cState.get(i).getResponses());
						c2 = String.valueOf(cState.get(j).getResponses());

						if (c1.equals(c2)) {
							cState.get(j).setVisited(true);
							cState.get(j).setBlock(count);
							cState.get(j).changeBlocks();
							block.add(cState.get(j));
						}

					}
				}

				list.add(block);
				count++;
			}


		}

		return list;
	}

	public ArrayList<ArrayList<State>> getFinalPartition(){

		ArrayList<ArrayList<State>> list1 = getFirstPartition();
		ArrayList<ArrayList<State>> list2 = new ArrayList<>();

		//int count;
		ArrayList<State> newBlocks;
		boolean exit1 = true;
		int iteraciones = 1;

		while (exit1) {
			
			int count = 0;

			for (ArrayList<State> arrayList : list1) {
				for (State s : arrayList){
					s.setVisited(false);
					s.changeBlocks();
					System.out.println("----------------------");
					System.out.println(s.getName() + " " + s.getPrevBlock());
					System.out.println("----------------------");
				}
			}

			System.out.println("Empezando iteracion: " + iteraciones);
			int iteraciones2 = 1;
			for (ArrayList<State> block : list1) {
				
				System.out.println("Bloque " + iteraciones2 + " de particion " + iteraciones);
				for (int i = 0; i < block.size(); i++) {
					//System.out.println("i = " +i);
					
					if (block.get(i).getVisited() == false) {
						System.out.println("Verificando estado: " + block.get(i).getName());
						block.get(i).setVisited(true);
						block.get(i).setBlock(count);
						newBlocks = new ArrayList<>();
						newBlocks.add(block.get(i));

						for (int j = i+1; j < block.size(); j++) {
							
							if (block.get(j).getVisited() == false) { // Solo funciona con dos estados sucesores
								System.out.println("Comparando estado " + block.get(i).getName() + " con estado " + block.get(j).getName());
								int i1 = block.get(i).getSuccessorStates().get(0).getPrevBlock();
								int i2 = block.get(i).getSuccessorStates().get(1).getPrevBlock();
								int	j1 = block.get(j).getSuccessorStates().get(0).getPrevBlock();
								int	j2 = block.get(j).getSuccessorStates().get(1).getPrevBlock();
								System.out.println("Estado " + block.get(i).getName() + " = " + block.get(i).getSuccessorStates().get(0).getName() + i1 + " " + block.get(i).getSuccessorStates().get(1).getName() + i2);
								System.out.println("Estado " + block.get(j).getName() + " = " + block.get(j).getSuccessorStates().get(0).getName() + j1 + " " + block.get(j).getSuccessorStates().get(1).getName() + j2);
								if (i1 == j1 && i2 == j2) {
									System.out.println(block.get(i).getName() + " y " + block.get(j).getName() + " estan en la misma particion");
									//block.get(i).setBlock(count);
									block.get(j).setBlock(count);
									block.get(j).setVisited(true);
									newBlocks.add(block.get(j));

								}else System.out.println(block.get(i).getName() + " y " + block.get(j).getName() + " NO estan en la misma particion");

							}

						}
		
						list2.add(newBlocks);
						count++;

					}else System.out.println("Estado " + block.get(i).getName() + " visitado");

				}
				System.out.println("Fin de Bloque " + iteraciones2 + " de particion " + iteraciones);
				iteraciones2++;

			}

			System.out.println("Fin particion " + iteraciones);
			iteraciones++;
			
			//System.out.println(printPartition(list1));
			System.out.println(printPartition(list2));

			if (!list1.equals(list2)) {
				
				list1 = new ArrayList<>(list2);
				list2 = new ArrayList<>();
				//flag = true;
			}else{
				exit1 = false;
			}


		}


		return list2;
	} 

	private String printPartition(ArrayList<ArrayList<State>> list){

		String message = "{";

		for (ArrayList<State> block : list) {
			message += "{";
			for (State state : block) {
				message += state.getName() + state.getBlock();
			}
			message += "}";
		}

		message += "}";

		return message;
	}

	private String printSuccessorBlock(State s){
		
		String m = s.getName() + " = {" + s.getSuccessorStates().get(0).getName() + " " + s.getSuccessorStates().get(0).getBlock() + "}, ";
		m += "{" + s.getSuccessorStates().get(1).getName() + " " + s.getSuccessorStates().get(1).getBlock() + "}";

		return m;
	}

}
