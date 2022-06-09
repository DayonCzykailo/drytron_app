
package drytron.cep_api;

/**
 * @author Dayon
 */
public interface ViaCEPEvents {
    
    public void onCEPSuccess(ViaCEP cep);
    
    public void onCEPError(String cep);
}
