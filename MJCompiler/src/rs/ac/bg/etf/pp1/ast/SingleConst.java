// generated with ast extension for cup
// version 0.8
// 3/0/2021 22:13:2


package rs.ac.bg.etf.pp1.ast;

public class SingleConst extends ConstList {

    private ConstParamSemi ConstParamSemi;

    public SingleConst (ConstParamSemi ConstParamSemi) {
        this.ConstParamSemi=ConstParamSemi;
        if(ConstParamSemi!=null) ConstParamSemi.setParent(this);
    }

    public ConstParamSemi getConstParamSemi() {
        return ConstParamSemi;
    }

    public void setConstParamSemi(ConstParamSemi ConstParamSemi) {
        this.ConstParamSemi=ConstParamSemi;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstParamSemi!=null) ConstParamSemi.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstParamSemi!=null) ConstParamSemi.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstParamSemi!=null) ConstParamSemi.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("SingleConst(\n");

        if(ConstParamSemi!=null)
            buffer.append(ConstParamSemi.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [SingleConst]");
        return buffer.toString();
    }
}
