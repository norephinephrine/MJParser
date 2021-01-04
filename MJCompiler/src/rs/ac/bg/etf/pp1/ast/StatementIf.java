// generated with ast extension for cup
// version 0.8
// 3/0/2021 22:13:2


package rs.ac.bg.etf.pp1.ast;

public class StatementIf extends Statement {

    private ConditionParen ConditionParen;
    private Statement Statement;

    public StatementIf (ConditionParen ConditionParen, Statement Statement) {
        this.ConditionParen=ConditionParen;
        if(ConditionParen!=null) ConditionParen.setParent(this);
        this.Statement=Statement;
        if(Statement!=null) Statement.setParent(this);
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

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionParen!=null) ConditionParen.accept(visitor);
        if(Statement!=null) Statement.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionParen!=null) ConditionParen.traverseTopDown(visitor);
        if(Statement!=null) Statement.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionParen!=null) ConditionParen.traverseBottomUp(visitor);
        if(Statement!=null) Statement.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("StatementIf(\n");

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

        buffer.append(tab);
        buffer.append(") [StatementIf]");
        return buffer.toString();
    }
}
