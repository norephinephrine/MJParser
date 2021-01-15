// generated with ast extension for cup
// version 0.8
// 15/0/2021 17:43:44


package rs.ac.bg.etf.pp1.ast;

public class CaseListParam extends CaseList {

    private CaseList CaseList;
    private CaseParam CaseParam;

    public CaseListParam (CaseList CaseList, CaseParam CaseParam) {
        this.CaseList=CaseList;
        if(CaseList!=null) CaseList.setParent(this);
        this.CaseParam=CaseParam;
        if(CaseParam!=null) CaseParam.setParent(this);
    }

    public CaseList getCaseList() {
        return CaseList;
    }

    public void setCaseList(CaseList CaseList) {
        this.CaseList=CaseList;
    }

    public CaseParam getCaseParam() {
        return CaseParam;
    }

    public void setCaseParam(CaseParam CaseParam) {
        this.CaseParam=CaseParam;
    }

    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    public void childrenAccept(Visitor visitor) {
        if(CaseList!=null) CaseList.accept(visitor);
        if(CaseParam!=null) CaseParam.accept(visitor);
    }

    public void traverseTopDown(Visitor visitor) {
        accept(visitor);
        if(CaseList!=null) CaseList.traverseTopDown(visitor);
        if(CaseParam!=null) CaseParam.traverseTopDown(visitor);
    }

    public void traverseBottomUp(Visitor visitor) {
        if(CaseList!=null) CaseList.traverseBottomUp(visitor);
        if(CaseParam!=null) CaseParam.traverseBottomUp(visitor);
        accept(visitor);
    }

    public String toString(String tab) {
        StringBuffer buffer=new StringBuffer();
        buffer.append(tab);
        buffer.append("CaseListParam(\n");

        if(CaseList!=null)
            buffer.append(CaseList.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        if(CaseParam!=null)
            buffer.append(CaseParam.toString("  "+tab));
        else
            buffer.append(tab+"  null");
        buffer.append("\n");

        buffer.append(tab);
        buffer.append(") [CaseListParam]");
        return buffer.toString();
    }
}
