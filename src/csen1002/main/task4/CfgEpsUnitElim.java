package csen1002.main.task4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Write your info here
 * 
 * @name Mohamed Moustafa
 * @id 49-3603
 * @labNumber 21
 */

public class CfgEpsUnitElim {

	ArrayList<String> variables = new ArrayList<>();
	ArrayList<String> terminals = new ArrayList<>();

	HashMap<String, ArrayList<String>> rules = new HashMap<>();

	/**
	 * Constructs a Context Free Grammar
	 * 
	 * @param cfg A formatted string representation of the CFG. The string
	 *             representation follows the one in the task description
	 */
	public CfgEpsUnitElim(String cfg) {
		// TODO Auto-generated constructor stub
		String[] cfgArray = cfg.split("#");

		variables.addAll(List.of(cfgArray[0].split(";")));

		terminals.addAll(List.of(cfgArray[1].split(";")));

		for(int i= 0; i < variables.size(); i++){
			ArrayList<String> rightHand = new ArrayList<>();
			String[] temp = cfgArray[2].split(";")[i].split("/")[1].split(",");
			System.out.println(temp);
			rightHand.addAll(List.of(temp));
			rules.put(variables.get(i), rightHand);
		}


		System.out.println(variables);
		System.out.println(terminals);
		System.out.println(rules);
	}

	/**
	 * @return Returns a formatted string representation of the CFG. The string
	 *         representation follows the one in the task description
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * Eliminates Epsilon Rules from the grammar
	 */
	public void eliminateEpsilonRules() {
		// TODO Auto-generated method stub
	}

	/**
	 * Eliminates Unit Rules from the grammar
	 */
	public void eliminateUnitRules() {
		// TODO Auto-generated method stub
	}

	public static void main (String[] args){
		CfgEpsUnitElim test = new CfgEpsUnitElim("S;A;B;C#a;b;c;d;x#S/aAb,xB;A/Bc,C,c,d;B/CACA,e;C/A,b,e");
	}

}
