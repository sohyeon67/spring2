package kr.or.ddit.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.vo.crud.NoticeMemberVO;
import kr.or.ddit.vo.crud.NoticeVO;
import kr.or.ddit.vo.crud.PaginationInfoVO;

public interface INoticeService {

	public int selectNoticeCount(PaginationInfoVO<NoticeVO> pagingVO);
	public List<NoticeVO> selectNoticeList(PaginationInfoVO<NoticeVO> pagingVO);
	public ServiceResult insertNotice(HttpServletRequest req, NoticeVO noticeVO);
	public NoticeVO selectNotice(int boNo);
	public ServiceResult updateNotice(NoticeVO noticeVO);
	public ServiceResult deleteNotice(int boNo);
	
	// noticemember
	public NoticeMemberVO loginCheck(NoticeMemberVO member);
	public ServiceResult idCheck(String memId);
	public ServiceResult signup(HttpServletRequest req, NoticeMemberVO memberVO);

}
