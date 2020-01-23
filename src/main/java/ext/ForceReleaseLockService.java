package ext;

import liquibase.exception.DatabaseException;
import liquibase.exception.LockException;
import liquibase.lockservice.StandardLockService;

/**
 * Forces the liquibase to release a lock in the `databaselock` table.
 * If it is left locked the app will wait indefinitely.
 */
public class ForceReleaseLockService extends StandardLockService {

    @Override
    public int getPriority() {
        return super.getPriority() + 1;
    }

    @Override
    public void waitForLock() throws LockException {
        try {
            super.forceReleaseLock();
        } catch (DatabaseException e) {
            throw new LockException("Could not enforce getting the lock.", e);
        }
        super.waitForLock();
    }
}
