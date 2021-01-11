// generated with ast extension for cup
// version 0.8
// 10/0/2021 5:13:6


package rs.ac.bg.etf.pp1.ast;

public class GlobalMultVar extends GlobalVarList {

    private GlobalVarComma GlobalVarComma;
    private GlobalVarList GlobalVarList;

    public GlobalMultVar (GlobalVarComma GlobalVarComma, GlobalVarList GlobalVarList) {
        this.GlobalVarComma=GlobalVarComma;
        if(GlobalVarComma!=null) GlobalVarComma.setParent(this);
        this.GlobalVarList=GlobalVarList;
        if(GlobalVarList!=null) GlobalVarList.setParent(this);
    }

    public GlobalVarComma getGlobalVarComma() {
        return GlobalVarComma;
    }

    public void setGlobalVarComma(GlobalVarComma GlobalVarComma) {
        this.GlobalVarComma=GlobalVarComma;
    }

    public GlobalVarList getGlobalVarList() {
        return GlobalVarList;
    }

    public void setGlobalVarList(GlobalVarList GlobalVarList) {
        this.GlobalVarList=GlobalVarList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobalVarComma!=null) GlobalVarComma.accept(visitor);
        if(GlobalVarList!=null) GlobalVarList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalVarComma!=null) GlobalVarComma.traverseTopDown(visitor);
        if(GlobalVarList!=null) GlobalVarList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalVarComma!=null) GlobalVarComma.traverseBottomUp(visitor);
        if(GlobalVarList!=null) GlobalVarList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalMultVar(\n");

        if(GlobalVarComma!=null)
            buffer.append(GlobalVarComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(GlobalVarList!=null)
            buffer.append(GlobalVarList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalMultVar]");
        return buffer.toString();
    }
}
