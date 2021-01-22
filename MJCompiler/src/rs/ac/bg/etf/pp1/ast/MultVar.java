// generated with ast extension for cup
// version 0.8
// 21/0/2021 15:53:15


package rs.ac.bg.etf.pp1.ast;

public class MultVar extends VarList {

    private VarList VarList;
    private VarParam VarParam;

    public MultVar (VarList VarList, VarParam VarParam) {
        this.VarList=VarList;
        if(VarList!=null) VarList.setParent(this);
        this.VarParam=VarParam;
        if(VarParam!=null) VarParam.setParent(this);
    }

    public VarList getVarList() {
        return VarList;
    }

    public void setVarList(VarList VarList) {
        this.VarList=VarList;
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
        if(VarList!=null) VarList.accept(visitor);
        if(VarParam!=null) VarParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarList!=null) VarList.traverseTopDown(visitor);
        if(VarParam!=null) VarParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarList!=null) VarList.traverseBottomUp(visitor);
        if(VarParam!=null) VarParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultVar(\n");

        if(VarList!=null)
            buffer.append(VarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarParam!=null)
            buffer.append(VarParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultVar]");
        return buffer.toString();
    }
}
