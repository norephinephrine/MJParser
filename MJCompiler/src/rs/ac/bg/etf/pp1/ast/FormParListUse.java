// generated with ast extension for cup
// version 0.8
// 21/0/2021 15:53:15


package rs.ac.bg.etf.pp1.ast;

public class FormParListUse extends FormParListIf {

    private FormParList FormParList;

    public FormParListUse (FormParList FormParList) {
        this.FormParList=FormParList;
        if(FormParList!=null) FormParList.setParent(this);
    }

    public FormParList getFormParList() {
        return FormParList;
    }

    public void setFormParList(FormParList FormParList) {
        this.FormParList=FormParList;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParList!=null) FormParList.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParList!=null) FormParList.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParList!=null) FormParList.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParListUse(\n");

        if(FormParList!=null)
            buffer.append(FormParList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParListUse]");
        return buffer.toString();
    }
}
