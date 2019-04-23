package io.nutz.nutzsite.module.sys.controllers;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import io.nutz.nutzsite.module.sys.models.Task;
import io.nutz.nutzsite.module.sys.services.TaskService;
import io.nutz.nutzsite.common.base.Result;;
import org.nutz.dao.Cnd;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Strings;
import org.nutz.log.Log;
import org.nutz.log.Logs;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.POST;
import org.nutz.mvc.annotation.Param;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * 定时任务 信息操作处理
 *
 * @author haiming
 * @date 2019-04-23
 */
@IocBean
@At("/sys/task")
public class TaskController {
    private static final Log log = Logs.get();

    @Inject
    private TaskService taskService;

    @RequiresPermissions("sys:task:view")
    @At("")
    @Ok("th:/sys/task/task.html")
    public void index(HttpServletRequest req) {

    }

    /**
     * 查询定时任务列表
     */
    @RequiresPermissions("sys:task:list")
    @At
    @Ok("json")
    public Object list(@Param("pageNum") int pageNum,
                       @Param("pageSize") int pageSize,
                       @Param("name") String name,
                       HttpServletRequest req) {
        Cnd cnd = Cnd.NEW();
        if (!Strings.isBlank(name)) {
            //cnd.and("name", "like", "%" + name +"%");
        }
        return taskService.tableList(pageNum, pageSize, cnd);
    }

    /**
     * 新增定时任务
     */
    @At("/add")
    @Ok("th:/sys/task/add.html")
    public void add(HttpServletRequest req) {

    }

    /**
     * 新增保存定时任务
     */
    @RequiresPermissions("sys:task:add")
    @At
    @POST
    @Ok("json")
    public Object addDo(@Param("..") Task task, HttpServletRequest req) {
        try {
            taskService.insert(task);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    /**
     * 修改定时任务
     */
    @At("/edit/?")
    @Ok("th://sys/task/edit.html")
    public void edit(String id, HttpServletRequest req) {
        Task task = taskService.fetch(id);
        req.setAttribute("task", task);
    }

    /**
     * 修改保存定时任务
     */
    @RequiresPermissions("sys:task:edit")
    @At
    @POST
    @Ok("json")
    public Object editDo(@Param("..") Task task, HttpServletRequest req) {
        try {
            taskService.update(task);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

    /**
     * 删除定时任务
     */
    @At("/remove")
    @Ok("json")
    @RequiresPermissions("sys:task:remove")
    public Object remove(@Param("ids") String[] ids, HttpServletRequest req) {
        try {
            taskService.delete(ids);
            return Result.success("system.success");
        } catch (Exception e) {
            return Result.error("system.error");
        }
    }

}