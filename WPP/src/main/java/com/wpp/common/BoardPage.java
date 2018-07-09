package com.wpp.common;

public class BoardPage {
	// �������� �Խù� 
    public static final int PAGE_SCALE = 20;
    // ��� ũ��(���� 5���� �Ѿ�� �� ��� ����)
    public static final int BLOCK_SCALE = 5;
    private int curPage; // ���� ������ ��
    private int prevPage; // ���� ��� ������������
    private int nextPage; // ���� ��� ù������
    private int totPage; // ��ü ������ ��
    private int totBlock; // ��ü ������ ��� ��
    private int curBlock; // ���� ������ ��� 
    private int prevBlock; // ���� ������ ���
    private int nextBlock; // ���� ������ ���
    
    private int pageBegin; // #{start}
    private int pageEnd; // #{end}
    private int blockBegin; // ���� ������ ����� ���۹�ȣ
    private int blockEnd; // ���� ������ ����� ����ȣ
    
    
    //���ڵ� ��, ���� ������ ��ȣ
    public BoardPage(int count, int curPage){
        curBlock = 1; // ���� ������ ��� ��ȣ(�⺻1)
        this.curPage = curPage; 
        setTotPage(count); // ��ü ������ ���� ���
        setPageRange(); //����������, �������� ���ϱ�
        setTotBlock(); // ��ü ������ ��� ���� ���
        setBlockRange(); // ������ ����� ����, �� ��ȣ ���
    }
    
    public void setBlockRange(){
        // *���� �������� ���° ������ ��Ͽ� ���ϴ��� ���
        curBlock = (int)Math.ceil((curPage-1) / BLOCK_SCALE)+1;
        // *���� ������ ����� ����, �� ��ȣ ���
        blockBegin = (curBlock-1)*BLOCK_SCALE+1;
        // ������ ����� ����ȣ
        blockEnd = blockBegin+BLOCK_SCALE-1;
        // *������ ����� ������ �ʰ����� �ʵ��� ���
        if(blockEnd > totPage) blockEnd = totPage;
        
        //������ ������ �� �̵��� ��������� ������ ��ȣ  ex_ 1~5 ���� Ŭ�� �� ��������� 6������
        prevPage = (curPage == 1)? 1:(curBlock-1)*BLOCK_SCALE;
        //������ ������ �� �̵��� ��������� ������ ��ȣ ex_ 6~10 ���� Ŭ���� ���� ����� 5������
        nextPage = curBlock > totBlock ? (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1;
        // ������ �������� ������ �ʰ����� �ʵ��� ó��
        if(nextPage >= totPage) nextPage = totPage;
    }
    
    public void setPageRange(){
    // WHERE rn BETWEEN #{start} AND #{end}
        // ���۹�ȣ 
        pageBegin = (curPage-1)*PAGE_SCALE;
        // ����ȣ 
        pageEnd = pageBegin+PAGE_SCALE;
    }
    
    public int getCurPage() {
        return curPage;
    }
    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }
    public int getPrevPage() {
        return prevPage;
    }
    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }
    public int getNextPage() {
        return nextPage;
    }
    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
    public int getTotPage() {
        return totPage;
    }
    
    //�Խù� ���� �� ��ġ�� ���ϱ� 
    public void setTotPage(int count) {
        //ceil =  �ø� 
        totPage = (int) Math.ceil(count*1.0 / PAGE_SCALE);
    }
    public int getTotBlock() {
        return totBlock;
    }
    
    // ������ ����� ���� ���(�� 9��������� 2���� ���)
    public void setTotBlock() {
       
        totBlock = (int)Math.ceil(totPage / BLOCK_SCALE)+1;
    }
    
    public int getCurBlock() {
        return curBlock;
    }
    public void setCurBlock(int curBlock) {
        this.curBlock = curBlock;
    }
    public int getPrevBlock() {
        return prevBlock;
    }
    public void setPrevBlock(int prevBlock) {
        this.prevBlock = prevBlock;
    }
    public int getNextBlock() {
        return nextBlock;
    }
    public void setNextBlock(int nextBlock) {
        this.nextBlock = nextBlock;
    }
    public int getPageBegin() {
        return pageBegin;
    }
    public void setPageBegin(int pageBegin) {
        this.pageBegin = pageBegin;
    }
    public int getPageEnd() {
        return pageEnd;
    }
    public void setPageEnd(int pageEnd) {
        this.pageEnd = pageEnd;
    }
    public int getBlockBegin() {
        return blockBegin;
    }
    public void setBlockBegin(int blockBegin) {
        this.blockBegin = blockBegin;
    }
    public int getBlockEnd() {
        return blockEnd;
    }
    public void setBlockEnd(int blockEnd) {
        this.blockEnd = blockEnd;
    }
    

}
