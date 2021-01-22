// generated with ast extension for cup
// version 0.8
// 21/0/2021 15:53:15


package rs.ac.bg.etf.pp1.ast;

public class MethodLocal implements SyntaxNode {

    private SyntaxNode parent;
    private int line;
    private VarDeclIf VarDeclIf;

    public MethodLocal (VarDeclIf VarDeclIf) {
        this.VarDeclIf=VarDeclIf;
        if(VarDeclIf!=null) VarDeclIf.setParent(this);
    }

    public VarDeclIf getVarDeclIf() {
        return VarDeclIf;
    }

    public void setVarDeclIf(VarDeclIf VarDeclIf) {
        this.VarDeclIf=VarDeclIf;
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
        if(VarDeclIf!=null) VarDeclIf.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclIf!=null) VarDeclIf.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclIf!=null) VarDeclIf.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodLocal(\n");

        if(VarDeclIf!=null)
            buffer.append(VarDeclIf.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodLocal]");
        return buffer.toString();
    }
}
