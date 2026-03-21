public interface PilhaRubroNegra{
    public boolean estaVaziaVermelha();
    public boolean estaVaziaPreta();
    public int sizeVermelha();
    public int sizePreta();
    public Object topVermelha();
    public Object topPreta();
    public void pushVermelha(Object o);
    public void pushPreta(Object o); 
    public Object popVermelha();
    public Object popPreta();
}