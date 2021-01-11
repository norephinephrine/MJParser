package rs.ac.bg.etf.pp1;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Stack;

import rs.ac.bg.etf.pp1.ast.AddMinus;
import rs.ac.bg.etf.pp1.ast.AddPlus;
import rs.ac.bg.etf.pp1.ast.Addop;
import rs.ac.bg.etf.pp1.ast.Assignop;
import rs.ac.bg.etf.pp1.ast.CaseColon;
import rs.ac.bg.etf.pp1.ast.CaseParam;
import rs.ac.bg.etf.pp1.ast.ClassDecl;
import rs.ac.bg.etf.pp1.ast.ClassDeclMeth;
import rs.ac.bg.etf.pp1.ast.ClassDeclNoMeth;
import rs.ac.bg.etf.pp1.ast.ClassName;
import rs.ac.bg.etf.pp1.ast.CondFactNoOp;
import rs.ac.bg.etf.pp1.ast.CondFactOp;
import rs.ac.bg.etf.pp1.ast.CondTerm;
import rs.ac.bg.etf.pp1.ast.CondTermList;
import rs.ac.bg.etf.pp1.ast.CondTermSingle;
import rs.ac.bg.etf.pp1.ast.ConditionList;
import rs.ac.bg.etf.pp1.ast.ConditionPar;
import rs.ac.bg.etf.pp1.ast.ConditionParen;
import rs.ac.bg.etf.pp1.ast.ConditionSingle;
import rs.ac.bg.etf.pp1.ast.ConstBool;
import rs.ac.bg.etf.pp1.ast.ConstChar;
import rs.ac.bg.etf.pp1.ast.ConstName;
import rs.ac.bg.etf.pp1.ast.ConstNum;
import rs.ac.bg.etf.pp1.ast.DesArr;
import rs.ac.bg.etf.pp1.ast.DesMember;
import rs.ac.bg.etf.pp1.ast.DesStmActPar;
import rs.ac.bg.etf.pp1.ast.DesStmAssign;
import rs.ac.bg.etf.pp1.ast.DesStmDec;
import rs.ac.bg.etf.pp1.ast.DesStmInc;
import rs.ac.bg.etf.pp1.ast.DesStmNoActPar;
import rs.ac.bg.etf.pp1.ast.Designator;
import rs.ac.bg.etf.pp1.ast.DesignatorSingle;
import rs.ac.bg.etf.pp1.ast.DoStart;
import rs.ac.bg.etf.pp1.ast.ElseStart;
import rs.ac.bg.etf.pp1.ast.ElseStm;
import rs.ac.bg.etf.pp1.ast.ExprTern;
import rs.ac.bg.etf.pp1.ast.Factor;
import rs.ac.bg.etf.pp1.ast.FactorBool;
import rs.ac.bg.etf.pp1.ast.FactorChar;
import rs.ac.bg.etf.pp1.ast.FactorExpr;
import rs.ac.bg.etf.pp1.ast.FactorNew;
import rs.ac.bg.etf.pp1.ast.FactorNewArr;
import rs.ac.bg.etf.pp1.ast.FactorNum;
import rs.ac.bg.etf.pp1.ast.FunCallNoParam;
import rs.ac.bg.etf.pp1.ast.FunCallParam;
import rs.ac.bg.etf.pp1.ast.IfOnlyStm;
import rs.ac.bg.etf.pp1.ast.MethodDecPar;
import rs.ac.bg.etf.pp1.ast.MethodTypeName;
import rs.ac.bg.etf.pp1.ast.MulDiv;
import rs.ac.bg.etf.pp1.ast.MulMod;
import rs.ac.bg.etf.pp1.ast.MulMul;
import rs.ac.bg.etf.pp1.ast.Mulop;
import rs.ac.bg.etf.pp1.ast.ProgName;
import rs.ac.bg.etf.pp1.ast.ReLSE;
import rs.ac.bg.etf.pp1.ast.RelBG;
import rs.ac.bg.etf.pp1.ast.RelBGE;
import rs.ac.bg.etf.pp1.ast.RelDiff;
import rs.ac.bg.etf.pp1.ast.RelEqual;
import rs.ac.bg.etf.pp1.ast.RelLS;
import rs.ac.bg.etf.pp1.ast.Relop;
import rs.ac.bg.etf.pp1.ast.StatementBreak;
import rs.ac.bg.etf.pp1.ast.StatementContinue;
import rs.ac.bg.etf.pp1.ast.StatementPrint;
import rs.ac.bg.etf.pp1.ast.StatementPrintLoop;
import rs.ac.bg.etf.pp1.ast.StatementRead;
import rs.ac.bg.etf.pp1.ast.StatementRet;
import rs.ac.bg.etf.pp1.ast.StatementRetExpr;
import rs.ac.bg.etf.pp1.ast.StatementSwitch;
import rs.ac.bg.etf.pp1.ast.StatementWhile;
import rs.ac.bg.etf.pp1.ast.SwitchExpr;
import rs.ac.bg.etf.pp1.ast.SwitchStart;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.TermExpr;
import rs.ac.bg.etf.pp1.ast.TermSingleNeg;
import rs.ac.bg.etf.pp1.ast.TernCond;
import rs.ac.bg.etf.pp1.ast.TernDoTrue;
import rs.ac.bg.etf.pp1.ast.Type;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.ac.bg.etf.pp1.ast.WhileCondStart;
import rs.ac.bg.etf.pp1.visitor.CountVisitor;
import rs.ac.bg.etf.pp1.visitor.CountVisitor.FormParamCounter;
import rs.ac.bg.etf.pp1.visitor.CountVisitor.VarCounter;
import rs.etf.pp1.mj.runtime.Code;
import rs.etf.pp1.symboltable.concepts.Obj;
import rs.etf.pp1.symboltable.concepts.Struct;

public class CodeGenerator extends VisitorAdaptor {

	private int mainPc;
	private boolean classDef=false;
	ArrayList<Obj>classTypes =new ArrayList<Obj>();
	ArrayList<Integer>andAdr =new ArrayList<Integer>();
	ArrayList<Integer>orAdr =new ArrayList<Integer>();
	Stack<ArrayList<Designator>> methodStack=new Stack<ArrayList<Designator>>();
	Stack<Integer> whileAdrStart=new Stack<Integer>();
	Stack<ArrayList<Integer>> breakStackAdr=new Stack<ArrayList<Integer>>();
	Stack<ArrayList<Integer>> continueStackAdr=new Stack<ArrayList<Integer>>();
	Stack<ArrayList<Integer>> adrStack=new Stack<ArrayList<Integer>>();
	Stack<CaseNameList> caseNamesStack=new Stack<CaseNameList>();
	int ternTrueAdr=0;
	int ternFalseAdr=0;
	public int getMainPc(){
		return mainPc;
	}
	private void addVirtual() {
		for(Obj obj:classTypes) {
			obj.setAdr(Code.dataSize);
			for(Obj methodObj:obj.getType().getMembers()) {
				if(methodObj.getKind()==Obj.Meth) {
					String s=methodObj.getName();
					
					for(int i=0;i<s.length();i++) {
						int num=s.charAt(i);
						Code.loadConst(num);
						Code.put(Code.putstatic);
						Code.put2(Code.dataSize);
						Code.dataSize++;
					}
					Code.loadConst(-1);
					Code.put(Code.putstatic);
					Code.put2(Code.dataSize);
					Code.dataSize++;
					
					Code.loadConst(methodObj.getAdr());
					Code.put(Code.putstatic);
					Code.put2(Code.dataSize);
					Code.dataSize++;								
				}			
			}
			Code.loadConst(-2);
			Code.put(Code.putstatic);
			Code.put2(Code.dataSize);
			Code.dataSize++;			
		}
	}
	private void addUniverseMethods() {
		
		//LEN Method
		
		MJTab.lenObj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.put(Code.arraylength);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		//CHR METHOD
		MJTab.chrObj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.loadConst(256);
		Code.put(Code.rem);
		Code.put(Code.exit);
		Code.put(Code.return_);
		
		//ORD METHOD
		MJTab.ordObj.setAdr(Code.pc);
		Code.put(Code.enter);
		Code.put(1);
		Code.put(1);
		Code.put(Code.load_n);
		Code.put(Code.exit);
		Code.put(Code.return_);		
	}
	public void visit(ProgName prog) {
		addUniverseMethods();
	}
	
    public void visit(ClassName className) {
    	Obj classObj=className.obj;
		classTypes.add(classObj);
		if(classObj.getType().getElemType()!=null) {
			for(Obj o:classObj.getType().getElemType().getMembers()) {
				if(o.getKind()==Obj.Meth) {
					for(Obj o_sub:classObj.getType().getMembers()) {
						if(o_sub.getKind()==Obj.Meth && o_sub.getName().equals(o.getName())) {
							o_sub.setAdr(o.getAdr());
							break;
						}
					}
				}
			}
		}
		classDef=true;
    }
    public void visit(ClassDeclMeth ClassDeclMeth) {
    	classDef=false;
    }
    public void visit(ClassDeclNoMeth classDeclNoMeth) {
    	classDef=false;
    }
        	
	public void visit(MethodTypeName methodTypeName){
		if("main".equalsIgnoreCase(methodTypeName.getName())){
			mainPc = Code.pc;	
			addVirtual();			
		}
		methodTypeName.obj.setAdr(Code.pc);
		SyntaxNode methodNode = methodTypeName.getParent();
	
		VarCounter varCnt = new VarCounter();
		methodNode.traverseTopDown(varCnt);
		
		FormParamCounter fpCnt = new FormParamCounter();
		methodNode.traverseTopDown(fpCnt);
		
		int increment=0;
		if(classDef) {
			increment=1;
		}
		
		Code.put(Code.enter);
		Code.put(fpCnt.getCount()+increment);
		Code.put(fpCnt.getCount() + varCnt.getCount()+increment);		
	
	}
	
	public void visit(MethodDecPar methodDecl){
		Code.put(Code.exit);
		Code.put(Code.return_);		
	}    
	
	//DESIGNATOR
	public void visit(DesignatorSingle designator) {
		SyntaxNode parent = designator.getParent();
		
		if(designator.obj.getKind()==Obj.Fld ) {
			//System.out.println("haha");
			Code.put(Code.load_n);
		}
		else if(classDef && designator.obj.getKind()==Obj.Meth) {
			Code.put(Code.load_n);
		}
		
		if(DesStmAssign.class != parent.getClass() && DesStmActPar.class != parent.getClass() 
				&& DesStmNoActPar.class != parent.getClass() && FunCallNoParam.class!=parent.getClass() 
				&& FunCallParam.class!=parent.getClass() && StatementRead.class!=parent.getClass()){
				Code.load(designator.obj);
			
		}
		
	}
	public void visit(DesArr designator) {
		SyntaxNode parent = designator.getParent();
		
		if(DesStmAssign.class != parent.getClass() && DesStmActPar.class != parent.getClass() 
				&& DesStmNoActPar.class != parent.getClass() && FunCallNoParam.class!=parent.getClass() 
				&& FunCallParam.class!=parent.getClass() && StatementRead.class!=parent.getClass()){
			Code.load(designator.obj);
		}		
	}
	public void visit(DesMember designator) {
		SyntaxNode parent = designator.getParent();
		
		if(DesStmAssign.class != parent.getClass() && DesStmActPar.class != parent.getClass() 
				&& DesStmNoActPar.class != parent.getClass() && FunCallNoParam.class!=parent.getClass() 
				&& FunCallParam.class!=parent.getClass() && StatementRead.class!=parent.getClass()){
			Code.load(designator.obj);
		}	
		if(designator.obj.getKind()==Obj.Meth && classDef==false) {
			ArrayList<Designator> list=new ArrayList<Designator>();
			
			Designator parentNode=designator.getDesignator();
			while(parentNode.getClass()!=DesignatorSingle.class) {
				list.add(0,parentNode);
				
				if(parentNode.getClass()==DesMember.class) {
					parentNode=((DesMember)parentNode).getDesignator();
				}
				else if(parentNode.getClass()==DesArr.class) {
					parentNode=((DesArr)parentNode).getDesignator();
				}				
			}
			list.add(0,parentNode);
			methodStack.push(list);
		}
	}
	
	//FACTOR
	
	private void funCall(Obj functObj) {
		int offset = functObj.getAdr() - Code.pc;
		Code.put(Code.call);
		
		Code.put2(offset);			
	}
	public void visit(FunCallParam funct) {
		if(funct.getDesignator().getClass()==DesignatorSingle.class) {
			if(classDef)
				virtualProcCall(funct.getDesignator().obj,false);
			else
				funCall(funct.getDesignator().obj);
		}
			
		else  {
			virtualProcCall(funct.getDesignator().obj,true);
		}	
		
	
	}
	public void visit(FunCallNoParam funct) {
		if(funct.getDesignator().getClass()==DesignatorSingle.class) {
			if(classDef)
				virtualProcCall(funct.getDesignator().obj,false);
			else
				funCall(funct.getDesignator().obj);
		}
			
		else  {
			virtualProcCall(funct.getDesignator().obj,true);
		}	
	}	
    public void visit(FactorNum fact) {
		Obj con = MJTab.insert(Obj.Con, "$", fact.struct);
		con.setLevel(0);
		con.setAdr(fact.getN1());
		
		Code.load(con);
    }
    public void visit(FactorBool fact) {
		Obj con = MJTab.insert(Obj.Con, "$", MJTab.charType);
		con.setLevel(0);
		//int ascii0=(int)'0';
    	if(fact.getB1().equals("true"))
    		con.setAdr(1); 
    	else  con.setAdr(0); 
		
		Code.load(con);
    }  
    public void visit(FactorChar fact) {
		Obj con = MJTab.insert(Obj.Con, "$", fact.struct);
		con.setLevel(0);	
		int value=fact.getC1();
		con.setAdr(value); 

		Code.load(con);
    }     	
    
    
	public void visit(FactorNewArr arr) {
		Code.put(Code.newarray);
		if(arr.struct.getElemType()==MJTab.charType || arr.struct.getElemType()==MJTab.boolType) {
			Code.put(0);
		}
		else {
			Code.put(1);
		}
	}
    
	public void visit(FactorNew newClass) {
		Code.put(Code.new_);
		Code.put2(newClass.struct.getNumberOfFields()*4);
		Code.put(Code.dup);
		
		for(Obj o:classTypes) {
			if(newClass.struct==o.getType()) {
				Code.loadConst(o.getAdr());
				break;
			}
		}
		
		Code.put(Code.putfield);
		Code.put2(0);	
		
	}
    public void visit(StatementPrintLoop stm) {
    	int width=stm.getNumLoop();
		if(stm.getExpr().struct == MJTab.intType){
			Code.loadConst(width);
			Code.put(Code.print);				

		}
		else if(stm.getExpr().struct == MJTab.charType){
			Code.loadConst(width);
			Code.put(Code.bprint);					
		}
		
		else if(stm.getExpr().struct==MJTab.boolType) {
			int jmp1;
			int jmp2=0;
			Code.loadConst(0);
			Code.putFalseJump(Code.ne,0);
			jmp1=Code.pc-2;
			
			String t="true";	
			Code.loadConst('t');
			Code.loadConst(width);
			Code.put(Code.bprint);	
			for(int i=1;i<t.length();i++) {
				Code.loadConst(t.charAt(i));
				Code.loadConst(1);
				Code.put(Code.bprint);	
			}
			Code.putJump(0);
			jmp2=Code.pc-2;
			Code.fixup(jmp1);
			
			t="false";	
			Code.loadConst('f');
			Code.loadConst(width);
			Code.put(Code.bprint);	
			for(int i=1;i<t.length();i++) {
				Code.loadConst(t.charAt(i));
				Code.loadConst(1);
				Code.put(Code.bprint);	
			}			
			
			Code.fixup(jmp2);	
		}

    }  
    public void visit(StatementPrint stm) {
		if(stm.getExpr().struct == MJTab.intType){
			Code.loadConst(5);
			Code.put(Code.print);
		}
		else if(stm.getExpr().struct == MJTab.charType){
			Code.loadConst(1);
			Code.put(Code.bprint);					
		}
		
		else if(stm.getExpr().struct==MJTab.boolType) {
			int jmp1;
			int jmp2=0;
			Code.loadConst(0);
			Code.putFalseJump(Code.ne,0);
			jmp1=Code.pc-2;
			
			String t="true";	
			Code.loadConst('t');
			Code.loadConst(1);
			Code.put(Code.bprint);	
			for(int i=1;i<t.length();i++) {
				Code.loadConst(t.charAt(i));
				Code.loadConst(1);
				Code.put(Code.bprint);	
			}
			Code.putJump(0);
			jmp2=Code.pc-2;
			Code.fixup(jmp1);
			
			t="false";	
			Code.loadConst('f');
			Code.loadConst(1);
			Code.put(Code.bprint);	
			for(int i=1;i<t.length();i++) {
				Code.loadConst(t.charAt(i));
				Code.loadConst(1);
				Code.put(Code.bprint);	
			}			
			
			Code.fixup(jmp2);		
		}		
    } 
    //Read
    public void visit(StatementRead stm) {
		if(stm.getDesignator().obj.getType() == MJTab.intType){
			Code.put(Code.read);
			Code.store(stm.getDesignator().obj);
		}
		else if(stm.getDesignator().obj.getType() == MJTab.charType){
			Code.put(Code.bread);		
			Code.store(stm.getDesignator().obj);
		}
		
		else if(stm.getDesignator().obj.getType()==MJTab.boolType) {
			Code.put(Code.bread);	
			Code.loadConst('0');
			int jmp1,jmp2;
			
			Code.putFalseJump(Code.ne,0);
			jmp1=Code.pc-2;
			Code.loadConst(1);
			Code.putJump(0);
			jmp2=Code.pc-2;
			
			Code.fixup(jmp1);
			
			
			Code.loadConst(0);
			Code.fixup(jmp2);
			
			Code.store(stm.getDesignator().obj);
		}	   	
    }
    
    
	public void visit(StatementRet ret){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	
	public void visit(StatementRetExpr ret){
		Code.put(Code.exit);
		Code.put(Code.return_);
	}
	   
	
	public void visit(FactorExpr expr) {
		Mulop mulOp=expr.getMulop();
		if(mulOp instanceof MulMul) {
			Code.put(Code.mul);
		}
		else if(mulOp instanceof MulDiv) {
			Code.put(Code.div);
		}
		else if(mulOp instanceof MulMod) {
			Code.put(Code.rem);
		}
	}
    
	public void visit(TermExpr expr) {
		Addop addOp=expr.getAddop();
		if(addOp instanceof AddPlus) {
			Code.put(Code.add);
		}
		else if(addOp instanceof AddMinus) {
			Code.put(Code.sub);
		}	
	}
	public void visit(TermSingleNeg expr) {
		Code.put(Code.neg);
	}
	
	//DESIGNATOR STATEMENTS
	public void visit(DesStmAssign assignment){
		Obj obj=assignment.getDesignator().obj;
		Code.store(obj);

	}	
	
	private void procCall(Obj functObj) {
		int offset = functObj.getAdr() - Code.pc;
		Code.put(Code.call);
		Code.put2(offset);
		if(functObj.getType() != MJTab.noType){
			Code.put(Code.pop);
		}				
	}
	private void virtualProcCall(Obj methodObj,boolean stackPop) {
		
		if(stackPop) {
			ArrayList<Designator> list=methodStack.pop();
			for(Designator d:list) {
				if(d.getClass()==DesignatorSingle.class) {
					Code.load(((DesignatorSingle) d).obj);
				}
				else if(d.getClass()==DesMember.class) {
					Code.load(((DesMember)d).obj);
				}
				else if(d.getClass()==DesArr.class) {
					((DesArr)d).getExpr().traverseBottomUp(this);
					Code.load(((DesArr)d).obj);
				}
			}
			list.clear();			
		}
		else {
			Code.put(Code.load_n);
		}
		Code.put(Code.getfield);
		Code.put2(0);		
		Code.put(Code.invokevirtual);
		
		String s=methodObj.getName();
		for(int i=0;i<s.length();i++) {
			int num=s.charAt(i);
			Code.put4(num);
		}
		Code.put4(-1);		
	}
	public void visit(DesStmNoActPar funct) {
		if(funct.getDesignator().getClass()==DesignatorSingle.class) {
			if(classDef) {
				virtualProcCall(funct.getDesignator().obj,false);
				if(funct.getDesignator().obj.getType() != MJTab.noType){
					Code.put(Code.pop);		
				}
			}			
			else
				procCall(funct.getDesignator().obj);
		}
			
		else  {
			virtualProcCall(funct.getDesignator().obj,true);
			if(funct.getDesignator().obj.getType() != MJTab.noType){
				Code.put(Code.pop);
			}				
		}
	}
	public void visit(DesStmActPar funct) {
		if(funct.getDesignator().getClass()==DesignatorSingle.class) {
			if(classDef) {
				virtualProcCall(funct.getDesignator().obj,false);
				if(funct.getDesignator().obj.getType() != MJTab.noType){
					Code.put(Code.pop);		
				}
			}			
			else
				procCall(funct.getDesignator().obj);
		}
			
		else  {
			virtualProcCall(funct.getDesignator().obj,true);
			if(funct.getDesignator().obj.getType() != MJTab.noType){
				Code.put(Code.pop);
			}				
		}
	}	
	
	public void visit(DesStmInc desInc) {
		addIncrement(desInc.getDesignator().obj,Code.const_1);
	}
	public void visit(DesStmDec desDec) {
		addIncrement(desDec.getDesignator().obj,Code.const_m1);
	}
	
	private void addIncrement(Obj obj,int incType) {
		if(obj.getKind()==Obj.Elem) {
			Code.pc--;
			Code.put(Code.dup2);
			Code.load(obj);
			Code.put(incType);
			Code.put(Code.add);
			Code.store(obj);
		}
		else if(obj.getKind()==Obj.Var) {
			if(obj.getLevel()==0) {
				Code.put(incType);
				Code.put(Code.add);
				Code.store(obj);
			}
			else {
				Code.put(Code.pop);
				Code.put(Code.inc);
				Code.put(obj.getAdr());
				if(incType==Code.const_1)
					Code.put(1);
				else if(incType==Code.const_m1)
					Code.put(-1);
			}
		}
		else if(obj.getKind()==Obj.Fld) {
			Code.pc=Code.pc-3;
			Code.put(Code.dup);
			Code.load(obj);
			Code.put(incType);
			Code.put(Code.add);
			Code.store(obj);
		}
		else {
			System.out.println("Pokusaj inkrementovanja/dekrementovanje necega sto nije promenljiva,polje ili element niza");
		}
	}
	
	public void visit(CondFactNoOp cond) {
		Code.loadConst(0);
		SyntaxNode gParent=cond.getParent().getParent();
		SyntaxNode ggParent=cond.getParent().getParent().getParent();
		if(ggParent.getClass()==ConditionList.class && (gParent.getClass()==ConditionSingle.class || gParent.getClass()==ConditionList.class)) {
			Code.putFalseJump(Code.eq, 0);
			for(int i=0;i<andAdr.size();i++) {
				Code.fixup(andAdr.get(i));
			}
			andAdr.clear();
			orAdr.add(Code.pc-2);
		}
		else if(ggParent.getClass()==StatementWhile.class && (gParent.getClass()==ConditionSingle.class || gParent.getClass()==ConditionList.class)) {
			Code.putFalseJump(Code.eq, 0);
			orAdr.add(Code.pc-2);	
		}		
		else {
			Code.putFalseJump(Code.ne, 0);
			andAdr.add(Code.pc-2);
		}		
	}
	public void visit(CondFactOp cond) {
		
		Relop relOp= cond.getRelop();
		
		int op=-1;
		if(relOp instanceof RelEqual) {
			op=Code.eq;
		}
		else if(relOp instanceof RelDiff) {
			op=Code.ne;
		}
		else if(relOp instanceof RelBG) {
			op=Code.gt;
		}	
		else if(relOp instanceof RelBGE) {
			op=Code.ge;
		}	
		else if(relOp instanceof RelLS) {
			op=Code.lt;
		}	
		else if(relOp instanceof ReLSE) {
			op=Code.le;
		}		
		SyntaxNode gParent=cond.getParent().getParent();
		SyntaxNode ggParent=cond.getParent().getParent().getParent();
		if(ggParent.getClass()==ConditionList.class && (gParent.getClass()==ConditionSingle.class || gParent.getClass()==ConditionList.class)) {
			Code.putFalseJump(Code.inverse[op], 0);
			for(int i=0;i<andAdr.size();i++) {
				Code.fixup(andAdr.get(i));
			}
			andAdr.clear();
			orAdr.add(Code.pc-2);			
		}
		else if(ggParent.getClass()==StatementWhile.class && (gParent.getClass()==ConditionSingle.class || gParent.getClass()==ConditionList.class)) {
			Code.putFalseJump(Code.inverse[op], 0);
			orAdr.add(Code.pc-2);	
		}
		else {
			Code.putFalseJump(op, 0);
			andAdr.add(Code.pc-2);
		}
	}
	
	public void visit(ConditionPar condition) {
		for(int i=0;i<orAdr.size();i++) {
			Code.fixup(orAdr.get(i));
		}
		orAdr.clear();
		adrStack.push(new ArrayList<Integer>(andAdr));
		andAdr.clear();
		//System.out.println(adrStack.peek());
	}	
	public void visit(ElseStart elseStart) {
		Code.putJump(0);
		ArrayList<Integer> adrList=adrStack.pop();
		for(int i=0;i<adrList.size();i++) {
			Code.fixup(adrList.get(i));
		}
		adrList.clear();
		adrList.add(Code.pc-2);
		adrStack.push(adrList);
	}
	public void visit(ElseStm elseStm) {
		ArrayList<Integer> adrList=adrStack.pop();
		for(int i=0;i<adrList.size();i++) {
			Code.fixup(adrList.get(i));
		}
		adrList.clear();
	}
	public void visit(IfOnlyStm ifOnlyStm) {
		ArrayList<Integer> adrList=adrStack.pop();
		for(int i=0;i<adrList.size();i++) {
			Code.fixup(adrList.get(i));
		}
		adrList.clear();
	}
	// DO WHILE
	public void visit(DoStart doStart) {
		whileAdrStart.add(Code.pc);
		breakStackAdr.add(new ArrayList<Integer>());
		continueStackAdr.add(new ArrayList<Integer>());
	}
	
	public void visit(StatementWhile stm) {
		for(int i=0;i<andAdr.size();i++) {
			Code.fixup(andAdr.get(i));
		}
		
		
		for(int i=0;i<orAdr.size();i++) {
			 Code.put2(orAdr.get(i), (whileAdrStart.peek()-orAdr.get(i) + 1));
		}
		
		orAdr.clear();
		andAdr.clear();
		
		
		whileAdrStart.pop();
		
		ArrayList<Integer> adrList=breakStackAdr.pop();
		
		for(int i=0;i<adrList.size();i++) {
			Code.fixup(adrList.get(i));
		}
		adrList.clear();
	}
	public void visit(StatementContinue stm) {
		Code.putJump(0);
		continueStackAdr.peek().add(Code.pc-2);
	}
	
	public void visit(WhileCondStart wh) {
		ArrayList<Integer> adrList=continueStackAdr.pop();
		for(int i=0;i<adrList.size();i++) {
			Code.fixup(adrList.get(i));
		}
		adrList.clear();		
	}
	
	public void visit(StatementBreak brk) {
		Code.putJump(0);
		breakStackAdr.peek().add(Code.pc-2);
	}
	
	//Swith Start
	public void visit(SwitchExpr str) {
		breakStackAdr.add(new ArrayList<Integer>());
		StatementSwitch stm=(StatementSwitch)str.getParent();
		CaseNameList list=stm.getCaseList().casenamelist;
		for(int i:list.getList()) {
			Code.put(Code.dup);
			Code.loadConst(i);
			Code.putFalseJump(Code.ne, 0);;
			list.addAdr(Code.pc-2);
		}
		Code.put(Code.pop);
		Code.putJump(0);
		list.addAdr(Code.pc-2);	
		
		caseNamesStack.add(list);
		//System.out.println(list.getList());
	}
    public void visit(CaseColon caseParam) {
    	int num=((CaseParam)caseParam.getParent()).getNum();
    	

    	int adr=caseNamesStack.peek().getAdr(num);
    	Code.fixup(adr);
    }
	public void visit(StatementSwitch stm) {
		ArrayList<Integer> adrList=breakStackAdr.pop();
		
		for(int i=0;i<adrList.size();i++) {
			Code.fixup(adrList.get(i));
		}
		int adr=caseNamesStack.peek().getFinal();
		Code.fixup(adr);
		caseNamesStack.pop();
		adrList.clear();		
	}
	//Tern
	public void visit(TernCond expr) {
		Code.loadConst(0);
		Code.putFalseJump(Code.ne, 0);
		ternTrueAdr=Code.pc-2;
		
	}
	public void visit(TernDoTrue expr) {
	
		Code.putJump(0);
		ternFalseAdr=Code.pc-2;
		
		Code.fixup(ternTrueAdr);
	}
	public void visit(ExprTern expr) {
		Code.fixup(ternFalseAdr);
	}
}
