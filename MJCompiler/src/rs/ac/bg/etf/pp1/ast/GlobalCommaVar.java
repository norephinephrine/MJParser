// generated with ast extension for cup
// version 0.8
// 21/0/2021 15:53:15


package rs.ac.bg.etf.pp1.ast;

public class GlobalCommaVar extends GlobalVarComma {

    private VarParam VarParam;

    public GlobalCommaVar (VarParam VarParam) {
        this.VarParam=VarParam;
        if(VarParam!=null) VarParam.setParent(this);
    }

    public VarParam getVarParam() {
        return VarParam;
    }

    public void setVarParam(VarParam VarParam) {
        this.VarParam=VarParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarParam!=null) VarParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarParam!=null) VarParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarParam!=null) VarParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalCommaVar(\n");

        if(VarParam!=null)
            buffer.append(VarParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalCommaVar]");
        return buffer.toString();
    }
}
