package models;

import java.util.ArrayList;

public class sort {
	public void sortnumbers() {
	ArrayList<Integer> numbers=new ArrayList<Integer>();
	numbers.add(1);
	numbers.add(9);
	numbers.add(7);
	numbers.add(3);
	numbers.add(4);
	ArrayList<Integer> leaders=new ArrayList<Integer>();
	int max=0;
	User maxuser=null;
	for (int i=0; i<numbers.size(); i++) {
		if (numbers.get(i)>=max) {
			leaders.add(numbers.get(i));
			max=numbers.get(i);
		}
		else {
			for (int j=0; j<leaders.size(); j++) {
				if (leaders.size()==1) {
					leaders.add(0, numbers.get(i));
				}
				else if (leaders.get((leaders.size()-1)-j)<=numbers.get(i)); {
					leaders.add(leaders.size()-j, numbers.get(i));
				}
			}
		}
	}System.out.println(leaders);}
}
