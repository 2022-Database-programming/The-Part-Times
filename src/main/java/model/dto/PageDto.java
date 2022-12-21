package model.dto;


public class PageDto {
	private int startPageNum; // 게시글 화면에 보여질 첫번째 페이지네이션 번호
	private int endPageNum; // 게시글 화면에 보여질 마지막 페이지네이션 번호
	private boolean prev, next; // 이전버튼, 다음버튼 활성화여부
	
	private int pageNum; // 현재 조회하는 페이지네이션 번호
	private int amount = 10; // 화면에 그려질 데이터
	private int total; // 전체게시글 수
	
	public PageDto (int pageNum, int amount, int total) {
		this.pageNum = pageNum;
		this.amount = amount;
		this.total = total;
		
		// endPageNum 결정 (10 -> 페이지네이션 개수)
		this.endPageNum = (int) Math.ceil(this.pageNum * 0.1) * 10;
		
		// startPageNum 결정
		this.startPageNum = this.endPageNum - 10 + 1;
		
		// endPage (마지막 페이지네이션)
		int endPage = (int)Math.ceil(this.total / (double)this.amount);
		
		// 마지막 페이지의 페이지네이션 끝번호
		if(this.endPageNum > endPage) {
			this.endPageNum = endPage;
		}
		
		// prev
		this.prev = this.startPageNum > 1;
		
		// next
		this.next = this.endPageNum < endPage;
	}

	public int getStartPageNum() {
		return startPageNum;
	}

	public void setStartPageNum(int startPageNum) {
		this.startPageNum = startPageNum;
	}

	public int getEndPageNum() {
		return endPageNum;
	}

	public void setEndPageNum(int endPageNum) {
		this.endPageNum = endPageNum;
	}

	public boolean isPrev() {
		return prev;
	}

	public void setPrev(boolean prev) {
		this.prev = prev;
	}

	public boolean isNext() {
		return next;
	}

	public void setNext(boolean next) {
		this.next = next;
	}

	public int getPageNum() {
		return pageNum;
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}
	
}