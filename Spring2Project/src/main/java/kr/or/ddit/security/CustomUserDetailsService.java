package kr.or.ddit.security;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import kr.or.ddit.mapper.ILoginMapper;
import kr.or.ddit.vo.CustomUser;
import kr.or.ddit.vo.crud.NoticeMemberVO;

public class CustomUserDetailsService implements UserDetailsService {

	private static final Logger log = LoggerFactory.getLogger(CustomUserDetailsService.class);
	
	@Inject
	private BCryptPasswordEncoder bpe;
	
	@Inject
	private ILoginMapper loginMapper;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		log.info("loadUserByUsername() 실행...!");
		log.info("Load User By Username : " + username);
		
		// UserDetailsService를 등록하는 과정에서 우리가 할 목표는 User 객체의 정보와
		// 인증되어 실제로 사용될 내 id에 해당하는 회원정보를 CrudMember에 담고 그 녀석을 UserDetails정보 안에서
		// 가용할 수 있도록 만든다.
		NoticeMemberVO member;
		try {
			member = loginMapper.readByUserId(username);
			log.info("queried by member mapper : " + member);
			return member == null ? null : new CustomUser(member);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
