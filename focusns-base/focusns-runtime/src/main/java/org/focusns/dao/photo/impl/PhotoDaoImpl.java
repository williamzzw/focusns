package org.focusns.dao.photo.impl;

/*
 * #%L
 * FocusSNS Runtime
 * %%
 * Copyright (C) 2011 - 2013 FocusSNS
 * %%
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as 
 * published by the Free Software Foundation, either version 2.1 of the 
 * License, or (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Lesser Public License for more details.
 * 
 * You should have received a copy of the GNU General Lesser Public 
 * License along with this program.  If not, see
 * <http://www.gnu.org/licenses/lgpl-2.1.html>.
 * #L%
 */

import org.focusns.dao.common.impl.MyBatisBaseDao;
import org.focusns.dao.photo.PhotoDao;
import org.focusns.model.photo.Photo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PhotoDaoImpl extends MyBatisBaseDao<Photo> implements PhotoDao {

    public List<Photo> selectList(long albumId) {
        return getSqlSession().selectList(NAMESPACE.concat(".selectList"), albumId);
    }
}
