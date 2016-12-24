package snapshotEngine;

/**
 * interfaccia contenente i metodi per scattare snapshot o ripristinare lo stato
 * @author Luca
 */
public interface SnapEngine {
    public abstract void snap();
    public abstract void restore();
    public abstract void startDaemon();
    public abstract void setMilliseconds(int millis);
    public abstract int getMilliseconds();
}
