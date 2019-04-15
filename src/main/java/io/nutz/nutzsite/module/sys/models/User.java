package io.nutz.nutzsite.module.sys.models;

import io.nutz.nutzsite.common.base.Model;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.nutz.dao.entity.annotation.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 用户信息
 */
@Table("sys_user")
public class User extends Model implements Serializable {
    private static final long serialVersionUID = 1L;

    @Name
    @Column
    @Comment("ID")
    @ColDefine(type = ColType.VARCHAR, width = 32)
    @Prev(els = {@EL("uuid()")})
    private String id;

    /**
     * 部门ID
     */
    @Column("dept_id")
    @Comment("部门ID")
    private String deptId;

    /**
     * 部门父ID
     */
    private String parentId;

    /**
     * 登录名称
     */
    @Column("login_name")
    @Comment("登录名称")
    private String loginName;

    /**
     * 用户名称
     */
    @Column("user_name")
    @Comment("用户名称")
    private String userName;

    /**
     * 密码
     */
    @Column("password")
    @Comment("密码")
    private String password;

    /**
     * 盐加密
     */
    @Column("salt")
    @Comment("盐加密")
    private String salt;

    /**
     * 用户邮箱
     */
    @Column("email")
    @Comment("用户邮箱")
    private String email;

    /**
     * 手机号码
     */
    @Column("phonenumber")
    @Comment("手机号码")
    private String phonenumber;

    /**
     * 用户性别
     */
    @Column("gender")
    @Comment("用户性别")
    private String gender;

    /**
     * 用户头像
     */
    @Column("gender")
    @Comment("用户性别")
    private String avatar;

    /**
     * 帐号状态（0正常 1停用）
     */
    @Column("status")
    @Comment("帐号状态（0正常 1停用） ")
    private boolean status;

    /**
     * 删除标志（0代表存在 1代表删除）
     */
    @Column("del_flag")
    @Comment("删除标记")
    @Prev(els = @EL("$me.flag()"))
    @ColDefine(type = ColType.BOOLEAN)
    private boolean delFlag;

    /**
     * 最后登陆IP
     */
    @Column("login_ip")
    @Comment("最后登陆IP")
    private String loginIp;

    /**
     * 最后登陆时间
     */
    @Column("login_date")
    @Comment("最后登陆时间")
    private Date loginDate;

    /**
     * 部门对象
     */
    @One(field = "deptId")
    private Dept dept;

    /**
     * 角色集合
     */
    @ManyMany(from = "user_id", relation = "sys_user_role", to = "role_id")
    private List<Role> roles;

    /**
     * 角色组
     */
    private String roleIds;

    /**
     * 岗位组
     */
    private String postIds;

    /**
     * 设置 密码 和salt
     * @param password
     */
    public void setPasswordSalt(String password){
        RandomNumberGenerator rng = new SecureRandomNumberGenerator();
        String salt = rng.nextBytes().toBase64();
        setSalt(salt);
        String hashedPasswordBase64 = new Sha256Hash(password, salt, 1024).toBase64();
        setPassword(hashedPasswordBase64);
    }


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getLoginName() {
        return loginName;
    }

    public void setLoginName(String loginName) {
        this.loginName = loginName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean isDelFlag() {
        return delFlag;
    }

    public void setDelFlag(boolean delFlag) {
        this.delFlag = delFlag;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public Dept getDept() {
        return dept;
    }

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public String getRoleIds() {
        return roleIds;
    }

    public void setRoleIds(String roleIds) {
        this.roleIds = roleIds;
    }

    public String getPostIds() {
        return postIds;
    }

    public void setPostIds(String postIds) {
        this.postIds = postIds;
    }
}
