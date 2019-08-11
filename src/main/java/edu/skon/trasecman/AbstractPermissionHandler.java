package edu.skon.trasecman;

import java.lang.SecurityManager;
import java.security.Permission;

public abstract class AbstractPermissionHandler extends SecurityManager {
    private ThreadLocal<Boolean> securityEnabled = ThreadLocal.withInitial(() -> true);

    @Override
    public void checkPermission(Permission perm) {
        if (securityEnabled.get()) {
            try {
                securityEnabled.set(false);
                handleCheckPermission(perm, null);
            } finally {
                securityEnabled.set(true);
            }
        }
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
        if (securityEnabled.get()) {
            try {
                securityEnabled.set(false);
                handleCheckPermission(perm, context);
            } finally {
                securityEnabled.set(true);
            }
        }
    }

    protected abstract void handleCheckPermission(Permission perm, Object context);
}
