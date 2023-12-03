package kr.or.ddit.mapper;

import java.util.List;

import kr.or.ddit.vo.test.TagBoardVO;
import kr.or.ddit.vo.test.TagVO;

public interface ITagMapper {

	public int createBoard(TagBoardVO tagboardVO);
	public int createTag(TagVO tagVO);
	public void incrementHit(int boNo);
	public TagBoardVO selectBoard(int boNo);
	public List<TagVO> selectTag(int boNo);
	public int deleteBoard(int boNo);
	public int deleteTag(int boNo);
	public int updateBoard(TagBoardVO tagBoardVO);
	public List<TagBoardVO> selectBoardList(TagBoardVO tagBoardVO);

}
