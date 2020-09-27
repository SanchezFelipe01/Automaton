package model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

class TestAutomaton {

	private Automaton auto;
	
	private void setUp1() { //Moore example
		
		ArrayList<State> states = new ArrayList<>();
		
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

		auto = new Automaton("Mealy", states, stimuli, outputs);

		
	}

	private void setUp2(){//Mealy Example
		
		ArrayList<State> states = new ArrayList<>();

		char[] c1 = new char[]{'0', '0'};
		char[] c2 = new char[]{'1', '1'};
		char[] c3 = new char[]{'1', '0'};

		State a = new State("A", c1);
		State b = new State("B", c2);
		State c = new State("C", c1);
		State d = new State("D", c2);
		State e = new State("E", c2);
		State f = new State("F", c1);
		State g = new State("G", c2);
		State h = new State("H", c3);
		State j = new State("J", c3);

		a.addSuccessor(b);
		a.addSuccessor(c);
		b.addSuccessor(c);
		b.addSuccessor(d);
		c.addSuccessor(d);
		c.addSuccessor(e);
		d.addSuccessor(c);
		d.addSuccessor(b);
		e.addSuccessor(f);
		e.addSuccessor(e);
		f.addSuccessor(g);
		f.addSuccessor(c);
		g.addSuccessor(f);
		g.addSuccessor(g);
		h.addSuccessor(j);
		h.addSuccessor(b);
		j.addSuccessor(h);
		j.addSuccessor(d);

		states.add(a);
		states.add(b);
		states.add(c);
		states.add(d);
		states.add(e);
		states.add(f);
		states.add(g);
		states.add(h);
		states.add(j);

		char[] stimuli = new char[]{'0', '1'};
		char[] outputs = new char[]{'0', '1'};

		auto = new Automaton("Moore", states, stimuli, outputs);
	}

	private void setUp3(){

		ArrayList<State> states = new ArrayList<>();
		
		//Moore example
		char[] c1 = new char[]{'a'};
		char[] c2 = new char[]{'b'};
		char[] c3 = new char[]{'c'};

		State a = new State("A", c1);
		State b = new State("B", c2);
		State c = new State("C", c3);
		State d = new State("D", c1);
		State e = new State("E", c2);

		a.addSuccessor(b);
		a.addSuccessor(c);
		a.addSuccessor(d);
		b.addSuccessor(c);
		b.addSuccessor(d);
		b.addSuccessor(e);
		c.addSuccessor(d);
		c.addSuccessor(e);
		c.addSuccessor(a);
		d.addSuccessor(e);
		d.addSuccessor(a);
		d.addSuccessor(b);
		e.addSuccessor(c);
		e.addSuccessor(d);
		e.addSuccessor(e);
		
		states.add(a);
		states.add(b);
		states.add(c);
		states.add(d);
		states.add(e);

		char[] stimuli = new char[]{'0', '1', '2'};
		char[] outputs = new char[]{'a', 'b', 'c'};

		auto = new Automaton("Moore", states, stimuli, outputs);

	}
	
	@Test
	public void testGetReducedAutomaton1() {
		setUp1();
		
		ArrayList<State> list = auto.getReducedAutomaton();
		int n = list.size();
		boolean flag = true;

		String cad;
		String name;

		for (int i = 0; i < n && flag; i++) {
			cad = "Q" + i;
			name = list.get(i).getName();
			if (!cad.equals(name)) {
				flag = false;
			}

		}

		assertTrue(flag,"Unexpected automaton");

	}

	@Test
	public void testGetReducedAutomaton2() {
		
		setUp2();
		
		ArrayList<State> list = auto.getReducedAutomaton();
		int n = list.size();
		boolean flag = true;

		String cad;
		String name;

		for (int i = 0; i < n && flag; i++) {
			cad = "Q" + i;
			name = list.get(i).getName();
			if (!cad.equals(name)) {
				flag = false;
			}

		}

		assertTrue(flag,"Unexpected automaton");

	}

}
