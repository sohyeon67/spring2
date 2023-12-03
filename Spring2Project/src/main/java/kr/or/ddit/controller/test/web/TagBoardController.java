package kr.or.ddit.controller.test.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.ITagService;
import kr.or.ddit.vo.test.TagBoardVO;

@Controller
@RequestMapping("/board/tag")
public class TagBoardController {
	
	@Inject
	private ITagService tagService;

	// 태그 게시판 목록 
	@RequestMapping(value="/list.do", method = RequestMethod.GET)
	public String tagList(
			@RequestParam(required = false, defaultValue = "title") String searchType,
			@RequestParam(required = false) String searchWord,
			Model model
			) {
		TagBoardVO tagBoardVO = new TagBoardVO();
		
		if(StringUtils.isNotBlank(searchWord)) {
			tagBoardVO.setSearchType(searchType);
			tagBoardVO.setSearchWord(searchWord);
			model.addAttribute("searchType", searchType);
			model.addAttribute("searchWord", searchWord);
		}
		
		List<TagBoardVO> boardList = tagService.selectBoardList(tagBoardVO);
		model.addAttribute("boardList", boardList);
		
		return "test/list";
	}
	
	@RequestMapping(value="/form.do", method = RequestMethod.GET)
	public String tagForm() {
		return "test/form";
	}
	
	@RequestMapping(value="/form.do", method = RequestMethod.POST)
	public String tagInsert(TagBoardVO tagBoardVO, Model model, RedirectAttributes ra) {
		String goPage = "";
		
		Map<String, String> errors = new HashMap<String, String>();
		if(StringUtils.isBlank(tagBoardVO.getBoTitle())) {
			errors.put("boTitle", "제목을 입력해주세요.");
		}
		if(StringUtils.isBlank(tagBoardVO.getBoContent())) {
			errors.put("boContent", "내용을 입력해주세요.");
		}
		
		if(errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("tagBoardVO", tagBoardVO);
			goPage = "board/tag/form.do";
		} else {
			// 작성자 하드코딩
			tagBoardVO.setBoWriter("a001");
			ServiceResult result = tagService.insertTag(tagBoardVO);
			if(result.equals(ServiceResult.OK)) {
				// 등록 후 리다이렉트
				goPage = "redirect:/board/tag/detail.do?boNo="+tagBoardVO.getBoNo();
				ra.addFlashAttribute("message", "게시글 등록이 완료되었습니다.");
			} else {
				model.addAttribute("tagBoardVO", tagBoardVO);
				model.addAttribute("message", "서버에러, 다시 시도해주세요!");
				goPage = "board/tag/form.do";
			}
		}
		return goPage;
	}
	
	@RequestMapping(value="/detail.do", method = RequestMethod.GET)
	public String tagDetail(int boNo, Model model) {
		TagBoardVO tagBoardVO = tagService.selectTagBoard(boNo);	// 태그 내용들도 다 들어있다.
		model.addAttribute("tagBoardVO", tagBoardVO);
		
		return "test/detail";
	}
	
	@RequestMapping(value="/delete.do", method = RequestMethod.POST)
	public String tagDelete(int boNo, Model model, RedirectAttributes ra) {
		String goPage = "";
		
		ServiceResult result = tagService.deleteTag(boNo);
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/board/tag/list.do";
			ra.addFlashAttribute("message", "게시글 삭제가 완료되었습니다!");
		} else {
			goPage = "redirect:/board/tag/detail.do?boNo="+boNo;
			ra.addFlashAttribute("message", "게시글 삭제가 실패했습니다!");
		}
		
		return goPage;
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.GET)
	public String tagUpdateForm(int boNo, Model model) {
		TagBoardVO tagBoardVO = tagService.selectTagBoard(boNo);	// 태그 내용들도 다 들어있다.
		model.addAttribute("tagBoardVO", tagBoardVO);
		model.addAttribute("status", "u");	// 수정 flag
		return "test/form";
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public String tagUpdate(TagBoardVO tagBoardVO, Model model, RedirectAttributes ra) {
		String goPage = "";
		ServiceResult result = tagService.updateTagBoard(tagBoardVO);
		
		if(result.equals(ServiceResult.OK)) {
			goPage = "redirect:/board/tag/detail.do?boNo="+tagBoardVO.getBoNo();
			ra.addFlashAttribute("message", "게시글 수정이 완료되었습니다!");
		} else {
			model.addAttribute("message", "게시글 수정에 실패했습니다!");
			model.addAttribute("tagBoardVO", tagBoardVO);
			model.addAttribute("status", "u");
			goPage = "board/tag/update.do?boNo"+tagBoardVO.getBoNo();
		}
		
		return goPage;
	}
	
}
