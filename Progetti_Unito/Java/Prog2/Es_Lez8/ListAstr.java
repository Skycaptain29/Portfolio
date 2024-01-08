public abstract class ListAstr
{
    public abstract boolean empty();
    public abstract int size();
    public abstract boolean contains(int x);
    public abstract ListAstr insert(int x);
    public abstract ListAstr append(ListAstr l);
    // NUOVI METODI
    public abstract int sum();
    public abstract int get(int i);
    public abstract ListAstr succ();
    public abstract ListAstr filter_le(int x);
    public abstract ListAstr filter_gt(int x);
    public abstract ListAstr intersect(ListAstr l);
}
