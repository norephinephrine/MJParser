// generated with ast extension for cup
// version 0.8
// 10/0/2021 5:13:6


package rs.ac.bg.etf.pp1.ast;

public class ExtendTypeExist extends ExtendType {

    private ExtendErr ExtendErr;

    public ExtendTypeExist (ExtendErr ExtendErr) {
        this.ExtendErr=ExtendErr;
        if(ExtendErr!=null) ExtendErr.setParent(this);
    }

    public ExtendErr getExtendErr() {
        return ExtendErr;
    }

    public void setExtendErr(ExtendErr ExtendErr) {
        this.ExtendErr=ExtendErr;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ExtendErr!=null) ExtendErr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ExtendErr!=null) ExtendErr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ExtendErr!=null) ExtendErr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExtendTypeExist(\n");

        if(ExtendErr!=null)
            buffer.append(ExtendErr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExtendTypeExist]");
        return buffer.toString();
    }
}
