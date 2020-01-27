package store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StoreServiceImpl implements StoreService {
	@Autowired private StoreDAO dao;

	@Override
	public StorePageVO store_list(StorePageVO page) {
		return dao.store_list(page);
	}

	@Override
	public StoreVO store_detail(int id) {
		return dao.store_detail(id);
	}

	@Override
	public void store_insert(StoreVO vo) {

	}

	@Override
	public void store_update(StoreVO vo) {

	}

	@Override
	public void store_delete(int id) {

	}

	@Override
	public void store_read(int id) {

	}

}
