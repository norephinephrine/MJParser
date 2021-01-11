package rs.ac.bg.etf.pp1;

import rs.ac.bg.etf.pp1.ast.*;
import rs.ac.bg.etf.pp1.ast.SyntaxNode;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;
import rs.etf.pp1.symboltable.*;
import rs.etf.pp1.symboltable.concepts.*;
import rs.etf.pp1.symboltable.structure.HashTableDataStructure;

import java.util.Collection;

import org.apache.log4j.*;
import org.omg.CORBA.OBJ_ADAPTER;

public class SemanticAnalyzer extends VisitorAdaptor {
	Logger log = Logger.getLogger(getClass());
	
	Obj currentMethod = null;
	Struct currentClass=null;
	Struct currentExtendClass=null;
	Obj extendObj=null;
	Obj currentClassObj=null;
	int numFormal=0;
	boolean errorDetected=false;
	boolean mainDeclared=false;
	boolean returnNotFound=false;
	boolean returnVoid=false;
    private Struct curr_type=MJTab.noType;
	private String const_name=null;
	private int switchLevel=0;
	private int whileLevel=0;
    public int nVars=0;
    private int ifLvl=0;
    
	public void report_error(String message, SyntaxNode info) {
		errorDetected = true;
		StringBuilder msg = new StringBuilder(message);
		int line = (info == null) ? 0: info.getLine();
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.error(msg.toString());
	}

	public void report_info(String message, SyntaxNode info,boolean addInfo,Obj obj) {
		StringBuilder msg = new StringBuilder(message); 
		int line = (info == null) ? 0: info.getLine();
		if(addInfo) {
			msg.append(" | ");
			visitObjNode(obj, msg);
		}
		if (line != 0)
			msg.append (" na liniji ").append(line);
		log.info(msg.toString());
	}	
	
    public void visit(ProgName progName){
    	progName.obj = MJTab.insert(Obj.Prog, progName.getProg(), MJTab.noType);
    	MJTab.openScope();
    }
    
    public void visit(Program prog){
    	nVars=MJTab.currentScope().getnVars();
    	if(nVars>65536) {
    		report_error("Greska program ima vise od 65536 globalnih promenljivi",prog);
    	}
    	
    	MJTab.chainLocalSymbols(prog.getProgName().obj);
    	MJTab.closeScope();
    	if(mainDeclared==false)
    		report_error("Nije definisana main metoda", null);
    }	
	
    public void visit(Type type){
    	Obj typeNode = MJTab.find(type.getTypeName());
    	if(typeNode == MJTab.noObj){
    		report_error("Nije pronadjen tip " + type.getTypeName() + " u tabeli simbola! ", type);
    		curr_type =type.struct= MJTab.noType;
    	}else{
    		if(Obj.Type == typeNode.getKind()){
    			curr_type =type.struct= typeNode.getType();
    		}else{
    			report_error("Greska: Ime " + type.getTypeName() + " ne predstavlja tip!", type);
    			curr_type= type.struct= MJTab.noType;
    		}
    	}
    }	
    
    //Visit CONST VALUES
    public void visit(ConstName param) {
    	const_name=param.getId();
    	
    }

    

    public void visit(ConstNum value) {
    	const_check(MJTab.intType,value,""+value.getNum());
    	Obj varNode =MJTab.insert(Obj.Con, const_name, curr_type);
    	varNode.setAdr(value.getNum());  	
    }
    
    public void visit(ConstBool value) {
    	const_check(MJTab.boolType,value,""+value.getBool_value());
    	Obj varNode =MJTab.insert(Obj.Con, const_name, curr_type);
    	//int ascii0=(int)'0';
    	if(value.getBool_value().equals("true"))
    		varNode.setAdr(1); 
    	else  varNode.setAdr(0); 
    }
    public void visit(ConstChar value) {
    	const_check(MJTab.charType,value,""+value.getChar_value());
    	Obj varNode =MJTab.insert(Obj.Con, const_name, curr_type);
		int vl=value.getChar_value(); 
    	varNode.setAdr(vl);  	
    }    
  
    
    //VISIT GLOBAL OR PRIVATE VALUES
    
	public void visit(VarParamNoBrack var) { 
		Obj obj=MJTab.findLocal(var.getName());
		if(obj==MJTab.noObj) {
			
			
			if(currentClass!=null && currentMethod==null) 
				MJTab.insert(Obj.Fld, var.getName(),curr_type); 
			else
				MJTab.insert(Obj.Var, var.getName(),curr_type); 
		}
		else report_error("Greska "+var.getName()+" je vec deklarisano", var);
		
	}

	public void visit(VarParamBrack varArray) {
		Obj obj=MJTab.findLocal(varArray.getName());
		if(obj==MJTab.noObj) {
			
			if(currentClass!=null && currentMethod==null) 
				MJTab.insert(Obj.Fld, varArray.getName(),new Struct(Struct.Array,curr_type));
			else
				MJTab.insert(Obj.Var, varArray.getName(),new Struct(Struct.Array,curr_type));		

		}
		else report_error("Greska "+varArray.getName()+" je vec deklarisano", varArray);		

	}
    
	// VISIT FORMAL PARAMETAR
	public void visit(FormParNoBrack formParNoBrack) { 
		Obj obj=MJTab.findLocal(formParNoBrack.getName());
		if(obj==MJTab.noObj) {
			
			Obj obj_formal=MJTab.insert(Obj.Var, formParNoBrack.getName(),formParNoBrack.getType().struct); 	
			++numFormal;		
			obj_formal.setFpPos(numFormal);			
			
		}
		else report_error("Greska formalni parametar "+formParNoBrack.getName()+" je vec deklarisan", formParNoBrack);			
		
		
	}

	public void visit(FormParBrack formParBrack) {
		
		Obj obj=MJTab.findLocal(formParBrack.getName());
		if(obj==MJTab.noObj) {
			
			Obj obj_formal=MJTab.insert(Obj.Var, formParBrack.getName(),new Struct(Struct.Array,formParBrack.getType().struct));	
			++numFormal;		
			obj_formal.setFpPos(numFormal);			
			
		}
		else report_error("Greska formalni parametar "+formParBrack.getName()+" je vec deklarisan", formParBrack);			
		

	}	
	//VISIT METHOD
	
    public void visit(MethodTypeName methodTypeName){
    	Obj obj=currentMethod=MJTab.findLocal(methodTypeName.getName());
		if(obj==MJTab.noObj) {
			
	    	currentMethod = MJTab.insert(Obj.Meth, methodTypeName.getName(), methodTypeName.getMethodType().struct);
	    	methodTypeName.obj = currentMethod;
	    	numFormal=0;			
			
		}    	
		else report_error("Greska metoda  "+methodTypeName.getName()+" je vec deklarisana", methodTypeName);	
		
    	MJTab.openScope();
    	
    	if(currentClass!=null) {
    		MJTab.insert(Obj.Var,"this",currentClass);
    	}
    }
    public void visit(MethType meth) {
    	meth.struct=meth.getType().struct;
    	returnNotFound=true;
    }
    public void visit(MethVoid meth) {
    	meth.struct=MJTab.noType;
    	returnVoid=true;
    }
    public void visit(MethodDecPar methodDecl){

    	MJTab.chainLocalSymbols(currentMethod);
    	if(currentMethod.getName().equals("main") && currentClass==null) {
    		mainDeclared=true;
    		if(currentMethod.getType()!=MJTab.noType || numFormal!=0) {
    			report_error("Greska main mora da bude void tipa i ne sme da ima formalne argumente", methodDecl);
    			
    		}
    	}
    	currentMethod.setLevel(numFormal);
    	
    	int localVars=0;
    	localVars=MJTab.currentScope().getnVars();
    	if(localVars>256) {
    		report_error("Greska globalna funkcija ili metoda ne sme imati vise od 256 lokalnih promenljivi",methodDecl);
    	}
    	MJTab.closeScope();
    	
    	
    	if(returnNotFound) {
    		report_error("Greska nije pronadjena return naredba u funkciji ("+currentMethod.getName()+") koja vraca rezultat(mora postajati jedan return van if i switch naredbi)",methodDecl);
    	}
    	returnNotFound=false;
    	returnVoid=false;
    	currentMethod = null;
    }    
    
    
    // VISIT CLASS
    
    public void visit(ClassName className) {
		Obj obj=MJTab.find(className.getName());
		if(obj==MJTab.noObj) {
	    	currentClass = new Struct(Struct.Class);
	    	currentClassObj=MJTab.insert(Obj.Type, className.getName(),currentClass);  
		}
		else {
			currentClass=obj.getType();
			currentClassObj=obj;
			report_error("Greska klasa  "+className.getName()+" je vec deklarisana", className);	
		}
		className.obj=currentClassObj;
		MJTab.openScope(); 
		
		MJTab.insert(Obj.Fld, "", MJTab.intType);
    }
    

    
    public void visit(ClassDeclMeth ClassDeclMeth) {
    	defineClass(ClassDeclMeth);
    }
    public void visit(ClassDeclNoMeth classDeclNoMeth) {
    	defineClass(classDeclNoMeth);
    }
    //Visit CLASS EXTEND
    public void visit(ExtendNoError extendType) {
    	extendType.struct=extendType.getType().struct;
    	extendObj=MJTab.find(extendType.getType().getTypeName());
    }
    

    
    public void visit(ExtendTypeExist extendType) {
    	currentExtendClass=extendType.getExtendErr().struct;
    	
    	//ADD ALL FIELDS FROM UPPER CLASS
    	if(currentExtendClass.getKind()!=Struct.Class) {	
    		report_error("Greska ne moze da se prosiri ("+extendObj.getName()+")",extendType);
    		currentExtendClass=MJTab.noType;
    	}
    	else if(currentExtendClass==currentClass) {
    		report_error("Greska klasa ne moze da prosiri samu sebe",extendType);
    	}
    	else {
    		for(Obj symbol:currentExtendClass.getMembers()) {
    			if(symbol.getKind()== Obj.Fld) {
    				Obj obj=MJTab.insert(symbol.getKind(), symbol.getName(), symbol.getType());
    				obj.setAdr(symbol.getAdr());
    			}
    		}
    		currentClass.setElementType(currentExtendClass);
    		report_info("Prosiruje se klasa ("+extendObj.getName()+")", extendType,true,extendObj);
    	}
    }
    
    //DESIGNATOR
    
    public void visit(DesignatorSingle designator) {
    	Obj obj = Tab.find(designator.getName());
    	
    	
    	if(obj==Tab.noObj && currentExtendClass!=null) {
    		obj=currentExtendClass.getMembersTable().searchKey(designator.getName());
    		if(obj==null)obj=MJTab.noObj;
    	}
    	if(obj == Tab.noObj){
			report_error("Greska na liniji " + designator.getLine()+ " : ime "+designator.getName()+" nije deklarisano! ", designator);
    	}
    	else {
    			report_info("("+designator.getName()+")", designator,true,obj);
    	}
    	
    	designator.obj=obj;
    }
    public void visit(DesMember desMember) {
    	Obj obj=desMember.getDesignator().obj;
  
    	if(obj!=MJTab.noObj) {
    		Struct struct=null;
    		
    		struct=obj.getType();
    		if(obj.getKind()==Obj.Type) {
    			struct=null;
    		}
    		else if(obj.getType().getKind()==Struct.Class) {
    			struct=obj.getType();
    		}
    		
    		if(struct!=null) {
        		Obj member_obj=MJTab.noObj;
        		
        		if(struct==currentClass) {
        			member_obj=MJTab.currentScope().getOuter().findSymbol(desMember.getName());
        			if(member_obj==null) {
        				member_obj=MJTab.noObj;
        				if(currentExtendClass!=null) {
        					member_obj=currentExtendClass.getMembersTable().searchKey(desMember.getName());
        					if(member_obj==null || member_obj.getKind()!=Obj.Fld)
        						member_obj=MJTab.noObj;
        				}
        			}
        		} 			
        		else {
            		for(Obj curr_obj:struct.getMembers()) {           		
            			if(curr_obj.getName().equals(desMember.getName())) {
            				member_obj=curr_obj;
            				break;
            			}
            		}    			
        		}
        		
        		if(member_obj==MJTab.noObj) {
        			report_error("Greska klasa ("+obj.getName()+") nema polje ili metodu ("+desMember.getName()+") ",desMember);
        		}
        		else {
        			report_info("Pristup polju ("+desMember.getName()+")", desMember,true,member_obj);
        		}
        		desMember.obj=member_obj;
        		
        		
    		}  		 		 		
        	else {
        		report_error("Greska  ("+obj.getName()+") nije objekat klase pa nema polje ili metodu ("+desMember.getName()+") ", desMember);
        		desMember.obj=MJTab.noObj;			
    		}

    	}
    	else {
    		report_error("Greska prazan objekat ne moze imati  polje ili metodu ("+desMember.getName()+") ", desMember);
    		desMember.obj=MJTab.noObj;				
		}    	

    }    	    
    
    
    public void visit(DesArr desArr) {
    	Obj obj=desArr.getDesignator().obj;
   	

    	if(obj.getType().getKind()!=Struct.Array) {
    		report_error("Greska ("+obj.getName()+") mora biti tipa niz", desArr);
    		desArr.obj=MJTab.noObj;
    	}
    	else {
    		desArr.obj=new Obj(Obj.Elem,"Elem:"+obj.getName(),obj.getType().getElemType());
    	}
    	
    	if(desArr.getExpr().struct!=MJTab.intType) {
    		report_error("Greska izraz za pristup clanu niza mora biti tipa (int)",desArr);
    	}
    }
    
    //DESIGNATOR STATEMENT
    
    public void visit(DesAssignNoErr desAssign) {
    	desAssign.struct=desAssign.getExpr().struct;
    }

    public void visit(DesStmAssign desStmAssign) {
    	Obj obj=desStmAssign.getDesignator().obj;
    	Struct expr=desStmAssign.getDesAssignIFErr().struct;
    	

    	if( (obj.getKind()==Obj.Var || obj.getKind()==Obj.Fld || obj.getKind()==Obj.Elem) && 
    			(MJTab.assignableToRef(expr, obj.getType()))) {
    			//report_info("("+obj.getName()+")", desStmInc);
    	}
    	else {
    		report_error("Greska izraz nije kompatibilan sa simbolom", desStmAssign); 
    	}  
    	
  	
    }
    public void visit(DesStmInc desStmInc) {
    	Obj obj=desStmInc.getDesignator().obj;
    	if((obj.getKind()==Obj.Var || obj.getKind()==Obj.Fld || obj.getKind()==Obj.Elem) &&
    			(obj.getType()==MJTab.intType )) {
    			//report_info("("+obj.getName()+")", desStmInc);

    	}
    	else {
    		report_error("Greska inkrementiranje moze samo za promenljive tipa int", desStmInc); 
    	}
    }
    
    public void visit(DesStmDec desStmDec) {
    	Obj obj=desStmDec.getDesignator().obj;
    	if((obj.getKind()==Obj.Var || obj.getKind()==Obj.Fld || obj.getKind()==Obj.Elem) &&
    			(obj.getType()==MJTab.intType )) {
    			//report_info("("+obj.getName()+")", desStmInc);
    	}
    	else {
    		report_error("Greska dekrementiranje moze samo za promenljive tipa int", desStmDec); 
    	}
    }   
    
    
    public void visit(DesStmNoActPar funct) {
    	Obj obj=funct.getDesignator().obj;
    	if(obj.getKind()==Obj.Meth) {
    		if((obj.getLevel()==0 && currentMethod!=obj)||(currentMethod==obj && formalSize()==0)) {     		
        		report_info("Koristi se rezultat metode bez parametara ("+obj.getName()+")", funct,true,obj);    			
    		}
    		else {
    			report_error("Greska metoda ("+obj.getName()+")  zahteva parametre",funct);
    		}
    			
    	}
    	else {
    		report_error("Greska ime ("+obj.getName()+") nije metoda",funct);
    	}   	
    }
    public void visit(DesStmActPar funct) {
    	Obj meth=funct.getDesignator().obj;
    	StructList list=funct.getActPars().structlist;
    	if(meth.getKind()==Obj.Meth) {
        	
        	
        	if((meth.getLevel()==list.size() && currentMethod!=meth) || (currentMethod==meth && formalSize()==list.size())) {
        		Collection<Obj> coll;
        		if(currentMethod==meth)coll=MJTab.currentScope().getLocals().symbols();
        		else coll=meth.getLocalSymbols();
        		
        		for(Obj obj:coll) {
        			int num=obj.getFpPos();
        			if(num>0) {
        				Struct s=list.getStruct(num-1);
            			if(MJTab.assignableToRef(s, obj.getType())==false) {
            				report_error("Greska parametar ("+obj.getName()+") redni broj ("+num+") nije kompatabilan sa pravim parametrom",funct);
            			}        				
        			}
        			

        		}
        		
        	}
        	else {
        		report_error("Greska funkcija ("+meth.getName()+")  prihvata "+meth.getLevel()+" parametara, dato je "+list.size(),funct);   		
        	}
    			
    	}
    	else {
    		report_error("Greska ime ("+meth.getName()+") nije funkcija/metoda",funct);
    	}  
    	if(list!=null)list.clean();
    }   
    //FACTOR VISIT METHODS 
    public void visit(FactVar fact) {
    	Obj obj=fact.getDesignator().obj;
    	fact.struct=obj.getType();
    	report_info("Koristi se promenljiva ("+obj.getName()+")", fact,true,obj);
    }
    
    public void visit(FunCallNoParam funct) {	
    	Obj obj=funct.getDesignator().obj;
    	if(obj.getKind()==Obj.Meth) {
    		if(MJTab.noType==obj.getType()) {
    			report_error("Greska funkcija bez parametara ("+obj.getName()+"ne moze se koristiti u izrazima jer nema povratnu vrednost",funct);
    		}
    		else {
        		if((obj.getLevel()==0 && currentMethod!=obj)||(currentMethod==obj && formalSize()==0)) {
            		funct.struct=obj.getType();
            		report_info("Koristi se rezultat metode bez parametara ("+obj.getName()+")", funct,true,obj);    			
        		}
        		else {
        			funct.struct=MJTab.noType;
        			report_error("Greska metoda ("+obj.getName()+")  zahteva parametre",funct);
        		}   			
    		}
		
    	}
    	else {
    		report_error("Greska ime ("+obj.getName()+") nije metoda",funct);
    		funct.struct= MJTab.noType;
    	}
    }
    public void visit(ActParsSingle par) {
    	par.structlist=new StructList();
    	par.structlist.add(par.getExpr().struct);
    }
    
    public void visit(ActParsList parList) {
    	parList.structlist=parList.getActPars().structlist;
    	parList.structlist.add(parList.getExpr().struct);
    }
    
    public void visit(FunCallParam funct) {
    	Obj meth=funct.getDesignator().obj;
    	StructList list=funct.getActPars().structlist;
    	if(meth.getKind()==Obj.Meth) {
    		if(MJTab.noType==meth.getType()) {
    			report_error("Greska funkcija sa parametrima ("+meth.getName()+"ne moze se koristiti u izrazima jer nema povratnu vrednost",funct);
    		}
    		else {
            	if((meth.getLevel()==list.size() && currentMethod!=meth) || (currentMethod==meth && formalSize()==list.size())) {
            		Collection<Obj> coll;
            		if(currentMethod==meth)coll=MJTab.currentScope().getLocals().symbols();
            		else coll=meth.getLocalSymbols();
            		
            		for(Obj obj:coll) {
            			int num=obj.getFpPos();
            			if(num>0) {
            				Struct s=list.getStruct(num-1);
                			if(MJTab.assignableToRef(s, obj.getType())==false) {
                				report_error("Greska parametar ("+obj.getName()+") redni broj ("+num+") nije kompatabilan sa pravim parametrom",funct);
                			}        				
            			}
            		}
            		funct.struct=meth.getType();
            		
            	}
            	else {
            		report_error("Greska funkcija ("+meth.getName()+")  prihvata "+meth.getLevel()+" parametara, dato je "+list.size(),funct);
            		funct.struct= MJTab.noType;    		
            	}    			
    		}       			
    	}
    	else {
    		report_error("Greska ime ("+meth.getName()+") nije metoda",funct);
    		funct.struct= MJTab.noType;
    	}
    	if(list!=null)list.clean();

    }
    
    public void visit(FactorNum fact) {
    	fact.struct=MJTab.intType;
    }
    public void visit(FactorBool fact) {
    	fact.struct=MJTab.boolType;
    }  
    public void visit(FactorChar fact) {
    	fact.struct=MJTab.charType;
    }     
    
    public void visit(FactorNew factNew) {
    	Obj obj=MJTab.find(factNew.getType().getTypeName());

		if(obj.getType().getKind()==Struct.Class) {
    		report_info("Alocira se memorija za objekat klase ("+obj.getName()+")", factNew,true,obj);  
    		factNew.struct=obj.getType();
		}
		else {
    		report_error("Greska ("+obj.getName()+") nije tip unutrasnje klase", factNew);  
    		factNew.struct=MJTab.noType;			
		}

    }
    
    public void visit(FactorNewArr factNew) {
    	Obj obj=MJTab.find(factNew.getType().getTypeName());

			
		if(factNew.getExpr().struct!=MJTab.intType) {
			report_error("Greska izraz za velicinu niza mora biti tipa (int)",factNew);
		}
		report_info("Alocira se memorija za objekat klase ("+obj.getName()+")", factNew,true,obj);  
		factNew.struct=new Struct(Struct.Array,obj.getType());

    }
    public void visit(FactorParen fact) {
    	fact.struct=fact.getExpr().struct;
    }
    
    //VISIT TERM AND EXPR
    public void visit(FactorSingle fact) {
    	fact.struct=fact.getFactor().struct;
    }
    public void visit(FactorExpr fact) {
    	if(fact.getTerm().struct==MJTab.intType && fact.getFactor().struct==MJTab.intType) {
    		fact.struct=MJTab.intType;
    	}
    	else {
    		report_error("Greska oba clana izraza mnozenja/deljenja/modula moraju biti tipa (int) ",fact);
    		fact.struct=MJTab.noType;
    	}
    }
    
    public void visit(TermSingleNeg term) {
    	if(term.getTerm().struct==MJTab.intType ) {
    		term.struct=MJTab.intType;
    	}
    	else {
    		report_error("Greska da bi clan mogao biti negativan mora biti tipa (int) ",term);
    		term.struct=MJTab.noType;
    	}   	
    }
    public void visit(TermSinglePos term) {
    	term.struct=term.getTerm().struct;
    }
    public void visit(TermExpr expr) {
    	if(expr.getTerm().struct==MJTab.intType && expr.getTermList().struct==MJTab.intType) {
    		expr.struct=MJTab.intType;
    	}
    	else {
    		report_error("Greska oba clana izraza mnozenja/deljenja/modula moraju biti tipa (int) ",expr);
    		expr.struct=MJTab.noType;
    	}   	
    }
    public void visit(ExprTermList expr) {
    	expr.struct= expr.getTermList().struct;
    }
    public void visit(ExprTern expr) {
    	
    	if(expr.getTermList().struct!=MJTab.boolType) {
    		report_error("Greska  uslovni izraz za ternarni operator nije tipa bool",expr);
    	}
    	if(MJTab.equals(expr.getTermList1().struct, expr.getTermList2().struct)) {
    			expr.struct=expr.getTermList1().struct;
    	}
    	else {
    		report_error("Greska oba clana izraza ternarnog operatora moraju biti istog tipa ",expr);
    		expr.struct=MJTab.noType;
    	}      	
    }
    
    //visit Condition
    public void visit(ElseStm elseStm) {
    	ifLvl--;
    }
    public void visit(IfOnlyStm ifOnlyStm) {
    	ifLvl--;
    }
    public void visit(CondFactNoOp condFact) {
    	//CHECK VALUE
    	if(condFact.getExpr().struct!=MJTab.boolType) {
    		report_error("Greska faktor kada nema relacioni operator mora biti tipa bool", condFact);
    	}
    	condFact.struct=MJTab.boolType;
    }
    public void visit(CondFactOp condFact) {
    	Struct expr1=condFact.getExpr().struct;
    	Struct expr2=condFact.getExpr1().struct;
    	Relop relop=condFact.getRelop();
    	if(MJTab.compatibleWith(expr1, expr2)) {
    		
    		if((expr1.getKind()==Struct.Array|| expr1.getKind()==Struct.Class) && !(relop instanceof RelEqual) && !(relop instanceof RelDiff)){
    			report_error("Greska  izmedju izraza tipa klase ili niza mogu se koristiti samo operatori (==) ili (!=) ",condFact);
    		}
    		condFact.struct=MJTab.boolType;
    	}
    	else {
    		report_error("Greska  izrazi nisu kompatibilni za relacionu operaciju ",condFact);
    		condFact.struct=MJTab.boolType;
    	}
    }
    public void visit(CondTermSingle condTerm) {
    	condTerm.struct=condTerm.getCondFact().struct;
    }
    public void visit(CondTermList condTermList) {
    	//TO DO
    	condTermList.struct=condTermList.getCondFact().struct;
    }
    
    public void visit(ConditionSingle condition) {
    	condition.struct=condition.getCondTerm().struct;
    }
    public void visit(ConditionList condition) {
    	//TO DO
    	condition.struct=condition.getCondition().struct;
    }
    
    public void visit(ConditionPar cond) {
    	cond.struct=cond.getCondition().struct;
    	ifLvl++;
    }

    //STATEMENTS VISIT
    
    public void visit(StatementIf stm) {
    	Struct cond=stm.getConditionParen().struct;
    	if(cond==MJTab.boolType) {
    		//TO DO IF 
    	}
    	else if(cond==MJTab.noType){
    		//ispravak
    	}
    	else {
    		report_error("Greska uslovni izraz kod if treba da bude tipa bool",stm);
    	}
    }
    public void visit(StatementIfElse stm) {
    	Struct cond=stm.getConditionParen().struct;
    	if(cond==MJTab.boolType) {
    		//TO DO IF ELSE
    	}
    	else if(cond==MJTab.noType){
    		//ispravak
    	}
    	else {
    		report_error("Greska uslovni izraz kod if else treba da bude tipa bool",stm);
    	}    	
    }   
    public void visit(StatementWhile stm) {
    	Struct cond=stm.getCondition().struct;
    	if(cond==MJTab.boolType) {
    		//TO DO DO WHILE
    	}
    	else {
    		report_error("Greska uslovni izraz kod if else treba da bude tipa bool",stm);
    	}   
    	
    	whileLevel--;
    }
    public void visit(DoStart start) {
    	whileLevel++;
    }
    
    public void visit(StatementSwitch stm) {
    	Struct type=stm.getExpr().struct;
    	if(type==MJTab.intType) {
    		//To do
    	}
    	else {
    		report_error("Greska izraz kod switch-a treba da bude celobrojnog tipa",stm);
    	}
    	switchLevel--;
    }
    public void visit(SwitchStart start) {
    	switchLevel++;
    }
    
    public void visit(CaseListEmpty caseList) {
    	CaseNameList  list=new CaseNameList();
    	caseList.casenamelist=list;
    	
    }
    public void visit(CaseListParam caseList) {
    	CaseNameList list=caseList.getCaseList().casenamelist;
    	int num=caseList.getCaseParam().casenamelist.getInt(0);
    	
    	if(list.contains(num)) {
    		report_error("Greska grana sa celobrojnom konstantom ("+num+") vec postoji",caseList);
    	}
    	else {
    		list.add(num);
    	}
    	caseList.casenamelist=list;
    	
    }
    public void visit(CaseParam caseParam) {
    	CaseNameList param=new CaseNameList();
    	param.add(caseParam.getNum());
    	
    	caseParam.casenamelist=param;
    }
    public void visit(StatementBreak stm) {
    	if(whileLevel>0 || switchLevel>0) {
    		//TO DO 
    	}
    	else {
    		report_error("Greska break iskaz  se moze koristiti samo unutar do-while petlje i visestrukog grananja (switch)",stm);
    	}
    }
    public void visit(StatementContinue stm) {
    	if(whileLevel>0 ) {
    		//TO DO 
    	}
    	else {
    		report_error("Greska continue iskaz  se moze koristiti samo unutar do-while petlje ",stm);
    	}    	
    }
    public void visit(StatementRet ret) {
    	if(currentMethod!=null) {
        	if(returnVoid==false) {
        		report_error("Greska funkcija nije tipa (void) mora da vraca rezultat", ret);
        	}    		
        	
    	}
    	else {
    		report_error("Greska prazan return moze samo da se koristi u telima funkcija",ret);
    	}
    }
    public void visit(StatementRetExpr ret) {
    	if(currentMethod!=null) {
        	if(returnVoid) {
        		report_error("Greska funkcija je tipa (void) pa ne treba da vraca rezultat", ret);
        	} 
        	else {
        		
            	if(MJTab.equals(currentMethod.getType(), ret.getExpr().struct)==false) {
            		report_error("Greska rezultat izraza nije ekvivalentan tipu rezultata funkcije",ret);
            	}  
            	if(ifLvl==0 && switchLevel==0)
            		returnNotFound=false;
        	}

        	
    	}
    	else {
    		report_error("Greska prazan return moze samo da se koristi u telima funkcija",ret);
    	}   	
    }
    public void visit(StatementRead stm) {
    	Obj obj=stm.getDesignator().obj;
    	if((obj.getKind()==Obj.Var || obj.getKind()==Obj.Fld || obj.getKind()==Obj.Elem) &&
    			(rpValid(obj.getType()) )) {
    			//TO DO

    	} 
    	else {
    		report_error("Greska kod read. Ne moze da se smesti vrednost u simbol koji  nije tipa int,char ili bool",stm);
    	}
    }
    
    public void visit(StatementPrintLoop stm) {
    	Struct type=stm.getExpr().struct;
    	if(rpValid(type)) {
    			
    			//TO DO
    			
    	} 
    	else {
    		report_error("Greska kod print loop. Symbol koji treba da se ispise nije tipa int,char ili bool",stm);
    	}
    }  
    public void visit(StatementPrint stm) {
    	Struct type=stm.getExpr().struct;
    	if(rpValid(type)) {
    			
    			//TO DO
    	} 
    	else {
    		report_error("Greska kod print loop. Symbol koji treba da se ispise nije tipa int,char ili bool",stm);
    	}
    }  
    
    
    //ERROR VISITS
    public void visit(ClassErrorVarSemi err) {
    	report_error("Greska kod deklaracija polja klase("+currentClassObj.getName()+"). Bio je izvrsen oporavak do (;).",err);
    }
    public void visit(ConstErrorSemi err) {
    	report_error("Greska kod deklaracije konstante . Bio je izvrsen oporavak do (;).",err);
    }
    public void visit(ConstErrorComma err) {
    	report_error("Greska kod deklaracije konstante . Bio je izvrsen oporavak do (,).",err);
    }
    public void visit(GlobalErrorVarSemi err) {
    	report_error("Greska kod deklaracije globalne promenljive . Bio je izvrsen oporavak do (;).",err);
    }
    public void visit(GlobalErrorVarComma err) {
    	report_error("Greska kod deklaracije globalne promenljive . Bio je izvrsen oporavak do (,).",err);
    }
    public void visit(ExtendErr err) {
    	err.struct=MJTab.noType;
    	report_error("Greska kod nasledjivanje klase ("+currentClassObj.getName()+") . Bio je izvrsen oporavak do ( '{' ).",err);
    }    
    public void visit(ErrorFormComma err) {
    	report_error("Greska kod deklaracija formalnih parametara funkcije ("+currentMethod.getName()+") . Bio je izvrsen oporavak do ( ,).",err);
    }
    public void visit(ErrorFormParen err) {
    	report_error("Greska kod deklaracija formalnih parametara funkcije ("+currentMethod.getName()+") . Bio je izvrsen oporavak do ( ')' ).",err);
    } 
    public void visit(ErrorCondition err) {
    	err.struct=MJTab.noType;
    	report_error("Greska kod logickog izraza kod if-a . Bio je izvrsen oporavak do ( ')' ).",err);    	
    }       
    public void visit(ErrorDesignator err) {
    	err.struct=MJTab.noType;
    	report_error("Greska kod izraza dodele . Bio je izvrsen oporavak do ( ; ).",err);    	
    }
    
    //OTHER PRIVATE METHODS
    //checks if type can be 
    private boolean rpValid(Struct type) {
    	if(type==MJTab.intType) return true;
    	if(type==MJTab.boolType) return true;
    	if(type==MJTab.charType)return true;
    	return false;
    }
    
    //checks if the type st equals the current type of the declared constants
    private boolean const_check(Struct st,SyntaxNode nd,String vl) {
    	boolean ret=true;	
    	if(!MJTab.equals(curr_type, st)) {
    		report_error("Greska:  konstanta "+const_name+" i vrednost:"+vl+" se ne poklapaju po tipu", nd); 
    		ret=false;
    	}
    	
    	if(MJTab.find(const_name)!=MJTab.noObj) {
    		report_error("Greska: konstanta "+const_name+" je vec jednom deklarisana", nd);   		
    	}
    	
    	return ret;
    		  	
    }
    
    //add all the non-overridden methods from extendedClass to currentClass
    private void addCopyUpperMeth(){
    	//ADD METHODS FROM UPPER CLASS
    	if(currentExtendClass!=null && currentExtendClass!=MJTab.noType) {
    		for(Obj ext_o:currentExtendClass.getMembers()) {
				if(ext_o.getKind()==Obj.Meth) {
					
					Obj obj=MJTab.currentScope().getLocals().searchKey(ext_o.getName());
					
					if(obj==null) {
						Obj obj_new=MJTab.insert(ext_o.getKind(),ext_o.getName(), ext_o.getType());
						HashTableDataStructure locals=new HashTableDataStructure();
						for(Obj symbol:ext_o.getLocalSymbols()) {

							Obj curr;
							if(symbol.getName().equals("this"))
								curr=new Obj(symbol.getKind(), symbol.getName(), currentClass,symbol.getAdr(),symbol.getLevel());
							else
								curr=new Obj(symbol.getKind(), symbol.getName(), symbol.getType(),symbol.getAdr(),symbol.getLevel());
						
							curr.setFpPos(symbol.getFpPos());
							locals.insertKey(curr);
						}
						
						obj_new.setLocals(locals);
					}
					else {
						if(obj.getKind()==Obj.Meth) {
							boolean wrongFormalPar=false;
							if((obj.getLevel()!=ext_o.getLevel()) || checkReturn(ext_o.getType(),obj.getType())==false) {
								wrongFormalPar=true;
							}
							else {
								Collection<Obj> ext_formal=ext_o.getLocalSymbols();
								Collection<Obj> obj_formal=obj.getLocalSymbols();
								
								//CHEKS IF FORMAL PARAMS ARE THE SAME TYPE
								for(Obj ext_iterator:ext_formal) {
									int pos=ext_iterator.getFpPos();
									if(pos>=1 && ext_iterator.getFpPos()<=ext_o.getLevel()) {
										for(Obj obj_iterator:obj_formal) {
											if(pos==obj_iterator.getFpPos()) {		
												//System.out.println()
												if(ext_iterator.getType().equals(obj_iterator.getType())==false) {
													wrongFormalPar=true;
												}
												break;
											}
										}
									}
									if(wrongFormalPar)break;
								}
							}
							
							if(wrongFormalPar) {
								report_error("Greska kod klase  ("+currentClassObj.getName()+"), popis  redefinisane metode ("
										+obj.getName()+") se razlikuje od popisa nadklase", null);	
							}
							
						}
						else {
							 report_error("Greska klasa  "+currentClassObj.getName()+" redefinise metodu "+obj.getName()+" kao polje", null);								
						}
					}
				}
			}
    		
    		currentExtendClass=null;
    		
    	}    	
    }
    
    //Checks if return is of same type or if it returns a subclass of an upperclass
    private boolean checkReturn(Struct upper,Struct sub) {
    	
    	if(upper==sub)return true;
    	
    	if(upper.getKind()==Struct.Class && sub.getKind()==Struct.Class) {
        	Struct curr=sub.getElemType();
        	while(curr!=null) {
        		if(curr==upper) return true;
        		curr=curr.getElemType();
        		
        	}   		
    	}

    	return false;
    }
    
    //
    private void defineClass(ClassDecl classDecl) {
    	//add methods from upper class and null currentExtendClass
    	addCopyUpperMeth();
    	MJTab.chainLocalSymbols(currentClass);
    	MJTab.closeScope();  
 
    	int numberFields=currentClass.getNumberOfFields();
    	if(numberFields>65536) {
    		report_error("Greska klasa ima vise od 65536 polja",classDecl);
    	}    	
    	
    	currentClass=null;
    	currentClassObj=null;
    }    
    
    //finds the size of the number of formal parameters
    private int formalSize() {
    	int num=0;
    	for(Obj obj:MJTab.currentScope().getLocals().symbols()) {
    		if(obj.getFpPos()>0)num++;
    	}
    	return num;
    }
    
    //funkcije ispisa za log
	public void visitObjNode(Obj objToVisit,StringBuilder output) {
		//output.append("[");
		switch (objToVisit.getKind()) {
		case Obj.Con:  output.append("Con "); break;
		case Obj.Var:  output.append("Var "); break;
		case Obj.Type: output.append("Type "); break;
		case Obj.Meth: output.append("Meth "); break;
		case Obj.Fld:  output.append("Fld "); break;
		case Obj.Prog: output.append("Prog "); break;
		}
		
		output.append(objToVisit.getName());
		output.append(": ");
		
		if ((Obj.Var == objToVisit.getKind()) && "this".equalsIgnoreCase(objToVisit.getName()))
			output.append("");
		else
			visitStructNode(objToVisit.getType(), output);
		
		output.append(", ");
		output.append(objToVisit.getAdr());
		output.append(", ");
		output.append(objToVisit.getLevel() + " ");
		//output.append("]");
		
	}    
	public void visitStructNode(Struct structToVisit,StringBuilder output) {
		switch (structToVisit.getKind()) {
		case Struct.None:
			output.append("notype");
			break;
		case Struct.Int:
			output.append("int");
			break;
		case Struct.Char:
			output.append("char");
			break;
		case Struct.Bool:
			output.append("bool");
			break;				
		case Struct.Array:
			output.append("Arr of ");
			
			switch (structToVisit.getElemType().getKind()) {
			case Struct.None:
				output.append("notype");
				break;
			case Struct.Int:
				output.append("int");
				break;
			case Struct.Char:
				output.append("char");
				break;
			case Struct.Bool:
				output.append("bool");
				break;					
			case Struct.Class:
				output.append("Class");
				break;
			}
			break;
		case Struct.Class:
			output.append("Class [");
			output.append("]");
			break;
		}

	}    
    
    public boolean passed(){
    	return !errorDetected;
    }	
}
