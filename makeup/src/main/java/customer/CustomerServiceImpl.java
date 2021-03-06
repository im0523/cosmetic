package customer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
	@Autowired CustomerDAO dao;

	// customer list 조회
	@Override
	public CustomerPageVO customer_list(CustomerPageVO page) {
		return dao.customer_list(page);
	}

	@Override
	public CustomerVO customer_detail(String customer_id) {
		return dao.customer_detail(customer_id);
	}

	// customer 등록
	@Override
	public void customer_insert(CustomerVO vo) {
		dao.customer_insert(vo);
	}

	@Override
	public void customer_update(CustomerVO vo) {
		dao.customer_update(vo);
	}

	@Override
	public void customer_delete(String customer_id) {
		dao.customer_delete(customer_id);
	}

	// 아이디 중복검사
	@Override
	public boolean id_usable(String customer_id) {
		return dao.id_usable(customer_id);
	}

	// 로그인 처리
	@Override
	public CustomerVO login_check(String customer_id, String customer_pw) {
		return dao.login_check(customer_id, customer_pw);
	}

	// 아이디 찾기 - email
	@Override
	public CustomerVO findId_email(String customer_name, String customer_email) {
		return dao.findId_email(customer_name, customer_email);
	}

	// 아이디 찾기 - phone
	@Override
	public CustomerVO findId_phone(String customer_name, String customer_phone) {
		return dao.findId_phone(customer_name, customer_phone);
	}

	// 비밀번호 찾기
	public CustomerVO findPw(String customer_id, String customer_name, String customer_phone, String customer_email) {
		return dao.findPw(customer_id, customer_name, customer_phone, customer_email);
	}

}
