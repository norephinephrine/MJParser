package rs.ac.bg.etf.pp1.visitor;

import rs.ac.bg.etf.pp1.ast.FormParBrack;
import rs.ac.bg.etf.pp1.ast.FormParNoBrack;
import rs.ac.bg.etf.pp1.ast.VarParamBrack;
import rs.ac.bg.etf.pp1.ast.VarParamNoBrack;
import rs.ac.bg.etf.pp1.ast.VisitorAdaptor;

public class CountVisitor extends VisitorAdaptor {
	protected int count;
	
	public int getCount(){
		return count;
	}
	
	public static class FormParamCounter extends CountVisitor{
	
		public void visit(FormParBrack formPar){
			count++;
		}
		public void visit(FormParNoBrack formPar) {
			count++;
		}
	}
	
	public static class VarCounter extends CountVisitor{
		
		public void visit(VarParamBrack varDecl){
			count++;
		}
		public void visit(VarParamNoBrack varDecl) {
			count++;
		}
	}
}
