package com.thoughtworks.gaia.student.dao;

import com.thoughtworks.gaia.common.jpa.BaseDaoWrapper;
import com.thoughtworks.gaia.student.model.ArticalModel;
import org.springframework.stereotype.Component;

/**
 * Created by lavender on 17-4-25.
 */
@Component
public class ArticalDao extends BaseDaoWrapper<ArticalModel> {
    public ArticalDao() {
        super(ArticalModel.class);
    }
}
