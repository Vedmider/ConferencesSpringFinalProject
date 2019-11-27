package com.study.persistence.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "user_role")
public class Role {

    @Column(name = "id")
    private int id;
    @Column(name = "role_name")
    private String roleTitle;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "role_rights_relation",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "right_id"))
    private List<Right> rights;

    public Role() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Right> getRights() {
        return rights;
    }

    public void setRights(List<Right> rights) {
        this.rights = rights;
    }

    public String getRoleTitle() {
        return roleTitle;
    }

    public void setRoleTitle(String roleTitle) {
        this.roleTitle = roleTitle;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Role)) {
            return false;
        }
        Role role = (Role) obj;

        return id == role.getId()
                && (roleTitle == null ? role.getRoleTitle() == null : roleTitle.equals(role.getRoleTitle()))
                && (rights == null ? role.getRights() == null : rights.equals(role.getRights()));
    }

    @Override
    public int hashCode() {
        int result = 10;
        result = 31 * result + id + (roleTitle == null ? 0 : roleTitle.hashCode()) + (rights == null ? 0 : rights.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return "Role ID " + id + ", roleTitle " + roleTitle;
    }
}
