package rs.ac.bg.etf.pp1;

import rs.etf.pp1.symboltable.Tab;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Scope;
import rs.etf.pp1.symboltable.concepts.Struct;

public class MJTab extends Tab {
	public static final Struct boolType=new Struct(Struct.Bool);
	public static void init() {
		Tab.init();
		Scope universe=MJTab.currentScope;
		universe.addToLocals(new Obj(Obj.Type, "bool", boolType));
		
		//sets fppos for already declared functions
		Obj obj=universe.findSymbol("chr");
		Obj par=obj.getLocalSymbols().iterator().next();
		par.setFpPos(1);
		
		obj=universe.findSymbol("ord");
		par=obj.getLocalSymbols().iterator().next();
		par.setFpPos(1);	
		
		obj=universe.findSymbol("len");
		par=obj.getLocalSymbols().iterator().next();
		par.setFpPos(1);			
	}
	
	public static Obj findLocal(String name) {
		Obj resultObj = null;
		if (currentScope.getLocals() != null) {
			resultObj = currentScope.getLocals().searchKey(name);
		}
		return (resultObj != null) ? resultObj : noObj;
	}	
	
	public static boolean equals(Struct source,Struct other) {
		if (source.getKind() == Struct.Array) return other.getKind() == Struct.Array
				&& source.getElemType().equals(other.getElemType());


		return source == other;
	}
	
	public static boolean compatibleWith(Struct source,Struct other) {
		return MJTab.equals(source,other) || source == MJTab.nullType && other.isRefType()
				|| other == Tab.nullType && source.isRefType();
	}
	
	 private static boolean assignableTo(Struct source,Struct dest) {
		return 	MJTab.equals(source,dest) 
				|| 
				(source == MJTab.nullType && dest.isRefType())
				|| 
				(source.getKind() == Struct.Array && dest.getKind() == Struct.Array && dest.getElemType() == Tab.noType);	
	}
	public static boolean assignableToRef(Struct source,Struct dest) {
		boolean rez=false;
		if(source.getKind()==Struct.Class && dest.getKind()==Struct.Class) {
			if(source==dest)return true;
        	Struct curr=source.getElemType();
        	while(curr!=null) {
        		if(curr==dest) return true;
        		curr=curr.getElemType();     		
        	}   			
		}
		rez=MJTab.assignableTo(source,dest);
			

		return rez;
	}
	
	public static String findClassName(Struct s) {
		Scope universeScope=currentScope;
		if(universeScope.getOuter()==null)return "";
		while(universeScope.getOuter().getOuter()!=null)
			universeScope=universeScope.getOuter();
		
		for(Obj o:universeScope.getLocals().symbols()) {
			if(o.getType()==s) {
				return o.getName();
			}
		}
		return "";
	}
	
}

