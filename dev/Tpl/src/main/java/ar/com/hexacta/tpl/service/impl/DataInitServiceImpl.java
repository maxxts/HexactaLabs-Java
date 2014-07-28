package ar.com.hexacta.tpl.service.impl;

import org.springframework.beans.factory.InitializingBean;

import ar.com.hexacta.tpl.persistence.repository.DataInitRepository;

public class DataInitServiceImpl implements InitializingBean {

    private DataInitRepository repository;

    public void setRepository(final DataInitRepository repository) {
        this.repository = repository;
    }

    public DataInitRepository getRepository() {
        return repository;
    }

	@Override
	public void afterPropertiesSet() throws Exception {
        this.getRepository().initializeData();
	}

}
