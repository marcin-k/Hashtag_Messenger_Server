package com.marcin.hashtagmessenger.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurerAdapter;


import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

/**************************************************************
 * Class used to expose id attribute,
 * (by default those are not part of JSON)
 *
 **************************************************************/
@Configuration
public class RepositoryConfig extends RepositoryRestConfigurerAdapter {

    @Autowired
    private EntityManager entityManager;

    @Override
    public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
        config.exposeIdsFor(
                entityManager.getMetamodel().getEntities().stream()
                        .map(Type::getJavaType)
                        .toArray(Class[]::new));
    }
}
