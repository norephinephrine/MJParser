// generated with ast extension for cup
// version 0.8
// 10/0/2021 5:13:6


package rs.ac.bg.etf.pp1.ast;

public class CaseParam implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    public rs.ac.bg.etf.pp1.CaseNameList casenamelist = null;

    private Integer num;
    private CaseColon CaseColon;
    private StatementList StatementList;

    public CaseParam (Integer num, CaseColon CaseColon, StatementList StatementList) {
        this.num=num;
        this.CaseColon=CaseColon;
        if(CaseColon!=null) CaseColon.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num=num;
    }

    public CaseColon getCaseColon() {
        return CaseColon;
    }

    public void setCaseColon(CaseColon CaseColon) {
        this.CaseColon=CaseColon;
    }

    public StatementList getStatementList() {
        return StatementList;
    }

    public void setStatementList(StatementList StatementList) {
        this.StatementList=StatementList;
    }

    public SyntaxNode getParent() {
        return parent;
    }

    public void setParent(SyntaxNode parent) {
        this.parent=parent;
    }

    public int getLine() {
        return line;
    }

    public void setLine(int line) {
        this.line=line;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CaseColon!=null) CaseColon.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CaseColon!=null) CaseColon.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CaseColon!=null) CaseColon.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CaseParam(\n");

        buffer.append(" "+tab+num);
        buffer.append("\n");

        if(CaseColon!=null)
            buffer.append(CaseColon.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CaseParam]");
        return buffer.toString();
    }
}
