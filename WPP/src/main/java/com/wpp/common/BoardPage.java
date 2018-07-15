package com.wpp.common;

public class BoardPage {
	// 페이지당 게시물 
    public static final int PAGE_SCALE = 20;
    // 블록 크기(글이 5개가 넘어가면 한 블록 생성)
    public static final int BLOCK_SCALE = 5;
    private int curPage; // 현재 페이지 수
    private int prevPage; // 이전 블록 마지막페이지
    private int nextPage; // 다음 블록 첫페이지
    private int totPage; // 전체 페이지 수
    private int totBlock; // 전체 페이지 블록 수
    private int curBlock; // 현재 페이지 블록 
    private int prevBlock; // 이전 페이지 블록
    private int nextBlock; // 다음 페이지 블록
    
    private int pageBegin; // #{start}
    private int pageEnd; // #{end}
    private int blockBegin; // 현재 페이지 블록의 시작번호
    private int blockEnd; // 현재 페이지 블록의 끝번호
    
    
    //레코드 수, 현재 페이지 번호
    public BoardPage(int count, int curPage){
        curBlock = 1; // 현재 페이지 블록 번호(기본1)
        this.curPage = curPage; 
        setTotPage(count); // 전체 페이지 갯수 계산
        setPageRange(); //시작페이지, 끝페이지 구하기
        setTotBlock(); // 전체 페이지 블록 갯수 계산
        setBlockRange(); // 페이지 블록의 시작, 끝 번호 계산
    }
    
    public void setBlockRange(){
        // *현재 페이지가 몇번째 페이지 블록에 속하는지 계산
        curBlock = (int)Math.ceil((curPage-1) / BLOCK_SCALE)+1;
        // *현재 페이지 블록의 시작, 끝 번호 계산
        blockBegin = (curBlock-1)*BLOCK_SCALE+1;
        // 페이지 블록의 끝번호
        blockEnd = blockBegin+BLOCK_SCALE-1;
        // *마지막 블록이 범위를 초과하지 않도록 계산
        if(blockEnd > totPage) blockEnd = totPage;
        
        //이전을 눌렀을 때 이동할 이전블록의 페이지 번호  ex_ 1~5 다음 클릭 시 다음블록의 6페이지
        prevPage = (curPage == 1)? 1:(curBlock-1)*BLOCK_SCALE;
        //다음을 눌렀을 때 이동할 다음블록의 페이지 번호 ex_ 6~10 이전 클릭시 이전 블록의 5페이지
        nextPage = curBlock > totBlock ? (curBlock*BLOCK_SCALE) : (curBlock*BLOCK_SCALE)+1;
        // 마지막 페이지가 범위를 초과하지 않도록 처리
        if(nextPage >= totPage) nextPage = totPage;
    }
    
    public void setPageRange(){
    // WHERE rn BETWEEN #{start} AND #{end}
        // 시작번호 
        pageBegin = (curPage-1)*PAGE_SCALE;
        // 끝번호 
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
    
    //게시물 수로 총 페치지 구하기 
    public void setTotPage(int count) {
        //ceil =  올림 
        totPage = (int) Math.ceil(count*1.0 / PAGE_SCALE);
    }
    public int getTotBlock() {
        return totBlock;
    }
    
    // 페이지 블록의 갯수 계산(총 9페이지라면 2개의 블록)
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