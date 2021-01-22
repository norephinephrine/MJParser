// generated with ast extension for cup
// version 0.8
// 21/0/2021 15:53:15


package rs.ac.bg.etf.pp1.ast;

public class GlobalSingleVar extends GlobalVarList {

    private GlobalVarParamSemi GlobalVarParamSemi;

    public GlobalSingleVar (GlobalVarParamSemi GlobalVarParamSemi) {
        this.GlobalVarParamSemi=GlobalVarParamSemi;
        if(GlobalVarParamSemi!=null) GlobalVarParamSemi.setParent(this);
    }

    public GlobalVarParamSemi getGlobalVarParamSemi() {
        return GlobalVarParamSemi;
    }

    public void setGlobalVarParamSemi(GlobalVarParamSemi GlobalVarParamSemi) {
        this.GlobalVarParamSemi=GlobalVarParamSemi;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(GlobalVarParamSemi!=null) GlobalVarParamSemi.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(GlobalVarParamSemi!=null) GlobalVarParamSemi.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(GlobalVarParamSemi!=null) GlobalVarParamSemi.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalSingleVar(\n");

        if(GlobalVarParamSemi!=null)
            buffer.append(GlobalVarParamSemi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [GlobalSingleVar]");
        return buffer.toString();
    }
}
