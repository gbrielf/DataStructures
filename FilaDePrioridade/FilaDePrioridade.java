interface FilaDePrioridade{
    public void insert(int k, Object o);
    public Object removeMin();
    public int min();
    public int size();
    public boolean isEmpty();
}