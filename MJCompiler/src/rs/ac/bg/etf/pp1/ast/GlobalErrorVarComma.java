// generated with ast extension for cup
// version 0.8
// 21/0/2021 15:53:15


package rs.ac.bg.etf.pp1.ast;

public class GlobalErrorVarComma extends GlobalVarComma {

    public GlobalErrorVarComma () {
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("GlobalErrorVarComma(\n");

        buffer.append(tab);
        buffer.append(") [GlobalErrorVarComma]");
        return buffer.toString();
    }
}
