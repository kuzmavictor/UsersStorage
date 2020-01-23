package ua.kharkiv.storage.service.process;

/**
 * A base for processes that can be executed in `UsersStorage` application.
 * <p> It allows you to correctly divide business logic into independent aspects.
 * Each process is a separate aspect of business logic.
 *
 * @param <R>
 *         a type of process execution result
 * @param <P>
 *         a type of process parameter
 */
public interface Process<R, P> {

    /**
     * Handles the parameters using necessary business logic.
     *
     * @param parameter
     *         a parameter to be handled
     * @return the process execution result
     */
    R doHandle(P parameter);
}
