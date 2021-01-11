// generated with ast extension for cup
// version 0.8
// 10/0/2021 5:13:6


package rs.ac.bg.etf.pp1.ast;

public class ConstSemi extends ConstParamSemi {

    private ConstName ConstName;
    private ConstParam ConstParam;

    public ConstSemi (ConstName ConstName, ConstParam ConstParam) {
        this.ConstName=ConstName;
        if(ConstName!=null) ConstName.setParent(this);
        this.ConstParam=ConstParam;
        if(ConstParam!=null) ConstParam.setParent(this);
    }

    public ConstName getConstName() {
        return ConstName;
    }

    public void setConstName(ConstName ConstName) {
        this.ConstName=ConstName;
    }

    public ConstParam getConstParam() {
        return ConstParam;
    }

    public void setConstParam(ConstParam ConstParam) {
        this.ConstParam=ConstParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConstName!=null) ConstName.accept(visitor);
        if(ConstParam!=null) ConstParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConstName!=null) ConstName.traverseTopDown(visitor);
        if(ConstParam!=null) ConstParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConstName!=null) ConstName.traverseBottomUp(visitor);
        if(ConstParam!=null) ConstParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ConstSemi(\n");

        if(ConstName!=null)
            buffer.append(ConstName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ConstParam!=null)
            buffer.append(ConstParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ConstSemi]");
        return buffer.toString();
    }
}
