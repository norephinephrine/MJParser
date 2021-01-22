// generated with ast extension for cup
// version 0.8
// 21/0/2021 15:53:15


package rs.ac.bg.etf.pp1.ast;

public class CaseDefault extends CaseParam {

    private CaseColonDef CaseColonDef;
    private StatementList StatementList;

    public CaseDefault (CaseColonDef CaseColonDef, StatementList StatementList) {
        this.CaseColonDef=CaseColonDef;
        if(CaseColonDef!=null) CaseColonDef.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public CaseColonDef getCaseColonDef() {
        return CaseColonDef;
    }

    public void setCaseColonDef(CaseColonDef CaseColonDef) {
        this.CaseColonDef=CaseColonDef;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CaseColonDef!=null) CaseColonDef.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CaseColonDef!=null) CaseColonDef.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CaseColonDef!=null) CaseColonDef.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CaseDefault(\n");

        if(CaseColonDef!=null)
            buffer.append(CaseColonDef.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CaseDefault]");
        return buffer.toString();
    }
}
