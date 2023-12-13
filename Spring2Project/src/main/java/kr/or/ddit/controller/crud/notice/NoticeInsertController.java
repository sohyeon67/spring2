package kr.or.ddit.controller.crud.notice;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.CustomUser;
import kr.or.ddit.vo.crud.NoticeMemberVO;
import kr.or.ddit.vo.crud.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeInsertController {
	
	@Inject
	private INoticeService noticeService;

	@RequestMapping(value="/form.do", method = RequestMethod.GET)
	public String noticeInsertForm() {
		return "notice/form";
	}
	
	@RequestMapping(value = "/insert.do", method = RequestMethod.POST)
	public String noticeInsert(
			HttpServletRequest req,
			NoticeVO noticeVO, Model model, RedirectAttributes ra) {
		String goPage = "";	// 이동할 페이지 정보
		
		// 넘겨받은 데이터 검증 후, 에러가 발생한 데이터에 대한 에러정보를 담을 공간
		Map<String, String> errors = new HashMap<String, String>();
		
		// 제목 데이터가 누락되었을 때 에러 정보 저장
		if(StringUtils.isBlank(noticeVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요.");
		}
		
		// 내용 데이터가 누락되었을 때 에러 정보 저장
		if(StringUtils.isBlank(noticeVO.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요.");
		}
		
		// 기본 데이터의 누락정보에 따른 에러정보 개수로 분기 처리
		if(errors.size() > 0) {	// 에러 개수가 0보다 클 때(에러가 존재)
			model.addAttribute("errors", errors);
			model.addAttribute("noticeVO", noticeVO);
			goPage = "notice/form";
		} else {				// 에러가 없을 때
			// 예전 실습 - 로그인 처리를 하지 않고 게시글을 작성하므로 작성자를 하드코딩한다
			// noticeVO.setBoWriter("a001");
			
			// [HttpSession] 로그인 처리 후 세션 정보에서 얻어 온 회원정보를 가용하기 위한 준비
//			HttpSession session = req.getSession();
//			NoticeMemberVO memberVO = (NoticeMemberVO) session.getAttribute("SessionInfo");
			
			// [스프링 시큐리티] 회원 ID를 스프링 시큐리티 UserDetails 정보에서 가져오기
			CustomUser user = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			NoticeMemberVO memberVO = user.getMember();
			
			// 세션 정보에서 꺼낸 회원 데이터가 null이 아닐때 (로그인을 진행)
			if(memberVO != null) {
				noticeVO.setBoWriter(memberVO.getMemId());
				ServiceResult result = noticeService.insertNotice(req, noticeVO);
				if(result.equals(ServiceResult.OK)) {	// 등록 성공
					goPage = "redirect:/notice/detail.do?boNo="+noticeVO.getBoNo();
					ra.addFlashAttribute("message", "게시글 등록이 완료되었습니다.");
				} else {	// 등록 실패
					model.addAttribute("noticeVO", noticeVO);
					model.addAttribute("message", "서버에러, 다시 시도해주세요!");
					goPage = "notice/form";
				}
			} else {	// 로그인을 진행하지 않았을 때
				// [방법1] - 등록을 진행하지만, 로그인 후에 가능하다는 걸 알려주기 위한 프로세스일때
//				model.addAttribute("message", "로그인 후에 사용 가능합니다!");
//				model.addAttribute("notice", noticeVO);
//				goPage = "notice/form";
				
				// [방법2] - 등록을 진행하기 위해서는 로그인을 필수로 진행해야합니다. 라는 프로세스 일때
				ra.addFlashAttribute("message", "로그인 후에 사용 가능합니다!");
				goPage = "redirect:/notice/login.do";
			}
			
		}
		return goPage;
	}
}
