// generated with ast extension for cup
// version 0.8
// 21/0/2021 15:53:15


package rs.ac.bg.etf.pp1.ast;

public class StatementPrintLoop extends Statement {

    private Expr Expr;
    private Integer numLoop;

    public StatementPrintLoop (Expr Expr, Integer numLoop) {
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.numLoop=numLoop;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public Integer getNumLoop() {
        return numLoop;
    }

    public void setNumLoop(Integer numLoop) {
        this.numLoop=numLoop;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(Expr!=null) Expr.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementPrintLoop(\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(" "+tab+numLoop);
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementPrintLoop]");
        return buffer.toString();
    }
}
