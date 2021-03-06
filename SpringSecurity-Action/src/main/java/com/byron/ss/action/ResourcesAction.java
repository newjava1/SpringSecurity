

package com.byron.ss.action;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.byron.ss.common.base.BaseStruts2Action;
import com.byron.ss.model.Resources;
import com.byron.ss.service.ResourcesManager;
import com.byron.ss.service.RolesResourcesManager;
import com.opensymphony.xwork2.ModelDriven;
import com.opensymphony.xwork2.Preparable;


/**
 * @author byron
 * @version 1.0
 * @since 1.0
 */


public class ResourcesAction extends BaseStruts2Action implements Preparable,ModelDriven{
	//默认多列排序,example: username desc,createTime asc
	protected static final String DEFAULT_SORT_COLUMNS = null; 
	
	//forward paths
	protected static final String QUERY_JSP = "/ss/Resources/query.jsp";
	protected static final String LIST_JSP= "/ss/Resources/list.jsp";
	protected static final String CREATE_JSP = "/ss/Resources/create.jsp";
	protected static final String EDIT_JSP = "/ss/Resources/edit.jsp";
	protected static final String SHOW_JSP = "/ss/Resources/show.jsp";
	//redirect paths,startWith: !
	protected static final String LIST_ACTION = "!/ss/Resources/list.do";
	
	private ResourcesManager resourcesManager;
	private RolesResourcesManager rolesResourcesManager;
	
	
	public void setRolesResourcesManager(RolesResourcesManager rolesResourcesManager) {
		this.rolesResourcesManager = rolesResourcesManager;
	}

	private Resources resources;
	java.lang.String id = null;
	private String[] items;

	public void prepare() throws Exception {
		if (isNullOrEmptyString(id)) {
			resources = new Resources();
		} else {
			resources = (Resources)resourcesManager.getById(id);
		}
	}
	
	/** 增加setXXXX()方法,spring就可以通过autowire自动设置对象属性,注意大小写 */
	public void setResourcesManager(ResourcesManager manager) {
		this.resourcesManager = manager;
	}	
	
	public Object getModel() {
		return resources;
	}
	
	public void setId(java.lang.String val) {
		this.id = val;
	}

	public void setItems(String[] items) {
		this.items = items;
	}
	
	/** 执行搜索 */
	/*public String list() {
		ResourcesQuery query = newQuery(ResourcesQuery.class,DEFAULT_SORT_COLUMNS);
		
		Page page = resourcesManager.findPage(query);
		savePage(page,query);
		
		this.setSfSelectIndex("resources");
		String ssPage = "/ss/Resources/list.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return LIST_JSP;
	}*/
	
	/** 查看对象*/
	public String show() {
		String ssPage = "/ss/Resources/show.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return SHOW_JSP;
	}
	
	/** 进入新增页面*/
	public String create() {
		String ssPage = "/ss/Resources/create.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return CREATE_JSP;
	}
	
	/** 保存新增对象 */
	/*public String save() {
		resourcesManager.save(resources);
		Flash.current().success(CREATED_SUCCESS); //存放在Flash中的数据,在下一次http请求中仍然可以读取数据,error()用于显示错误消息
		return list();
//		return LIST_ACTION;
	}*/
	
	/**进入更新页面*/
	public String edit() {
		String ssPage = "/ss/Resources/edit.jsp";
		this.setMainPage(ssPage);
		return indexPage;
//		return EDIT_JSP;
	}
	
	/**保存更新对象*/
	/*public String update() {
		resourcesManager.update(this.resources);
		Flash.current().success(UPDATE_SUCCESS);
		return list();
//		return LIST_ACTION;
	}*/
	
	/**删除对象*/
	/*public String delete() {
		for(int i = 0; i < items.length; i++) {
			Hashtable params = HttpUtils.parseQueryString(items[i]);
			java.lang.String id = new java.lang.String((String)params.get("id"));
			resourcesManager.removeById(id);
			rolesResourcesManager.doDeleteByResources(id);
		}
		Flash.current().success(DELETE_SUCCESS);
		return list();
//		return LIST_ACTION;
	}*/

	public String doList() {
		HttpServletRequest request = this.getRequest();
		
		List<Resources> list = this.resourcesManager.findAll();
		request.setAttribute("resourceList", list);
		
		request.setAttribute("context", "/ss/Resources/resourceList.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "resource");
		doInitTMenu();
		
		this.setBoxPath(this.getBoxPath());
		this.setLeftPath(systemLeft);
		this.setRightPath("/ss/Resources/resourceList.jsp");
		return "/index.jsp";
		//return "/myIndex.jsp";
	}
	
	
	
	

	public String doSavePage() {
		HttpServletRequest request = this.getRequest();
		
		request.setAttribute("context", "/ss/Resources/resourceSave.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "resource");
		//return "/myIndex.jsp";
		
		return "/ss/Resources/resourceSaveOpenWin.jsp";
	}
	
	public String doSave() {
		String msg = "success";
		HttpServletRequest request = this.getRequest();
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String remark = request.getParameter("remark");
		
		Resources resource = new Resources();
		resource.setName(name);
		resource.setEnable("1");
		resource.setMemo("1");
		resource.setPriority(1);
		resource.setType(1);
		resource.setUrl(url);
		resource.setCreatedt(new Date());
		resource.setUpdatedt(new Date());
		try {
			resourcesManager.save(resource);
		} catch(Exception e) {
			msg = "error";
			e.printStackTrace();
		}
		
		//return "!/ss/Resources/doList.do";
		request.setAttribute("msg", msg);
		return "/ss/Resources/saveResult.jsp";
	}
	
	public void doSaveAjax() {
		String msg = "success";
		HttpServletRequest request = this.getRequest();
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String memo = request.getParameter("memo");
		
		Resources resource = new Resources();
		resource.setName(name);
		resource.setEnable("1");
		resource.setMemo("1");
		resource.setPriority(1);
		resource.setType(1);
		resource.setUrl(url);
		resource.setMemo(memo);
		resource.setCreatedt(new Date());
		resource.setUpdatedt(new Date());
		try {
			/*List<Resources> list = this.resourcesManager.getEntityDao().findAllBy("name", name);*/
			List<Resources> list = this.resourcesManager.getResourcesByName(name);
			if(null != list && list.size() > 0) {
				out("{\"success\":false,\"message\":\"数据库中已存在该资源名\"}");
				return;
			}
			resourcesManager.save(resource);
		} catch(Exception e) {
			msg = "error";
			e.printStackTrace();
			out("{\"success\":false,\"message\":\"保存失败\"}");
			return;
		}
		
		out("{\"success\":true,\"message\":\"保存成功\"}");
	}
	
	public String doEditPage() {
		HttpServletRequest request = this.getRequest();
		
		String id = request.getParameter("id");
		Resources resource = this.resourcesManager.getById(id);
		request.setAttribute("resources", resource);
		
		/*request.setAttribute("context", "/ss/Resources/resourceSave.jsp");
		request.setAttribute("left", systemLeft);
		request.setAttribute("top", "/top.jsp");
		request.setAttribute("footer", "/footer.jsp");
		request.setAttribute("lMenuSelect", "resource");*/
		//return "/myIndex.jsp";
		
		return "/ss/Resources/resourceEditOpenWin.jsp";
	}
	
	public void doEditAjax() {
		String msg = "success";
		HttpServletRequest request = this.getRequest();
		String id = request.getParameter("id");
		Resources resource = this.resourcesManager.getById(id);
		String name = request.getParameter("name");
		String url = request.getParameter("url");
		String memo = request.getParameter("memo");
		
		//Resources resource = new Resources();
		resource.setName(name);
		//resource.setEnable("1");
		//resource.setMemo("1");
		resource.setPriority(1);
		resource.setType(1);
		resource.setMemo(memo);
		//resource.setUrl(url);
		//resource.setCreatedt(new Date());
		resource.setUpdatedt(new Date());
		try {
			resourcesManager.update(resource);
		} catch(Exception e) {
			msg = "error";
			out("{\"success\":false,\"message\":\"修改失败\"}");
			e.printStackTrace();
			return;
		}
		
		out("{\"success\":true,\"message\":\"修改成功\"}");
	}
	
	public void doDeleteAjax() {
		String ret = "success";
		HttpServletRequest request = this.getRequest();
		String id = request.getParameter("id");
		try {
			this.resourcesManager.doDeleteResource(id);
			//resourcesManager.removeById(id);
		} catch(Exception e) {
			e.printStackTrace();
			ret = "删除数据失败!";
		}
		out(ret);
	}
}
