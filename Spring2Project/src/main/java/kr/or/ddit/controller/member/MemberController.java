package kr.or.ddit.controller.member;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.FileMember;
import kr.or.ddit.vo.Member;
import kr.or.ddit.vo.MultiFileMember;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class MemberController {

	/*
	 * [ 5장 : 컨트롤러 요청 처리 ]
	 * 
	 * 		1. 컨트롤러 메서드 매개변수
	 * 
	 * 			- Model : 이동 대상에 전달할 데이터를 가지고 있는 인터페이스
	 * 			- RedirectAttributes : 리다이렉트 대상에 전달할 데이터를 가지고 있는 인터페이스
	 * 			- 자바빈즈 클래스 : 요청 파라미터를 가지고 있는 자바빈즈 클래스
	 * 			- MultipartFile : 멀티파트 요청을 사용해 업로드된 파일 정보를 가지고 있는 인터페이스
	 * 			- BindingResult : 도메인 클래스의 입력값 검증 결과를 가지고 있는 인터페이스
	 * 			- Locale : 클라이언트 Locale
	 * 			- Principal : 클라이언트 인증을 위한 사용자 정보를 가지고 있는 인터페이스
	 */
	
	// 요청 처리 페이지
	@RequestMapping(value="/registerForm", method = RequestMethod.GET)
	public String registerForm() {
		log.info("registerForm() 실행...!");
		return "member/registerForm";
	}
	
	// 1) URL 경로 상의 쿼리 파라미터 정보로부터 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String registerByParameter(String userId, String password) {
		log.info("registerByParameter() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		return "success";
	}
	
	// 2) URL 경로 상의 경로 변수로부터 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register/{userId}", method = RequestMethod.GET)
	public String registerByPath(@PathVariable String userId) {
		// 넘겨받은 userId를 경로상에서 꺼낼 때, @PathVariable를 이용한다.
		log.info("registerByPath() 실행...!");
		log.info("userId : " + userId);
		return "success";
	}
	
	// 3) HTML Form 필드명과 컨트롤러 매개변수명이 일치하면 요청 데이터를 취득할 수 있다.
	//		그리고, 매개변수의 위치는 순서에 상관없고 오직 매개변수명이 일치하면 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register01", method = RequestMethod.POST)
	public String register01(String userId, String password) {
		log.info("register01() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		return "success";
	}
	
	// 4) HTML Form 필드 값이 숫자일 경우에는 숫자로 타입 변환되어 데이터를 취득할 수 있다.
	@RequestMapping(value="/register02", method = RequestMethod.POST)
	public String register02(String userId, String password, int coin) {
		log.info("register02() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		log.info("coin : " + coin);
		return "success";
	}
	
	
	/*
	 * 		3. 요청 데이터 처리 어노테이션
	 * 
	 * 			- @PathVariable : URL에서 경로 변수 값을 가져오기 위한 어노테이션
	 * 			- @RequestParam : 요청 파라미터 값을 가져오기 위한 어노테이션
	 * 			- @RequestHeader : 요청 헤더 값을 가져오기 위한 어노테이션
	 * 			- @RequestBody : 요청 본문 내용을 가져오기 위한 어노테이션
	 * 			- @CookieValue : 쿠키 값을 가져오기 위한 어노테이션
	 */
	// 1) URL 경로 상의 경로 변수가 여러 개 일 때 @PathVariable 어노테이션을 사용하여 특정한 경로 변수명을 지정해준다.
	@RequestMapping(value="/register/{userId}/{coin}", method = RequestMethod.GET)
	public String registerByPath(
			@PathVariable("userId") String userId,
			@PathVariable("coin") int coin
			) {
		log.info("registerByPath() 실행...!");
		log.info("userId : " + userId);
		log.info("coin : " + coin);
		return "success";
	}
	
	// 2) @RequestParam 어노테이션을 사용하여 특정한 HTML Form의 필드명을 지정하여 요청을 처리한다.
	@RequestMapping(value="/register0202", method = RequestMethod.POST)
	public String register0202(
			@RequestParam("userId") String username, String password, int coin
			) {
		
		log.info("register0202() 실행...!");
		log.info("username : " + username);
		log.info("password : " + password);
		log.info("coin : " + coin);
		return "success";
	}
	
	
	/*
	 * 		4. 요청 처리 자바빈즈
	 */
	// 1) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/beans/register01", method = RequestMethod.POST)
	public String registerBeans01(Member member) {
		log.info("registerBean01() 실행...!");
		log.info("member.userId : " + member.getUserId());
		log.info("member.password : " + member.getPassword());
		log.info("member.coin : " + member.getCoin());
		return "success";
	}
	
	// 2) 폼 텍스트 필드 요소값을 자바빈즈 매개변수와 기본 데이터 타입인 정수 타입 매개변수로 처리한다.
	@RequestMapping(value="/beans/register02", method = RequestMethod.POST)
	public String registerBeans02(Member member, int coin) {
		log.info("registerBean02() 실행...!");
		log.info("member.userId : " + member.getUserId());
		log.info("member.password : " + member.getPassword());
		log.info("member.coin : " + member.getCoin());
		log.info("coin : " + coin);
		return "success";
	}
	
	/*
	 * 		5. Date 타입 처리
	 * 
	 * 			- 스프링 MVC는 Date 타입의 데이터를 처리하는 여러 방법을 제공한다.
	 * 			- 클라이언트에서 날짜 데이터를 서버로 보낼 때에는 '년/월/일' 형식에 맞춰서 보내야한다.
	 * 
	 */
	// 1)
	@RequestMapping(value="/registerByGet01", method = RequestMethod.GET)
	public String registerByGet01(String userId, Date dateOfBirth) {
		log.info("registerByGet01() 실행...!");
		log.info("userId : " + userId);
		log.info("dateOfBirth : " + dateOfBirth);
		return "success";
	}
	
	// 2)
	@RequestMapping(value="/registerByGet02", method = RequestMethod.GET)
	public String registerByGet02(Member member) {
		log.info("registerByGet02() 실행...!");
		log.info("member.userId : " + member.getUserId());
		log.info("member.dateOfBirth : " + member.getDateOfBirth());
		return "success";
	}
	
	// 3)
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register(Member member) {
		log.info("register() 실행...!");
		log.info("member.userId : " + member.getUserId());
		log.info("member.password : " + member.getPassword());
		log.info("member.dateOfBirth : " + member.getDateOfBirth());
		return "success";
	}
	
	
	/*
	 * 		6. @DateTimeFormat 어노테이션
	 * 
	 * 			- @DateTimeFormat 어노테이션의 pattern 속성값에 원하는 날짜형식을 지정할 수 있다.
	 */
	@RequestMapping(value="/registerByGet03", method = RequestMethod.POST)
	public String registerByGet03(String userId, @DateTimeFormat(pattern = "yyyyMMdd") Date dateOfBirth) {
		log.info("register() 실행...!");
		log.info("userId : " + userId);
		log.info("dateOfBirth : " + dateOfBirth);
		return "success";
	}
	
	
	/*
	 * 		7. 폼 방식 요청 처리
	 */
	// 1) 폼 텍스트 필드 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerUserId", method = RequestMethod.POST)
	public String registerUserId(String userId) {
		log.info("registerUserId() 실행...!");
		log.info("userId : " + userId);
		return "success";
	}
	
	
	// 2) 폼 텍스트 필드 요소 값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerMemberUserId", method = RequestMethod.POST)
	public String registerMemberUserId(Member member) {
		log.info("registerMemberUserId() 실행...!");
		log.info("member.userId : " + member.getUserId());
		return "success";
	}
	
	
	// 3) 폼 비밀번호 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerPassword", method = RequestMethod.POST)
	public String registerPassword(Member member) {
		log.info("registerPassword() 실행...!");
		log.info("member.password : " + member.getPassword());
		return "success";
	}
	
	// 4) 폼 라디오버튼 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerRadio", method = RequestMethod.POST)
	public String registerRadio(String gender) {
		log.info("registerRadio() 실행...!");
		log.info("gender : " + gender);
		return "success";
	}
	
	// 5) 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerSelect", method = RequestMethod.POST)
	public String registerSelect(String nationality) {
		log.info("registerSelect() 실행...!");
		log.info("nationality : " + nationality);
		return "success";
	}
	
	// 6) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultiSelect01", method = RequestMethod.POST)
	public String registerMultiSelect01(String cars) {
		log.info("registerMultiSelect01() 실행...!");
		log.info("cars : " + cars);
		return "success";
	}
	
	// 7) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 배열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultiSelect02", method = RequestMethod.POST)
	public String registerMultiSelect02(String[] carArray) {
		log.info("registerMultiSelect02() 실행...!");
		log.info("carArray : " + carArray);
		if(carArray != null) {
			log.info("carArray.length : " + carArray.length);
			for(int i = 0; i < carArray.length; i++) {
				log.info("carArray["+i+"] : " + carArray[i]);
			}
		} else {
			log.info("carArray is null");
		}
		return "success";
	}
	
	// 8) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultiSelect03", method = RequestMethod.POST)
	public String registerMultiSelect03(ArrayList<String> carList) {	// 여기 직접 list 설정해준게 문제가 됨
		// 받는 타입을 List로 하게되면 No primary or default constructor found for interface java.util.List] 에러 발생
		// 스프링에서는 List타입으로 데이터를 받는데에 문제가 있다. (데이터 바인딩이 안됨)
		// 리스트와 같은 형태의 값을 받으려면 String[]로 여러데이터를 받아주거나 객체 안에 있는 List 필드로 받아야한다.
		log.info("registerMultiSelect03() 실행...!");
		log.info("carList : " + carList);
		if(carList != null && carList.size() > 0) {	// 데이터가 존재
			log.info("carList.size() : " + carList.size());
			for(int i = 0; i < carList.size(); i++) {
				log.info("carList.get("+i+") : " + carList.get(i));
			}
		} else {
			log.info("carList is null");
		}
		return "success";
	}
	
	// 9) 폼 체크박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox01", method = RequestMethod.POST)
	public String registerCheckbox01(String hobby) {
		log.info("registerCheckbox01() 실행...!");
		log.info("hobby : " + hobby);
		return "success";
	}
	
	// 10) 폼 체크박스 요소값을 문자열 배열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox02", method = RequestMethod.POST)
	public String registerCheckbox02(String[] hobbyArray) {
		log.info("registerCheckbox02() 실행...!");
		log.info("hobbyArray : " + hobbyArray);
		
		if(hobbyArray != null) {
			log.info("hobbyArray.length : " + hobbyArray.length);
			for(int i = 0; i < hobbyArray.length; i++) {
				log.info("hobbyArray["+i+"] : " + hobbyArray[i]);
			}
		} else {
			log.info("hobbyArray is null");
		}
		return "success";
	}
	
	// 11) 폼 체크박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	// 체크박스도 컬렉션 리스트로는 데이터를 가져올 수 없다.
	@RequestMapping(value="/registerCheckbox03", method = RequestMethod.POST)
	public String registerCheckbox03(ArrayList<String> hobbyList) {
		log.info("registerCheckbox03() 실행...!");
		log.info("hobbyList : " + hobbyList);
		
		if(hobbyList != null && hobbyList.size() > 0) {
			log.info("hobbyList.size() : " + hobbyList.size());
			for(int i = 0; i < hobbyList.size(); i++) {
				log.info("hobbyList.get("+i+") : " + hobbyList.get(i));
			}
		} else {
			log.info("hobbyList is null");
		}
		return "success";
	}
	
	// 13) 폼 체크박스 요소값을 기본 데이터 타입인 불리언 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox05", method = RequestMethod.POST)
	public String registerCheckbox05(boolean foreigner) {
		log.info("registerCheckbox05() 실행...!");
		log.info("foreigner : " + foreigner);
		return "success";
	}
	
	// 14) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerAddress", method = RequestMethod.POST)
	public String registerAddress(Address address) {
		log.info("registerAddress() 실행...!");
		if(address != null) {
			log.info("address.postCode : " + address.getPostCode());
			log.info("address.location : " + address.getLocation());
		} else {
			log.info("address is null");
		}
		return "success";
	}
	
	// 15) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerUserAddress", method = RequestMethod.POST)
	public String registerUserAddress(Member member) {
		log.info("registerUserAddress() 실행...!");
		
		Address address = member.getAddress();
		if(address != null) {
			log.info("member.address.postCode : " + address.getPostCode());
			log.info("member.address.location : " + address.getLocation());
		} else {
			log.info("address is null");
		}
		
		return "success";
	}
	
	// 16) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerUserCardList", method = RequestMethod.POST)
	public String registerUserCardList(Member member) {
		log.info("registerUserCardList() 실행...!");
		
		List<Card> cardList = member.getCardList();
		
		if(cardList != null && cardList.size() > 0) {
			log.info("cardList.size() : " + cardList.size());
			
			for(int i = 0; i < cardList.size(); i++) {
				Card card = cardList.get(i);
				log.info("card["+i+"].no : " + card.getNo());
				log.info("card["+i+"].validMonth : " + card.getValidMonth());
			}
		} else {
			log.info("cardList is null");
		}
		
		return "success";
	}
	
	
	// 회원가입에 필요한 전체 폼 페이지 (문제)
	@RequestMapping(value="/registerAllForm", method = RequestMethod.GET)
	public String registerAllForm() {
		return "member/registerAllForm";
	}
	
	// 전체 폼 페이지 결과
	@RequestMapping(value="/registerUser", method = RequestMethod.POST)
	public String registerUser(Member member, Model model) {
		log.info(member.toString());
		Map<String, Object> memInfo = new HashMap<String, Object>();
		DateFormat formatter = new SimpleDateFormat("yyyy년 MM월 dd일");
		
		memInfo.put("userId", member.getUserId());
		memInfo.put("password", member.getPassword());
		memInfo.put("userName", member.getUserName());
		memInfo.put("email", member.getEmail());
		
		String dateOfBirth = "";
		if(member.getDateOfBirth() != null) {
			dateOfBirth = formatter.format(member.getDateOfBirth());
		}
		memInfo.put("dateOfBirth", dateOfBirth);
		
		String gender = "";
		switch(member.getGender()) {
		case "male":
			gender = "남자";
			break;
		case "female":
			gender = "여자";
			break;
		default:
			gender = "기타";
		}
		memInfo.put("gender", gender);
		
		String developer = "";
		if(member.getDeveloper() != null && member.getDeveloper().equals("Y")) {
			developer = "개발자";
		} else {
			developer = "일반";
		}
		memInfo.put("developer", developer);
		
		
		String foreigner = "내국인";
		if(member.isForeigner()) {
			foreigner = "외국인";
		}
		memInfo.put("foreigner", foreigner);

		
		String nationality = "";
		switch(member.getNationality()) {
		case "korea":
			nationality = "대한민국";
			break;
		case "germany":
			nationality = "독일";
			break;
		case "austrailia":
			nationality = "호주";
			break;
		case "canada":
			nationality = "캐나다";
			break;
		case "usa":
			nationality = "미국";
			break;
		}
		memInfo.put("nationality", nationality);
		
		
		String cars = "";
		String[] carArray = member.getCarArray();
		if(carArray != null) {
			for(int i=0; i<carArray.length; i++) {
				cars += carArray[i] + " ";
			}
		} else {
			cars = "소유차량 없음";
		}
		memInfo.put("cars", cars);
		
		String hobbys = "";
		String[] hobbyArray = member.getHobbyArray();
		if(hobbyArray != null) {
			for(int i=0; i<hobbyArray.length; i++) {
				if(hobbyArray[i].equals("sports")) {
					hobbys += "운동 ";
				} else if(hobbyArray[i].equals("music")) {
					hobbys += "음악감상 ";
				} else if(hobbyArray[i].equals("movie")) {
					hobbys += "영화시청 ";
				}
			}
		} else {
			hobbys = "취미 없음";
		}
		memInfo.put("hobbys", hobbys);
		
		memInfo.put("postCode", member.getAddress().getPostCode());
		memInfo.put("location", member.getAddress().getLocation());
		
		List<Card> cardList = member.getCardList();
		if(cardList != null && cardList.size() > 0) {
			
			 for(int i = 0; i < cardList.size(); i++) { 
				 Card card = cardList.get(i);
			 
				 memInfo.put("cardNo"+(i+1), card.getNo()); 
				 memInfo.put("cardValidMonth"+(i+1), formatter.format(card.getValidMonth())); 
			}
			
		} else {
			log.info("cardList is null");
		}
		
		memInfo.put("introduction", member.getIntroduction());
		
		model.addAttribute("memInfo", memInfo);
		return "member/registerAllResult";
	}
	
	
	/*
	 * 		8. 파일업로드 폼 방식 요청 처리
	 * 
	 * 			- 파일 업로드 폼 방식 요청 처리를 위한 의존 라이브러리 추가
	 * 			- pom.xml 내, commons-fileupload, commons-io 라이브러리 의존관계 등록
	 * 			- web.xml 에 모든 경로에 대한 MultipartFilter를 등록
	 * 
	 */
	// 3) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile03", method = RequestMethod.POST)
	public String registerFile03(Member member, MultipartFile picture) {
		log.info("registerFile03() 실행...!");
		log.info("member.userId : " + member.getUserId());
		log.info("member.password : " + member.getPassword());
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		return "success";
	}
	
	// 4) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 FileMember 타입의 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile04", method = RequestMethod.POST)
	public String registerFile04(FileMember fileMember) {
		log.info("registerFile04() 실행...!");
		log.info("fileMember.userId : " + fileMember.getUserId());
		log.info("fileMember.password : " + fileMember.getPassword());
		
		MultipartFile picture = fileMember.getPicture();
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		return "success";
	}
	
	// 5) 여러 개의 파일업로드를 폼 파일 요소값을 여러 개의 MultipartFile 매개변수로 처리한다.
	@RequestMapping(value="/registerFile05", method = RequestMethod.POST)
	public String registerFile05(MultipartFile picture,  MultipartFile picture2) {
		log.info("registerFile05() 실행...!");
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		log.info("originalName : " + picture2.getOriginalFilename());
		log.info("size : " + picture2.getSize());
		log.info("contentType : " + picture2.getContentType());
		
		return "success";
	}
	
	// 6) 여러 개의 파일업로드를 폼 파일 요소값을 MultipartFile 타입의 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	//    파일업로드도 마찬가지로 List로는 데이터를 바인딩할 수 없다.
	@RequestMapping(value="/registerFile06", method = RequestMethod.POST)
	public String registerFile06(List<MultipartFile> pictureList) {
		log.info("registerFile06() 실행...!");
		log.info("pictureList.size() : " + pictureList.size());
		
		for(int i = 0; i < pictureList.size(); i++) {
			MultipartFile picture = pictureList.get(i);
			log.info("originalName"+i+" : " + picture.getOriginalFilename());
			log.info("size"+i+" : " + picture.getSize());
			log.info("contentType"+i+" : " + picture.getContentType());
		}
		
		return "success";
	}
	
	// 7) 여러 개의 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultiFileMember 타입의 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile07", method = RequestMethod.POST)
	public String registerFile07(MultiFileMember multiFileMember) {
		log.info("registerFile07() 실행...!");
		List<MultipartFile> pictureList = multiFileMember.getPictureList();
		log.info("pictureList.size() : " + pictureList.size());
		
		for(int i = 0; i < pictureList.size(); i++) {
			MultipartFile picture = pictureList.get(i);
			log.info("originalName"+i+" : " + picture.getOriginalFilename());
			log.info("size"+i+" : " + picture.getSize());
			log.info("contentType"+i+" : " + picture.getContentType());
		}
		
		return "success";
	}
	
	// 8) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 타입의 배열 매개변수로 처리한다.
	@RequestMapping(value="/registerFile08", method = RequestMethod.POST)
	public String registerFile08(MultipartFile[] pictureArray) {
		log.info("registerFile08() 실행...!");
		log.info("pictureArray.length : " + pictureArray.length);
		
		for(int i = 0; i < pictureArray.length; i++) {
			MultipartFile picture = pictureArray[i];
			log.info("originalName"+i+" : " + picture.getOriginalFilename());
			log.info("size"+i+" : " + picture.getSize());
			log.info("contentType"+i+" : " + picture.getContentType());
		}
		
		return "success";
	}
	
	
	
	
}
