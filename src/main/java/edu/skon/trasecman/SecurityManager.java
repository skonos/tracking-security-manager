package edu.skon.trasecman;

import java.security.Permission;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class SecurityManager extends AbstractPermissionHandler {

    @Override
    protected void handleCheckPermission(Permission perm, Object context) {
        List<String> stack = Stream.of(new Exception().getStackTrace())
                .map(a -> a.getClassName() + "." + a.getMethodName())
                .filter(s -> !s.startsWith(SecurityManager.class.getPackage().getName()))
                .filter(s -> !s.startsWith(java.lang.SecurityManager.class.getName()))
                .distinct()
                .collect(Collectors.toList());

        handleEvent(perm, stack);
    }

    protected void handleEvent(Permission perm, List<String> stack) {
        System.out.println("perm = " + perm + " -> " + stack);
    }
}
