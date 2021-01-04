// generated with ast extension for cup
// version 0.8
// 3/0/2021 22:13:2


package rs.ac.bg.etf.pp1.ast;

public class VarDeclUse extends VarDeclIf {

    private VarDeclIf VarDeclIf;
    private VarDecl VarDecl;

    public VarDeclUse (VarDeclIf VarDeclIf, VarDecl VarDecl) {
        this.VarDeclIf=VarDeclIf;
        if(VarDeclIf!=null) VarDeclIf.setParent(this);
        this.VarDecl=VarDecl;
        if(VarDecl!=null) VarDecl.setParent(this);
    }

    public VarDeclIf getVarDeclIf() {
        return VarDeclIf;
    }

    public void setVarDeclIf(VarDeclIf VarDeclIf) {
        this.VarDeclIf=VarDeclIf;
    }

    public VarDecl getVarDecl() {
        return VarDecl;
    }

    public void setVarDecl(VarDecl VarDecl) {
        this.VarDecl=VarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(VarDeclIf!=null) VarDeclIf.accept(visitor);
        if(VarDecl!=null) VarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(VarDeclIf!=null) VarDeclIf.traverseTopDown(visitor);
        if(VarDecl!=null) VarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(VarDeclIf!=null) VarDeclIf.traverseBottomUp(visitor);
        if(VarDecl!=null) VarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("VarDeclUse(\n");

        if(VarDeclIf!=null)
            buffer.append(VarDeclIf.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(VarDecl!=null)
            buffer.append(VarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [VarDeclUse]");
        return buffer.toString();
    }
}
