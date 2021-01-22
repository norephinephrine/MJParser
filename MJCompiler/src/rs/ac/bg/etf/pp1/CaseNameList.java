package rs.ac.bg.etf.pp1;

import java.util.ArrayList;


public class CaseNameList {
	ArrayList<Integer> caseList=new ArrayList<Integer>();
	ArrayList<Integer> caseAdr=new ArrayList<Integer>();
	
	boolean defFlag=false;
	int defAddr=0;
	
	public int getAdr(int a) {
		for(int i=0;i<caseList.size();i++) {
			if(caseList.get(i)==a)
				return caseAdr.get(i); 
		}
		return -1;
	}
	public int getFinal() {
		return caseAdr.get(caseAdr.size()-1);
	}
	public void addAdr(int a) {
		caseAdr.add(a);
	}
	public ArrayList<Integer> getList(){
		return caseList;
	}
	public void add(int a) {
		caseList.add(a);
	}
	public boolean contains(int a) {
		return caseList.contains(a);
	}
	public int getInt(int num) {
		return caseList.get(num);
	}
	public boolean addDefault() {
		boolean b=defFlag;
		defFlag=true;
		return b;
	}
	public void setDef(int adr) {
		defAddr=adr;
	}
	public boolean isDefFlag() {
		return defFlag;
	}

	public int getDef() {
		return defAddr;
	}
}
