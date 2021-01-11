// generated with ast extension for cup
// version 0.8
// 10/0/2021 5:13:6


package rs.ac.bg.etf.pp1.ast;

public class MethodDecPar implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private MethodTypeName MethodTypeName;
    private FormParListIf FormParListIf;
    private MethodLocal MethodLocal;
    private StatementList StatementList;

    public MethodDecPar (MethodTypeName MethodTypeName, FormParListIf FormParListIf, MethodLocal MethodLocal, StatementList StatementList) {
        this.MethodTypeName=MethodTypeName;
        if(MethodTypeName!=null) MethodTypeName.setParent(this);
        this.FormParListIf=FormParListIf;
        if(FormParListIf!=null) FormParListIf.setParent(this);
        this.MethodLocal=MethodLocal;
        if(MethodLocal!=null) MethodLocal.setParent(this);
        this.StatementList=StatementList;
        if(StatementList!=null) StatementList.setParent(this);
    }

    public MethodTypeName getMethodTypeName() {
        return MethodTypeName;
    }

    public void setMethodTypeName(MethodTypeName MethodTypeName) {
        this.MethodTypeName=MethodTypeName;
    }

    public FormParListIf getFormParListIf() {
        return FormParListIf;
    }

    public void setFormParListIf(FormParListIf FormParListIf) {
        this.FormParListIf=FormParListIf;
    }

    public MethodLocal getMethodLocal() {
        return MethodLocal;
    }

    public void setMethodLocal(MethodLocal MethodLocal) {
        this.MethodLocal=MethodLocal;
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
        if(MethodTypeName!=null) MethodTypeName.accept(visitor);
        if(FormParListIf!=null) FormParListIf.accept(visitor);
        if(MethodLocal!=null) MethodLocal.accept(visitor);
        if(StatementList!=null) StatementList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodTypeName!=null) MethodTypeName.traverseTopDown(visitor);
        if(FormParListIf!=null) FormParListIf.traverseTopDown(visitor);
        if(MethodLocal!=null) MethodLocal.traverseTopDown(visitor);
        if(StatementList!=null) StatementList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodTypeName!=null) MethodTypeName.traverseBottomUp(visitor);
        if(FormParListIf!=null) FormParListIf.traverseBottomUp(visitor);
        if(MethodLocal!=null) MethodLocal.traverseBottomUp(visitor);
        if(StatementList!=null) StatementList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDecPar(\n");

        if(MethodTypeName!=null)
            buffer.append(MethodTypeName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(FormParListIf!=null)
            buffer.append(FormParListIf.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodLocal!=null)
            buffer.append(MethodLocal.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(StatementList!=null)
            buffer.append(StatementList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDecPar]");
        return buffer.toString();
    }
}
