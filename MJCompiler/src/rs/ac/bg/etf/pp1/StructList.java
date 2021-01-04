package rs.ac.bg.etf.pp1;

import java.util.ArrayList;

import rs.etf.pp1.symboltable.concepts.Struct;

public class StructList {
	ArrayList<Struct> structList=new ArrayList<Struct>();
	
	public void add(Struct s) {
		structList.add(s);
	}
	public void clean() {
		structList.clear();
	}
	
	public int  size() {
		return structList.size();
	}
	
	public Struct getStruct(int num) {
		return structList.get(num);
	}
}
