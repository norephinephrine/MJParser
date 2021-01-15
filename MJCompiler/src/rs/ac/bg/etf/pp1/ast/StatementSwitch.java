// generated with ast extension for cup
// version 0.8
// 15/0/2021 17:43:44


package rs.ac.bg.etf.pp1.ast;

public class StatementSwitch extends Statement {

    private SwitchStart SwitchStart;
    private Expr Expr;
    private SwitchExpr SwitchExpr;
    private CaseList CaseList;

    public StatementSwitch (SwitchStart SwitchStart, Expr Expr, SwitchExpr SwitchExpr, CaseList CaseList) {
        this.SwitchStart=SwitchStart;
        if(SwitchStart!=null) SwitchStart.setParent(this);
        this.Expr=Expr;
        if(Expr!=null) Expr.setParent(this);
        this.SwitchExpr=SwitchExpr;
        if(SwitchExpr!=null) SwitchExpr.setParent(this);
        this.CaseList=CaseList;
        if(CaseList!=null) CaseList.setParent(this);
    }

    public SwitchStart getSwitchStart() {
        return SwitchStart;
    }

    public void setSwitchStart(SwitchStart SwitchStart) {
        this.SwitchStart=SwitchStart;
    }

    public Expr getExpr() {
        return Expr;
    }

    public void setExpr(Expr Expr) {
        this.Expr=Expr;
    }

    public SwitchExpr getSwitchExpr() {
        return SwitchExpr;
    }

    public void setSwitchExpr(SwitchExpr SwitchExpr) {
        this.SwitchExpr=SwitchExpr;
    }

    public CaseList getCaseList() {
        return CaseList;
    }

    public void setCaseList(CaseList CaseList) {
        this.CaseList=CaseList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(SwitchStart!=null) SwitchStart.accept(visitor);
        if(Expr!=null) Expr.accept(visitor);
        if(SwitchExpr!=null) SwitchExpr.accept(visitor);
        if(CaseList!=null) CaseList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(SwitchStart!=null) SwitchStart.traverseTopDown(visitor);
        if(Expr!=null) Expr.traverseTopDown(visitor);
        if(SwitchExpr!=null) SwitchExpr.traverseTopDown(visitor);
        if(CaseList!=null) CaseList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(SwitchStart!=null) SwitchStart.traverseBottomUp(visitor);
        if(Expr!=null) Expr.traverseBottomUp(visitor);
        if(SwitchExpr!=null) SwitchExpr.traverseBottomUp(visitor);
        if(CaseList!=null) CaseList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementSwitch(\n");

        if(SwitchStart!=null)
            buffer.append(SwitchStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Expr!=null)
            buffer.append(Expr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(SwitchExpr!=null)
            buffer.append(SwitchExpr.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseList!=null)
            buffer.append(CaseList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementSwitch]");
        return buffer.toString();
    }
}
