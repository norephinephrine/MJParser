// generated with ast extension for cup
// version 0.8
// 3/0/2021 22:13:2


package rs.ac.bg.etf.pp1.ast;

public class ClassVarDeclUse extends ClassVarDeclIf {

    private ClassVarDeclIf ClassVarDeclIf;
    private ClassVarDecl ClassVarDecl;

    public ClassVarDeclUse (ClassVarDeclIf ClassVarDeclIf, ClassVarDecl ClassVarDecl) {
        this.ClassVarDeclIf=ClassVarDeclIf;
        if(ClassVarDeclIf!=null) ClassVarDeclIf.setParent(this);
        this.ClassVarDecl=ClassVarDecl;
        if(ClassVarDecl!=null) ClassVarDecl.setParent(this);
    }

    public ClassVarDeclIf getClassVarDeclIf() {
        return ClassVarDeclIf;
    }

    public void setClassVarDeclIf(ClassVarDeclIf ClassVarDeclIf) {
        this.ClassVarDeclIf=ClassVarDeclIf;
    }

    public ClassVarDecl getClassVarDecl() {
        return ClassVarDecl;
    }

    public void setClassVarDecl(ClassVarDecl ClassVarDecl) {
        this.ClassVarDecl=ClassVarDecl;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassVarDeclIf!=null) ClassVarDeclIf.accept(visitor);
        if(ClassVarDecl!=null) ClassVarDecl.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassVarDeclIf!=null) ClassVarDeclIf.traverseTopDown(visitor);
        if(ClassVarDecl!=null) ClassVarDecl.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassVarDeclIf!=null) ClassVarDeclIf.traverseBottomUp(visitor);
        if(ClassVarDecl!=null) ClassVarDecl.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassVarDeclUse(\n");

        if(ClassVarDeclIf!=null)
            buffer.append(ClassVarDeclIf.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarDecl!=null)
            buffer.append(ClassVarDecl.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassVarDeclUse]");
        return buffer.toString();
    }
}
