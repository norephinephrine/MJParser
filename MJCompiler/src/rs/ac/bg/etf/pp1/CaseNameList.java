package rs.ac.bg.etf.pp1;

import java.util.ArrayList;


public class CaseNameList {
	ArrayList<Integer> caseList=new ArrayList<Integer>();
	
	public void add(int a) {
		caseList.add(a);
	}
	public boolean contains(int a) {
		return caseList.contains(a);
	}
	public int getInt(int num) {
		return caseList.get(num);
	}
}
