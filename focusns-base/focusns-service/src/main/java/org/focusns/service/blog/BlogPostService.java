package org.focusns.service.blog;

/*
 * #%L
 * FocusSNS Service
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

import org.focusns.model.blog.BlogPost;
import org.focusns.model.common.Page;

public interface BlogPostService {

    BlogPost getBlogPost(long postId);

    void createBlogPost(BlogPost post);

    void modifyBlogPost(BlogPost post);

    void removeBlogPost(BlogPost post);

    Page<BlogPost> fetchPageByCategoryId(Page<BlogPost> page, long categoryId);

    Page<BlogPost> fetchPageByProjectId(Page<BlogPost> page, long projectId);

}
