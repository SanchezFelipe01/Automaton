package userInterface;

import java.util.ArrayList;

import model.Automaton;
import model.State;

public class Main {

	public static void main(String[] args) {
		
		ArrayList<State> states = new ArrayList<>();
		/*
		char[] c1 = new char[]{'0', '1', '2'};
		char[] c2 = new char[]{'0', '1', '2'};

		State d = new State("D", c1);
		State e = new State("E", c2);
		State f = new State("F");
		State g = new State("G");
		State h = new State("H");

		d.addSuccessor(e);
		d.addSuccessor(d);
		e.addSuccessor(d);
		e.addSuccessor(f);
		f.addSuccessor(f);
		f.addSuccessor(d);
		g.addSuccessor(e);
		g.addSuccessor(h);
		h.addSuccessor(d);
		h.addSuccessor(g);

		states.add(d);
		states.add(e);
		states.add(f);
		states.add(g);
		states.add(h);
		
		
		State d = new State("P1");
		State e = new State("P2");
		State f = new State("P3");
		State g = new State("P4");
		State h = new State("P5");
		State i = new State("P6");

		d.addSuccessor(f);
		d.addSuccessor(g);
		e.addSuccessor(h);
		e.addSuccessor(f);
		f.addSuccessor(g);
		f.addSuccessor(h);
		g.addSuccessor(f);
		g.addSuccessor(g);
		h.addSuccessor(e);
		h.addSuccessor(h);
		i.addSuccessor(i);
		i.addSuccessor(g);

		states.add(d);
		states.add(e);
		states.add(f);
		states.add(g);
		states.add(h);
		states.add(i);
		*/

		char[] c1 = new char[]{'0'};
		char[] c2 = new char[]{'1'};

		State a = new State("A", c1);
		State b = new State("B", c1);
		State c = new State("C", c1);
		State d = new State("D", c1);
		State e = new State("E", c1);
		State f = new State("F", c1);
		State g = new State("G", c1);
		State h = new State("H", c1);
		State i = new State("I", c2);
		State j = new State("J", c1);
		State k = new State("K", c2);

		a.addSuccessor(b);
		a.addSuccessor(a);
		b.addSuccessor(c);
		b.addSuccessor(d);
		c.addSuccessor(e);
		c.addSuccessor(c);
		d.addSuccessor(f);
		d.addSuccessor(b);
		e.addSuccessor(g);
		e.addSuccessor(e);
		f.addSuccessor(h);
		f.addSuccessor(f);
		g.addSuccessor(i);
		g.addSuccessor(g);
		h.addSuccessor(j);
		h.addSuccessor(h);
		i.addSuccessor(a);
		i.addSuccessor(k);
		j.addSuccessor(k);
		j.addSuccessor(i);
		k.addSuccessor(a);
		k.addSuccessor(k);

		states.add(a);
		states.add(b);
		states.add(c);
		states.add(d);
		states.add(e);
		states.add(f);
		states.add(g);
		states.add(h);
		states.add(i);
		states.add(j);
		states.add(k);
		


		char[] stimuli = new char[]{'0', '1'};
		char[] outputs = new char[]{'0', '1'};

		Automaton auto = new Automaton("type", states, stimuli, outputs);
		
		ArrayList<ArrayList<State>> list = auto.getFirstPartition();
		ArrayList<State> block;

		for (int n = 0; n < list.size(); n++) {
			block = list.get(n);

			for (int l = 0; l < block.size(); l++) {
				System.out.print(block.get(l).getName() + block.get(l).getBlock() + " ");
			}

			System.out.println();

		}
		

	}

}
