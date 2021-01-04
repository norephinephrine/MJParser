// generated with ast extension for cup
// version 0.8
// 3/0/2021 22:13:2


package rs.ac.bg.etf.pp1.ast;

public class ExprTern extends Expr {

    private TermList TermList;
    private TermList TermList1;
    private TermList TermList2;

    public ExprTern (TermList TermList, TermList TermList1, TermList TermList2) {
        this.TermList=TermList;
        if(TermList!=null) TermList.setParent(this);
        this.TermList1=TermList1;
        if(TermList1!=null) TermList1.setParent(this);
        this.TermList2=TermList2;
        if(TermList2!=null) TermList2.setParent(this);
    }

    public TermList getTermList() {
        return TermList;
    }

    public void setTermList(TermList TermList) {
        this.TermList=TermList;
    }

    public TermList getTermList1() {
        return TermList1;
    }

    public void setTermList1(TermList TermList1) {
        this.TermList1=TermList1;
    }

    public TermList getTermList2() {
        return TermList2;
    }

    public void setTermList2(TermList TermList2) {
        this.TermList2=TermList2;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(TermList!=null) TermList.accept(visitor);
        if(TermList1!=null) TermList1.accept(visitor);
        if(TermList2!=null) TermList2.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(TermList!=null) TermList.traverseTopDown(visitor);
        if(TermList1!=null) TermList1.traverseTopDown(visitor);
        if(TermList2!=null) TermList2.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(TermList!=null) TermList.traverseBottomUp(visitor);
        if(TermList1!=null) TermList1.traverseBottomUp(visitor);
        if(TermList2!=null) TermList2.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ExprTern(\n");

        if(TermList!=null)
            buffer.append(TermList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TermList1!=null)
            buffer.append(TermList1.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(TermList2!=null)
            buffer.append(TermList2.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ExprTern]");
        return buffer.toString();
    }
}
