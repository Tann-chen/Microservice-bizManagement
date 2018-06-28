package com.user.comm.result;


import com.user.domain.enums.PermissionType;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class ModulePermissions implements Serializable {
    @Getter
    @Setter
    private boolean create;

    @Getter
    @Setter
    private boolean update;

    @Getter
    @Setter
    private boolean read;

    @Getter
    @Setter
    private boolean delete;


    public ModulePermissions() {
        this.create = false;
        this.update = false;
        this.read = false;
        this.delete = false;
    }

    public void setFieldValue(String type, boolean value) {
        switch (type) {
            case "create":
                this.create = value;
                break;
            case "update":
                this.update = value;
                break;
            case "read":
                this.read = value;
                break;
            case "delete":
                this.delete = value;
                break;
        }
    }
}
