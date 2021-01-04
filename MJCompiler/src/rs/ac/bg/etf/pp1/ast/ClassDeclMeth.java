// generated with ast extension for cup
// version 0.8
// 3/0/2021 22:13:2


package rs.ac.bg.etf.pp1.ast;

public class ClassDeclMeth extends ClassDecl {

    private ClassName ClassName;
    private ExtendType ExtendType;
    private ClassVarDeclIf ClassVarDeclIf;
    private MethodDecList MethodDecList;

    public ClassDeclMeth (ClassName ClassName, ExtendType ExtendType, ClassVarDeclIf ClassVarDeclIf, MethodDecList MethodDecList) {
        this.ClassName=ClassName;
        if(ClassName!=null) ClassName.setParent(this);
        this.ExtendType=ExtendType;
        if(ExtendType!=null) ExtendType.setParent(this);
        this.ClassVarDeclIf=ClassVarDeclIf;
        if(ClassVarDeclIf!=null) ClassVarDeclIf.setParent(this);
        this.MethodDecList=MethodDecList;
        if(MethodDecList!=null) MethodDecList.setParent(this);
    }

    public ClassName getClassName() {
        return ClassName;
    }

    public void setClassName(ClassName ClassName) {
        this.ClassName=ClassName;
    }

    public ExtendType getExtendType() {
        return ExtendType;
    }

    public void setExtendType(ExtendType ExtendType) {
        this.ExtendType=ExtendType;
    }

    public ClassVarDeclIf getClassVarDeclIf() {
        return ClassVarDeclIf;
    }

    public void setClassVarDeclIf(ClassVarDeclIf ClassVarDeclIf) {
        this.ClassVarDeclIf=ClassVarDeclIf;
    }

    public MethodDecList getMethodDecList() {
        return MethodDecList;
    }

    public void setMethodDecList(MethodDecList MethodDecList) {
        this.MethodDecList=MethodDecList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(ClassName!=null) ClassName.accept(visitor);
        if(ExtendType!=null) ExtendType.accept(visitor);
        if(ClassVarDeclIf!=null) ClassVarDeclIf.accept(visitor);
        if(MethodDecList!=null) MethodDecList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(ClassName!=null) ClassName.traverseTopDown(visitor);
        if(ExtendType!=null) ExtendType.traverseTopDown(visitor);
        if(ClassVarDeclIf!=null) ClassVarDeclIf.traverseTopDown(visitor);
        if(MethodDecList!=null) MethodDecList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(ClassName!=null) ClassName.traverseBottomUp(visitor);
        if(ExtendType!=null) ExtendType.traverseBottomUp(visitor);
        if(ClassVarDeclIf!=null) ClassVarDeclIf.traverseBottomUp(visitor);
        if(MethodDecList!=null) MethodDecList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("ClassDeclMeth(\n");

        if(ClassName!=null)
            buffer.append(ClassName.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ExtendType!=null)
            buffer.append(ExtendType.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(ClassVarDeclIf!=null)
            buffer.append(ClassVarDeclIf.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecList!=null)
            buffer.append(MethodDecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [ClassDeclMeth]");
        return buffer.toString();
    }
}
