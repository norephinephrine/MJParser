// generated with ast extension for cup
// version 0.8
// 3/0/2021 22:13:2


package rs.ac.bg.etf.pp1.ast;

public class MultConst extends ConstList {

    private ConstParamComma ConstParamComma;
    private ConstList ConstList;

    public MultConst (ConstParamComma ConstParamComma, ConstList ConstList) {
        this.ConstParamComma=ConstParamComma;
        if(ConstParamComma!=null) ConstParamComma.setParent(this);
        this.ConstList=ConstList;
        if(ConstList!=null) ConstList.setParent(this);
    }

    public ConstParamComma getConstParamComma() {
        return ConstParamComma;
    }

    public void setConstParamComma(ConstParamComma ConstParamComma) {
        this.ConstParamComma=ConstParamComma;
    }

    public ConstList getConstList() {
        return ConstList;
    }

    public void setConstList(ConstList ConstList) {
        this.ConstList=ConstList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstParamComma!=null) ConstParamComma.accept(visitor);
        if(ConstList!=null) ConstList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstParamComma!=null) ConstParamComma.traverseTopDown(visitor);
        if(ConstList!=null) ConstList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstParamComma!=null) ConstParamComma.traverseBottomUp(visitor);
        if(ConstList!=null) ConstList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultConst(\n");

        if(ConstParamComma!=null)
            buffer.append(ConstParamComma.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstList!=null)
            buffer.append(ConstList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultConst]");
        return buffer.toString();
    }
}
