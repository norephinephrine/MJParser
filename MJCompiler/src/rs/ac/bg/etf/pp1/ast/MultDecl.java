// generated with ast extension for cup
// version 0.8
// 10/0/2021 5:13:6


package rs.ac.bg.etf.pp1.ast;

public class MultDecl extends DecList {

    private DecList DecList;
    private DecSingle DecSingle;

    public MultDecl (DecList DecList, DecSingle DecSingle) {
        this.DecList=DecList;
        if(DecList!=null) DecList.setParent(this);
        this.DecSingle=DecSingle;
        if(DecSingle!=null) DecSingle.setParent(this);
    }

    public DecList getDecList() {
        return DecList;
    }

    public void setDecList(DecList DecList) {
        this.DecList=DecList;
    }

    public DecSingle getDecSingle() {
        return DecSingle;
    }

    public void setDecSingle(DecSingle DecSingle) {
        this.DecSingle=DecSingle;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(DecList!=null) DecList.accept(visitor);
        if(DecSingle!=null) DecSingle.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(DecList!=null) DecList.traverseTopDown(visitor);
        if(DecSingle!=null) DecSingle.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(DecList!=null) DecList.traverseBottomUp(visitor);
        if(DecSingle!=null) DecSingle.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MultDecl(\n");

        if(DecList!=null)
            buffer.append(DecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(DecSingle!=null)
            buffer.append(DecSingle.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MultDecl]");
        return buffer.toString();
    }
}
