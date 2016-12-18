package UIcassa;

/**
 * Gestisce l'accesso controllato agli oggetti che implementano l'interfaccia
 * IdiscountStrategy
 * @see IDiscountStrategy
 * @author Fabio
 */
public class ProxyDiscount implements IDiscountStrategy{
    
    private IDiscountStrategy discountStrategy;

    public ProxyDiscount(IDiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }

    @Override
    public Double getDiscountedPrice(ScontrinoPanel scontrino) {
       return discountStrategy.getDiscountedPrice(scontrino);
    }

    @Override
    public void setAttributes(int attribute1, int attribute2) {
        discountStrategy.setAttributes(attribute1, attribute2);
    }

    @Override
    public String getAttributeString() {
        return discountStrategy.getAttributeString();
    }
    
    public void setDiscountStrategy(IDiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
    }
    
    public IDiscountStrategy getDiscountStrategy() {
        return discountStrategy;
    }
}
