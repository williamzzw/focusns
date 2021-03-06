package org.focusns.service.core.impl;

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

import org.focusns.dao.core.ProjectDao;
import org.focusns.dao.core.ProjectHistoryDao;
import org.focusns.dao.core.ProjectUserDao;
import org.focusns.model.common.Page;
import org.focusns.model.core.Project;
import org.focusns.model.core.ProjectHistory;
import org.focusns.model.core.ProjectUser;
import org.focusns.service.core.ProjectHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ProjectHistoryServiceImpl implements ProjectHistoryService {

    @Autowired
    private ProjectDao projectDao;
    @Autowired
    private ProjectUserDao projectUserDao;
    @Autowired
    private ProjectHistoryDao projectHistoryDao;

    public void createProjectHistory(ProjectHistory histroy) {
        //
        Date now = new Date();
        histroy.setCreateAt(now);
        //
        projectHistoryDao.insert(histroy);
    }

    public void modifyProjectHistory(ProjectHistory history) {
        projectHistoryDao.update(history);
    }

    public void removeProjectHistory(ProjectHistory history) {
        projectHistoryDao.delete(history.getId());
    }

    public Page<ProjectHistory> fetchPage(Page<ProjectHistory> page, long projectId) {
        page = projectHistoryDao.fetchByProjectId(page, projectId);
        // TODO performance tuning
        for (ProjectHistory history : page.getResults()) {
            Project project = projectDao.select(history.getProjectId());
            ProjectUser projectUser = projectUserDao.selectWithProject(history.getCreateById());
            history.setProject(project);
            history.setCreateBy(projectUser);
            //
            List<ProjectHistory> children = projectHistoryDao.selectByParentId(history.getId());
            for (ProjectHistory child : children) {
                //
                Project childProject = projectDao.select(child.getProjectId());
                ProjectUser childProjectUser = projectUserDao.selectWithProject(child.getCreateById());
                child.setProject(childProject);
                child.setCreateBy(childProjectUser);
            }
            history.setChildren(children);
        }
        //
        return page;
    }

}
