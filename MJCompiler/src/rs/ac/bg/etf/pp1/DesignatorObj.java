package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class DesignatorObj {
	
	private Obj obj=null;
	private boolean isArr=false;
	
	public boolean CheckIsArr() {
		return isArr;
	}
	
	public void setIsArr(boolean bool) {
		isArr=bool;
	}
	
	public Struct getType() {
		return obj.getType();
	}
	
	public String getName() {
		return obj.getName();
	}
	
	public void setObj(Obj obj) {
		this.obj=obj;
	}
	
	public Obj getObj() {
		return obj;
	}
}
