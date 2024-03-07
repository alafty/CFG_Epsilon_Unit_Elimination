package csen1002.main.task4;

import java.util.*;

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

	ArrayList<String> removedEpsilonFrom = new ArrayList<>();
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
		String returnString = "";

		for(String var : variables){
			returnString += var + ";";
		}
		returnString = returnString.substring(0, returnString.length() - 1);
		returnString += "#";

		for(String terminal : terminals){
			returnString += terminal + ";";
		}
		returnString = returnString.substring(0, returnString.length() - 1);
		returnString += "#";

		for(String key : variables){
			returnString += key + "/";
			Collections.sort(rules.get(key));
			for(String rule : rules.get(key)){
				returnString += rule + ",";
			}
			returnString = returnString.substring(0, returnString.length() - 1);
			returnString += ";";
		}

		return returnString.substring(0, returnString.length() - 1);
	}

	/**
	 * Eliminates Epsilon Rules from the grammar
	 */
	public void eliminateEpsilonRules() {
		boolean localChanges = false;
		for(String key : rules.keySet()){
			if(rules.get(key).contains("e")){
				rules.get(key).remove("e");
				System.out.println("The Variable: " + key);

				if(!removedEpsilonFrom.contains(key)) {
					if (SubstituteEpsilonFor(key)) {
						localChanges = true;
						removedEpsilonFrom.add(key);
					}
				}
				System.out.println("Rules after removal: " + rules);
			}
		}
		if(localChanges){
			eliminateEpsilonRules();
		}
	}

	public boolean SubstituteEpsilonFor(String key){
		boolean localChanges = false;
		//loops over every other variable
		for(String currKey : rules.keySet()){
			//if(currKey.equals(key)){continue;}
			ArrayList<String> toBeAdded = new ArrayList<>();

			//loops over every rule for currKey
			for(String miniRule : rules.get(currKey)){
				//checks if the current rule has the key inside it
				if(miniRule.contains(key)){
					ArrayList<String> newRules = GenerateNewRules(miniRule, key);
					for(String newRule : newRules) {
						if (!rules.get(currKey).contains(newRule) && !toBeAdded.contains(newRule)) {
							if(newRule.equals("e") && removedEpsilonFrom.contains(currKey)){continue;}
							toBeAdded.add(newRule);
							localChanges = true;
						}
					}
				}
			}
			rules.get(currKey).addAll(toBeAdded);

		}
		return localChanges;
	}

	public ArrayList<String> GenerateNewRules(String rule, String variable){
		String newRuleSoFar = "";
		int totalOccurrences = 0;

		ArrayList<String> possiblePermutations = new ArrayList<>();

		//calculate the total number of occurrences of the variable inside the rule
		for(int i= 0; i < rule.length(); i++){
			if(rule.charAt(i) == variable.charAt(0)){totalOccurrences++;}
		}

		for(int j = 0; j < totalOccurrences; j++) {
			int occurencesSoFar = 0;
			for (int i = 0; i < rule.length(); i++) {
				if (rule.charAt(i) == variable.charAt(0)) {
					occurencesSoFar++;
					if(occurencesSoFar - 1 == j) {
						continue;
					}
				}
				newRuleSoFar += rule.charAt(i);
			}
			if(newRuleSoFar.length() == 0) {newRuleSoFar = "e";}
			if(!possiblePermutations.contains(newRuleSoFar)){
				possiblePermutations.add(newRuleSoFar);
			}
			newRuleSoFar = "";
		}

		for(int i = 0; i < possiblePermutations.size(); i++){
			possiblePermutations.addAll(GenerateNewRules(possiblePermutations.get(i), variable));
		}

		return possiblePermutations;
	}

	/**
	 * Eliminates Unit Rules from the grammar
	 */
	public void eliminateUnitRules() {
		boolean localChanges = false;
		for(String key : rules.keySet()){
			ArrayList<String> toBeAdded = new ArrayList<>();
			System.out.println("Removing For: " + key);

			for(String rule : rules.get(key)){
				if(rule.length() == 1 && variables.contains(rule)){
					System.out.println("To Be Removed:" + rule);
					toBeAdded.add(rule);
					localChanges = true;
				}
			}
			for(String newRule : toBeAdded) {
				rules.get(key).remove(newRule);
				if (!key.equals(newRule)) {
					for(String duplicate : rules.get(newRule)){
						if(!rules.get(key).contains(duplicate)){
							rules.get(key).add(duplicate);
						}
					}
				}
			}
			System.out.println("Rules after removal: " + rules);
			toBeAdded.clear();

		}
		if(localChanges){
			eliminateUnitRules();
		}
	}

	public static void main (String[] args){
		String test1 = "S;A;B;C#a;b;c;d;x#S/aAb,xB;A/Bc,C,c,d;B/CACA,e;C/A,b,e";
		String test2 = "S;V;Z;R;W#m;n;w#S/RnV,m;V/S,V,Z,e,n,nRmWS,nVmRR;Z/S,WwZ,ZRR,e,nSnV;R/R,SRwVZ,SW,Zw,e;W/ZVR,mWn";
		CfgEpsUnitElim test = new CfgEpsUnitElim(test1);
		test.eliminateUnitRules();

	}

}
