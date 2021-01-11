// generated with ast extension for cup
// version 0.8
// 10/0/2021 5:13:6


package rs.ac.bg.etf.pp1.ast;

public class MethodDeclUse extends MethodDecList {

    private MethodDecList MethodDecList;
    private MethodDecPar MethodDecPar;

    public MethodDeclUse (MethodDecList MethodDecList, MethodDecPar MethodDecPar) {
        this.MethodDecList=MethodDecList;
        if(MethodDecList!=null) MethodDecList.setParent(this);
        this.MethodDecPar=MethodDecPar;
        if(MethodDecPar!=null) MethodDecPar.setParent(this);
    }

    public MethodDecList getMethodDecList() {
        return MethodDecList;
    }

    public void setMethodDecList(MethodDecList MethodDecList) {
        this.MethodDecList=MethodDecList;
    }

    public MethodDecPar getMethodDecPar() {
        return MethodDecPar;
    }

    public void setMethodDecPar(MethodDecPar MethodDecPar) {
        this.MethodDecPar=MethodDecPar;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(MethodDecList!=null) MethodDecList.accept(visitor);
        if(MethodDecPar!=null) MethodDecPar.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(MethodDecList!=null) MethodDecList.traverseTopDown(visitor);
        if(MethodDecPar!=null) MethodDecPar.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(MethodDecList!=null) MethodDecList.traverseBottomUp(visitor);
        if(MethodDecPar!=null) MethodDecPar.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("MethodDeclUse(\n");

        if(MethodDecList!=null)
            buffer.append(MethodDecList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(MethodDecPar!=null)
            buffer.append(MethodDecPar.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [MethodDeclUse]");
        return buffer.toString();
    }
}
