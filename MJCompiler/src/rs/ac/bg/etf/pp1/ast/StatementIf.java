// generated with ast extension for cup
// version 0.8
// 21/0/2021 15:53:15


package rs.ac.bg.etf.pp1.ast;

public class StatementIf extends Statement {

    private ConditionParen ConditionParen;
    private IfOnlyStm IfOnlyStm;

    public StatementIf (ConditionParen ConditionParen, IfOnlyStm IfOnlyStm) {
        this.ConditionParen=ConditionParen;
        if(ConditionParen!=null) ConditionParen.setParent(this);
        this.IfOnlyStm=IfOnlyStm;
        if(IfOnlyStm!=null) IfOnlyStm.setParent(this);
    }

    public ConditionParen getConditionParen() {
        return ConditionParen;
    }

    public void setConditionParen(ConditionParen ConditionParen) {
        this.ConditionParen=ConditionParen;
    }

    public IfOnlyStm getIfOnlyStm() {
        return IfOnlyStm;
    }

    public void setIfOnlyStm(IfOnlyStm IfOnlyStm) {
        this.IfOnlyStm=IfOnlyStm;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ConditionParen!=null) ConditionParen.accept(visitor);
        if(IfOnlyStm!=null) IfOnlyStm.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ConditionParen!=null) ConditionParen.traverseTopDown(visitor);
        if(IfOnlyStm!=null) IfOnlyStm.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ConditionParen!=null) ConditionParen.traverseBottomUp(visitor);
        if(IfOnlyStm!=null) IfOnlyStm.traverseBottomUp(visitor);
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

        if(IfOnlyStm!=null)
            buffer.append(IfOnlyStm.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [StatementIf]");
        return buffer.toString();
    }
}
