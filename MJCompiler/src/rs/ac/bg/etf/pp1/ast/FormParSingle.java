// generated with ast extension for cup
// version 0.8
// 21/0/2021 15:53:15


package rs.ac.bg.etf.pp1.ast;

public class FormParSingle extends FormParList {

    private FormParParen FormParParen;

    public FormParSingle (FormParParen FormParParen) {
        this.FormParParen=FormParParen;
        if(FormParParen!=null) FormParParen.setParent(this);
    }

    public FormParParen getFormParParen() {
        return FormParParen;
    }

    public void setFormParParen(FormParParen FormParParen) {
        this.FormParParen=FormParParen;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(FormParParen!=null) FormParParen.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(FormParParen!=null) FormParParen.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(FormParParen!=null) FormParParen.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("FormParSingle(\n");

        if(FormParParen!=null)
            buffer.append(FormParParen.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [FormParSingle]");
        return buffer.toString();
    }
}
