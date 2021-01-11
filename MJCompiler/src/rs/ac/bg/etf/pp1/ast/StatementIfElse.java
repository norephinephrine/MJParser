// generated with ast extension for cup
// version 0.8
// 10/0/2021 5:13:6


package rs.ac.bg.etf.pp1.ast;

public class StatementIfElse extends Statement {

    private ConditionParen ConditionParen;
    private Statement Statement;
    private ElseStart ElseStart;
    private ElseStm ElseStm;

    public StatementIfElse (ConditionParen ConditionParen, Statement Statement, ElseStart ElseStart, ElseStm ElseStm) {
        this.ConditionParen=ConditionParen;
        if(ConditionParen!=null) ConditionParen.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
        this.ElseStart=ElseStart;
        if(ElseStart!=null) ElseStart.setParent(this);
        this.ElseStm=ElseStm;
        if(ElseStm!=null) ElseStm.setParent(this);
    }

    public ConditionParen getConditionParen() {
        return ConditionParen;
    }

    public void setConditionParen(ConditionParen ConditionParen) {
        this.ConditionParen=ConditionParen;
    }

    public Statement getStatement() {
        return Statement;
    }

    public void setStatement(Statement Statement) {
        this.Statement=Statement;
    }

    public ElseStart getElseStart() {
        return ElseStart;
    }

    public void setElseStart(ElseStart ElseStart) {
        this.ElseStart=ElseStart;
    }

    public ElseStm getElseStm() {
        return ElseStm;
    }

    public void setElseStm(ElseStm ElseStm) {
        this.ElseStm=ElseStm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionParen!=null) ConditionParen.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
        if(ElseStart!=null) ElseStart.accept(visitor);
        if(ElseStm!=null) ElseStm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionParen!=null) ConditionParen.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
        if(ElseStart!=null) ElseStart.traverseTopDown(visitor);
        if(ElseStm!=null) ElseStm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionParen!=null) ConditionParen.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        if(ElseStart!=null) ElseStart.traverseBottomUp(visitor);
        if(ElseStm!=null) ElseStm.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementIfElse(\n");

        if(ConditionParen!=null)
            buffer.append(ConditionParen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(Statement!=null)
            buffer.append(Statement.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseStart!=null)
            buffer.append(ElseStart.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ElseStm!=null)
            buffer.append(ElseStm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementIfElse]");
        return buffer.toString();
    }
}
